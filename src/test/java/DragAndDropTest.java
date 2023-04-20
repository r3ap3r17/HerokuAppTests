import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import util.ReadProperties;

public class DragAndDropTest {
    HomePage page = new HomePage();

    @BeforeMethod
    public void initDriver() {
        page.openUrl(ReadProperties.readConfigUrl() + "/drag_and_drop");
    }

    @Test(enabled = false)
    public void dragAndDropTest() {
        page.dragAndDrop();
    }

    @AfterMethod
    public void closeDriver() {
        page.closeDriver();
    }
}
