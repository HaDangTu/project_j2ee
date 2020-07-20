package MotelManagement.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public abstract class Validator {
    protected String regex;

    protected Pattern pattern;
    
    protected Matcher matcher;
    
    public Validator(String regex) {
        this.regex = regex;
        
        pattern = Pattern.compile(regex);
    }
    
    public boolean isValid(String string) {
        matcher = pattern.matcher(string);
        return !matcher.find();
    }
}
