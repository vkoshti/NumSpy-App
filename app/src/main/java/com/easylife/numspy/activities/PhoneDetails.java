package com.easylife.numspy.activities;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.easylife.numspy.R;

public class PhoneDetails extends AppCompatActivity {
    private String name,phone,provider,state;
    private TextView textName,textPhone,textProvider,textState;
    private ImageView makePhone,makeMessage,userLocation;
    private FloatingActionButton saveButton;
    private CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        initViews();
        setUpToolBar();
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null)
        {
            name = bundle.getString("userName");
            phone = bundle.getString("userPhone");
            provider = bundle.getString("userProvider");
            state = bundle.getString("userState");
        }
        setData();
    }

    private void initViews()
    {
        textName = findViewById(R.id.userName);
        textPhone = findViewById(R.id.userPhone);
        textProvider = findViewById(R.id.userProvider);
        textState = findViewById(R.id.userLocation);
        makePhone = findViewById(R.id.makeCall);
        makeMessage = findViewById(R.id.makeMessage);
        userLocation = findViewById(R.id.directLocation);
        saveButton = findViewById(R.id.saveContact);
        collapsingToolbarLayout = findViewById(R.id.collapsingToolbar);
        makePhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMakeCallUI();
            }
        });

        makeMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMakeMessageUI();
            }
        });

        userLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLocationMap();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveContact();
            }
        });



    }


    private void setUpToolBar()
    {
        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
    }

    private void setData()
    {
        if(name == null || name.isEmpty())
            name = "UnNamed";
        textName.setText(name);
        textPhone.setText(phone);
        if(provider == null || provider.isEmpty())
            provider = "UnDefined";
        textProvider.setText(provider);
        textState.setText(state);
        collapsingToolbarLayout.setTitle(name);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);

    }

    private void openMakeCallUI()
    {
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse(String.valueOf("tel:+91")+phone));
        startActivity(callIntent);
    }

    private void openMakeMessageUI()
    {
        Intent messageIntent = new Intent(Intent.ACTION_VIEW);
        messageIntent.setData(Uri.parse(String.valueOf("smsto:+91")+phone));
        startActivity(messageIntent);
    }

    private void openLocationMap()
    {
        Intent mapIntent = new Intent(Intent.ACTION_VIEW,Uri.parse(String.valueOf(
                "https://maps.google.com/maps?q="+state
        )));
        startActivity(mapIntent);
    }

    private void saveContact()
    {
        Intent saveIntent = new Intent(Intent.ACTION_INSERT);
        saveIntent.setType(ContactsContract.Contacts.CONTENT_TYPE);
        saveIntent.putExtra(ContactsContract.Intents.Insert.NAME,name);
        saveIntent.putExtra(ContactsContract.Intents.Insert.PHONE,String.valueOf("+91"+phone));
        startActivity(saveIntent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;
                default:return false;
        }
    }
}
