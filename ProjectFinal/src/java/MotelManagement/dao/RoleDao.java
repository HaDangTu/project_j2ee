package MotelManagement.dao;

import MotelManagement.dto.Role;
import MotelManagement.util.Generator;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDao extends BaseDao {
    public RoleDao() {
        super();
    }
    
    public String nextId() {
        String query = "SELECT id FROM roles ORDER BY id DESC LIMIT 1;";

        String id = "";
        ResultSet resultSet = dbConnection.select(query, null);
        try {
            if (resultSet.next()) {
                //Get last id in table
                id = resultSet.getString("id");
                //Generate next id
                id = Generator.nextId("R", id, false);
            }
            else id = "R01";
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            dbConnection.closeConnection();
        }

        return id;
    }
    
    public boolean insert(Role role) {
        String sql = "INSERT INTO roles (id, name) VALUES (?, ?);";
        
        Object[] parameters = new Object[] {
            role.getId(),
            role.getName()
        };
        
        return dbConnection.save(sql, parameters);
    }
    
    public boolean update(Role role) {
        String sql = "UPDATE roles SET name = ? WHERE id = ?;";
        
        Object[] parameters = new Object[] {
            role.getName(),
            role.getId()
        };
        
        return dbConnection.save(sql, parameters);
    }
    
    public boolean delete(Role role) {
        String sql = "DELETE FROM roles WHERE id = ?;";
        
        Object[] parameters = new Object[] { role.getId() };
        
        return dbConnection.save(sql, parameters);
    }
    
    public List<Role> selectAll() {
        String query = "SELECT * FROM roles;";
        List<Role> roles = new ArrayList<>();
        
        try {
            ResultSet resultSet = dbConnection.select(query, null);
            
            if (resultSet != null) {
                while(resultSet.next()) {
                   Role role = new Role(
                   resultSet.getString("id"),
                   resultSet.getString("name"));
                   
                   roles.add(role);
                }
            }
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
            System.err.println(e.getCause());
        } finally {
            dbConnection.closeConnection();
        }
        
        return roles;
        
    }
}
