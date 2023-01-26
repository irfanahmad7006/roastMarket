package stepdefs;

import driverFactory.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import roastMarketPages.HomePage;
import roastMarketPages.Product;
import utils.SelUtils;

public class ProductStepDefs {
    private HomePage homePage = new HomePage(DriverFactory.getDriver());
    private Product productSelection = new Product(DriverFactory.getDriver());

    @Given("User selects quantity {int} for the selected product")
    public void user_selects_quantity_for_the_selected_product(Integer int1) {
        productSelection.selectQuantity(int1);
    }

    @Given("User clicks on IN THE CART button")
    public void user_clicks_on_in_the_cart_button() {
        productSelection.clickOnAddToCart();
    }

    @When("User click on cart icon very the quantity and price")
    public void user_click_on_cart_icon() throws InterruptedException {
        productSelection.clickOnCartIcon();
    }

    @When("User clicks on to checkout button")
    public void user_clicks_on_to_checkout_button() {
        productSelection.clickOnToCheckoutButton();
    }

    @When("User selects as a guest and click on continue")
    public void user_selects_as_a_guest_and_click_on_continue() {
        productSelection.selectGuestClickContinue();
    }

    @When("User fills the form Title {string} {string} {string} {string} {string} {string} {string}")
    public void user_fills_the_form_title(String string, String string2, String string3, String string4, String string5, String string6, String string7) {
        productSelection.fillForm(string,string2,string3,string4,string5,string6,string7);
    }

    @Then("User clicks on the continue option and select payment type")
    public void user_clicks_on_the_continue_option_and_select_payment_type() throws InterruptedException {
        productSelection.clickPaymentBtnSelectPaymentOption();
    }

    @Then("User verifies the quantity and price again")
    public void user_verifies_the_quantity_and_price_again() {
        productSelection.verifyQuantityAndPriceOnOverview();
    }
}
