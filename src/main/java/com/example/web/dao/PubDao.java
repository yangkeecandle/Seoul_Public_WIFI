package com.example.web.dao;

import com.example.web.common.Db;
import com.example.web.domain.PubWifi;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PubDao {

    public void insert(PubWifi pubWifi) {

        try {
            Class.forName(Db.CLASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection conn = null;
        PreparedStatement stat = null;


        try {

            conn = DriverManager.getConnection(Db.URL);

            String sql = "INSERT INTO PUB_WIFI VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

            stat = conn.prepareStatement(sql);
            stat.setString(1, pubWifi.getMgrNo());
            stat.setString(2, pubWifi.getRegion());
            stat.setString(3, pubWifi.getMainNm());
            stat.setString(4, pubWifi.getAddress());
            stat.setString(5, pubWifi.getAddressDetail());
            stat.setString(6, pubWifi.getInstallFloor());
            stat.setString(7, pubWifi.getInstallTy());
            stat.setString(8, pubWifi.getInstallMby());
            stat.setString(9, pubWifi.getServiceSe());
            stat.setString(10, pubWifi.getNetworkTy());
            stat.setString(11, pubWifi.getInstallYear());
            stat.setString(12, pubWifi.getIsOutdoor());
            stat.setString(13, pubWifi.getConnectEnv());
            stat.setString(14, pubWifi.getLongitude());
            stat.setString(15, pubWifi.getLatitude());
            stat.setString(16, pubWifi.getWorkDate());

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

    public List<PubWifi> selectList(String lnt, String lat) {
        List<PubWifi> wifiList = new ArrayList<>();

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
            
            String sql = "SELECT *," +
                    " round(6371*acos(cos(radians( ? ))*cos(radians(LAT))*cos(radians(LNT)-radians( ? ))+sin(radians( ? ))*sin(radians(LAT))), 4)" +
                    " AS DISTANCE" +
                    " FROM PUB_WIFI" +
                    " ORDER BY DISTANCE" +
                    " LIMIT 20;";

            stat = conn.prepareStatement(sql);
            stat.setString(1, String.valueOf(lnt));
            stat.setString(2, String.valueOf(lat));
            stat.setString(3, String.valueOf(lnt));

            rs = stat.executeQuery();

            while (rs.next()) {
                String dist = rs.getString("DISTANCE");
                String mgrNo = rs.getString("MGR_NO");
                String wrdofc = rs.getString("WRDOFC");
                String mainNm = rs.getString("MAIN_NM");
                String addre1 = rs.getString("ADRES1");
                String addre2 = rs.getString("ADRES2");
                String instlFloor = rs.getString("INSTL_FLOOR");
                String instlTy = rs.getString("INSTL_TY");
                String instlMby = rs.getString("INSTL_MBY");
                String svcSe = rs.getString("SVC_SE");
                String cmcwr = rs.getString("CMCWR");
                String cnstcYear = rs.getString("CNSTC_YEAR");
                String inOutDoor = rs.getString("INOUT_DOOR");
                String remars3 = rs.getString("REMARS3");
                String lntt = rs.getString("LNT");
                String latt = rs.getString("LAT");
                String workDttm = rs.getString("WORK_DTTM" );

                PubWifi pubWifi = new PubWifi();
                pubWifi.setDist(dist);
                pubWifi.setMgrNo(mgrNo);
                pubWifi.setRegion(wrdofc);
                pubWifi.setMainNm(mainNm);
                pubWifi.setAddress(addre1);
                pubWifi.setAddressDetail(addre2);
                pubWifi.setInstallFloor(instlFloor);
                pubWifi.setInstallTy(instlTy);
                pubWifi.setInstallMby(instlMby);
                pubWifi.setServiceSe(svcSe);
                pubWifi.setNetworkTy(cmcwr);
                pubWifi.setInstallYear(cnstcYear);
                pubWifi.setIsOutdoor(inOutDoor);
                pubWifi.setConnectEnv(remars3);
                pubWifi.setLongitude(lntt);
                pubWifi.setLatitude(latt);
                pubWifi.setWorkDate(workDttm);

                wifiList.add(pubWifi);
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

    public void deleteAll() {
        try {
            Class.forName(Db.CLASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection conn = null;
        PreparedStatement stat = null;

        String sql = "DELETE FROM PUB_WIFI";

        try {

            conn = DriverManager.getConnection(Db.URL);
            stat = conn.prepareStatement(sql);

            int affected = stat.executeUpdate();

            if (affected < 0) {
                System.out.println("리셋 실패");
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
}
