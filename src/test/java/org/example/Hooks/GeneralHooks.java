package org.example.Hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;

import org.example.WaitMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class GeneralHooks {

    public static WebDriver driver;
    public static dataProvider.ConfigFileReader configFileReader= new dataProvider.ConfigFileReader();
    public static WaitMethods waitMethods = WaitMethods.getInstance();

    @Before(value = "@PC_Browser_Open", order = 90)
    public void openChromeBrowser(){
        openBrowser();
    }

    @Before(value = "@PC_User_Login", order = 91)
    public void adminLogin() {
        loginHepsiBurada("testgizem@hotmail.com", "Test12345678");
    }

    @Before(value = "@PC_Open_Browser_And_User_Login", order = 91)
    public void openBrowserAdminLogin() {
        openBrowser();
        loginHepsiBurada("testgizem@hotmail.com", "Test12345678");
    }

    @After(value = "@TD_Empty_Basket", order = 9990)
    public void emptyBasket() {
        WebDriverWait wait = new WebDriverWait(GeneralHooks.driver, 20);
        waitMethods.waitForLoad(driver);

        List<WebElement> productListInBasket = driver.findElements(By.xpath("/html/body/div/div/div/div[2]/div/div[2]/section/section/ul/li"));

        WebElement deleteIcon= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div/div[2]/div/div[2]/section/section/ul/li[1]/div/div/div[2]/div[4]/div[2]/div/a[2]")));
        deleteIcon.click();

        WebElement deleteIcon2= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div/div[2]/div/div[2]/section/section/ul/li[1]/div/div/div[2]/div[4]/div[2]/div[2]/div/div/div/button[2]")));
        deleteIcon2.click();

        if(productListInBasket.size()>1){
            WebElement deleteIconForSecondProduct = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div/div[2]/div/div[2]/section/section/ul/li/div/div/div[2]/div[4]/div[2]/div/a[2]")));
            deleteIconForSecondProduct.click();
        }

       /* for (int i = 1; i <= productListInBasket.size(); i++) {
            WebElement deleteIcon= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div/div[2]/div/div[2]/section/section/ul/li[" + (i) + "]/div/div/div[2]/div[4]/div[2]/div/a[2]")));
            deleteIcon.click();
            WebElement deleteIcon2= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div/div[2]/div/div[2]/section/section/ul/li[1]/div/div/div[2]/div[4]/div[2]/div[2]/div/div/div/button[2]")));
            deleteIcon2.click();
        }*/


    }

    @After(value = "@TD_Logout_And_Close_Browser", order = 9900)
    public void logoutAndCloseChromeBrowser() {
        logoutHepsiBurada();
        closeBrowser();
    }

    @After(value = "@TD_Close_Browser", order = 9900)
    public void closeChromeBrowser() {
        closeBrowser();
    }

    public void openBrowser() {
        System.setProperty("webdriver.chrome.driver", configFileReader.getDriverPath());
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        configFileReader.getImplicitlyWait();
        driver.get(configFileReader.getApplicationUrl());
    }

    public void loginHepsiBurada(String username, String password) {

        WebElement loginMenu = driver.findElement(By.id("myAccount"));

        Actions builder = new Actions(driver);
        builder.moveToElement(loginMenu).build().perform();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("myAccount")));

        WebElement loginOption = wait.until(ExpectedConditions.elementToBeClickable(By.id("login")));
        loginOption.click();

        WebElement usernameField = wait.until(ExpectedConditions.elementToBeClickable(By.id("txtUserName")));
        usernameField.sendKeys(username);

        WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(By.id("txtPassword")));
        passwordField.sendKeys(password);

        WebElement loginButtonAfterEmail = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnLogin")));
        loginButtonAfterEmail.click();


    }

    public void closeBrowser() {
        driver.close();
    }

    public void logoutHepsiBurada() {

        WebElement logoutUserIcon = GeneralHooks.driver.findElement(By.xpath("/html/body/div/div/div/div[1]/div/div/div/div/div/div/div/div[4]/div[2]/div/div/span"));
        logoutUserIcon.click();
        WebElement logoutButton = GeneralHooks.driver.findElement(By.xpath("/html/body/div/div/div/div[1]/div/div/div/div/div/div/div/div[4]/div[2]/div/div[2]/div[2]/ul/li[2]/a"));
        logoutButton.click();

    }
}

