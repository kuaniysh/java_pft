package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kuanysh on 01.03.16.
 */
public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
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
    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value = '"+ id +"']")).click();
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
     * @param contactData объект типа ContactData
     */
    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getName());
        type(By.name("middlename"), contactData.getMiddleName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("nickname"), contactData.getNickName());
        type(By.name("mobile"), contactData.getTelephone());

        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    /**
     * Нажав на обновить обновляет отредактированный контакт
     */
    public void submitContactModification() {
        click(By.name("update"));
    }

    /**
     * Метод для создания контакта
     *
     * @param contactData
     * @param b
     */
    public void createContact(ContactData contactData, boolean b) {
        addNewContact();
        fillContactForm(contactData, b);
        clickToEnter();
        returnToHomePage();
    }

    public void modifyContact(int index, ContactData contactData) {
        selectContact(index);
        modifySelectedContact();
        fillContactForm(contactData, false);
        submitContactModification();
        returnToHomePage();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        modifySelectedContact();
        deleteSelectedContact();
        returnToHomePage();
    }

    public void deleteWithAccept(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectedContact();
        accept();
        returnToHomePage();
    }

    /**
     * Метод который проверяет существует ли контакт
     *
     * @return возвращает true/false
     */
    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public List<ContactData> list() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            String name = element.findElement(By.xpath("./td[3]")).getText();
            String lastName = element.findElement(By.xpath("./td[2]")).getText();
            String telephone = element.findElement(By.xpath("./td[6]")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
            ContactData contact = new ContactData().withId(id).withName(name).withLastName(lastName).withTelephone(telephone);
            contacts.add(contact);
        }
        return contacts;
    }

    public Contacts all() {
        Contacts contacts = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            String name = element.findElement(By.xpath("./td[3]")).getText();
            String lastName = element.findElement(By.xpath("./td[2]")).getText();
            String telephone = element.findElement(By.xpath("./td[6]")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
            ContactData contact = new ContactData().withId(id).withName(name).withLastName(lastName).withTelephone(telephone);
            contacts.add(contact);
        }
        return contacts;
    }
}
