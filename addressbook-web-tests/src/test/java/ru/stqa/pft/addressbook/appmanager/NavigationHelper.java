package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by kuanysh on 01.03.16.
 */
public class NavigationHelper extends HelperBase {

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    /**
     * Переход в каталог groups
     */
    public void gotoGroupPage() {
        click(By.linkText("groups"));
    }

    /**
     * Переход в каталог home
     */
    public void gotoHomePage() {
        click(By.linkText("home"));
    }
}
