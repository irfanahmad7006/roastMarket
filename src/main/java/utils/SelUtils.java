package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SelUtils {
    public static void explicitWaitClickable(WebDriver driver, By by) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public static void explicitWaitVisible(WebDriver driver, By by) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
    }
    public static double getPrice(String numInTxt){

        String y[] = numInTxt.split("");
        String z ="";
        for(int i=0;i<y.length-1;i++){
            if (y[i].contains(",")){
                z= z+".";
            }else{
                z= z+y[i];
            }
        };
        return Double.parseDouble(z);
    }
}
