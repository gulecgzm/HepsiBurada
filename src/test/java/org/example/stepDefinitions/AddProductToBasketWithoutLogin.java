package org.example.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.Hooks.GeneralHooks;
import org.example.WaitMethods;
import org.junit.Assert;
import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.CoreMatchers.containsString;

public class AddProductToBasketWithoutLogin {

    public static GeneralHooks generalHooks = new GeneralHooks();
    public static WaitMethods waitMethods = WaitMethods.getInstance();
    public static GeneralFunctions generalFunctions = new GeneralFunctions();
    WebDriverWait wait = new WebDriverWait(GeneralHooks.driver, 20);


    @Given("User is home page")
    public void userIsHomePage() {
        waitMethods.waitForLoad(GeneralHooks.driver);
        String currentUrlForHomePage = generalHooks.driver.getCurrentUrl();
        Assert.assertEquals("https://www.hepsiburada.com/" , currentUrlForHomePage);
    }

    @When("User search product from menu")
    public void userSearchProductFromMenu() {
        waitMethods.waitForLoad(GeneralHooks.driver);
        WebElement categoryOfBooksMusic = generalHooks.driver.findElement(By.xpath("//*[starts-with(@id,'NavigationDesktop')]/div/div/div/div/div[1]/div/ul/li[9]/span/span"));

        Actions builder = new Actions(generalHooks.driver);
        builder.moveToElement(categoryOfBooksMusic).build().perform();

        WebElement carsOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[starts-with(@id,'NavigationDesktop')]/div/div/div/div/div[1]/div/ul/li[9]/div/div/div[1]/div[2]/ul/li/ul[3]/li/a[2]/span")));
        carsOption.click();
    }

    @And("User add product to basket")
    public void userAddProductToBasket() {
        generalFunctions.clickAddToBasketButtonForFirstProductInProductList();
    }

    @Then("Product is successfully added")
    public void productIsSuccessfullyAdded() {

        generalFunctions.openBasket();

        WebElement productInBasket = wait.until(ExpectedConditions.elementToBeClickable(By.className("product_name_3Lh3t")));
        String nameOfProductInBasket = productInBasket.getText();
        Assert.assertThat(generalFunctions.nameOfFirstProductInSearchResults, containsString(nameOfProductInBasket));

    }
}
