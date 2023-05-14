package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PersonalAreaPage {
    private final WebDriver driver;

    //локатор кнопки "Выход"
    private final By logoutButton = By.xpath(".//button[text()='Выход']");
    //локатор кнопки "Конструктор"
    private final By constructorButton = By.xpath(".//p[text()='Конструктор']");
    //локатор логотипа 'Stellar Burgers'
    private final By stellarBurgersLogo = By.className("AppHeader_header__logo__2D0X2");
    //локатор текста 'В этом разделе вы можете изменить свои персональные данные'
    private final By accountText = By.xpath(".//p[text()='В этом разделе вы можете изменить свои персональные данные']");

    public PersonalAreaPage(WebDriver driver) {
        this.driver = driver;
    }
    //выход в личном кабинете
    public void clickLogoutButton(){
        driver.findElement(logoutButton).click();
    }
    //нажатие на логотип 'Stellar Burgers'
    public void clickLogo(){
        driver.findElement(stellarBurgersLogo).click();
    }
    //нажатие на 'Конструктор' в личном кабинете
    public void clickConstructor(){
        driver.findElement(constructorButton).click();
    }
    //ожидание прогрузки страницы и поиск текста
    public void waitForPageLoad(){
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(accountText));
    }
}
