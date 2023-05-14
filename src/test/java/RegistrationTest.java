import constants.Constants;
import io.qameta.allure.Step;
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
import pageobjects.RegistrationPage;
import user.User;
import user.UserApi;
import user.UserCredentials;
import user.UserGenerator;

import static constants.Constants.LOGIN_PAGE;

public class RegistrationTest {
    private User user;
    private String userToken;
    private WebDriver driver;

    @Before
    public void setUp() {
        user = UserGenerator.getRandomUser();
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
    }
    @After
    public void teardown() {
        driver.quit();
    }

    @Test
    @DisplayName("Успешная регистрация")
    public void registrationPassed() {
        driver.get(Constants.REGISTER_PAGE);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.registration(user.getName(),user.getEmail(),user.getPassword());
        LoginPage loginPage = new LoginPage(driver);
        loginPage.findLoginButton();
        Assert.assertEquals(LOGIN_PAGE,driver.getCurrentUrl());

        Response response = UserApi.loginUser(new UserCredentials(user.getEmail(), user.getPassword()));
        userToken = response.path("accessToken");
        UserApi.deleteUser(userToken);
    }

    @Test
    @Step("Регистрация с некорректным паролем")
    public void registrationWithWrongPasswordFailed() {
        driver.get(Constants.REGISTER_PAGE);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.registration(user.getName(), user.getEmail(), "pass");
        Assert.assertEquals("Некорректный пароль", registrationPage.getErrorValidation());
    }

}
