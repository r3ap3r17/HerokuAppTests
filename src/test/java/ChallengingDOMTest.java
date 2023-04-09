import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import util.ReadProperties;

public class ChallengingDOMTest {
    HomePage page = new HomePage();

    @BeforeMethod
    public void initDriver() {
        page.openUrl(ReadProperties.readConfigUrl() + "/challenging_dom");
    }

    @Test
    public void challengingDOMTest() {
        page.testDOMelements();
    }

    @AfterMethod
    public void closeDriver() {
        page.closeDriver();
    }
}
