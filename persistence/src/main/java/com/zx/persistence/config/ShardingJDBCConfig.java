package com.zx.persistence.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.dangdang.ddframe.rdb.sharding.api.ShardingDataSourceFactory;
import com.dangdang.ddframe.rdb.sharding.api.rule.DataSourceRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.ShardingRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.TableRule;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.DatabaseShardingStrategy;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.TableShardingStrategy;
import com.zx.persistence.config.algorithm.SimpleDatabaseShardingAlgorithm;
import com.zx.persistence.config.algorithm.SimpleTableShardingAlgorithm;
import com.zx.persistence.dto.SQLDataSourceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AlternativeJdkIdGenerator;
import org.springframework.util.IdGenerator;

import javax.sql.DataSource;
import java.util.*;

@Configuration
public class ShardingJDBCConfig {

    @Autowired
    private SQLDataSourceDTO sqlDataSourceDTO;

    @Autowired
    SimpleTableShardingAlgorithm simpleTableShardingAlgorithm;

    @Autowired
    SimpleDatabaseShardingAlgorithm simpleDatabaseShardingAlgorithm;

    @Bean
    public IdGenerator getIdGenerator() {
        return new AlternativeJdkIdGenerator();
    }

    @Bean
    public DataSource getDataSource() {
        return buildDataSource();
    }

    private DataSource buildDataSource() {
        //设置分库映射
        Map<String, DataSource> dataSourceMap = new HashMap<>(sqlDataSourceDTO.getDatasource_num());
        //添加两个数据库ds_0,ds_1到map里
        sqlDataSourceDTO.getDbconfig().forEach((k,v)->
            dataSourceMap.put(v.get("data_name"), createDataSource(v.get("driver_class"),v.get("url"),v.get("user_name"),v.get("password")))
        );

        //设置默认db为ds_0，也就是为那些没有配置分库分表策略的指定的默认库
        //如果只有一个库，也就是不需要分库的话，map里只放一个映射就行了，只有一个库时不需要指定默认库，但2个及以上时必须指定默认库，否则那些没有配置策略的表将无法操作数据
        DataSourceRule dataSourceRule = new DataSourceRule(dataSourceMap, sqlDataSourceDTO.getDefault_data());

        //设置分表映射，将t_order_0和t_order_1两个实际的表映射到t_order逻辑表
        //0和1两个表是真实的表，t_order是个虚拟不存在的表，只是供使用。如查询所有数据就是select * from t_order就能查完0和1表的
        String [] tableNames = sqlDataSourceDTO.getTable_names().split(",");
        Set<TableRule> tableRules = new HashSet<>();
        for (String tableName :
                tableNames) {
            List<String> names = new ArrayList<>();
            for (int i = 0;i < sqlDataSourceDTO.getTable_num(); i++){
                names.add(tableName+"_"+i);
            }
            TableRule orderTableRule = TableRule.builder(tableName)
                    .actualTables(names)
                    .dataSourceRule(dataSourceRule)
                    .build();
            tableRules.add(orderTableRule);
        }
        //具体分库分表策略，按什么规则来分
        ShardingRule shardingRule = ShardingRule.builder()
                .dataSourceRule(dataSourceRule)
                .tableRules(tableRules)
                .tableShardingStrategy(new TableShardingStrategy(sqlDataSourceDTO.getTable_key(), simpleTableShardingAlgorithm))
                .databaseShardingStrategy(new DatabaseShardingStrategy(sqlDataSourceDTO.getTable_key(), simpleDatabaseShardingAlgorithm))
                .build();
        DataSource dataSource = null;
        try {
            dataSource = ShardingDataSourceFactory.createDataSource(shardingRule);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataSource;
    }


    private static DataSource createDataSource(String driverClass, String url, String userName, String password) {
        DruidDataSource result = new DruidDataSource();
        result.setDriverClassName(driverClass);
        result.setUrl(url);
        result.setUsername(userName);
        result.setPassword(password);
        return result;
    }
}
