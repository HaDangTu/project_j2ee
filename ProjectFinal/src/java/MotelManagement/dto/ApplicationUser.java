
package MotelManagement.dto;


public class ApplicationUser {
    private String id;
    private String username;
    private String password;
    private String roleId;
    
    public ApplicationUser() {
    }

    public ApplicationUser(String id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    
    @Override
    public String toString() {
        return "ApplicationUser{" + "id=" + id + ", username=" + username + ", password=" + password + '}';
    }
    
    
}
