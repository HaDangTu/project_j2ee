package MotelManagement.dao;

import MotelManagement.dto.ApplicationUser;
import MotelManagement.util.Generator;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDao extends BaseDao {

    public AccountDao() {
        super();
    }

    public String nextId() {
        String query = "SELECT id FROM accounts ORDER BY id DESC LIMIT 1;";

        String id = "";
        
        try {
            ResultSet resultSet = dbConnection.select(query, null);
            if (resultSet.next()) {
                //Get last id in table
                id = resultSet.getString("id");
                //Generate next id
                id = Generator.nextId("U", id, false);
            } else {
                id = "U00001";
            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            dbConnection.closeConnection();
        }
        return id;
    }

    public boolean insert(ApplicationUser user) {
        String sql = "INSERT INTO accounts (id, username, password, role_id) VALUES "
                + "(?, ?, ?, ?);";

        Object[] parameters = new Object[]{
            user.getId(),
            user.getUsername(),
            user.getPassword(),
            user.getRoleId()
        };

        return dbConnection.save(sql, parameters);
    }

    public boolean update(ApplicationUser user) {
        String sql = "UPDATE accounts SET username = ?, password = ?, role_id = ? WHERE "
                + "id = ?;";

        Object[] parameters = new Object[]{
            user.getUsername(),
            user.getPassword(),
            user.getRoleId(),
            user.getId()
        };

        return dbConnection.save(sql, parameters);
    }

    public boolean delete(ApplicationUser user) {
        String sql = "DELETE FROM accounts WHERE id = ?;";

        Object[] parameters = new Object[]{user.getId()};

        return dbConnection.save(sql, parameters);
    }

    public List<ApplicationUser> selectAll() {
        List<ApplicationUser> users = new ArrayList<>();

        String query = "SELECT * FROM accounts;";

        try {
            ResultSet resultSet = dbConnection.select(query, null);

            if (resultSet != null) {
                while (resultSet.next()) {
                    ApplicationUser user = new ApplicationUser();
                    user.setId(resultSet.getString("id"));
                    user.setUsername(resultSet.getString("username"));
                    user.setPassword(resultSet.getString("password"));
                    user.setRoleId(resultSet.getString("role_id"));
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            System.err.println(e.getCause());
        }
        finally {
            dbConnection.closeConnection();
        }
        return users;
    }

    /**
     * get user by user name
     * @param username
     * @return 
     */
    public ApplicationUser selectUserByUsername(String username) {
        ApplicationUser user = null;

        String query = "SELECT * FROM accounts WHERE username = ?";

        Object[] parameters = new Object[]{username};

        try {
            ResultSet resultSet = dbConnection.select(query, parameters);

            if (resultSet.next()) {
                user = new ApplicationUser();
                user.setId(resultSet.getString("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setRoleId(resultSet.getString("role_id"));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            System.err.println(e.getCause());
        }
        finally {
            dbConnection.closeConnection();
        }
        return user;
    }
    
    /**
     * Lấy quyền user
     * @param user
     * @return 
     */
    public String selectUserRole(ApplicationUser user) {
        String query = "SELECT name FROM roles WHERE id = (SELECT role_id "
                + "FROM accounts WHERE id = ?)";
        
        Object[] parameters = new Object[] {
            user.getId()
        };
        
        String role = "";
        try {
            ResultSet resultSet = dbConnection.select(query, parameters);

            if (resultSet.next()) {
                role = resultSet.getString("name");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            System.err.println(e.getCause());
        }
        finally {
            dbConnection.closeConnection();
        }
        return role;
    }

    public ApplicationUser selectUserById(String userId) {
        ApplicationUser user = null;

        String query = "SELECT * FROM accounts WHERE id = ?";

        Object[] parameters = new Object[]{ userId };

        try {
            ResultSet resultSet = dbConnection.select(query, parameters);

            if (resultSet.next()) {
                user = new ApplicationUser();
                user.setId(resultSet.getString("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setRoleId(resultSet.getString("role_id"));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            System.err.println(e.getCause());
        }
        finally {
            dbConnection.closeConnection();
        }
        return user;
    }
}
