package org.example;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NavigationTest {

    private static WebDriver webDriver;

    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "D:/baze/webdr/chromedriver-win64/chromedriver.exe");
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
    void testSarajevoNavigation() throws InterruptedException {
        webDriver.get("https://en.wikipedia.org/wiki/Sarajevo");
        Thread.sleep(2000);
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("window.scrollBy(0, 600);");
        Thread.sleep(2000);
        js.executeScript("window.scrollBy(0, -400);");
        Thread.sleep(1000);
        webDriver.findElement(By.xpath("//*[@id=\"mw-content-text\"]/div[2]/p[2]/a[4]")).click();
        Thread.sleep(2000);
        String countryHeader = webDriver.findElement(By.xpath("//*[@id=\"firstHeading\"]/span")).getText();
        assertEquals("Bosnia and Herzegovina", countryHeader);
        webDriver.navigate().back();
        Thread.sleep(2000);
        assertTrue(webDriver.getTitle().contains("Sarajevo"));
        WebElement categoryLink = webDriver.findElement(By.xpath("//*[@id='mw-normal-catlinks']/ul/li[1]/a"));
        js.executeScript("arguments[0].scrollIntoView(true);", categoryLink);
        Thread.sleep(1000);
        categoryLink.click();
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//a[@class='mw-logo']")).click();
        Thread.sleep(2000);
        assertEquals("https://en.wikipedia.org/wiki/Main_Page", webDriver.getCurrentUrl());
    }
}

