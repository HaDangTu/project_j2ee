package MotelManagement.bus;

import MotelManagement.dao.RoomDao;
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
        List<Room> rooms = getRentedRooms();
        
        for (Room room : rooms) {
            String roomId = room.getId();
            if (isFull(roomId))
                rooms.remove(room);
        }
        
        return rooms;
    }
    
    public List<Room> getFullRooms() {
        List<Room> rooms = getRentedRooms();
        
        for (Room room : rooms) {
            String roomId = room.getId();
            if (!isFull(roomId))
                rooms.remove(room);
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
    
    /**
     * Lấy tiền thuê của phòng
     * @param roomId mã phòng
     * @return tiền thuê phòng
     */
    public double getMoney(String roomId) {
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
}
