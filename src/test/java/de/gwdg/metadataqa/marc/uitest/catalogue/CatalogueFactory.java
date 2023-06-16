package de.gwdg.metadataqa.marc.uitest.catalogue;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CatalogueFactory {

    public static Catalogue readConfig(String fileName) throws FileNotFoundException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(fileName);

        try {
            CatalogueDao catalogue = objectMapper.readValue(file, CatalogueDao.class);
            return catalogue;
        } catch (IOException var6) {
            throw new FileNotFoundException(var6.getMessage());
        }
    }

}
