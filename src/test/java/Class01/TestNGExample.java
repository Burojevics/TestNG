package Class01;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TestNGExample {

    public static WebDriver driver;
    @BeforeMethod
    public void SetUpBrowser(){

        WebDriverManager.chromedriver().setup();
        driver= new ChromeDriver();
        driver.get("http://hrm.syntaxtechs.net/humanresources/symfony/web/index.php/auth/login");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

    }
    @AfterMethod
    public void closeBrowser() throws InterruptedException {
        Thread.sleep(5000);
        driver.quit();

    }

    @Test
    public void LoginFunctionality(){

       WebElement userName=driver.findElement(By.xpath("//input[@name='txtUsername']"));
       userName.sendKeys("Admin");
       WebElement password=driver.findElement(By.xpath("//input[@id = 'txtPassword']"));
       password.sendKeys("Hum@nhrm123");
       WebElement loginBtn=driver.findElement(By.xpath("//input[@name='Submit']"));
       loginBtn.click();
    }
    @Test
    public void passwordTextBoxVerification(){

        WebElement password=driver.findElement(By.xpath("//input[@id = 'txtPassword']"));
        System.out.println(password.isDisplayed());
    }
}

