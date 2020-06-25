package MotelManagement.dao;

import MotelManagement.dbconnection.DbConnection;


public abstract class BaseDao {
    DbConnection dbConnection;
    
    public BaseDao() {
        dbConnection = new DbConnection();
    }
}
