import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import util.ReadProperties;

public class EntryAdTest {
    HomePage page = new HomePage();

    @BeforeMethod
    public void initDriver() {
        page.openUrl(ReadProperties.readConfigUrl() + "/entry_ad");
    }

    @Test
    public void entryAdTest() {
        page.dealWithEntryAdd();
    }

    @AfterMethod
    public void closeDriver() {
        page.closeDriver();
    }
}
