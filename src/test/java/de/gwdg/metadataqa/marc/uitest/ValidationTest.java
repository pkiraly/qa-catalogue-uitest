package de.gwdg.metadataqa.marc.uitest;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidationTest extends QAPageTest {

    @Test
    public void testNumeric() {
        driver.get(baseUrl + "?tab=completeness&lang=en");

        WebElement counter = driver.findElement(By.cssSelector(".header-info strong"));
        assertEquals("18585", counter.getText().replaceAll("\\D", ""));

        WebElement definition = driver.findElement(By.cssSelector(".metric-definition"));
        assertTrue(definition.getText().startsWith("Which fields and subfields occur how often in which records"));

        WebElement docType = driver.findElement(By.xpath("/html/body/div/div[2]/div/div[1]"));
        assertEquals("by document types: all", docType.getText());
    }

}
