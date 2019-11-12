package org.myprm.com.app.exp.ui.contact;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import org.myprm.com.app.exp.R;
import org.myprm.com.app.exp.binding.FragmentDataBindingComponent;
import org.myprm.com.app.exp.databinding.AddNewNoteDialogFrgmentBinding;
import org.myprm.com.app.exp.util.AutoClearedValue;
import org.myprm.com.app.exp.vo.MyContact;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

public class AddNewNoteDialogFragment extends BottomSheetDialogFragment {

    public static final String TAG = "followupdialog.class";


    androidx.databinding.DataBindingComponent dataBindingComponent = new FragmentDataBindingComponent(this);

    AutoClearedValue<AddNewNoteDialogFrgmentBinding> binding;

    private MyContact myContact;
    private ContactViewModel contactViewModel;

    /**
     * Create a new instance of MyDialogFragment, providing "num"
     * as an argument.
     */
    static AddNewNoteDialogFragment newInstance(ContactViewModel contactViewModel, MyContact myContact) {
        AddNewNoteDialogFragment addNewNoteDialogFragment = new AddNewNoteDialogFragment();
        addNewNoteDialogFragment.myContact=myContact;
        addNewNoteDialogFragment.contactViewModel =  contactViewModel;

        return addNewNoteDialogFragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //View v = inflater.inflate(R.layout.doctor_leave_custom_fragment, container, false);

        AddNewNoteDialogFrgmentBinding dataBinding = DataBindingUtil.inflate(inflater, R.layout.add_new_note_dialog_frgment, container, false, dataBindingComponent);
        binding = new AutoClearedValue<>(this, dataBinding);

        // Do all the stuff to initialize your custom view

        return dataBinding.getRoot();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        Log.d(TAG, "In onActivity created  ");

        super.onActivityCreated(savedInstanceState);

        setNextFollowUpDate();
    }

    private void setNextFollowUpDate(){

        binding.get().newNoteFollowUpDate.setOnClickListener( v -> {

            SelectFollowUpSpanDialogFragment selectFollowUpSpanDialogFragment = SelectFollowUpSpanDialogFragment.newInstance(contactViewModel,myContact);
            selectFollowUpSpanDialogFragment.show(this.getActivity().getSupportFragmentManager(),"selectFollowUpSpanDialogFragment");

        });
    }


}