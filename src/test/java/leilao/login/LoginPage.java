package leilao.login;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginPage {
    protected static final String URL_LOGIN = "http://localhost:8080/login";
    private WebDriver browser;

    public LoginPage() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        this.browser = new ChromeDriver();
        this.browser.navigate().to(URL_LOGIN);
    }

    public LoginPage(String URL){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        this.browser = new ChromeDriver();
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
        if(args == null){
            args[0] = "aas";
            args[1] = "pass";
        }
        browser.findElement(By.id(idParamUsernameOrEmail)).sendKeys(args[0]);
        browser.findElement(By.id(idParamPassword)).sendKeys(args[1]);
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
