package com.example.poem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;


import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.LinkedList;
import java.util.logging.LogRecord;

public class heartlist extends AppCompatActivity {

    SharedPreferences sp = null;
    private LinkedList<Poem> mWordList;
    private RecyclerView mRecyclerView;
    private WordListAdapter mAdapter;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heartlist);
        sp = this.getSharedPreferences("userinfo", Context.MODE_PRIVATE);

        String sql = "select * from heart where user = '" + sp.getString("uid",null) + "'";
        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mWordList = new LinkedList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                mWordList=DBConnection.get(sql);
                handler.sendEmptyMessage(0);
            }
        }).start();
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                mRecyclerView = findViewById(R.id.recyclerview);
                mAdapter = new WordListAdapter(heartlist.this, mWordList,4);
                mRecyclerView.setAdapter(mAdapter);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(heartlist.this));
                DividerItemDecoration decoration = new DividerItemDecoration(heartlist.this, DividerItemDecoration.VERTICAL);
                decoration.setDrawable(ContextCompat.getDrawable(heartlist.this, R.drawable.divider_item_decoration));
                mRecyclerView.addItemDecoration(decoration);
            }
        };


    }

}