package org.example;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpecificDayArticlesTest {

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
    void testSpecificDayNavigation() throws InterruptedException {
        webDriver.get("https://en.wikipedia.org/wiki/Main_Page");
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//*[@id=\"mp-otd\"]/div[4]/ul/li[3]/b/a")).click();
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//*[@id=\"mw-content-text\"]/div[2]/table/tbody/tr[6]/td[11]/a")).click();
        Thread.sleep(2000);
        WebElement headingSpan = webDriver.findElement(By.xpath("//*[@id=\"firstHeading\"]/span"));
        String actualHeading = headingSpan.getText();
        assertEquals("June 11", actualHeading, "The page heading does not match the expected date!");
    }
}