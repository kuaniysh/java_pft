package ru.stqa.pft.addressbook.tests;

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

    @Test
    public void testContactDetails() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();

        // информация о контакте, представленной в форме редактирования контакта
        ContactData contactDetailsInfoForm = app.contact().infoFromEditForm(contact);

        // переход на страницу с подробной информацией о контакте
        app.contact().detailsSelectedContact();

        // информация о контакте, которая представлена на тестируемой странице
        String contactInfo = contact.getLastName() + contact.getAddress() + contact.getEmail();

        /**
         * сравнивание информации о контакте, которая представлена на тестируемой странице,
         * с информацией, представленной в форме редактирования контакта.
         */
        assertThat(contactInfo, equalTo(mergeDetails(contactDetailsInfoForm)));
    }

    private String mergeDetails(ContactData contact) {
        return Arrays.asList(
                contact.getLastName(),
                contact.getAddress(),
                contact.getEmail())
                .stream().filter((s) -> ! s.equals(""))
                .collect(Collectors.joining(""));
    }
}
