import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import util.ReadProperties;

public class BasicAuthTest {
    HomePage page = new HomePage();

    @BeforeMethod
    public void initDriver() {
        page.openUrlWithCreds(ReadProperties.readConfigUrl() + "/basic_auth", "admin:admin");
    }

    @Test
    public void basicAuthTest() {
        page.loginBasicAuth();
    }

    @AfterMethod
    public void closeDriver() {
        page.closeDriver();
    }
}
