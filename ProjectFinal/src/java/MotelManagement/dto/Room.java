package MotelManagement.dto;


public class Room {
    private String id;
    private String name;
    private String roomTypeId;
    private String userId;

    public Room() {
    }

    public Room(String id, String name, String roomTypeId, String userId) {
        this.id = id;
        this.name = name;
        this.roomTypeId = roomTypeId;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(String roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Room{" + "id=" + id + ", name=" + name + ", roomTypeId=" + roomTypeId + ", userId=" + userId + '}';
    }
    
    
}
