/**
 *
 */
package com.bigdata.datalake.utils;

import com.cloudera.sqoop.SqoopOptions;
import org.apache.sqoop.tool.SqoopTool;

import java.util.List;

/**
 * @author vsnair
 */
public class DataLakeWorker implements Runnable {

    private SqoopTool sqoopTool = null;
    private List<SqoopOptions> details = null;

    public DataLakeWorker(SqoopTool sqoopTool, List<SqoopOptions> details) {
        super();
        this.sqoopTool = sqoopTool;
        this.details = details;
    }

    @Override
    public void run() {

        for (SqoopOptions sqoopOption : details) {
            this.sqoopTool.run(sqoopOption);
        }
    }

}
