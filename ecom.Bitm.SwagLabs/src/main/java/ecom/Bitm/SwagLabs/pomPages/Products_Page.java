package ecom.Bitm.SwagLabs.pomPages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class Products_Page {

    WebDriver driver;

    public Products_Page(WebDriver driver) {

        this.driver = driver;

        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[text()='Products']")
    private WebElement productsText;

    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    private WebElement backpackAddToCartBtn;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartIcon;

    @FindBy(className = "shopping_cart_badge")
    private WebElement cartBadge;

    @FindBy(css = "[class='inventory_item_img']")
    private WebElement product;

    @FindBy(css = "[class='inventory_item']")
    private List<WebElement> inventoryItems;

    @FindBy(css = "[class='inventory_item_price']")
    private List<WebElement> price;

    @FindBy(xpath = "//span[text()='Your Cart']")
    private WebElement cartpagetitle;

    @FindBy(id = "react-burger-menu-btn")
    private WebElement menuclick;

    @FindBy(id = "react-burger-cross-btn")
    private WebElement menucancel;

    public void verifyProductsPage() {

        Assert.assertTrue(
                productsText.isDisplayed(),
                "Products page is not displayed"
        );

        System.out.println(
                "Inventory Homepage Displayed"
        );
    }

    public void getProduct() {

        Assert.assertTrue(
                product.isDisplayed(),
                "Product is not displayed"
        );

        System.out.println(
                "Product is Displayed"
        );
    }

    public void getClickproduct(String productName) {

        WebElement clickproduct =
                driver.findElement(
                        By.xpath(
                                "//div[text()='"
                                        + productName
                                        + "']"
                        )
                );

        Assert.assertTrue(
                clickproduct.isDisplayed(),
                productName
                        + " is not displayed"
        );

        String text =
                clickproduct.getText();

        Assert.assertEquals(
                text,
                productName,
                "Product name does not match"
        );

        clickproduct.click();

        System.out.println(
                "Corresponding page opened: "
                        + productName
        );
    }

    public void getExpectedproductdata() {

        WebElement clickproduct =
                driver.findElement(
                        By.xpath(
                                "//div[text()='Sauce Labs Backpack']"
                        )
                );

        String text =
                clickproduct.getText();

        Assert.assertEquals(
                text,
                "Sauce Labs Backpack",
                "Expected product does not match"
        );

        System.out.println(
                "Expected Product is Visible"
        );
    }

    public void Visibledata() {

        for (WebElement i : inventoryItems) {

            Assert.assertTrue(
                    i.isDisplayed(),
                    "Product is not displayed"
            );
        }

        for (WebElement i : price) {

            Assert.assertTrue(
                    i.isDisplayed(),
                    "Product price is not displayed"
            );
        }

        System.out.println(
                "Product data is visible"
        );
    }

    public void productrefresh() {

        driver.navigate().refresh();

        Assert.assertTrue(
                product.isDisplayed(),
                "Product is not displayed after refresh"
        );

        System.out.println(
                "User is still logged in"
        );
    }

    public void menu()
            throws InterruptedException {

        menuclick.click();

        Thread.sleep(1000);

        menucancel.click();

        System.out.println(
                "Navigation Menu successfully opens and closes"
        );
    }

    public void getInventoryItems() {

        Assert.assertEquals(
                inventoryItems.size(),
                6,
                "Expected 6 products but found "
                        + inventoryItems.size()
        );

        System.out.println(
                "Every Product has cart control"
        );
    }

    public void getAddtocart() {

        backpackAddToCartBtn.click();

        System.out.println(
                "Backpack added to cart"
        );
    }

    public void verifyCartContainsOneItem() {

        Assert.assertTrue(
                cartBadge.isDisplayed(),
                "Cart badge is not displayed"
        );

        Assert.assertEquals(
                cartBadge.getText(),
                "1",
                "Cart badge is not equal to 1"
        );

        System.out.println(
                "Cart badge reflects the added item count"
        );
    }

    public boolean isProductDisplayed(String productName) {

        List<WebElement> products =
                driver.findElements(
                        By.xpath("//div[text()='" + productName + "']")
                );

        return !products.isEmpty();
    }

    public void clickCart() {

        cartIcon.click();

        Assert.assertTrue(
                cartpagetitle.isDisplayed(),
                "Cart page is not displayed"
        );

        System.out.println(
                "Cart navigation is successful"
        );
    }
}