package com.easylife.numspy.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.easylife.numspy.R;
import com.easylife.numspy.dialogs.LicenseDialog;

public class AboutActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        initViews();
        setUpToolBar();

    }

    private void initViews()
    {
        TextView sWeb = findViewById(R.id.sameer_web);
        TextView sGit = findViewById(R.id.sameer_github);
        TextView vWeb = findViewById(R.id.vishal_web);
        TextView vGit = findViewById(R.id.vishal_github);
        sWeb.setOnClickListener(this);
        sGit.setOnClickListener(this);
        vWeb.setOnClickListener(this);
        vGit.setOnClickListener(this);

    }

    private void setUpToolBar()
    {
        setSupportActionBar((Toolbar) findViewById(R.id.toolBar));
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
    }

    @Override
    public void onClick(View v) {
        Intent webIntent  = new Intent(Intent.ACTION_VIEW);
        switch (v.getId())
        {
            case R.id.sameer_web:
                webIntent.setData(Uri.parse("http://bhattsameer.github.io"));
                break;

            case R.id.sameer_github:
                webIntent.setData(Uri.parse("https://github.com/bhattsameer"));
                break;

            case R.id.vishal_web:
                webIntent.setData(Uri.parse("http://vishalkoshti165.wixsite.com/vishalk"));
                break;

            case R.id.vishal_github:
                webIntent.setData(Uri.parse("https://github.com/vkoshti"));
                break;
        }
        startActivity(webIntent);
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

    public void openLicenseBox(View view) {
        LicenseDialog.showMe(this);
    }
}
