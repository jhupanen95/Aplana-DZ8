import org.junit.Assert;
import org.junit.Test;
import pages.CardProductPage;
import pages.CartPage;
import pages.ResaltPage;
import pages.SearchPage;
import utils.Product;

public class DnsTest extends BaseTest{

    @Test
    public void newTest(){

        CardProductPage cardProductPage = new CardProductPage(driver);
        CartPage cartPage = new CartPage(driver);
        ResaltPage resaltPage = new ResaltPage(driver);
        SearchPage searchPage = new SearchPage(driver);

        Product Ps4SlimBlack = new Product("1366507");
        Product detroit = new Product("1225442");

        searchPage.search("PlayStation");
        resaltPage.clickOnProduct(Ps4SlimBlack.getId());
        Ps4SlimBlack.setPrice(cardProductPage.getPrice());
        cardProductPage.selectGuarant(2);
        Ps4SlimBlack.setPriceWithGuar(cardProductPage.getPrice());
        cardProductPage.addToCart();
        searchPage.search("Detroit");
        detroit.setPrice(cardProductPage.getPrice());
        cardProductPage.addToCart();
        Assert.assertTrue(detroit.getPrice() + Ps4SlimBlack.getPriceWithGuar() == cardProductPage.getSummCart());
        cardProductPage.goToCart();
        Assert.assertTrue(cartPage.getGuar(Ps4SlimBlack.getId()) == 2);

        Assert.assertTrue(detroit.getPrice() == cartPage.getPriceProduct(detroit.getId()));
        Assert.assertTrue(Ps4SlimBlack.getPrice() == cartPage.getPriceProduct(Ps4SlimBlack.getId()));
        Assert.assertTrue(Ps4SlimBlack.getPriceWithGuar() + detroit.getPrice() == cartPage.getSummCart());

        cartPage.removeProduct(detroit.getId());
        Assert.assertFalse(cartPage.productInCart(detroit.getId()));
        Assert.assertTrue(Ps4SlimBlack.getPriceWithGuar() == cartPage.getSummCart());

        cartPage.addCount(Ps4SlimBlack.getId(), 2);
        Assert.assertTrue(Ps4SlimBlack.getPriceWithGuar()*3 == cartPage.getSummCart());

        cartPage.returnProduct(detroit.getId());
        Assert.assertTrue(cartPage.productInCart(detroit.getId()));
        Assert.assertTrue(Ps4SlimBlack.getPriceWithGuar()*3 + detroit.getPrice() == cartPage.getSummCart());

    }
}
