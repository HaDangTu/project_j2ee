
package MotelManagement.dto;

public class RoomInfo {
    private String id;
    private String name;
    private String roomType;
    private double price;
    private String userId;

    public RoomInfo() {
    }

    public RoomInfo(String id, String name, String roomType, double price, String userId) {
        this.id = id;
        this.name = name;
        this.roomType = roomType;
        this.price = price;
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

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    
}
