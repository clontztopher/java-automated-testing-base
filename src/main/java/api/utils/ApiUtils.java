package api.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import java.io.IOException;
import java.net.http.HttpResponse;

public class ApiUtils {
    public static final String BASE_ENDPOINT = "https://api.github.com";

    public enum HeaderName {
        CONTENT_TYPE("Content-Type"),
        SERVER("Server"),
        X_RATE_LIMIT("X-RateLimit-Limit"),
        X_RATE_LIMIT_REMAINING("X-RateLimit-Remaining"),
        ETAG("ETag"),
        DATE("Date"),
        ACCESS_METHODS("Access-Control-Allow-Methods");

        private String val;

        HeaderName(String val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return val;
        }
    }

    public static <T> T unmarshall(ClassicHttpResponse response, Class<T> entityClass) throws IOException, ParseException {
        String jsonBody = EntityUtils.toString(response.getEntity());

        return new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .readValue(jsonBody, entityClass);
    }

    public static <T> T unmarshall(HttpResponse response, Class<T> entityClass) throws IOException {
        String jsonBody = (String) response.body();

        return new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .readValue(jsonBody, entityClass);
    }
}
