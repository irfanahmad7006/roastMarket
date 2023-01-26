package roastMarketPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.SelUtils;

public class HomePage {
    private WebDriver driver;
    private By acceptCookie = By.xpath("//button[@id='onetrust-accept-btn-handler']");
    private By coffeeExpresso =By.xpath("//a/span[contains(text(),'Kaffee & Espresso')]");
    private By headingCoffeeExpresso = By.xpath("//h1[contains(text(),'Kaffee & Espresso')]");
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public void clickOnCoffeeExpresso(){
        driver.findElement(coffeeExpresso).click();
        SelUtils.explicitWaitVisible(driver, headingCoffeeExpresso);
    }

    public void selectProduct(String name){
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setAcceptCookie();
        String prodName = "//p[contains(text(),'"+name+"')]";
        SelUtils.explicitWaitClickable(driver, By.xpath(prodName));
        driver.findElement(By.xpath(prodName)).click();
    }

    public void setAcceptCookie(){
        if(driver.findElement(acceptCookie).isDisplayed()){
            driver.findElement(acceptCookie).click();
        }
    }
}
