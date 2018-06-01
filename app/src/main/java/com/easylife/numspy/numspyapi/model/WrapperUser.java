package com.easylife.numspy.numspyapi.model;

import com.google.gson.annotations.SerializedName;

public class WrapperUser {
    @SerializedName("data")
    private User user;

    public WrapperUser(User user)
    {
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }
}
