package org.example;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HistoryTest {

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
    void testSarajevoHistory() throws InterruptedException {
        webDriver.get("https://en.wikipedia.org/wiki/Sarajevo");
        Thread.sleep(2000);
        String latestString = webDriver.findElement(By.xpath("//*[@id='footer-info-lastmod']")).getText();
        webDriver.findElement(By.xpath("//*[@id='ca-history']/a")).click();
        Thread.sleep(2000);
        String historyHeader = webDriver.findElement(By.xpath("//*[@id='firstHeading']")).getText();
        assertEquals("Sarajevo: Revision history", historyHeader);
        webDriver.findElement(By.xpath("//*[@id='mw-oldid-1314082251']")).click();
        webDriver.findElement(By.xpath("//*[@id='mw-history-compare']/div/input")).click();
        Thread.sleep(2000);
        String compareHeader = webDriver.findElement(By.xpath("//*[@id='firstHeading']")).getText();
        assertEquals("Sarajevo: Difference between revisions", compareHeader);
        webDriver.findElement(By.xpath("//*[@id='ca-history']/a")).click();
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//*[@id='pagehistory']/ul[14]/li/bdi/bdi/a")).click();
        Thread.sleep(2000);
        String oldVersionWarning = webDriver.findElement(By.xpath("//*[@id='mw-revision-info']/b")).getText();
        assertTrue(oldVersionWarning.contains("This is an old revision"));
        String revisionString = webDriver.findElement(By.xpath("//*[@id='footer-info-lastmod']")).getText();
        assertNotEquals(latestString, revisionString);
    }
}

