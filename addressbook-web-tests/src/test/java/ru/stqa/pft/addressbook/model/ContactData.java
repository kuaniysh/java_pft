package ru.stqa.pft.addressbook.model;

/**
 * Класс который описывает новый контакт
 */
public class ContactData {
    private int id = Integer.MAX_VALUE;
    private String name;
    private String middleName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return telephone != null ? telephone.equals(that.telephone) : that.telephone == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (telephone != null ? telephone.hashCode() : 0);
        return result;
    }

    private String lastName;
    private String nickName;
    private String telephone;
    private String group;

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

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withName(String name) {
        this.name = name;
        return this;
    }

    public ContactData withMiddleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public ContactData withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ContactData withNickName(String nickName) {
        this.nickName = nickName;
        return this;
    }

    public ContactData withTelephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }
}
