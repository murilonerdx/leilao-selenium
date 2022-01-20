package leilao.login;

import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {

    private static final String URL_LOGIN = "http://localhost:8080/login";
    private static final String URL_LANCES = "http://localhost:8080/leiloes/2";

    private WebDriver browser;

    @BeforeAll
    public static void beforeAll() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
    }

    @BeforeEach
    public void beforeEach() {
        this.browser = new ChromeDriver();
    }

    @AfterEach
    public void afterEach() {
        this.browser.quit();
    }

    @org.junit.jupiter.api.Test
    public void deveriaEfetuarLoginComDadosValidos() {
        browser.navigate().to(URL_LOGIN);

        browser.findElement(By.id("username")).sendKeys("fulano");
        browser.findElement(By.id("password")).sendKeys("pass");
        browser.findElement(By.id("login-form")).submit();

        String nomeUsuarioLogado = browser.findElement(By.id("usuario-logado")).getText();
        Assertions.assertEquals("fulano", nomeUsuarioLogado);
        Assertions.assertFalse(browser.getCurrentUrl().equals(URL_LOGIN));
    }

    @org.junit.jupiter.api.Test
    public void naoDeveriaEfetuarLoginComDadosInvalidos() {
        browser.navigate().to(URL_LOGIN);

        browser.findElement(By.id("username")).sendKeys("invalido");
        browser.findElement(By.id("password")).sendKeys("123");
        browser.findElement(By.id("login-form")).submit();

        Assert.assertThrows(NoSuchElementException.class, () -> browser.findElement(By.id("usuario-logado")));
        Assertions.assertTrue(browser.getCurrentUrl().contains(URL_LOGIN));
        Assertions.assertTrue(browser.getPageSource().contains("Usuário e senha inválidos"));
    }

    @Test
    public void naoDeveriaAcessarUrlRestritaSemEstarLogado() {
        browser.navigate().to(URL_LANCES);

        Assertions.assertTrue(browser.getCurrentUrl().equals(URL_LOGIN));
        Assertions.assertFalse(browser.getPageSource().contains("Dados do Leilão"));
    }
}


