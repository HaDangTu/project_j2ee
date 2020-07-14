package MotelManagement.validator;
        
import java.text.NumberFormat;


public class NumberValidator extends Validator{
    protected long max;
    
    protected long min;
    
    public NumberValidator() {
        super(".[^0-9.,]");
        this.max = 1000000;
        this.min = 0;
    }

    public NumberValidator(int max, int min) {
        super(".[^0-9.,]");
        
        this.max = max;
        this.min = min;
    }

    
    @Override
    public String isValid(String string) {
        if (string.equals(""))
            return "Không được trống";

        matcher = pattern.matcher(string);
        
        if (!matcher.find()) {
            try {
                NumberFormat numberFormat = NumberFormat.getInstance();
                double num = numberFormat.parse(string).doubleValue();

                if (num < min || num > max)
                    return "Số nằm trong khoảng [" + max + ", " + min + "]";
            }
            catch (Exception e) {
                return "Số không hợp lệ";
            }
            return "";
        }
        else 
            return "Số không chứa kí tự đặc biệt";
    }
    
    
}
