package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.NewContactData;

/**
 * Created by kuanysh on 02.03.16.
 */
public class ContactModificationTest extends TestBase {

    @Test
    public void testContactModification() {
        app.getNavigationHelper().gotoHomePage();
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new NewContactData("TestName", "MiddleName", "LastName", "NickName", "767854345", "test1"), true);
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().modifySelectedContact();
        app.getContactHelper().fillContactForm(new NewContactData("test name", "test middleName", "test lastName", "test nickName", "test telephone", null), false);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnToHomePage();
    }
}
