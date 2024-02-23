/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.jsu.mcis.cs310.tas_sp24.dao;

import edu.jsu.mcis.cs310.tas_sp24.Department;

import java.sql.*;

import java.sql.SQLException;

/**
 *
 * @author egmck
 */
public class DepartmentDAO {
    
    private final DAOFactory daoFactory;
    
    private static final String QUERY_FIND = "SELECT * FROM badge WHERE id = ?";

    DepartmentDAO(DAOFactory daoFactory) {

        this.daoFactory = daoFactory;

    }
    //dpfind(termid)
    
     public String find(String termid){
         Department dp = null;

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            Connection conn = daoFactory.getConnection();

            if (conn.isValid(0)) {

                ps = conn.prepareStatement(QUERY_FIND);
                ps.setString(1, termid);

                boolean hasresults = ps.execute();

                if (hasresults) {

                    rs = ps.getResultSet();

                    while (rs.next()) {

                        //String description = rs.getString("description");
                        dp = new Department(termid);

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
        
         return department;
         
     
     //Pull termid
     //Update it as well into Department class
}}