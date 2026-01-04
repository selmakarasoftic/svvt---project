package org.example;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class GalleryTest {

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
    void testSarajevoGallery() throws InterruptedException {
        webDriver.get("https://en.wikipedia.org/wiki/Sarajevo");
        Thread.sleep(2000);
        String startUrl = webDriver.getCurrentUrl();
        System.out.println("Starting URL: " + startUrl);
        webDriver.findElement(By.xpath("//*[@id=\"mw-content-text\"]/div[2]/table[1]/tbody/tr[4]/td/div/div/div[1]/div/div[1]/span/a/img")).click();
        Thread.sleep(2000);
        WebElement body = webDriver.findElement(By.tagName("body"));
        body.sendKeys(Keys.ARROW_RIGHT);
        Thread.sleep(1000);
        body.sendKeys(Keys.ARROW_RIGHT);
        Thread.sleep(1000);
        body.sendKeys(Keys.ARROW_RIGHT);
        Thread.sleep(1000);
        body.sendKeys(Keys.ARROW_RIGHT);
        Thread.sleep(1500);
        body.sendKeys(Keys.ARROW_LEFT);
        Thread.sleep(1500);
        webDriver.findElement(By.xpath("/html/body/div[9]/div/div[1]/button[3]")).click();
        Thread.sleep(1500);
        webDriver.findElement(By.xpath("/html/body/div[9]/div/div[1]/button[4]")).click();
        Thread.sleep(1500);
        String galleryUrl = webDriver.getCurrentUrl();
        assertNotEquals(startUrl, galleryUrl, "The URL should change when the gallery is active.");
        webDriver.findElement(By.xpath("/html/body/div[9]/div/div[1]/a[2]")).click();
        Thread.sleep(1000);
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//button[@title='Select and copy (if supported) the link for sharing this file']")).click();
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//button[@title='Close this tool (Esc)']")).click();

    }
}