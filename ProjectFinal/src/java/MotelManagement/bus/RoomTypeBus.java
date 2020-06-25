package MotelManagement.bus;

import MotelManagement.dao.RoomTypeDao;
import MotelManagement.dto.RoomType;
import java.util.List;

public class RoomTypeBus {

    private RoomTypeDao roomTypeDao;

    public RoomTypeBus() {
        roomTypeDao = new RoomTypeDao();
    }
    
    public String nextId() {
        return roomTypeDao.nextId();
    }
    
    public boolean insert(RoomType roomType) {
        roomType.setId(nextId());
        return roomTypeDao.insert(roomType);
    }
    
    public boolean update(RoomType roomType) {
        return roomTypeDao.update(roomType);
    }
    
    public boolean delete(RoomType roomType) {
        return roomTypeDao.delete(roomType);
    }
    
    public List<RoomType> getAll() {
        return roomTypeDao.selectAll();
    }
    
    /**
     * get room type by id
     * @param roomTypeId
     * @return roomType
     */
    public RoomType getRoomType(String roomTypeId) {
        return roomTypeDao.selectRoomType(roomTypeId);
    }
}
