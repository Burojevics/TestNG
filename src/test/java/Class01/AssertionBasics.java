package Class01;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class AssertionBasics {
    public static WebDriver driver;

    //pre condition
    @BeforeMethod
    public void SetUpBrowser() {


        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://hrm.syntaxtechs.net/humanresources/symfony/web/index.php/auth/login");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    //post condition
    @AfterMethod
    public void closeBrowser() throws InterruptedException {
        Thread.sleep(5000);
        driver.quit();
    }

    //test case
    @Test
    public void invalidCredentials() {

        WebElement userName = driver.findElement(By.xpath("//input[@name='txtUsername']"));
        userName.sendKeys("Admin");
        WebElement password = driver.findElement(By.xpath("//input[@id = 'txtPassword']"));
        password.sendKeys("abracadabra");
        WebElement loginBtn = driver.findElement(By.xpath("//input[@name='Submit']"));
        loginBtn.click();

        WebElement error = driver.findElement(By.xpath("//span[@id='spanMessage']"));
        String errorMessage = error.getText();

        // checks if the message is the same, and if the test will pass
       String expectedError="Invalid credentials";
       //assert the value
       Assert.assertEquals(expectedError, errorMessage);
       System.out.println("I am here");
       //finding the password again as it is discarded when we clicked on login button because DOM refreshes

       //password text box is displayed
       password = driver.findElement(By.xpath("//input[@id = 'txtPassword']"));
       boolean pass=password.isDisplayed();
       //verify that the text box is displayed
       Assert.assertTrue(pass);



    }
}