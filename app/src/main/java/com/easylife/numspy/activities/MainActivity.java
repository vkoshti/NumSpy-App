package com.easylife.numspy;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.easylife.numspy.numspyapi.api.NumSpyClient;
import com.easylife.numspy.numspyapi.model.User;
import com.easylife.numspy.numspyapi.model.WrapperUser;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {
    private AppCompatButton searchButton;
    private AppCompatImageView appCompatImageView;
    private ImageView about_image;
    private EditText edit_phone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        edit_phone = findViewById(R.id.edit_phone);
        searchButton = findViewById(R.id.search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performSearch();
            }
        });
    }


    private void initViews()
    {
        about_image = findViewById(R.id.about_image);
        appCompatImageView = findViewById(R.id.appCompatImageView);
        appCompatImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNumSpyWebsite();
            }
        });
        about_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAboutPage();
            }
        });

    }

    private void openAboutPage()
    {
        startActivity(new Intent(MainActivity.this,AboutActivity.class));
    }

    private void openNumSpyWebsite()
    {
        Intent numspyWeb = new Intent(Intent.ACTION_VIEW, Uri.parse("https://bhattsameer.github.io/numspy"));
        startActivity(numspyWeb);
    }

    private void performSearch()
    {

        if(isValidPhone())
        {
            final AlertDialog progressDialog = createProgressDialog();
            progressDialog.show();
            if(!checkNetwork())
            {
                Snackbar.make(appCompatImageView,"You are Offline, Try Again",Snackbar.LENGTH_LONG).show();
                if (progressDialog.isShowing())
                    progressDialog.dismiss();
            }
            else
            {
                NumSpyClient.getClient().getPhoneDetails(edit_phone.getText().toString())
                        .enqueue(new Callback<WrapperUser>() {
                            @Override
                            public void onResponse(@NonNull Call<WrapperUser> call, @NonNull retrofit2.Response<WrapperUser> response) {
                                if(response.body()!=null)
                                {
                                    User user = response.body().getUser();
                                    if(user!=null)
                                    {
                                        String phone = user.mobile;
                                        String name = user.name;
                                        String provider = user.provider;
                                        String state = user.state;
                                        Bundle bundle = new Bundle();
                                        bundle.putString("userPhone",phone);
                                        bundle.putString("userName",name);
                                        bundle.putString("userProvider",provider);
                                        bundle.putString("userState",state);
                                        startActivity(new Intent(MainActivity.this, PhoneDetails.class).putExtras(bundle));
                                    }
                                }
                                if(progressDialog.isShowing())
                                    progressDialog.dismiss();
                                edit_phone.getText().clear();
                            }

                            @Override
                            public void onFailure(@NonNull Call<WrapperUser> call, @NonNull Throwable t) {
                                Toast.makeText(MainActivity.this, "Error Occurred\nTry Again", Toast.LENGTH_SHORT).show();
                            }
                        });
            }

        }
    }

    private boolean isValidPhone()
    {
        String phone = edit_phone.getText().toString();
        if(phone.isEmpty() || phone.length()>10 || phone.length()<10)
        {
            edit_phone.setError("Invalid Phone\nEnter Valid Number");
            return false;
        }
        try {
            Long.valueOf(phone);
        } catch (NumberFormatException e) {
            edit_phone.setError("Phone Number can not contain special characters or letters");
            return false;
        }
        return true;
    }

    private AlertDialog createProgressDialog()
    {
        return new AlertDialog.Builder(this)
                .setView(R.layout.dialog_progress_bar)
                .setTitle("Searching")
                .setCancelable(false)
                .create();
    }

    private boolean checkNetwork()
    {
        ConnectivityManager conMgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        if (conMgr != null) {
            if ( conMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED
                    || conMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED ) {

                return true;

            }
            else if ( conMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.DISCONNECTED
                    || conMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.DISCONNECTED) {

                return false;
            }
        }
        return false;
    }


}
