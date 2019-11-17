package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ResaltPage {

    @FindBy(xpath = "//div[@class='products-list__content']")
    private WebElement searchResalts;

    @FindBy(xpath = "//h1[@class='page-title price-item-title']")
    private WebElement titleProduct;

    private Wait<WebDriver> wait;
    Actions actions;

    public ResaltPage (WebDriver driver){
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 5, 1000);
        actions = new Actions(driver);
    }

    public void clickOnProduct(String id){
//        WebElement product = searchResalts.findElement(By.xpath("//span[text()='" + id + "']/ancestor::div[@class='catalog-item']/div"));
//        product.findElement(By.xpath("//div[@class='product-info__title-link']/a")).click();
        WebElement product = searchResalts.findElement(By.xpath("//span[text()='" + id + "']/../../a"));
        actions.moveToElement(product);
        actions.perform();
        product.click();
        wait.until(ExpectedConditions.visibilityOf(titleProduct));
    }

}
