package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

/**
 * Created by kuanysh on 02.03.16.
 */
public class ContactDeleteTest extends TestBase {

    @Test
    public void testContactDelete() {
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteSelectedContact();
        app.getContactHelper().returnToHomePage();
    }
}
