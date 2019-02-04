package com.weborders.tests.functional_tests.login;

import com.weborders.utilities.ApplicationConstants;
import com.weborders.utilities.BrowserUtils;
import com.weborders.utilities.ConfigurationReader;
import com.weborders.utilities.TestBase;
import org.openqa.selenium.By;
import org.testng.annotations.*;

import static org.testng.Assert.*;

public class PositiveLoginTests extends TestBase {

    @Test()
    public void positiveLoginTest() {
        extentLogger = report.createTest("Positive login test");
        extentLogger.info("Verifying login page title");

        assertEquals(driver.getTitle(), ApplicationConstants.LOGIN_PAGE_TITLE);
        extentLogger.info("Login to application");
        pages.login().login(ConfigurationReader.getProperty("username"),
                ConfigurationReader.getProperty("password"));

        extentLogger.info("Verifying url");
        assertEquals(driver.getTitle(),
                ApplicationConstants.APPLICATION_TITLE);
        assertEquals(driver.getCurrentUrl(),
                "http://secure.smartbearsoftware.com/samples/testcomplete12/weborders/");
        fail();
        extentLogger.pass("Passed: Cart Icon Delete Test");

    }

}
