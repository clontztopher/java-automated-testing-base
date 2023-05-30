package ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchPage extends PageBase {

    private By searchIdentifier = By.cssSelector("input[aria-label='Search GitHub']");

    SearchPage() {
        url = "https://github.com/search";
    }

    public void search(String term) {
        WebElement input = driver.findElement(searchIdentifier);
        input.sendKeys(term);
        input.submit();
    }

    public String getMenuItem(String text) {
       List<WebElement> menuItems = driver.findElements(By.xpath(String.format("//a[contains(@class, 'menu-item') and text()='%s']", text)));
       if (menuItems.size() > 0) {
           return menuItems.get(0).getText();
       }
       return null;
    }
}
