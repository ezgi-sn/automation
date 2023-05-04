import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;

@Test
public class AutomatedUES {
    private static WebDriver webDriver;
    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        try {
            ScreenRecorderUtil.startRecord("main");
        } catch (Exception e) {
            e.printStackTrace();
        }
        webDriver = new ChromeDriver(options);
    }

    public void login(){

        String url = "https://ues.marmara.edu.tr/Account/LoginBefore";
        webDriver.navigate().to(url);
        webDriver.manage().window().maximize();

        WebElement usernameInputfield = webDriver.findElement(By.id("UserName"));
        WebElement loginButton = webDriver.findElement(By.id("btnLoginName"));
        usernameInputfield.sendKeys("");
        loginButton.click();

        WebElement passwordInputfield = webDriver.findElement(By.id("Password"));
        WebElement logintoSiteButton = webDriver.findElement(By.id("btnLoginPass"));
        passwordInputfield.sendKeys("");
        logintoSiteButton.click();

        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        WebElement dspField = webDriver.findElement(By.xpath("/html//div[@id='courses']/table/tbody/tr[3]/td[1]/a[@title='Görüntüle']"));
        dspField.click();

        WebElement dspPopUpField = webDriver.findElement(By.xpath("/html//table[@id='activityTableContainer']/tbody/tr[2]/td[3]/a[@title='Introduction to Digital Signal Processing Canlı Ders/Hafta 1 - Oluşturan: null']/span[.='Introduction to Digital Signal Processing Canlı Ders/Hafta 1']"));
        dspPopUpField.click();

        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        ArrayList<String> tabs2 = new ArrayList<String> (webDriver.getWindowHandles());
        webDriver.switchTo().window(tabs2.get(1));

        WebElement okButton = webDriver.findElement(By.xpath("/html//div[@id='modal-optain-permissions-modal-onreplay']/div[@role='document']//button[.='Tamam']"));
        okButton.click();
        //webDriver.manage().timeouts().implicitlyWait(Duration.ofMinutes(92));
        try {
            Thread.sleep(20*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @AfterMethod
    public void tearDown() {
        webDriver.quit();
        try {
            ScreenRecorderUtil.stopRecord();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
