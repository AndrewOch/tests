package com.example.helpers;

import com.example.AppManager;
import com.example.models.AccountData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginHelper extends HelperBase {

    private boolean loggedIn = false;
    private String loggedInUsername;

    public LoginHelper(AppManager app) {
        super(app);
    }

    public void enterLogin(AccountData accountData) {
        if (isLoggedIn())
        {
            if (isLoggedIn(accountData.username))
            {
                return;
            }
            logout();
        }
        driver.findElement(By.linkText("Войти")).click();
        driver.get("https://www.evernote.com/Login.action");
        WebElement username = driver.findElement(By.id("username"));
        username.clear();
        for (char character: accountData.getUsername().toCharArray()) {
            username.sendKeys(String.valueOf(character));
        }
        driver.findElement(By.id("loginButton")).click();
    }

    public void enterPassword(AccountData accountData) {
        WebElement password = driver.findElement(By.id("password"));
        password.clear();
        password.sendKeys(accountData.getPassword());
//        driver.findElement(By.id("loginButton")).click();
        loggedIn = true;
        loggedInUsername = accountData.getUsername();
    }

    private void logout() {
        driver.get("https://www.evernote.com/client/web?login=true#?hm=true&");
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='vigvamcev.media@gmail.com'])[1]/following::*[name()='svg'][1]")).click();
        driver.findElement(By.xpath("//a[@id='qa-ACCOUNT_DROPDOWN_LOGOUT']/div/span/span")).click();
        driver.get("https://www.evernote.com/Logout.action?currentOnly=true");
        driver.get("https://www.evernote.com/AnalyticsPitStop.action?analyticsEventDataJson=%7B%22events%22%3A%5B%7B%22identityId%22%3A0%2C%22action%22%3A%22login_action%22%2C%22exportToDatabase%22%3Afalse%2C%22label%22%3A%22log_out%22%2C%22category%22%3A%22account%22%7D%5D%7D&targetUrl=https%3A%2F%2Fevernote.com%2Flogged-out%2F%3Flogout%26uid%3D237928662");
        driver.get("https://evernote.com/intl/ru/logged-out?logout=&uid=237928662");
    }

    public AccountData getUserData() {
        driver.get("https://www.evernote.com/Settings.action?cfc=true");

        WebElement textContent = driver.findElement(By.xpath("//*[@id=\"container\"]/div[1]/div[2]/div[8]/div"));
        String email = textContent.getText();
        return new AccountData(email, "");
    }

    public boolean isLoggedIn()
    {
        return loggedIn;
    }

    public boolean isLoggedIn(String username)
    {
        return loggedInUsername.equals(username);
    }

    public boolean gotResponseMessage() {
       return driver.findElement(By.id("responseMessage")).getText() != null;
    }
}
