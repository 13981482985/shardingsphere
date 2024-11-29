package com.yang.shardingsphere.config;

import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.RangeShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm;
import org.apache.shardingsphere.sharding.spi.ShardingAlgorithm;

import java.util.Collection;
import java.util.Properties;

public final class CustomTableShardingAlgorithm implements StandardShardingAlgorithm<Comparable<?>> {

    @Override
    public String doSharding(final Collection<String> availableTargetNames, final PreciseShardingValue<Comparable<?>> shardingValue) {
        long userId = Long.parseLong(shardingValue.getValue().toString());
        String databaseName = shardingValue.getLogicTableName(); // 确保传入逻辑表名
        int tableCount = databaseName.equals("ds_0") ? 5 : 3;
        String suffix = String.valueOf(userId % tableCount + 1);
        return availableTargetNames.stream()
                .filter(target -> target.endsWith(suffix))
                .findFirst()
                .orElseThrow(() -> new UnsupportedOperationException("No matching table found."));
    }


    /**
     * ShardingSphere 的标准分片接口中的方法之一，主要用于处理范围查询（如 BETWEEN 或 >, < 等 SQL 语句）。如果你不实现这个方法，就会导致分片功能在范围查询时出现问题。
     * @param availableTargetNames
     * @param rangeShardingValue
     * @return
     */
    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, RangeShardingValue<Comparable<?>> rangeShardingValue) {
        // 对于范围查询，默认返回所有可能的目标表
        return availableTargetNames;
    }

    @Override
    public void init(Properties props) {}

    @Override
    public String getType() {
        return "CUSTOM_TABLE_ALGORITHM";
    }
}
