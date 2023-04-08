import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import util.ReadProperties;

public class BrokenImagesTest {
    HomePage page = new HomePage();

    @BeforeMethod
    public void initDriver() {
        page.openUrl(ReadProperties.readConfigUrl() + "/broken_images");
    }

    @Test
    public void brokenImagesTest() {
        page.loginBasicAuth();
    }

    @AfterMethod
    public void closeDriver() {
        page.closeDriver();
    }
}
