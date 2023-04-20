package util;

import data.Timeouts;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import static util.DriverUtils.driver;

public class BaseActions {
    protected static WebDriverWait wait;
    protected static WebElement element;
    protected static Select select;
    private static int counter = 1;
    // Prints a comment to console
    protected void comment(String message) {
        if (counter == 1) System.out.println("\n###### USER LOG ######");
        System.out.println("STEP " + counter + ": " + message.toUpperCase());
        counter++;
    }
    // Method Used To Take ScreenShot
    public void takeScreenShot(String fileName) {
        try {
            File file = new File(ReadProperties.readConfigScreenShotDirPath() + "/" + fileName + ".png");
            Screenshot myScreenshot = new AShot().shootingStrategy(ShootingStrategies
                    .viewportPasting(100)).takeScreenshot(driver);
            ImageIO.write(myScreenshot.getImage(),"PNG", file);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    // Screenshot specific element
    public void takeScreenShotOfElement(By locator, String fileName) {
        try {
            File file = new File(ReadProperties.readConfigScreenShotDirPath() + "/" + fileName + ".png");
            Screenshot myScreenshot = new AShot().shootingStrategy(ShootingStrategies
                    .viewportPasting(100)).takeScreenshot(driver, waitForElementToBeVisible(locator));
            ImageIO.write(myScreenshot.getImage(),"PNG", file);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    // Method Used To Take ScreenShot Before Closing Driver
    public void takeScreenShotBeforeClosing() {
        try {
            File file = new File(ReadProperties.readConfigScreenShotDirPath()
                    + "/" + Thread.currentThread().getStackTrace()[3].getClassName() + ".png");
            Screenshot myScreenshot = new AShot().shootingStrategy(ShootingStrategies
                    .viewportPasting(100)).takeScreenshot(driver);
            ImageIO.write(myScreenshot.getImage(),"PNG", file);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    // Sleeps for 3 seconds
    protected void sleep(Integer... time) {
        int timeout = (time.length > 0) ? time[0] : 3;
        try {
            Thread.sleep(Duration.ofSeconds(timeout));
        } catch (InterruptedException ignored) {
            System.out.println("Sleep interrupted !");
        }
    }
    // Initialises and configure WebDriver
    public void openUrl(String URL) {
        counter = 1;
        DriverUtils.initDriver();
        DriverUtils.configDriver();
        driver.get(URL);
    }
    // Send Auth Params
    public void openUrlWithCreds(String URL, String auth) {
        // Split the URL into two parts: the scheme (https://)
        int schemeIndex = URL.indexOf("://");
        // Append the authentication information after the scheme
        String urlWithAuth = URL.substring(0, schemeIndex + 3) + auth + "@" + URL.substring(schemeIndex + 3);
        System.out.println(urlWithAuth);
        this.openUrl(urlWithAuth);
    }
    // Closes WebDriver
    public void closeDriver() {
        takeScreenShotBeforeClosing();
        driver.quit();
    }
    // Waits for page to completely load
    public void waitForPageToLoad(Integer... time) {
        int timeout = (time.length > 0) ? time[0] : Timeouts.TIMEOUT_MEDIUM;
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
        wait.until(driver -> ((JavascriptExecutor) driver)
                .executeScript("return document.readyState").equals("complete"));
    }
    // Waits for URL to change
    public void waitForUrlChange(String url, Integer... time) {
        int timeout = (time.length > 0) ? time[0] : Timeouts.TIMEOUT_MEDIUM;
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.urlContains(url));
    }
    // Waits until element is visible and returns WebElement obj
    protected WebElement waitForElementToBeVisible(By locator, Integer... time) {
        int timeout = (time.length > 0) ? time[0] : Timeouts.TIMEOUT_MEDIUM;
        wait  = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    // Waits for WebElement to be visible
    protected void waitToBeVisible(By locator, Integer... time) {
        int timeout = (time.length > 0) ? time[0] : Timeouts.TIMEOUT_MEDIUM;
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    // Waits for WebElement to be added to the DOM
    protected void waitToBePresent(By locator, Integer... time) {
        int timeout = (time.length > 0) ? time[0] : Timeouts.TIMEOUT_MEDIUM;
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
    // Waits for element to be clickable
    protected void waitToBeClickable(By locator, Integer... time) {
        int timeout = (time.length > 0) ? time[0] : Timeouts.TIMEOUT_MEDIUM;
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    // Waits for element to not be visible
    protected void waitNotToBeVisible(By locator, Integer... time) {
        int timeout = (time.length > 0) ? time[0] : Timeouts.TIMEOUT_MEDIUM;
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }
    // Returns true if element is displayed
    public boolean verifyElementIsVisible(By locator, Integer... time) {
        int timeout = (time.length > 0) ? time[0] : Timeouts.TIMEOUT_MEDIUM;
        return waitForElementToBeVisible(locator, timeout).isDisplayed();
    }
    // Waits for element to be visible than returns a list of WebElements
    protected List<WebElement> waitForVisibleElements(By lcoator, Integer... time) {
        int timeout = (time.length > 0) ? time[0] : Timeouts.TIMEOUT_MEDIUM;
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(lcoator));
    }
    // Clicks WebElement
    protected void clickElement(By locator) {
        element = waitForElementToBeVisible(locator);
        element.click();
    }
    // Tries to click element, if ad appears  closes the ad and finally clicks element
    protected void closeAdAndClickElement(By elementLocator, By closeAdLocator) {
        try {
            waitForElementToBeVisible(elementLocator).click();
        } catch (ElementClickInterceptedException e) {
            waitForElementToBeVisible(closeAdLocator).click();
            waitForElementToBeVisible(elementLocator).click();
        }
    }
    // Right-Clicks WebElement
    protected void rightClickElement(By locator) {
        element = waitForElementToBeVisible(locator);
        Actions actions = new Actions(driver);
        actions.contextClick(element).perform();
    }
    // Drags element to desired location
    protected void dragAndDropElement(By srcLocator, By targetLocator) {
        Actions builder = new Actions(driver);
        builder.clickAndHold(waitForElementToBeVisible(srcLocator))
                .moveToElement(waitForElementToBeVisible(targetLocator))
                .release()
                .perform();
    }
    // Moves mouse to desired cordinates
    protected void moveMouse(int x, int y) {
        Actions builder = new Actions(driver);
        builder.moveByOffset(x, y).build().perform();
    }
    // Waits for Select and selects option by index
    public void selectOptionByIndex(By locator, int index) {
        select = new Select(waitForElementToBeVisible(locator, Timeouts.TIMEOUT_MEDIUM));
        try {
            select.selectByIndex(index);
        } catch (UnsupportedOperationException e) {
            System.out.printf("Option [%d] is disabled%n", index);
            System.out.println("Error: " + e);
        }
    }
    // Returns number of all options from select box
    public int getAllOptions(By locator) {
        select = new Select(waitForElementToBeVisible(locator, Timeouts.TIMEOUT_MEDIUM));
        return select.getOptions().size();
    }
    // Waits, then returns Alert
    protected Alert switchToAlert(Integer... time) {
        int timeout = (time.length > 0) ? time[0] : Timeouts.TIMEOUT_MEDIUM;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.alertIsPresent());
        return driver.switchTo().alert();
    }
    // Returns text from WebElement
    protected String getTextFromElement(By locator) {
        element = waitForElementToBeVisible(locator);
        return element.getText();
    }
    // Returns desired attribute
    protected String getAttributeValueFromElement(By locator, String attribute) {
        element = waitForElementToBeVisible(locator);
        return element.getAttribute(attribute);
    }
    protected String getAttributeValueFromElement(WebElement element, String attribute) {
        return element.getAttribute(attribute);
    }
}
