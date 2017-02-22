package com.example.android.ymlandroidtest.model;

import com.example.android.ymlandroidtest.entities.Follower;
import com.example.android.ymlandroidtest.entities.RepoUser;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by User on 2/20/2017.
 */

public interface GitHubSrvice {

    @GET("users/{user}/followers")
    Observable<List<Follower>> getFollowers(@Path("user") String user);


    @GET("users/{user}")
    Observable<RepoUser> getUserDetail(@Path("user") String user);


}


