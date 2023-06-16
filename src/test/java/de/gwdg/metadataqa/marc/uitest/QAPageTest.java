package de.gwdg.metadataqa.marc.uitest;

import de.gwdg.metadataqa.marc.uitest.catalogue.Catalogue;
import de.gwdg.metadataqa.marc.uitest.catalogue.CatalogueFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileNotFoundException;

public class QAPageTest {

    protected static String webDriver = "chrome";
    protected WebDriver driver;
    protected String baseUrl;
    protected Catalogue catalogue = null;

    @BeforeEach
    public void setupData() {
        if (System.getProperties().containsKey("baseUrl")) {
            baseUrl = System.getProperty("baseUrl");
        } else {
            baseUrl = "http://localhost:90/qa-catalogue/";
        }

        if (System.getProperties().containsKey("catalogue-config")) {
            try {
                catalogue = CatalogueFactory.readConfig(System.getProperty("catalogue-config"));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @AfterEach
    public void closeBrowser(){
        driver.close();
    }

}
