package pages;

import data.CommonStrings;
import data.Locators;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import util.BaseActions;
import util.ReadProperties;

import java.awt.*;
import java.io.File;

public class HomePage extends BaseActions {
    private final String uploadFilePath = ReadProperties.readFileForUploadPath();
    // Method used to extract the name of the file
    private String extractFileName(String filePath) {
        File file = new File(filePath);
        return file.getName();
    }
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
    // Right-clicks in box area, then checks if alert is present before closing it
    public void openAndCloseContextMenu() {
        rightClickElement(Locators.rightClickBox);
        comment("user right-clicked box area");
        Assert.assertEquals(switchToAlert().getText(), CommonStrings.contextMenuMessage);
        switchToAlert().accept();
        comment("user clicked ok in alert box");
    }
    // Drags element, than asserts that element was dragged
    public void dragAndDrop() {
        Assert.assertEquals(getTextFromElement(Locators.aContainer), "A");
        dragAndDropElement(Locators.aContainer, Locators.bContainer);
        Assert.assertEquals(getTextFromElement(Locators.bContainer), "A");
    }
    // Clicks all options from select
    public void clickAllSelectOptions() {
        int options = getAllOptions(Locators.select);
        for (int i = 0; i < options; i++) {
            selectOptionByIndex(Locators.select, i);
            comment(String.format("user clicked select option [%d]", i));
        }
    }

    // Waits for element to be visible to user
    public void testHiddenElement() {
        clickElement(Locators.showElementBtn);
        comment("user clicked show button");
        waitToBeVisible(Locators.hiddenElement);
    }

    // Waits for element to be present in DOM
    public void testNonExistingElement() {
        clickElement(Locators.showElementBtn);
        comment("user clicked show button");
        waitToBePresent(Locators.hiddenElement);
        // waitToBeVisible can be used in both cases
    }

    // Closes the Ad and then clicks desired element
    public void dealWithEntryAdd() {
        // sleep();
        closeAdAndClickElement(Locators.restartAdBtn, Locators.modalBtn);
    }
    // Moves mouse outside of viewport
    private void moveMouseOutsideViewport() {
        try {
            Robot robot = new Robot();
            robot.mouseMove(0, -5000);
        } catch (AWTException e) {
            System.out.println("Error: " + e);
        }
    }
    // Checks if modal appears when the mouse is out of viewport
    public void testMouseFromViewport() {
        moveMouseOutsideViewport();
        waitToBeVisible(Locators.mouseMoveModal);
    }
    // Uploads a file
    public void testUploadButton() {
        uploadToElement(Locators.uploadInput, uploadFilePath);
        comment("user chose a file to upload");
        clickElement(Locators.uploadSubmit);
        comment("user clicked upload button");
        String fileName = getTextFromElement(Locators.uploadedFileContainer);
        Assert.assertEquals(fileName, extractFileName(uploadFilePath));
    }

    // Uploads a file and then checks if it exists in downloads
    public void uploadAndCheckInDownloads() {
        testUploadButton();
        openNewTab(ReadProperties.readConfigUrl() + "/download");
        switchToTab(1);
        comment("user opened new tab " + getCurrentUrl());
        By fileLink = By.xpath(String
                .format("//div[@id='content']//a[@href='download/%s']", extractFileName(uploadFilePath)));
        waitToBeVisible(fileLink);
    }
    public boolean isElementVisibleAfterScrollingToBottom(By locator) {
        // Check if the element is within the visible viewport
        WebElement element = waitForElementToBeVisible(locator);
        int viewportHeight = getViewportHeight();
        Point elementLocation = element.getLocation();
        int elementTop = elementLocation.getY();
        int elementBottom = elementTop + element.getSize().getHeight();
        return !(elementTop >= 0 && elementTop < viewportHeight || elementBottom > 0 && elementBottom <= viewportHeight);
    }

    // Checks if floating menu is present when scrolled to bottom
    public void testFloatingMenu() {
        scrollToBottom();
        comment("user scrolled to the bottom of the page");
        Assert.assertFalse(isElementVisibleAfterScrollingToBottom(Locators.testTitleLocator));
        Assert.assertTrue(isElementVisibleAfterScrollingToBottom(Locators.floatingMenu));
    }

    // Fills out form
    private void submitForm(String username, String password) {
        clearAndTypeToElement(Locators.usernameInput, username);
        comment("user typed username");
        clearAndTypeToElement(Locators.passwordInput, password);
        comment("user typed password");
        clickElement(Locators.submitFormButton);
        comment("user clicked submit button");
    }
    // Checks if alert message is correct
    private void checkAlertMessage(String message) {
        Assert.assertTrue(getTextFromElement(Locators.alertMessage).contains(message));
    }

    // Fills out form with wrong username
    public void testFormWrongUsername() {
        submitForm(CommonStrings.USERNAME + "123", CommonStrings.PASSWORD);
        checkAlertMessage(CommonStrings.USERNAME_ERROR_MESSAGE);
    }
    // Fills out form with wrong password
    public void testFormWrongPassword() {
        submitForm(CommonStrings.USERNAME, CommonStrings.PASSWORD + "123");
        checkAlertMessage(CommonStrings.PASSWORD_ERROR_MESSAGE);
    }
    // Fills out form with Correct credentials
    public void testFormSuccessful() {
        submitForm(CommonStrings.USERNAME, CommonStrings.PASSWORD);
        waitForUrlChange(ReadProperties.readConfigUrl() + "/secure");
        checkAlertMessage(CommonStrings.SUCCESS_MESSAGE);
    }
    // Logins, then logs out
    public void testLogout() {
        testFormSuccessful();
        clickElement(Locators.logoutButton);
        checkAlertMessage(CommonStrings.LOGOUT_MESSAGE);
    }
}