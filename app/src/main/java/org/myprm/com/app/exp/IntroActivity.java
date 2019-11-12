package org.myprm.com.app.exp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import androidx.core.content.ContextCompat;
import android.os.Bundle;

import com.github.paolorotolo.appintro.AppIntro2;
import com.github.paolorotolo.appintro.AppIntroFragment;

import org.myprm.com.app.exp.util.Constants;


public class IntroActivity extends AppIntro2 {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addSlide(AppIntroFragment.newInstance("Welcome","Your perfect tool for a meaningful relationship",R.mipmap.intro_image_1, ContextCompat.getColor(this, R.color.sky_blue)));
        addSlide(AppIntroFragment.newInstance("Follow Up","Be In TOuch Always",R.mipmap.lap_mob_intro_screen2, ContextCompat.getColor(this, R.color.spring_green)));
        addSlide(AppIntroFragment.newInstance("Terms","By Continuing You Agree To Our Terms and Conditions http://bit.ly/dhoorbinTerms",R.mipmap.intro_image_settings2, ContextCompat.getColor(this, R.color.slate_blue)));

        showSkipButton(false);

    }

    @Override
    public void onDonePressed() {


        try {
            SharedPreferences prefs;

            prefs = PreferenceManager.getDefaultSharedPreferences(this);
            prefs.edit().putBoolean(Constants.ACCEPT_TERMS_AND_CONDITIONS, true).apply();


        } catch (Exception e) {
            e.printStackTrace();

        }finally{
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }


    }
}
