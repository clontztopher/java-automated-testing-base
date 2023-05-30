package api.apache_http_client;

import api.ApiTestBase;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;
import api.utils.ApiUtils;

import java.io.IOException;

import static org.testng.Assert.assertEquals;

public class Get404Tests extends ApiTestBase {
    CloseableHttpClient httpClient = HttpClients.createDefault();

    @AfterSuite
    public void suiteCleanup() throws IOException {
        httpClient.close();
    }

    @Test
    public void nonExistingUrlReturns404() throws IOException {
        ClassicHttpRequest get = ClassicRequestBuilder.get(ApiUtils.BASE_ENDPOINT + "/nonexistingurl").build();

        httpClient.execute(get, response -> {
            int actualStatus = response.getCode();
            assertEquals(actualStatus, 404);
            response.close();
            return null;
        });
    }
}
