package api.apache_http_client;

import api.ApiTestBase;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.*;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;
import api.utils.ApiUtils;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class ResponseHeaderTests extends ApiTestBase {

    CloseableHttpClient httpClient = HttpClients.createDefault();

    @AfterSuite
    public void suiteCleanup() throws IOException {
        httpClient.close();
    }

    @Test
    public void contentTypeIsAppJsonCharUtf8() throws IOException {
        ClassicHttpRequest get = ClassicRequestBuilder.get(ApiUtils.BASE_ENDPOINT).build();

        httpClient.execute(get, response -> {
            String contentType = response.getEntity().getContentType();
            assertEquals(contentType, "application/json; charset=utf-8");

            response.close();
            return null;
        });
    }

    @Test
    public void serverIsGithub() throws IOException {
        ClassicHttpRequest get = ClassicRequestBuilder.get(ApiUtils.BASE_ENDPOINT).build();

        httpClient.execute(get, response -> {
            Header serverHeader = response.getHeader(ApiUtils.HeaderName.SERVER.toString());
            assertEquals(serverHeader.getValue(), "GitHub.com");

            response.close();
            return null;
        });
    }

    @Test
    public void rateLimitIs60() throws IOException {
        ClassicHttpRequest get = ClassicRequestBuilder.get(ApiUtils.BASE_ENDPOINT).build();

        httpClient.execute(get, response -> {
            Header rateLimitHeader = response.getHeader(ApiUtils.HeaderName.X_RATE_LIMIT.toString());
            assertEquals(rateLimitHeader.getValue(), "60");

            response.close();
            return null;
        });
    }

    @Test
    public void eTagIsPresent() throws IOException {
        ClassicHttpRequest get = ClassicRequestBuilder.get(ApiUtils.BASE_ENDPOINT).build();

        httpClient.execute(get, response -> {
            Header eTagHeader = response.getHeader(ApiUtils.HeaderName.ETAG.toString());
            assertNotNull(eTagHeader);

            response.close();
            return null;
        });
    }
}
