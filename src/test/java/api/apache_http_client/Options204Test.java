package api.apache_http_client;

import api.ApiTestBase;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;
import org.testng.annotations.Test;
import api.utils.ApiUtils;

import java.io.IOException;

import static org.testng.Assert.assertEquals;

public class Options204Test extends ApiTestBase {

    CloseableHttpClient httpClient = HttpClients.createDefault();

    @Test
    public void optionsReturnsCorrectMethodList() throws IOException {

        String header = "access-control-allow-methods";
        String expectedReply = "GET, POST, PATCH, PUT, DELETE";

        ClassicHttpRequest options = ClassicRequestBuilder.options(ApiUtils.BASE_ENDPOINT).build();

        httpClient.execute(options, response -> {

            Header actualHeader = response.getHeader(header);

            assertEquals(actualHeader.getValue(), expectedReply);

            response.close();
            return null;
        });
    }
}
