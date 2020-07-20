package MotelManagement.dao;

import MotelManagement.dto.PowerInfo;
import MotelManagement.util.Generator;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PowerInfoDao extends BaseDao {

    public PowerInfoDao() {
        super();
    }

    public String nextId() {
        String query = "SELECT id FROM power_infos ORDER BY id DESC LIMIT 1;";

        String id = "";
        ResultSet resultSet = dbConnection.select(query, null);
        try {
            if (resultSet.next()) {
                //Get last id in table
                id = resultSet.getString("id");
                //Generate next id
                id = Generator.nextId("IEW", id, true);
            } else {
                id = "IEW200001";
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            dbConnection.closeConnection();
        }

        return id;
    }

    public boolean insert(PowerInfo info) {
        String sql = "INSERT INTO power_infos (id, room_id, date, electricity_index,"
                + "water_index) VALUES (?, ?, ?, ?, ?);";

        Object[] parameters = new Object[]{
            info.getId(),
            info.getRoomId(),
            info.getDate(),
            info.getElectricityIndex(),
            info.getWaterIndex()
        };

        return dbConnection.save(sql, parameters);
    }

    public boolean update(PowerInfo info) {
        String sql = "UPDATE power_infos SET room_id = ?, date = ?, "
                + "electricity_index = ?, water_index = ? WHERE id = ?;";

        Object[] parameters = new Object[]{
            info.getRoomId(),
            info.getDate(),
            info.getElectricityIndex(),
            info.getWaterIndex(),
            info.getId()
        };

        return dbConnection.save(sql, parameters);
    }

    public boolean delete(PowerInfo info) {
        String sql = "DELETE FROM power_infos WHERE id = ?;";

        Object[] parameters = new Object[]{info.getId()};

        return dbConnection.save(sql, parameters);
    }

    /**
     * select chỉ số điện của phòng theo tháng.
     *
     * @param roomId mã phòng.
     * @param month tháng cần lấy chỉ số điện.
     * @return chỉ số điện của phòng.
     */
    public int selectElectricIndex(String roomId, int month, int year) {
        int electricIndex = 0;

        String query = "SELECT electricity_index FROM power_infos "
                + "WHERE room_id = ? AND month(date) = ? AND YEAR(date) = ?;";

        Object[] parameters = new Object[]{roomId, month, year};

        try {
            ResultSet resultSet = dbConnection.select(query, parameters);
            if (resultSet.next()) {
                electricIndex = resultSet.getInt("electricity_index");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            dbConnection.closeConnection();
        }

        return electricIndex;
    }

    /**
     * select chỉ số nước của phòng theo tháng.
     *
     * @param roomId mã phòng.
     * @param month tháng cần lấy chỉ số nước.
     * @return chỉ số nước của phòng.
     */
    public int selectWaterIndex(String roomId, int month, int year) {
        int waterIndex = 0;

        String query = "SELECT water_index FROM power_infos "
                + "WHERE room_id = ? AND month(date) = ? AND YEAR(date) = ?;";

        Object[] parameters = new Object[]{roomId, month, year};

        try {
            ResultSet resultSet = dbConnection.select(query, parameters);
            if (resultSet.next()) {
                waterIndex = resultSet.getInt("water_index");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            dbConnection.closeConnection();
        }

        return waterIndex;
    }

    public PowerInfo selectInfosByMonth(String room_id, int month) {
        PowerInfo powerInfo = null;

        String query = "SELECT * FROM power_infos WHERE room_id = ? AND MONTH(date) = ?";

        Object[] parameters = new Object[]{room_id, month};

        try {
            ResultSet resultSet = dbConnection.select(query, parameters);
            if (resultSet != null) {
                if (resultSet.next()) {
                    powerInfo = new PowerInfo();
                    powerInfo.setId(resultSet.getString("id"));
                    powerInfo.setDate(resultSet.getDate("date"));
                    powerInfo.setRoomId(resultSet.getString("room_id"));
                    powerInfo.setElectricityIndex(resultSet.getLong("electricity_index"));
                    powerInfo.setWaterIndex(resultSet.getLong("water_index"));
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            dbConnection.closeConnection();
        }

        return powerInfo;
    }
}
