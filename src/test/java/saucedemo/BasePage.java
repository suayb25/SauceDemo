package saucedemo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    WebDriver driver;
    WebDriverWait wait;

    public  BasePage(WebDriver browserDriver){
        driver = browserDriver;
        //Waiting for page loading
        wait = new WebDriverWait(driver,10);
    }
}
