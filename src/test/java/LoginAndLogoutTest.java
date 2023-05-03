import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import util.ReadProperties;

public class LoginAndLogoutTest {
    HomePage page = new HomePage();

    @BeforeMethod
    public void initDriver() {
        page.openUrl(ReadProperties.readConfigUrl() + "/login");
    }

    @Test
    public void testFormSuccessful() {
        page.testFormSuccessful();
    }

    @Test
    public void testFormWrongUsername() {
        page.testFormWrongUsername();
    }

    @Test
    public void testFormWrongPassword() {
        page.testFormWrongPassword();
    }

    @Test
    public void testLogout() {
        page.testLogout();
    }


    @AfterMethod
    public void closeDriver() {
        page.closeDriver();
    }
}
