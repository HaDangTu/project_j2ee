package MotelManagement.dto;

public class Parameter {

    public String id;
    public String name;
    public String type;
    public String value;
    public boolean state;

    public static enum TYPE { INT, DOUBLE, STRING, FLOAT, BOOLEAN, LONG, BYTE, CHAR }
    
    public Parameter() {
    }

    public Parameter(String id, String name, String type, String value, boolean state) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.value = value;
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Parameter{" + "id=" + id + ", name=" + name + 
                ", type=" + type + ", value=" + value + ", state=" + state + '}';
    }

    
}
