package tests;

import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import suporte.Web;

@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "InformacoesUsuarioTestPageObjects.csv")
public class InformacoesUsuarioTestPageObjects {
    private WebDriver driver;
    @Before
    public void SetUp(){
        driver = Web.createchrome();
    }

    @Test
    public void testAdicionarInformacaoAdicionalDoUsuario(
            @Param(name="login")String login,
            @Param(name="password")String password,
            @Param(name="type")String type,
            @Param(name="contact")String contact,
            @Param(name="message")String message
            ){
        new LoginPage(driver)
                .clickSignIn()
                .typeLogin(login)
                .typePassword(password)
                .signInClick()
                .clickProfile()
                .MoreDataAboutYouClick()
                .addMoreDataClick()
                .addContact(type,contact)
                .assertToastText(message);
    }

    @After
    public void TearDown(){
        driver.quit();
    }
}
