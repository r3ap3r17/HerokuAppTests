package data;

import org.openqa.selenium.By;

public class Locators {
    public static final By addButton = By.xpath("//button[text()='Add Element']");
    public static final By removeButton = By.xpath("//button[text()='Delete']");
    public static final By basicAuthLink = By.xpath("//a[text()='Basic Auth']");
    public static final By authSuccessMessage = By.xpath("//p[contains(text(),'Congratulations! You must have the proper credenti')]");
}
