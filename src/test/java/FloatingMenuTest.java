import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import util.ReadProperties;

public class FloatingMenuTest {
    HomePage page = new HomePage();

    @BeforeMethod
    public void initDriver() {
        page.openUrl(ReadProperties.readConfigUrl() + "/floating_menu");
    }

    @Test
    public void floatingMenuTest() {
        page.testFloatingMenu();
    }

    @AfterMethod
    public void closeDriver() {
        page.closeDriver();
    }
}
