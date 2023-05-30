package ui;

import org.openqa.selenium.WebDriver;
import ui.utils.Browser;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;

public abstract class PageBase {

    protected final WebDriver driver;
    protected String url;

    PageBase() {
        driver = Browser.getChromeDriver();
    }

    public void visit() {
        if (url != null) {
            driver.get(url);
        }
    }

    public boolean isCurrent() {
        try {
            URI uri = new URI(driver.getCurrentUrl());

            String cleanUri = new URI(
                    uri.getScheme(),
                    uri.getAuthority(),
                    uri.getPath(),
                    null,
                    uri.getFragment())
                    .toString();

            return cleanUri.equals(url);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getQueryParams() {
        try {
            URI uri = new URI(driver.getCurrentUrl());
            return uri.getQuery();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }
}
