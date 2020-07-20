package MotelManagement.dbconnection;


public class DbConfiguration {
    public static final String DB_NAME = "emotel";
    public static final String PORT = "3306";
    public static final String USER_NAME = "root";
    public static final String PASSWORD = "Hatu1612";
    public static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final int MIN_POOL_SIZE = 4;
    public static final int MAX_POOL_SIZE = 20;
    //jdbc:mysql://localhost:3306/homework
    public static final String CONNECTION_URL = "jdbc:mysql://localhost:" +
            PORT + "/" + DB_NAME + "?allowPublicKeyRetrieval=true&useSSL=false";
}
