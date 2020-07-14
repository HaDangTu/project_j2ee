package MotelManagement.dto;
import java.util.Date;


public class Guest {
    private String id;
    private String name;
    private Date birthday;
    private String genderId;
    private String identityNumber;
    private String homeTown;
    private String occupation;
    private Date startDate;
    private String stateId;
    private String roomId;

    public Guest() {
    }

    public Guest(String id, String name, Date birthday, String genderId, String indentityNumber, String homeTown, String occupation, Date startDate, String stateId, String roomId) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.genderId = genderId;
        this.identityNumber = indentityNumber;
        this.homeTown = homeTown;
        this.occupation = occupation;
        this.startDate = startDate;
        this.stateId = stateId;
        this.roomId = roomId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getGenderId() {
        return genderId;
    }

    public void setGenderId(String genderId) {
        this.genderId = genderId;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String indentityNumber) {
        this.identityNumber = indentityNumber;
    }

    public String getHomeTown() {
        return homeTown;
    }

    public void setHomeTown(String homeTown) {
        this.homeTown = homeTown;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getStateId() {
        return stateId;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    @Override
    public String toString() {
        return "Guest{" + "id=" + id + ", name=" + name + ", birthday=" + birthday + ", genderId=" + genderId + ", indentityNumber=" + identityNumber + ", homeTown=" + homeTown + ", occupation=" + occupation + ", startDate=" + startDate + ", stateId=" + stateId + ", roomId=" + roomId + '}';
    }
    
    
}
