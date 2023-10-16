package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage{
    @FindBy(xpath = "//p[@class='a-spacing-mini']//span")
    public WebElement basketPrice;

    public Double getBasketPrice() {
        return Double.parseDouble(basketPrice.getText().replace("$", ""));
    }

    @FindBy(id = "a-autoid-0-announce")
    public WebElement quantityDropdown;

    public void quantityValue(int quantity) {
        driver.findElement(By.xpath("//li[@aria-labelledby='quantity_" + quantity + "']")).click();
    }
}
