package MotelManagement.dao;

import MotelManagement.dto.Guest;
import MotelManagement.dto.Room;
import MotelManagement.util.Generator;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RoomDao extends BaseDao {

    public RoomDao() {
        super();
    }

    public String nextId() {
        String query = "SELECT id FROM rooms ORDER BY id DESC LIMIT 1;";

        String id = "";
        ResultSet resultSet = dbConnection.select(query, null);
        try {
            if (resultSet.next()) {
                //Get last id in table
                id = resultSet.getString("id");
                //Generate next id
                id = Generator.nextId("R", id, false);
            }
            else id = "R0001";
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return id;
    }
    
    public boolean insert(Room room) {
        String sql = "INSERT INTO rooms (id, name, room_type_id, user_id) VALUES"
                + "(?, ?, ?, ?);";

        Object[] parameters = new Object[]{
            room.getId(),
            room.getName(),
            room.getRoomTypeId(),
            room.getUserId()
        };

        return dbConnection.save(sql, parameters);
    }

    public boolean update(Room room) {
        String sql = "UPDATE rooms SET name = ?, room_type_id = ?, user_id = ? "
                + "WHERE id = ?;";
        Object[] parameters = new Object[]{
            room.getName(),
            room.getRoomTypeId(),
            room.getUserId(),
            room.getId()
        };

        return dbConnection.save(sql, parameters);
    }

    public boolean delete(Room room) {
        String sql = "DELETE FROM rooms WHERE id = ?;";

        Object[] parameters = new Object[]{room.getId()};

        return dbConnection.save(sql, parameters);
    }

    public List<Room> selectAll() {
        String query = "SELECT * FROM rooms;";
        List<Room> rooms = new ArrayList<>();

        try {
            ResultSet resultSet = dbConnection.select(query, null);

            if (resultSet != null) {
                while (resultSet.next()) {
                    Room room = new Room(
                            resultSet.getString("id"),
                            resultSet.getString("name"),
                            resultSet.getString("room_type_id"),
                            resultSet.getString("user_id"));

                    rooms.add(room);
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            System.err.println(e.getCause());
        }

        return rooms;
    }

    /**
     * Lấy danh sách các phòng đang cho thuê
     *
     * @return danh sách phòng đang cho thuê
     */
    public List<Room> selectRentedRooms() {
        String query = "SELECT * FROM rooms WHERE id in (SELECT room_id FROM "
                + "guests WHERE state_id = 'S01');";

        List<Room> rooms = new ArrayList<>();

        try {
            ResultSet resultSet = dbConnection.select(query, null);

            if (resultSet != null) {
                while (resultSet.next()) {
                    Room room = new Room(
                            resultSet.getString("id"),
                            resultSet.getString("name"),
                            resultSet.getString("room_type_id"),
                            resultSet.getString("user_id"));

                    rooms.add(room);
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            System.err.println(e.getCause());
        }

        return rooms;
    }

    /**
     * Lấy danh sách các phòng chưa cho thuê
     * @return danh sách các phòng chưa cho thuê
     */
    public List<Room> selectNotRentedRooms() {
        String query = "SELECT * FROM rooms WHERE id not in (SELECT room_id FROM "
                + "guests WHERE state_id = 'S01');";
        
        List<Room> rooms = new ArrayList<>();

        try {
            ResultSet resultSet = dbConnection.select(query, null);

            if (resultSet != null) {
                while (resultSet.next()) {
                    Room room = new Room(
                            resultSet.getString("id"),
                            resultSet.getString("name"),
                            resultSet.getString("room_type_id"),
                            resultSet.getString("user_id"));

                    rooms.add(room);
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            System.err.println(e.getCause());
        }

        return rooms;
    }
    
    /**
     * Lấy số lượng người tối đa trong phòng.
     * @param roomId mã phòng
     * @return max
     */
    public int selectMaxNumGuest(String roomId) {
        int max = 0;
        
        String query = "SELECT num_of_guest from room_types WHERE id = ("
                + "SELECT room_type_id from rooms where id = ?);";
        
        Object[] parameters = new Object[] { roomId };
        
        try {
            ResultSet resultSet = dbConnection.select(query, parameters);
            
            if (resultSet.next()) {
                max = resultSet.getInt("num_of_guest");
            }
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
            System.err.println(e.getCause());
        }
        
        return max;
    }
    
    /**
     * Lấy danh sách khách trọ của 1 phòng
     *
     * @param roomId mã phòng
     * @return danh sách khách trọ
     */
    public List<Guest> selectGuests(String roomId) {
        String query = "SELECT * FROM guests WHERE room_id = ?;";
        List<Guest> guests = new ArrayList<>();

        Object[] parameters = new Object[]{ roomId };

        try {
            ResultSet resultSet = dbConnection.select(query, parameters);

            if (resultSet != null) {
                while (resultSet.next()) {
                    Guest guest = new Guest();
                    guest.setId(resultSet.getString("id"));
                    guest.setName(resultSet.getString("name"));
                    guest.setBirthday(resultSet.getDate("birthday"));
                    guest.setGenderId(resultSet.getString("gender_id"));
                    guest.setIndentityNumber(resultSet.getString("identity_number"));
                    guest.setHomeTown(resultSet.getString("home_town"));
                    guest.setOccupation(resultSet.getString("occupation"));
                    guest.setRoomId(resultSet.getString("room_id"));

                    guests.add(guest);
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            System.err.println(e.getCause());
        }

        return guests;
    }

    
    /**
     * select room by id
     * @param roomId mã phòng
     * @return room
     */
    public Room selectRoom(String roomId) {
        Room room = null;

        String query = "SELECT * FROM rooms WHERE id = ?";

        Object[] parameters = new Object[]{roomId};

        try {
            ResultSet resultSet = dbConnection.select(query, parameters);

            if (resultSet.next()) {
                room = new Room(
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getString("room_type_id"),
                        resultSet.getString("user_id"));
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return room;
    }
    
    /**
     * Lấy tiền thuê của phòng
     * @param roomId mã phòng
     * @return tiền thuê phòng
     */
    public double selectMoney(String roomId) {
        double money = 0;
        
        String query = "SELECT price FROM room_types WHERE id = ("
                + "SELECT room_type_id from rooms where id = ?);";
        
        Object[] parameters = new Object[] { roomId };
        
        try {
            ResultSet resultSet = dbConnection.select(query, parameters);
            
            if (resultSet.next()) {
                money = resultSet.getDouble("price");
            }
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
            System.err.println(e.getCause());
        }
        
        return money;
    }
    
    /**
     * Lấy ngày bắt đầu thuê của phòng = ngày bắt đầu ở của khách trọ
     * @param roomId mã phòng
     * @return ngày bắt đầu thuê
     */
    public Date selectStartDate(String roomId) {
        String query = "SELECT min(start_date) AS start_date"
                + " FROM guests WHERE room_id = ? AND state_id = 'S01';";
        Date date = null;
        
        Object[] parameters = new Object[] { roomId };
        
        try {
            ResultSet resultSet = dbConnection.select(query, parameters);
            
            if (resultSet.next()) {
                date = resultSet.getDate("start_date");
            }
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
            System.err.println(e.getCause());
        }
        
        return date;
    }

}
