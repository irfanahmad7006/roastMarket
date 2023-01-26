Feature: Shop for Coffee project

  Scenario: Automation of Roastmarket
    Given User is on roast market home page
    And User clicks on Kaffee & Espresso category
    And User clicks on the product named as "Caffè Caimano Espresso Classico"
    And User selects quantity 3 for the selected product
    And User clicks on IN THE CART button
    When User click on cart icon very the quantity and price
    And User clicks on to checkout button
    And User selects as a guest and click on continue
    And User fills the form Title "Herr" "Irfan" "Ahmad" "Hugo-Junkers-Strasse 7" "60386" "Frankfurt am Main" "irfan.ahmad.22@gmail.com"
    Then User clicks on the continue option and select payment type
    And User verifies the quantity and price again

