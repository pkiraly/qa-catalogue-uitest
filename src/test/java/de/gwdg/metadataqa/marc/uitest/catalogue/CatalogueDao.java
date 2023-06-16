package de.gwdg.metadataqa.marc.uitest.catalogue;

import java.util.List;
import java.util.Map;

public class CatalogueDao implements Catalogue {
    private int recordCount;
    private String libraryName;
    private String libraryUrl;
    private Completeness completeness;

    @Override
    public int getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }

    @Override
    public String getLibraryName() {
        return libraryName;
    }

    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
    }

    @Override
    public String getLibraryUrl() {
        return libraryUrl;
    }

    public void setLibraryUrl(String libraryUrl) {
        this.libraryUrl = libraryUrl;
    }

    @Override
    public Completeness getCompleteness() {
        return completeness;
    }

    public void setCompleteness(Completeness completeness) {
        this.completeness = completeness;
    }
}
