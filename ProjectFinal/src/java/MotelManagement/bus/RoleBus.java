package MotelManagement.bus;

import MotelManagement.dao.RoleDao;
import MotelManagement.dto.Role;

import java.util.List;

public class RoleBus {
    private RoleDao roleDao;

    public RoleBus() {
        roleDao = new RoleDao();
    }
    
    public String nextId() {
        return roleDao.nextId();
    }
    
    public boolean insert(Role role) {
        role.setId(nextId());
        return roleDao.insert(role);
    }
    
    public boolean update(Role role) {
        return roleDao.update(role);
    }
    
    public boolean delete(Role role) {
        return roleDao.delete(role);
    }
    
    public List<Role> getAll() {
        return roleDao.selectAll();
    }
}
