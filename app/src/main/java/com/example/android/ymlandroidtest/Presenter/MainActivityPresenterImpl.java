package com.example.android.ymlandroidtest.Presenter;

import android.util.Log;

import com.example.android.ymlandroidtest.Entities.Follower;
import com.example.android.ymlandroidtest.Utilities.RetrofitHelper;
import com.example.android.ymlandroidtest.View.IMainFollowerView;

import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by User on 2/20/2017.
 */

public class MainActivityPresenterImpl implements IPresenterMainActivity {

 IMainFollowerView iMainFollowerView;

    public MainActivityPresenterImpl(IMainFollowerView iMainFollowerView) {
        this.iMainFollowerView = iMainFollowerView;
    }

    @Override
    public void getDetailObserver(String query) {

        Observable<List<Follower>> resultObservable = RetrofitHelper.Factory.createObservable(query);

        Observer myOberver = new Observer<List<Follower>>() {
            @Override
            public void onCompleted() {


            }

            @Override
            public void onError(Throwable e) {


            }

            @Override
            public void onNext(List<Follower>  list) {
                iMainFollowerView.setItemsRecycler(list);

            }
        };

        resultObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(myOberver);
    }

    @Override
    public void onClick(Follower follower) {
        iMainFollowerView.goToDetail(follower.getLogin());

    }
}
