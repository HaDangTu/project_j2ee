package MotelManagement.dto;

import java.util.Date;


public class GuestPowerInvoice {
    private String roomId;
    private String roomName;
    
    private Date fromDate;
    private Date toDate;
    
    private int oldElectricityIndex;
    private int newElectricityIndex;
    
    private int oldWaterIndex;
    private int newWaterIndex;
    
    private double electricMoney;
    private double waterMoney;
    
    private double debt;
    private double preDebt;

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
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

    public int getOldElectricityIndex() {
        return oldElectricityIndex;
    }

    public void setOldElectricityIndex(int oldElectricityIndex) {
        this.oldElectricityIndex = oldElectricityIndex;
    }

    public int getNewElectricityIndex() {
        return newElectricityIndex;
    }

    public void setNewElectricityIndex(int newElectricityIndex) {
        this.newElectricityIndex = newElectricityIndex;
    }

    public int getOldWaterIndex() {
        return oldWaterIndex;
    }

    public void setOldWaterIndex(int oldWaterIndex) {
        this.oldWaterIndex = oldWaterIndex;
    }

    public int getNewWaterIndex() {
        return newWaterIndex;
    }

    public void setNewWaterIndex(int newWaterIndex) {
        this.newWaterIndex = newWaterIndex;
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

    public double getElectricMoney() {
        return electricMoney;
    }

    public void setElectricMoney(double electricMoney) {
        this.electricMoney = electricMoney;
    }

    public double getWaterMoney() {
        return waterMoney;
    }

    public void setWaterMoney(double waterMoney) {
        this.waterMoney = waterMoney;
    }
    
    
}
