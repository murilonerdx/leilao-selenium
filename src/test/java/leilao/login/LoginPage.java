package leilao.login;

import leilao.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class LoginPage extends PageObject {
    protected static final String URL_LOGIN = "http://localhost:8080/login";

    public LoginPage() {
        super(null);
        this.browser.navigate().to(URL_LOGIN);
    }

    public LoginPage(String URL){
        super(null);
        this.browser.navigate().to(URL);
    }

    public WebDriver getBrowser() {
        return browser;
    }

    public void setBrowser(WebDriver browser) {
        this.browser = browser;
    }

    public void quit() {
        browser.quit();
    }

    public void setForm(String idParamUsernameOrEmail, String idParamPassword, String ... args){
        if(args.length > 2){
            throw new RuntimeException("Parameters are beyond the limit");
        }

        browser.findElement(By.id(idParamUsernameOrEmail)).sendKeys(args.length == 0 ? "fulano" : args[0]);
        browser.findElement(By.id(idParamPassword)).sendKeys(args.length == 0 ? "pass" : args[1]);
    }

    private void preencherFormularioDeLogin(String username, String password) {
        browser.findElement(By.id("username")).sendKeys(username);
        browser.findElement(By.id("password")).sendKeys(password);
    }

    public LeiloesPage efetuarLogin(String username, String password) {
        this.preencherFormularioDeLogin(username, password);
        browser.findElement(By.id("login-form")).submit();
        return new LeiloesPage(browser);
    }

    public String getNomeUsuarioLogado() {
        try {
            return browser.findElement(By.id("usuario-logado")).getText();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public void submitLoginPage(String idLogin){
        browser.findElement(By.id(idLogin)).submit();
    }

    public String getNameUser(String idUser){
        return browser.findElement(By.id(idUser)).getText();
    }

    public String getCurrentUrl(){
        return this.browser.getCurrentUrl();
    }

    public String getSourceLoginPage(){
        return browser.getPageSource();
    }
}
