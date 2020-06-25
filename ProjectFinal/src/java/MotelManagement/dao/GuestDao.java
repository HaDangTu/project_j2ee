package MotelManagement.dao;

import MotelManagement.dto.Guest;
import MotelManagement.util.Generator;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GuestDao extends BaseDao {

    public GuestDao() {
        super();
    }

    public String nextId() {
        String query = "SELECT id FROM guests ORDER BY id DESC LIMIT 1;";

        String id = "";
        ResultSet resultSet = dbConnection.select(query, null);
        try {
            if (resultSet.next()) {
                //Get last id in table
                id = resultSet.getString("id");
                //Generate next id
                id = Generator.nextId("G", id, false);
            }
            else id = "G000001";
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return id;
    }

    public boolean insert(Guest guest) {
        String sql = "INSERT INTO guests (id, name, birthday, gender_id, "
                + "identity_number, home_town, occupation, start_date, state_id, room_id) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        Object[] parameters = new Object[]{
            guest.getId(),
            guest.getName(),
            guest.getBirthday(),
            guest.getGenderId(),
            guest.getIndentityNumber(),
            guest.getHomeTown(),
            guest.getOccupation(),
            guest.getStartDate(),
            guest.getStateId(),
            guest.getRoomId()
        };

        return dbConnection.save(sql, parameters);
    }

    public boolean update(Guest guest) {
        String sql = "UPDATE guests SET name = ?, birthday = ?, gender_id = ?, "
                + "identity_number = ?, home_town = ?, occupation = ?, "
                + "start_date = ?, state_id = ?, room_id = ? WHERE id = ?;";

        Object[] parameters = new Object[]{
            guest.getName(),
            guest.getBirthday(),
            guest.getGenderId(),
            guest.getIndentityNumber(),
            guest.getHomeTown(),
            guest.getOccupation(),
            guest.getStartDate(),
            guest.getStateId(),
            guest.getRoomId(),
            guest.getId()
        };

        return dbConnection.save(sql, parameters);
    }

    public boolean delete(Guest guest) {
        String sql = "DELETE FROM guests WHERE id = ?;";

        Object[] parameters = new Object[]{
            guest.getId()};

        return dbConnection.save(sql, parameters);
    }

    public List<Guest> selectAll() {
        List<Guest> guests = new ArrayList<>();

        String query = "SELECT * FROM guests";

        ResultSet resultSet = dbConnection.select(query, null);

        try {
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
            System.err.print(e.getCause());
        }

        return guests;
    }

    /**
     * select guest by id
     *
     * @param guestId mã khách hàng
     * @return khách hàng
     */
    public Guest selectGuest(String guestId) {
        Guest guest = null;

        String query = "SELECT * FROM guests WHERE id = ?;";

        Object[] parameters = new Object[]{guestId};

        try {
            ResultSet resultSet = dbConnection.select(query, parameters);
            if (resultSet.next()) {
                guest = new Guest();
                guest.setId(resultSet.getString("id"));
                guest.setName(resultSet.getString("name"));
                guest.setBirthday(resultSet.getDate("birthday"));
                guest.setGenderId(resultSet.getString("gender_id"));
                guest.setIndentityNumber(resultSet.getString("indentity_number"));
                guest.setHomeTown(resultSet.getString("home_town"));
                guest.setOccupation(resultSet.getString("occupation"));
                guest.setRoomId(resultSet.getString("room_id"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return guest;
    }
}
