package MotelManagement.bus;

import MotelManagement.dao.AccountDao;
import MotelManagement.dto.ApplicationUser;
import MotelManagement.util.Generator;

import java.util.List;

public class AccountBus {
    private AccountDao accountDao;
    
    
    public AccountBus() {
        accountDao = new AccountDao();
       
    }
    
    public String isValidUsername(String username) {
        if (username.trim().length() < 1) {
            return "Vui lòng nhập username";
        }
        
        return "";
    }
    
    public String isValidPassword(String password, String confirmPass) {
        if (password.trim().length() < 1) {
            return "Vui lòng nhập password";
        }
        else if (!password.equals(confirmPass)) {
            return "Vui lòng kiểm tra password đã nhập";
        }
        return "";
    }
    
    public String nextId() {
        return accountDao.nextId();
    }
    
    public boolean insert(ApplicationUser account) {
        account.setId(nextId());
        return accountDao.insert(account);
    }
    
    public boolean update(ApplicationUser account) {
        return accountDao.update(account);
    }
    
    public boolean delete(ApplicationUser account) {
        return accountDao.delete(account);
    }
    
    public List<ApplicationUser> getAll() {
        return accountDao.selectAll();
    }
    
    /**
     * Get user by username
     * @param username
     * @return 
     */
    public ApplicationUser getUser(String username) {
        return accountDao.selectUserByUsername(username);
    }
    
    public ApplicationUser getUserById(String userId) {
        return accountDao.selectUserById(userId);
    }
    /**
     * Kiểm tra user có hợp lệ để đăng nhập vào hệ thống hay không
     * @param user user có username hợp lệ
     * @param password
     * @return true nếu password hợp lệ, false nếu password không hợp lệ
     */
    public boolean isValid(ApplicationUser user, String password) {
        password = Generator.hashPassword(password);
        
        return password.equalsIgnoreCase(user.getPassword());
    }
    
    /**
     * Lấy quyền user
     * @param user
     * @return 
     */
    public String getRole(ApplicationUser user) {
        return accountDao.selectUserRole(user);
    }
}
