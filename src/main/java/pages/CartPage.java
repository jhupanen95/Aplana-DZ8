package pages;

import com.google.common.primitives.Chars;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;


public class CartPage {

    @FindBy(xpath = "//div[@class='cart-page-new']")
    public static WebElement cartContainer;

    @FindBy(xpath = "//div[@class='total-amount__info-block']")
    public static WebElement totalPriceBlock;

    @FindBy(xpath = "//a[@class='restore-last-removed']")
    public static WebElement returnProduct;

    private Wait<WebDriver> wait;

    public CartPage (WebDriver driver){
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 5, 1000);
    }

    public long getPriceProduct(String id) {
        String price = cartContainer.findElement(By.xpath("//div[contains(text(), '" + id + "')]/../../../div[@class='cart-list__product-count-price']/div/div/span")).getText().replaceAll(" ", "");
        return Long.parseLong(price);
    }

    public void removeProduct(String id) {
        cartContainer.findElement(By.xpath("//div[contains(text(), '" + id + "')]/../../../div[@class='cart-list__product-special-buttons']/button[@class='remove-button']")).click();
        wait.until(ExpectedConditions.visibilityOf(returnProduct));
    }

    public void returnProduct(String id) {
        returnProduct.click();
        wait.until(ExpectedConditions.visibilityOf(cartContainer.findElement(By.xpath("//div[contains(text(), '" + id + "')]/../../.."))));
    }

    public boolean productInCart(String id) {
        try {
            cartContainer.findElement(By.xpath("//div[contains(text(), '" + id + "')]/../../.."));
            return true;
        }catch (NoSuchElementException ex){
            return false;
        }
    }

    public void addCount(String id, int count) {
        String price = cartContainer.findElement(By.xpath("//div[contains(text(), '" + id + "')]/../../../div[@class='cart-list__product-count-price']/div[@class='cart-list__product-price']/div/span")).getText().replaceAll(" ", "");
        WebElement buttonPlus = cartContainer.findElement(By.xpath("//div[contains(text(), '" + id + "')]/../../../div[@class='cart-list__product-count-price']/div[@class='cart-list__product-count-buttons']/div/button[@class='count-buttons__button count-buttons__button_plus']"));
        for (int i = 1; i <= count; i++){
            buttonPlus.click();
            String actualPrice = reformInt(price, i);
            wait.until(ExpectedConditions.textToBePresentInElement(cartContainer.findElement(By.xpath("//div[contains(text(), '" + id + "')]/../../../div[@class='cart-list__product-count-price']/div[@class='cart-list__product-price']/div/span")), actualPrice));

        }
    }

    public String reformInt(String str, int mult){
        int priceInt = Integer.parseInt(str);
        StringBuilder stringBuilder = new StringBuilder("");
        String summ = Integer.toString(priceInt * (mult+1));
        char[] array = summ.toCharArray();
        int b = 0;
        for (int j = array.length; j > 0; j--, b++){
            if (b == 3) stringBuilder.append(' ');
            stringBuilder.append(array[j-1]);
        }
        stringBuilder.reverse();
        return stringBuilder.toString();
    }

    public int getGuar(String id) {
        WebElement product = cartContainer.findElement(By.xpath("//div[contains(text(), '" + id + "')]/../../.."));
        WebElement divGuar = product.findElement(By.xpath("//div[@class='cart-list__product-additional-warranty cart-list__product-additional-warranty_wide-screen']"));
        String year = divGuar.findElement(By.xpath("//div[@class='radio radio_checked']/label")).getText();
        if (year.charAt(2) == 'ы') return 0;
        else return year.charAt(2)-48;
    }

    public long getSummCart() {
        String summ = totalPriceBlock.findElement(By.xpath("//div[@class='total-amount__info-block']/div[@class='item-price']/span")).getText().replaceAll(" ", "");
        return Long.parseLong(summ);
    }




}
