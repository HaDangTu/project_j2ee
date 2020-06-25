package MotelManagement.bus;

import MotelManagement.dao.ParameterDao;
import MotelManagement.dao.PowerInfoDao;
import MotelManagement.dto.PowerInfo;


public class PowerInfoBus {
    private PowerInfoDao powerInfoDao;
    private ParameterDao parameterDao;
    
    public PowerInfoBus() {
        powerInfoDao = new PowerInfoDao();
        parameterDao = new ParameterDao();
    }
    
    public String nextId() {
        return powerInfoDao.nextId();
    }
    
    public boolean insert(PowerInfo powerInfo) {
        powerInfo.setId(nextId());
        return powerInfoDao.insert(powerInfo);
    }
    
    public boolean update(PowerInfo powerInfo) {
        return powerInfoDao.update(powerInfo);
    }
    
    public boolean delete(PowerInfo powerInfo) {
        return powerInfoDao.delete(powerInfo);
    }
    
    /**
     * select chỉ số điện của phòng theo tháng.
     * @param roomId mã phòng.
     * @param month tháng cần lấy chỉ số điện.
     * @return chỉ số điện của phòng.
     */
    public int getElectricIndex(String roomId, int month) {
        return powerInfoDao.selectElectricIndex(roomId, month);
    }
    
    /**
     * select chỉ số nước của phòng theo tháng.
     * @param roomId mã phòng.
     * @param month tháng cần lấy chỉ số nước.
     * @return chỉ số nước của phòng.
     */
    public int getWaterIndex(String roomId, int month) {
        return powerInfoDao.selectWaterIndex(roomId, month);
    }
    
    /**
     * Tính tiền điện theo tháng của phòng
     * @param roomId mã phòng
     * @param month tháng
     * @return 
     */
    public double calculateElectricBill(String roomId, int month) {
        //chỉ số điện cũ
        int oldIndex = getElectricIndex(roomId, month == 1 ? 12 : month - 1);
        //chỉ số điện mới
        int newIndex = getElectricIndex(roomId, month);
        
        //giá điện
        double electricPrice = Double.valueOf(parameterDao.selectValue("Giá điện"));
        
        return (newIndex - oldIndex) * electricPrice;
    }
    
    /**
     * Tính tiền nước theo tháng của phòng
     * @param roomId mã phòng
     * @param month tháng
     * @return 
     */
    public double calculateWaterBill(String roomId, int month) {
        //Chỉ số nước cũ
        int oldIndex = getWaterIndex(roomId, month == 1 ? 12 : month - 1);
        
        //Chỉ số nước mới
        int newIndex = getWaterIndex(roomId, month);
        
        //giá nước
        double waterPrice = Double.valueOf(parameterDao.selectValue("Giá nước"));
        
        return (newIndex - oldIndex) * waterPrice;
    }
}
