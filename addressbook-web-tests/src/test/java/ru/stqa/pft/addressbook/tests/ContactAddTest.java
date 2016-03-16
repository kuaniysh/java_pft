package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactAddTest extends TestBase {

    @Test
    public void testAddContact() {
        List<ContactData> before = app.getContactHelper().getContactList();
        ContactData contactData = new ContactData("TestName", "MiddleName", "LastName", "NickName", "767854345", "test1");
        app.getContactHelper().createContact(contactData, true);
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(contactData);
        Comparator<? super ContactData> byId = (q1 , q2) -> Integer.compare(q1.getId(), q2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
