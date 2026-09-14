package tests;

import org.testng.annotations.Test;
import pages.LoginPage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest {
    // 1. Открыть браузер
    // 2. зайдем на нужный нам сайт

    @Test
    public void lockedUserTest() {
        loginPage.open();
        loginPage.login("locked_out_user", "secret_sauce");

        boolean isVisible = loginPage.isErrorVisible();
        String errorText = loginPage.getErrorText();

        assertTrue(isVisible, "Error message does not appear");
        assertEquals(errorText, "Epic sadface: Sorry, this user has been locked out.", "Error text does");
    }

    @Test
    public void wrongUserTest() {
        loginPage.open();
        loginPage.login("Standard_user", "secret_sauce");

        boolean isVisible = loginPage.isErrorVisible();
        String errorText = loginPage.getErrorText();

        assertTrue(isVisible, "Error message does not appear");
        assertEquals(errorText, "Epic sadface: Username and password do not match any user in this service", "Error text does");
    }

    @Test
    public void correctUserTest() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        boolean pageTitleVisible = productsPage.isPageTitleVisible();
        assertTrue(pageTitleVisible);
        assertEquals(productsPage.getPageTitle(), "Products");
    }

    @Test
    public void EmptyUserLoginTest() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");

        driver.findElement(By.cssSelector("[id='user-name']")).sendKeys("");
        driver.findElement(By.cssSelector("[id='password']")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("[name='login-button']")).click();

        boolean isVisible = driver.findElement(By.xpath("//h3[@data-test='error']")).isDisplayed();
        String errorText = driver.findElement(By.xpath("//h3[@data-test='error']")).getText();

        assertTrue(isVisible, "Error message does not appear");
        assertEquals(errorText, "Epic sadface: Username is required");
    }

    @Test
    public void EmptyUserPasswordTest() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");

        driver.findElement(By.cssSelector("[id='user-name']")).sendKeys("standard_user");
        driver.findElement(By.cssSelector("[id='password']")).sendKeys("");
        driver.findElement(By.cssSelector("[name='login-button']")).click();

        boolean isVisible = driver.findElement(By.xpath("//h3[@data-test='error']")).isDisplayed();
        String errorText = driver.findElement(By.xpath("//h3[@data-test='error']")).getText();

        assertTrue(isVisible, "Error message does not appear");
        assertEquals(errorText, "Epic sadface: Password is required");
    }

    @Test
    public void CapslockUserLoginTest() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");

        driver.findElement(By.cssSelector("[id='user-name']")).sendKeys("Standard_user");
        driver.findElement(By.cssSelector("[id='password']")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("[name='login-button']")).click();

        boolean isVisible = driver.findElement(By.xpath("//h3[@data-test='error']")).isDisplayed();
        String errorText = driver.findElement(By.xpath("//h3[@data-test='error']")).getText();

        assertTrue(isVisible, "Error message does not appear");
        assertEquals(errorText, "Epic sadface: Username and password do not match any user in this service");
    }

//    @Test
//    public void zipCode4Digits() throws InterruptedException {
//        WebDriver browser = new ChromeDriver();
//        browser.get("https://www.sharelane.com/cgi-bin/register.py");
//        browser.findElement(By.cssSelector("[name='zip_code']")).sendKeys("12346");
//        Thread.sleep(2000);
//        browser.findElement(By.cssSelector("[name='zip_code']")).sendKeys(Keys.CONTROL + "A");
//        browser.findElement(By.cssSelector("[name='zip_code']")).sendKeys(Keys.BACK_SPACE);
//        browser.findElement(By.cssSelector("[name='zip_code']")).sendKeys("123");
//        browser.findElement(By.cssSelector("[value='Continue']")).click();
//
//        boolean pageTitleVisible = browser.findElement(By.cssSelector("[data-test='title']")).isDisplayed();
//        String errorText = browser.findElement(By.cssSelector(".error_message")).getText();
//
//        //boolean isVisible = browser.findElement(By.cssSelector(".error_message")).isDisplayed();
//        //String errorText = browser.findElement(By.cssSelector(".error_message")).getText();
//
//        assertFalse(isVisible, "Error message does not appear");
//        assertEquals(errorText, "Oops, error on page. ZIP code should have 5 digits");
//
////        browser.findElement(By.xpath("//*[@name='zip_code']"));
////        browser.findElement(By.name("zip_code"));
//
//
//        //    browser.quit();
//    }
}

// Домашнее задание