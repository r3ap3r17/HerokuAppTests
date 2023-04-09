package data;

import org.openqa.selenium.By;

public class Locators {
    // There is 44 Links in total - https://the-internet.herokuapp.com
    // https://the-internet.herokuapp.com/add_remove_elements/
    public static final By addButton = By.xpath("//button[text()='Add Element']");
    public static final By removeButton = By.xpath("//button[text()='Delete']");

    // https://the-internet.herokuapp.com/basic_auth
    public static final By basicAuthLink = By.xpath("//a[text()='Basic Auth']");
    public static final By authSuccessMessage = By.xpath("//p[contains(text(),'Congratulations! You must have the proper credenti')]");

    // https://the-internet.herokuapp.com/broken_images
    public static final By images = By.xpath("//div[@class='example']/img");

    // https://the-internet.herokuapp.com/challenging_dom Locators
    public static final By blueButton = By.xpath("//a[@class='button']");
    public static final By redButton = By.xpath("//a[contains(@class, 'alert')]");
    public static final By greenButton = By.xpath("//a[contains(@class, 'success')]");
    public static final By canvasWithText = By.xpath("//canvas[@id='canvas']");

    // https://the-internet.herokuapp.com/checkboxes
    public static final By checkboxes = By.xpath("//form[@id='checkboxes']//input");

    // https://the-internet.herokuapp.com/context_menu
    public static final By rightClickBox = By.xpath("//div[@id='hot-spot']");
}
