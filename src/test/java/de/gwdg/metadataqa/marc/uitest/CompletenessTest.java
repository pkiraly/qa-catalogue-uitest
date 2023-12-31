package de.gwdg.metadataqa.marc.uitest;

import de.gwdg.metadataqa.marc.uitest.catalogue.FieldGroup;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CompletenessTest extends QAPageTest {

    @Test
    public void testLabels() {
        driver.get(baseUrl + "?tab=completeness&lang=en");

        WebElement definition = driver.findElement(By.cssSelector(".metric-definition"));
        assertTrue(definition.getText().startsWith("Which fields and subfields occur how often in which records"));

        if (catalogue != null) {
            WebElement docTypes = driver.findElement(By.id("document-type-selector"));
            assertEquals(catalogue.getCompleteness().getDocTypes().getEn(), docTypes.getText());

            WebElement nameEl = driver.findElement(By.cssSelector("div.col-md-9 a"));
            assertEquals(catalogue.getLibraryName(), nameEl.getText());
            assertEquals(catalogue.getLibraryUrl(), nameEl.getAttribute("href"));
        }
    }
    @Test
    public void testCount() {
        driver.get(baseUrl + "?tab=completeness&lang=en");

        if (catalogue != null) {
            WebElement counter = driver.findElement(By.cssSelector(".header-info strong"));
            assertEquals(catalogue.getRecordCount(), Integer.parseInt(counter.getText().replaceAll("\\D", "")));
        }
    }

    @Test
    void fieldGroupTable() {
        driver.get(baseUrl + "?tab=completeness&lang=en");

        WebElement groupLabel;
        WebElement groupCount;
        if (catalogue != null) {
            for (FieldGroup fieldGroup : catalogue.getCompleteness().getFieldGroups()) {
                String row = "div#completeness-group-table table tbody tr:nth-child(" + (fieldGroup.getId() + 1) + ")";
                groupLabel = driver.findElement(By.cssSelector(row + " td:nth-child(2)"));
                groupCount = driver.findElement(By.cssSelector(row + " td:nth-child(6)"));
                assertEquals(fieldGroup.getLabel(), groupLabel.getText());
                assertEquals(fieldGroup.getCount(), Integer.parseInt(groupCount.getText().replaceAll("\\D", "")));
            }
        }
    }

    @Test
    public void testTranslation() {
        driver.get(baseUrl + "?tab=completeness&lang=de");

        WebElement counter = driver.findElement(By.cssSelector(".header-info strong"));
        assertEquals(catalogue.getRecordCount(), Integer.parseInt(counter.getText().replaceAll("\\D", "")));

        WebElement definition = driver.findElement(By.cssSelector(".metric-definition"));
        assertTrue(definition.getText().startsWith("Alle Felder und Unterfelder mit der jeweiligen Anzahl und dem Anteil ihres Vorkommens"));

        if (catalogue != null) {
            WebElement docTypes = driver.findElement(By.id("document-type-selector"));
            assertEquals(catalogue.getCompleteness().getDocTypes().getDe(), docTypes.getText());
        }
    }
}
