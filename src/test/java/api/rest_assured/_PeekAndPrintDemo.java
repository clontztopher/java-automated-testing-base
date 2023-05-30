package api.rest_assured;

import api.ApiTestBase;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import api.utils.ApiUtils;

public class _PeekAndPrintDemo extends ApiTestBase {
    Response response;

    @BeforeSuite
    public void getResponse() {
        response = RestAssured.get(ApiUtils.BASE_ENDPOINT);
    }

    @Test
    public void peek() {
        // Prints response headers and body
        response.peek();
    }

    @Test
    public void prettyPeek() {
        // Pretty prints response body after headers
        response.prettyPeek();
    }

    @Test
    public void print() {
        // Prints body only
        response.print();
    }

    @Test
    public void prettyPrint() {
        // Pretty print body
        response.prettyPrint();
    }
}
