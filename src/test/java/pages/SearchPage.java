package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchPage extends BasePage {
    @FindBy(xpath = "//h2//a")
    public List<WebElement> productList;

    public void selectPriceRange(String priceRange){
        driver.findElement(By.xpath("//span[.='Price']//..//..//span[.='"+priceRange+"']")).click();
    }

    @FindBy(xpath = "//*[@id=\"search\"]/span[2]/div/h1/div/div[1]/div/div/span[1]")
    public WebElement productCount;

}
