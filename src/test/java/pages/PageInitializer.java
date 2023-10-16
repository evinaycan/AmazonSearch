package pages;

import utilities.Driver;

public class PageInitializer extends Driver {
    public static Homepage homepage;
    public static SearchPage searchPage;
    public static ProductPage productPage;
    public static CartPage cartPage;

    public static void initialize() {
        homepage = new Homepage();
        searchPage = new SearchPage();
        productPage = new ProductPage();
        cartPage = new CartPage();
    }

}
