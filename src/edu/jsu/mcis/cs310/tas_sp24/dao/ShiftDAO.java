/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.jsu.mcis.cs310.tas_sp24.dao;

import edu.jsu.mcis.cs310.tas_sp24.Badge;
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
    

    public Shift findById(Integer id) {

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
                        shiftInfo.put("shiftstart", rs.getTime("shiftstart"));
                        shiftInfo.put("shiftstop", rs.getTime("shiftstop"));
                        shiftInfo.put("roundinterval", rs.getInt("roundinterval"));
                        shiftInfo.put("graceperiod", rs.getInt("graceperiod"));
                        shiftInfo.put("dockpenalty", rs.getInt("dockpenalty"));
                        shiftInfo.put("lunchstart", rs.getTime("lunchstart"));
                        shiftInfo.put("lunchstop", rs.getTime("lunchstop"));
                        shiftInfo.put("lunchthreshold", rs.getInt("lunchthreshold"));
                        shift = new Shift(id, shiftInfo);
                        

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

    public Shift findByBadge(Badge badge) {

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
                        shift = findById(shiftId);
                

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