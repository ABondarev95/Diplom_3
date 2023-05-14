package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationPage {
    private final WebDriver driver;
    //локатор поля 'Имя'
    private final By nameField = By.xpath(".//fieldset[1]/div/div/input");
    //локатор поля 'Email'
    private final By emailField = By.xpath(".//fieldset[2]/div/div/input");
    //локатор поля 'Пароль'
    private final By passwordField = By.xpath(".//input[@name='Пароль']");
    //локатор кнопки 'Зарегистрироваться'
    private final By registerButton = By.xpath(".//button[text()='Зарегистрироваться']");
    //локатор валидатора ввода формы
    private final By errorValidation = By.className("input__error");
    //локатор кнопки 'Войти'
    private final By loginButton = By.xpath(".//a[text()='Войти']");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }
    //заполнение поля 'Имя'
    public void setName(String name){
        driver.findElement(nameField).sendKeys(name);
    }
    //заполнение поля 'Email'
    public void setEmail(String email){
        driver.findElement(emailField).sendKeys(email);
    }
    //заполнение поля 'Пароль'
    public void setPassword(String password){
        driver.findElement(passwordField).sendKeys(password);
    }
    //нажатие кнопки 'Зарегистрироваться'
    public void clickRegisterButton(){
        driver.findElement(registerButton).click();
    }
    //Процесс регистрации
    public void registration(String name, String email, String password){
        setName(name);
        setEmail(email);
        setPassword(password);
        clickRegisterButton();
    }
    //Отображение ошибки при некорректном пароле
    public String getErrorValidation(){
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(errorValidation));
        return driver.findElement(errorValidation).getText();
    }
    //Нажатие кнопки 'Войти'
    public void clickLoginButton(){
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(loginButton));
        driver.findElement(loginButton).click();
    }

}
