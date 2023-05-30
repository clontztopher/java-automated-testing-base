package api.rest_assured;

import api.utils.ApiUtils;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static api.utils.ApiUtils.BASE_ENDPOINT;
import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.equalTo;

public class HeaderAndOptionsRequests {

    @Test
    public void headTest() {
        RestAssured.head(BASE_ENDPOINT)
                .then()
                .statusCode(200)
                .body(emptyOrNullString());
    }

    @Test
    public void optionsTest() {
        RestAssured.options(BASE_ENDPOINT)
                .then()
                .statusCode(204)
                .header(ApiUtils.HeaderName.ACCESS_METHODS.toString(), equalTo("GET, POST, PATCH, PUT, DELETE"))
                .body(emptyOrNullString());
    }
}
