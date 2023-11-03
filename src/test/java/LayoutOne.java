import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LayoutOne extends DriverSetup {
    public void tooltip() {
        WebElement elementToHover = driver.findElement(By.className("tooltip"));
        Actions actions = new Actions(driver);
        actions.moveToElement(elementToHover).perform();
       WebElement tooltip = driver.findElement(By.xpath("//span[text()='This is your sample Tooltip text']"));
        if (tooltip.isDisplayed()) {
            System.out.println("Tooltip is displayed after hover.");
            // You can add your assertions or further actions here
        } else {
            System.out.println("Tooltip is not displayed after hover.");
            // Handle the case where the tooltip is not present
        }
    }
    public void doubleClick() {
        WebElement element = driver.findElement(By.className("ex1"));
    // Use JavaScript to scroll the element into view
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        WebElement doubleClickButton = driver.findElement(By.xpath("//button[text()=\"Double-click me\"]"));
        Actions actions = new Actions(driver);
        actions.doubleClick(doubleClickButton).perform();
        WebElement messageDisplayed = driver.findElement(By.xpath("//p[text()='Your Sample Double Click worked!']"));
        if (messageDisplayed.isDisplayed()) {
            System.out.println("Double-click worked!");
        } else {
            System.out.println("Double-click did not work.");
        }
    }
    public void dragnDrop() throws InterruptedException {
        WebElement sourceImage = driver.findElement(By.xpath("//div/img[@id=\"drag1\"]"));
        WebElement targetBox = driver.findElement(By.xpath("//div[@id=\"div1\"]"));
        Thread.sleep(5000);
        // Scroll ==========
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", sourceImage);
        JavascriptExecutor js = (JavascriptExecutor) driver;
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
//        // Drag n drop ===========
//        Actions actions = new Actions(driver);
//        actions.clickAndHold(sourceImage).moveToElement(targetBox).release().perform();
//        actions.dragAndDrop(sourceImage, targetBox).perform();
        System.out.println("Drag and drop performed");
    }
    @Test

    public void run() throws InterruptedException {
        tooltip();
        doubleClick();
        dragnDrop();
    }
}
