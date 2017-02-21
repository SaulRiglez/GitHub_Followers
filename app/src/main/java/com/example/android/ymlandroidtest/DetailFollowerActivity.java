package com.example.android.ymlandroidtest;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.ymlandroidtest.Entities.RepoUser;
import com.example.android.ymlandroidtest.Presenter.DetailPresenterImpl;
import com.example.android.ymlandroidtest.Presenter.IDetailActivityPresenter;
import com.example.android.ymlandroidtest.Utilities.ShapeTransformation;
import com.example.android.ymlandroidtest.View.IDetailFollowerView;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailFollowerActivity extends AppCompatActivity implements IDetailFollowerView{


    String toolbarTitle;


    @BindView(R.id.detailImageView) ImageView imageView;
    @BindView(R.id.logInName) TextView loginNametx;
    @BindView(R.id.tvFollowing) TextView followingtx;
    @BindView(R.id.tvFollowers) TextView followertx;
    @BindView(R.id.tvRepositories) TextView reopstx;
    @BindView(R.id.tvEmail) TextView emailtx;
    @BindView(R.id.tvLocation) TextView locationtx;

    IDetailActivityPresenter idetailPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_follower);
        ButterKnife.bind(this);


        Bundle bundle = getIntent().getExtras();
        String loginNameUser = bundle.getString("loginName");
        idetailPresenter = new DetailPresenterImpl(this);
        idetailPresenter.getUserData(loginNameUser);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.myLinearLayOut);


    }




    @Override
    public void setItems(RepoUser repoUser) {
        ButterKnife.bind(this);
        Log.d("IMplementaion On Next ", repoUser.toString());
        Picasso.with(getApplicationContext()).load(repoUser.getAvatarUrl()).transform(new ShapeTransformation()).into(imageView);
        loginNametx.setText(repoUser.getLogin());
        followertx.setText(repoUser.getFollowers().toString());
        followingtx.setText(repoUser.getFollowing().toString());
        reopstx.setText(repoUser.getPublicRepos().toString());
        emailtx.setText(repoUser.getEmail());
        locationtx.setText(repoUser.getLocation());
    }
}
