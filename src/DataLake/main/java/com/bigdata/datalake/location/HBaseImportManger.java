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
import org.apache.sqoop.tool.ImportTool;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vsnair
 */
public class HBaseImportManger extends DataStoreManager {

    private SchemaDetails schemaDetails = null;

    @Override
    public int storeData(SchemaDetails details) {

        importTableDataIntoHBASE(details);
        return 0;
    }

    public int importTableDataIntoHBASE(SchemaDetails details) {

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
                options.setHBaseBulkLoadEnabled(true);
                options.setHBaseColFamily("");// TODO
                options.setHBaseTable("");// TODO
                options.setHBaseRowKeyColumn("");// TODO
                options.setCreateHBaseTable(true);// TODO
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

}
