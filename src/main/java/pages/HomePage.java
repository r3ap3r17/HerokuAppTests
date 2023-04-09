package pages;

import data.Locators;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import util.BaseActions;

public class HomePage extends BaseActions {
    // Test's if image is broken or not
    private void checkIfImageIsBroken(WebElement img) {
        if (getAttributeValueFromElement(img, "naturalWidth").equals("0")) {
            System.out.println(getAttributeValueFromElement(img, "outerHTML") + " is broken");
        } else {
            System.out.println(getAttributeValueFromElement(img, "outerHTML") + " is not broken");
        }
//        Assert.assertEquals(getAttributeValueFromElement(img, "naturalWidth"), "0");
    }
    private String getTextFromImage(String imageName) {
//ReadProperties.readConfigScreenShotDirPath() + "/" + fileName + ".png"

        return imageName;
    }
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
        for (WebElement img : waitForVisibleElements(Locators.images)) {
            checkIfImageIsBroken(img);
        }
    }
    public void testDOMelements() {
        waitToBeVisible(Locators.blueButton);
        comment("blue button visible");
        waitToBeVisible(Locators.redButton);
        comment("red button visible");
        waitToBeVisible(Locators.greenButton);
        comment("green button visible");
        takeScreenShotOfElement(Locators.canvasWithText, "CanvasImage");
    }
}
