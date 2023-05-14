import constants.Constants;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageobjects.MainPage;

public class ConstructorTest {
    private WebDriver driver;
    @Before
    public void setUp(){
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
    }
    @After
    public void teardown() {
        driver.quit();
    }
    //Не догадался как сделать лаконично в один тест с переключением вкладок, поэтому написал отдельно три теста
    //Проблема была в переходе с активной вкладки
    @Test
    @DisplayName("Проверка переходов на вкладку 'Соусы'")
    public void goToSauceSectionPassed() {
        driver.get(Constants.MAIN_PAGE);
        MainPage mainPage = new MainPage(driver);
        mainPage.clickSauceTab();
        Assert.assertEquals("Соусы",mainPage.activeTab());
    }
    @Test
    @DisplayName("Проверка переходов на вкладку 'Начинки'")
    public void goToFillingSectionPassed() {
        driver.get(Constants.MAIN_PAGE);
        MainPage mainPage = new MainPage(driver);
        mainPage.clickFillingsTab();
        Assert.assertEquals("Начинки",mainPage.activeTab());
    }
    @Test
    @DisplayName("Проверка переходов на вкладку 'Булки'")
    public void goToBunsSectionPassed() {
        driver.get(Constants.MAIN_PAGE);
        MainPage mainPage = new MainPage(driver);
        mainPage.clickSauceTab();
        mainPage.clickBunsTab();
        Assert.assertEquals("Булки",mainPage.activeTab());
    }
}
