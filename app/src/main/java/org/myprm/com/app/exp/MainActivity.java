package org.myprm.com.app.exp;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.myprm.com.app.exp.ui.common.NavigationController;
import org.myprm.com.app.exp.ui.contact.ContactViewModel;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class MainActivity extends AppCompatActivity implements HasSupportFragmentInjector{

    public static final String TAG = "MainActivity.class";


    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;


    @Inject
    NavigationController navigationController;

    @Override
    public DispatchingAndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }


    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private ContactViewModel addContactViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Log.d(TAG, "In Main Activity");

        addContactViewModel = ViewModelProviders.of(this, viewModelFactory).get(ContactViewModel.class);

        getDetailsByAndroidId();

        setFabButton();

    }


  /*  @Override
    public void onBackPressed() {
        int count = getFragmentManager().getBackStackEntryCount();
        if (count == 0) {
            super.onBackPressed();
        } else {
            getFragmentManager().popBackStack();
        }
    }*/

    private void getDetailsByAndroidId(){


        try {
            String android_id = Settings.Secure.getString(this.getContentResolver(),
                    Settings.Secure.ANDROID_ID);
            Log.d(TAG,"android_id " +android_id);
               addContactViewModel.getUserId().setValue(android_id);

            BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
            navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

            navigationController.getHomeFragment();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void setFabButton(){

        FloatingActionButton addContactFab = (FloatingActionButton) findViewById(R.id.addContactFab);

        addContactFab.setOnClickListener( v -> {

          navigationController.addContactFragment();


           /* AddContactBottomSheetFragment addContactBottomSheetFragment = AddContactBottomSheetFragment.newInstance();
            addContactBottomSheetFragment.show(this.getSupportFragmentManager(),"addContactBottomSheetFragment");
*/

        });


    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Log.d(TAG, "Bottom Nav Select");
            FloatingActionButton addContactFab = (FloatingActionButton) findViewById(R.id.addContactFab);

            switch (item.getItemId()) {
                case R.id.home_bottom_nav: {
                    Log.d(TAG, "selected Home");
                    addContactFab.setVisibility(View.VISIBLE);
                    navigationController.getHomeFragment();
                    return true;
                }

                case R.id.contacts_bottom_nav: {
                    Log.d(TAG, "selected Contacts");
                    addContactFab.setVisibility(View.VISIBLE);
                    navigationController.getContactListFragment();
                    return true;
                }


                case R.id.settings_bottom_nav: {
                    addContactFab.setVisibility(View.INVISIBLE);
                    navigationController.getSettingsFragment();
                    return true;

                }

            }

            return false;
        }
    };

}
