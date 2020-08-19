package com.exercise.shaadicarddemo.viewmodel;

import android.annotation.SuppressLint;
import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.exercise.shaadicarddemo.MyApplication;
import com.exercise.shaadicarddemo.model.repository.UserRepository;
import com.exercise.shaadicarddemo.model.response.User;
import com.exercise.shaadicarddemo.model.roomdatabase.UserTable;
import com.exercise.shaadicarddemo.model.webservice.ApiService;

import java.util.List;

public class UserViewModel extends AndroidViewModel {

    UserRepository userRepository;
    public UserViewModel(@NonNull Application application) {
        super(application);
        Log.d("BookViewModel: ","default constructor call and create repository");
        userRepository = new UserRepository(application);

    }

    @SuppressLint("LongLogTag")
    public LiveData<List<UserTable>> getAllUser()
    {
        Log.d("BookViewModel getAllBook(): ","getBookMutableLivedata() from BookRepository and return");
        MyApplication myApplication =MyApplication.create(getApplication());
        ApiService userService =myApplication.getApiService();
        return userRepository.getUserMutableLivedata(userService);
    }
}
