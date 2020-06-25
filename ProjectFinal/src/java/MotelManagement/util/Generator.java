package MotelManagement.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.DatatypeConverter;

public class Generator {

    /**
     * Generate next id
     *
     * @param prefix tiền tố vd USR, U, I...
     * @param previousId id của item cuối cùng trong danh sách vd U000001
     * @param addTime add 2 số cuối của năm (20, 21, 22...) vào id
     * @return next id vd U000002
     */
    public static String nextId(String prefix, String previousId, boolean addTime) {

        if (addTime) {
            //get present year
            Calendar calendar = Calendar.getInstance();
            Integer year = calendar.get(Calendar.YEAR);

            //lấy 2 số cuối của năm add vào prefix
            String yearStr = year.toString().substring(2);
            prefix = prefix.concat(yearStr);

            //thay thế old prefix bằng prefix mới
            String oldPrefix = previousId.substring(0, prefix.length());
            previousId = previousId.replace(oldPrefix, prefix);
        }
        //Lấy phần postfix của id
        String postfix = previousId.substring(previousId.indexOf(prefix) + prefix.length());

        //tăng id 1 đơn vị
        String oldId = Integer.valueOf(postfix).toString();
        String newId = String.valueOf((Integer.valueOf(oldId) + 1));

        if (newId.length() > oldId.length()) {
            StringBuilder builder = new StringBuilder(oldId);
            builder.insert(0, "0");

            oldId = builder.toString();
        }

        previousId = previousId.replace(oldId, newId);

        return previousId;
    }

    public static String hashPassword(String password) {
        
        String passwordHashed = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            
            byte[] input = password.getBytes();
            md.update(input);
            
            byte[] digest = md.digest();
            
            passwordHashed = DatatypeConverter.printHexBinary(digest).toUpperCase();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Generator.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return passwordHashed;
    }
}
