package com.basepages;

import com.driver.DriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.HashMap;

public class BaseTest {


    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        DriverManager.setDriver(new ChromeDriver(getChromeOptions()));
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quit();
    }

    public ChromeOptions getChromeOptions(){
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("credentials_enable_service", false);
        chromePrefs.put("profile.password_manager_enabled", false);

        ChromeOptions chromeUp = new ChromeOptions();
        chromeUp.setExperimentalOption("prefs", chromePrefs);
        chromeUp.addArguments( "--start-fullscreen");
        return chromeUp;
    }
}
