package org.example;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LicenceTermsAndPolicyTest {

    private static WebDriver webDriver;

    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/Omar/Downloads/chromedriver-win64/chromedriver-win64/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*", "--start-maximized");
        webDriver = new ChromeDriver(options);
    }

    @AfterAll
    public static void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

    @Test
    void testLicenceAndPolicyLinks() throws InterruptedException {
        webDriver.get("https://en.wikipedia.org/wiki/Sarajevo");
        Thread.sleep(2000);
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        WebElement footer = webDriver.findElement(By.xpath("//*[@id='footer-info-copyright']"));
        js.executeScript("arguments[0].scrollIntoView(true);", footer);
        Thread.sleep(2000);
        String expectedText = "Text is available under the Creative Commons Attribution-ShareAlike 4.0 License; additional terms may apply. By using this site, you agree to the Terms of Use and Privacy Policy. Wikipedia® is a registered trademark of the Wikimedia Foundation, Inc., a non-profit organization.";
        String actualText = footer.getText();
        assertTrue(actualText.contains("Creative Commons Attribution-ShareAlike 4.0 License"), "Tekst licence se ne podudara!");
        webDriver.findElement(By.xpath("//*[@id=\"footer-info-copyright\"]/a[2]")).click();
        Thread.sleep(2000);
        assertTrue(webDriver.getTitle().contains("Terms of Use"));
        webDriver.navigate().back();
        Thread.sleep(2000);
        js.executeScript("arguments[0].scrollIntoView(true);", webDriver.findElement(By.xpath("//*[@id='footer-info-copyright']")));
        webDriver.findElement(By.xpath("//*[@id=\"footer-info-copyright\"]/a[3]")).click();
        Thread.sleep(2000);
        assertTrue(webDriver.getTitle().contains("Privacy Policy"));
    }
}