package MotelManagement.dto;

public class ReportData {
    private int month;
    private String roomName;
    private int rentedNum;
    private double money;

    public ReportData() {
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getRentedNum() {
        return rentedNum;
    }

    public void setRentedNum(int rentedNum) {
        this.rentedNum = rentedNum;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }
    
    
}
