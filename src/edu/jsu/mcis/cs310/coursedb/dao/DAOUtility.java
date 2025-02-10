package edu.jsu.mcis.cs310.coursedb.dao;

import java.sql.*;
import com.github.cliftonlabs.json_simple.*;
import java.util.ArrayList;

public class DAOUtility {
    
    public static final int TERMID_FA24 = 1;
    
    public static String getResultSetAsJson(ResultSet rs) {
        
        JsonArray records = new JsonArray();
        String key,value;
        int columnCount;
        
        
        try {
            // My init commit for repo
            // INSERT YOUR CODE HERE
            ResultSetMetaData metadata;
            metadata = rs.getMetaData();
            columnCount = metadata.getColumnCount();
            
            if (rs != null) {
                while(rs.next()){
                    JsonObject studentObj  = new JsonObject();
                    studentObj.put("studentid",rs.getString("studentid"));
                    studentObj.put("termid",rs.getString("termid"));
                    studentObj.put("crn",rs.getString("crn"));
                    records.add(studentObj);
                    
                }
                
                records.toString();
                
            }
            
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        return Jsoner.serialize(records);
        
    }
    
}
