package io.truthencode.dal.general;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import org.junitpioneer.jupiter.displaynamegenerator.ReplaceCamelCaseAndUnderscoreAndNumber;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.text.IsEmptyString.emptyString;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
@DisplayNameGeneration(ReplaceCamelCaseAndUnderscoreAndNumber.class)
class FeatsEndpointTest {

    @Test
    void TestExistingFeat() {
        Response response = given()
            .when()
            .get("/db/feat/2")
            .then()
            .statusCode(200)
            .contentType("application/json")
            .extract().response();
        assertEquals("Sneak", response.jsonPath().getString("name"));
    }


    @Test
    void testUpdateFeatSingleUsage() {
        //Sneak is an Active Feat. The test example has it unspecified (null)
        // We will update it.
        // Update Usage to active
        given()
            .when()
            .body("{\"name\":\"Sneak\",\"usages\" : [\"ACTIVE\"]}")
            .contentType("application/json")
            .put("/db/feat/2")
            .then()
            .statusCode(200)
            .body(
                containsString("\"id\":"),
                containsString("\"name\":\"Sneak\""),
                containsString("\"usages\":[\"ACTIVE\"]"),
                not(containsString("\"PASSIVE\""))
            );

    }

    @Test
    void testUpdateEmptyBody() {
        given()
            .when()
            .body("{}")
            .contentType("application/json")
            .put("/db/feat/2")
            .then()
            .statusCode(422);

    }

    @Test
    void testFeatsUpdateMultiUsage() {
        //Sneak is an Active Feat.  We will update it.

        var response = given()
            .when()
            .body("{\"id\":4,\"name\":\"Improved Sneak Attack\",\"usages\":[\"ACTIVE\",\"PASSIVE\"]}")
            .contentType("application/json")
            .put("/db/feat/4")
            .then()
            .statusCode(200)
            .body(
                containsString("\"id\":"),
                containsString("\"name\":\"Improved Sneak Attack\"")
            ).extract().response();
        assertThat(response.jsonPath().getList("usages")).containsAll(Arrays.asList("ACTIVE", "PASSIVE"));

    }

    @Test
    void testLocateSampled() {
        //List all, should have all 4 Feats the database has initially:

        Response response = given()
            .when()
            .get("/db/feat")
            .then()
            .statusCode(200)
            .contentType("application/json")
            .extract().response();
        assertThat(response.jsonPath().getList("description")).containsAll(List.of("Sneak", "Alertness", "Sneak Attack", "Improved Sneak Attack"));
    }

    @Test
    void testListAllFeats() {

        // Update Alertness to Creep
        given()
            .when()
            .body("{\"name\" : \"Creep\"}")
            .contentType("application/json")
            .put("/db/feat/1")
            .then()
            .statusCode(200)
            .body(
                containsString("\"id\":"),
                containsString("\"name\":\"Creep\""));

        //List all, Creep should've replaced Alertness:
        Response response = given()
            .when()
            .get("/db/feat")
            .then()
            .statusCode(200)
            .contentType("application/json")
            .extract().response();
        assertThat(response.jsonPath().getList("name"))
            .containsExactlyInAnyOrder("Creep", "Sneak", "Sneak Attack", "Improved Sneak Attack");

        //Delete Creep:
        given()
            .when()
            .delete("/db/feat/1")
            .then()
            .statusCode(204);

        response = given()
            .when()
            .get("/db/feat")
            .then()
            .statusCode(200)
            .contentType("application/json")
            .extract().response();
        assertThat(response.jsonPath().getList("name"))
            .containsExactlyInAnyOrder("Sneak", "Sneak Attack", "Improved Sneak Attack");

        //Create the Pear:
        given()
            .when()
            .body("{\"name\" : \"Stalk\"}")
            .contentType("application/json")
            .post("/db/feat")
            .then()
            .statusCode(201)
            .body(
                containsString("\"id\":"),
                containsString("\"name\":\"Stalk\""));

        //List all, Pineapple should be still missing now:
        response = given()
            .when()
            .get("/db/feat")
            .then()
            .statusCode(200)
            .extract().response();
        assertThat(response.jsonPath().getList("name"))
            .containsExactlyInAnyOrder("Stalk", "Sneak", "Sneak Attack", "Improved Sneak Attack");
    }

    @Test
    void testEntityNotFoundForDelete() {
        given()
            .when()
            .delete("/db/feat/9236")
            .then()
            .statusCode(404)
            .body(emptyString());
    }

    @Test
    void testEntityNotFoundForUpdate() {
        given()
            .when()
            .body("{\"name\" : \"Watermelon\"}")
            .contentType("application/json")
            .put("/db/feat/32432")
            .then()
            .statusCode(404)
            .body(emptyString());
    }

    @Test
    void testRawJsonExtraction() {
        Response response = given()
            .when()
            .body("{\"name\":\"Sneak\",\"usages\" : [\"ACTIVE\"]}")
            .put("/db/feat/2")
            .then()

            .statusCode(200)
            .contentType("application/json")
            .extract().response();
        assertEquals("Sneak", response.jsonPath().getString("name"));
        assertEquals("[ACTIVE]", response.jsonPath().getString("usages"));
    }
}
