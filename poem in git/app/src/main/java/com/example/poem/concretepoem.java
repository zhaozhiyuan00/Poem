package com.example.poem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

public class concretepoem extends AppCompatActivity {

    private TextView p_title;
    private TextView p_author;
    private TextView p_content;
    private TextView p_translate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concretepoem);
        Toolbar toolbar = findViewById(R.id.toolbar);
        
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        p_title = findViewById(R.id.poemtitle);
        String title=getIntent().getStringExtra("title");
        p_title.setText(title);
        p_author = findViewById(R.id.author);
        String author = getIntent().getStringExtra("author");
        p_author.setText(author);
        p_content = findViewById(R.id.content);
        String content = getIntent().getStringExtra("content");
        p_content.setText(content);
        p_translate = findViewById(R.id.translate);
        String explain = getIntent().getStringExtra("explain");
        p_translate.setText(explain);

    }
}