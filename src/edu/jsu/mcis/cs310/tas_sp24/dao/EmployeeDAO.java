/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.jsu.mcis.cs310.tas_sp24.dao;

import edu.jsu.mcis.cs310.tas_sp24.Badge;
import edu.jsu.mcis.cs310.tas_sp24.Department;
import edu.jsu.mcis.cs310.tas_sp24.Employee;
import edu.jsu.mcis.cs310.tas_sp24.EmployeeType;
import edu.jsu.mcis.cs310.tas_sp24.Shift;

import java.sql.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 *
 * @author TIMI
 */
public class EmployeeDAO {
    private final DAOFactory daoFactory;
    
    private static final String QUERY_FIND = "SELECT * FROM employee WHERE id = ?";
    private static final String QUERY_BADGE_FIND = "SELECT * FROM employee WHERE badgeid = ?";

    EmployeeDAO(DAOFactory daoFactory) {

        this.daoFactory = daoFactory;
}
    public Employee find(int id){
        Employee emp = null;

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
                        BadgeDAO badgedao = new BadgeDAO(daoFactory);
                        ShiftDAO shiftdao = new ShiftDAO(daoFactory);
                        DepartmentDAO departmentdao = new DepartmentDAO(daoFactory);
                        
                        String firstname = rs.getString("firstname");
                        String lastname = rs.getString("lastname");
                        String middlename = rs.getString("middlename");
                        
                        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                        LocalDate active = LocalDate.parse(rs.getString("active"), format);
                        EmployeeType employeeType = EmployeeType.values()[rs.getInt("employeeTypeID")];

                        Badge badge = badgedao.find(rs.getString("badgeid"));
                        Department department = departmentdao.find(rs.getInt("departmentid"));
                        Shift shift = shiftdao.find(badge);
                        
                         emp = new Employee(id,firstname,middlename,lastname,active,badge,department,shift,employeeType);

                    }

                    }

                }

            }

        
         
         catch (SQLException e) {

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
        
         return emp;
}
     
    public Employee find(Badge badge){
        
        Employee emp = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            Connection conn = daoFactory.getConnection();

            if (conn.isValid(0)) {

                ps = conn.prepareStatement(QUERY_BADGE_FIND);
                ps.setString(1, badge.getId());
                  

                boolean hasresults = ps.execute();

                if (hasresults) {

                    rs = ps.getResultSet();

                    while (rs.next()) {
                        
                        ShiftDAO shiftdao = new ShiftDAO(daoFactory);
                        DepartmentDAO departmentdao = new DepartmentDAO(daoFactory);

                        
                       
                        int id = rs.getInt("id");
                        String firstname = rs.getString("firstname");
                        String lastname = rs.getString("lastname");
                        String middlename = rs.getString("middlename");
                        
                        DateTimeFormatter formatt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        LocalDate active = LocalDate.parse(rs.getString("active"), format);
                        EmployeeType employeeType = EmployeeType.values()[rs.getInt("employeeTypeID")];

                        
                        Department department = departmentdao.find(rs.getInt("departmentid"));
                        Shift shift = shiftdao.find(rs.getInt("shiftid"));

                        emp = new Employee(id,firstname,middlename,lastname,active,badge,department,shift,employeeType);

                    }
                        
                    }

                }

            }

        
         
         catch (SQLException e) {

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
        
         return emp;
         //Should be returning Badge id instead of Employee object
}
}
