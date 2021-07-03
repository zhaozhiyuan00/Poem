package com.example.poem;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;
import com.xuexiang.xui.widget.searchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link clarify#newInstance} factory method to
 * create an instance of this fragment.
 */
public class clarify extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private XBanner xbanner_view;
    private ImageView A;
    private ImageView B;
    private ImageView C;
    private ImageView D;
    private ImageView E;
    private ImageView F;
    private ImageView G;
    private ImageView H;


    public clarify() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment clarify.
     */
    // TODO: Rename and change types and number of parameters
    public static clarify newInstance(String param1, String param2) {
        clarify fragment = new clarify();
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
        View root = inflater.inflate(R.layout.fragment_clarify, container, false);
        xbanner_view = root.findViewById(R.id.xbanner_view);

        //图片集合,模拟一下数据
        final List<String> imgesUrl = new ArrayList<>();
        imgesUrl.add("http://tiebapic.baidu.com/forum/w%3D580/sign=477e988bdf26cffc692abfba89014a7d/766bf3246b600c33777f30da474c510fd9f9a1a3.jpg");
        imgesUrl.add("http://tiebapic.baidu.com/forum/w%3D580/sign=eb74a3d2aa36afc30e0c3f6d8319eb85/852a770e0cf3d7ca93f0d164af1fbe096b63a9a3.jpg");
        imgesUrl.add("http://tiebapic.baidu.com/forum/w%3D580/sign=897314157ec79f3d8fe1e4388aa0cdbc/b47f3c12b31bb051b857e2c2737adab44bede0f5.jpg");
        imgesUrl.add("http://tiebapic.baidu.com/forum/w%3D580/sign=409b8c5de5d3572c66e29cd4ba126352/b03c5c82b2b7d0a246bedf29dcef76094b369a13.jpg");

        final List<String> Word = new ArrayList<>();
        Word.add("初春时节采诗去");
        Word.add("夏日正是读诗时");
        Word.add("翩翩秋叶携诗来");
        Word.add("万里雪飘冬日诗");

        //数据集合导入banner里
        xbanner_view.setData(imgesUrl,Word);
        //图片加载
        xbanner_view.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                //glide请求网络图片
                Glide.with(getActivity()).load(imgesUrl.get(position)).into((ImageView) view);
            }
        });
        //设置切换延时,单位sm，默认5000sm
        xbanner_view.setPageChangeDuration(5000);

        xbanner_view.setPageTransformer(Transformer.Alpha); //渐变，效果不明显

        //设置轮播图点击监听
        xbanner_view.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, Object model, View view, int position) {
                Intent intent =new Intent(getActivity(),clarifylist.class);
                intent.putExtra("season",position);
                startActivity(intent);
            }
        });

        A = root.findViewById(R.id.a);
        A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getActivity(),clarifylist.class);
                intent.putExtra("season",-1);
                startActivity(intent);
            }
        });
        B = root.findViewById(R.id.b);
        B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getActivity(),clarifylist.class);
                intent.putExtra("season",-2);
                startActivity(intent);
            }
        });
        C = root.findViewById(R.id.c);
        C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getActivity(),clarifylist.class);
                intent.putExtra("season",-3);
                startActivity(intent);
            }
        });
        D = root.findViewById(R.id.d);
        D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getActivity(),clarifylist.class);
                intent.putExtra("season",-4);
                startActivity(intent);
            }
        });
        E = root.findViewById(R.id.e);
        E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getActivity(),clarifylist.class);
                intent.putExtra("season",-5);
                startActivity(intent);
            }
        });
        F = root.findViewById(R.id.f);
        F.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getActivity(),clarifylist.class);
                intent.putExtra("season",-6);
                startActivity(intent);
            }
        });
        G = root.findViewById(R.id.g);
        G.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getActivity(),clarifylist.class);
                intent.putExtra("season",-7);
                startActivity(intent);
            }
        });
        H = root.findViewById(R.id.h);
        H.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getActivity(),clarifylist.class);
                intent.putExtra("season",-8);
                startActivity(intent);
            }
        });
        return root;
    }
}