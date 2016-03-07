package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.NewContactData;

/**
 * Created by kuanysh on 01.03.16.
 */
public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    /**
     * Метод для заполнения данных нового контакта
     *
     * @param newContactData
     */
    public void fillNewContact(NewContactData newContactData) {
        type(By.name("firstname"), newContactData.getName());
        type(By.name("middlename"), newContactData.getMiddleName());
        type(By.name("lastname"), newContactData.getLastName());
        type(By.name("nickname"), newContactData.getNickName());
        type(By.name("mobile"), newContactData.getTelephone());
    }

    /**
     * Метод для добавление нового контакта
     */
    public void addNewContact() {
        click(By.linkText("add new"));
    }

    /**
     * Метод сохраняет(принятие) нового контакта
     */
    public void clickToEnter() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    /**
     * Выбрать контакт
     */
    public void selectContact() {
        click(By.name("selected[]"));
    }

    /**
     * Возврат в каталог home
     */
    public void returnToHomePage() {
        click(By.linkText("home"));
    }

    /**
     * Удаляет контакт
     */
    public void deleteSelectedContact() {
        click(By.xpath(".//*[@value = 'Delete']"));
    }

    /**
     * Нажимает на иконку для редактирования контакта
     */
    public void modifySelectedContact() {
        click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
    }

    /**
     * Заполняет поля контакта
     *
     * @param newContactData объект типа NewContactData
     */
    public void fillContactForm(NewContactData newContactData) {
        type(By.name("firstname"), newContactData.getName());
        type(By.name("middlename"), newContactData.getMiddleName());
        type(By.name("lastname"), newContactData.getLastName());
        type(By.name("nickname"), newContactData.getNickName());
        type(By.name("mobile"), newContactData.getTelephone());
    }

    /**
     * Нажав на обновить обновляет отредактированный контакт
     */
    public void submitContactModification() {
        click(By.name("update"));
    }
}
