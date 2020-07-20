package MotelManagement.dbconnection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DataSource {
    
    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource dataSource;
    
    static {
        config.setJdbcUrl(DbConfiguration.CONNECTION_URL);
        config.setUsername(DbConfiguration.USER_NAME);
        config.setPassword(DbConfiguration.PASSWORD);
        config.setDriverClassName(DbConfiguration.DB_DRIVER);
        config.setMinimumIdle(DbConfiguration.MIN_POOL_SIZE);
        config.setMaximumPoolSize(DbConfiguration.MAX_POOL_SIZE);
        config.addDataSourceProperty( "cachePrepStmts" , "true" );
        config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
        config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
        
        dataSource = new HikariDataSource(config);
    }
    
    public static Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
