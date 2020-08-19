package com.exercise.shaadicarddemo.model.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Devendra Mehra on 4/19/2019.
 */
public class Street {

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @SerializedName("number")
    private String number;
    @SerializedName("name")
    private String name;

    @Override
    public String toString() {
        return "street{" +
                "number='" + number +
                '}';
    }
}
