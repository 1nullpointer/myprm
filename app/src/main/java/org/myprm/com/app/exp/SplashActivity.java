package org.myprm.com.app.exp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;

import org.myprm.com.app.exp.util.Constants;

/**
 * Created by Rakesh on 1/14/18.
 */

public class SplashActivity extends AppCompatActivity {

    public static final String TAG = "SplashActivity.class";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        Log.d(TAG, "In SplashActivity ");

        super.onCreate(savedInstanceState);

        try {
            SharedPreferences prefs;

            prefs = PreferenceManager.getDefaultSharedPreferences(this);

            boolean isTermsAndConditionsAccepted = prefs.getBoolean(Constants.ACCEPT_TERMS_AND_CONDITIONS, false);

            boolean isBizIdEntered = prefs.getBoolean(Constants.IS_BIZ_ENTERED, false);

            Log.d(TAG, " Going to  " + isTermsAndConditionsAccepted + "isBizIdEntered "+isBizIdEntered);


            if (isTermsAndConditionsAccepted && true) {

                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
            }else if (isTermsAndConditionsAccepted && !isBizIdEntered) {
                Intent intent = new Intent(this, BizEntryActivity.class);
                startActivity(intent);
                finish();
            } else{
                Intent intent = new Intent(this, IntroActivity.class);
                startActivity(intent);
                prefs.edit().putBoolean(Constants.ACCEPT_TERMS_AND_CONDITIONS, true).apply();
                finish();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
