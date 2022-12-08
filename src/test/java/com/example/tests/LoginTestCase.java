package com.example.tests;

import com.example.Settings;
import com.example.models.AccountData;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class LoginTestCase extends TestBase{

    private final AccountData userValid = new AccountData(Settings.getInstance().getLogin(), Settings.getInstance().getPassword());
    private final AccountData userInvalid = new AccountData("wdjfbvwdcb@mail.ru", "webckweasviweb");

    @Test
    public void testLoginValid() {
        app.navigation.openHomePage();
        app.login.enterLogin(userValid);
        app.login.enterPassword(userValid);
        AccountData userData = app.login.getUserData();
        System.out.println("Expected: |"+userData.username +"|");
        System.out.println("Got: |"+ userValid.username +"|");
        Assertions.assertTrue(userData.username.contains(userValid.username));
    }

    @Test
    public void testLoginInvalid() {
        app.navigation.openHomePage();
        app.login.enterLogin(userInvalid);
        boolean gotResponseMessage = app.login.gotResponseMessage();
        Assertions.assertTrue(gotResponseMessage);
    }
}
