package pages;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class TestsBase {
    protected static List<WebDriver> drivers;
    protected static String URL;

    protected static String hhUsername;
    protected static String hhPass;

    protected static String vkEmail;
    protected static String vkPassword;

    @BeforeEach
    public void setUp() throws IOException {
        drivers = new ArrayList<>();

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--lang=ru");
        WebDriver chromeDriver = new ChromeDriver(chromeOptions);

        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("intl.accept_languages", "ru");
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setProfile(profile);
        WebDriver firefoxDriver = new FirefoxDriver(firefoxOptions);

        chromeDriver.manage().window().maximize();
        firefoxDriver.manage().window().maximize();
        
        drivers.add(chromeDriver);
        drivers.add(firefoxDriver);

        Properties prop = new Properties();
        InputStream input = getClass().getClassLoader().getResourceAsStream("web.properties");
        prop.load(input);

        URL = prop.getProperty("url");

        hhUsername = prop.getProperty("username");
        hhPass = prop.getProperty("password");

        vkEmail = prop.getProperty("vk-email");
        vkPassword = prop.getProperty("vk-password");
    }

    @AfterEach
    public void tearDown() {
        if (drivers != null) {
            drivers.forEach(WebDriver::quit);
        }
    }
}
