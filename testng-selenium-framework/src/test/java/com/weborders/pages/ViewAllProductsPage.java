package com.weborders.pages;

import com.weborders.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ViewAllProductsPage extends Menu{
    public ViewAllProductsPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//table[@class='ProductsTable']//td[1]")
    public List<WebElement> allProducts;

    public WebElement getPrice(String product) {
        String xpath = "//table//td[.='" +product+"']/../td[2]";
        return Driver.getDriver().findElement(By.xpath(xpath));
    }

    public WebElement getDiscount(String product) {
        String xpath = "//table//td[.='" +product+"']/../td[3]";
        return Driver.getDriver().findElement(By.xpath(xpath));
    }
}
