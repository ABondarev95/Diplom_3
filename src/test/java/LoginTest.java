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
import pageobjects.RegistrationPage;
import user.User;
import user.UserApi;
import user.UserGenerator;

public class LoginTest {
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
    @DisplayName("Авторизация по кнопке 'Войти в аккаунт' на главной странице")
    public void loginToAccountPassed() {
        driver.get(Constants.MAIN_PAGE);
        MainPage mainPage = new MainPage(driver);
        mainPage.clickSignIn();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user.getEmail(), user.getPassword());
        Assert.assertEquals("Оформить заказ",mainPage.findCheckoutButton());
    }
    @Test
    @DisplayName("Авторизация через 'Личный кабинет' на главной странице")
    public void loginToPersonalAccountPassed() {
        driver.get(Constants.MAIN_PAGE);
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalArea();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user.getEmail(), user.getPassword());
        Assert.assertEquals("Оформить заказ",mainPage.findCheckoutButton());
    }
    @Test
    @DisplayName("Авторизация через форму регистрации")
    public void loginFromRegisterFormPassed() {
        driver.get(Constants.REGISTER_PAGE);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.clickLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user.getEmail(), user.getPassword());
        MainPage mainPage = new MainPage(driver);
        Assert.assertEquals("Оформить заказ",mainPage.findCheckoutButton());
    }

}
