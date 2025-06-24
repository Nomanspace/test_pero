package com.example.demo;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;
import org.openqa.selenium.chrome.ChromeOptions;


public abstract class BaseStructure {
    @BeforeAll
    public static void setUP() {
        Configuration.browserCapabilities = new ChromeOptions().addArguments("--remote-allow-origins=*");
        Configuration.browserSize = "1280x800";
        Configuration.headless = false;
    }
}
