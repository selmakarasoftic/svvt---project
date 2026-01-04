package org.example;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class SuccessfulLoginLogoutTest {

    private static WebDriver webDriver;
    private static Properties props;

    @BeforeAll
    public static void setUp() {
        props = new Properties();
        try (FileInputStream fis = new FileInputStream("config.properties")) {
            props.load(fis);
        } catch (IOException e) {
            System.err.println("Error: config.properties file not found!");
        }
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
    void LoginTest() throws InterruptedException {
        webDriver.get("https://en.wikipedia.org/wiki/Main_Page");
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//*[@id=\"pt-login-2\"]/a")).click();
        Thread.sleep(2000);
        String savedUsername = props.getProperty("wiki.username");
        String savedPassword = props.getProperty("wiki.password");
        webDriver.findElement(By.xpath("//*[@id=\"wpName1\"]")).sendKeys(savedUsername);
        webDriver.findElement(By.xpath("//*[@id=\"wpPassword1\"]")).sendKeys(savedPassword);
        webDriver.findElement(By.xpath("//*[@id=\"wpLoginAttempt\"]")).click();
        Thread.sleep(4000);
        WebElement userPageSpan = webDriver.findElement(By.xpath("//*[@id=\"pt-userpage-2\"]/a/span"));
        assertEquals(savedUsername, userPageSpan.getText(), "Login failed: Username does not match!");
    }
    @Test
    void LogoutTest() throws InterruptedException {
        String initialUrl = webDriver.getCurrentUrl();
        webDriver.findElement(By.xpath("//*[@id=\"vector-user-links-dropdown-checkbox\"]")).click();
        Thread.sleep(1000);
        webDriver.findElement(By.xpath("//*[@id=\"pt-logout\"]/a")).click();
        Thread.sleep(3000);
        assertNotEquals(initialUrl, webDriver.getCurrentUrl());
        String headingText = webDriver.findElement(By.xpath("//*[@id=\"firstHeading\"]")).getText();
        assertEquals("Log out", headingText);
        String logoutMessage = webDriver.findElement(By.xpath("//*[@id=\"mw-content-text\"]/p[1]")).getText();
        assertTrue(logoutMessage.contains("You are now logged out"));
        WebElement loginLink = webDriver.findElement(By.xpath("//*[@id=\"pt-login-2\"]/a"));
        assertTrue(loginLink.isDisplayed());
    }
}

