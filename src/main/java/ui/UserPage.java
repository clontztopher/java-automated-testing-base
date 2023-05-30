package ui;

import org.openqa.selenium.By;


public class UserPage extends PageBase {

    private final By userClass = By.className("vcard-username");
    private final By primaryLinkClass = By.className("Link--primary");

    UserPage() {
        url = "https://github.com/clontztopher";
    }

    public void visitTab(String tabName) {
        driver.get(String.format("%s/tab=%s", url, tabName));
    }

    public String getUserName() {
        return driver.findElement(userClass).getText();
    }

    public String getPrimaryLink() {
        return driver.findElement(primaryLinkClass).getText();
    }
}
