/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.jsu.mcis.cs310.tas_sp24.dao;

/**
 *
 * @author egmck
 */
public class DepartmentDAO {
    
    private final DAOFactory daoFactory;

    DepartmentDAO(DAOFactory daoFactory) {

        this.daoFactory = daoFactory;

    }
    dpfind(terminalID)
    
     public String find(int terminalD){
        
         
     }
}
             /*
      public String find(int termid, String subjectid, String num) {
        
        String result = "[]";
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        ResultSetMetaData rsmd = null;
        
        try {
            
            Connection conn = daoFactory.getConnection();
            
            if (conn.isValid(0)) {
                
                //TermID should be an int
                //SubjectID and Course Number should be strings
                String QUERY_FIND = "SELECT * FROM section WHERE subjectid = ? AND num = ? AND termid = ? ORDER BY crn";
                ps = conn.prepareStatement(QUERY_FIND);
                ps.setString(1, subjectid);
                ps.setString(2, num);
                ps.setInt(3, termid);
                rs = ps.executeQuery();
                
                while(rs.next())
                {
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.put("crn", rs.getInt("crn"));
                    jsonObject.put("subjectid", rs.getString("subjectid"));
                    jsonObject.put("num", rs.getString("num"));
                    jsonObject.put("termid", rs.getInt("termid"));
                    jsonArray.add(jsonObject);
                }
                
                
            }
            
        }
        
        catch (Exception e) { e.printStackTrace(); }
        
        finally {
            
            if (rs != null) { try { rs.close(); } catch (Exception e) { e.printStackTrace(); } }
            if (ps != null) { try { ps.close(); } catch (Exception e) { e.printStackTrace(); } }
            
        }
        
        //return result;
        return jsonArray.toString();
        
    }
    
}
    
}
