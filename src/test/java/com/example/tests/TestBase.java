package com.example.tests;

import com.example.AppManager;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {
    public static AppManager app = AppManager.getInstance();

    @BeforeAll
    public static void setUp() {
        app = AppManager.getInstance();
    }
}
