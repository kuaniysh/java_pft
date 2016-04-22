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
public class ContactDeleteTest extends TestBase {

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

    /**
     * Тест проверяет удаления контакта в окне HomePage
     */
    @Test
    public void testContactDelete() {
        Contacts before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        app.contact().deleteWithAccept(deletedContact);
        Contacts after = app.contact().all();
        assertEquals(after.size(), before.size() - 1);

        assertThat(after, equalTo(before.without(deletedContact)));
    }

    /**
     * Тест проверяет удаления контакта в окне редактирования контакта
     * Отличается от предыдущего теста тем что в окне редактирования контакта
     * при удаления контакта не выходит alert
     */
    @Test
    public void testContactDeleteInModifyPage() {
        Contacts before = app.db().contacts();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        app.goTo().homePage();

        assertEquals(app.contact().count(), before.size() - 1);
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(deletedContact)));
    }
}
