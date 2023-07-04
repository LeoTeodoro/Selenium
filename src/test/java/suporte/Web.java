package suporte;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Web {
    public static WebDriver createchrome(){
        //Abrindo o navegador
        System.setProperty("webdriver.chrome.driver","C:\\Users\\Leonardo\\Drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);

        //Entrando no site desejado
        driver.get("http://www.juliodelima.com.br/taskit");

        return driver;
    }

}
