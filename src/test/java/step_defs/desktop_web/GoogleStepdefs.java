package step_defs.desktop_web;

import cucumber.api.java.en.*;
import page_objects.desktop_web.BasePage;
import page_objects.desktop_web.GooglePage;

import static org.assertj.core.api.Assertions.*;

public class GoogleStepdefs {
    @Given("the Google Homepage has been opened")
    public void theGoogleHomepageHasBeenOpened() {
        BasePage.goToHomePage();
    }

    @When("the user search for the keyword {string}")
    public void theUserSearchForTheKeyword(String keyword) {
        GooglePage.searchForKeyword(keyword);
    }

    @Then("the list of search result must be displayed with over {int} results")
    public void theListOfSearchResultMustBeDisplayedWithOverResults(int numberOfResult) {
        assertThat(GooglePage.getNumberOfResult()).isLessThanOrEqualTo(numberOfResult);
    }

}
