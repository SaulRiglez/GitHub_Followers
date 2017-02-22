package com.example.android.ymlandroidtest.presenter;

import com.example.android.ymlandroidtest.entities.Follower;

/**
 * Created by User on 2/20/2017.
 */

public interface IPresenterMainActivity {

    public void getDetailObserver(String name);
    public void onClick(Follower follower);

}
