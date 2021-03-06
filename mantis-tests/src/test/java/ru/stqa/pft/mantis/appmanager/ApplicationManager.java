package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by kuanysh on 29.02.16.
 */
public class ApplicationManager {

    private WebDriver wd;
    private String browser;
    private  final Properties properties;
    private FtpHelper ftp;
    private RegistrationHelper registration;
    private AuthorizationHelper authorization;
    private MailHelper mailHelper;
    private NavigationHelper navigationHelper;
    private DbHelper dbHelper;

    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException {
        dbHelper = new DbHelper();
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
    }

    public void stop() {
        if (wd != null) {
            wd.quit();
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public HttpSession newSession() {
        return new HttpSession(this);
    }

    public FtpHelper ftp(){
        if (ftp == null){
            ftp = new FtpHelper(this);
        }
        return ftp;
    }

    public AuthorizationHelper authorization() throws Exception {
        if (authorization == null) {
            authorization = new AuthorizationHelper(this);
        }
        return  authorization;
    }

    public RegistrationHelper registration() throws Exception {
        if (registration == null) {
            registration = new RegistrationHelper(this);
        }
        return registration;
    }

    public NavigationHelper goTo() throws Exception {
        if (navigationHelper == null) {
            navigationHelper = new NavigationHelper(this);
        }
        return  navigationHelper;
    }

    public WebDriver getDriver() throws Exception {
        if (wd == null) {
            if (browser == BrowserType.FIREFOX) {
                wd = new FirefoxDriver();
            } else if (browser == BrowserType.CHROME) {
                wd = new ChromeDriver();
            } else if (browser == BrowserType.IE) {
                wd = new InternetExplorerDriver();
            }
            wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            wd.get(properties.getProperty("web.baseUrl"));
        }
        return wd;
    }

    public MailHelper mail() throws Exception {
        if (mailHelper == null) {
            mailHelper = new MailHelper(this);
        }
        return  mailHelper;
    }

    public DbHelper db() {
        return dbHelper;
    }

}
