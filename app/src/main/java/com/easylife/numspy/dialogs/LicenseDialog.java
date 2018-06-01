package com.easylife.numspy.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.easylife.numspy.R;

public class LicenseDialog extends DialogFragment {


    public static void showMe(Context context){

        LicenseDialog licenseDialog = new LicenseDialog();
        licenseDialog.show(((AppCompatActivity)context).getSupportFragmentManager(),"Song Tagger");
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view =getActivity().getLayoutInflater().inflate(R.layout.dialog_license,null);
        TextView androidLicense = view.findViewById(R.id.android_license);
        androidLicense.setText(String.format(getString(R.string.apache_license),"2016","Android Support Libaries"));
        TextView retrofitLicense = view.findViewById(R.id.retrofit_license);
        retrofitLicense.setText(String.format(getString(R.string.apache_license),"2013","Square, Inc."));
        return new AlertDialog.Builder(getActivity())
                .setTitle("Licenses")
                .setView(view)
                .setPositiveButton("CLOSE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
    }
}
