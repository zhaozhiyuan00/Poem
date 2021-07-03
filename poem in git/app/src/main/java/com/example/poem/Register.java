package com.example.poem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button re_register = findViewById(R.id.re_register);
        re_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText username = findViewById(R.id.re_username);
                EditText password = findViewById(R.id.re_password);
                EditText passwordAffirm = findViewById(R.id.re_affirm);
                String inputUsername = username.getText().toString();
                String inputPassword = password.getText().toString();
                String inputAffirm = passwordAffirm.getText().toString();

                if (inputAffirm.equals(inputPassword)) {
                    //存储账号密码
                    String sql = "INSERT INTO users (name, password) VALUES ('" + inputUsername + "','" + inputPassword + "')";
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            DBConnection.insert(sql);
                        }
                    }).start();
                    //传回账号
                    Intent intent = new Intent();
                    intent.putExtra("username", inputUsername);
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    Toast.makeText(Register.this,"两次密码不一致", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
