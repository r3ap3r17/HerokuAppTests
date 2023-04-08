package pages;

import util.BaseActions;
import util.ReadProperties;

public class HomePage extends BaseActions {
    public void AddRemoveElements() {
        // Opens A Desired Page
        openUrl(ReadProperties.readConfigUrl() + "add_remove_elements/");

    }
}
