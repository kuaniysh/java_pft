package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import java.util.List;

/**
 * Created by kuanysh on 24.04.16.
 */
public class RegistrationTests extends TestBase {

    @BeforeMethod
    public void startMailServer() throws Exception{
        app.mail().start();
    }

    @Test
    public void testRegistration() throws Exception {
        long now = System.currentTimeMillis();
        String email = String.format("user%s@localhost.localdomain",now);
        String password = "password";
        String user = "user" + now;
        app.registration().start(user, email);
        List<MailMessage> mailMessages =  app.mail().waitForMail(2,10000);
        String confirmationLink = findConfirmationLink(mailMessages, email);
        app.registration().finish(confirmationLink,password);
        app.newSession().login(user,password);
        app.newSession().isLoggedInAs(user);
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m)->m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return  regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() throws Exception {
        app.mail().stop();
    }
}