package com.itts.lala;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;
import com.huawei.hms.support.account.result.AuthAccount;

public class UserInfoActivity extends AppCompatActivity {

    private static final String TAG = UserInfoActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        Intent mainIntent = getIntent();
        Gson gson = new Gson();
        AuthAccount  authHuaweiAccount =  gson.fromJson(mainIntent.getStringExtra("authHuaweiAccount"), AuthAccount.class);

        if(authHuaweiAccount!=null){
            Log.d(TAG,authHuaweiAccount.getDisplayName());
        }else{
            Log.d(TAG,"authHuaweiAccount is null");
        }

        TextView textViewId = findViewById(R.id.userTextViewHuaweiId);
        textViewId.setText(authHuaweiAccount.getUnionId());

        TextView textViewName = findViewById(R.id.userTextViewHuaweiName);
        textViewName.setText(authHuaweiAccount.getDisplayName());

        TextView textViewEmail = findViewById(R.id.userTextViewHuaweiEmail);
        textViewEmail.setText(authHuaweiAccount.getEmail());
    }
}