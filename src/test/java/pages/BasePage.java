package pages;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public abstract class BasePage extends Driver{

    public BasePage(){
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "twotabsearchtextbox")
    public List<WebElement> searchBox;


    @FindBy(id = "nav-search-submit-button")
    public WebElement searchButton;

    @FindBy(id="nav-cart")
    public WebElement cartButton;


}
