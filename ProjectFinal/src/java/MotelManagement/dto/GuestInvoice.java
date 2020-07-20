
package MotelManagement.dto;

import java.util.List;

public class GuestInvoice {
    private String id;
    
    private String roomName;
    
    private List<RoomInvoice> roomInvoices;
    
    private List<PowerInvoice> powerInvoices;

    public GuestInvoice() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public List<RoomInvoice> getRoomInvoices() {
        return roomInvoices;
    }

    public void setRoomInvoices(List<RoomInvoice> roomInvoices) {
        this.roomInvoices = roomInvoices;
    }

    public List<PowerInvoice> getPowerInvoices() {
        return powerInvoices;
    }

    public void setPowerInvoices(List<PowerInvoice> powerInvoices) {
        this.powerInvoices = powerInvoices;
    }
    
    
}
