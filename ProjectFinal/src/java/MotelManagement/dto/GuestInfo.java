
package MotelManagement.dto;

import java.util.Date;


public class GuestInfo {
    private String id;
    private String name;
    private Date birthday;
    private String gender;
    private String indentityNumber;
    private String homeTown;
    private String occupation;
    private Date startDate;
    private String state;
    private String room;

    public GuestInfo() {
    }

    public GuestInfo(String id, String name, Date birthday, String gender, String indentityNumber, String homeTown, String occupation, Date startDate, String state, String room) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.indentityNumber = indentityNumber;
        this.homeTown = homeTown;
        this.occupation = occupation;
        this.startDate = startDate;
        this.state = state;
        this.room = room;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIndentityNumber() {
        return indentityNumber;
    }

    public void setIdentityNumber(String indentityNumber) {
        this.indentityNumber = indentityNumber;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
    
    
}
