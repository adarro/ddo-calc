package io.truthencode.dal.general;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.text.IsEmptyString.emptyString;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class FeatsEndpointTest {

    @Test
    public void TestExistingFeat() {
        Response response = given()
            .when()
            .get("/Feats/2")
            .then()
            .statusCode(200)
            .contentType("application/json")
            .extract().response();
        assertEquals(response.jsonPath().getString("name"), "Sneak");
    }


    @Test
    public void testUpdateFeatSingleUsage() {
        //Sneak is an Active Feat. The test example has it unspecified (null)
        // We will update it.
        // Update Usage to active
        given()
            .when()
            .body("{\"name\":\"Sneak\",\"usages\" : [\"ACTIVE\"]}")
            .contentType("application/json")
            .put("/Feats/2")
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
    public void testUpdateEmptyBody() {
        given()
            .when()
            .body("{}")
            .contentType("application/json")
            .put("/Feats/2")
            .then()
            .statusCode(422);

    }

    @Test
    public void testFeatsUpdateMultiUsage() {
        //Sneak is an Active Feat.  We will update it.

        var response = given()
            .when()
            .body("{\"id\":4,\"name\":\"Improved Sneak Attack\",\"usages\":[\"ACTIVE\",\"PASSIVE\"]}")
            .contentType("application/json")
            .put("/Feats/4")
            .then()
            .statusCode(200)
            .body(
                containsString("\"id\":"),
                containsString("\"name\":\"Improved Sneak Attack\"")
            ).extract().response();
        assertThat(response.jsonPath().getList("usages")).containsAll(Arrays.asList("ACTIVE", "PASSIVE"));

    }

    @Test
    public void testListAllFeats() {
        //List all, should have all 4 Feats the database has initially:
        Response response = given()
            .when()
            .get("/Feats")
            .then()
            .statusCode(200)
            .contentType("application/json")
            .extract().response();
        assertThat(response.jsonPath().getList("name")).containsExactlyInAnyOrder("Sneak", "Alertness", "Sneak Attack", "Improved Sneak Attack");

        // Update Alertness to Creep
        given()
            .when()
            .body("{\"name\" : \"Creep\"}")
            .contentType("application/json")
            .put("/Feats/1")
            .then()
            .statusCode(200)
            .body(
                containsString("\"id\":"),
                containsString("\"name\":\"Creep\""));

        //List all, Creep should've replaced Alertness:
        response = given()
            .when()
            .get("/Feats")
            .then()
            .statusCode(200)
            .contentType("application/json")
            .extract().response();
        assertThat(response.jsonPath().getList("name"))
            .containsExactlyInAnyOrder("Creep", "Sneak", "Sneak Attack", "Improved Sneak Attack");

        //Delete Creep:
        given()
            .when()
            .delete("/Feats/1")
            .then()
            .statusCode(204);

        response = given()
            .when()
            .get("/Feats")
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
            .post("/Feats")
            .then()
            .statusCode(201)
            .body(
                containsString("\"id\":"),
                containsString("\"name\":\"Stalk\""));

        //List all, Pineapple should be still missing now:
        response = given()
            .when()
            .get("/Feats")
            .then()
            .statusCode(200)
            .extract().response();
        assertThat(response.jsonPath().getList("name"))
            .containsExactlyInAnyOrder("Stalk", "Sneak", "Sneak Attack", "Improved Sneak Attack");
    }

    @Test
    public void testEntityNotFoundForDelete() {
        given()
            .when()
            .delete("/Feats/9236")
            .then()
            .statusCode(404)
            .body(emptyString());
    }

    @Test
    public void testEntityNotFoundForUpdate() {
        given()
            .when()
            .body("{\"name\" : \"Watermelon\"}")
            .contentType("application/json")
            .put("/Feats/32432")
            .then()
            .statusCode(404)
            .body(emptyString());
    }

    @Test
    public void testRawJsonExtraction() {
        Response response = given()
            .when()
            .body("{\"name\":\"Sneak\",\"usages\" : [\"ACTIVE\"]}")
            .put("/Feats/2")
            .then()

            .statusCode(200)
            .contentType("application/json")
            .extract().response();
        assertEquals(response.jsonPath().getString("name"), "Sneak");
        assertEquals(response.jsonPath().getString("usages"), "[ACTIVE]");
    }
}
