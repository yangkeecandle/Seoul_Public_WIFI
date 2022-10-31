package com.example.web.dao;

import com.example.web.common.Db;
import com.example.web.domain.HistWifi;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistDao {

    public void insert(HistWifi histWifi) {
        try {
            Class.forName(Db.CLASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection conn = null;
        PreparedStatement stat = null;


        try {

            conn = DriverManager.getConnection(Db.URL);

            String sql = "INSERT INTO HIST(LNT, LAT, SRCH_DTTM) VALUES ( ? , ? , datetime('now', 'localtime'));";

            stat = conn.prepareStatement(sql);
            stat.setString(1, String.valueOf(histWifi.getLongitude()));
            stat.setString(2, String.valueOf(histWifi.getLatitude()));

            int affected = stat.executeUpdate();

            if (affected < 0) {
                System.out.println("저장 실패");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                if (stat != null && !stat.isClosed()) {
                    stat.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    public List<HistWifi> selectAll() {

        List<HistWifi> wifiList = new ArrayList<>();

        try {
            Class.forName(Db.CLASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(Db.URL);

            String sql = "SELECT * FROM HIST";
            stat = conn.prepareStatement(sql);

            rs = stat.executeQuery();

            while (rs.next()) {
                String histNo = rs.getString("HIST_NO");
                String longitude = rs.getString("LNT");
                String latitude = rs.getString("LAT");
                Date date = rs.getDate("SRCH_DTTM");

                HistWifi histWifi = new HistWifi();
                histWifi.setHistNo(Integer.parseInt(histNo));
                histWifi.setLongitude(longitude);
                histWifi.setLatitude(latitude);
                histWifi.setSearchDate(date);

                wifiList.add(histWifi);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (stat != null && !stat.isClosed()) {
                    stat.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return wifiList;
    }
}
