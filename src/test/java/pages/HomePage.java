package pages;

import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.logging.Level;
import java.util.logging.Logger;

public class HomePage {
    private WebDriver driver;

    @FindBy(xpath = "//span[contains(@style,'two-fingers')]")
    private WebElement newTicketButton;

    @FindBy(xpath = "//td[contains(@colspan,'2')]//table[contains(@id,'combobox-2')]//td[contains(@class,'x-form-trigger')]//input[contains(@class,'x-form-field')][1]")
    private WebElement tradeNumber;

    @FindBy(xpath = "//div[contains(@class,'x-h')]//input[@placeholder='Найти инструмент']")
    private WebElement inputNameOfLot;

    @FindBy(xpath = "//td[contains(@id,'combobox-2')]//input[contains(@tabindex,'6')]")
    private WebElement clientsCode;

    @FindBy(xpath = "//td[contains(@id,'plus')]//input[contains(@data-errorqtip,'Значение поля')]")
    private WebElement numberOfLotsToBuy; //5

    @FindBy(xpath = "//td[contains(@class,'x-form-tr')]//input[contains(@tabindex,'11')]")
    private WebElement costForInstrument; //1

    @FindBy(xpath = "//table[contains(@style,'195px; top: 25px')]//div[contains(@role,'input')]")
    private WebElement sumOfTransactionBeforeSubmit;

    @FindBy(xpath = "//a[@tabindex=41]//span[contains(@style,'height: 19px')]//span[contains(@id,'submit')][2]")
    private WebElement submitTicketButton;

    @FindBy(xpath = "//a[@tabindex=41]//span[contains(@style,'height: 19px')]//span[contains(@id,'submit')][2]")
    private WebElement sendTicketButton;


    @FindBy(xpath = "//td[@colspan=2]//div[contains(@style,'width: 100%')][1]")
    private WebElement tradeNumberAfterSubmitTicket;


    @FindBy(id = "button-2136-btnIconEl")
    private WebElement changeTypeOfTicketToMarketable;


    @FindBy(id = "checkbox-2064-inputEl")
    private WebElement stopTicketButton;

    private String tradenumberActual;
    private String clientsCodeActual;

    private String tradenumberExpected;
    private String clientsCodeExpected;

    public HomePage(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver, this);

    }

    protected static WebElement waitForElementToBeClickable(WebDriver driver, WebElement element) {
        return new WebDriverWait(driver, 100)
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    protected static WebElement waitForVisibilityOfElement(WebDriver driver, WebElement element) {
        return new WebDriverWait(driver, 100)
                .until(ExpectedConditions.visibilityOf(element));
    }
    public void createNewTicketWithlimits(String nameOfLot, String countOfLots, String costPerInstruments) {

            newTicketButton.click();

            tradeNumber.sendKeys(Keys.ARROW_DOWN + "\n" + Keys.ENTER);

            tradenumberActual = tradeNumber.getText();
            clientsCodeActual = clientsCode.getText();

            inputNameOfLot.sendKeys(nameOfLot);
            inputNameOfLot.sendKeys(Keys.ENTER);

            numberOfLotsToBuy.sendKeys(countOfLots);
            costForInstrument.sendKeys("1");

            submitTicketButton.click();

            tradenumberExpected = tradeNumberAfterSubmitTicket.getText();

            sendTicketButton.click();

            Assert.assertEquals(clientsCodeActual, clientsCodeExpected);
            Assert.assertEquals(tradeNumber.getText(), tradeNumberAfterSubmitTicket.getText());
    }
    public void createNewTicketWithMarket(String nameOfLot, String countOfLots,String costForEachInstument) throws InterruptedException {
            try {
                newTicketButton.click();
                tradeNumber.sendKeys(Keys.ARROW_DOWN + "\n");
                Thread.sleep(5000);
                inputNameOfLot.sendKeys(nameOfLot);
                inputNameOfLot.sendKeys(Keys.ENTER);
                numberOfLotsToBuy.sendKeys(countOfLots);
                costForInstrument.sendKeys(costForEachInstument);
                stopTicketButton.click();
                submitTicketButton.click();
                sendTicketButton.click();

                Assert.assertEquals(tradeNumber.getText(), tradeNumberAfterSubmitTicket.getText());
            }
            catch (Exception e){
                Logger logger = Logger.getLogger(HomePage.class.getName());
                logger.log(Level.INFO,e.getMessage());
            }
        }
   }

