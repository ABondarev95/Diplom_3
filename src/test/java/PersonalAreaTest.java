import constants.Constants;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageobjects.LoginPage;
import pageobjects.MainPage;
import pageobjects.PersonalAreaPage;
import user.User;
import user.UserApi;
import user.UserGenerator;

import static constants.Constants.LOGIN_PAGE;
import static constants.Constants.PERSONAL_AREA_PAGE;

public class PersonalAreaTest {
    private User user;
    private String userToken;
    private WebDriver driver;

    @Before
    public void setUp() {
        user = UserGenerator.getRandomUser();
        Response createUserResponse = UserApi.createUser(user);
        createUserResponse.then().statusCode(200);
        userToken = createUserResponse.path("accessToken");

        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
    }
    @After
    public void teardown() {
        UserApi.deleteUser(userToken);
        driver.quit();
    }
    @Test
    @DisplayName("Переход в Личный кабинет")
    public void loginToAccountPassed() {
        driver.get(Constants.MAIN_PAGE);
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalArea();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user.getEmail(), user.getPassword());
        mainPage.waitForMainPageLoad();
        mainPage.clickPersonalArea();
        PersonalAreaPage personalAreaPage = new PersonalAreaPage(driver);
        personalAreaPage.waitForPageLoad();
        Assert.assertEquals(PERSONAL_AREA_PAGE,driver.getCurrentUrl());
    }
    @Test
    @DisplayName("Переход из личного кабинета по кнопке 'Конструктор'")
    public void goFromAccountByConstructorButtonPassed() {
        driver.get(Constants.MAIN_PAGE);
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalArea();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user.getEmail(), user.getPassword());
        mainPage.waitForMainPageLoad();
        mainPage.clickPersonalArea();
        PersonalAreaPage personalAreaPage = new PersonalAreaPage(driver);
        personalAreaPage.waitForPageLoad();
        personalAreaPage.clickConstructor();
        Assert.assertEquals("Оформить заказ",mainPage.findCheckoutButton());
    }
    @Test
    @DisplayName("Переход из личного кабинета по логотипу 'Stellar Burgers'")
    public void goFromAccountByLogotypePassed() {
        driver.get(Constants.MAIN_PAGE);
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalArea();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user.getEmail(), user.getPassword());
        mainPage.waitForMainPageLoad();
        mainPage.clickPersonalArea();
        PersonalAreaPage personalAreaPage = new PersonalAreaPage(driver);
        personalAreaPage.waitForPageLoad();
        personalAreaPage.clickLogo();
        Assert.assertEquals("Оформить заказ",mainPage.findCheckoutButton());
    }
    @Test
    @DisplayName("Выход из аккаунта")
    public void logoutFromAccountPassed() {
        driver.get(Constants.MAIN_PAGE);
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalArea();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user.getEmail(), user.getPassword());
        mainPage.waitForMainPageLoad();
        mainPage.clickPersonalArea();
        PersonalAreaPage personalAreaPage = new PersonalAreaPage(driver);
        personalAreaPage.waitForPageLoad();
        personalAreaPage.clickLogoutButton();
        loginPage.findLoginButton();
        Assert.assertEquals(LOGIN_PAGE,driver.getCurrentUrl());
    }
}
