/**
 *
 */
package com.bigdata.datalake.jsonobject;

/**
 * @author vsnair
 */
public class SchemaDetails implements JsonObject {

    private String connectionUrl = null;
    private String userName = null;
    private String password = null;
    private String application = null;
    private String schema = null;
    private String driverClass = null;
    private String operationType = null;

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    private TableDetails[] tableDetails = null;

    public String getConnectionUrl() {
        return connectionUrl;
    }

    public void setConnectionUrl(String connectionUrl) {
        this.connectionUrl = connectionUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public TableDetails[] getTableDetails() {
        return tableDetails;
    }

    public void setTableDetails(TableDetails[] tableDetails) {
        this.tableDetails = tableDetails;
    }

    public String getDriverClass() {
        return driverClass;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }
}
