package com.easylife.numspy.numspyapi.api;

import com.easylife.numspy.numspyapi.model.WrapperUser;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface NumSpyApi {


    @GET("LocateMobile/{phoneNumber}")
    Call<WrapperUser> getPhoneDetails(@Path("phoneNumber") String phoneNumber);
}
