package MotelManagement.dto;

public class ReportData {
    private String roomName;
    private String rentedNum;
    private String money;

    public ReportData() {
    }

    public ReportData(String roomName, String rentedNum, String money) {
        this.roomName = roomName;
        this.rentedNum = rentedNum;
        this.money = money;
    }

    
    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRentedNum() {
        return rentedNum;
    }

    public void setRentedNum(String rentedNum) {
        this.rentedNum = rentedNum;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
    
    
}
