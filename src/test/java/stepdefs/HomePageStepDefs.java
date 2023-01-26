package stepdefs;

import driverFactory.DriverFactory;
import io.cucumber.java.en.Given;
import roastMarketPages.HomePage;

public class HomePageStepDefs {
    private HomePage homePage = new HomePage(DriverFactory.getDriver());
    String title;

    @Given("User is on roast market home page")
    public void user_is_on_roast_market_home_page() {
        title = homePage.getTitle();
        System.out.println(title);

    }

    @Given("User clicks on Kaffee & Espresso category")
    public void user_clicks_on_kaffee_espresso_category() {
        homePage.clickOnCoffeeExpresso();
        System.out.println(homePage.getTitle());
    }

    @Given("User clicks on the product named as {string}")
    public void user_clicks_on_the_product_named_as(String name) {
        homePage.selectProduct(name);
    }

}
