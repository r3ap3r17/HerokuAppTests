import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import util.ReadProperties;

public class HiddenElementTest {
    HomePage page = new HomePage();

    @BeforeMethod
    public void initDriver() {
        page.openUrl(ReadProperties.readConfigUrl() + "/dynamic_loading/1");
    }

    @Test
    public void hiddenElementTest() {
        page.testHiddenElement();
    }

    @AfterMethod
    public void closeDriver() {
        page.closeDriver();
    }
}
