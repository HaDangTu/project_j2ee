package MotelManagement.bus;

import MotelManagement.dao.GenderDao;
import MotelManagement.dto.Gender;
import java.util.List;


public class GenderBus {
    private GenderDao genderDao;
    
    public GenderBus() {
        genderDao = new GenderDao();
    }
    
    public String nextId() {
        return genderDao.nextId();
    }
    
    public boolean insert(Gender gender) {
        gender.setId(nextId());
        return genderDao.insert(gender);
    }
    
    public boolean update(Gender gender) {
        return genderDao.update(gender);
    }
    
    public boolean delete(Gender gender) {
        return genderDao.delete(gender);
    }
    
    public List<Gender> getAll() {
        return genderDao.selectAll();
    }
    
    public Gender getGender(String genderId) {
        return genderDao.selectGender(genderId);
    }
}
