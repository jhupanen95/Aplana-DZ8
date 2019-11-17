package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage {

    @FindBy(xpath = "//input[@class='ui-input-search__input main-search-form__input ui-autocomplete-input']")
    private WebElement searchForm;

    @FindBy(xpath = "//input[@class='ui-input-search__input main-search-form__input ui-autocomplete-input']/parent::div/span[@class='ui-input-search__icon ui-input-search__icon_search']")
    private WebElement buttonSearch;

    @FindBy(xpath = "//footer")
    private WebElement footer;

    private Wait<WebDriver> wait;

    public SearchPage (WebDriver driver){
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 5, 1000);
    }

    public void search (String request){
        searchForm.clear();
        searchForm.sendKeys(request);
        buttonSearch.click();
        wait.until(ExpectedConditions.visibilityOf(footer));
    }

}
