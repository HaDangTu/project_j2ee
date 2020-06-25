package MotelManagement.dao;

import MotelManagement.dto.RoomType;
import MotelManagement.util.Generator;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomTypeDao extends BaseDao {

    public RoomTypeDao() {
        super();
    }

    public String nextId() {
        String query = "SELECT id FROM room_types ORDER BY id DESC LIMIT 1;";

        String id = "";
        ResultSet resultSet = dbConnection.select(query, null);
        try {
            if (resultSet.next()) {
                //Get last id in table
                id = resultSet.getString("id");
                //Generate next id
                id = Generator.nextId("T", id, false);
            }
            else id = "T01";
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return id;
    }
    
    public boolean insert(RoomType roomType) {
        String sql = "INSERT INTO room_types (id, name, num_of_guest, price) "
                + "VALUES (?, ?, ?, ?);";

        Object[] parameters = new Object[]{
            roomType.getId(),
            roomType.getName(),
            roomType.getNumOfGuest(),
            roomType.getPrice()
        };

        return dbConnection.save(sql, parameters);
    }

    public boolean update(RoomType roomType) {
        String sql = "UPDATE room_types SET name = ?, num_of_guest = ?, price = ?"
                + "WHERE id = ?;";

        Object[] parameters = new Object[]{
            roomType.getName(),
            roomType.getNumOfGuest(),
            roomType.getPrice(),
            roomType.getId()
        };

        return dbConnection.save(sql, parameters);
    }

    public boolean delete(RoomType roomType) {
        String sql = "DELETE FROM room_types WHERE id = ?;";

        Object[] parameters = new Object[]{
            roomType.getId()
        };

        return dbConnection.save(sql, parameters);
    }

    public List<RoomType> selectAll() {
        String query = "SELECT * FROM room_types;";
        List<RoomType> roomTypes = new ArrayList<>();

        ResultSet resultSet = dbConnection.select(query, null);

        try {
            if (resultSet != null) {
                while (resultSet.next()) {
                    RoomType roomType = new RoomType();
                    roomType.setId(resultSet.getString("id"));
                    roomType.setName(resultSet.getString("name"));
                    roomType.setNumOfGuest(resultSet.getInt("num_of_guest"));
                    roomType.setPrice(resultSet.getDouble("price"));

                    roomTypes.add(roomType);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
        }

        return roomTypes;
    }

    /**
     * Select roomType by id
     *
     * @param roomTypeId
     * @return roomType
     */
    public RoomType selectRoomType(String roomTypeId) {
        RoomType roomType = null;

        String sql = "SELECT * FROM room_types WHERE id = ?;";

        Object[] parameters = new Object[]{roomTypeId};

        try {
            ResultSet resultSet = dbConnection.select(sql, parameters);

            if (resultSet.next()) {
                roomType = new RoomType();
                roomType.setId(resultSet.getString("id"));
                roomType.setName(resultSet.getString("name"));
                roomType.setNumOfGuest(resultSet.getInt("num_of_guest"));
                roomType.setPrice(resultSet.getDouble("price"));
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return roomType;
    }
}
