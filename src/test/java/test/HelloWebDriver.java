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

    @Before
    public void browserSetup() {
        System.out.println("aaa");
        options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.NONE);
        options.addArguments("start-maximized");
        options.addArguments("disable-infobars");
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
        Thread.sleep(10000);

        loginPageObj = new LoginPage(driver);
        loginPageObj.singIntoSystemAsUser("U0191767","06258");

        Assert.assertEquals(driver.getTitle(),"webQUIK 7.6.2");
        homePage = new HomePage(driver);
        homePage.createNewTicketWithlimits("CNYRUB_SPT","5","1");
    }
    @Test
    public  void createNewTicketWithMarketablePrice() throws InterruptedException {
        driver.get("https://junior.webquik.ru/");

        Thread.sleep(10000);

        loginPageObj = new LoginPage(driver);

        loginPageObj.singIntoSystemAsUser("U0191767","06258");


        Assert.assertEquals(driver.getTitle(),"webQUIK 7.6.2");
        homePage = new HomePage(driver);
        homePage.createNewTicketWithMarket("CHMF","5","1");
    }
    @After
    public void closeBrowser() {
        driver.close();
        driver = null;
    }
}

