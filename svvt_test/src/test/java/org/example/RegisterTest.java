package org.example;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RegisterTest {

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
    @Order(1)
    void RegistrationFormTest() throws InterruptedException {
        webDriver.get("https://en.wikipedia.org/wiki/Main_Page");
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//*[@id=\"pt-createaccount-2\"]/a")).click();
        Thread.sleep(2000);
        WebElement usernameField = webDriver.findElement(By.xpath("//*[@id=\"wpName2\"]"));
        usernameField.sendKeys("username");
        Thread.sleep(2000);
        WebElement inUseMessage = webDriver.findElement(By.xpath("//*[@id=\"userlogin2\"]/div[1]/div[2]/div/div/div/div/div"));
        assertTrue(inUseMessage.getText().contains("Username entered already in use"));
        Thread.sleep(2000);
    }
    @Test
    @Order(2)
    void AccountCreationTest() throws InterruptedException {
        WebElement usernameField = webDriver.findElement(By.xpath("//*[@id=\"wpName2\"]"));
        usernameField.clear();
        String targetUsername = "UsernameSvvtTestUser5";
        usernameField.sendKeys(targetUsername);
        String password = props.getProperty("wiki.password");
        webDriver.findElement(By.xpath("//*[@id=\"wpPassword2\"]")).sendKeys(password);
        webDriver.findElement(By.xpath("//*[@id=\"wpRetype\"]")).sendKeys(password);
        webDriver.findElement(By.xpath("//*[@id=\"wpCreateaccount\"]")).click();
        Thread.sleep(16000);
        WebElement userSpan = webDriver.findElement(By.xpath("//*[@id=\"pt-userpage-2\"]/a/span"));
        assertEquals(targetUsername, userSpan.getText());
    }
    @Test
    @Order(3)
    void testPasswordResetMissingEmailValidation() throws InterruptedException {
        webDriver.get("https://en.wikipedia.org/w/index.php?title=Special:PasswordReset");
        Thread.sleep(2000);
        WebElement usernameField = webDriver.findElement(By.xpath("//*[@id=\"ooui-php-2\"]"));
        usernameField.sendKeys("UsernameSvvtTestUser5");
        webDriver.findElement(By.xpath("//*[@id=\"ooui-php-6\"]/button")).click();
        Thread.sleep(2000);
        String pageContent = webDriver.findElement(By.tagName("body")).getText();
        assertTrue(pageContent.contains("Email address must be entered"));
    }
}

