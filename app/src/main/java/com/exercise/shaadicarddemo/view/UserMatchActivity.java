package com.exercise.shaadicarddemo.view;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import com.exercise.shaadicarddemo.R;
import com.exercise.shaadicarddemo.model.response.User;
import com.exercise.shaadicarddemo.model.roomdatabase.UserTable;
import com.exercise.shaadicarddemo.viewmodel.UserViewModel;

import java.util.List;

public class UserMatchActivity extends AppCompatActivity {

    private SwipeRefreshLayout swipeRefresh;
    RecyclerView mRecyclerView;
    public UserViewModel userViewModel;
    public UserAdapter userAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar_usermatch);

        initializationViews();
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        getUserList();
        swipeRefresh.setOnRefreshListener(() -> {
            getUserList();
        });
    }


    private void initializationViews() {
        Log.d("BookActivity: ","initializationViews() call");
        Toolbar toolbar = findViewById(R.id.user_toolbar);
        setSupportActionBar(toolbar);
        swipeRefresh = findViewById(R.id.swiperefresh);
        mRecyclerView = findViewById(R.id.blogRecyclerView);
    }
    private void getUserList() {
        Log.d("BookActivity: ","initializationViews() call and observe active");
        swipeRefresh.setRefreshing(true);
        userViewModel.getAllUser().observe(this, new Observer<List<UserTable>>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onChanged(List<UserTable> userList) {
                Log.d("BookActivity observe Livedata: ","onChanged() call");
                swipeRefresh.setRefreshing(false);
                prepareRecycleview(userList);
            }
        });
    }

    private void prepareRecycleview(List<UserTable> userlist) {
        Log.d("prepareRecycleview: ","create Adapter");
        userAdapter = new UserAdapter(UserMatchActivity.this,userlist);
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        } else {
            mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        }
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        Log.d("prepareRecycleview: ","set Adapter");
        mRecyclerView.setAdapter(userAdapter);
        userAdapter.notifyDataSetChanged();
    }

}
