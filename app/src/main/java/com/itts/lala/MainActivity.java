package com.itts.lala;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.support.account.AccountAuthManager;
import com.huawei.hms.support.account.request.AccountAuthParams;
import com.huawei.hms.support.account.request.AccountAuthParamsHelper;
import com.huawei.hms.support.account.result.AuthAccount;
import com.huawei.hms.support.account.service.AccountAuthService;

public class MainActivity extends AppCompatActivity {

    // AccountAuthService provides a set of APIs, including silentSignIn, getSignInIntent, and signOut.
    private AccountAuthService mAuthService;
    // Set HUAWEI ID sign-in authorization parameters.
    private AccountAuthParams mAuthParam;
    // Define the request code for signInIntent.
    private static final int REQUEST_CODE_SIGN_IN = 1000;
    // Define the log flag.
    private static final String TAG = "MainActivity";

    private String authHuaweiAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // activity_main is the name of the custom layout file.
        setContentView(R.layout.activity_main);
        findViewById(R.id.HuaweiIdAuthButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                silentSignInByHwId();
            }
        });
    }

    /**
     * Silent sign-in: If a user has authorized your app and signed in, no authorization or sign-in screen will appear during subsequent sign-ins, and the user will directly sign in.
     * After a successful silent sign-in, the HUAWEI ID information will be returned in the success event listener.
     * If the user has not authorized your app or signed in, the silent sign-in will fail. In this case, your app will show the authorization or sign-in screen to the user.
     */
    private void silentSignInByHwId() {
        // 1. Use AccountAuthParams to specify the user information to be obtained, including the user ID (OpenID and UnionID), email address, and profile (nickname and picture).
        // 2. By default, DEFAULT_AUTH_REQUEST_PARAM specifies two items to be obtained, that is, the user ID and profile.
        // 3. If your app needs to obtain the user's email address, call setEmail().
        //  4. To support ID token-based HUAWEI ID sign-in, use setIdToken(). User information can be parsed from the ID token.
        mAuthParam = new AccountAuthParamsHelper(AccountAuthParams.DEFAULT_AUTH_REQUEST_PARAM)
                .setEmail()
                .setIdToken()
                .createParams();
        // Use AccountAuthParams to build AccountAuthService.
        mAuthService = AccountAuthManager.getService(this, mAuthParam);
        // Sign in with a HUAWEI ID silently.
        Task<AuthAccount> task = mAuthService.silentSignIn();
        task.addOnSuccessListener(new OnSuccessListener<AuthAccount>() {
            @Override
            public void onSuccess(AuthAccount authAccount) {
                // The silent sign-in is successful. Process the returned AuthAccount object to obtain the HUAWEI ID information.
                dealWithResultOfSignIn(authAccount);
            }
        });
        task.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
                // The silent sign-in fails. Your app will call getSignInIntent() to show the authorization or sign-in screen.
                if (e instanceof ApiException) {
                    ApiException apiException = (ApiException) e;
                    Intent signInIntent = mAuthService.getSignInIntent();
                    startActivityForResult(signInIntent, REQUEST_CODE_SIGN_IN);

                }
            }
        });
    }

    /**
     * Process the returned AuthAccount object to obtain the HUAWEI ID information.
     *
     * @param authAccount AuthAccount object, which contains the HUAWEI ID information.
     */
    private void dealWithResultOfSignIn(AuthAccount authAccount) {
        Log.i(TAG, "idToken:" + authAccount.getIdToken());
        Gson gson = new Gson();
        authHuaweiAccount = gson.toJson(authAccount);

        Intent userIntent = new Intent(this, UserInfoActivity.class);

        userIntent.putExtra("authHuaweiAccount", authHuaweiAccount);
        startActivity(userIntent);

        // TODO: After obtaining the ID token, your app will send it to your app server if there is one. If you have no app server, your app will verify and parse the ID token locally.
    }
}