package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

/**
 * Created by kuanysh on 02.03.16.
 */
public class ContactDeleteTest extends TestBase {

    /**
     * Тест проверяет удаления контакта в окне HomePage
     */
    @Test
    public void testContactDelete() {
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteSelectedContact();
        app.getContactHelper().accept();
        app.getContactHelper().returnToHomePage();
    }

    /**
     * Тест проверяет удаления контакта в окне редактирования контакта
     * Отличается от предыдущего теста тем что в окне редактирования контакта
     * при удаления контакта не выходит alert
     */
    @Test
    public void testContactDeleteInModifyPage() {
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().selectContact();
        app.getContactHelper().modifySelectedContact();
        app.getContactHelper().deleteSelectedContact();
        app.getContactHelper().returnToHomePage();
    }
}
