package org.example;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class SearchTest {

    private static WebDriver webDriver;
    private static final String WIKIPEDIA_URL = "https://en.wikipedia.org/wiki/Main_Page";

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
    void testWikipediaSearch() throws InterruptedException {
        webDriver.get(WIKIPEDIA_URL);
        Thread.sleep(1500);
        WebElement searchInput = webDriver.findElement(By.id("searchInput"));
        String searchTerm = "Software verification and validation";
        searchInput.sendKeys(searchTerm);
        searchInput.sendKeys(Keys.ENTER);
        Thread.sleep(3000);
        WebElement mainHeader = webDriver.findElement(By.xpath("//*[@id='firstHeading']/span"));
        String pageTitle = webDriver.getTitle();
        assertTrue(pageTitle.contains(searchTerm));
        assertTrue(mainHeader.getText().startsWith(searchTerm));
    }
}
