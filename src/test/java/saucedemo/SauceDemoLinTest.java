package saucedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.Test;

import java.util.List;

public class SauceDemoLinTest {
    WebDriver driver;
    WebDriverWait wait;

    @DataProvider(name="Scenarios")
    public static Object[][] sauceBrowserDataProvider() {
        return new Object[][]{
                new Object[]{"standard_user", "secret_sauce"}, //successful case
                new Object[]{"problem_user", "secret_sauce"}, // problem case with soft assert
                new Object[]{"locked_out_user", "secret_sauce"} // failure login attempt
        };
    }

    public void SauceDemoLinAddCart(String username,String password) throws Exception {
        System.out.println("username= "+username);
        System.out.println("password= "+password);
        System.setProperty("webdriver.chrome.driver",".\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        PageNavigation pageNavigation=new PageNavigation(driver);
        pageNavigation.goToLoginPage();

        wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-button")));
        SoftAssert softAssert = new SoftAssert();

        //login
        LogInPage logInPage = new LogInPage(driver);
        logInPage.setUserName(username);
        logInPage.setPassword(password);

        logInPage.clickLoginButton(); //In this method, I am also calling verifySuccessfulLogin().
        // If an error occurs in a login attempt, I am throwing an exception and printing the message.

        //sort low to high
        InventoryPage inventoryPage=new InventoryPage(driver);
        inventoryPage.sortLowToHigh();

        //select first item
        String product1 = inventoryPage.selectProduct(0);//selecting first product
        //first added item
        softAssert.assertEquals(product1,"Sauce Labs Onesie","Low to High sort is not working!");

        //add item to the cart
        inventoryPage.addToCart();

        //Go back inventory page add first item to the Shopping Cart
        pageNavigation.goToInventoryPage();

        String product2 = inventoryPage.selectProductWithImage(0);
        //softAssert.onAssertFailure(softAssert.assertEquals(products.size(),6),"Images are not loaded");
        softAssert.assertEquals(product2,"Sauce Labs Backpack","Clicked item is not matching!");

        inventoryPage.addToCart();

        //go and check Shopping Cart
        pageNavigation.goShoppingCartPage();
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);

        softAssert.assertEquals(shoppingCartPage.getProductsInCart().size(),2);//first size checking

        System.out.println("product1 = "+product1);
        System.out.println("product2 = "+product2);
        List<WebElement> productLinks = shoppingCartPage.getProductLinks();
        softAssert.assertNotNull(productLinks,"Product links can not be null before clicking checkout");
        softAssert.assertFalse(productLinks.isEmpty(),"Product links can not be empty before clicking checkout");

        if(!productLinks.isEmpty()){//I wrote this if because I use SoftAssert. Program will continue whatever "productlinks" is empty or not .
            // If it is empty, I can not check the index 0 or 1. It will throw an exception so I wrote this if statement to handle this situation.
            softAssert.assertEquals(productLinks.get(0).getText(),product1); //I am checking the product name
            softAssert.assertEquals(productLinks.get(1).getText(),product2);
        }

        //checkout
        shoppingCartPage.clickCheckout();

        //complete Checkout
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.enterContactDetails("Talha","Ozcelik","34704");
        checkoutPage.clickContinue();
        checkoutPage.clickFinish();

        //logout
        MenuPage menuPage = new MenuPage(driver);
        menuPage.logOut();

        softAssert.assertAll();
    }

    @Test(dataProvider = "Scenarios")
    public static void main(String args,String args1) throws Exception {
        SauceDemoLinTest test = new SauceDemoLinTest();
        test.SauceDemoLinAddCart(args,args1);
    }
}