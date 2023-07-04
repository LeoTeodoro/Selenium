package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ContactPage extends BasePage {
    public ContactPage(WebDriver driver) {
        super(driver);
    }
    public ContactPage choiceContactType(String type){
        WebElement campoType = driver.findElement(By.id("addmoredata")).findElement(By.name("type"));
        new Select(campoType).selectByVisibleText(type);
        return this;
    }
    public ContactPage fillInTheContactField(String contact){
        driver.findElement(By.id("addmoredata")).findElement(By.name("contact")).sendKeys(contact);
        return this;
    }
    public ProfilePage saveClick(){
        driver.findElement(By.id("addmoredata")).findElement(By.linkText("SAVE")).click();
        return new ProfilePage(driver);
    }
    public ProfilePage addContact(String type,String contact){
        choiceContactType(type);
        fillInTheContactField(contact);
        saveClick();

        return new ProfilePage(driver);
    }
}
