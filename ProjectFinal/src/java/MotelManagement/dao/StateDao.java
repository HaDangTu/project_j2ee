package MotelManagement.dao;

import MotelManagement.dto.State;
import MotelManagement.util.Generator;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class StateDao extends BaseDao{
    public StateDao() {
        super();
    }
    
    public String nextId() {
        String query = "SELECT id FROM states ORDER BY id DESC LIMIT 1;";

        String id = "";
        ResultSet resultSet = dbConnection.select(query, null);
        try {
            if (resultSet.next()) {
                //Get last id in table
                id = resultSet.getString("id");
                //Generate next id
                id = Generator.nextId("S", id, false);
            }
            else id = "S01";
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            dbConnection.closeConnection();
        }

        return id;
    }
    
    public boolean insert(State state) {
        String sql = "INSERT INTO states (id, name) VALUES (?, ?);";
        
        Object[] parameters = new Object[] { state.getId(), state.getName() };
        
        return dbConnection.save(sql, parameters);
    }
    
    public boolean update(State state) {
        String sql = "UPDATE states SET name = ? WHERE id = ?;";
        
        Object[] parameters = new Object[] { state.getName(),  state.getId() };
        
        return dbConnection.save(sql, parameters);
    }
    
    public boolean delete(State state) {
        String sql = "DELETE FROM states WHERE id = ?;";
        
        Object[] parameters = new Object[] { state.getId() };
        
        return dbConnection.save(sql, parameters);
    }
    
    public List<State> selectAll() {
        String query = "SELECT * FROM states";
        List<State> states = new ArrayList<>();
        
        ResultSet resultSet = dbConnection.select(query, null);
        try {
            if (resultSet != null) {
                while (resultSet.next()) {
                    State state = new State();
                    state.setId(resultSet.getString("id"));
                    state.setName(resultSet.getString("name"));
                    
                    states.add(state);
                }
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.print(e.getCause());
        } finally {
            dbConnection.closeConnection();
        }
        return states;
    }

    public State selectState(String stateId) {
        State state = null;
        String query = "SELECT * FROM states WHERE id = ?;";
        
        Object[] parameters = new Object[] { stateId };
        
        ResultSet resultSet = dbConnection.select(query, parameters);
        try {
            if (resultSet != null) {
                if (resultSet.next()) {
                    state = new State();
                    state.setId(resultSet.getString("id"));
                    state.setName(resultSet.getString("name"));
                }
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
        } finally {
            dbConnection.closeConnection();
        }
        return state;
    }
}
