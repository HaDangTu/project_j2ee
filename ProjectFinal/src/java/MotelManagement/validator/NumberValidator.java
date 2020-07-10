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
    public boolean isValid(String string) {
        if (string.equals(""))
            return false;

        if (super.isValid(string)) {
            try {
                NumberFormat numberFormat = NumberFormat.getInstance();
                double num = numberFormat.parse(string).doubleValue();

                if (num < min || num > max)
                    return false;
            }
            catch (Exception e) {
                return false;
            }
            return true;
        }
        else return false;
    }
    
    
}
