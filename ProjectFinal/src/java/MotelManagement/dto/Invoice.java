package MotelManagement.dto;
import java.util.Date;

public class Invoice {
    private String id;
    private String roomId;
    private Date date;
    private Date collectionDate;
    private String content;
    private double debt;
    private double proceeds;
    private double excessCash;

    public Invoice() {
    }

    public Invoice(String id, String roomId, Date date, Date collectionDate, String content, double debt, double proceeds, double excessCash) {
        this.id = id;
        this.roomId = roomId;
        this.date = date;
        this.collectionDate = collectionDate;
        this.content = content;
        this.debt = debt;
        this.proceeds = proceeds;
        this.excessCash = excessCash;
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

    public Date getCollectionDate() {
        return collectionDate;
    }

    public void setCollectionDate(Date collectionDate) {
        this.collectionDate = collectionDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public double getDebt() {
        return debt;
    }

    public void setDebt(double debt) {
        this.debt = debt;
    }

    public double getProceeds() {
        return proceeds;
    }

    public void setProceeds(double proceeds) {
        this.proceeds = proceeds;
    }

    public double getExcessCash() {
        return excessCash;
    }

    public void setExcessCash(double excessCash) {
        this.excessCash = excessCash;
    }

    @Override
    public String toString() {
        return "Invoice{" + "id=" + id + ", roomId=" + roomId + ", date=" + date + ", collectionDate=" + collectionDate + ", content=" + content + ", debt=" + debt + ", proceeds=" + proceeds + ", excessCash=" + excessCash + '}';
    }
}
