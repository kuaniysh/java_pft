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
        if (app.db().contacts().size() == 0) {
            app.goTo().homePage();
            app.contact().createContact(new ContactData()
                    .withFirstname("TestName")
                    .withMiddleName("MiddleName")
                    .withLastName("LastName")
                    .withNickName("NickName")
                    .withTelephone("767854345")
                    .withGroup("test1"), true);
        } else {
            app.goTo().homePage();
        }
    }

    @Test
    public void testContactModification() {
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        int index = before.size() - 1;
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId()).withFirstname("TestName").withMobilePhone("767854345");
        app.contact().modifyContact(index, contact);
        app.goTo().homePage();
        Contacts after = app.contact().all();
        assertEquals(after.size(), before.size());
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }
}
