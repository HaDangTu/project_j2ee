
package MotelManagement.dbconnection;

import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DbConnection {
    
    private Connection connection;
    
    public DbConnection() {
        
    }
    
    /**
     * Thực thi lệnh thao tác dữ liệu insert, update, delete
     * @param sql câu lệnh insert, update, delete
     * @param parameters tham số của câu lệnh
     * @return true nếu insert/update/delete thành công. Ngược lại, false
     */
    public boolean save(String sql, Object[] parameters) {
        connection = DataSource.getConnection();
        try  {
            connection.setAutoCommit(false);
            
            PreparedStatement statement = connection.prepareStatement(sql);
            for (int i = 0; i < parameters.length; i++) {
                statement.setObject(i + 1, parameters[i]);
            }
            
            statement.executeUpdate();
            connection.commit();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.print(e.getCause());
            return false;
        }
        finally {
            closeConnection();
        }
        return true;
    }
    
    /**
     * Thực thi câu truy vấn select
     * @param query câu truy vấn
     * @param parameters tham số của câu truy vấn nếu không có thì dể null
     * @return kết quả của câu truy vấn
     */
    public ResultSet select(String query, Object[] parameters) {
        ResultSet resultSet = null;
        
        connection = DataSource.getConnection();
        try {
            if (parameters != null) {
                connection.setAutoCommit(false);
                
                PreparedStatement statement = connection.prepareStatement(query);
                for (int i = 0; i < parameters.length; i++) {
                    statement.setObject(i + 1, parameters[i]);
                }
                
                resultSet = statement.executeQuery();
                connection.commit();
            }
            else {
                Statement statement = connection.createStatement();
                resultSet = statement.executeQuery(query);
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.print(e.getCause());
        }
        return resultSet;
    }
    
    public void closeConnection() {
        try {
            if (connection != null) connection.close();
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
            System.err.println(e.getCause());
        }
    }
}
