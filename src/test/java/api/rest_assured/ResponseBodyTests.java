package api.rest_assured;

import api.entities.User;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.number.OrderingComparison.lessThanOrEqualTo;
import static org.testng.Assert.assertEquals;
import static api.utils.ApiUtils.BASE_ENDPOINT;

public class ResponseBodyTests {

    /**
     * JSON Path Tests
     */
    @Test
    public void returnsCorrectUserInfo() {
        RestAssured.get(BASE_ENDPOINT + "/users/clontztopher")
                .then() // Returns ValidatableResponse for assertions
                .body(
                        User.LOGIN, equalTo("clontztopher"),
                        User.ID, equalTo(14989492)
                );
    }

    @Test
    public void returnsCorrectRateLimitInfo() {
        RestAssured.get(BASE_ENDPOINT + "/rate_limit")
                .then()
                .rootPath("resources.core")
                    .body(
                            "limit", equalTo(60),
                            "remaining", lessThanOrEqualTo(60),
                            "reset", notNullValue()
                    )
                .rootPath("resources.search")
                    .body(
                            "limit", equalTo(10),
                            "remaining", lessThanOrEqualTo(10)
                    );
    }

    /**
     * Jackson Tests
     */
    @Test
    public void returnsCorrectUserInfoUsingJackson() {
        User user = RestAssured.get(BASE_ENDPOINT + "/users/clontztopher")
                .as(User.class);

        assertEquals(user.getLogin(), "clontztopher");
        assertEquals(user.getId(), 14989492);
    }
}
