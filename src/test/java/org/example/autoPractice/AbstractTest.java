package org.example.autoPractice;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public abstract class AbstractTest {

    static WebDriver webDriver;
    static WebDriverWait wait;

    @BeforeAll
    static void SetDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options=new ChromeOptions();
        options.addArguments ("start-maximized"); // открытие полноэкранного формата
        options.addArguments ("--incognito");// открытие страницы в режиме инкогнито
        options.addArguments ("version"); //
        options.addArguments ("disable-popup-blocking"); //блокировка всплывающих окон
        webDriver =new ChromeDriver(options);
        wait = new WebDriverWait(webDriver,Duration.ofSeconds(10));
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20)); //не явное ожидание появления элемента на странице
    }

    @BeforeEach
    void initMainPage () {
        Assertions.assertDoesNotThrow(()->webDriver.navigate().to("http://automationpractice.com/index.php"),"Страница не найдена"); //открыли страницу
        new MainPage(getWebDriver()).submitSignInClick();
    }

    @AfterEach
    void deleteAllCookies(){
        webDriver.manage().deleteAllCookies();
    }

    @AfterAll
    public static void close(){
        webDriver.close();
    }
    WebDriver getWebDriver() {return this.webDriver;}
}
