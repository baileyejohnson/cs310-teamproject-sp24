/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.jsu.mcis.cs310.tas_sp24.dao;

import edu.jsu.mcis.cs310.tas_sp24.Badge;
import edu.jsu.mcis.cs310.tas_sp24.Department;
import edu.jsu.mcis.cs310.tas_sp24.Employee;
import edu.jsu.mcis.cs310.tas_sp24.EventType;
import edu.jsu.mcis.cs310.tas_sp24.Punch;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;

public class PunchDAO {
    private static final String QUERY_FIND = "SELECT * FROM event WHERE id = ?";
    private static final String QUERY_CREATE = "INSERT INTO event(terminalid,badgeid,timestamp,eventtypeid)VALUES(?,?,?,?)";
    private static final String QUERY_LIST = "SELECT * FROM event WHERE badgeid = ? ORDER BY timestamp";

    private final DAOFactory daoFactory;

    PunchDAO(DAOFactory daoFactory) {

        this.daoFactory = daoFactory;

    }

    public Punch find(Integer id) {

        Punch punch = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            Connection conn = daoFactory.getConnection();

            if (conn.isValid(0)) {

                ps = conn.prepareStatement(QUERY_FIND);
                ps.setInt(1, id);

                boolean hasresults = ps.execute();

                if (hasresults) {

                    rs = ps.getResultSet();

                    while (rs.next()) {
                         
                         LocalDateTime timestamp = rs.getTimestamp("timestamp").toLocalDateTime();
                         int terminalId = rs.getInt("terminalid");

                       // Getting badge
                        String badgeid = rs.getString("badgeid");
                        Badge badge = daoFactory.getBadgeDAO().find(badgeid);

                        // Getting punch type 
                        EventType punchtype = EventType.values()[rs.getInt("eventtypeid")];

                        // Getting timestamp
                        LocalDateTime originaltimestamp = rs.getTimestamp("timestamp").toLocalDateTime();

                        // Creating Punch object
                        punch = new Punch(id, terminalId, badge, originaltimestamp, punchtype);

                        }
                    }

                }

            

        } catch (SQLException e) {

            throw new DAOException(e.getMessage());

        } finally {

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new DAOException(e.getMessage());
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    throw new DAOException(e.getMessage());
                }
            }

        }

        return punch;

    }
    
    public Integer create(Punch punch) {

        Integer punchId = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        EmployeeDAO employeedao = daoFactory.getEmployeeDAO();
        DepartmentDAO departmentdao = daoFactory.getDepartmentDAO();
        Employee emp = employeedao.find(punch.getBadge());
        
        Integer Empid =emp.getId();
        Department department = departmentdao.find(Empid);
        Integer Terminalid = department.getTermid();
        
        
        
        
        if (Objects.equals(Terminalid, punch.getTerminalid())){

            try {

                Connection conn = daoFactory.getConnection();

                if (conn.isValid(0)) {

                    ps = conn.prepareStatement(QUERY_CREATE,PreparedStatement.RETURN_GENERATED_KEYS);
                    ps.setInt(1, punch.getTerminalid());
                    ps.setString(2, punch.getBadge().getId());
                    ps.setTimestamp(3, Timestamp.valueOf(punch.getOriginaltimestamp().withNano(0)));
                    ps.setInt(4, punch.getPunchtype().ordinal());


                    int rowAffected = ps.executeUpdate();

                    if (rowAffected == 1) {

                        rs = ps.getGeneratedKeys();

                        if (rs.next()) {
                            punchId = rs.getInt(1);

                        
                        }
                    }

                }

            

        } catch (SQLException e) {

            throw new DAOException(e.getMessage());

        } finally {

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new DAOException(e.getMessage());
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    throw new DAOException(e.getMessage());
                }
            }

        }
    }
        return punchId;

    
    
    }
    


       public ArrayList list (Badge badge , LocalDate date) {
           
        ArrayList <Punch> list = new ArrayList();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
         try {

                Connection conn = daoFactory.getConnection();

                if (conn.isValid(0)) {
                    
                    ps = conn.prepareStatement(QUERY_LIST);
                    ps.setString(1, badge.getId());

                    boolean hasresults = ps.execute();

                    if (hasresults) {

                        rs = ps.getResultSet();

                        while (rs.next()) {
                            LocalDate l_date = rs.getTimestamp("timestamp").toLocalDateTime().toLocalDate();
                            

                                if (l_date.equals(date)) {
                                  int id = rs.getInt("id");
                                  list.add(find(id));
                        }

                    }

                }
                    

            }
                
            
                    

                             

                    
                        
                        
                    

                

            

        } catch (SQLException e) {

            throw new DAOException(e.getMessage());

        } finally {

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new DAOException(e.getMessage());
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    throw new DAOException(e.getMessage());
                }
            }

        }
    }
        return list;

    
    
    }
}

