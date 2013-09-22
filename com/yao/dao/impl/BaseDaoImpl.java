package com.yao.dao.impl;

import com.yao.entity.AllConfig;

import java.io.IOException;
import java.sql.*;


public class BaseDaoImpl {


    public boolean index(AllConfig indexObj) {


        boolean flag = true;
        //getConn(indexObj);
        getMinAndMax_f_id(indexObj);

        System.out.println("min batch-id\t-->" + minFlag);

        System.out.println("max batch-id\t-->" + maxFlg);


        ResultSet rs;

        long from = 0;
        long to = 0;

        from = minFlag;


        importIndex = new ImportIndex();
        importIndex.initClusterClient(indexObj);
        while (to < maxFlg) {
            to = from + Long.valueOf(indexObj.getBatchSize());
            System.out.println("from\t" + from + "\tto\t" + to);
            rs = getRs(indexObj, from, to);

            try {
                importIndex.importIndex(indexObj, rs);
            } catch (SQLException e) {
                flag = false;
                e.printStackTrace();
            } catch (IOException e) {
                flag = false;
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            ;
            from = to + 1;
        }

        close();
        importIndex.close();

        return flag;
    }


    private void getMinAndMax_f_id(AllConfig indexObj) {
        getConn(indexObj);
        Statement stmt;
        try {
            stmt = conn.createStatement();
            String sql = "SELECT MIN(" + indexObj.getBatch_id() + ") FROM "
                    + indexObj.getDb_table();


            rs = stmt.executeQuery(sql);
            rs.next();
            String s = rs.getString(1);

            minFlag = Long.valueOf(s);


            sql = "select max(" + indexObj.getBatch_id() + ") FROM " + indexObj.getDb_table();


            rs = stmt.executeQuery(sql);
            rs.next();
            s = rs.getString(1);
            maxFlg = Long.valueOf(s);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        close();
    }


    private ResultSet getRs(AllConfig indexObj, long from, long to) {
        getConn(indexObj);

        String selectSql = "select * from " + indexObj.getDb_table() + " where " + indexObj.getBatch_id() + " between ? and ? order by " + indexObj.getBatch_id();
        try {
            pstmt = conn.prepareStatement(selectSql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            pstmt.setLong(1, from);
            pstmt.setLong(2, to);
            rs = pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }


    public void getConn(AllConfig obj) {
        String url = obj.getDb_url() + "/" + obj.getDb_name();

        try {
            Class.forName(JDBCCLASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            conn = DriverManager.getConnection(url, obj.getDb_username(),
                    obj.getDb_password());
        } catch (SQLException e) {
            System.out.println("init database connection fail");
            e.printStackTrace();
        }

    }


    public void close() {
        try {
            if (rs != null && !rs.isClosed()) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (pstmt != null && !pstmt.isClosed()) {
                pstmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;
    private long minFlag;
    private long maxFlg;
    private ImportIndex importIndex;
    private static final String JDBCCLASS = "com.mysql.jdbc.Driver";
}
