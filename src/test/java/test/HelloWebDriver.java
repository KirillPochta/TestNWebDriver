package test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import pages.HomePage;
import pages.LoginPage;
import java.util.concurrent.TimeUnit;


public class HelloWebDriver {

    private WebDriver driver;
    private ChromeOptions options;
    private LoginPage loginPageObj;
    private HomePage homePage;
    private WebDriverWait wait;
    public String timeBeforeChanges;
    public String timeAfterChanges;
    @Before
    public void browserSetup() {
        options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.NONE); // https://www.skptricks.com/2018/08/timed-out-receiving-message-from-renderer-selenium.html
        options.addArguments("start-maximized"); // https://stackoverflow.com/a/26283818/1689770
        options.addArguments("enable-automation"); // https://stackoverflow.com/a/43840128/1689770
        options.addArguments("--headless"); // only if you are ACTUALLY running headless
        options.addArguments("--no-sandbox"); //https://stackoverflow.com/a/50725918/1689770
        options.addArguments("--disable-infobars"); //https://stackoverflow.com/a/43840128/1689770
        options.addArguments("--disable-dev-shm-usage"); //https://stackoverflow.com/a/50725918/1689770
        options.addArguments("--disable-browser-side-navigation"); //https://stackoverflow.com/a/49123152/1689770
        options.addArguments("--disable-gpu");


        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(15000,
                TimeUnit.MILLISECONDS);
        driver.manage().timeouts().pageLoadTimeout(15000,
                TimeUnit.MILLISECONDS);
        driver.manage().timeouts().setScriptTimeout(16000,
                TimeUnit.MILLISECONDS);
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

    }

    @org.junit.Test
    public  void createNewTicketWithLimits() throws InterruptedException {
        driver.get("https://junior.webquik.ru/");
        loginPageObj = new LoginPage(driver);
        loginPageObj.singIntoSystemAsUser("U0193146","08134");

        homePage = new HomePage(driver);
        homePage.createNewTicketWithlimits("CNYRUB_SPT","5","1");
    }
    @Test
    public  void createNewTicketWithMarketablePrice() throws InterruptedException {
        driver.get("https://junior.webquik.ru/");


        loginPageObj = new LoginPage(driver);

        loginPageObj.singIntoSystemAsUser("U0193146","08134");


        homePage = new HomePage(driver);
        homePage.createNewTicketWithMarket("CHMF","5","1");
    }
    @After
    public void closeBrowser() {
        driver.close();
        driver = null;
    }
}

