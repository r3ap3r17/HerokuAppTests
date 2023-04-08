package pages;

import data.Locators;
import util.BaseActions;

public class HomePage extends BaseActions {
    public void AddRemoveElements() {
        clickElement(Locators.addButton);
        comment("clicked add button");
        waitToBeVisible(Locators.removeButton);
//        takeScreenShot("delete-button"); // it works :D
        clickElement(Locators.removeButton);
        comment("clicked remove button");
        waitNotToBeVisible(Locators.removeButton);
    }
    public void loginBasicAuth() {
        waitToBeVisible(Locators.authSuccessMessage);
    }
    public void testImages() {
        // Check to see if images are broken or not
        // Need to use
    }

}
