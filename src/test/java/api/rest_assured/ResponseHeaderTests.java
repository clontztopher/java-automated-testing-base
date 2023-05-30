package api.rest_assured;

import api.ApiTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.hamcrest.number.OrderingComparison;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import api.utils.ApiUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.hamcrest.Matchers.*;

public class ResponseHeaderTests extends ApiTestBase {
    Response response;

    @BeforeSuite
    public void getResponse() {
        response = RestAssured.get(ApiUtils.BASE_ENDPOINT);
    }

    @Test
    public void contentTypeIsAppJsonCharUtf8() {
        assertEquals(
                response.getContentType(),
                "application/json; charset=utf-8"
        );
    }

    @Test
    public void xRateLimitIsPresentAndHasExpectedValue() {
        Headers headers = response.getHeaders();
        assertTrue(headers.hasHeaderWithName(ApiUtils.HeaderName.X_RATE_LIMIT.toString()));
        assertEquals(
                response.getHeader(ApiUtils.HeaderName.X_RATE_LIMIT.toString()),
                "60"
        );
    }

    @Test
    public void testMultipleAspectsTogether() {
        response
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .header(
                        ApiUtils.HeaderName.X_RATE_LIMIT.toString(),
                        Integer::parseInt,
                        equalTo(60)
                ).header(
                        ApiUtils.HeaderName.X_RATE_LIMIT.toString(),
                        response1 -> greaterThan(response.header(ApiUtils.HeaderName.X_RATE_LIMIT_REMAINING.toString()))
                ).header(
                        ApiUtils.HeaderName.DATE.toString(),
                        date -> LocalDate.parse(date, DateTimeFormatter.RFC_1123_DATE_TIME),
                        OrderingComparison.comparesEqualTo(LocalDate.now())
                );
    }
}
