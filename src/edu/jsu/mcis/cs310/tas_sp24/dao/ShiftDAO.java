/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.jsu.mcis.cs310.tas_sp24.dao;

import edu.jsu.mcis.cs310.tas_sp24.Badge;
import edu.jsu.mcis.cs310.tas_sp24.Shift;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ShiftDAO {
    private static final String QUERY_FIND_BY_ID = "SELECT * FROM shift WHERE id = ?";
    private static final String QUERY_FIND_BY_BADGE = "SELECT shiftid FROM employee WHERE badgeid = ?";

    private final DAOFactory daoFactory;

    ShiftDAO(DAOFactory daoFactory) {

        this.daoFactory = daoFactory;

    }
    

    public Shift find(int id) {

        Shift shift = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            Connection conn = daoFactory.getConnection();

            if (conn.isValid(0)) {

                ps = conn.prepareStatement(QUERY_FIND_BY_ID);
                ps.setInt(1, id);

                boolean hasresults = ps.execute();

                if (hasresults) {

                    rs = ps.getResultSet();

                    while (rs.next()) {

                        Map<String, Object> shiftInfo = new HashMap<>();
                        shiftInfo.put("description", rs.getString("description"));
                        shiftInfo.put("shiftstart", rs.getString("shiftstart"));
                        shiftInfo.put("shiftstop", rs.getString("shiftstop"));
                        shiftInfo.put("roundinterval", rs.getString("roundinterval"));
                        shiftInfo.put("graceperiod", rs.getString("graceperiod"));
                        shiftInfo.put("dockpenalty", rs.getString("dockpenalty"));
                        shiftInfo.put("lunchstart", rs.getString("lunchstart"));
                        shiftInfo.put("lunchstop", rs.getString("lunchstop"));
                        shiftInfo.put("lunchthreshold", rs.getString("lunchthreshold"));
                        shift = new Shift (shiftInfo);
                        

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

        return shift;

    }

    public Shift find(Badge badge) {

        Shift shift = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            Connection conn = daoFactory.getConnection();

            if (conn.isValid(0)) {

                ps = conn.prepareStatement(QUERY_FIND_BY_BADGE);
                ps.setString(1, badge.getId());

                boolean hasresults = ps.execute();

                if (hasresults) {

                    rs = ps.getResultSet();

                    if (rs.next()) {
                        
                        int shiftId = rs.getInt("shiftid");
                        shift = find(shiftId);
                

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

        return shift;

    }
    
}