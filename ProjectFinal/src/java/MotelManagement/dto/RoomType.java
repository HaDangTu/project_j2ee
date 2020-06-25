package MotelManagement.dto;


public class RoomType {
    private String id;
    private String name;
    private int numOfGuest;
    private double price;

    public RoomType() {
    }

    public RoomType(String id, String name, int numOfGuest, double price) {
        this.id = id;
        this.name = name;
        this.numOfGuest = numOfGuest;
        this.price = price;
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

    public int getNumOfGuest() {
        return numOfGuest;
    }

    public void setNumOfGuest(int numOfGuest) {
        this.numOfGuest = numOfGuest;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "RoomType{" + "id=" + id + ", name=" + name + ", numOfGuest=" + numOfGuest + ", price=" + price + '}';
    }
    
    
}
