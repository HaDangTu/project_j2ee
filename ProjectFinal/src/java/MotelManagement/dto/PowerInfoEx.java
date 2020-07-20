package MotelManagement.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PowerInfoEx {
    private String id;
    private String roomId;
    private String roomName;
    private String date;
    private long electricityIndex;
    private long waterIndex;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        this.date = formatter.format(date);
    }
    
    public long getElectricityIndex() {
        return electricityIndex;
    }

    public void setElectricityIndex(long electricityIndex) {
        this.electricityIndex = electricityIndex;
    }

    public long getWaterIndex() {
        return waterIndex;
    }

    public void setWaterIndex(long waterIndex) {
        this.waterIndex = waterIndex;
    }
    
}
