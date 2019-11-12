package org.myprm.com.app.exp.ui.contact;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.myprm.com.app.exp.R;
import org.myprm.com.app.exp.binding.FragmentDataBindingComponent;
import org.myprm.com.app.exp.databinding.TimelineFragmentBinding;
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

public class TimelineFragment extends Fragment implements Injectable {

    public static final String TAG = "TimelineFragment.class";


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

    private Map<String, Object> searchQueryMap = new HashMap<>();


    AutoClearedValue<TimelineFragmentBinding> binding;

    MyContact myContact;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        Log.d(TAG, "In onCreateView Activity");

        TimelineFragmentBinding dataBinding = DataBindingUtil
                .inflate(inflater, R.layout.timeline_fragment, container, false,
                        dataBindingComponent);
        binding = new AutoClearedValue<>(this, dataBinding);

        return dataBinding.getRoot();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Log.d(TAG, "In onActivityCreated Activity");

        contactViewModel = ViewModelProviders.of(this.getActivity(), viewModelFactory).get(ContactViewModel.class);
        setAddNoteButton();


    }

    private void setAddNoteButton(){

        binding.get().timelineAddNewNote.setOnClickListener( v-> {

            AddNewNoteDialogFragment  addNewNoteDialogFragment = AddNewNoteDialogFragment.newInstance(contactViewModel,myContact);
            addNewNoteDialogFragment.show(this.getActivity().getSupportFragmentManager(),"addNewNoteDialogFragment");
        });
    }




}
