package MotelManagement.dao;

import MotelManagement.dto.Gender;
import MotelManagement.util.Generator;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GenderDao extends BaseDao{

    public GenderDao() {
        super();
    }
    
    public String nextId() {
        String query = "SELECT id FROM genders ORDER BY id DESC LIMIT 1;";

        String id = "";
        ResultSet resultSet = dbConnection.select(query, null);
        try {
            if (resultSet.next()) {
                //Get last id in table
                id = resultSet.getString("id");
                //Generate next id
                id = Generator.nextId("G", id, false);
            } else {
                id = "G01";
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return id;
    }
    
    public boolean insert(Gender gender) {
        String sql = "INSERT INTO genders (id, name) VALUES (?, ?);";
        
        Object[] parameters = new Object[] { gender.getId(), gender.getName() };
        
        return dbConnection.save(sql, parameters);
    }
    
    public boolean update(Gender gender) {
        String sql = "UPDATE genders SET name = ? WHERE id = ?;";
        
        Object[] parameters = new Object[] { gender.getName(), gender.getId() };
        
        return dbConnection.save(sql, parameters);
    }
    
    public boolean delete (Gender gender) {
        String sql = "DELETE FROM genders WHERE id = ?;";
        
        Object[] parameters = new Object[] { gender.getId() };
        
        return dbConnection.save(sql, parameters);
    }
    
    public List<Gender> selectAll() {
        List<Gender> genders = new ArrayList<>();
        
        String query = "SELECT * FROM genders;";
        
        ResultSet resultSet = dbConnection.select(query, null);
        
        try {
            if (resultSet != null) {
                
                while (resultSet.next()) {
                    Gender gender = new Gender(
                            resultSet.getString("id"), 
                            resultSet.getString("name"));
                    
                    genders.add(gender);
                }
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.print(e.getCause());
        }
        return genders;
    }
}
