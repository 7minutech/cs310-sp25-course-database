package edu.jsu.mcis.cs310.coursedb.dao;

import java.sql.*;
import com.github.cliftonlabs.json_simple.*;
import java.util.ArrayList;

public class DAOUtility {
    
    public static final int TERMID_FA24 = 1;
    
    public static String getResultSetAsJson(ResultSet rs) {
        
        JsonArray records = new JsonArray();
        JsonObject studentObj  = new JsonObject();
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
                    studentObj.put("studentid",rs.getInt("studentid"));
                    studentObj.put("termid",rs.getInt("termid"));
                    studentObj.put("crn",rs.getInt("crn"));
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
