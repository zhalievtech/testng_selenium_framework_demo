package com.weborders.pages;

import com.weborders.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public abstract class Menu {
    public Menu() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy (tagName = "h2")
    public WebElement header;

    @FindBy(linkText="View all orders")
    public WebElement viewAllOrdersLink;

    @FindBy(linkText="View all products")
    public WebElement viewAllProductsLink;

    @FindBy(linkText="Order")
    public WebElement orderLink;

    @FindBy(css=".login_info")
    public WebElement welcomeMessage;

    @FindBy(linkText="Logout")
    public WebElement logout;
}
