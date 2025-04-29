package de.gwdg.metadataqa.marc.uitest;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidationTest extends QAPageTest {

    private String tab = "?tab=issues&lang=en";

    @Test
    public void testNumeric() {
        driver.get(baseUrl + tab);

        WebElement counter = driver.findElement(By.cssSelector(".header-info strong:first-child"));
        assertEquals(catalogue.getRecordCount(), Integer.parseInt(counter.getText().replaceAll("\\D", "")));

        WebElement definition = driver.findElement(By.cssSelector(".metric-definition"));
        assertTrue(definition.getText().startsWith(catalogue.getValidation().getDefinition()));
    }

    @Test
    public void recordsWithoutIssues_plan() {
        if (catalogue != null) {
            driver.get(baseUrl + tab);

            WebElement counter = driver.findElement(By.xpath("//div[@id='issues-table-placeholder']/div[@class='row'][2]/div[1]"));
            assertEquals(catalogue.getValidation().getRecordsWithoutIssues().getPlain(), counter.getText().trim());
        }
    }

    @Test
    public void recordsWithoutIssues_exclude() {
        if (catalogue != null) {
            driver.get(baseUrl + tab);

            WebElement counter = driver.findElement(By.xpath("//div[@id='issues-table-placeholder']/div[@class='row'][4]/div[1]"));
            assertEquals(catalogue.getValidation().getRecordsWithoutIssues().getExclude(), counter.getText().trim());
        }
    }
}
