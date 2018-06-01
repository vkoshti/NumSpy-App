package com.easylife.numspy.numspyapi.model;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("Mobile")
    public String mobile;
    @SerializedName("Name")
    public String name;
    @SerializedName("Provider")
    public String provider;
    @SerializedName("State")
    public String state;

    User(String mobile,String name,String provider,String state)
    {
        this.mobile = mobile;
        this.name = name;
        this.provider = provider;
        this.state = state;
    }

    @Override
    public String toString() {
        return this.mobile+" "+this.name+" "+this.provider+" "+this.state;
    }
}
