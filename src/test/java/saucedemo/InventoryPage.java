package saucedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class InventoryPage extends BasePage{

    WebDriver driver;

    public InventoryPage(WebDriver browserDriver) {
        super(browserDriver);
        driver = browserDriver;
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("app_logo")));
    }

    public List <WebElement> getProductNameLinks(){
        List<WebElement> productLinks = driver.findElements(By.className("inventory_item_name"));
        return productLinks;
    }

    public void sortLowToHigh(){
        WebElement sortContainer = driver.findElement(By.className("product_sort_container"));
        Select objSelect = new Select(sortContainer);
        objSelect.selectByVisibleText("Price (low to high)");
    }

    public void addToCart(){
        WebElement addToCartButton = driver.findElement(By.xpath("//*[contains(@class,'btn_primary btn_inventory')]"));
        addToCartButton.click();
    }

    public String selectProductWithImage(int index){
        List<WebElement> products_Names = driver.findElements(By.className("inventory_item_name"));
        List<WebElement> products = driver.findElements(By.className("inventory_item_img"));
        //softAssert.assertEquals(products.size(),12,"Size needs to 12");
        String product2 = products_Names.get(0).getText(); //we store first added item
        products.get(index).click();
        return product2;
    }

    public String selectProduct(int index){
        List<WebElement> products = driver.findElements(By.className("inventory_item_name"));
        String product1 = products.get(0).getText(); //we store first added item
        products.get(0).click();
        return product1;
    }

    /*public boolean isLoaded() {
        try {
            return getDriver().findElement(By.className("app_logo")).isDisplayed();
        }
        catch (NoSuchElementException e) {
            return false;
        }
    }*/
}
