package tests;

import static org.junit.Assert.*;

import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import suporte.Generator;
import suporte.ScreenShot;
import suporte.Web;

import java.time.Duration;

@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "InformacoesUsuarioTestData.csv")

public class InformacoesUsuarioTest {
    private WebDriver driver;

    @Rule
    public TestName testName = new TestName();

    @Before
    public void setUp(){
        driver = Web.createchrome();

        //clicando no botão de sign in pelo seu texto
        driver.findElement(By.linkText( "Sign in")).click();

        //Identificando o formulario de login
        WebElement formularioSignin = driver.findElement(By.id("signinbox"));

        //preenchendo o campo usuario que está dentro do fomulario de id=signinbox com o nome de usuario
        formularioSignin.findElement(By.name("login")).sendKeys("leoteodoro");

        //reenchendo o campo senha que está dentro do fomulario de id=signinbox com a senha
        formularioSignin.findElement(By.name("password")).sendKeys("leo123");

        //clicar no botão para logar
        driver.findElement(By.linkText("SIGN IN")).click();

        //Clicar no link da mensagem Hi, Leonardo
        driver.findElement(By.className("me")).click();

        //Clicar em MORE DATA ABOUT YOU
        driver.findElement(By.linkText("MORE DATA ABOUT YOU")).click();

    }

    @Test
    public void testAdicionarInformacaoAdicionalDoUsuario(@Param(name="tipo")String tipo, @Param(name="contato")String contato, @Param(name="mensagem")String mensagemEsperada){

        //Clicar no botão ADD MORE DATA
        driver.findElement(By.xpath("//button[@data-target=\"addmoredata\"]")).click();

        //Escolher a opção phone
        WebElement addmoredata = driver.findElement(By.id("addmoredata"));
        WebElement campoType = addmoredata.findElement(By.name("type"));
        new Select(campoType).selectByVisibleText(tipo);

        //Digitar +551199991111
        addmoredata.findElement(By.name("contact")).sendKeys(contato);

        //Clicar em save
        addmoredata.findElement(By.linkText("SAVE")).click();

        //Confirmar adição de informações
        WebElement id = driver.findElement(By.id("toast-container"));
        String mensagem = id.getText();
        assertEquals(mensagemEsperada,mensagem);

    }

    @Test
    public void excluirUmContato(){
        //Clicar no ícone de lixeira no numero escolhido
        driver.findElement(By.xpath("//span[text()=\"+551199991111\"]/following-sibling::a")).click();

        //Confirmar a exclusão do número
        driver.switchTo().alert().accept();

        //Verificar a mensagem de exclusão
        WebElement id = driver.findElement(By.id("toast-container"));
        String mensagem = id.getText();
        assertEquals("Rest in peace, dear phone!",mensagem);

        String screenShotArq = "C:\\Users\\Leonardo\\Documents\\MAGISDEV\\report-test\\" + Generator.dataHoraParaArquivo() + testName.getMethodName() + ".png";
        ScreenShot.tirar(driver,screenShotArq);

        //Aguardar até que a mensagem de exclusão desapareça
        int seconds = 10;
        Duration duration = Duration.ofSeconds(seconds);
        WebDriverWait aguardar = new WebDriverWait(driver, duration);
        aguardar.until(ExpectedConditions.stalenessOf(id));
    }

    @After
    public void tearDown(){
        //Fecha o navegador
        driver.quit();
    }
}
