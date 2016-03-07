package ru.stqa.pft.addressbook.model;

/**
 * Класс который описывает новый контакт
 */
public class NewContactData {
    private final String name;
    private final String middleName;
    private final String lastName;
    private final String nickName;
    private final String telephone;
    private String group;

    public NewContactData(String name, String middleName, String lastName, String nickName, String telephone, String group) {
        this.name = name;
        this.middleName = middleName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.telephone = telephone;
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getGroup() {
        return group;
    }
}
