package org.example;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProtectedTest {

    private static WebDriver webDriver;
    private static final String WIKIPEDIA_URL = "https://en.wikipedia.org/wiki/Main_Page";

    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver","C:/Users/Omar/Downloads/chromedriver-win64/chromedriver-win64/chromedriver.exe");

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
    void testProtectedPageWarning() throws InterruptedException {
        webDriver.get("https://en.wikipedia.org/wiki/George_W._Bush");
        webDriver.manage().window().maximize();
        Thread.sleep(1000);
        String iconTitle = webDriver.findElement(By.xpath("//*[@id='mw-indicator-pp-default']/div/span/a")).getAttribute("title");
        assertEquals("This article is semi-protected to promote compliance with the policy on biographies of living persons", iconTitle);
        webDriver.findElement(By.xpath("//*[@id=\"mw-indicator-pp-default\"]/div/span/a")).click();
        Thread.sleep(2000);
        String text = webDriver.findElement(By.xpath("//*[@id=\"Semi-protection\"]")).getText();
        assertTrue(text.contains("Semi-protection"), "The text should confirm we are on the Semi-protection information section.");
    }
}