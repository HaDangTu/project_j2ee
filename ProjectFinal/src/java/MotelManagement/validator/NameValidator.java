package MotelManagement.validator;

public class NameValidator extends Validator{
    
    protected int maxLength;
    
    protected int minLength;
    
    public NameValidator() {
        super("\\d|[~`!@#\\$%\\^&\\*\\(\\)_\\=\\+{}\\[\\],:;\"\\?<>\\/]");
        maxLength = 255;
        minLength = 3;
    }
    
    public NameValidator(int maxLength, int minLength) {
        super("\\d|[~`!@#\\$%\\^&\\*\\(\\)_\\=\\+{}\\[\\],:;\"\\?<>\\/]");
        
        this.maxLength = maxLength;
        this.minLength = minLength;
    }

    public NameValidator(String regex, int maxLength, int minLength) {
        super(regex);
        this.maxLength = maxLength;
        this.minLength = minLength;
    }

    
    @Override
    public String isValid(String string) {
        if (string == null) {
            return "Vui lòng nhập tên";
        }
        int length = string.trim().length();
        if (length < minLength || length > maxLength)
            return "Tên quá ngắn hoặc quá dài";
        
        matcher = pattern.matcher(string);
        if (matcher.find()) {
            return "Tên không chứa số hoặc các kí tự đặc biệt";
        }
        return "";
    }
    
    
}
