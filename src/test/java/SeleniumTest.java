import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SeleniumTest {

    private WebDriver webDriver;

    @BeforeEach
    public void setUp() {
     
        
         System.setProperty("webdriver.chrome.driver", "driver/chromedriver");//linux_64
       

        // Get file
        File file = new File("index.html");
        String path = "file://" + file.getAbsolutePath();

        // Create a new ChromeDriver instance
         ChromeOptions options = new ChromeOptions();
        
        options.addArguments("headless");
          webDriver = new ChromeDriver(options);

        // Open the HTML file
        webDriver.get(path);
    }

    
    @Test
    public void testAdd() {
        webDriver.get(path);
        
        JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
        Object result = jsExecutor.executeScript("return add(5, 3);");
        
        assertEquals(8, ((Number) result).intValue());
    }
    
    @Test
    public void testSubtract() {
        webDriver.get(path);
        
        JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
        Object result = jsExecutor.executeScript("return subtract(5, 3);");
        
        assertEquals(2, ((Number) result).intValue());
    }
    
    @Test
    public void testMultiply() {
        webDriver.get(path);
        
        JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
        Object result = jsExecutor.executeScript("return multiply(5, 3);");
        
        assertEquals(15, ((Number) result).intValue());
    }
    
    @Test
    public void testDivide() {
        webDriver.get(path);
        
        JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
        Object result = jsExecutor.executeScript("return divide(6, 3);");
        
        assertEquals(2, ((Number) result).intValue());
        
        Object resultUndefined = jsExecutor.executeScript("return divide(6, 0);");
        
        assertEquals("undefined", resultUndefined);
    }
    
    @Test
    public void testModulus() {
        webDriver.get(path);
        
        JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
        Object result = jsExecutor.executeScript("return modulus(5, 3);");
        
        assertEquals(2, ((Number) result).intValue());
    }
    
    @Test
    public void testExponentiate() {
        webDriver.get(path);
        
        JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
        Object result = jsExecutor.executeScript("return exponentiate(2, 3);");
        
        assertEquals(8, ((Number) result).intValue());
    }


    @AfterEach
    public void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}


class BrowserUtils {
    public static String getWebDriverName() {
        String[] browsers = { "chrome", "firefox", "edge", "ie" };

        for (String browser : browsers) {
            try {
                switch (browser) {
                    case "chrome":
                        WebDriverManager.chromedriver().setup();
                        new ChromeDriver().quit();
                        break;
                    case "firefox":
                        WebDriverManager.firefoxdriver().setup();
                        new FirefoxDriver().quit();
                        break;
                    case "edge":
                        WebDriverManager.edgedriver().setup();
                        new EdgeDriver().quit();
                        break;
                    case "ie":
                        WebDriverManager.iedriver().setup();
                        new InternetExplorerDriver().quit();
                        break;
                }
                return browser;
            } catch (Exception e) {
                continue;
            }
        }
        return "Unsupported Browser";
    }
}
