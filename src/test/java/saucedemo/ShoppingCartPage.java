package saucedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ShoppingCartPage extends BasePage{
    WebDriver driver;
    List<WebElement> productsInCart;
    List<WebElement>  productLinks;

    public ShoppingCartPage(WebDriver browserDriver) {
        super(browserDriver);
        driver = browserDriver;
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("subheader")));
    }

    public void clickCheckout(){
        WebElement checkout = driver.findElement(By.cssSelector(".btn_action.checkout_button"));
        checkout.click();
    }

    public List<WebElement> getProductsInCart(){
        productsInCart = driver.findElements(By.className("cart_item"));
        return productsInCart;
    }

    public List<WebElement> getProductLinks(){
        productLinks= driver.findElements(By.className("inventory_item_name"));
        return productLinks;
    }

}
