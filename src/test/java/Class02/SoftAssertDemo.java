package Class02;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;

public class SoftAssertDemo {
    public static WebDriver driver;

    @BeforeMethod
    public void SetupBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://hrm.syntaxtechs.net/humanresources/symfony/web/index.php/auth/login");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void testCase() {

        WebElement loginBtn=driver.findElement(By.xpath("//input[@type='submit']"));
        loginBtn.click();
        //get the text error message
        WebElement error=driver.findElement(By.xpath("//span[text()='Username cannot be empty']"));
        String actualError=error.getText();
        //String that contain expected error
        String expectedError="Username cannot be empty";

        //for soft assertion
        SoftAssert soft=new SoftAssert();
        //compare
        soft.assertEquals(actualError,expectedError);

        //login button is displayed or not
        boolean displayed = loginBtn.isDisplayed();
        System.out.println("The state of the display login is "+displayed);

        //call soft assertion
        soft.assertTrue(displayed);

        //this is a must to do in softAssert after making all the assertions
        soft.assertAll();
        //everytime when we use soft assertion, we should have assertAll();


    }

}