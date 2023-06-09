import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import util.ReadProperties;

public class AddRemoveElementsTest {
    HomePage page = new HomePage();

    @BeforeMethod
    public void initDriver() {
        page.openUrl(ReadProperties.readConfigUrl() + "/add_remove_elements/");
    }

    @Test
    public void addRemoveElementsTest() {
        page.AddRemoveElements();
    }

    @AfterMethod
    public void closeDriver() {
        page.closeDriver();
    }
}
