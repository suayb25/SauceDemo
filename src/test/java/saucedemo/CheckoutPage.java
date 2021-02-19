package saucedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutPage extends BasePage{

    WebDriver driver;

    public CheckoutPage(WebDriver browserDriver) {
        super(browserDriver);
        driver = browserDriver;
    }

    public void enterContactDetails(String firstName, String lastName, String postalCode) {
        WebElement firstNameElement = driver.findElement(By.id("first-name"));
        WebElement lastNameElement = driver.findElement(By.id("last-name"));
        WebElement postalCodeElement = driver.findElement(By.id("postal-code"));
        firstNameElement.sendKeys(firstName);
        lastNameElement.sendKeys(lastName);
        postalCodeElement.sendKeys(postalCode);
    }

    public void clickContinue() {
        WebElement continueButton = driver.findElement(By.cssSelector("[type='submit']"));
        continueButton.click();
    }

    public void clickFinish() {
        WebElement finishButton = driver.findElement(By.cssSelector(".cart_button"));
        finishButton.click();
    }
}
