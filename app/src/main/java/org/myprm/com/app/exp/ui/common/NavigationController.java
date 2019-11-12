package org.myprm.com.app.exp.ui.common;

import androidx.fragment.app.FragmentManager;
import android.util.Log;

import org.myprm.com.app.exp.MainActivity;
import org.myprm.com.app.exp.R;
import org.myprm.com.app.exp.ui.contact.AddContactFragment;
import org.myprm.com.app.exp.ui.contact.ContactDetailsFragment;
import org.myprm.com.app.exp.ui.contact.ContactListFragment;
import org.myprm.com.app.exp.ui.home.HomeFragment;
import org.myprm.com.app.exp.ui.settings.SettingsFragment;


import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

public class NavigationController {

    public static final String TAG = "NavigationControl.class";

    private final int containerId;
    private final FragmentManager fragmentManager;

    public Map<String, Object> getSearchQueryMap() {
        return searchQueryMap;
    }

    private Map<String, Object> searchQueryMap = new HashMap<>();

    @Inject
    public NavigationController(MainActivity mainActivity) {

        Log.d(TAG, "In NavigationController Activity");

        this.containerId = R.id.container;
        this.fragmentManager = mainActivity.getSupportFragmentManager();
    }


    public void addContactFragment() {

        Log.d(TAG, "In addContactFragment  " + getSearchQueryMap());

        AddContactFragment addContactFragment = new AddContactFragment();
        addContactFragment.setSearchQueryMap(getSearchQueryMap());

        fragmentManager.beginTransaction()
                .add(addContactFragment,"addContactFragment")
                .addToBackStack(null)
                .replace(containerId, addContactFragment)
                .commitAllowingStateLoss();

    }

    public void getContactListFragment() {

        Log.d(TAG, "In getContactListFragment  " + getSearchQueryMap());

        ContactListFragment contactListFragment = new ContactListFragment();
        contactListFragment.setSearchQueryMap(getSearchQueryMap());

        fragmentManager.beginTransaction()
                .add(contactListFragment,"contactListFragment")
                .addToBackStack(null)
                .replace(containerId, contactListFragment)
                .commitAllowingStateLoss();

    }

    public void getContactDetailsFragment() {

        Log.d(TAG, "In getContactDetailsFragment  " + getSearchQueryMap());

        ContactDetailsFragment contactDetailsFragment = new ContactDetailsFragment();
        contactDetailsFragment.setSearchQueryMap(getSearchQueryMap());

        fragmentManager.beginTransaction()
                .add(contactDetailsFragment,"contactDetailsFragment")
                .addToBackStack(null)
                .replace(containerId, contactDetailsFragment)
                .commitAllowingStateLoss();

    }

    public void getSettingsFragment() {

        Log.d(TAG, "In getSettingsFragment  " + getSearchQueryMap());

        SettingsFragment settingsFragment = new SettingsFragment();
        settingsFragment.setSearchQueryMap(getSearchQueryMap());

        fragmentManager.beginTransaction()
                .add(settingsFragment,"settingsFragment")
                .replace(containerId, settingsFragment)
                .commitAllowingStateLoss();

    }

    public void getHomeFragment() {

        Log.d(TAG, "In getHomeFragment  " + getSearchQueryMap());

        HomeFragment homeFragment = new HomeFragment();
        homeFragment.setSearchQueryMap(getSearchQueryMap());

        fragmentManager.beginTransaction()
                .add(homeFragment,"homeFragment")
                .replace(containerId, homeFragment)
                .commitAllowingStateLoss();

    }


}
