package MotelManagement.dao;

import MotelManagement.dto.Parameter;
import MotelManagement.util.Generator;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ParameterDao extends BaseDao{

    public ParameterDao() {
    }
    
    public String nextId() {
        String query = "SELECT id FROM parameters ORDER BY id DESC LIMIT 1;";

        String id = "";
        ResultSet resultSet = dbConnection.select(query, null);
        try {
            if (resultSet.next()) {
                //Get last id in table
                id = resultSet.getString("id");
                //Generate next id
                id = Generator.nextId("P", id, false);
            }
            else id = "P001";
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            dbConnection.closeConnection();
        }

        return id;
    }
    
    public boolean insert(Parameter parameter) {
        String sql = "INSERT INTO parameters (id, name, type, value, state) "
                + "VALUES (?, ?, ?, ?, ?);";
        
        Object[] parameters = new Object[] {
            parameter.getId(),
            parameter.getName(),
            parameter.getType(),
            parameter.getValue(),
            parameter.isState()
        };
        
        return dbConnection.save(sql, parameters);
    }
    
    public boolean update(Parameter parameter) {
        String sql = "UPDATE parameters SET name = ?, type = ?, value = ?, "
                + "state = ? WHERE id = ?;";
        
        Object[] parameters = new Object[] {
            parameter.getName(),
            parameter.getType(),
            parameter.getValue(),
            parameter.isState(),
            parameter.getId()
        };
        
        return dbConnection.save(sql, parameters);
    }
    
    /**
     * select giá trị của tham số.
     * @param parameterName tên tham số.
     * @return giá trị của tham số.
     */
    public String selectValue(String parameterName) {
        String value = "";
        
        String query = "SELECT value from parameters WHERE name = ?;";
        
        Object[] parameters = new Object[] { parameterName };
        
        try {
            ResultSet resultSet = dbConnection.select(query, parameters);
            if (resultSet.next()) {
                value = resultSet.getString("value");
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            dbConnection.closeConnection();
        }
        
        return value;
    }
}
