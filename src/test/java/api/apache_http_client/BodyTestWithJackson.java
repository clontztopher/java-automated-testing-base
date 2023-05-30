package api.apache_http_client;

import api.ApiTestBase;
import api.entities.NotFound;
import api.entities.RateLimit;
import api.entities.User;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;
import org.testng.annotations.Test;
import api.utils.ApiUtils;

import java.io.IOException;

import static api.utils.ApiUtils.unmarshall;
import static org.testng.Assert.assertEquals;

public class BodyTestWithJackson extends ApiTestBase {

    CloseableHttpClient httpClient = HttpClients.createDefault();

    @Test
    public void returnsCorrectLogin() throws IOException {
        ClassicHttpRequest get = ClassicRequestBuilder.get(ApiUtils.BASE_ENDPOINT + "/users/clontztopher").build();

        httpClient.execute(get, response -> {

            User user = unmarshall(response, User.class);

            assertEquals(user.getLogin(), "clontztopher");

            response.close();
            return null;
        });
    }

    @Test
    public void returnsCorrectId() throws IOException {
        ClassicHttpRequest get = ClassicRequestBuilder.get(ApiUtils.BASE_ENDPOINT + "/users/clontztopher").build();

        httpClient.execute(get, response -> {
            User user = unmarshall(response, User.class);

            assertEquals(user.getId(), 14989492);

            response.close();
            return null;
        });
    }

    @Test
    public void notFoundTest() throws IOException {
        ClassicHttpRequest get = ClassicRequestBuilder.get(ApiUtils.BASE_ENDPOINT + "/nonexistingendpoint").build();

        httpClient.execute(get, response -> {
            NotFound notFound = unmarshall(response, NotFound.class);

            assertEquals(notFound.getMessage(), "Not Found");

            response.close();
            return null;
        });
    }

    @Test
    public void correctRateLimitsTest() throws IOException {
        ClassicHttpRequest get = ClassicRequestBuilder.get(ApiUtils.BASE_ENDPOINT + "/rate_limit").build();

        httpClient.execute(get, response -> {
            RateLimit rateLimit = unmarshall(response, RateLimit.class);

            assertEquals(rateLimit.getCoreLimit(), 60);

            response.close();
            return null;
        });
    }
}
