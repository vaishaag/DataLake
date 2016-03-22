/**
 *
 */
package com.bigdata.datalake.location;

import com.bigdata.datalake.jsonobject.SchemaDetails;
import com.bigdata.datalake.jsonobject.TableDetails;
import com.bigdata.datalake.utils.Constants;
import com.bigdata.datalake.utils.DataLakeWorker;
import com.cloudera.sqoop.SqoopOptions;
import com.cloudera.sqoop.SqoopOptions.IncrementalMode;
import org.apache.sqoop.tool.ImportAllTablesTool;
import org.apache.sqoop.tool.ImportTool;
import org.apache.sqoop.tool.SqoopTool;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vsnair
 */
public class HDFSImportManager extends DataStoreManager {

    private SchemaDetails schemaDetails = null;

    @Override
    public int storeData(SchemaDetails schemaDetails) {

        if (null != schemaDetails.getTableDetails()) {
            importTableData(schemaDetails);
        } else {
            importAllTables(schemaDetails);
        }
        return 0;
    }

    private int importTableData(SchemaDetails schemaDetails) {

        try {
            List<SqoopOptions> list = new ArrayList<SqoopOptions>();
            for (TableDetails tableDetails : schemaDetails.getTableDetails()) {
                SqoopOptions options = new SqoopOptions();
                options.setConnectString(schemaDetails.getConnectionUrl());
                options.setTableName(
                        schemaDetails.getSchema() + Constants.DOT + tableDetails.getTableName());
                options.setColumns(tableDetails.getColumns().split(Constants.COMMA));
                options.setUsername(schemaDetails.getUserName());
                options.setPassword(schemaDetails.getPassword());
                options.setTargetDir(tableDetails.getHdfsPath());
                options.setSplitByCol(tableDetails.getSplitbyKey());
                options.setDriverClassName(schemaDetails.getDriverClass());
                options.setFieldsTerminatedBy(tableDetails.getDelimter());
                options.setNullNonStringValue(tableDetails.getNullNonStringValue());
                options.setNullStringValue(tableDetails.getNullStringValue());
                options.setSqlQuery(tableDetails.getQuery());
                if (!Constants.YES.equals(tableDetails.getFullLoad())) {
                    options.setIncrementalTestColumn(tableDetails.getIncrementalKey());
                    options.setIncrementalMode(IncrementalMode.valueOf(tableDetails.getIncrementalKey()));
                }
                list.add(options);
            }

            Thread thread = new Thread(
                    new DataLakeWorker(new ImportTool(), list));
            thread.start();
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }

    }

    private int importAllTables(SchemaDetails schemaDetails) {
        SqoopOptions options = new SqoopOptions();
        options.setConnectString(this.schemaDetails.getConnectionUrl());
        options.setUsername(this.schemaDetails.getUserName());
        options.setPassword(this.schemaDetails.getPassword());
        options.setDriverClassName(this.schemaDetails.getDriverClass());
        List<SqoopOptions> list = new ArrayList<SqoopOptions>();
        SqoopTool sqoopTool = new ImportAllTablesTool();
        Thread thread = new Thread(
                new DataLakeWorker(sqoopTool, list));
        thread.start();
        return 0;
    }

}
