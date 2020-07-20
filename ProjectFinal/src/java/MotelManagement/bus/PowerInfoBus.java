package MotelManagement.bus;

import MotelManagement.dao.ParameterDao;
import MotelManagement.dao.PowerInfoDao;
import MotelManagement.dto.PowerInfo;
import java.util.Calendar;
import java.util.List;

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

    public String isValidElecIndex(String roomId, String electricityIndex, int month, int year) {
        if (electricityIndex.equals("")) {
            return "Vui lòng nhập chỉ số điện";
        } else {
            long index = Long.valueOf(electricityIndex);

            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.MONTH, month - 1);
            calendar.set(Calendar.YEAR, year);

            calendar.add(Calendar.MONTH, -1);

            int prevMonth = calendar.get(Calendar.MONTH) + 1;
            int prevYear = calendar.get(Calendar.YEAR);

            long prevIndex = getElectricIndex(roomId, prevMonth, prevYear);

            if (index < prevIndex) {
                return "Chỉ số tháng sau phải lớn hơn tháng trước";
            } else {
                return "";
            }
        }
    }

    public String isValidWaterIndex(String roomId, String waterIndex, int month, int year) {
        if (waterIndex.equals("")) {
            return "Vui lòng nhập chỉ số nước";
        } else {
            long index = Long.valueOf(waterIndex);
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.MONTH, month - 1);
            calendar.set(Calendar.YEAR, year);

            calendar.add(Calendar.MONTH, -1);

            int prevMonth = calendar.get(Calendar.MONTH) + 1;
            int prevYear = calendar.get(Calendar.YEAR);

            int prevIndex = getWaterIndex(roomId, prevMonth, prevYear);

            if (index < prevIndex) {
                return "Chỉ số tháng sau phải lớn hơn tháng trước";
            } else {
                return "";
            }
        }
    }

    /**
     * Lấy thông tin điện nước của phòng
     *
     * @param room_id mã phòng
     * @param month tháng
     * @return
     */
    public PowerInfo getPowerInfo(String room_id, int month) {
        return powerInfoDao.selectInfosByMonth(room_id, month);
    }

    /**
     * select chỉ số điện của phòng theo tháng.
     *
     * @param roomId mã phòng.
     * @param month tháng cần lấy chỉ số điện.
     * @return chỉ số điện của phòng.
     */
    public int getElectricIndex(String roomId, int month, int year) {
        return powerInfoDao.selectElectricIndex(roomId, month, year);
    }

    /**
     * select chỉ số nước của phòng theo tháng.
     *
     * @param roomId mã phòng.
     * @param month tháng cần lấy chỉ số nước.
     * @return chỉ số nước của phòng.
     */
    public int getWaterIndex(String roomId, int month, int year) {
        return powerInfoDao.selectWaterIndex(roomId, month, year);
    }

    public int getOldElectricIndex(String roomId, int month, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.YEAR, year);

        calendar.add(Calendar.MONTH, -1);

        int prevMonth = calendar.get(Calendar.MONTH) + 1;
        int prevYear = calendar.get(Calendar.YEAR);
        
        return getElectricIndex(roomId, prevMonth, prevYear);
    }

    public int getOldWaterIndex(String roomId, int month, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.YEAR, year);

        calendar.add(Calendar.MONTH, -1);

        int prevMonth = calendar.get(Calendar.MONTH) + 1;
        int prevYear = calendar.get(Calendar.YEAR);
        
        return getWaterIndex(roomId, prevMonth, prevYear);
    }
    /**
     * Tính tiền điện theo tháng của phòng
     *
     * @param roomId mã phòng
     * @param month tháng
     * @return
     */
    public double calculateElectricBill(String roomId, int month, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.YEAR, year);

        calendar.add(Calendar.MONTH, -1);

        //chỉ số điện cũ
        int oldIndex = getElectricIndex(roomId, calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.YEAR));
        //chỉ số điện mới
        int newIndex = getElectricIndex(roomId, month, year);

        //giá điện
        double electricPrice = Double.valueOf(parameterDao.selectValue("Giá điện"));

        return (newIndex - oldIndex) * electricPrice;
    }

    /**
     * Tính tiền nước theo tháng của phòng
     *
     * @param roomId mã phòng
     * @param month tháng
     * @return
     */
    public double calculateWaterBill(String roomId, int month, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.YEAR, year);

        calendar.add(Calendar.MONTH, -1);
        //Chỉ số nước cũ
        int oldIndex = getWaterIndex(roomId, calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.YEAR));

        //Chỉ số nước mới
        int newIndex = getWaterIndex(roomId, month, year);

        //giá nước
        double waterPrice = Double.valueOf(parameterDao.selectValue("Giá nước"));

        return (newIndex - oldIndex) * waterPrice;
    }
}
