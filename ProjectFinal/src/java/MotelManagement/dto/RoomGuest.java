
package MotelManagement.dto;

import java.util.List;

public class RoomGuest {
    private Room room;
    private int maxGuest;
    
    private List<Guest> guests;
    
    public RoomGuest() { 
        
    }
    
    public RoomGuest(Room room, List<Guest> guests) {
        this.room = room;
        this.guests = guests;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public List<Guest> getGuests() {
        return guests;
    }

    public void setGuests(List<Guest> guests) {
        this.guests = guests;
    }

    public int getMaxGuest() {
        return maxGuest;
    }

    public void setMaxGuest(int maxGuest) {
        this.maxGuest = maxGuest;
    }
    
}
