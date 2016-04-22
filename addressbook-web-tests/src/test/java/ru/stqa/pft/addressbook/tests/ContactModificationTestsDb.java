package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by kuanysh on 22.04.16.
 */
public class ContactModificationTestsDb extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.goTo().homePage();
            ContactData contact = new ContactData().withFirstname("TestFirstName").withLastName("TestLastName")
                    .withMiddleName("TestMiddleName")
                    .withMobilePhone("79135356446");
            app.contact().createContact(contact, true);
        } else {
            app.goTo().homePage();
        }
    }

    @Test
    public void testContactModificationDb() {
        Contacts before = app.db().contacts();
        ContactData modifyContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifyContact.getId())
                .withFirstname("TestFirstName").withLastName("TestLastName").withMobilePhone("79135356446");
        app.contact().modify(contact, false);
        app.goTo().homePage();
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(modifyContact).withAdded(contact)));
    }
}