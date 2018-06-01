package com.easylife.numspy.numspyapi.api;

import com.easylife.numspy.numspyapi.model.WrapperUser;

import retrofit2.Call;

public class NumSpyClient {

    private static final Object sLock = new Object();
    private static NumSpyClient numSpyClient;
    private NumSpyApi mRestService;


    public static NumSpyClient getClient() {
        synchronized (sLock)
        {
            if (numSpyClient == null) {
                numSpyClient = new NumSpyClient();
                numSpyClient.mRestService = NumSpyApiService.createStatic(NumSpyApi.class);
            }

        }
        return numSpyClient;
    }

    public Call<WrapperUser> getPhoneDetails(String phone){
        return mRestService.getPhoneDetails(phone);
    }
}