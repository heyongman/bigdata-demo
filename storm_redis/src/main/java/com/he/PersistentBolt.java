package com.he;

import com.google.common.collect.Lists;
import org.apache.storm.jdbc.bolt.JdbcInsertBolt;
import org.apache.storm.jdbc.common.Column;
import org.apache.storm.jdbc.common.ConnectionProvider;
import org.apache.storm.jdbc.common.HikariCPConnectionProvider;
import org.apache.storm.jdbc.mapper.JdbcMapper;
import org.apache.storm.jdbc.mapper.SimpleJdbcMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static javax.swing.UIManager.put;

public class PersistentBolt {
    private static Logger logger = LoggerFactory.getLogger(PersistentBolt.class);

    private static Map<String, Object> hikariConfigMap = new HashMap<String, Object>() {{
        put("dataSourceClassName", "com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
        put("dataSource.url", "jdbc:mysql://192.168.80.132:3306/logmonitor?useUnicode=true&characterEncoding=UTF-8");
        put("dataSource.user", "root");
        put("dataSource.password", "123456");
    }};

    public static ConnectionProvider connectionProvider = new HikariCPConnectionProvider(hikariConfigMap);

    public static JdbcInsertBolt getJdbcInsertBolt() {
        JdbcInsertBolt jdbcInsertBolt = null;
        @SuppressWarnings("rawtypes")
        List<Column> schemaColumns = Lists.newArrayList(new Column("message", Types.VARCHAR));
        for(@SuppressWarnings("rawtypes") final Column column : schemaColumns) {
            if(null != column) {
                logger.info("column:" + column.toString());
            }
        }
        if(null != schemaColumns && !schemaColumns.isEmpty()) {
            JdbcMapper simpleJdbcMapper = new SimpleJdbcMapper(schemaColumns);
            jdbcInsertBolt = new JdbcInsertBolt(connectionProvider, simpleJdbcMapper)
                    .withInsertQuery("insert into t_source_log(message) values(?)")
                    .withQueryTimeoutSecs(50);
        }
        return jdbcInsertBolt;
    }
}
