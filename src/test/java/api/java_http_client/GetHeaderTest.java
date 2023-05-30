package api.java_http_client;

import api.ApiTestBase;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import api.utils.ApiUtils;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

import static org.testng.Assert.*;

public class GetHeaderTest extends ApiTestBase {

    static HttpClient httpClient = HttpClient.newBuilder().build();
    static HttpResponse<Void> response;

    @BeforeSuite
    public void sendGetToBaseEndpoint() throws IOException, InterruptedException {
        HttpRequest getRequest = HttpRequest.newBuilder(URI.create(ApiUtils.BASE_ENDPOINT)).build();
        response = httpClient.send(getRequest, HttpResponse.BodyHandlers.discarding());
    }

    @Test
    public void getReturns200() {
        int actualCode = response.statusCode();
        assertEquals(200, actualCode);
    }

    @Test
    public void contentTypeIsJson() {
        Optional<String> header = response.headers().firstValue(ApiUtils.HeaderName.CONTENT_TYPE.toString());

        if (header.isPresent()) {
            String contentType = header.get();
            assertEquals("application/json; charset=utf-8", contentType);
            return;
        }

        fail("Missing content-type header.");
    }

    @Test
    public void xRateLimitIsPresent() {
        Optional<String> header = response.headers().firstValue(ApiUtils.HeaderName.X_RATE_LIMIT.toString());
        assertTrue(header.isPresent());
    }

    @Test
    public void xRateLimitIs60() {
        Optional<String> header = response.headers().firstValue(ApiUtils.HeaderName.X_RATE_LIMIT.toString());

        if (!header.isPresent()) {
            fail("Missing header: X-RateLimit-Limit");
        }

        assertEquals(header.get(), String.valueOf(60));
    }
}
