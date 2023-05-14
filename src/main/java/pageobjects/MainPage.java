package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private final WebDriver driver;
    //локатор кнопки "Личный Кабинет"
    private final By personalAreaButton = By.xpath(".//p[text()='Личный Кабинет']");
    //локатор кнопки "Войти в аккаунт"
    private final By signInButton = By.xpath(".//button[text()='Войти в аккаунт']");
    //локатор вкладки "Булки"
    private final By bunsTab = By.xpath(".//span[text()= 'Булки']");
    //локатор вкладки "Соусы"
    private final By sauceTab = By.xpath(".//span[text()= 'Соусы']");
    //локатор вкладки "Начинки"
    private final By fillingsTab = By.xpath(".//span[text()= 'Начинки']");
    //локатор кнопки "Оформить заказ"
    private final By checkoutButton = By.xpath(".//button[text()='Оформить заказ']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }
    //Нажать 'Личный кабинет'
    public void clickPersonalArea(){
        driver.findElement(personalAreaButton).click();
    }
    //Нажать 'Войти в аккаунт'
    public void clickSignIn(){
        driver.findElement(signInButton).click();
    }
    //Нажать на вкладку 'Булки'
    public void clickBunsTab(){
        driver.findElement(bunsTab).click();
    }
    //Нажать на вкладку 'Соусы'
    public void clickSauceTab(){
        driver.findElement(sauceTab).click();
    }
    //Нажать на вкладку 'Начинки'
    public void clickFillingsTab(){
        driver.findElement(fillingsTab).click();
    }
    //ожидание загрузки страницы
    public void waitForMainPageLoad(){
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(checkoutButton));
    }
    //Поиск кнопки 'Оформить заказ'
    public String findCheckoutButton(){
        waitForMainPageLoad();
        return driver.findElement(checkoutButton).getText();
    }

}
