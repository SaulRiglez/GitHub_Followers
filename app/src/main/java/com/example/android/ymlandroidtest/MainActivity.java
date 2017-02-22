package com.example.android.ymlandroidtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.ymlandroidtest.utilities.FollowerAdapter;
import com.example.android.ymlandroidtest.entities.Follower;
import com.example.android.ymlandroidtest.presenter.IPresenterMainActivity;
import com.example.android.ymlandroidtest.presenter.MainActivityPresenterImpl;
import com.example.android.ymlandroidtest.utilities.OnItemClickListener;
import com.example.android.ymlandroidtest.view.IMainFollowerView;


import java.util.List;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements OnItemClickListener, IMainFollowerView {

    ViewGroup myViewGroup;

    List<Follower> FollowerList = new ArrayList<Follower>();

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private FollowerAdapter followerAdapter;


    IPresenterMainActivity iPresenterMainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);



        followerAdapter = new FollowerAdapter(FollowerList, this);
        GridLayoutManager mGridManagerGrid = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(mGridManagerGrid);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(followerAdapter);
        followerAdapter.setClickListener(this);
        iPresenterMainActivity = new MainActivityPresenterImpl(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the options menu from XML
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_follower_list, menu);

        // Get the SearchView and set the searchable configuration
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        // Assumes current activity is the searchable activity

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {


                iPresenterMainActivity.getDetailObserver(query);
                MainActivity.this.setTitle(query);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onClick(View view, int position) {

        Follower follower = FollowerList.get(position);
        iPresenterMainActivity.onClick(follower);

    }

    @Override
    public void setItemsRecycler(List<Follower> followerList) {
        FollowerList.clear();

        for (Follower l : followerList) {
            FollowerList.add(l);

        }

        followerAdapter.notifyDataSetChanged();
    }

    @Override
    public void goToDetail(String loginName) {
        Intent i = new Intent(this, DetailFollowerActivity.class);
        i.putExtra("loginName", loginName);
        startActivity(i);

    }
}

