import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LayoutOne extends DriverSetup {
    public void tooltip() throws InterruptedException {
        WebElement elementToHover = driver.findElement(By.className("tooltip"));
        Thread.sleep(1500);
        Actions actions = new Actions(driver);
        actions.moveToElement(elementToHover).perform();
        Thread.sleep(1500);
       WebElement tooltip = driver.findElement(By.xpath("//span[text()='This is your sample Tooltip text']"));
        if (tooltip.isDisplayed()) {
            Thread.sleep(1500);
            System.out.println("Tooltip is displayed after hover.");
            // You can add your assertions or further actions here
        } else {
            System.out.println("Tooltip is not displayed after hover.");
            // Handle the case where the tooltip is not present
        }
    }
    public void doubleClick() throws InterruptedException {
        WebElement element = driver.findElement(By.className("ex1"));
    // Scrolling =======================
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        WebElement doubleClickButton = driver.findElement(By.xpath("//button[text()=\"Double-click me\"]"));
        Actions actions = new Actions(driver);
        actions.doubleClick(doubleClickButton).perform();
        Thread.sleep(1500);
        WebElement messageDisplayed = driver.findElement(By.xpath("//p[text()='Your Sample Double Click worked!']"));
        if (messageDisplayed.isDisplayed()) {
            Thread.sleep(1500);
            System.out.println("Double-click worked!");
        } else {
            System.out.println("Double-click did not work.");
        }
    }
    public void dragnDrop() throws InterruptedException {
        WebElement sourceImage = driver.findElement(By.xpath("//div/img[@id=\"drag1\"]"));
        WebElement targetBox = driver.findElement(By.xpath("//div[@id=\"div1\"]"));
        Thread.sleep(1500);
        // Scroll ==========
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", sourceImage);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Thread.sleep(1500);
        js.executeScript("function createEvent(typeOfEvent) {\n" +
                "  var event = document.createEvent(\"CustomEvent\");\n" +
                "  event.initCustomEvent(typeOfEvent, true, true, null);\n" +
                "  event.dataTransfer = {\n" +
                "    data: {},\n" +
                "    setData: function (key, value) {\n" +
                "    this.data[key] = value;\n" +
                "  },\n" +
                "    getData: function (key) {\n" +
                "    return this.data[key];\n" +
                "  }\n" +
                "};\n" +
                "return event;\n" +
                "}\n" +
                "\n" +
                "function dispatchEvent(element, event, transferData) {\n" +
                "  if (transferData !== undefined) {\n" +
                "    event.dataTransfer = transferData;\n" +
                "  }\n" +
                "  if (element.dispatchEvent) {\n" +
                "    element.dispatchEvent(event);\n" +
                "  } else if (element.fireEvent) {\n" +
                "    element.fireEvent(\"on\" + event.type, event);\n" +
                "  }\n" +
                "}\n" +
                "\n" +
                "function simulateHTML5DragAndDrop(element, target) {\n" +
                "  var dragStartEvent = createEvent('dragstart');\n" +
                "  dispatchEvent(element, dragStartEvent);\n" +
                "  var dropEvent = createEvent('drop');\n" +
                "  dispatchEvent(target, dropEvent, dragStartEvent.dataTransfer);\n" +
                "  var dragEndEvent = createEvent('dragend');\n" +
                "  dispatchEvent(element, dragEndEvent, dropEvent.dataTransfer);\n" +
                "}\n" +
                "\n" +
                "var sourceImage = arguments[0];\n" +
                "var targetBox = arguments[1];\n" +
                "simulateHTML5DragAndDrop(sourceImage, targetBox);", sourceImage, targetBox);
        System.out.println("Drag and drop performed");
    }
    public void sampleAlertButton() throws InterruptedException {
        WebElement alertButton = driver.findElement(By.xpath("//button[text()=\" Your Sample Alert Button!\"]"));
        // Select OK button
        alertButton.click();
        Alert alert = driver.switchTo().alert();
        alert.accept(); // Click the "OK" button
        WebElement alertTextOk = driver.findElement(By.xpath("//p[text()=\"You Pressed the OK Button!\"]"));
        if (alertTextOk.equals("You Pressed the OK Button!")) {
            Thread.sleep(1500);
            System.out.println("Alert message is correct.");
        } else {
            System.out.println("Alert message is not as expected.");
        }
        //Select Cancel button ===============
        alertButton.click();
        Alert alert1 = driver.switchTo().alert();
        Thread.sleep(1500);
        alert.dismiss(); // Click the "Cancel" button
        Thread.sleep(1500);
        WebElement alertTextCancel = driver.findElement(By.xpath("//p[text()=\"You pressed the Cancel Button!\"]"));
        if (alertTextCancel.equals("You pressed the Cancel Button!")) {
            System.out.println("Alert message is correct.");
        } else {
            System.out.println("Alert message is not as expected.");
        }
    }
    public void loginPage() throws InterruptedException {
       WebElement loginButton = driver.findElement(By.xpath("//input[@type=\"submit\"]"));
       WebElement userNameField = driver.findElement(By.xpath("//input[@id=\"uname\"]"));
       WebElement passwordField = driver.findElement(By.xpath("//input[@id=\"pwd\"]"));
       String username = "test";
       String password = "test";
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", loginButton);
        Thread.sleep(1500);
        userNameField.sendKeys(username);
        Thread.sleep(1500);
        passwordField.sendKeys(password);
        Thread.sleep(1500);
        loginButton.click();
        // Login successful page =================
        WebElement loginSuccessMessage = driver.findElement(By.xpath("//*[text()=\"Login Successful :) \"]"));
        if (loginSuccessMessage.isDisplayed()) {
            System.out.println("Login worked!");
        } else {
            System.out.println("Login did not work.");
        }
        // Click on here =============
        WebElement here = driver.findElement(By.xpath("//*[text()=\"here\"]"));
        Thread.sleep(1500);
        here.click();
    }
    @Test

    public void run() throws InterruptedException {
        sampleAlertButton();
        tooltip();
        doubleClick();
        dragnDrop();
        loginPage();
    }
}
