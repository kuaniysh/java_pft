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
        wd.findElement(By.cssSelector("input[value = '" + id + "']")).click();
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
     * Нажимает на иконку для детали контакта
     */
    public void detailsSelectedContact() {
        click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[7]/a/img"));
    }

    /**
     * Заполняет поля контакта
     *
     * @param contactData объект типа ContactData
     */
    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("middlename"), contactData.getMiddleName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("nickname"), contactData.getNickName());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("work"), contactData.getWorkPhone());
        type(By.name("email"), contactData.getEmail());
        type(By.name("email2"), contactData.getEmail2());
        type(By.name("email3"), contactData.getEmail3());
        attach(By.name("photo"), contactData.getPhoto());

        if (creation) {
            if (isElementPresent(By.name("new_group"))) {
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());

            }
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
        contactCache = null;
        returnToHomePage();
    }

    public void modifyContact(int index, ContactData contactData) {
        selectContact(index);
        modifySelectedContact();
        fillContactForm(contactData, false);
        submitContactModification();
        contactCache = null;
        returnToHomePage();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        modifySelectedContact();
        deleteSelectedContact();
        contactCache = null;
        returnToHomePage();
    }

    public void deleteWithAccept(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectedContact();
        accept();
        contactCache = null;
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

    public int count() {
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
            ContactData contact = new ContactData().withId(id).withFirstname(name).withLastName(lastName).withTelephone(telephone);
            contacts.add(contact);
        }
        return contacts;
    }

    private Contacts contactCache = null;

    public Contacts all() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> rows = wd.findElements(By.name("entry"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));

            String lastName = cells.get(1).getText();
            String firstname = cells.get(2).getText();
            String address = cells.get(3).getText();
            String email = cells.get(4).getText();
            String allPhones = cells.get(5).getText();

            ContactData contact = new ContactData()
                    .withId(id)
                    .withFirstname(firstname)
                    .withLastName(lastName)
                    .withAllPhones(allPhones)
                    .withAddress(address)
                    .withAllEmails(email);
            contactCache.add(contact);
        }
        return new Contacts(contactCache);
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String middlename = wd.findElement(By.name("middlename")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId())
                .withFirstname(firstname)
                .withMiddleName(middlename)
                .withLastName(lastname)
                .withHomePhone(home)
                .withMobilePhone(mobile)
                .withWorkPhone(work)
                .withEmail(email)
                .withEmail2(email2)
                .withEmail3(email3)
                .withAddress(address);
    }

    private void initContactModificationById(int id) {
        WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
        WebElement row = checkbox.findElement(By.xpath("./../.."));
        List<WebElement> cells = row.findElements(By.tagName("td"));
        cells.get(7).findElement(By.tagName("a")).click();
    }

    public String infoDetailsContact(ContactData contact) {
        initContactDetailsById(contact.getId());
        return wd.findElement(By.id("content")).getText();
    }

    private void initContactDetailsById(int id) {
        WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
        WebElement row = checkbox.findElement(By.xpath("./../.."));
        List<WebElement> cells = row.findElements(By.tagName("td"));
        cells.get(6).findElement(By.tagName("a")).click();
    }
}
