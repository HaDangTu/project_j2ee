package MotelManagement.validator;

public class IdentityNumberValidator extends NameValidator {
    
    public IdentityNumberValidator() {
        super("[a-zA-Z]|\\s|[~`!@#\\$%\\^&\\*\\(\\)_\\+{}\\[\\]:;\"\\?<>\\/]", 10, 9);
    }

    public IdentityNumberValidator(int max, int min) {
        super("[a-zA-Z]|\\s|[~`!@#\\$%\\^&\\*\\(\\)_\\+{}\\[\\]:;\"\\?<>\\/]", max, min);
    }
    
    @Override
    public String isValid(String string) {
        
        if (string  == null) {
            return "Vui lòng nhập CMND";
        }
        
        int length = string.length();
        if (length < minLength || length > maxLength)
            return "Số cmnd tối thiểu " + minLength + "số và tối đa " + maxLength + "số";
        
        matcher = pattern.matcher(string);
        if (matcher.find()) {
            return "Số CMND không kí tự hoặc kí tự đặc biệt";
        }
        
        return ""; 
    }
}
