package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.NewContactData;

public class ContactAddTest extends TestBase {

    @Test
    public void testAddContact() {
        app.getContactHelper().addNewContact();
        app.getContactHelper().fillContactForm(new NewContactData("TestName", "MiddleName", "LastName", "NickName", "767854345", "test1"), true);
        app.getContactHelper().clickToEnter();
    }
}
