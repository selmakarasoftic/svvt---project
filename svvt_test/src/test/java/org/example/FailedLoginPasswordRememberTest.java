package org.example;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FailedLoginPasswordRememberTest {

    private static WebDriver webDriver;
    String username="WrongUsername";

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
    @Order(1)
    void RememberMeOptionTest() throws InterruptedException {
        webDriver.get("https://en.wikipedia.org/w/index.php?title=Special:UserLogin");
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//*[@id=\"wpRemember\"]")).click();
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//*[@id=\"wpRemember\"]")).click();
    }
    @Test
    @Order(2)
    void FailedLoginTest() throws InterruptedException {
        webDriver.get("https://en.wikipedia.org/w/index.php?title=Special:UserLogin");
        Thread.sleep(8000);
        webDriver.findElement(By.xpath("//*[@id=\"wpName1\"]")).sendKeys(username);
        webDriver.findElement(By.xpath("//*[@id=\"wpPassword1\"]")).sendKeys("password");
        webDriver.findElement(By.xpath("//*[@id=\"wpLoginAttempt\"]")).click();
        Thread.sleep(2000);
        WebElement errorMessage = webDriver.findElement(By.xpath("//*[@id=\"userloginForm\"]/form/div[1]/div"));
        String errorText = errorMessage.getText();
        assertTrue(errorText.contains("Incorrect username or password entered"));
        Thread.sleep(2000);
        Thread.sleep(2000);
    }
    @Test
    @Order(3)
    void PasswordResetTest() throws InterruptedException
    {
        webDriver.get("https://en.wikipedia.org/w/index.php?title=Special:UserLogin");
        Thread.sleep(8000);
        webDriver.findElement(By.xpath("//*[@id=\"userloginForm\"]/form/div[6]/div/a")).click();
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//*[@id=\"ooui-php-2\"]")).sendKeys(username);
        webDriver.findElement(By.xpath("//*[@id=\"ooui-php-6\"]/button")).click();
        Thread.sleep(2000);
        String resetMessage = webDriver.findElement(By.xpath("//*[@id=\"mw-content-text\"]/p[1]")).getText();
        assertTrue(resetMessage.contains("You have requested a password reset"), "Password reset message not found!");
        String usernameCheck = webDriver.findElement(By.xpath("//*[@id=\"mw-content-text\"]/ul/li")).getText();
        assertTrue(usernameCheck.contains(username));
    }
}