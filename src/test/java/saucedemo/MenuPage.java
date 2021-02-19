package saucedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MenuPage extends BasePage{
    WebDriver driver;
    WebDriverWait wait;

    public MenuPage(WebDriver browserDriver) {
        super(browserDriver);
        driver = browserDriver;
        WebElement menu = this.driver.findElement(By.cssSelector("div.bm-burger-button"));
        menu.click();
    }

    public void logOut() {
        wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logout_sidebar_link")));
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); //this is not a good way
        System.out.println("driver= "+driver.toString());
        WebElement logoutButton = this.driver.findElement(By.id("logout_sidebar_link"));
        logoutButton.click();
    }
}
