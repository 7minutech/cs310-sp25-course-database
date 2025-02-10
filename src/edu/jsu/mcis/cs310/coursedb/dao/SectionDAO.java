package edu.jsu.mcis.cs310.coursedb.dao;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class SectionDAO {
    
    private static final String QUERY_FIND = "SELECT * FROM section WHERE termid = ? AND subjectid = ? AND num = ? ORDER BY crn";
    
    private final DAOFactory daoFactory;
    
    SectionDAO(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    
    public String find(int termid, String subjectid, String num) {
        
        String result = "[]";
        String key;
        PreparedStatement ps = null;
        ResultSet rs = null;    
        ResultSetMetaData rsmd = null;
        String value;
        int columnCount;
        JsonArray records = new JsonArray();
        try {
            
            Connection conn = daoFactory.getConnection();
            
            if (conn.isValid(0)) {
                
                // INSERT YOUR CODE HERE
                ps = conn.prepareStatement(QUERY_FIND, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, termid);
                ps.setString(2, subjectid);
                ps.setString(3, num);
                ps.executeQuery();
                rs = ps.getResultSet();
                rsmd = rs.getMetaData();
                columnCount = rsmd.getColumnCount();
                while(rs.next()) {
                    JsonObject sectionObj  = new JsonObject();
                    for (int i = 1; i <= columnCount; ++i) {
                        key = rsmd.getColumnLabel(i);
                        value = rs.getString(i);
                        sectionObj.put(key,value);
                    }
                    records.add(sectionObj);
                }
            }
            
        }
        
        catch (Exception e) { e.printStackTrace(); }
        
        finally {
            
            if (rs != null) { try { rs.close(); } catch (Exception e) { e.printStackTrace(); } }
            if (ps != null) { try { ps.close(); } catch (Exception e) { e.printStackTrace(); } }
            
        }
        
        return Jsoner.serialize(records);
        
    }
    
}