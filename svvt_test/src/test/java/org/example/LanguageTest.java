package org.example;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LanguageTest {

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
    void LanguageSwitchTest() throws InterruptedException {
        webDriver.get("https://en.wikipedia.org/wiki/Sarajevo");
        Thread.sleep(2000);
        String engVersion = webDriver.findElement(By.xpath("//*[@id='mw-content-text']/div[2]/p[2]")).getText();
        webDriver.findElement(By.xpath("//*[@id='p-lang-btn-checkbox']")).click();
        Thread.sleep(1500);
        WebElement langSearch = webDriver.findElement(By.xpath("//*[@id='search']/div/div/input[2]"));
        langSearch.sendKeys("bosanski");
        Thread.sleep(1000);
        webDriver.findElement(By.xpath("/html/body/div[1]/div[2]/div[9]/div/ul/li/a")).click();
        Thread.sleep(2000);
        String bosVerse = webDriver.findElement(By.xpath("//*[@id='mwHg']")).getText();
        assertNotEquals(engVersion, bosVerse);
        assertTrue(webDriver.getCurrentUrl().contains("bs.wikipedia.org"));
        webDriver.findElement(By.xpath("//*[@id=\"p-lang-btn-checkbox\"]")).click();
        Thread.sleep(5000);
        webDriver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div/ul[1]/li[2]/a")).click();
        Thread.sleep(2000);
    }
}
