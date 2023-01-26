package roastMarketPages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import utils.SelUtils;

import javax.swing.*;

public class Product {
    private WebDriver driver;
    private By acceptCookie = By.xpath("//button[@id='onetrust-accept-btn-handler']");
    private By finalPrice = By.xpath("//span[@class='product__price__final']");
    private By inTheCart = By.xpath("//button[contains(text(),'In den Warenkorb')]");
    private By cartIcon = By.xpath("//button[@data-testid='openMicrocart']");
    private By toCheckout = By.xpath("/html/body/div[1]/div/div/div[1]/div[4]/div/div[1]/div[2]/div[2]/div[2]/section[1]/div[1]/div[3]/div[3]/a");
    private By continueBTN = By.xpath("//button[contains(text(),'Weiter')]");
    private By maleRdioBTN = By.xpath("//*[@id='billing-new-address-form']/div/ul/li[2]/div/div[2]/div/label[1]");
    private By femaleRdioBTN = By.xpath("//*[@id='billing-new-address-form']/div/ul/li[2]/div/div[2]/div/label[2]");
    private By fName = By.xpath("//input[@id='billing:firstname']");
    private By lName = By.xpath("//input[@id='billing:lastname']");
    private By strNamNum = By.xpath("//input[@id='billing:street1']");
    private By pin1 = By.xpath("//input[@id='billing:postcode']");
    private By city1 = By.xpath("//input[@id='billing:city']");
    private By email1 = By.xpath("//input[@id='billing:email']");
    private By contPaymntBtn = By.xpath("//*[@id='billing-buttons-container']");
    private By advPaymentBtn = By.xpath("//*[@id='dt_method_payone_advance_payment']/label");
    private By contToOverview = By.xpath("//button[@class='button btn-payment']");

    private static double totalPrice;
    private static int quatity;

    public Product(WebDriver driver) {
        this.driver = driver;
    }

    public void selectQuantity(int num){
        quatity= num;
        String quantityNum = "//label[@for='qty"+num+"']";
        System.out.println(quantityNum);
        setAcceptCookie();
        SelUtils.explicitWaitClickable(driver, By.xpath(quantityNum));
        driver.findElement(By.xpath(quantityNum)).click();
        getTotalPrice();
    }

    public void clickOnAddToCart(){
        driver.findElement(inTheCart).click();
    }

    public void clickOnCartIcon() throws InterruptedException {
        driver.findElement(cartIcon).click();
//        Thread.sleep(10000);
        SelUtils.explicitWaitVisible(driver,By.xpath("//*[@id='viewport']/div[4]/div/div[1]/div[2]/div[2]/div[1]/div/div/div[3]/div/div[2]/div"));
        String totalQuantityOnCheckOut = driver.findElement(By.xpath("//*[@id='viewport']/div[4]/div/div[1]/div[2]/div[2]/div[1]/div/div/div[3]/div/div[2]/div")).getText();
        String totalPriceOnCheckOut = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[1]/div[4]/div/div[1]/div[2]/div[2]/div[1]/div/div/div[3]/div/div[3]/span")).getText();
        Assert.assertEquals(quatity,Integer.parseInt(totalQuantityOnCheckOut));
        double priceFromCheckout = SelUtils.getPrice(totalPriceOnCheckOut);
        Assert.assertTrue(totalPrice==priceFromCheckout);
        System.out.println(totalPriceOnCheckOut);
    }

    public void clickOnToCheckoutButton(){
        driver.findElement(toCheckout).click();
    }

    public void selectGuestClickContinue(){
        SelUtils.explicitWaitVisible(driver,continueBTN);
        driver.findElement(continueBTN).click();
    }

    public void fillForm(String title, String firstName, String lastName, String strNameNum,String pin,String city,String email){
        SelUtils.explicitWaitVisible(driver,maleRdioBTN);
        if (title == "Herr"){
            driver.findElement(maleRdioBTN).click();
        } else {
            driver.findElement(femaleRdioBTN).click();
        }
        driver.findElement(fName).sendKeys(firstName);
        driver.findElement(lName).sendKeys(lastName);
        driver.findElement(strNamNum).sendKeys(strNameNum);
        driver.findElement(pin1).sendKeys(pin);
        driver.findElement(city1).sendKeys(city);
        driver.findElement(email1).sendKeys(email);
    }

    public void clickPaymentBtnSelectPaymentOption() throws InterruptedException {
        driver.findElement(contPaymntBtn).click();
        Thread.sleep(5000);
        SelUtils.explicitWaitVisible(driver,By.xpath("//div[@class='suggestion-popup featherlight-inner']/button[@class='rma-button action']"));
        Thread.sleep(5000);
        driver.findElement(By.xpath("//div[@class='suggestion-popup featherlight-inner']/button[@class='rma-button action']")).click();
        Thread.sleep(5000);
        SelUtils.explicitWaitVisible(driver,advPaymentBtn);
        Thread.sleep(5000);
        driver.findElement(advPaymentBtn).click();
        SelUtils.explicitWaitVisible(driver,contToOverview);
        driver.findElement(contToOverview).click();
    }

    public void verifyQuantityAndPriceOnOverview(){
        WebElement ele = driver.findElement(By.xpath("//select[@class='qty-dropdown review-dropdown']"));
        Select select = new Select(ele);
        WebElement selectedOPtion=  select.getFirstSelectedOption();
        String quantity1 = selectedOPtion.getText();

//        String quantity1 = driver.findElement(By.xpath("//select[@class='qty-dropdown review-dropdown']")).getText();
        Assert.assertTrue(quatity==Integer.parseInt(quantity1));
        String price= driver.findElement(By.xpath("//span[@class='cart-price']")).getText();
        double checkoutprice = SelUtils.getPrice(price);
        Assert.assertTrue(totalPrice == checkoutprice);
    }

    public void setAcceptCookie(){
        if(driver.findElement(acceptCookie).isDisplayed()){
            driver.findElement(acceptCookie).click();
        }
    }


    public void getTotalPrice(){
        String indiviPrice = driver.findElement(finalPrice).getText();
//        String x = "6,69€";
        String y[] = indiviPrice.split("");
        String z ="";
        for(int i=0;i<y.length-1;i++){
            if (y[i].contains(",")){
                z= z+".";
            }else{
                z= z+y[i];
            }
        };
        totalPrice = quatity*Double.parseDouble(z);
        System.out.println(z);
        System.out.println(quatity);
        System.out.println(totalPrice);
    }
}
