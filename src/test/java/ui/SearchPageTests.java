package ui;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SearchPageTests extends UITestBase {

    SearchPage searchPage = new SearchPage();
    String[] navItems = {
            "Repositories",
            "Code",
            "Commits",
            "Issues",
            "Discussions",
            "Packages",
            "Marketplace",
            "Topics",
            "Wikis",
            "Users"
    };

    String[] languageItems = {
            "Java",
            "JavaScript",
            "HTML",
            "CSS",
            "Shell",
            "TypeScript",
            "Python",
            "Kotlin",
            "C++",
            "C"
    };

    @BeforeSuite
    public void visitPage() {
        searchPage.visit();
        searchPage.search("Java");
    }

    @Test
    public void confirmPage() {
        assertTrue(searchPage.isCurrent());
    }

    @Test
    public void navItemsTest() {
        for (String navItem : navItems) {
            assertNotNull(searchPage.getMenuItem(navItem), String.format("%s item not found.", navItem));
        }
    }

    @Test
    public void languageItemsTest() {
        assertEquals(searchPage.getQueryParams(), "q=Java&ref=simplesearch");
    }
}
