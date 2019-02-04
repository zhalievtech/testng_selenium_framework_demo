package com.weborders.tests.functional_tests.order;

import com.weborders.utilities.BrowserUtils;
import com.weborders.utilities.ConfigurationReader;
import com.weborders.utilities.TestBase;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;

public class checkPrice extends TestBase {

    @Test (groups = "regression")
    public void unitPriceTest() throws InterruptedException {
        extentLogger = report.createTest("Unit price test");
        extentLogger.info("Logging in to the application");

        pages.login().login(ConfigurationReader.getProperty("username"),
                ConfigurationReader.getProperty("password"));
        pages.viewAllOrders().viewAllProductsLink.click();

        List<String> allProducts = BrowserUtils.getElementsText(pages.viewAllProducts().allProducts);


        for (String product : allProducts) {
            extentLogger.info("Testing unit price for: " + product);
            pages.viewAllOrders().viewAllProductsLink.click();
            String expectedPrice = pages.viewAllProducts().getPrice(product).getText();

            pages.viewAllOrders().orderLink.click();

            pages.order().productList().selectByVisibleText(product);
            String actualPrice = "$" + pages.order().pricePerUnit.getAttribute("value");

            Assert.assertEquals(actualPrice, expectedPrice, "Unit price did not match");
        }
        extentLogger.pass("Passed: Unit price test");
    }


    @Test
    public void calculateTest() throws InterruptedException {
        extentLogger = report.createTest("Calculate total test");
        extentLogger.info("Logging in to the application");

        pages.login().login(ConfigurationReader.getProperty("username"),
                ConfigurationReader.getProperty("password"));
        pages.viewAllOrders().viewAllProductsLink.click();
        List<String> allProducts = BrowserUtils.getElementsText(pages.viewAllProducts().allProducts);


        for (String product : allProducts) {
            extentLogger.info("Testing total price for: " + product);

            pages.viewAllOrders().viewAllProductsLink.click();
            String discountString = pages.viewAllProducts().getDiscount(product).getText().replace("%", "");
            Double discount = Double.parseDouble(discountString) / 100;
            String unitPriceString = pages.viewAllProducts().getPrice(product).getText().replace("$", "");
            Double unitPrice = Double.parseDouble(unitPriceString);

            pages.viewAllOrders().orderLink.click();
            pages.order().productList().selectByVisibleText(product);
            pages.order().quantity.clear();
            int quantity = new Random().nextInt(100) + 1;
            pages.order().quantity.sendKeys(Keys.BACK_SPACE + "" + quantity);
            pages.order().calculate.click();

            Double expectedTotal = unitPrice * quantity;
            ;
            if (quantity > 9) {
                expectedTotal = expectedTotal - (expectedTotal * discount);
            }

            String actualTotal = pages.order().total.getAttribute("value");
            Assert.assertEquals(actualTotal, expectedTotal.toString().replace(".0", ""), "Total price did not match");
            extentLogger.pass("Passed: Calculate total test");

        }
    }
}
