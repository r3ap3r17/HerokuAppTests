import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import util.ReadProperties;

public class UploadAndCheckDownloadsTest {
    HomePage page = new HomePage();

    @BeforeMethod
    public void initDriver() {
        page.openUrl(ReadProperties.readConfigUrl() + "/upload");
    }

    @Test
    public void uploadAndCheckDownloadsTest() {
        page.uploadAndCheckInDownloads();
    }

    @AfterMethod
    public void closeDriver() {
        page.closeDriver();
    }
}
