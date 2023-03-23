package Class02;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class dataProvider {
    //test scenario
    //navigate to the Syntax HRMS
    //login into the website using the following credentials and check for correct errors
    //a.username- "Admin", pass "12345" ----> error message="Invalid credentials"
    //b.username- "ABCD",  pass "Hum@nhtm123" ----> error message="Invalid credentials"
    //c.username-  "",      pass "Hum@nhrm123" ----> error message="Username cannot be empty"
    //d.username- "Admin", pass "" ----> error message="Password cannot be empty"

    //dataProvider
    public static WebDriver driver;
    @DataProvider(name="credentials")

    public Object[][] data(){
        Object[][]loginData= {
                {"Admin", "12345", "Invalid credentials"},
                {"ABCD", "Hum@nhrm123", "Invalid credentials"},
                {"Admin", "", "Password cannot be empty"},
                {"", "Hum@nhrm123", "Username cannot be empty"}
        };
        return loginData;
        }

        @Test(dataProvider= "credentials") //connects the test with data provider
        public void invalidCredentials(String username, String password, String errorMrg){

        driver.findElement(By.xpath("//input[@name='txtUsername']")).sendKeys(username);
        driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys(password);
        driver.findElement(By.xpath("//input[@type='submit']")).click();

        //get the error message
        WebElement error=driver.findElement(By.xpath("//span[@id='spanMessage']"));
        String actualError=error.getText();

        // assertion
            Assert.assertEquals(actualError,errorMrg);

        }

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
}
