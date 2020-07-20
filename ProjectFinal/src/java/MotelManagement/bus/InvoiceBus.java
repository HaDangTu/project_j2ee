package MotelManagement.bus;

import MotelManagement.dao.InvoiceDao;
import MotelManagement.dao.RoomDao;
import MotelManagement.dto.Invoice;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class InvoiceBus {

    private InvoiceDao invoiceDao;
    private RoomDao roomDao;
    
    public InvoiceBus() {
        invoiceDao = new InvoiceDao();
        roomDao = new RoomDao();
    }

    public String nextId() {
        return invoiceDao.nextId();
    }
    
    public boolean insert(Invoice invoice) {
        invoice.setId(nextId());
        return invoiceDao.insert(invoice);
    }

    public boolean update(Invoice invoice) {
        return invoiceDao.update(invoice);
    }

    public boolean delete(Invoice invoice) {
        return invoiceDao.delete(invoice);
    }

    /**
     * Lấy hóa đơn nợ
     *
     * @return
     */
    public List<Invoice> getDebtInvoices() {
        return invoiceDao.selectDebtInvoices();
    }

    /**
     * Lấy hóa đơn nợ của phòng
     *
     * @param roomId mã phòng
     * @return
     */
    public List<Invoice> getRoomDebtInvoices(String roomId) {
        return invoiceDao.selectRoomDebtInvoices(roomId);
    }
    
    /**
     * Lấy ngày hóa đơn của phòng theo tháng
     * @param roomId
     * @param month
     * @return 
     */
    
    public Date getDateInvoice(String roomId, int month, int year) {
        return invoiceDao.selectDateInvoice(roomId, month, year);
    }
    
    /**
     * Tính ngày bắt đầu hóa đơn
     * @param roomId mã phòng
     * @param month tháng
     * @return 
     */
    public Date calculateFromDate(String roomId, int month, int year) {
        Date startDate = roomDao.selectStartDate(roomId);
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.YEAR, year);
        
        return calendar.getTime();
    }
    
    /**
     * Tính ngày kết thúc hóa đơn
     * @param roomId mã phòng
     * @param fromDate tháng
     * @return 
     */
    public Date calculateToDate(Date fromDate) { 
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fromDate);
        
        calendar.add(Calendar.MONTH, 1);
        return calendar.getTime();
    }
    
    
    /**
     * Lấy hóa đơn tiền phòng trọ mới nhất của phòng
     * @param roomId mã phòng
     * @return hóa đơn
     */
    public Invoice getLastRoomInvoice(String roomId) {
        return invoiceDao.selectLastRoomInvoice(roomId);
    }
    
    /**
     * Lấy hóa đơn điện nước phòng trọ mới nhất của phòng
     * @param roomId mã phòng
     * @return hóa đơn
     */
    public Invoice getLastPowerInvoice(String roomId) {
        return invoiceDao.selectLastPowerInvoice(roomId);
    }
    
    public Invoice getRoomInvoice(String roomId, int month, int year) {
        return invoiceDao.selectRoomInvoice(roomId, month, year);
    }
    
    public Invoice getPowerInvoice(String roomId, int month, int year) {
        return invoiceDao.selectPowerInvoice(roomId, month, year);
    }
}
