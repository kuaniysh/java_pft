package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

/**
 * Created by kuanysh on 02.03.16.
 */
public class ContactModificationTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            app.contact().createContact(new ContactData()
                    .withName("TestName")
                    .withMiddleName("MiddleName")
                    .withLastName("LastName")
                    .withNickName("NickName")
                    .withTelephone("767854345")
                    .withGroup("test1"), true);
        }
    }

    @Test
    public void testContactModification() {
        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        int index = before.size() - 1;
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId()).withName("TestName").withTelephone("767854345");
        app.contact().modifyContact(index, contact);
        Contacts after = app.contact().all();
        assertEquals(after.size(), before.size());
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }
}
