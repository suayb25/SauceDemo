package saucedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageNavigation {

    private WebDriver driver;
    WebDriverWait wait;

    public PageNavigation(WebDriver driver) {
        this.driver = driver;
    }

    public void goToInventoryPage() {
        driver.get("https://www.saucedemo.com/inventory.html");
        wait = new WebDriverWait(driver,10);
    }

    public void goToLoginPage() {
        driver.get("https://www.saucedemo.com/index.html");
    }

    public void goShoppingCartPage() {
        WebElement shoppingCart = driver.findElement(By.xpath("//*[contains(@class,'svg-inline--fa fa-shopping-cart fa-w-18 fa-3x')]"));
        shoppingCart.click();
    }

}
