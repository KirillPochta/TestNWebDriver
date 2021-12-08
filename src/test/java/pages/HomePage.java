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
    private WebDriver driver = new ChromeDriver();

    @FindBy(xpath = "//*[@id=\"button-1050-btnIconEl\"]")
    private WebElement newTicketButton;

    @FindBy(name = "combobox-2022-inputEl")
    private WebElement tradeNumber;

    @FindBy(xpath = "//*[@id=\"combobox-2024-inputEl\"]")
    private WebElement inputNameOfLot;

    @FindBy(xpath = "//*[@id=\"combobox-2023-inputEl\"]")
    private WebElement clientsCode;

    @FindBy(xpath = "//*[@id=\"plusminusnumberfield-2333-inputEl\"]")
    private WebElement numberOfLotsToBuy; //5

    @FindBy(xpath = "//*[@id=\"plusminusnumberfield-2357-inputEl\"]")
    private WebElement costForInstrument; //1

    @FindBy(xpath = "//*[@id=\"displayfield-2359-inputEl\"]")
    private WebElement sumOfTransactionBeforeSubmit;

    @FindBy(xpath = "//*[@id=\"submitBtn2-btnIconEl\"]")
    private WebElement submitTicketButton;

    @FindBy(xpath = "//*[@id=\"button-2445-btnIconEl\"]")
    private WebElement sendTicketButton;


    @FindBy(xpath = "//*[@id=\"displayfield-2464-inputEl\"]")
    private WebElement tradeNumberAfterSubmitTicket;


    @FindBy(xpath = "//*[@id=\"displayfield-2465-inputEl\"]")
    private WebElement clientsCodeAfterSubmitTicket;


    @FindBy(xpath = "//*[@id=\"displayfield-2466-inputEl\"]")
    private WebElement nameOfLotAfterSubmit;

    @FindBy(xpath = "//*[@id=\"displayfield-2470-inputEl\"]")
    private WebElement sumOfTransactionAfterSubmit;
    @FindBy(xpath = "//*[@id=\"button-2232-btnIconEl\"]")
    private WebElement changeTypeOfTicketToMarketable;

    public HomePage(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver, this);

    }

    public void createNewTicketWithlimits(String nameOfLot, String countOfLots, String costPerInstruments) {
        try {
            newTicketButton.click();
            tradeNumber.sendKeys(Keys.ARROW_DOWN + "\n");
            inputNameOfLot.sendKeys(nameOfLot);
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
    public void createNewTicketWithMarket(String nameOfLot, String countOfLots) {
            newTicketButton.click();
            tradeNumber.sendKeys(Keys.ARROW_DOWN + "\n");
            inputNameOfLot.sendKeys(nameOfLot);
            numberOfLotsToBuy.sendKeys(countOfLots);
            changeTypeOfTicketToMarketable.click();
            submitTicketButton.click();
            sendTicketButton.click();

            Assert.assertEquals(clientsCode.getText(), clientsCodeAfterSubmitTicket.getText());
            Assert.assertEquals(tradeNumber.getText(), tradeNumberAfterSubmitTicket.getText());
            Assert.assertEquals(nameOfLot, nameOfLotAfterSubmit.getText());
            Assert.assertEquals(sumOfTransactionBeforeSubmit.getText(), sumOfTransactionAfterSubmit.getText());
       }
    }

