package MotelManagement.bus;

import MotelManagement.dao.RoomDao;
import MotelManagement.dto.ApplicationUser;
import MotelManagement.dto.Guest;
import MotelManagement.dto.Room;
import java.util.Date;
import java.util.List;


public class RoomBus {
    private RoomDao roomDao;

    public RoomBus() {
        roomDao = new RoomDao();
    }
    
    public String nextId() {
        return roomDao.nextId();
    }
    
    public String isValidRoomName(String name) {
        if (name.trim().length() < 1) {
            return "Vui lòng nhập tên phòng";
        }
        return "";
    }
    public boolean insert(Room room) {
        room.setId(nextId());
        return roomDao.insert(room);
    }
    
    public boolean update(Room room) {
        return roomDao.update(room);
    }
    
    public boolean delete(Room room) {
        return roomDao.delete(room);
    }
    
    public List<Room> getAll() {
        return roomDao.selectAll();
    }
    
    /**
     * Lấy danh sách các phòng đang cho thuê
     * @return danh sách phòng đang cho thuê
     */
    public List<Room> getRentedRooms() {
        return roomDao.selectRentedRooms();
    }
    
    /**
     * Lấy danh sách các phòng chưa cho thuê
     * @return danh sách phòng chưa cho thuê
     */
    public List<Room> getNotRentedRooms() {
        return roomDao.selectNotRentedRooms();
    }
    
    /**
     * Lấy số lượng người tối đa trong phòng.
     * @param roomId mã phòng
     * @return max
     */
    public int getMaxNumGuest(String roomId) {
        return roomDao.selectMaxNumGuest(roomId);
    }
    
    /**
     * Lấy những phòng chưa full người
     * @return danh sách phòng
     */
    public List<Room> getNotFullRooms() {
        List<Room> rooms = getAll();
        
        for (int i = 0; i < rooms.size(); i++) {
            Room room = rooms.get(i);
            if (isFull(room.getId())) {
                rooms.remove(room);
            }
        }
        
        return rooms;
    }
    
    public List<Room> getFullRooms() {
        List<Room> rooms = getRentedRooms();
        
        for (int i = 0; i < rooms.size(); i++) {
            Room room = rooms.get(i);
            if (!isFull(room.getId())) {
                rooms.remove(room);
            }
        }
        
        return rooms;
    }
    
    /**
     * Kiểm tra phòng đã full người hay không
     * @param roomId mã phòng
     * @return full -> true, ngược lại -> false
     */
    public boolean isFull(String roomId) {
        int maxGuest = getMaxNumGuest(roomId);
        if (getGuests(roomId).size() == maxGuest)
            return true;
        return false;
    }
    
    /**
     * Lấy danh sách khách trọ của 1 phòng
     * @param roomId mã phòng
     * @return danh sách khách trọ
     */
    public List<Guest> getGuests(String roomId) {
        return roomDao.selectGuests(roomId);
    }
    
    /**
     * Get room by id
     * @param roomId mã phòng
     * @return room
     */
    public Room getRoom(String roomId) {
        return roomDao.selectRoom(roomId);
    }
    
    public Room getRoom(ApplicationUser user) {
        return roomDao.selectRoom(user);
    }
    /**
     * Lấy tiền thuê của phòng
     * @param roomId mã phòng
     * @return tiền thuê phòng
     */
    public double getPrice(String roomId) {
        return roomDao.selectMoney(roomId);
    }
    
    /**
     * Lấy ngày bắt đầu thuê của phòng
     * @param roomId mã phòng
     * @return ngày bắt đầu thuê
     */
    public Date getStartDate(String roomId) {
        return roomDao.selectStartDate(roomId);
    }
    
    /**
     * Lấy số lượt thuê phòng theo quý
     * @param roomId mã phòng
     * @param month1 tháng đầu quý
     * @param month2 tháng cuối quý
     * @param year năm 
     * @return 
     */
    public int getRentedNumberByQuarter(String roomId, int month1, int month2, int year) {
        int maxGuest = getMaxNumGuest(roomId);
        int count = roomDao.countRentedNumber(roomId, month1, month2, year);
        return (int) Math.round((double) count / maxGuest);
    }
    
    /**
     * Lấy số lượt thuê phòng theo tháng
     * @param roomId mã phòng
     * @param month tháng 
     * @param year năm
     * @return 
     */
    public int getRentedNumberByMonth(String roomId, int month, int year) {
        int maxGuest = getMaxNumGuest(roomId);
        int count = roomDao.countRentedNumber(roomId, month, year);
        return (int) Math.round((double) count / maxGuest);
    }
    
    /**
     * Lấy số lượt thuê phòng theo khoảng thời gian bất kì (từ ngày...đến ngày....)
     * @param roomId mã phòng
     * @param fromDate từ ngày
     * @param toDate đến ngày
     * @return 
     */
    public int getRentedNumberByDate(String roomId, Date fromDate, Date toDate) {
        int maxGuest = getMaxNumGuest(roomId);
        int count = roomDao.countRentedNumber(roomId, fromDate, toDate);
        return (int) Math.round((double) count / maxGuest);
    }
    
    
    /**
     * Lấy tổng số tiền thu được theo quý
     * @param roomId mã phòng
     * @param startMonth tháng đầu quý
     * @param endMonth tháng cuối quý
     * @param year năm
     * @return 
     */
    public double getSumMoneyByQuarter(String roomId, int startMonth, int endMonth, int year) {
        return roomDao.selectSumMoney(roomId, startMonth, endMonth, year);
    }
    
    /**
     * Lấy tổng số tiền phòng thu được theo tháng
     * @param roomId mã phòng
     * @param month tháng
     * @param year năm
     * @return 
     */
    public double getSumMoneyByMonth(String roomId, int month, int year) {
        return roomDao.selectSumMoney(roomId, month, year);
    }
    
    /**
     * Lấy tổng số tiền phòng thu được theo khoảng thời gian (từ ngày...đến ngày)
     * @param roomId mã phòng
     * @param fromDate từ ngày
     * @param toDate đến ngày
     * @return 
     */
    public double getSumMoneyByDate(String roomId, Date fromDate, Date toDate) {
        return roomDao.selectSumMoney(roomId, fromDate, toDate);
    }
}
