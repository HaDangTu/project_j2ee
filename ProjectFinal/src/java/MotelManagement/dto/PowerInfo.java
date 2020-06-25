package MotelManagement.dto;
import java.util.Date;

public class PowerInfo {
    private String id;
    private String roomId;
    private Date date;
    private long electricityIndex;
    private long waterIndex;

    public PowerInfo() {
    }

    public PowerInfo(String id, String roomId, Date date, long electricityIndex, long waterIndex) {
        this.id = id;
        this.roomId = roomId;
        this.date = date;
        this.electricityIndex = electricityIndex;
        this.waterIndex = waterIndex;
    }

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    @Override
    public String toString() {
        return "PowerInfo{" + "id=" + id + ", roomId=" + roomId + ", date=" + date + ", electricityIndex=" + electricityIndex + ", waterIndex=" + waterIndex + '}';
    }
}
