package pages;

import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.logging.Level;
import java.util.logging.Logger;

public class HomePage {
    private WebDriver driver;

    @FindBy(xpath = "//*[@id=\"button-1050-btnIconEl\"]")
    private WebElement newTicketButton;

    @FindBy(xpath = "//*[@id=\"combobox-2022-inputEl\"]")
    private WebElement tradeNumber;

    @FindBy(xpath = "//*[@id=\"combobox-2024-inputEl\"]")
    private WebElement inputNameOfLot;

    @FindBy(xpath = "//*[@id=\"combobox-2023-inputEl\"]")
    private WebElement clientsCode;

    @FindBy(id = "plusminusnumberfield-2032-inputEl")
    private WebElement numberOfLotsToBuy; //5

    @FindBy(id = "plusminusnumberfield-2056-inputEl")
    private WebElement costForInstrument; //1

    @FindBy(xpath = "//*[@id=\"displayfield-2359-inputEl\"]")
    private WebElement sumOfTransactionBeforeSubmit;

    @FindBy(xpath = "//*[@id=\"submitBtn2-btnIconEl\"]")
    private WebElement submitTicketButton;

    @FindBy(id = "button-2203-btnIconEl")
    private WebElement sendTicketButton;


    @FindBy(xpath = "//*[@id=\"displayfield-2464-inputEl\"]")
    private WebElement tradeNumberAfterSubmitTicket;


    @FindBy(xpath = "//*[@id=\"displayfield-2465-inputEl\"]")
    private WebElement clientsCodeAfterSubmitTicket;


    @FindBy(xpath = "//*[@id=\"displayfield-2466-inputEl\"]")
    private WebElement nameOfLotAfterSubmit;

    @FindBy(xpath = "//*[@id=\"displayfield-2470-inputEl\"]")
    private WebElement sumOfTransactionAfterSubmit;
    @FindBy(id = "button-2136-btnIconEl")
    private WebElement changeTypeOfTicketToMarketable;

    @FindBy(id = "tool-2520-toolEl")
    private WebElement closeWindowOfTicket;

    @FindBy(id = "checkbox-2064-inputEl")
    private WebElement stopTicketButton;

    public HomePage(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver, this);

    }

    public void createNewTicketWithlimits(String nameOfLot, String countOfLots, String costPerInstruments) {
        try {
            newTicketButton.click();
            Thread.sleep(5000);
            tradeNumber.sendKeys(Keys.ARROW_DOWN + "\n" + Keys.ENTER);
            Thread.sleep(5000);
            inputNameOfLot.sendKeys(nameOfLot);
            inputNameOfLot.sendKeys(Keys.ENTER);
            numberOfLotsToBuy.sendKeys(countOfLots);
            costForInstrument.sendKeys("1");
            submitTicketButton.click();
            sendTicketButton.click();

            Assert.assertEquals(clientsCode.getText(), clientsCodeAfterSubmitTicket.getText());
            Assert.assertEquals(tradeNumber.getText(), tradeNumberAfterSubmitTicket.getText());
            Assert.assertEquals(nameOfLot, nameOfLotAfterSubmit.getText());
            Assert.assertEquals(sumOfTransactionBeforeSubmit.getText(), sumOfTransactionAfterSubmit.getText());
        }
        catch (Exception e){
            Logger logger = Logger.getLogger(HomePage.class.getName());
            logger.log(Level.INFO,e.getMessage());
        }
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

                Assert.assertEquals(clientsCode.getText(), clientsCodeAfterSubmitTicket.getText());
                Assert.assertEquals(tradeNumber.getText(), tradeNumberAfterSubmitTicket.getText());
                Assert.assertEquals(nameOfLot, nameOfLotAfterSubmit.getText());
                Assert.assertEquals(sumOfTransactionBeforeSubmit.getText(), sumOfTransactionAfterSubmit.getText());
            }
            catch (Exception e){
                Logger logger = Logger.getLogger(HomePage.class.getName());
                logger.log(Level.INFO,e.getMessage());
            }
        }
   }

