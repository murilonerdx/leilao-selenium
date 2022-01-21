package leilao.login;

import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

public class LoginTest {

    private static final String URL_LANCES = "http://localhost:8080/leiloes/2";

    LoginPage paginaLogin;

    @BeforeEach
    public void beforeEach() {
        this.paginaLogin = new LoginPage();
    }

    @AfterEach
    public void afterEach() {
        this.paginaLogin.quit();
    }

    @org.junit.jupiter.api.Test
    public void deveriaEfetuarLoginComDadosValidos() {
        paginaLogin.setForm("username", "password");
        paginaLogin.submitLoginPage("login-form");

        String nomeUsuarioLogado = paginaLogin.getNameUser("usuario-logado");
        Assertions.assertEquals("fulano", nomeUsuarioLogado);
        Assertions.assertNotEquals(LoginPage.URL_LOGIN, paginaLogin.getCurrentUrl());

    }

    @org.junit.jupiter.api.Test
    public void naoDeveriaEfetuarLoginComDadosInvalidos() {

        paginaLogin.setForm("username", "password", "invalido", "123");
        paginaLogin.submitLoginPage("login-form");

        Assert.assertThrows(NoSuchElementException.class, () -> paginaLogin.getNameUser("usuario-logado"));
        Assertions.assertTrue(paginaLogin.getCurrentUrl().contains(LoginPage.URL_LOGIN));
        Assertions.assertTrue(paginaLogin.getSourceLoginPage().contains("Usuário e senha inválidos"));
    }

    @Test
    public void naoDeveriaAcessarUrlRestritaSemEstarLogado() {
        this.paginaLogin = new LoginPage(URL_LANCES);

        Assertions.assertEquals(LoginPage.URL_LOGIN, paginaLogin.getCurrentUrl());
        Assertions.assertFalse(paginaLogin.getSourceLoginPage().contains("Dados do Leilão"));
    }
}


