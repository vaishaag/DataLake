/**
 *
 */
package com.bigdata.datalake.datasource;

import com.bigdata.datalake.dataimport.DataImporter;

/**
 * @author vsnair
 */
public class DocumentImporter implements DataImporter {

    public void documentImport() {

    }

    public static void main(String[] args) {
        DocumentImporter documentImporter = new DocumentImporter();
        documentImporter.documentImport();
    }

    @Override
    public int importData() {

        return 0;
    }
}
