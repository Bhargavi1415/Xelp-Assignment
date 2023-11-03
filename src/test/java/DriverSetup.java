import jdk.internal.access.JavaIOFileDescriptorAccess;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

public class DriverSetup {
    public static  EdgeDriver driver = new EdgeDriver();
    @Test
    public void openMicrosoftEdge() throws InterruptedException {
        String driverPath = System.getProperty("user.dir") + "/src/test/Resources/msedgedriver.exe";
        System.setProperty("webdriver.edge.driver", driverPath);
        driver.manage().window().maximize();
    }
    @Test
    public void launchURL() throws InterruptedException{
        driver.get("https://trytestingthis.netlify.app/");
    }
}
