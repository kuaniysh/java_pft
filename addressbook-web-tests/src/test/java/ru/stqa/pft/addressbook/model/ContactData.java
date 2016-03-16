package ru.stqa.pft.addressbook.model;

/**
 * Класс который описывает новый контакт
 */
public class ContactData {
    private int id;
    private final String name;
    private final String middleName;
    private final String lastName;
    private final String nickName;
    private final String telephone;
    private String group;

    public ContactData(int id, String name, String middleName, String lastName, String nickName, String telephone, String group) {
        this.id = id;
        this.name = name;
        this.middleName = middleName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.telephone = telephone;
        this.group = group;
    }

    public ContactData(int id, String name, String middleName, String lastName, String nickName, String telephone) {
        this.id = id;
        this.name = name;
        this.middleName = middleName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.telephone = telephone;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        return name != null ? name.equals(that.name) : that.name == null;

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    public ContactData(String name, String middleName, String lastName, String nickName, String telephone, String group) {
        this.id = Integer.MAX_VALUE;
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

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", group='" + group + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }
}
