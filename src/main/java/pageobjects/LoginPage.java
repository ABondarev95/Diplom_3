package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;
    //локатор поля 'Email'
    private final By emailField = By.xpath(".//input[@name='name']");
    //локатор поля 'Пароль'
    private final By passwordField = By.xpath(".//input[@name='Пароль']");
    //локатор кнопки 'Войти'
    private final By loginButton = By.xpath(".//button[text()='Войти']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    //заполнение поля 'Email'
    public void setEmail(String email){
        driver.findElement(emailField).sendKeys(email);
    }
    //заполнение поля 'Пароль'
    public void setPassword(String password){
        driver.findElement(passwordField).sendKeys(password);
    }
    //Поиск кнопки 'Войти'
    public void findLoginButton(){
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.	visibilityOfElementLocated(loginButton));
    }
    //Нажатие кнопки 'Войти'
    public void clickLoginButton(){
        driver.findElement(loginButton).click();
    }
    //Авторизация
    public void login(String email, String password){
        setEmail(email);
        setPassword(password);
        clickLoginButton();
    }

}
