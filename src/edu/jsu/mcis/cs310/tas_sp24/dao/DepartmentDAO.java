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
    
    private static final String QUERY_FIND = "SELECT * FROM department WHERE id = ?";

    DepartmentDAO(DAOFactory daoFactory) {

        this.daoFactory = daoFactory;

    }
    //dpfind(termid)
    
     public Department find(int id){
         Department dp = null;

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
                        String description = rs.getString("description");
                        int terminalid = rs.getInt("terminalid");
                        dp = new Department(id, description, terminalid);

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
        
         return dp;
         
     
     
}}