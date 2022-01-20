package leilao.login;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {

    @Test
    public void efetuarLoginComDadosValidos() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        WebDriver browser = new ChromeDriver();
        browser.navigate().to("http://localhost:8080/login");
        browser.findElement(By.id("username")).sendKeys("fulano");
        browser.findElement(By.id("password")).sendKeys("pass");
        browser.findElement(By.id("login-form")).submit();
        browser.quit();

        Assert.assertNotEquals("http://localhost:8080/login", browser.getCurrentUrl());
        Assert.assertEquals("fulano",browser.findElement(By.id("usuario-logado")));
    }
}
