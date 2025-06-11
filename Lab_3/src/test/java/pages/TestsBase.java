package pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

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

    @BeforeEach
    public void setUp() throws IOException {
        drivers = new ArrayList<>();

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--lang=ru");
        chromeOptions.setBinary("/usr/bin/google-chrome-stable");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.addArguments("--remote-debugging-port=9222");
//        chromeOptions.addArguments("--no-sandbox");
//        chromeOptions.addArguments("--disable-dev-shm-usage");
//        chromeOptions.addArguments("--headless=new"); // Enable for speed up

//        chromeOptions.addArguments("--disable-blink-features=AutomationControlled");
//        chromeOptions.setExperimentalOption("excludeSwitches", List.of("enable-automation"));
//        chromeOptions.setExperimentalOption("useAutomationExtension", false);
//        chromeOptions.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/124.0.0.0 Safari/537.36");

        WebDriver chromeDriver = new ChromeDriver(chromeOptions);

        ((JavascriptExecutor) chromeDriver).executeScript(
                "Object.defineProperty(navigator, 'webdriver', {get: () => undefined})"
        );

        chromeDriver.manage().window().maximize();
        drivers.add(chromeDriver);

        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
//        options.addArguments("--headless"); // Enable for speed up
        options.addArguments("--width=1920");
        options.addArguments("--height=1080");
        options.addArguments("--disable-dev-shm-usage");
        WebDriver firefoxDriver = new FirefoxDriver(options);

        firefoxDriver.manage().window().maximize();
        drivers.add(firefoxDriver);


        Properties prop = new Properties();
        InputStream input = getClass().getClassLoader().getResourceAsStream("web.properties");
        prop.load(input);

        URL = prop.getProperty("url");
        hhUsername = prop.getProperty("username");
        hhPass = prop.getProperty("password");
    }

    @AfterEach
    public void tearDown() {
        if (drivers != null) {
            drivers.forEach(WebDriver::quit);
        }
    }
}
