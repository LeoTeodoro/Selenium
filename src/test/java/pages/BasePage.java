package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static org.junit.Assert.assertEquals;

public class BasePage {
    protected WebDriver driver;
    public BasePage(WebDriver driver){
        this.driver = driver;
    }
    public void assertToastText(String expectedText){
        String toast = driver.findElement(By.id("toast-container")).getText();
        assertEquals(expectedText,toast);
    }
}
