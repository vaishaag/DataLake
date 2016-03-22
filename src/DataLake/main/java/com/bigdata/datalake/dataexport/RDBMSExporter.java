/**
 *
 */
package com.bigdata.datalake.dataexport;

import com.bigdata.datalake.jsonobject.SchemaDetails;
import com.bigdata.datalake.jsonobject.TableDetails;
import com.bigdata.datalake.utils.Constants;
import com.bigdata.datalake.utils.DataLakeWorker;
import com.cloudera.sqoop.SqoopOptions;
import com.cloudera.sqoop.SqoopOptions.IncrementalMode;
import org.apache.sqoop.tool.ExportTool;

import java.util.List;

/**
 * @author vsnair
 */
public class RDBMSExporter implements DataExporter {

    private SchemaDetails schemaDetails = null;
    private List<SqoopOptions> list = null;

    public RDBMSExporter(SchemaDetails schemaDetails) {
        super();
        this.schemaDetails = schemaDetails;
    }

    @Override
    public int exportData() {
        try {

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
                    new DataLakeWorker(new ExportTool(), list));
            thread.start();
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

}
