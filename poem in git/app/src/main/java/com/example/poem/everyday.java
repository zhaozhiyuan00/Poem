package com.example.poem;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.jinrishici.sdk.android.JinrishiciClient;
import com.jinrishici.sdk.android.factory.JinrishiciFactory;
import com.jinrishici.sdk.android.listener.JinrishiciCallback;
import com.jinrishici.sdk.android.model.JinrishiciRuntimeException;
import com.jinrishici.sdk.android.model.PoetySentence;
import com.xuexiang.xui.widget.button.shinebutton.ShineButton;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link everyday#newInstance} factory method to
 * create an instance of this fragment.
 */
public class everyday extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "MainActivity";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextView poem;
    private Button change;
    private ShineButton heart;
    private TextView tags;
    private Button concrete;
    private String p_title;
    private String p_content;
    private String p_author;
    private String p_explain;
    SharedPreferences sp = null;

    public everyday() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment everydday.
     */
    // TODO: Rename and change types and number of parameters
    public static everyday newInstance(String param1, String param2) {
        everyday fragment = new everyday();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_everyday,container,false);
        poem = root.findViewById(R.id.everyday);
        change = root.findViewById(R.id.change);
        tags = root.findViewById(R.id.tags);
        concrete = root.findViewById(R.id.concrete);
        heart = root.findViewById(R.id.heart);
        sp = getActivity().getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        String id = sp.getString("uid",null);
        initpoem();
        concrete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), concretepoem.class);
                intent.putExtra("title",p_title);
                intent.putExtra("author",p_author);
                intent.putExtra("content",p_content);
                intent.putExtra("explain",p_explain);
                startActivity(intent);
            }
        });
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initpoem();
            }
        });
        heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sql = "INSERT INTO heart (user, title, author, content, translation) VALUES ('" + id + "','" + p_title + "','" + p_author + "','" + p_content + "','" + p_explain +"')";
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        DBConnection.insert(sql);
                    }
                }).start();
            }
        });
        return root;
    }

    public void initpoem() {
        JinrishiciFactory.init(getContext());
        JinrishiciClient client = JinrishiciClient.getInstance().init(getContext());
        client.getOneSentenceBackground(new JinrishiciCallback() {
            @Override
            public void done(PoetySentence poetySentence) {
                p_title = poetySentence.getData().getOrigin().getTitle();
                p_author = poetySentence.getData().getOrigin().getAuthor();
                List<String> X = poetySentence.getData().getOrigin().getContent();
                List<String> Y = poetySentence.getData().getOrigin().getTranslate();
                heart.setChecked(false);
                String arrString1 = "";
                String arrString2 = "";
                for (int i = 0; i < X.size(); i++)
                {
                    arrString1 = arrString1.concat(X.get(i));
                    arrString1 = arrString1.concat("\n\n");
                }
                if(poetySentence.getData().getOrigin().getTranslate() != null)
                {
                    for (int i = 0; i < Y.size(); i++)
                    {
                        arrString2 = arrString2.concat(Y.get(i));
                        arrString2 = arrString2.concat("\n\n");
                    }
                }
                else {
                    arrString2 = arrString2.concat("暂无翻译");
                    Log.i(TAG, "done: aaa" + arrString2);
                }
                p_content = arrString1;
                p_explain = arrString2;
                Log.i(TAG, "done: ppp" + "sss");
                poem.setText(poetySentence.getData().getContent());
                tags.setText(poetySentence.getData().getMatchTags().toString());
                concrete.setText("查看《" + poetySentence.getData().getOrigin().getTitle() + "》全诗");
            }

            @Override
            public void error(JinrishiciRuntimeException e) {
                poem.setText(e.getMessage());
            }
        });
    }

}