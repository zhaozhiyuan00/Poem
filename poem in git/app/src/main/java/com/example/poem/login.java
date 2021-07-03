package com.example.poem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.service.autofill.UserData;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

import org.w3c.dom.Text;

import java.util.List;

public class login extends AppCompatActivity {
    private String basepassword;
    private String id;
    private TextView register;
    private Button loginButton;
    private CheckBox remember;
    private boolean ischecked;
    SharedPreferences sp = null;
    private EditText username;
    private EditText password;
    private String name;
    private String word;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.lg_username);
        password = findViewById(R.id.lg_password);
        sp = this.getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        remember = findViewById(R.id.lg_rememberPsd);

        if (sp.getBoolean("auto", true))
        {
            username.setText(sp.getString("uname", null));
            password.setText(sp.getString("upswd", null));
            System.out.println("asasasas"+ sp.getString("uname", null));
            remember.setChecked(true);
        }

        register = findViewById(R.id.lg_register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this, Register.class);
                startActivityForResult(intent, 1);
            }
        });

        //登录
        loginButton = findViewById(R.id.lg_login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = username.getText().toString();
                word = password.getText().toString();
                ischecked =remember.isChecked();

                TryTest.set(name);
                Handler handler = new Handler(Looper.getMainLooper()){
                    @SuppressLint("HandlerLeak")
                    @Override
                    public void handleMessage(@NonNull Message msg) {
                        super.handleMessage(msg);
                        //msg.what对应子线程中msg的标签，在子线程中进行赋值
                        if(msg.what==1){
                            basepassword = TryTest.getStr();
                            id = TryTest.getStr2();
                        }
                        if (basepassword.equals(word) && !word.equals("")) {
                            SharedPreferences.Editor editor1 = sp.edit();
                            editor1.putString("uname", name);
                            editor1.putString("upswd", word);
                            editor1.putString("uid", id);
                            editor1.commit();
                            if (ischecked)
                            {
                                SharedPreferences.Editor editor = sp.edit();
                                editor.putBoolean("auto", true);
                                editor.commit();
                            }
                            else
                            {
                                SharedPreferences.Editor editor = sp.edit();
                                editor.putBoolean("auto", false);
                                editor.commit();
                            }
                            Intent intent = new Intent(login.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(login.this,"账号或密码错误！",Toast.LENGTH_SHORT).show();
                        }
                    }
                };
                TryTest.Test(handler);
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    //接受传回来的账号
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    final EditText loginUsername = findViewById(R.id.lg_username);
                    String returnUsername = data.getStringExtra("username");
                    loginUsername.setText(returnUsername);
                    loginUsername.setSelection(returnUsername.length());
                }
                break;
            default:
        }
    }
}

