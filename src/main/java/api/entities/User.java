package api.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    // For tests using JsonObject
    public static final String LOGIN = "login";
    public static final String ID = "id";

    // For tests using Jackson
    private String login;
    private int id;

    public String getLogin() {
        return login;
    }

    public int getId() {
        return id;
    }
}
