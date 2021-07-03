package com.example.poem;

import android.os.Handler;
import android.os.Message;

public class TryTest {
    private static String test_str1;
    private static String test_str2;
    private static String name;
    public static void Test(final Handler mHandler){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String sql = "select password from users where name = '" + name + "'";
                String sql2 = "select id from users where name = '" + name + "'";
                test_str1 = DBConnection.getPassword(sql);
                test_str2  = DBConnection.getid(sql2);
                Message msg = new Message();
                msg.what = 1;
                //发送消息
                mHandler.sendMessage(msg);
            }
        }).start();
    }
    public static String getStr(){
        return test_str1;
    }
    public static String getStr2(){
        return test_str2;
    }
    public static void set(String name){
        TryTest.name =name;
    }
}

