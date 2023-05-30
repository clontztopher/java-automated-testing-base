package api.java_http_client;

import api.ApiTestBase;
import org.testng.annotations.Test;
import api.utils.ApiUtils;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.testng.Assert.assertEquals;

public class UnauthorizedPostTest extends ApiTestBase {

    @Test
    void unauthorizedPostIsRejected() throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpRequest postRequest = HttpRequest.newBuilder(URI.create(ApiUtils.BASE_ENDPOINT + "/user/repos"))
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<Void>  response = httpClient.send(postRequest, HttpResponse.BodyHandlers.discarding());
        int actualCode = response.statusCode();

        assertEquals(401, actualCode);
    }
}
