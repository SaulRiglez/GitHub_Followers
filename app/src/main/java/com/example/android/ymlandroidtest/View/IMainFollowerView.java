package com.example.android.ymlandroidtest.view;

import com.example.android.ymlandroidtest.entities.Follower;

import java.util.List;

/**
 * Created by User on 2/20/2017.
 */

public interface IMainFollowerView {

    void setItemsRecycler(List<Follower> followerList);
    void goToDetail(String loginName);
}
