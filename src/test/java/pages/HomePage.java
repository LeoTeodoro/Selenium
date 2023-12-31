package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class HomePage extends BasePage{
    public HomePage(WebDriver driver) {
        super(driver);
    }

    public ProfilePage clickProfile(){
        driver.findElement(By.className("me")).click();

        return new ProfilePage(driver);
    }
    
}
