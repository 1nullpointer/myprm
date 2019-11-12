package org.myprm.com.app.exp;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import org.myprm.com.app.exp.util.Constants;

public class BizEntryActivity extends AppCompatActivity {

    public static final String TAG = "BizEntryActivity.class";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biz_entry);

        Log.d(TAG,"In BizEntryActivity");

        Button enterBizEntryButton  = findViewById(R.id.bizEntryNextButton);

        enterBizEntryButton.setOnClickListener(v-> {

            String bizUserName = null;
            String pwd = null;


                EditText bizIdView =  (EditText) findViewById(R.id.bizIdEntryValue);

                if(bizIdView !=null ){
                    bizUserName = bizIdView.getText().toString().trim();
                }

                EditText pwdView =  (EditText) findViewById(R.id.bizIdPasswordValue);



                if(pwdView !=null ){
                     pwd = pwdView.getText().toString().trim();
                }

            Log.d(TAG,"In bizUserName "+bizUserName + " bizPwd "+pwd);

            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(Constants.BIZ_USERNAME, bizUserName);
            intent.putExtra(Constants.BIZ_PWD, pwd);

                startActivity(intent);
                finish();



        });


    }
}
