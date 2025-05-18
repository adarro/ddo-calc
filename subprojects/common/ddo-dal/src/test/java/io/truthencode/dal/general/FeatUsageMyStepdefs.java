package io.truthencode.dal.general;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.text.IsEmptyString.emptyString;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class FeatUsageMyStepdefs {

    Response response = null;

    @Given("a Feat with at least one Active or Passive values")
    public void aFeatWithAtLeastOneActiveOrPassiveValues() {
        response = given()
            .when()
            .get("/db/feat/2")
            .then()
            .statusCode(200)
            .contentType("application/json")
            .extract().response();
    }

    @When("you try to remove all usages")
    public void youTryToRemoveAllUsages() {
    }

    @Then("The operation should fail")
    public void theOperationShouldFail() {
    }
}
