/**
 *
 */
package com.bigdata.datalake.jsonobject;

/**
 * @author vsnair
 */
public class TableDetails implements JsonObject {

    private String tableName = null;
    private String columns = null;
    private String incrementalKey = null;
    private String splitbyKey = null;
    private String incrementalMode = null;
    private String fullLoad = null;
    private String primaryKey = null;
    private String hdfsPath = null;
    private char delimiter = ',';
    private String query = null;
    private String nullNonStringValue = null;
    private String nullStringValue = null;
    private String numberOfMappers = null;

    public String getNumberOfMappers() {
        return numberOfMappers;
    }

    public void setNumberOfMappers(String numberOfMappers) {
        this.numberOfMappers = numberOfMappers;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColumns() {
        return columns;
    }

    public void setColumns(String columns) {
        this.columns = columns;
    }

    public String getIncrementalKey() {
        return incrementalKey;
    }

    public void setIncrementalKey(String incrementalKey) {
        this.incrementalKey = incrementalKey;
    }

    public String getSplitbyKey() {
        return splitbyKey;
    }

    public void setSplitbyKey(String splitbyKey) {
        this.splitbyKey = splitbyKey;
    }

    public String getIncrementalMode() {
        return incrementalMode;
    }

    public void setIncrementalMode(String incrementalMode) {
        this.incrementalMode = incrementalMode;
    }

    public String getFullLoad() {
        return fullLoad;
    }

    public void setFullLoad(String fullLoad) {
        this.fullLoad = fullLoad;
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getHdfsPath() {
        return hdfsPath;
    }

    public void setHdfsPath(String hdfsPath) {
        this.hdfsPath = hdfsPath;
    }

    public char getDelimter() {
        return delimiter;
    }

    public void setDelimter(char delimter) {
        this.delimiter = delimter;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getNullNonStringValue() {
        return nullNonStringValue;
    }

    public void setNullNonStringValue(String nullNonStringValue) {
        this.nullNonStringValue = nullNonStringValue;
    }

    public String getNullStringValue() {
        return nullStringValue;
    }

    public void setNullStringValue(String nullStringValue) {
        this.nullStringValue = nullStringValue;
    }

}
