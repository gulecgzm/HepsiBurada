@AddProductToBasketWithLogin
Feature: Add Product To Basket With Login Feature

  @PC_Open_Browser_And_User_Login @TD_Empty_Basket @TD_Logout_And_Close_Browser
  Scenario: Add Product To Basket With Login
    Given User is logged in
    When User search product
    And User add product to basket from two different seller
    Then Product is successfully added from two different seller