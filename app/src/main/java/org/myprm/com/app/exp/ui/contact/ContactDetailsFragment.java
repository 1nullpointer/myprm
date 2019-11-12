package org.myprm.com.app.exp.ui.contact;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import org.myprm.com.app.exp.R;
import org.myprm.com.app.exp.binding.FragmentDataBindingComponent;
import org.myprm.com.app.exp.databinding.ContactDetailsFragmentBinding;
import org.myprm.com.app.exp.di.Injectable;
import org.myprm.com.app.exp.ui.common.NavigationController;
import org.myprm.com.app.exp.util.AutoClearedValue;
import org.myprm.com.app.exp.vo.MyContact;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager2.widget.ViewPager2;

public class ContactDetailsFragment extends Fragment implements Injectable {

    public static final String TAG = "ContactDetailsFmt.class";


    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Inject
    NavigationController navigationController;

    @Inject
    ViewModelProvider.Factory viewModelFactory1;

    androidx.databinding.DataBindingComponent dataBindingComponent = new FragmentDataBindingComponent(this);

    private ContactViewModel contactViewModel;


    public void setSearchQueryMap(Map<String, Object> searchQueryMap) {
        this.searchQueryMap = searchQueryMap;
    }

    private Map<String,Object> searchQueryMap = new HashMap<>();


    AutoClearedValue<ContactDetailsFragmentBinding> binding;

    MyContact myContact ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        Log.d(TAG,"In onCreateView Activity");

        ContactDetailsFragmentBinding dataBinding = DataBindingUtil
                .inflate(inflater, R.layout.contact_details_fragment, container, false,
                        dataBindingComponent);
        binding = new AutoClearedValue<>(this, dataBinding);

        return dataBinding.getRoot();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Log.d(TAG, "In onActivityCreated Activity");

        FloatingActionButton addContactFab = (FloatingActionButton) this.getActivity().findViewById(R.id.addContactFab);
        addContactFab.setVisibility(View.INVISIBLE);

        contactViewModel = ViewModelProviders.of(this.getActivity(), viewModelFactory).get(ContactViewModel.class);

        setUpViewPager();

        setupProfileImage();

    }

    public static void createContactDetailsFragment (MyContact myContact){
        ContactDetailsFragment contactDetailsFragment = new ContactDetailsFragment();
        contactDetailsFragment.myContact = myContact;

    }

    private void setUpViewPager(){

      /*  SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter();

        viewpager.adapter = new ContactDetailsViewPagerAdapter(get, lifecycle);*/

        binding.get().viewpager.setAdapter(new ContactDetailsViewPagerAdapter(this.getActivity()));
        binding.get().viewpager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);

        TabLayoutMediator tabLayoutMediator =  new TabLayoutMediator(binding.get().tabs, binding.get().viewpager ,
                  new TabLayoutMediator.TabConfigurationStrategy()

        {
            public void onConfigureTab (TabLayout.Tab tab, int position){
                //Log.d(TAG, "inconfigure tab  " + binding.get().timelineTab.text);
                if (position == 0)
                    tab.setText("Timeline");
                if (position == 1)
                    //binding.get().timelineTab.setBackgroundColor(getResources().getColor(R.color.blue_5));
                    tab.setText("Reminder");
                if (position == 2)
                   // binding.get().timelineTab.setBackgroundColor(getResources().getColor(R.color.green_1));
                    tab.setText("Info");
            }

        });

        tabLayoutMediator.attach();
    }

    private void setupProfileImage(){
        Glide
                .with(this)
                .load(R.mipmap.profile_img_contact_dtls)
                .circleCrop()
                .placeholder(R.drawable.ic_add_white_24dp)
                .into(binding.get().profileImg1);
    }

}