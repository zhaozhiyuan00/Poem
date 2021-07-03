package com.example.poem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class change extends AppCompatActivity {

    SharedPreferences sp = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        sp = this.getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        Button re_register = findViewById(R.id.re_register);
        re_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText password = findViewById(R.id.re_password);
                EditText passwordAffirm = findViewById(R.id.re_affirm);

                String inputPassword = password.getText().toString();
                String inputAffirm = passwordAffirm.getText().toString();

                if (inputAffirm.equals(inputPassword)) {
                    //存储账号密码
                    String sql = "UPDATE users SET password = '" + inputPassword + "' WHERE id = '" + sp.getString("uid",null) + "'";
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            DBConnection.insert(sql);
                        }
                    }).start();
                    //传回账号
                    Toast.makeText(change.this,"更改密码成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(change.this,"两次密码不一致", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}