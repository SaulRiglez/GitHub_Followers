package com.example.android.ymlandroidtest.Utilities;

import com.example.android.ymlandroidtest.Entities.Follower;
import com.example.android.ymlandroidtest.Entities.RepoUser;
import com.example.android.ymlandroidtest.Model.GitHubSrvice;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by User on 2/20/2017.
 */

public class RetrofitHelper {

    public static class Factory {

       static Retrofit retrofit = create();
        static GitHubSrvice service = retrofit.create(GitHubSrvice.class);


        public static Retrofit create(){
            return new Retrofit.Builder()
                    .baseUrl("https://api.github.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                    .build();
        }

        public static Observable<List<Follower>> createObservable(String username){


            return service.getFollowers(username);
        }

        public static Observable<RepoUser> createUserDetailsObservable(String username)
        {

            return service.getUserDetail(username);

        }

    }

}
