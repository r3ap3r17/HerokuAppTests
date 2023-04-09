import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import util.ReadProperties;

public class CheckboxesTest {
    HomePage page = new HomePage();

    @BeforeMethod
    public void initDriver() {
        page.openUrl(ReadProperties.readConfigUrl() + "/checkboxes");
    }

    @Test
    public void checkboxesTest() {
        page.clickUncheckedCheckBoxes();
    }

    @AfterMethod
    public void closeDriver() {
        page.closeDriver();
    }
}
