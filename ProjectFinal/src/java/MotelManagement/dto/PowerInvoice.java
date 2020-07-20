/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MotelManagement.dto;

/**
 *
 * @author Home
 */
public class PowerInvoice {
    private int month;
    private int year;
    private long oldElectricityIndex;
    private long newElectricityIndex;
    private long oldWaterIndex;
    private long newWaterIndex;
    private double electricityMoney;
    private double waterMoney;

    public PowerInvoice() {
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public long getOldElectricityIndex() {
        return oldElectricityIndex;
    }

    public void setOldElectricityIndex(long oldElectricityIndex) {
        this.oldElectricityIndex = oldElectricityIndex;
    }

    public long getNewElectricityIndex() {
        return newElectricityIndex;
    }

    public void setNewElectricityIndex(long newElectricityIndex) {
        this.newElectricityIndex = newElectricityIndex;
    }

    public long getOldWaterIndex() {
        return oldWaterIndex;
    }

    public void setOldWaterIndex(long oldWaterIndex) {
        this.oldWaterIndex = oldWaterIndex;
    }

    public long getNewWaterIndex() {
        return newWaterIndex;
    }

    public void setNewWaterIndex(long newWaterIndex) {
        this.newWaterIndex = newWaterIndex;
    }

    public double getElectricityMoney() {
        return electricityMoney;
    }

    public void setElectricityMoney(double electricityMoney) {
        this.electricityMoney = electricityMoney;
    }

    public double getWaterMoney() {
        return waterMoney;
    }

    public void setWaterMoney(double waterMoney) {
        this.waterMoney = waterMoney;
    }
    
    public double getSumMoney() {
        return electricityMoney + waterMoney;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
