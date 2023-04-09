import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import util.ReadProperties;

public class ContextMenuTest {
    HomePage page = new HomePage();

    @BeforeMethod
    public void initDriver() {
        page.openUrl(ReadProperties.readConfigUrl() + "/context_menu");
    }

    @Test
    public void contextMenuTest() {
        page.openAndCloseContextMenu();
    }

    @AfterMethod
    public void closeDriver() {
        page.closeDriver();
    }
}
