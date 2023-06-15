package de.gwdg.metadataqa.marc.uitest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CompletenessTest extends QAPageTest {

    @Test
    public void testNumeric() {
        driver.get(baseUrl + "?tab=completeness&lang=en");

        // Find the text input element by its name
        WebElement counter = driver.findElement(By.cssSelector(".header-info strong"));
        assertEquals("18585", counter.getText().replaceAll("\\D", ""));

        WebElement definition = driver.findElement(By.cssSelector(".metric-definition"));
        assertTrue(definition.getText().startsWith("Which fields and subfields occur how often in which records"));

        WebElement docType = driver.findElement(By.xpath("/html/body/div/div[2]/div/div[1]"));
        assertEquals("by document types: all", docType.getText());

        // Enter something to search for
        // element.sendKeys("Cheese!");

        // Now submit the form
        // element.submit();
    }

    @Test
    void fieldGroupTable() {
        driver.get(baseUrl + "?tab=completeness&lang=en");

        WebElement groupLabel;
        WebElement groupCount;
        groupLabel = driver.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/table/tbody/tr[2]/td[2]"));
        assertEquals("Control Fields", groupLabel.getText());
        groupCount = driver.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/table/tbody/tr[2]/td[4]"));
        assertEquals("18,585", groupCount.getText());

        groupLabel = driver.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/table/tbody/tr[4]/td[2]"));
        assertEquals("Main Entry", groupLabel.getText());
        groupCount = driver.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/table/tbody/tr[4]/td[4]"));
        assertEquals("15,476", groupCount.getText());
    }

    @Test
    public void testTranslation() {
        driver.get(baseUrl + "?tab=completeness&lang=de");

        // Find the text input element by its name
        WebElement counter = driver.findElement(By.cssSelector(".header-info strong"));
        assertEquals("18585", counter.getText().replaceAll("\\D", ""));

        WebElement definition = driver.findElement(By.cssSelector(".metric-definition"));
        assertTrue(definition.getText().startsWith("Alle Felder und Unterfelder mit der jeweiligen Anzahl und dem Anteil ihres Vorkommens"));

        WebElement docType = driver.findElement(By.xpath("/html/body/div/div[2]/div/div[1]"));
        assertEquals("Dokumenttyp: all", docType.getText());


        // Enter something to search for
        // element.sendKeys("Cheese!");

        // Now submit the form
        // element.submit();
    }

    @AfterEach
    public void closeBrowser(){
        driver.close();
    }
}
