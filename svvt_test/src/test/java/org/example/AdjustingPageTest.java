package org.example;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class AdjustingPageTest {

    private static WebDriver webDriver;
    private static final String TARGET_URL = "https://en.wikipedia.org/wiki/Selenium_(software)";

    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver","D:/baze/webdr/chromedriver-win64/chromedriver.exe");
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
    void testAdjustAppearance() throws InterruptedException {
        webDriver.get(TARGET_URL);
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//*[@id='skin-client-pref-vector-feature-custom-font-size-value-0']")).click();
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//*[@id='skin-client-pref-vector-feature-custom-font-size-value-1']")).click();
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//*[@id='skin-client-pref-vector-feature-custom-font-size-value-2']")).click();
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//*[@id='skin-client-pref-vector-feature-limited-width-value-1']")).click();
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//*[@id='skin-client-pref-vector-feature-limited-width-value-0']")).click();
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//*[@id='skin-client-pref-skin-theme-value-day']")).click();
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//*[@id='skin-client-pref-skin-theme-value-night']")).click();
        Thread.sleep(2000);
    }
}

