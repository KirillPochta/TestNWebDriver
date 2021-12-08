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
        driver.manage().timeouts().implicitlyWait(5000,
                TimeUnit.MILLISECONDS);
        driver.manage().timeouts().pageLoadTimeout(5000,
                TimeUnit.MILLISECONDS);
        driver.manage().timeouts().setScriptTimeout(5000,
                TimeUnit.MILLISECONDS);
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver.get("https://junior.webquik.ru/");
        wait = new WebDriverWait(driver,10);
    }
    @org.junit.Test
    public  void signIntoSystem() throws InterruptedException {

        wait.until(ExpectedConditions.presenceOfElementLocated
                (By.id("textfield-1015-inputEl")));
        wait.until(ExpectedConditions.presenceOfElementLocated
                (By.id("textfield-1017-inputEl")));
        wait.until(ExpectedConditions.presenceOfElementLocated
                (By.id("button-1021-btnIconEl")));
        loginPageObj = new LoginPage(driver);

        loginPageObj.singIntoSystemAsUser("U0191767","06258");
        Assert.assertEquals(driver.getTitle(),"webQUIK 7.6.2");
    }

    @org.junit.Test
    public  void createNewTicketWithLimits() throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated
                (By.xpath("//*[@id=\"textfield-1015-inputEl\"]")));
        wait.until(ExpectedConditions.presenceOfElementLocated
                (By.xpath("//*[@id=\"textfield-1017-inputEl\"]")));
        wait.until(ExpectedConditions.presenceOfElementLocated
                (By.xpath("//*[@id=\"button-1021-btnIconEl\"]")));
        loginPageObj = new LoginPage(driver);
        loginPageObj.singIntoSystemAsUser("U0191767","06258");

        Thread.sleep(5000);
        Assert.assertEquals(driver.getTitle(),"webQUIK 7.6.2");

        homePage.createNewTicketWithlimits("CNYRUB_SPT","5","1");
    }
    @Test
    public  void createNewTicketWithMarketablePrice() throws InterruptedException {
        Thread.sleep(5000);
        loginPageObj.singIntoSystemAsUser("U0191767","06258");

        Thread.sleep(5000);
        Assert.assertEquals(driver.getTitle(),"webQUIK 7.6.2");

        homePage.createNewTicketWithMarket("CHMF","5");
    }
    @After
    public void closeBrowser() {
        driver.quit();
        driver = null;
    }
}

