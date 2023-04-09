package pages;

import data.Locators;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import util.BaseActions;
import util.ReadProperties;

import java.io.File;

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
    // Returns text from image (OCR)
    private String getTextFromImage(String imageName) {
        // In order for this to work, tessdata needs to be installed (https://github.com/tesseract-ocr/tessdata).
        File imageFile = new File(ReadProperties.readConfigScreenShotDirPath() + "/" + imageName + ".png");
        ITesseract instance = new Tesseract();
        instance.setLanguage("eng");
        instance.setDatapath("tessdata");
        try {
            return instance.doOCR(imageFile);
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
            return "Error while reading image";
        }
    }
    // Clicks add button, clicks remove button and waits until it disappear
    public void AddRemoveElements() {
        clickElement(Locators.addButton);
        comment("clicked add button");
        waitToBeVisible(Locators.removeButton);
//        takeScreenShot("delete-button"); // it works :D
        clickElement(Locators.removeButton);
        comment("clicked remove button");
        waitNotToBeVisible(Locators.removeButton);
    }
    // Verifies welcome message
    public void loginBasicAuth() {
        waitToBeVisible(Locators.authSuccessMessage);
    }
    // Goes through List<WebElement> and checks if image is broken
    public void testImages() {
        // Check to see if images are broken or not
        for (WebElement img : waitForVisibleElements(Locators.images)) {
            checkIfImageIsBroken(img);
        }
    }
    // Clicks 3 buttons, then screenshots canvas with text and prints that text to console
    public void testDOMelements() {
        waitToBeVisible(Locators.blueButton);
        comment("blue button visible");
        waitToBeVisible(Locators.redButton);
        comment("red button visible");
        waitToBeVisible(Locators.greenButton);
        comment("green button visible");
        takeScreenShotOfElement(Locators.canvasWithText, "CanvasImage");
//        System.out.println(getTextFromImage("CanvasImage")); // This works
    }
    // Clicks all unchecked checkboxes
    public void clickUncheckedCheckBoxes() {
        for (WebElement checkbox : waitForVisibleElements(Locators.checkboxes)) {
            if (!checkbox.isSelected()) {
                checkbox.click(); // It already has wait
                comment("clicked checkbox");
            }
        }
    }
}
