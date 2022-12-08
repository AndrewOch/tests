package com.example.tests;

import com.example.AppManager;
import com.example.Settings;
import com.example.models.AccountData;
import org.junit.jupiter.api.BeforeAll;

public class AuthBase extends TestBase {
    public static AppManager app = AppManager.getInstance();

    @BeforeAll
    public static void setUp() {
        Settings settings = Settings.getInstance();
        AccountData user = new AccountData(settings.getLogin(), settings.getPassword());
        app = AppManager.getInstance();
        app.login.enterLogin(user);
        app.login.enterPassword(user);
    }
}
