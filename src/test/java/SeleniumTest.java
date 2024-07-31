import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.JavascriptExecutor;

public class SeleniumTest {

    private WebDriver webDriver;
    private String path;

    @BeforeEach
    public void setUp() {

        System.setProperty("webdriver.chrome.driver", "driver/chromedriver");// linux_64

        // Get file
        File file = new File("src/main/java/com/revature/index.html");
        path = "file://" + file.getAbsolutePath();

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
        if (result instanceof String) {
            fail("Please ensure the returned value is numeric.");
        }
        assertEquals(8, ((Number) result).intValue());
    }

    @Test
    public void testSubtract() {
        webDriver.get(path);

        JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
        Object result = jsExecutor.executeScript("return subtract(5, 3);");
        if (result instanceof String) {
            fail("Please ensure the returned value is numeric.");
        }
        assertEquals(2, ((Number) result).intValue());
    }

    @Test
    public void testMultiply() {
        webDriver.get(path);

        JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
        Object result = jsExecutor.executeScript("return multiply(5, 3);");

        if (result instanceof String) {
            fail("Please ensure the returned value is numeric.");
        }
        assertEquals(15, ((Number) result).intValue());
    }

    @Test
    public void testDivide() {
        webDriver.get(path);

        JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
        Object result = jsExecutor.executeScript("return divide(6, 3);");
        if (result instanceof String) {
            fail("Please ensure the returned value is numeric.");
        }
        assertEquals(2, ((Number) result).intValue());

        Object resultUndefined = jsExecutor.executeScript("return divide(6, 0);");
        if (resultUndefined == null) {
            resultUndefined = "undefined";
        }
        assertEquals("undefined", resultUndefined);
    }

    @Test
    public void testModulus() {
        webDriver.get(path);

        JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
        Object result = jsExecutor.executeScript("return modulus(5, 3);");
        if (result instanceof String) {
            fail("Please ensure the returned value is numeric.");
        }
        assertEquals(2, ((Number) result).intValue());
    }

    @Test
    public void testExponentiate() {
        webDriver.get(path);

        JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
        Object result = jsExecutor.executeScript("return exponentiate(2, 3);");
        if (result instanceof String) {
            fail("Please ensure the returned value is numeric.");
        }
        assertEquals(8, ((Number) result).intValue());
    }

    @AfterEach
    public void tearDown() {

        webDriver.quit();

    }
}
