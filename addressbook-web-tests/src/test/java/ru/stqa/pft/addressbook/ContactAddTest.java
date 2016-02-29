package ru.stqa.pft.addressbook;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.*;

public class ContactAddTest {
    FirefoxDriver wd;
    
    @BeforeMethod
    public void setUp() throws Exception {
        wd = new FirefoxDriver();
        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

        wd.get("http://localhost/addressbook/group.php");
        login("admin", "secret");
    }

    /**
     * Метод для авторизации
     * @param userName имя пользователя
     * @param password пароль пользователя
     */
    private void login(String userName, String password) {
        wd.findElement(By.name("user")).click();
        wd.findElement(By.name("user")).clear();
        wd.findElement(By.name("user")).sendKeys(userName);
        wd.findElement(By.name("pass")).click();
        wd.findElement(By.name("pass")).clear();
        wd.findElement(By.name("pass")).sendKeys(password);
        wd.findElement(By.xpath("//form[@id='LoginForm']/input[3]")).click();
    }

    @Test
    public void testAddContact() {
        addNewContact();
        fillNewContact(new NewContactData("TestName", "MiddleName", "LastName", "NickName", "767854345"));
        clickToEnter();
    }

    /**
     * Метод для заполнения данных нового контакта
     * @param newContactData
     */
    private void fillNewContact(NewContactData newContactData) {
        wd.findElement(By.name("firstname")).click();
        wd.findElement(By.name("firstname")).clear();
        wd.findElement(By.name("firstname")).sendKeys(newContactData.getName());
        wd.findElement(By.name("middlename")).click();
        wd.findElement(By.name("middlename")).clear();
        wd.findElement(By.name("middlename")).sendKeys(newContactData.getMiddleName());
        wd.findElement(By.name("lastname")).click();
        wd.findElement(By.name("lastname")).clear();
        wd.findElement(By.name("lastname")).sendKeys(newContactData.getLastName());
        wd.findElement(By.name("nickname")).click();
        wd.findElement(By.name("nickname")).clear();
        wd.findElement(By.name("nickname")).sendKeys(newContactData.getNickName());
        wd.findElement(By.name("mobile")).click();
        wd.findElement(By.name("mobile")).clear();
        wd.findElement(By.name("mobile")).sendKeys(newContactData.getTelephone());
    }

    /**
     * Метод для добавление нового контакта
     */
    private void addNewContact() {
        wd.findElement(By.linkText("add new")).click();
    }

    /**
     * Метод сохраняет(принятие) нового контакта
     */
    private void clickToEnter() {
        wd.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
    }

    @AfterMethod
    public void tearDown() {
        wd.quit();
    }
    
    public static boolean isAlertPresent(FirefoxDriver wd) {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
}
