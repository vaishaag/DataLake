/**
 *
 */
package com.bigdata.datalake.datasource;

import com.bigdata.datalake.dataimport.DataImporter;
import com.bigdata.datalake.jsonobject.SchemaDetails;
import com.bigdata.datalake.location.DataStoreManager;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author 1078847
 */
public class RDBMSImporter implements DataImporter {

    private SchemaDetails schemaDetails = null;
    private DataStoreManager dataStoreManager = null;

    public RDBMSImporter(SchemaDetails schemaDetails, DataStoreManager dataStoreManager) {
        this.schemaDetails = schemaDetails;
        this.dataStoreManager = dataStoreManager;
    }

    public static Properties getPropertyValues() {

        Properties prop = null;
        try {
            prop = new Properties();
            String propName = "./config/import_properties.properties";
            InputStream inputStream = new FileInputStream(propName);
            if (null != inputStream) {
                prop.load(inputStream);
            }
        } catch (Exception e) {
            System.out
                    .println("Error Occurred During Loading Of Property File : "
                            + e.getMessage());
        }
        return prop;
    }

    @Override
    public int importData() {
        dataStoreManager.storeData(schemaDetails);
        return 0;
    }

}
