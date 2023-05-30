package ui;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class UserPageTests extends UITestBase {

    UserPage userPage = new UserPage();

    @BeforeSuite
    public void visitUserPage() {
        userPage.visit();
    }

    @Test
    public void confirmUserPage() {
        assertTrue(userPage.isCurrent());
    }

    @Test
    public void userNameIsCorrectOnOverviewTab() {
        assertEquals(userPage.getUserName(), "clontztopher");
    }

    @Test
    public void primaryLinkIsCorrectOnOVerviewTab() {
        assertEquals(userPage.getPrimaryLink(), "clontz.dev");
    }
}
