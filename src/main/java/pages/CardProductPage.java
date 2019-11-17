package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CardProductPage {
    @FindBy(xpath = "//select[@class='form-control select']")
    private WebElement selectG;

    @FindBy(xpath = "//div[@class='clearfix']")
    private WebElement priceBox;

    @FindBy(xpath = "//div[@class='buttons-wrapper buttons-wrapper_without-by-in-shop']")
    private WebElement buttonWrapper;

    @FindBy(xpath = "(//a[@class='btn-cart-link'])[2]")
    private WebElement buttonToCart;

    @FindBy(xpath = "(//span[@data-of='totalPrice'])[2]")
    private WebElement summCart;

    private Wait<WebDriver> wait;

    public CardProductPage (WebDriver driver){
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 5, 1000);
    }

    public void selectGuarant(int year){
        Select select = new Select(selectG);
        select.selectByIndex(year);
    }

    public long getPrice() {
        String price = priceBox.findElement(By.xpath("//span[@class='current-price-value']")).getText().replaceAll(" ", "");
        return Long.parseLong(price);
    }

    public void addToCart() {
        buttonWrapper.findElement(By.xpath("//button[@class='btn btn-cart btn-lg']")).click();
        wait.until(ExpectedConditions.visibilityOf(buttonWrapper.findElement(By.xpath("//button[@class='btn btn-cart btn-lg btn-active']"))));
    }

    public long getSummCart() {
        String summ = summCart.getText().replaceAll(" ", "");
        return Long.parseLong(summ);
    }

    public void goToCart() {
        buttonToCart.click();
        wait.until(ExpectedConditions.visibilityOf(CartPage.cartContainer.findElement(By.xpath("//a[@class='tab-controls__tab tab-controls__tab_active']"))));
    }
}
