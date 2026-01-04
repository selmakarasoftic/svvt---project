package org.example;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TableSortTest {

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
    void testTechnologyTableSorting() throws InterruptedException {
        webDriver.get("https://en.wikipedia.org/wiki/List_of_largest_technology_companies_by_revenue");
        Thread.sleep(2000);

        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("window.scrollBy(0, 600);");
        Thread.sleep(1500);
        WebElement firstCompanyCell = webDriver.findElement(By.xpath("//*[@id='mw-content-text']/div[2]/table[2]/tbody/tr[1]/td[1]"));
        String companyBeforeSort = firstCompanyCell.getText();
        System.out.println("Company before sort: " + companyBeforeSort);
        webDriver.findElement(By.xpath("//*[@id='mw-content-text']/div[2]/table[2]/thead/tr/th[1]")).click();
        Thread.sleep(1500);
        String companyAfterSort = webDriver.findElement(By.xpath("//*[@id='mw-content-text']/div[2]/table[2]/tbody/tr[1]/td[1]")).getText();
        System.out.println("Company after sort: " + companyAfterSort);
        assertNotEquals(companyBeforeSort, companyAfterSort, "The first company should change after sorting alphabetically.");
        webDriver.findElement(By.xpath("//*[@id='mw-content-text']/div[2]/table[2]/thead/tr/th[2]")).click();
        Thread.sleep(1000);
        webDriver.findElement(By.xpath("//*[@id='mw-content-text']/div[2]/table[2]/thead/tr/th[3]")).click();
        Thread.sleep(1000);
        webDriver.findElement(By.xpath("//*[@id='mw-content-text']/div[2]/table[2]/thead/tr/th[4]")).click();
        Thread.sleep(1000);
        webDriver.findElement(By.xpath("//*[@id='mw-content-text']/div[2]/table[2]/thead/tr/th[5]")).click();
        Thread.sleep(1000);
        webDriver.findElement(By.xpath("//*[@id='mw-content-text']/div[2]/table[2]/thead/tr/th[6]")).click();
        Thread.sleep(1000);

    }
}