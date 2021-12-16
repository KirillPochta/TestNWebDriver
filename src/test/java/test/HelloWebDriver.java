package test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageObject_model.page.HomePage;
import pageObject_model.page.LoginPage;
import java.util.concurrent.TimeUnit;


public class HelloWebDriver {

    private WebDriver driver;
    private ChromeOptions options;
    private LoginPage loginPageObj;
    private HomePage homePage;

    private String timeBeforeTicket;
    private String timeAfterTicket;

    private String tradeNumberAfter;

    @Before
    public void browserSetup() {
        options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.NONE);
        options.addArguments("start-maximized");


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
        loginPageObj.singIntoSystemAsUser("U0193146","08134");

        homePage = new HomePage(driver);

        timeBeforeTicket = homePage.setDateBeforTicketCreation();

        homePage.openWindowOfCreationTicket();
        homePage.fillFieldsOnTicketWindow("CNYRUB_SPT","5","1");

        homePage.pressSubmitTicketButton();
        tradeNumberAfter = homePage.tradeNumberAfterSubmit();
        homePage.sendOfCretedTicketButton();

        timeAfterTicket = homePage.setDateAfterTicketCreation();

        Assert.assertNotEquals(timeBeforeTicket,timeAfterTicket);
        Assert.assertEquals(tradeNumberAfter,"MB1000100002");
    }
    @Test
    public  void createNewTicketWithMarketablePrice() throws InterruptedException {
        driver.get("https://junior.webquik.ru/");
        Thread.sleep(10000);

        loginPageObj = new LoginPage(driver);
        loginPageObj.singIntoSystemAsUser("U0193146","08134");

        homePage = new HomePage(driver);
        homePage.openWindowOfCreationTicket();
        homePage.fillFieldsOnTicketWindow("CNYRUB_SPT","5","1");
        homePage.setStopTicketMethod();
        homePage.pressSubmitTicketButton();
        tradeNumberAfter = homePage.tradeNumberAfterSubmit();
        homePage.sendOfCretedTicketButton();
        Assert.assertEquals(tradeNumberAfter,"MB1000100002");
    }
    @After
    public void closeBrowser() {
        driver.close();
        driver = null;
    }
}

