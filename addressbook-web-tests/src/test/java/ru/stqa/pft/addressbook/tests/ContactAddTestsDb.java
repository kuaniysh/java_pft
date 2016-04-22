package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by kuanysh on 20.04.16.
 */
public class ContactAddTestsDb extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            GroupData newGroup = new GroupData().withName("test").withHeader("test2").withFooter("test3");
            app.group().create(newGroup);
        }
    }

    @Test
    public void testContactCreationDb() {
        app.goTo().homePage();
        Contacts before = app.db().contacts();
        ContactData contact = new ContactData().withFirstname("Test").withLastName("Test")
                .withMiddleName("Test").withMobilePhone("79177121162").withGroup("test1");

        app.contact().createContact(contact, true);
        Contacts after = app.db().contacts();
        app.goTo().homePage();
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }
}
