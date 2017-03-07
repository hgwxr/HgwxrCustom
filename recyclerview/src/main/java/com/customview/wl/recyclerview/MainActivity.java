package com.customview.wl.recyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.customview.wl.recyclerview.adapter.BaseRecyclerAdapter;
import com.customview.wl.recyclerview.adapter.DataAdapter;
import com.customview.wl.recyclerview.adapter.HeaderAndFooterWraper;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView mRecyclerView;

    private  GitHubService mGitHubService;
    private ArrayList<HashMap<String, String>> mLists;
    private DataAdapter dataAdapter;
    private HeaderAndFooterWraper headerAndFooterWraper;
    private View header;
    private View footer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = ((RecyclerView) findViewById(R.id.recycler_view));

        initData();

        dataAdapter = new DataAdapter(this);
        GridLayoutManager grid = new GridLayoutManager(this, 3);
        LinearLayoutManager layout = new LinearLayoutManager(this);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);

        mRecyclerView.setLayoutManager(grid);


        headerAndFooterWraper = new HeaderAndFooterWraper(dataAdapter);
        header = LayoutInflater.from(this).inflate(R.layout.layout_header,mRecyclerView,false);
        footer = LayoutInflater.from(this).inflate(R.layout.layout_footer,mRecyclerView,false);

        headerAndFooterWraper.addHeader(header);
        headerAndFooterWraper.addFooter(footer);
        mRecyclerView.setAdapter(headerAndFooterWraper);
        dataAdapter.addAll(mLists);
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(MainActivity.this,DividerItemDecoration.VERTICAL));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(MainActivity.this,DividerItemDecoration.HORIZONTAL));
        dataAdapter.setItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<HashMap<String, String>>() {
            @Override
            public void onClick(View view, BaseRecyclerAdapter.BaseViewHolder holder, HashMap<String, String> data) {
                Toast.makeText(MainActivity.this,data.get("data")+" 点击",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onLongClick(View view, BaseRecyclerAdapter.BaseViewHolder holder, HashMap<String, String> data) {
                Toast.makeText(MainActivity.this,data.get("data")+" 长按",Toast.LENGTH_LONG).show();
            }
        });
      /*  mGitHubService=new GitHubServiceIml();
        Call<String> listCall = mGitHubService.listRepos();
        listCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.d(TAG, "onResponse() called with: call = [" + call + "], response = [" + response + "]");
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

                Log.d(TAG, "onFailure() called with: call = [" + call + "], t = [" + t + "]");
            }
        });*/

    }

    private void initData() {

        mLists = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            HashMap<String, String> map = new HashMap<>();
            map.put("data","data"+i+"条数据");
            mLists.add(map);
        }
    }

}
