package com.zx.persistence.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@ConfigurationProperties(prefix="db")
public class SQLDataSourceDTO {
    private Map<String,Map<String,String>> dbconfig = new HashMap<>();
    private String main_data;
    private String default_data;
    private String table_names;
    private String data_name;
    private String table_key;
    private Integer datasource_num;
    private Integer table_num;
    private Integer key_length;

    public String getTable_names() {
        return table_names;
    }

    public void setTable_names(String table_names) {
        this.table_names = table_names;
    }

    public String getData_name() {
        return data_name;
    }

    public void setData_name(String data_name) {
        this.data_name = data_name;
    }

    public String getTable_key() {
        return table_key;
    }

    public void setTable_key(String table_key) {
        this.table_key = table_key;
    }

    public Integer getDatasource_num() {
        return datasource_num;
    }

    public void setDatasource_num(Integer datasource_num) {
        this.datasource_num = datasource_num;
    }

    public Integer getTable_num() {
        return table_num;
    }

    public void setTable_num(Integer table_num) {
        this.table_num = table_num;
    }

    public Integer getKey_length() {
        return key_length;
    }

    public void setKey_length(Integer key_length) {
        this.key_length = key_length;
    }

    public String getDefault_data() {
        return default_data;
    }

    public void setDefault_data(String default_data) {
        this.default_data = default_data;
    }

    public String getMain_data() {
        return main_data;
    }

    public void setMain_data(String main_data) {
        this.main_data = main_data;
    }

    public Map<String, Map<String, String>> getDbconfig() {
        return dbconfig;
    }

    public void setDbconfig(Map<String, Map<String, String>> dbconfig) {
        this.dbconfig = dbconfig;
    }

    @Override
    public String toString() {
        return "SQLDataSourceDTO{" +
                "dbconfig=" + dbconfig +
                ", main_data='" + main_data + '\'' +
                ", default_data='" + default_data + '\'' +
                ", table_names='" + table_names + '\'' +
                ", data_name='" + data_name + '\'' +
                ", table_key='" + table_key + '\'' +
                ", datasource_num=" + datasource_num +
                ", table_num=" + table_num +
                ", key_length=" + key_length +
                '}';
    }
}
