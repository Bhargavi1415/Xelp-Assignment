import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LayoutTwo extends DriverSetup{
    public void firstAndLastName() throws InterruptedException {
        //Enter first name ========
        WebElement firstNameField = driver.findElement(By.xpath("//input[@id=\"fname\"]"));
        WebElement lastNameField = driver.findElement(By.xpath("//input[@id=\"lname\"]"));
        String firstName = "TestName";
        String lastName = "TestName";
        firstNameField.sendKeys(firstName);
        Thread.sleep(1500);
        lastNameField.sendKeys(lastName);
        Thread.sleep(1500);
   }
   public void gender() throws InterruptedException {
       // Select gender only one option must be selected ===============
       WebElement male = driver.findElement(By.xpath("//input[@id=\"male\"]"));
       WebElement female = driver.findElement(By.xpath("//input[@id=\"female\"]"));
       WebElement others = driver.findElement(By.xpath("//input[@id=\"other\"]"));
       // Select male ======
       male.click();
       Thread.sleep(1500);
       Assert.assertTrue(male.isSelected());
       Assert.assertFalse(female.isSelected());
       Assert.assertFalse(others.isSelected());
       // Select Female ========
       female.click();
       Thread.sleep(1500);
       Assert.assertTrue(female.isSelected());
       Assert.assertFalse(male.isSelected());
       Assert.assertFalse(others.isSelected());
       // Select others ========
       others.click();
       Thread.sleep(1500);
       Assert.assertFalse(female.isSelected());
       Assert.assertFalse(male.isSelected());
       Assert.assertTrue(others.isSelected());
   }
   public void dropDownOptions() throws InterruptedException {
        // Select dropdown options =============
       WebElement optionDropdown = driver.findElement(By.xpath("//select[@id=\"option\"]"));
       WebElement option1 = driver.findElement(By.xpath("//option[text()=\"Option 1\"]"));
       WebElement option2 = driver.findElement(By.xpath("//option[text()=\"Option 2\"]"));
       WebElement option3 = driver.findElement(By.xpath("//option[text()=\"Option 3\"]"));
       optionDropdown.click();
       Thread.sleep(1500);
       // Select option 1 =========
       option1.click();
       Thread.sleep(1500);
       //Select option 2 ========
       optionDropdown.click();
       Thread.sleep(1500);
       option2.click();
       Thread.sleep(1500);
       //Select option 3 =========
       Thread.sleep(1500);
       optionDropdown.click();
       Thread.sleep(1500);
       option3.click();
       Thread.sleep(1500);
    }
    public void multipleOptionsSelect() {
        WebElement multipleSelectDropdown = driver.findElement(By.xpath("//select[@id=\"owc\"]"));
        Select select = new Select(multipleSelectDropdown);
        // Select options by their visible text
        select.selectByVisibleText("Option 1");
        select.selectByVisibleText("Option 3");
    }
    public void applicableoptions() throws InterruptedException {
        WebElement option1 = driver.findElement(By.xpath("//input[@value=\"Option 1\"]"));
        WebElement option2 = driver.findElement(By.xpath("//input[@value=\"Option 2\"]"));
        WebElement option3 = driver.findElement(By.xpath("//input[@value=\"Option 3\"]"));
        //Select only one option ==============
        option1.click();
        Assert.assertTrue(option1.isSelected());
        Assert.assertFalse(option2.isSelected());
        Assert.assertFalse(option3.isSelected());
        // Select two options ================
        Thread.sleep(1500);
        option2.click();
        Thread.sleep(1500);
        Assert.assertTrue(option1.isSelected());
        Assert.assertTrue(option2.isSelected());
        Assert.assertFalse(option3.isSelected());
        // Select all three options ============
        option3.click();
        Thread.sleep(1500);
        Assert.assertTrue(option1.isSelected());
        Assert.assertTrue(option2.isSelected());
        Assert.assertTrue(option3.isSelected());
    }
    public void automaticallyGuessAnswer() throws InterruptedException{
        WebElement guessField = driver.findElement(By.xpath("//input[@list=\"datalists\"]"));
        guessField.sendKeys("chocolate");
    }
    public void rangeValue() throws InterruptedException {
        Thread.sleep(1500);

        WebElement rangeInput = driver.findElement(By.id("a"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", rangeInput);
        Thread.sleep(1500);
        // Set the desired value for the range input
        setRangeInputValue(driver, rangeInput, 75); // Replace 75 with the desired value
    }

    private void setRangeInputValue(EdgeDriver driver, WebElement rangeInput, int value) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value = arguments[1];", rangeInput, Integer.toString(value));
    }
    public void uploadFile() throws InterruptedException {
        Thread.sleep(1500);
        WebElement fileInput = driver.findElement(By.xpath("//input[@id=\"myfile\"]"));
        String filePath = "C:\\Users\\bob\\IdeaProjects\\TryTestingAssignment\\src\\test\\files\\image.jpg"; //Replace with the actual file path
        fileInput.sendKeys(filePath);
        Thread.sleep(1500);
    }
    public void quantityRange() throws InterruptedException {
        WebElement range = driver.findElement(By.xpath("//input[@id=\"quantity\"]"));
        range.sendKeys("11");
        Thread.sleep(1500);
    }
    public void longMessage() throws InterruptedException {
        WebElement longMessageField = driver.findElement(By.xpath("//textarea"));
        longMessageField.sendKeys("Dog is under the bed.");
        Thread.sleep(1500);
    }
    public void submit() throws InterruptedException {
        WebElement submitButton = driver.findElement(By.xpath("//button[text()=\" Submit\"]"));
        submitButton.click();
        Thread.sleep(1500);
    }
    public void newTabHandle() throws InterruptedException {
        // Here it is handling the new tab opened after clicking on submit
        String mainTabHandle = driver.getWindowHandle();
        for (String handle : driver.getWindowHandles()) {
            Thread.sleep(1500);
            if (!handle.equals(mainTabHandle)) {
                Thread.sleep(1500);
                // Switch to the new tab
                driver.switchTo().window(handle);
                Thread.sleep(1500);
                System.out.println("New tab opened");
                Thread.sleep(1500);
                driver.close();
            }
        }
        Thread.sleep(1500);
        driver.switchTo().window(mainTabHandle);
        System.out.println("New tab closed");
    }
    @Test
    public void runLayoutTwo() throws InterruptedException {
        firstAndLastName();
        gender();
        dropDownOptions();
        multipleOptionsSelect();
        applicableoptions();
        automaticallyGuessAnswer();
        rangeValue();
        uploadFile();
        quantityRange();
        longMessage();
        submit();
        newTabHandle();
    }
}
