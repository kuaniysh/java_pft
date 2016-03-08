package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.GroupData;

/**
 * Created by kuanysh on 29.02.16.
 */
public class GroupHelper extends HelperBase {

    public GroupHelper(WebDriver wd) {
        super(wd);
    }

    /**
     * Возврат в каталог groups
     */
    public void returnToGroupPage() {
        click(By.linkText("group page"));
    }

    /**
     * Принятие о создания группы
     */
    public void submitGroupCreation() {
        click(By.name("submit"));
    }

    /**
     * Заполняет поля группы
     *
     * @param groupData
     */
    public void fillGroupForm(GroupData groupData) {
        type(By.name("group_name"), groupData.getName());
        type(By.name("group_header"), groupData.getHeader());
        type(By.name("group_footer"), groupData.getFooter());
    }

    /**
     * Инициализация создания группы
     */
    public void initGroupCreation() {
        click(By.name("new"));
    }

    /**
     * Удаления группы
     */
    public void deleteSelectedGroups() {
        click(By.name("delete"));
    }

    /**
     * Выбор группы
     */
    public void selectGroup() {
        click(By.name("selected[]"));
    }

    /**
     * Инициализация редактирования группы
     */
    public void initGroupModification() {
        click(By.name("edit"));

    }

    /**
     * Нажав на обновить обновляет отредактированную группу
     */
    public void submitGroupModification() {
        click(By.name("update"));
    }

    public void createGroup(GroupData group) {
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        returnToGroupPage();
    }

    public boolean isThereAGroup() {
        return isElementPresent(By.name("selected[]"));
    }
}
