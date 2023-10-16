package step_definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import utilities.CommonSteps;
import utilities.ConfigurationReader;
import java.util.Random;

public class SearchStepDefs extends CommonSteps {

    String listedProductCount;
    double currentPrice;


    @When("kullanıcı Amazon anasayfasına gider")
    public void kullanıcıAmazonAnasayfasınaGider() {
        driver.get(ConfigurationReader.get("url"));
    }

    @And("Arama kutucuğuna {string} kelimesi girilir.")
    public void aramaKutucuğunaKelimesiGirilir(String keyword) {

        while (homepage.searchBox.size() == 0) {
            driver.navigate().refresh();
        }
        homepage.searchBox.get(0).click();
        homepage.searchBox.get(0).sendKeys(keyword);

    }


    @Then("Arama yapılır.")
    public void aramaYapılır() {
        homepage.searchButton.click();
    }

    @And("Arama kutucuğuna girilen {string} kelimesi silinir.")
    public void aramaKutucuğunaGirilenKelimesiSilinir(String keyword) {

        for (int i = 0; i < keyword.length(); i++) {
            homepage.searchBox.get(0).sendKeys(Keys.BACK_SPACE);
        }

    }

    @And("Klavye üzerinden {string} tuşuna bastırılır.")
    public void klavyeÜzerindenTuşunaBastırılır(String key) {
        key = key.toUpperCase();

        homepage.searchBox.get(0).sendKeys(Keys.valueOf(key));

    }


    @And("Sonuca göre sergilenen ürünlerden rastgele bir ürün seçilir.")
    public void sonucaGöreSergilenenÜrünlerdenRastgeleBirÜrünSeçilir() {

        // product list i SearchPage'e locate ettim
        //List<WebElement> productList = driver.findElements(By.xpath("//a[@class='a-link-normal s-underline-text s-underline-link-text s-link-style a-text-normal']"));
        Random random = new Random();
        WebElement randomItem = searchPage.productList.get(random.nextInt(searchPage.productList.size()));
        randomItem.click();

    }

    @And("Eğer fiyatı {int}$ dan fazlaysa, arama ekranına geri dönülür, {string} fiyat aralığı seçilir ve tekrar aranır. İlk seçenek seçilir.")
    public void eğerFiyatı$DanFazlaysaAramaEkranınaGeriDönülürFiyatAralığıSeçilerVeTekrarAranırİlkSeçenekSeçilir(int price, String priceRange) {


        if (productPage.getProductPrice() > price) {
            driver.navigate().back();
            searchPage.selectPriceRange(priceRange);
            listedProductCount = searchPage.productCount.getText().split(" ")[3];
            searchPage.productList.get(0).click();
        }


    }


    @And("Ürün arama sayfasında listelenen ürün sayısı \\(listedProductCount), seçilen ürünün ürün bilgisi\\(selectedProduct) ve tutar bilgisi\\(currentPrice) txt dosyasına yazılır.")
    public void ürünAramaSayfasındaListelenenÜrünSayısıListedProductCountSeçilenÜrününÜrünBilgisiSelectedProductVeTutarBilgisiCurrentPriceTxtDosyasınaYazılır() {

        currentPrice = productPage.getProductPrice();
        String selectedProduct = productPage.getProductDetails();

        String productDetails = "Listed Product Count: " + listedProductCount + "\n" +
                "Current Product Price: " + currentPrice + "\n" +
                "Product Details: " + selectedProduct;

        CommonSteps.writeToTxtFile("src\\test\\java\\productDetails.txt", productDetails);

    }


    @And("Seçilen ürün sepete eklenir.")
    public void seçilenÜrünSepeteEklenir() {
        currentPrice = productPage.getProductPrice();
        productPage.addToCartButton.click();
    }

    @Then("Ürün sayfasındaki fiyat\\(currentPrice) ile sepette yer alan ürün fiyatının \\(basketPrice) doğruluğu karşılaştırılır.")
    public void ürünSayfasındakiFiyatCurrentPriceIleSepetteYerAlanÜrünFiyatınınBasketPriceDoğruluğuKarşılaştırılır() {

        //driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        cartPage.cartButton.click();
        double basketPrice = cartPage.getBasketPrice();
        Assert.assertEquals(currentPrice, basketPrice, 0.00);
    }

    @And("Adet arttırılarak ürün adedinin {int} olduğu doğrulanır.")
    public void adetArttırılarakÜrünAdedininOlduğuDoğrulanır(int quantity) {

        cartPage.quantityDropdown.click();
        cartPage.quantityValue(quantity);
        int quantityInBasket = Integer.parseInt(driver.findElement(By.xpath("//span[@class='a-dropdown-prompt']")).getText());
        Assert.assertTrue(quantity == quantityInBasket);
    }

}
