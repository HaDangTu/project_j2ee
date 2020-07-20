
package MotelManagement.dto;

import java.util.List;


public class GuestPowerInvoices {
    private String roomName;
    
    private List<PowerInvoice> invoices;

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public List<PowerInvoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<PowerInvoice> invoices) {
        this.invoices = invoices;
    }
}
