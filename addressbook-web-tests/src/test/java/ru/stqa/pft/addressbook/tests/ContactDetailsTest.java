package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by kuanysh on 24.03.16.
 */
public class ContactDetailsTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            ContactData contactData = new ContactData()
                    .withFirstname("TestName")
                    .withMiddleName("MiddleName")
                    .withLastName("LastName")
                    .withAddress("Address")
                    .withHomePhone("111")
                    .withMobilePhone("222")
                    .withWorkPhone("333")
                    .withEmail("test_1@mail.ru")
                    .withEmail2("test_2@yandex.ru")
                    .withEmail3("test_3@gmail.com")
                    .withGroup("[none]");
            app.contact().createContact(contactData, true);
        }
    }

    @Test
    public void testContactDetails() {
        app.goTo().homePage();

        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFormEditForm = app.contact().infoFromEditForm(contact);
        assertThat(app.contact().infoDetailsContact(contact), equalTo(mergeAll(contactInfoFormEditForm)));
    }

    private String mergeFirstMiddleLastNames(ContactData contact) {
        return Arrays.asList(contact.getFirstName(), contact.getMiddleName(), contact.getLastName())
                .stream()
                .filter((s) -> !s.equals(""))
                .map(ContactDetailsTest::cleaned)
                .collect(Collectors.joining(" "));
    }

    private String mergePersonalData(ContactData contact) {
        return Arrays.asList(mergeFirstMiddleLastNames(contact), contact.getAddress())
                .stream()
                .filter((s) -> !s.equals(""))
                .map(ContactDetailsTest::cleaned)
                .collect(Collectors.joining("\n"));
    }

    private String mergePhones(ContactData contact) {
        return Arrays.asList(
                "H: " + contact.getHomePhone(),
                "M: " + contact.getMobilePhone(),
                "W: " + contact.getWorkPhone())
                .stream().filter((s) -> !s.equals(" "))
                .map(ContactDetailsTest::cleaned)
                .collect(Collectors.joining("\n"));
    }

    private String mergeEmails(ContactData contact) {
        String[] getEmail = contact.getEmail().split("@");
        String[] getEmail1 = contact.getEmail2().split("@");
        return Arrays.asList(
                contact.getEmail() + " (www." + getEmail[1] + ")",
                contact.getEmail2() + " (www." + getEmail1[1] + ")",
                contact.getEmail3())
                .stream()
                .filter((s) -> !s.equals(""))
                .map(ContactDetailsTest::cleaned)
                .collect(Collectors.joining("\n"));
    }

    private String mergeAll(ContactData contact) {
        return Arrays.asList(mergePersonalData(contact), mergePhones(contact), mergeEmails(contact))
                .stream()
                .filter((s) -> !s.equals(""))
                .collect(Collectors.joining("\n\n"));
    }

    public static String cleaned(String phone) {
        return phone.replaceAll("\\s{2,}", " ");
    }
}
