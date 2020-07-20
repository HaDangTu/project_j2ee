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
    public boolean isValid(String string) {
        int length = string.trim().length();
        if (length < minLength || length > maxLength)
            return false;
        return super.isValid(string);
    }
    
    
}
