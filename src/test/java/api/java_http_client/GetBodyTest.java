package api.java_http_client;

import api.ApiTestBase;
import api.entities.User;
import org.testng.annotations.Test;
import api.utils.ApiUtils;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static api.utils.ApiUtils.unmarshall;
import static org.testng.Assert.assertEquals;

public class GetBodyTest extends ApiTestBase {

    @Test
    public void bodyContainsCurrentUserUrl() throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newBuilder().build();

        HttpRequest get = HttpRequest.newBuilder(URI.create(ApiUtils.BASE_ENDPOINT + "/users/clontztopher")).build();

        HttpResponse<String> response = httpClient.send(get, HttpResponse.BodyHandlers.ofString());

        User user = unmarshall(response, User.class);

        assertEquals(user.getLogin(), "clontztopher");
    }
}
