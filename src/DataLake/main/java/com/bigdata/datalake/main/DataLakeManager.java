/**
 *
 */
package com.bigdata.datalake.main;

import com.bigdata.datalake.dataexport.RDBMSExporter;
import com.bigdata.datalake.datasource.RDBMSImporter;
import com.bigdata.datalake.jsonobject.SchemaDetails;
import com.bigdata.datalake.location.DataStoreManager;
import com.bigdata.datalake.location.HBaseImportManger;
import com.bigdata.datalake.location.HDFSImportManager;
import com.bigdata.datalake.location.HiveImportManager;
import com.bigdata.datalake.utils.Constants;
import com.bigdata.datalake.utils.Utililty;

import java.io.File;

/**
 * @author vsnair
 */
public class DataLakeManager {

    public static void main(String[] args) {

        System.out.println("Starting Data Lake..." + args[0]);
        File file = new File(args[0]);
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File configFile : files) {
                if (configFile.getName().contains(Constants.JSON_EXTENSION)
                        || configFile.getName().contains(Constants.JSON_EXTENSION_UPPER)) {
                    SchemaDetails schemaDetails = (SchemaDetails) Utililty.createJsonToJavaObject(configFile);
                    performOperations(schemaDetails);
                }
            }
        }
    }

    public static void performOperations(SchemaDetails schemaDetails) {

        String operationType = schemaDetails.getOperationType();
        DataStoreManager dataStoreManager = null;

        if (Constants.DATA_IMPORT.equals(operationType)) {

            dataStoreManager = new HDFSImportManager();
            RDBMSImporter importer = new RDBMSImporter(schemaDetails, dataStoreManager);
            int flag = importer.importData();
            if (flag == 0) {
                System.out.println("Data Import Successfully Completed..");
            } else {
                System.out.println("Data Import Failed..");
            }
        } else if (Constants.DATA_EXPORT.equals(operationType)) {

            RDBMSExporter exporter = new RDBMSExporter(schemaDetails);
            int flag = exporter.exportData();
            if (flag == 0) {
                System.out.println("Data Import Successfully Completed..");
            } else {
                System.out.println("Data Import Failed..");
            }

        } else if (Constants.IMPORT_ALL_TABLES.equals(operationType)) {

            dataStoreManager = new HDFSImportManager();
            RDBMSImporter importer = new RDBMSImporter(schemaDetails, dataStoreManager);
            int flag = importer.importData();
            if (flag == 0) {
                System.out.println("Data Import Successfully Completed..");
            } else {
                System.out.println("Data Import Failed..");
            }
        } else if (Constants.IMPORT_DATA_INTO_HIVE.equals(operationType)) {

            dataStoreManager = new HiveImportManager();
            RDBMSImporter importer = new RDBMSImporter(schemaDetails, dataStoreManager);
            int flag = importer.importData();
            if (flag == 0) {
                System.out.println("Data Import Successfully Completed..");
            } else {
                System.out.println("Data Import Failed..");
            }
        } else if (Constants.IMPORT_DATA_INTO_HBASE.equals(operationType)) {

            dataStoreManager = new HBaseImportManger();
            RDBMSImporter importer = new RDBMSImporter(schemaDetails, dataStoreManager);
            int flag = importer.importData();
            if (flag == 0) {
                System.out.println("Data Import Successfully Completed..");
            } else {
                System.out.println("Data Import Failed..");
            }
        }
    }
}
