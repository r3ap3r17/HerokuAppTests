import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import util.ReadProperties;

public class MoveMouseOutsideViewportTest {
    HomePage page = new HomePage();

    @BeforeMethod
    public void initDriver() {
        page.openUrl(ReadProperties.readConfigUrl() + "/exit_intent");
    }

    @Test
    public void moveMouseOutsideViewportTest() {
        page.testMouseFromViewport();
    }

    @AfterMethod
    public void closeDriver() {
        page.closeDriver();
    }
}
