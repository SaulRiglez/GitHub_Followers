package com.example.android.ymlandroidtest.Presenter;

import android.util.Log;

import com.example.android.ymlandroidtest.Entities.RepoUser;
import com.example.android.ymlandroidtest.Utilities.RetrofitHelper;
import com.example.android.ymlandroidtest.View.IDetailFollowerView;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by User on 2/20/2017.
 */

public class DetailPresenterImpl implements IDetailActivityPresenter {

    private IDetailFollowerView iDetailFollowerView;

    public DetailPresenterImpl(IDetailFollowerView iDetailFollowerView) {
        this.iDetailFollowerView = iDetailFollowerView;
    }

    @Override
    public void getUserData(String loginName) {


        Observable<RepoUser> resultObservable = RetrofitHelper.Factory.createUserDetailsObservable(loginName);


        Observer userObserver = new Observer<RepoUser>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {



            }

            @Override
            public void onNext(RepoUser repoUser) {
                iDetailFollowerView.setItems(repoUser);
            }

        };

        resultObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(userObserver);


    }
}
