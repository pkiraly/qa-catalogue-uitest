package de.gwdg.metadataqa.marc.uitest.catalogue;

import de.gwdg.metadataqa.marc.uitest.catalogue.completeness.DocTypes;

import java.util.List;

public class Completeness {
    private List<FieldGroup> fieldGroups;
    private DocTypes docTypes;

    public List<FieldGroup> getFieldGroups() {
        return fieldGroups;
    }

    public void setFieldGroups(List<FieldGroup> fieldGroups) {
        this.fieldGroups = fieldGroups;
    }

    public DocTypes getDocTypes() {
        return docTypes;
    }

    public void setDocTypes(DocTypes docTypes) {
        this.docTypes = docTypes;
    }
}
