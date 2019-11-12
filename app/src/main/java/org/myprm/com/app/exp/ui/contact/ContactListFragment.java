package org.myprm.com.app.exp.ui.contact;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amulyakhare.textdrawable.TextDrawable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.myprm.com.app.exp.R;
import org.myprm.com.app.exp.binding.FragmentDataBindingComponent;
import org.myprm.com.app.exp.databinding.ContactListFragmentBinding;
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
import androidx.recyclerview.widget.DividerItemDecoration;

public class ContactListFragment extends Fragment implements Injectable {

    public static final String TAG = "CtListFragment.class";


    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Inject
    NavigationController navigationController;

    @Inject
    ViewModelProvider.Factory viewModelFactory1;

    @Inject
    NavigationController navigationController1;

    androidx.databinding.DataBindingComponent dataBindingComponent = new FragmentDataBindingComponent(this);

    private ContactViewModel contactViewModel;


    public void setSearchQueryMap(Map<String, Object> searchQueryMap) {
        this.searchQueryMap = searchQueryMap;
    }

    private Map<String,Object> searchQueryMap = new HashMap<>();


    AutoClearedValue<ContactListFragmentBinding> binding;

    MyContact myContact = new MyContact();

    AutoClearedValue<ContactListItemAdapter> contactListItemAdapterACV;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        Log.d(TAG,"In onCreateView Activity");

        ContactListFragmentBinding dataBinding = DataBindingUtil
                .inflate(inflater, R.layout.contact_list_fragment, container, false,
                        dataBindingComponent);
        binding = new AutoClearedValue<>(this, dataBinding);

        return dataBinding.getRoot();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Log.d(TAG, "In onActivityCreated Activity");

        FloatingActionButton addContactFab = (FloatingActionButton) this.getActivity().findViewById(R.id.addContactFab);
        addContactFab.setVisibility(View.VISIBLE);

        contactViewModel = ViewModelProviders.of(this.getActivity(), viewModelFactory).get(ContactViewModel.class);

        ContactListItemAdapter contactListItemAdapter = new ContactListItemAdapter(dataBindingComponent,(v) -> navigationController.getContactDetailsFragment(),contactViewModel,this);
        contactListItemAdapterACV = new AutoClearedValue<>(this, contactListItemAdapter);
        binding.get().contactList.setAdapter(contactListItemAdapter);
        //binding.get().contactList.addItemDecoration(new DividerItemDecoration(binding.get().contactList.getContext(), DividerItemDecoration.VERTICAL));


        contactViewModel.getContactList().observe( this, contactList2 -> {

            Log.d(TAG, "contactList Intial" + contactList2 );

            if(null == contactList2){
                Log.d(TAG,"contactList2 is null");
                return;
            }
            contactListItemAdapterACV.get().replace(contactList2 == null ? null : contactList2);
            binding.get().executePendingBindings();

        });


  /*      contactViewModel.getMyContactList().observe( this, contactList -> {
            Log.d(TAG, "mycontactList " + contactList);

            contactListItemAdapterACV.get().replace(contactList == null ? null : contactList);
            //binding.get().executePendingBindings();

        });*/

    }



}