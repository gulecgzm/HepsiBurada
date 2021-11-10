@AddProductToBasketWithoutLogin
Feature: Add Product Selected From The Menu To Basket Without Login Feature

  @PC_Browser_Open @TD_Empty_Basket @TD_Close_Browser
  Scenario: Add Product To Basket With Login
    Given User is home page
    When User search product from menu
    And User add product to basket
    Then Product is successfully added