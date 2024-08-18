package io.truthencode.dal.general;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.truthencode.dal.general.JSONSupport.UPDATE_KEYS_HEADER;
import static org.hamcrest.CoreMatchers.containsString;
@QuarkusTest
class RequestServerReaderInterceptorTest {


    @Test
    @DisplayName(value = "Interceptor should work with only supplied keys to update")
    void testRequestServerReaderInterceptorOnlySuppliedKeys() {
        // Description field should be updated, while the changed usage should not be updated.
        given()
            .when()
            .body("{\"name\":\"Sneak\",\"description\":\"Really Pretty\",\"usages\" : [\"PASSIVE\"]}")
            .headers(UPDATE_KEYS_HEADER, "description")
            .contentType("application/json")
            .put("/Feats/2")
            .then()
            .statusCode(200)
            .body(
                containsString("\"id\":"),
                containsString("\"name\":\"Sneak\""),
                containsString("Really Pretty"));


    }

    @Test
    @DisplayName(value = "Interceptor should work with supplied keys to update")
    void testRequestServerReaderInterceptorSuppliedHeaderKeys() {
        // Description field should be updated, while the changed usage should not be updated.
        given()
            .when()
            .body("{\"name\":\"Sneak\",\"description\":\"Really Pretty\",\"usages\" : [\"PASSIVE\"]}")
            .headers(UPDATE_KEYS_HEADER, "description,usages")
            .contentType("application/json")
            .put("/Feats/2")
            .then()
            .statusCode(200)
            .body(
                containsString("\"id\":"),
                containsString("\"name\":\"Sneak\""),
                containsString("\"usages\":[\"PASSIVE\"]"),
                containsString("Really Pretty"));


    }

    @Test
    @Disabled(value = "Disaabled until we can properpy propagate the header from the interceptor to the rest client")
    @DisplayName(value = "Interceptor should supply keys to update")
    void testRequestServerReaderInterceptorWithoutHeader() {
        given()
            .when()
            .body("{\"name\":\"Sneak\",\"description\":\"Really Pretty\",\"usages\" : [\"PASSIVE\"]}")
            .contentType("application/json")
            .put("/Feats/2")
            .then()
            .statusCode(200)
            .headers(UPDATE_KEYS_HEADER, "name,description,usages");

    }
}