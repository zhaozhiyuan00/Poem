package com.example.poem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class DBConnection {
    public static Connection link (){
        //要连接的数据库url,注意：此处连接的应该是服务器上的MySQl的地址
        String url = "jdbc:mysql://cdb-500sl38h.gz.tencentcdb.com:10002/poem";
        //连接数据库使用的用户名
        String userName = "root";
        //连接的数据库时使用的密码
        String password = "1813056smbblj";
        Connection connection=null;
        try {
            //1、加载驱动
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            System.out.println("驱动加载成功！！！");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        try {
            //2、获取与数据库的连接
            connection = DriverManager.getConnection(url, userName, password);
            System.out.println("连接数据库成功！！！");

        }catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
    public static void insert(String sql){
        Connection conn = link();
        try{
            //4.获取用于向数据库发送sql语句的ps
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.execute(sql);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            if(conn!=null){
                try {
                    conn.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

    }

    public static LinkedList<Poem> get (String sql) {
        Connection conn = link();
        LinkedList<Poem> x = new LinkedList<>();
        try {
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery(sql);
            if (res == null) {
                //return null;
                System.out.println("null");
            } else {
                int cnt = res.getMetaData().getColumnCount();
                while (res.next())
                {
                    HashMap<String, String> map = new HashMap<>();
                    for (int i = 1; i <= cnt; ++i) {
                        String field = res.getMetaData().getColumnName(i);
                        map.put(field, res.getString(field));
                    }
                    x.add(new Poem(map.get("title"),map.get("author"),map.get("content"),map.get("translation")));

                }
                System.out.println(x.get(1).content);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return x;
    }

    public static String getPassword(String sql){
        Connection conn = link();
        String password = "";
        try {
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery(sql);
            if (res == null) {
                //return null;

            } else {
                while(res.next()){
                    password = res.getString("password");
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return password;
    }
    public static String getid(String sql){
        Connection conn = link();
        String password = "";
        try {
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery(sql);
            if (res == null) {
                //return null;

            } else {
                while(res.next()){
                    password = res.getString("id");
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return password;
    }

}
