package MotelManagement.dao;

import MotelManagement.dto.Invoice;
import MotelManagement.util.Generator;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InvoiceDao extends BaseDao {

    public InvoiceDao() {
        super();
    }

    public String nextId() {
        String query = "SELECT id FROM invoices ORDER BY id DESC LIMIT 1;";

        String id = "";
        ResultSet resultSet = dbConnection.select(query, null);
        try {
            if (resultSet.next()) {
                //Get last id in table
                id = resultSet.getString("id");
                //Generate next id
                id = Generator.nextId("I", id, true);
            } else {
                id = "I2000000001";
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            dbConnection.closeConnection();
        }
        return id;
    }

    public boolean insert(Invoice invoice) {
        String sql = "INSERT INTO invoices (id, room_id, date, collection_date,"
                + "content, debt, proceeds, excess_cash) VALUES "
                + "(?, ?, ?, ?, ?, ?, ?, ?);";

        Object[] parameters = new Object[]{
            invoice.getId(),
            invoice.getRoomId(),
            invoice.getDate(),
            invoice.getCollectionDate(),
            invoice.getContent(),
            invoice.getDebt(),
            invoice.getProceeds(),
            invoice.getExcessCash()
        };

        return dbConnection.save(sql, parameters);
    }

    public boolean update(Invoice invoice) {
        String sql = "UPDATE invoices SET room_id = ?, date = ?, "
                + "collection_date = ?, content = ?, debt = ?, proceeds = ?,"
                + "excess_cash = ? WHERE id = ?;";

        Object[] parameters = new Object[]{
            invoice.getRoomId(),
            invoice.getDate(),
            invoice.getCollectionDate(),
            invoice.getContent(),
            invoice.getDebt(),
            invoice.getProceeds(),
            invoice.getExcessCash(),
            invoice.getId()
        };

        return dbConnection.save(sql, parameters);
    }

    public boolean delete(Invoice invoice) {
        String sql = "DELETE FROM invoices WHERE id = ?";

        Object[] parameters = new Object[]{invoice.getId()};

        return dbConnection.save(sql, parameters);
    }

    /**
     * Lấy hóa đơn nợ
     *
     * @return
     */
    public List<Invoice> selectDebtInvoices() {
        List<Invoice> invoices = new ArrayList<>();

        String query = "SELECT * FROM invoices WHERE debt > 0;";

        try {
            ResultSet resultSet = dbConnection.select(query, null);

            if (resultSet != null) {
                while (resultSet.next()) {
                    Invoice invoice = new Invoice();
                    invoice.setId(resultSet.getString("id"));
                    invoice.setRoomId(resultSet.getString("room_id"));
                    invoice.setDate(resultSet.getDate("date"));
                    invoice.setCollectionDate(resultSet.getDate("collection_date"));
                    invoice.setContent(resultSet.getString("content"));
                    invoice.setDebt(resultSet.getDouble("debt"));
                    invoice.setProceeds(resultSet.getDouble("proceeds"));
                    invoice.setExcessCash(resultSet.getDouble("excess_cash"));

                    invoices.add(invoice);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            dbConnection.closeConnection();
        }
        return invoices;
    }

    /**
     * Lấy hóa đơn nợ của phòng
     *
     * @param roomId mã phòng
     * @return
     */
    public List<Invoice> selectRoomDebtInvoices(String roomId) {
        List<Invoice> invoices = new ArrayList<>();

        String query = "SELECT * FROM invoices WHERE room_id = ? AND debt > 0;";

        Object[] parameters = new Object[]{roomId};

        try {
            ResultSet resultSet = dbConnection.select(query, parameters);

            if (resultSet != null) {
                while (resultSet.next()) {
                    Invoice invoice = new Invoice();
                    invoice.setId(resultSet.getString("id"));
                    invoice.setRoomId(resultSet.getString("room_id"));
                    invoice.setDate(resultSet.getDate("date"));
                    invoice.setCollectionDate(resultSet.getDate("collection_date"));
                    invoice.setContent(resultSet.getString("content"));
                    invoice.setDebt(resultSet.getDouble("debt"));
                    invoice.setProceeds(resultSet.getDouble("proceeds"));
                    invoice.setExcessCash(resultSet.getDouble("excess_cash"));

                    invoices.add(invoice);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            dbConnection.closeConnection();
        }

        return invoices;
    }

    /**
     * Lấy ngày hóa đơn của phòng theo tháng
     *
     * @param roomId
     * @param month
     * @return
     */
    public Date selectDateInvoice(String roomId, int month) {
        Date date = null;
        String query = "SELECT date FROM invoices WHERE roomId = ? AND "
                + "MONTH(date) = ?";

        Object[] parameters = new Object[]{roomId, month};

        try {
            ResultSet resultSet = dbConnection.select(query, parameters);

            if (resultSet.next()) {
                date = resultSet.getDate("start_date");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            System.err.println(e.getCause());
        }finally {
            dbConnection.closeConnection();
        }

        return date;
    }

    /**
     * Lấy hóa đơn tiền phòng trọ mới nhất của phòng
     *
     * @param roomId mã phòng
     * @return hóa đơn
     */
    public Invoice selectLastRoomInvoice(String roomId) {
        Invoice invoice = null;

        String query = "SELECT * FROM invoices WHERE room_id = ? "
                + "AND content like '%Tiền phòng%'";

        Object[] parameters = new Object[]{roomId};

        try {
            ResultSet resultSet = dbConnection.select(query, parameters);

            if (resultSet != null) {
                while (resultSet.next()) {
                    invoice = new Invoice();
                    invoice.setId(resultSet.getString("id"));
                    invoice.setRoomId(resultSet.getString("room_id"));
                    invoice.setDate(resultSet.getDate("date"));
                    invoice.setCollectionDate(resultSet.getDate("collection_date"));
                    invoice.setContent(resultSet.getString("content"));
                    invoice.setDebt(resultSet.getDouble("debt"));
                    invoice.setProceeds(resultSet.getDouble("proceeds"));
                    invoice.setExcessCash(resultSet.getDouble("excess_cash"));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            dbConnection.closeConnection();
        }

        return invoice;
    }

    /**
     * Lấy hóa đơn điện nước phòng trọ mới nhất của phòng
     *
     * @param roomId mã phòng
     * @return hóa đơn
     */
    public Invoice selectLastPowerInvoice(String roomId) {
        Invoice invoice = null;

        Object[] parameters = new Object[]{roomId};

        String query = "SELECT * FROM invoices WHERE room_id = ? "
                + "AND content like '%Tiền điện%'";

        try {
            ResultSet resultSet = dbConnection.select(query, parameters);

            if (resultSet != null) {
                while (resultSet.next()) {
                    invoice = new Invoice();
                    invoice.setId(resultSet.getString("id"));
                    invoice.setRoomId(resultSet.getString("room_id"));
                    invoice.setDate(resultSet.getDate("date"));
                    invoice.setCollectionDate(resultSet.getDate("collection_date"));
                    invoice.setContent(resultSet.getString("content"));
                    invoice.setDebt(resultSet.getDouble("debt"));
                    invoice.setProceeds(resultSet.getDouble("proceeds"));
                    invoice.setExcessCash(resultSet.getDouble("excess_cash"));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            dbConnection.closeConnection();
        }

        return invoice;
    }
}
