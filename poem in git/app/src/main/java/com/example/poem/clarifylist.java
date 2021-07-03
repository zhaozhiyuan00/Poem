package com.example.poem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;

public class clarifylist extends AppCompatActivity {

    private LinkedList<Poem> mWordList;
    private RecyclerView mRecyclerView;
    private WordListAdapter mAdapter;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clarifylist);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        int flag = getIntent().getIntExtra("season",1);
        mWordList = new LinkedList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                String httpUrl;
                String jsonResult;
                switch (flag){
                    case 0:
                        httpUrl = "http://api.tianapi.com/txapi/flmj/index?key=81a7cff6452298ed76fce86362c740d5&type=春天&num=20";
                        jsonResult = request(httpUrl);
                        killjson(jsonResult);
                        break;
                    case 1:
                        httpUrl = "http://api.tianapi.com/txapi/flmj/index?key=81a7cff6452298ed76fce86362c740d5&type=夏天&num=20";
                        jsonResult = request(httpUrl);
                        killjson(jsonResult);
                        break;
                    case 2:
                        httpUrl = "http://api.tianapi.com/txapi/flmj/index?key=81a7cff6452298ed76fce86362c740d5&type=秋天&num=20";
                        jsonResult = request(httpUrl);
                        killjson(jsonResult);
                        break;
                    case 3:
                        httpUrl = "http://api.tianapi.com/txapi/flmj/index?key=81a7cff6452298ed76fce86362c740d5&type=冬天&num=20";
                        jsonResult = request(httpUrl);
                        killjson(jsonResult);
                        break;
                    case -1:
                        httpUrl = "http://api.tianapi.com/txapi/flmj/index?key=81a7cff6452298ed76fce86362c740d5&type=西游记&num=20";
                        jsonResult = request(httpUrl);
                        killjson(jsonResult);
                        break;
                    case -2:
                        httpUrl = "http://api.tianapi.com/txapi/flmj/index?key=81a7cff6452298ed76fce86362c740d5&type=红楼梦&num=20";
                        jsonResult = request(httpUrl);
                        killjson(jsonResult);
                        break;
                    case -3:
                        httpUrl = "http://api.tianapi.com/txapi/flmj/index?key=81a7cff6452298ed76fce86362c740d5&type=三国演义&num=20";
                        jsonResult = request(httpUrl);
                        killjson(jsonResult);
                        break;
                    case -4:
                        httpUrl = "http://api.tianapi.com/txapi/flmj/index?key=81a7cff6452298ed76fce86362c740d5&type=水浒传&num=20";
                        jsonResult = request(httpUrl);
                        killjson(jsonResult);
                        break;
                    case -5:
                        httpUrl = "http://api.tianapi.com/txapi/flmj/index?key=81a7cff6452298ed76fce86362c740d5&type=春节&num=20";
                        jsonResult = request(httpUrl);
                        killjson(jsonResult);
                        break;
                    case -6:
                        httpUrl = "http://api.tianapi.com/txapi/flmj/index?key=81a7cff6452298ed76fce86362c740d5&type=中秋节&num=20";
                        jsonResult = request(httpUrl);
                        killjson(jsonResult);
                        break;
                    case -7:
                        httpUrl = "http://api.tianapi.com/txapi/flmj/index?key=81a7cff6452298ed76fce86362c740d5&type=七夕节&num=20";
                        jsonResult = request(httpUrl);
                        killjson(jsonResult);
                        break;
                    case -8:
                        httpUrl = "http://api.tianapi.com/txapi/flmj/index?key=81a7cff6452298ed76fce86362c740d5&type=端午节&num=20";
                        jsonResult = request(httpUrl);
                        killjson(jsonResult);
                        break;
                }
                handler.sendEmptyMessage(0);
            }
        }).start();

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                mRecyclerView = findViewById(R.id.recyclerview2);
                mAdapter = new WordListAdapter(clarifylist.this, mWordList, flag);
                mRecyclerView.setAdapter(mAdapter);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(clarifylist.this));
                DividerItemDecoration decoration = new DividerItemDecoration(clarifylist.this, DividerItemDecoration.VERTICAL);
                decoration.setDrawable(ContextCompat.getDrawable(clarifylist.this, R.drawable.divider_item_decoration));
                mRecyclerView.addItemDecoration(decoration);
            }
        };

    }



    public String request(String httpUrl) {
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();

        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setRequestMethod("GET");
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private void killjson(String jsonResult){
        try {
            JSONObject jsonObject=new JSONObject(jsonResult);
            JSONArray  js = jsonObject.getJSONArray("newslist");
            for(int i=0;i<js.length();i++){
                JSONObject json = js.getJSONObject(i);
                mWordList.add(new Poem(json.getString("source"),json.getString("source"),json.getString("content"),"暂无翻译"));

            }
        } catch (JSONException e) {
            System.out.println("llllldddddddddaaaa");
            e.printStackTrace();
        }
    }

}