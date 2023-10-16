package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductPage extends BasePage {
    @FindBy(xpath = "//div[@id=\"corePrice_feature_div\"]//span[@class=\"a-price-whole\"]")
    public WebElement productWholePrice;

    @FindBy(xpath = "//div[@id=\"corePrice_feature_div\"]//span[@class=\"a-price-fraction\"]")
    public WebElement productFractionPrice;

    public Double getProductPrice() {
        String productPriceStr = productWholePrice.getText() + "." + productFractionPrice.getText();
        return Double.parseDouble(productPriceStr);
    }

    @FindBy(id = "productTitle")
    public WebElement productTitle;

    @FindBy(xpath = "//li[@class='a-spacing-mini']//span[@class='a-list-item']")
    public List<WebElement> productDescription;

    public String getProductDetails() {
        String productTitleText = productTitle.getText();

        StringBuilder resultBuilder = new StringBuilder();
        for (WebElement element : productDescription) {
            resultBuilder.append(element.getText()).append("\n");
        }

        if (resultBuilder.length() > 0) {
            resultBuilder.setLength(resultBuilder.length() - 2);
        }
        // Convert StringBuilder to String
        String result = resultBuilder.toString();

        return productTitleText + "\n" + result ;
    }

    @FindBy(id="add-to-cart-button")
    public WebElement addToCartButton;
}
