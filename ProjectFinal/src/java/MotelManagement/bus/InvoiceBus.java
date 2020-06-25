package MotelManagement.bus;

import MotelManagement.dao.InvoiceDao;
import MotelManagement.dto.Invoice;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class InvoiceBus {

    private InvoiceDao invoiceDao;

    public InvoiceBus() {
        invoiceDao = new InvoiceDao();
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
    
    public Date getDateInvoice(String roomId, int month) {
        return invoiceDao.selectDateInvoice(roomId, month);
    }
    
    /**
     * Tính ngày bắt đầu hóa đơn
     * @param roomId mã phòng
     * @param month tháng
     * @return 
     */
    public Date calculateFromDate(String roomId, int month) {
        return getDateInvoice(roomId, month == 1 ? 12 : month - 1);
    }
    
    /**
     * Tính ngày kết thúc hóa đơn
     * @param roomId mã phòng
     * @param month tháng
     * @return 
     */
    public Date calculateToDate(String roomId, int month) {
        Date fromDate = calculateFromDate(roomId, month);
        
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
}
