package ecom.Bitm.SwagLabs.pomPages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.time.Duration;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class DropDown_Page {

    WebDriver driver;

    public DropDown_Page(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".product_sort_container")
    private WebElement dropdownlist;

    @FindBy(css = ".inventory_item_name")
    private List<WebElement> productNames;

    @FindBy(css = ".inventory_item_price")
    private List<WebElement> productPrices;

    @FindBy(css = ".inventory_item button")
    private List<WebElement> addToCartButtons;

    @FindBy(css = ".shopping_cart_badge")
    private WebElement cartBadge;

    public void dropdownvisible() {

        Assert.assertTrue(
                dropdownlist.isDisplayed(),
                "Sort dropdown is not visible"
        );

        Assert.assertTrue(
                dropdownlist.isEnabled(),
                "Sort dropdown is not enabled"
        );

        System.out.println(
                "Sort dropdown is visible and enabled"
        );
    }

    public void selectSortOption(String sortOption) {

        Assert.assertNotNull(
                sortOption,
                "Sort option cannot be null"
        );

        Select select = new Select(dropdownlist);

        select.selectByVisibleText(sortOption);

        String actualOption =
                select.getFirstSelectedOption().getText();

        Assert.assertEquals(
                actualOption,
                sortOption,
                "Incorrect sorting option selected"
        );

        System.out.println(
                "Selected sorting option: " + actualOption
        );
    }

    public void lowToHighSort() {

        selectSortOption("Price (low to high)");

        verifyPriceLowToHigh();

        System.out.println(
                "Products are ordered from lowest to highest price"
        );
    }

    public void verifyAllSortOptions() {

        Select select = new Select(dropdownlist);

        List<String> actualOptions =
                new ArrayList<>();

        for (WebElement option : select.getOptions()) {

            actualOptions.add(
                    option.getText().trim()
            );
        }

        List<String> expectedOptions =
                new ArrayList<>();

        expectedOptions.add("Name (A to Z)");
        expectedOptions.add("Name (Z to A)");
        expectedOptions.add("Price (low to high)");
        expectedOptions.add("Price (high to low)");

        Assert.assertEquals(
                actualOptions,
                expectedOptions,
                "Sorting options are incorrect"
        );

        System.out.println(
                "All expected sorting options are available"
        );
    }


    public void verifyCorrectOrdering(String sortOption) {

        selectSortOption(sortOption);

        verifySelectedSorting(sortOption);

        System.out.println(
                "Correct ordering verified for: "
                        + sortOption
        );
    }

    public void verifyProductCountAfterSorting(
            String sortOption) {

        int beforeSorting =
                productNames.size();

        Assert.assertEquals(
                beforeSorting,
                6,
                "Expected 6 products before sorting"
        );

        selectSortOption(sortOption);

        int afterSorting =
                productNames.size();

        Assert.assertEquals(
                afterSorting,
                beforeSorting,
                "Product count changed after sorting"
        );

        System.out.println(
                "Product count remains "
                        + afterSorting
                        + " after sorting"
        );
    }

    public void verifySortingAfterAddingItem(
            String sortOption) {

        WebDriverWait wait =
                new WebDriverWait(
                        driver,
                        Duration.ofSeconds(10)
                );

        int originalProductCount =
                productNames.size();

        Assert.assertEquals(
                originalProductCount,
                6,
                "Expected 6 products on inventory page"
        );

        WebElement firstProduct =
                driver.findElements(
                        By.cssSelector(".inventory_item")
                ).get(0);

        WebElement productNameElement =
                firstProduct.findElement(
                        By.cssSelector(".inventory_item_name")
                );

        String addedProductName =
                productNameElement.getText().trim();

        Assert.assertFalse(
                addedProductName.isEmpty(),
                "Product name is empty"
        );

        System.out.println(
                "Product selected: "
                        + addedProductName
        );


        WebElement addButton =
                firstProduct.findElement(
                        By.cssSelector("button")
                );

        Assert.assertTrue(
                addButton.isDisplayed(),
                "Add to Cart button is not displayed"
        );

        Assert.assertTrue(
                addButton.isEnabled(),
                "Add to Cart button is not enabled"
        );


        addButton.click();

        System.out.println(
                "Clicked Add to Cart for: "
                        + addedProductName
        );

        verifyCartContainsOneItem();

        selectSortOption(sortOption);

        verifySelectedSorting(sortOption);

        Assert.assertEquals(
                productNames.size(),
                originalProductCount,
                "Product count changed after sorting"
        );

        WebElement cartIcon =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                By.className(
                                        "shopping_cart_link"
                                )
                        )
                );

        Assert.assertTrue(
                cartIcon.isDisplayed(),
                "Shopping cart icon is not displayed"
        );

        Assert.assertTrue(
                cartIcon.isEnabled(),
                "Shopping cart icon is not enabled"
        );

        cartIcon.click();

        System.out.println(
                "Shopping cart opened"
        );

        WebElement cartTitle =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                By.xpath(
                                        "//span[text()='Your Cart']"
                                )
                        )
                );

        Assert.assertTrue(
                cartTitle.isDisplayed(),
                "Cart page is not displayed"
        );

        WebElement cartItem =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector(".cart_item")
                        )
                );

        Assert.assertTrue(
                cartItem.isDisplayed(),
                "Added product is not displayed in cart"
        );

        WebElement cartProductName =
                cartItem.findElement(
                        By.cssSelector(
                                ".inventory_item_name"
                        )
                );

        Assert.assertTrue(
                cartProductName.isDisplayed(),
                "Product name is not displayed in cart"
        );

        Assert.assertEquals(
                cartProductName.getText().trim(),
                addedProductName,
                "Incorrect product is present in cart"
        );

        WebElement removeButton =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                By.cssSelector(".cart_button")
                        )
                );

        Assert.assertTrue(
                removeButton.isDisplayed(),
                "Remove button is not displayed"
        );

        Assert.assertTrue(
                removeButton.isEnabled(),
                "Remove button is not enabled"
        );

        removeButton.click();

        System.out.println(
                "Item removed from cart after completing "
                        + "all TC_036 validations"
        );

        wait.until(
                ExpectedConditions.invisibilityOfElementLocated(
                        By.cssSelector(".shopping_cart_badge")
                )
        );

        System.out.println(
                "Cart is empty and ready for next test"
        );

        System.out.println(
                "Sorting works after adding item "
                        + "and cart cleanup completed"
        );
    }

    public void verifyCartContainsOneItem() {

        WebDriverWait wait =
                new WebDriverWait(
                        driver,
                        Duration.ofSeconds(10)
                );

        WebElement badge =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector(
                                        ".shopping_cart_badge"
                                )
                        )
                );

        Assert.assertTrue(
                badge.isDisplayed(),
                "Cart badge is not displayed"
        );

        Assert.assertEquals(
                badge.getText().trim(),
                "1",
                "Cart badge is not equal to 1"
        );

        System.out.println(
                "Cart badge reflects the added item count"
        );
    }

    public void verifyDefaultSortOrder() {

        Select select =
                new Select(dropdownlist);

        String defaultOption =
                select.getFirstSelectedOption().getText();

        Assert.assertEquals(
                defaultOption,
                "Name (A to Z)",
                "Incorrect default sorting option"
        );

        List<String> actual =
                getProductNames();

        List<String> expected =
                new ArrayList<>(actual);

        Collections.sort(expected);

        Assert.assertEquals(
                actual,
                expected,
                "Products are not in default order"
        );

        System.out.println(
                "Default sort order is correct"
        );
    }

    public void verifyNameAZ() {

        selectSortOption("Name (A to Z)");

        verifyNameAscending();

        System.out.println(
                "Products are alphabetically ascending"
        );
    }

    public void verifyNameZA() {

        selectSortOption("Name (Z to A)");

        verifyNameDescending();

        System.out.println(
                "Products are alphabetically descending"
        );
    }

    public void verifyHighToLowSort() {

        selectSortOption("Price (high to low)");

        verifyPriceHighToLow();

        System.out.println(
                "Products are ordered from highest to lowest price"
        );
    }

    private void verifySelectedSorting(
            String sortOption) {

        switch (sortOption) {

            case "Name (A to Z)":

                verifyNameAscending();

                break;


            case "Name (Z to A)":

                verifyNameDescending();

                break;


            case "Price (low to high)":

                verifyPriceLowToHigh();

                break;


            case "Price (high to low)":

                verifyPriceHighToLow();

                break;


            default:

                Assert.fail(
                        "Invalid sorting option: "
                                + sortOption
                );
        }
    }

    private List<String> getProductNames() {

        List<String> names =
                new ArrayList<>();

        for (WebElement product :
                productNames) {

            names.add(
                    product.getText().trim()
            );
        }

        return names;
    }

    private List<Double> getProductPrices() {

        List<Double> prices =
                new ArrayList<>();

        for (WebElement product :
                productPrices) {

            String price =
                    product.getText()
                            .replace("$", "")
                            .trim();

            prices.add(
                    Double.parseDouble(price)
            );
        }

        return prices;
    }

    private void verifyNameAscending() {

        List<String> actual =
                getProductNames();

        List<String> expected =
                new ArrayList<>(actual);

        Collections.sort(expected);

        Assert.assertEquals(
                actual,
                expected,
                "Products are not sorted Name A-Z"
        );
    }

    private void verifyNameDescending() {

        List<String> actual =
                getProductNames();

        List<String> expected =
                new ArrayList<>(actual);

        expected.sort(
                Collections.reverseOrder()
        );

        Assert.assertEquals(
                actual,
                expected,
                "Products are not sorted Name Z-A"
        );
    }

    private void verifyPriceLowToHigh() {

        List<Double> actual =
                getProductPrices();

        List<Double> expected =
                new ArrayList<>(actual);

        Collections.sort(expected);

        Assert.assertEquals(
                actual,
                expected,
                "Products are not sorted Price low-to-high"
        );
    }
    
    private void verifyPriceHighToLow() {

        List<Double> actual =
                getProductPrices();

        List<Double> expected =
                new ArrayList<>(actual);

        expected.sort(
                Collections.reverseOrder()
        );

        Assert.assertEquals(
                actual,
                expected,
                "Products are not sorted Price high-to-low"
        );
    }
}