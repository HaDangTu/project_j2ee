package MotelManagement.dto;

import java.util.List;


public class GuestRoomInvoices {
    private String roomName;
    
    private List<RoomInvoice> invoices;

    public GuestRoomInvoices() {
        
    }
    
    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public List<RoomInvoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<RoomInvoice> invoices) {
        this.invoices = invoices;
    }
    
    
}
