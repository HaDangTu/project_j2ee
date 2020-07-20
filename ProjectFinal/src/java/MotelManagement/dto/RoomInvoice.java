package MotelManagement.dto;

public class RoomInvoice {
    private int month;
    private int year;
    private double money;

    public RoomInvoice() {
    }

    
    public RoomInvoice(int month, double money) {
        this.month = month;
        this.money = money;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    } 

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
    
}
