package org.example;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MenuTest {

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
    void TestMenu() throws InterruptedException {
        webDriver.get(WIKIPEDIA_URL);
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//*[@id=\"vector-main-menu-dropdown-checkbox\"]")).click();
        Thread.sleep(1500);
        WebElement currentEventsLink = webDriver.findElement(By.xpath("//*[@id='n-currentevents']/a"));
        currentEventsLink.click();
        Thread.sleep(2000);
        String headerText = webDriver.findElement(By.xpath("//*[@id=\"firstHeading\"]/span[3]")).getText();
        assertTrue(headerText.contains("Current events"));
        String pageTitle = webDriver.getTitle();
        assertTrue(pageTitle.contains("Current events"));
    }
}