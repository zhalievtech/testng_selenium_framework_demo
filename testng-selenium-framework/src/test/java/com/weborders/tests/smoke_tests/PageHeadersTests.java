package com.weborders.tests.smoke_tests;

import com.weborders.utilities.ApplicationConstants;
import com.weborders.utilities.ConfigurationReader;
import com.weborders.utilities.TestBase;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class PageHeadersTests extends TestBase {

    @Test
    public void pageHeadersTest(){
        // create a new test / test case in the report and give name : Page headers test
        extentLogger = report.createTest("Page headers test");

        // use logger to log the steps
        extentLogger.info("Logging to the application");
        pages.login().login(ConfigurationReader.getProperty("username"),
                ConfigurationReader.getProperty("password"));

        extentLogger.info("Verifying header for all orders page");
        assertEquals(pages.viewAllOrders().header.getText(), ApplicationConstants.LIST_OF_ALL_ORDERS);

        extentLogger.info("Clicking on view all products link");
        pages.viewAllOrders().viewAllProductsLink.click();

        extentLogger.info("Verifying header for all products page");
        assertEquals(pages.viewAllProducts().header.getText(), ApplicationConstants.LIST_OF_ALL_PRODUCTS);

        extentLogger.info("Clicking on order link");
        pages.viewAllProducts().orderLink.click();

        extentLogger.info("Verifying header for order page");
        assertEquals(pages.order().header.getText(), ApplicationConstants.ORDER);
        // log the pass
        extentLogger.pass("Page headers test");

    }

}
