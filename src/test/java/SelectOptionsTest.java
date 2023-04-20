import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import util.ReadProperties;

public class SelectOptionsTest {
    HomePage page = new HomePage();

    @BeforeMethod
    public void initDriver() {
        page.openUrl(ReadProperties.readConfigUrl() + "/dropdown");
    }

    @Test
    public void selectOptionsTest() {
        page.clickAllSelectOptions();
    }

    @AfterMethod
    public void closeDriver() {
        page.closeDriver();
    }
}
