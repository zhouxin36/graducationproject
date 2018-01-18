package com.zx.persistence.config.algorithm;

import com.zx.persistence.dto.SQLDataSourceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShardingTableRule {

    @Autowired
    private SQLDataSourceDTO sqlDataSourceDTO;

    /**
     * 获取UserID所在的分表
     * 策略：截取后4位（转十进制） % 表个数  作为分表的后缀
     *
     * @param userId 用户ID
     * @return
     */
    public int getTableId(String userId) {
        String keyStr = userId.substring(userId.length() - sqlDataSourceDTO.getKey_length());
        return Integer.parseInt(keyStr, 16) % sqlDataSourceDTO.getTable_num();
    }

    public int getDataId(String userId) {
        String keyStr = userId.substring(0, sqlDataSourceDTO.getKey_length());
        return Integer.parseInt(keyStr, 16) % sqlDataSourceDTO.getDatasource_num();
    }
}
