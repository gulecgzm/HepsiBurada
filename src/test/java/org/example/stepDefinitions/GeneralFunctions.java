package org.example.stepDefinitions;

import org.example.Hooks.GeneralHooks;
import org.example.WaitMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GeneralFunctions {

    public static WaitMethods waitMethods = WaitMethods.getInstance();
    WebDriverWait wait = new WebDriverWait(GeneralHooks.driver, 20);

    public String nameOfFirstProductInSearchResults = "" ;

    public void clickAddToBasketButtonForFirstProductInProductList() {
        WebElement firstProduct = wait.until(ExpectedConditions.elementToBeClickable(By.id("i1")));
        nameOfFirstProductInSearchResults = firstProduct.getText();
        firstProduct.click();

        WebElement addToCardButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"addToCart\"]")));
        addToCardButton.click();
    }

    public void openBasket() {
        waitMethods.waitForLoad(GeneralHooks.driver);
        WebElement shoppingCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("shoppingCart")));
        shoppingCartButton.click();
    }
}
