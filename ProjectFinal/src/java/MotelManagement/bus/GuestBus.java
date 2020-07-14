package MotelManagement.bus;

import MotelManagement.dao.GuestDao;
import MotelManagement.dto.Guest;
import MotelManagement.validator.IdentityNumberValidator;
import MotelManagement.validator.NameValidator;
import MotelManagement.validator.Validator;

import java.util.List;


public class GuestBus {
    private GuestDao guestDao;

    public GuestBus() {
        guestDao = new GuestDao();
    }
    
    public String nextId() {
        return guestDao.nextId();
    }
    
    public String isValidName(String name) {
        Validator validator = new NameValidator();
        return validator.isValid(name);
    }
    
    public String isValidIdentityNumber(String identityNumber) {
        Validator validator = new IdentityNumberValidator();
        return validator.isValid(identityNumber);
    }
    
    public String isValidDate(String date) {
        if (date.equals(""))
            return "Ngày không hợp lệ";
        return "";
    }
    
    public boolean insert(Guest guest) {
        guest.setId(nextId());
        return guestDao.insert(guest);
    }
    
    public boolean update(Guest guest) {
        return guestDao.update(guest);
    }
    
    public boolean delete(Guest guest) {
        return guestDao.delete(guest);
    }
    
    public List<Guest> getAll() {
        return guestDao.selectAll();
    }
    
    /**
     * Lấy khách hàng bằng id
     * @param guestId mã khách hàng
     * @return khách hàng
     */
    public Guest getGuest(String guestId) {
        return guestDao.selectGuest(guestId);
    }
}
