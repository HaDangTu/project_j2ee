package MotelManagement.bus;

import MotelManagement.dao.ParameterDao;
import MotelManagement.dto.Parameter;


public class ParameterBus {
    private ParameterDao parameterDao;
    
    public ParameterBus() {
        parameterDao = new ParameterDao();
    }
    
    public String nextId() {
        return parameterDao.nextId();
    }
    
    public boolean insert(Parameter parameter) {
        parameter.setId(nextId());
        return parameterDao.insert(parameter);
    }
    
    public boolean update(Parameter parameter) {
        return parameterDao.update(parameter);
    }
    
    /**
     * Lấy giá trị tham số
     * @param parameterName tên tham số
     * @return 
     */
    public String getParameterValue(String parameterName) {
        return parameterDao.selectValue(parameterName);
    }
}
