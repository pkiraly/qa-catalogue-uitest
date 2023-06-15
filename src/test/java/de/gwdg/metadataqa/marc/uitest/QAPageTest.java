package de.gwdg.metadataqa.marc.uitest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class QAPageTest {

    protected static String webDriver = "chrome";
    protected WebDriver driver;
    protected String baseUrl;

    @BeforeEach
    public void setupData() {
        if (System.getProperties().containsKey("baseUrl")) {
            baseUrl = System.getProperty("baseUrl");
        } else {
            baseUrl = "http://localhost:90/qa-catalogue/";
        }

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }
}
