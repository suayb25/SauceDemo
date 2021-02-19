package saucedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LogInPage extends BasePage{
    WebDriver driver;
    private By userNameLocator = By.id("user-name");
    private By passwordLocator = By.id("password");
    private By loginButtonLocator = By.id("login-button");

    public LogInPage(WebDriver browserDriver) {
        super(browserDriver);
        driver = browserDriver;
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-button")));
    }

    public LogInPage setUserName(String userName){
        WebElement userNameTextBox = driver.findElement(userNameLocator);
        userNameTextBox.sendKeys(userName);
        return this;
    }

    public LogInPage setPassword(String password){
        WebElement passwordTextBox = driver.findElement(passwordLocator);
        passwordTextBox.sendKeys(password);
        return this;
    }

    public InventoryPage clickLoginButton() throws Exception {
        WebElement loginButton = driver.findElement(loginButtonLocator);
        loginButton.click();
        verifySuccessfulLogin();
        return new InventoryPage(driver);
    }

    public void verifySuccessfulLogin() throws Exception{
        try {
            wait.until(ExpectedConditions.urlToBe("https://www.saucedemo.com/inventory.html"));
        }catch (TimeoutException e){
            String errorTextLogin = driver.findElement(By.cssSelector("h3[data-test=error]")).getText();
            throw new Exception("Login Failed: " + errorTextLogin);
        }
    }
}
