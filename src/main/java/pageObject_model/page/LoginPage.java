package pageObject_model.page;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private WebDriver driver = new ChromeDriver();

    @FindBy(xpath = "//*[@id=\"textfield-1015-inputEl\"]")
    private WebElement loginFiled;

    @FindBy(xpath = "//*[@id=\"textfield-1017-inputEl\"]")
    private WebElement passwordField;

    @FindBy(xpath = "//*[@id=\"button-1021-btnIconEl\"]")
    private WebElement signButton;

    public LoginPage(WebDriver driver) {
        this.driver=driver;
       PageFactory.initElements(driver, this);
    }

    protected static WebElement waitForElementToBeClickable(WebDriver driver, WebElement element) {
        return new WebDriverWait(driver, 40)
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    protected static WebElement waitForVisibilityOfElement(WebDriver driver, WebElement element) {
        return new WebDriverWait(driver, 40)
                .until(ExpectedConditions.visibilityOf(element));
    }

    public void singIntoSystemAsUser(String login,String password) throws InterruptedException {
            waitForVisibilityOfElement(driver,loginFiled);
            waitForVisibilityOfElement(driver,passwordField);
            waitForElementToBeClickable(driver,signButton);

            loginFiled.sendKeys(login);
            passwordField.sendKeys(password);
            Thread.sleep(2000);
            signButton.click();
    }
}
