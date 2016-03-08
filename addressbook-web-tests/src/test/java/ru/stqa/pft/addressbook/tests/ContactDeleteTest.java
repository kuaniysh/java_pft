package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.NewContactData;

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
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new NewContactData("TestName", "MiddleName", "LastName", "NickName", "767854345", "test1"), true);
        }
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
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new NewContactData("TestName", "MiddleName", "LastName", "NickName", "767854345", "test1"), true);
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().modifySelectedContact();
        app.getContactHelper().deleteSelectedContact();
        app.getContactHelper().returnToHomePage();
    }
}
