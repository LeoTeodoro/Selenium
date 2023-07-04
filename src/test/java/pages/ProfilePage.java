package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage extends BasePage{
    public ProfilePage(WebDriver driver) {
        super(driver);
    }
    public ProfilePage MoreDataAboutYouClick(){
        driver.findElement(By.linkText("MORE DATA ABOUT YOU")).click();

        return this;
    }
    public ContactPage addMoreDataClick(){
        driver.findElement(By.xpath("//button[@data-target=\"addmoredata\"]")).click();
        return new ContactPage(driver);
    }
}
