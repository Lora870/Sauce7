import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class DoubleTest {

    // 1. Открыть браузер
    // 2. зайдем на нужный нам сайт

    @Test
    public void FieldFormError() throws InterruptedException {
        WebDriver browser = new ChromeDriver();
        browser.get("https://www.saucedemo.com/");
        browser.findElement(By.cssSelector("[id='user-name']")).sendKeys("locked_out_user");
        Thread.sleep(1000);
        browser.findElement(By.cssSelector("[id='password']")).sendKeys("secret_sauce");
        Thread.sleep(1000);
        browser.findElement(By.cssSelector("[name='login-button']")).click();

        boolean isVisible = browser.findElement(By.xpath("//h3[@data-test='error']")).isDisplayed();
        String errorText = browser.findElement(By.xpath("//h3[@data-test='error']")).getText();

        assertTrue(isVisible, "Error message does not appear");
        assertEquals(errorText, "Epic sadface: Sorry, this user has been locked out.");

        //    browser.quit();
    }
}
