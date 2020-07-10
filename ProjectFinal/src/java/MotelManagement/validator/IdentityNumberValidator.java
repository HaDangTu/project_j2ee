package MotelManagement.validator;

public class IdentityNumberValidator extends NameValidator {
    
    public IdentityNumberValidator() {
        super("[a-zA-Z]|\\s|[~`!@#\\$%\\^&\\*\\(\\)_\\+{}\\[\\]:;\"\\?<>\\/]", 10, 9);
    }

    public IdentityNumberValidator(int max, int min) {
        super("[a-zA-Z]|\\s|[~`!@#\\$%\\^&\\*\\(\\)_\\+{}\\[\\]:;\"\\?<>\\/]", max, min);
    }
    
    @Override
    public boolean isValid(String string) {
        int length = string.length();
        if (length < minLength || length > maxLength)
            return false;
        return super.isValid(string); 
    }
}
