package com.example;

import com.example.helpers.MarksHelper;
import com.example.helpers.LoginHelper;
import com.example.helpers.NavigationHelper;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

import static org.junit.Assert.fail;

public class AppManager {

    public WebDriver driver;
    public String baseUrl;
    public boolean acceptNextAlert = true;
    public StringBuffer verificationErrors = new StringBuffer();
    public JavascriptExecutor js;

    public NavigationHelper navigation;
    public LoginHelper login;
    public MarksHelper marks;

    private static final ThreadLocal<AppManager> app = new ThreadLocal<>();

    private Settings settings;

    public static AppManager getInstance() {
        if (app.get() == null) {
            AppManager newInstance = new AppManager();
            newInstance.navigation.openHomePage();
            app.set(newInstance);
        }
        return app.get();
    }

    private AppManager() {
        System.setProperty("webdriver.chrome.driver", "/Users/mac/Documents/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.setBinary("/Applications/Yandex.app/Contents/MacOS/Yandex");
        driver = new ChromeDriver(options);
        settings = Settings.getInstance();
        baseUrl = settings.getBaseURL();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        js = (JavascriptExecutor) driver;

        navigation = new NavigationHelper(this);
        login = new LoginHelper(this);
        marks = new MarksHelper(this);
    }

    @Override
    public void finalize() throws Throwable {
        super.finalize();
        try {
            driver.quit();
        } catch (Exception ignored) {
        }
    }

    public void stop() {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }
}
