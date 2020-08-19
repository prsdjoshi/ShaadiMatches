package com.exercise.shaadicarddemo.model.repository;

import android.annotation.SuppressLint;
import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;


import com.exercise.shaadicarddemo.model.response.User;
import com.exercise.shaadicarddemo.model.response.UserResponse;
import com.exercise.shaadicarddemo.model.roomdatabase.AppDatabase;
import com.exercise.shaadicarddemo.model.roomdatabase.DatabaseClient;
import com.exercise.shaadicarddemo.model.roomdatabase.UserTable;
import com.exercise.shaadicarddemo.model.webservice.ApiService;
import com.exercise.shaadicarddemo.utils.StringHelper;
import com.exercise.shaadicarddemo.view.DisplayableUser;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UserRepository {
    private final AppDatabase appDatabase;
    private final StringHelper stringHelper;
    private ArrayList<User> userArrayList = new ArrayList<>();
    private MutableLiveData<List<UserTable>> mutableLiveData = new MutableLiveData<>();
    private Application application;

    public UserRepository(Application application) {
        this.application = application;
        Log.d("bookRepository: ","default constructor call");
        appDatabase = DatabaseClient.getInstance(application.getApplicationContext()).getAppDatabase();
        stringHelper =new StringHelper(application.getApplicationContext());

    }

    @SuppressLint("LongLogTag")
    public MutableLiveData<List<UserTable>> getUserMutableLivedata(ApiService apiService)
    {
        apiService.getUsersResponse(10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<UserResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onSuccess(UserResponse response) {
                        if(response!=null&&response.getUsers()!=null)
                        {
                            try {
                                Log.d("bookRepository MutableLiveData: ","getbookdata and set value to notify observer in view");
                                ArrayList<UserTable> userTableArrayList = new ArrayList<>();
                                userArrayList = (ArrayList<User>) response.getUsers();
                                for (User user:userArrayList)
                                {
                                    DisplayableUser displayableUser =  new DisplayableUser(user, stringHelper);
                                    UserTable userTable = new UserTable();
                                    userTable.setFullNameAge(displayableUser.getFullNameAge());
                                    userTable.setEmail(displayableUser.getEmail());
                                    userTable.setLocation(displayableUser.getLocation());
                                    userTable.setRegistrationPeriod(displayableUser.getRegistrationPeriod());
                                    userTable.setPicture(displayableUser.getPicture());
                                    userTable.setStatus("");
                                    userTableArrayList.add(userTable);
                                }
                                appDatabase.userDao().clearAll();
                                appDatabase.userDao().saveAll(userTableArrayList);
                                mutableLiveData.setValue(userTableArrayList);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Error in getBookMutableLivedata: ",e.toString());
                        try {
                            mutableLiveData.setValue(appDatabase.userDao().getAllUsers());
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }

                });
        return mutableLiveData;
    }
}
