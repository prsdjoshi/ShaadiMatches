package com.exercise.shaadicarddemo.model.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Devendra Mehra on 4/19/2019.
 */
public class UserResponse {

    @SerializedName("results")
    private List<User> users;

    @SerializedName("info")
    private Info info;

    public List<User> getUsers() {
        return users;
    }

    public void setData(List<User> data){
        this.users = data;
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "users=" + users +
                ", info=" + info +
                '}';
    }
}
