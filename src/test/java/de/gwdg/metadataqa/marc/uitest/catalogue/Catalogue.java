package de.gwdg.metadataqa.marc.uitest.catalogue;

import java.util.List;
import java.util.Map;

public interface Catalogue {
    int getRecordCount();
    String getLibraryName();

    String getLibraryUrl();
    Completeness getCompleteness();
    Validation getValidation();
}
