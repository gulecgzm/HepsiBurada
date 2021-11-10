package org.example.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.Hooks.GeneralHooks;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.example.WaitMethods;

public class AddProductToBasketWithLogin {

    public static WaitMethods waitMethods = WaitMethods.getInstance();
    public static GeneralFunctions generalFunctions = new GeneralFunctions();
    public static GeneralHooks generalHooks = new GeneralHooks();

    WebDriverWait wait = new WebDriverWait(GeneralHooks.driver, 20);

    @Given("User is logged in")
    public void userIsLoggedIn() {

        WebElement myAccountInfo = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"myAccount\"]/span/a/span[2]")));
        String loggedınUserInfo = myAccountInfo.getText();
        Assert.assertEquals("Test Gizem", loggedınUserInfo);

    }

    @When("User search product")
    public void userSearchProduct() {

        WebElement searchBar = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"SearchBoxOld\"]/div/div/div[1]/div[2]/input")));
        searchBar.sendKeys("İphone"); //product no for iphone xr 64gb
        searchBar.sendKeys(Keys.ENTER);

        waitMethods.waitForLoad(GeneralHooks.driver);
        WebElement searchResultPartialTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"SearchResultSummary\"]/div/div/h1/b[1]")));
        String searchResultPartialTitleText = searchResultPartialTitle.getText();
        Assert.assertEquals("İphone", searchResultPartialTitleText);

    }

    @And("User add product to basket from two different seller")
    public void userAddProductToBasketFromTwoDifferentSeller() {

        generalFunctions.clickAddToBasketButtonForFirstProductInProductList();

        WebElement closeIconForPopUp = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[starts-with(@id,'AddToCart')]/div/div/div/div/div/h1/a")));
        ((JavascriptExecutor)generalHooks.driver).executeScript("arguments[0].click();", closeIconForPopUp);

        WebElement addToCardButtonForSecondSeller = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/main/div[3]/section[1]/div[5]/div/div[4]/div[2]/div[2]/div/div[2]/table/tbody/tr[1]/td[3]/div/form/button")));
        addToCardButtonForSecondSeller.click();

        WebElement closeIconForSecondPopUp = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"pcwrapper\"]/div/i")));
        closeIconForSecondPopUp.click();

        WebElement closeIconForPopUp2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[starts-with(@id,'AddToCart')]/div/div/div/div/div/h1/a")));
        ((JavascriptExecutor)generalHooks.driver).executeScript("arguments[0].click();", closeIconForPopUp2);

    }

    @Then("Product is successfully added from two different seller")
    public void productIsSuccessfullyAddedFromTwoDifferentSeller() {

        generalFunctions.openBasket();

        waitMethods.waitForLoad(GeneralHooks.driver);
        String cardPageUrl = generalHooks.driver.getCurrentUrl();
        Assert.assertEquals("https://checkout.hepsiburada.com/sepetim", cardPageUrl);

        WebElement basketCount = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("basket-item-count")));
        String basketCountText = basketCount.getText();
        Assert.assertEquals("2", basketCountText);

        WebElement firstSeller = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div[2]/div/div[2]/section/section/ul/li[1]/div/div/div[2]/div[3]/div[1]/span/a")));
        String firstSellerName = firstSeller.getText();

        WebElement secondSeller = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div[2]/div/div[2]/section/section/ul/li[2]/div/div/div[2]/div[3]/div[1]/span/a")));
        String secondSellerName = secondSeller.getText();

        Assert.assertNotEquals(firstSellerName , secondSellerName);

    }
}
