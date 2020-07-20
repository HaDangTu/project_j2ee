
package MotelManagement.dto;

import java.util.Date;


public class GuestRoomInvoice {
    
    private String roomId;
    private String roomName;
    private Date fromDate;
    private Date toDate;
    private double debt;
    private double preDebt;
    

    public GuestRoomInvoice() {
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public double getDebt() {
        return debt;
    }

    public void setDebt(double debt) {
        this.debt = debt;
    }

    public double getPreDebt() {
        return preDebt;
    }

    public void setPreDebt(double preDebt) {
        this.preDebt = preDebt;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
    
}
