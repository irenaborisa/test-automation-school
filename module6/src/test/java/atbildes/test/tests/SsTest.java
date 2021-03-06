package atbildes.test.tests;

import atbildes.test.enums.Languages;
import atbildes.test.pageobjects.SearchPage;
import atbildes.test.pageobjects.SearchResults;
import atbildes.test.pageobjects.TopMenuBar;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static atbildes.test.enums.Languages.LV;
import static atbildes.test.enums.Languages.RU;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SsTest {

    WebDriver driver;
    TopMenuBar topMenuBar;
    SearchPage searchPage;
    SearchResults searchResults;

    @BeforeMethod
    public void setUp() {

        System.setProperty("webdriver.chrome.driver", "../drivers\\chromedriver_win32\\chromedriver.exe");
        //C:\Users\irena.borisa\Desktop\selenium\test-automation-school\drivers\chromedriver_win32\chromedriver.exe
        driver = new ChromeDriver();

        driver.get("http://ss.com");
      //  driver.manage().window().maximize();
    }


    @AfterMethod
    public void tearDown() throws Exception {

        driver.quit();
    }


    @Test
    public void testChangeLanguage() throws Exception {
        topMenuBar = new TopMenuBar(driver);
        topMenuBar.changeLanguage(LV);
        assertEquals(topMenuBar.getCurrentLanguage(),LV);
    }

    @Test
    public void testSearch(){
        topMenuBar = new TopMenuBar(driver);
        topMenuBar.changeLanguage(RU);
        topMenuBar.navigateToSearchPage();
        searchPage = new SearchPage(driver);
        searchPage.searchForPhrase("auto");
        searchPage.performSearch();
        searchResults = new SearchResults(driver);
        assertEquals(searchResults.getSearchResultElementCount(),30);
    }

}
