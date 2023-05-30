package api.apache_http_client;

import api.ApiTestBase;
import api.entities.User;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;
import org.json.JSONObject;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;
import api.utils.ApiUtils;

import java.io.IOException;

import static org.testng.Assert.assertEquals;

public class BodyTestWithSimpleMap extends ApiTestBase {
    CloseableHttpClient httpClient = HttpClients.createDefault();

    @AfterSuite
    public void suiteCleanup() throws IOException {
        httpClient.close();
    }

    @Test
    public void returnsCorrectLogin() throws IOException {
        ClassicHttpRequest get = ClassicRequestBuilder.get(ApiUtils.BASE_ENDPOINT + "/users/clontztopher").build();

        httpClient.execute(get, response -> {
            String body = EntityUtils.toString(response.getEntity());

            JSONObject jsonObject = new JSONObject(body);

            String loginValue = (String) getValueFor(jsonObject, User.LOGIN);

            assertEquals(loginValue, "clontztopher");

            response.close();
            return null;
        });
    }

    @Test
    public void returnsCorrectUserId() throws IOException {
        ClassicHttpRequest get = ClassicRequestBuilder.get(ApiUtils.BASE_ENDPOINT + "/users/clontztopher").build();

        httpClient.execute(get, response -> {
            String body = EntityUtils.toString(response.getEntity());

            JSONObject jsonObject = new JSONObject(body);

            Integer idValue = (Integer) getValueFor(jsonObject, User.ID);

            assertEquals(idValue, Integer.valueOf(14989492));

            response.close();
            return null;
        });
    }

    private Object getValueFor(JSONObject jsonObject, String key) {
        return jsonObject.get(key);
    }
}
