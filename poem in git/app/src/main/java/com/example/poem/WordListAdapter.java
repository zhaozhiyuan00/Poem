package com.example.poem;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class WordListAdapter extends
        RecyclerView.Adapter<WordListAdapter.WordViewHolder>  {

    private LinkedList<Poem> mWordList;
    private LayoutInflater mInflater;
    private Context context;
    private int flag;

    public WordListAdapter(Context context, LinkedList<Poem> mWordList, int flag) {
        mInflater = LayoutInflater.from(context);
        this.mWordList = mWordList;
        this.context = context;
        this.flag = flag;
    }

    class WordViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        public final TextView wordItemView;
        final WordListAdapter mAdapter;
        public WordViewHolder(View itemView, WordListAdapter adapter) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.word);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int mPosition = getLayoutPosition();
            Poem element = mWordList.get(mPosition);
            if (flag > 3)
            {
                Intent intent = new Intent(context,concretepoem.class);
                intent.putExtra("title",element.title);
                intent.putExtra("author",element.author);
                intent.putExtra("content",element.content);
                intent.putExtra("explain",element.translation);
                context.startActivity(intent);
                mAdapter.notifyDataSetChanged();
            }
            else {

            }

        }
    }
    @NonNull
    @Override
    public WordListAdapter.WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.wordlist_item,
                parent, false);
        return new WordViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull WordListAdapter.WordViewHolder holder, int position) {
        Poem mCurrent = mWordList.get(position);
        if (flag > 3)
        {
            holder.wordItemView.setText(mCurrent.title);
        }
        else {
            holder.wordItemView.setText(mCurrent.content);
        }
    }

    @Override
    public int getItemCount() {
        return mWordList.size();
    }
}