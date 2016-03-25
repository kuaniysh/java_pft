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
public class ContactEmailTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            ContactData contactData = new ContactData()
                    .withFirstname("TestName")
                    .withMiddleName("MiddleName")
                    .withLastName("LastName")
                    .withAddress("Address")
                    .withTelephone("767854345")
                    .withMobilePhone("79177121162")
                    .withEmail("test_1@mail.ru")
                    .withEmail2("test_2@yandex.ru")
                    .withEmail3("test_3@gmail.com")
                    .withGroup("test1");
            app.contact().createContact(contactData, true);
        }
    }

    @Test
    public void testContactEmail() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllEmails(),equalTo(mergeEmails(contactInfoFromEditForm)));

    }

    private String mergeEmails(ContactData contact) {
        return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
                .stream().filter((s) -> ! s.equals(""))
                .map(ContactEmailTest::cleaned)
                .collect(Collectors.joining("\n"));
    }

    public static String cleaned(String email) {
        return email.replaceAll("\\s{2,}"," ");
    }
}
