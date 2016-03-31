package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddTest extends TestBase {

    @Test
    public void testAddContact() {
        File photo = new File("src/test/resources/load.jpeg");

        Contacts before = app.contact().all();
        ContactData contactData = new ContactData()
                .withFirstname("TestName")
                .withMiddleName("MiddleName")
                .withLastName("LastName")
                .withNickName("NickName")
                .withMobilePhone("767854345")
                .withGroup("test1")
                .withPhoto(photo);
        app.contact().createContact(contactData, true);
        app.goTo().homePage();
        Contacts after = app.contact().all();
        assertThat(after.size(), equalTo(before.size() + 1));

        assertThat(after, equalTo(before.withAdded(contactData.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }
}
