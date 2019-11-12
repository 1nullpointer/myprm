package org.myprm.com.app.exp.ui.contact.add;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import org.myprm.com.app.exp.R;
import org.myprm.com.app.exp.binding.FragmentDataBindingComponent;
import org.myprm.com.app.exp.databinding.AddContactFragmentBinding;
import org.myprm.com.app.exp.util.AutoClearedValue;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

public class AddContactBottomSheetFragment extends BottomSheetDialogFragment {

    public static final String TAG = "addCOntactBSheet.class";


    androidx.databinding.DataBindingComponent dataBindingComponent = new FragmentDataBindingComponent(this);

    AutoClearedValue<AddContactFragmentBinding> binding;

    /**
     * Create a new instance of MyDialogFragment, providing "num"
     * as an argument.
     */
    public static AddContactBottomSheetFragment newInstance() {
        AddContactBottomSheetFragment addContactBottomSheetFragment = new AddContactBottomSheetFragment();

        return addContactBottomSheetFragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //View v = inflater.inflate(R.layout.doctor_leave_custom_fragment, container, false);

        AddContactFragmentBinding dataBinding = DataBindingUtil.inflate(inflater, R.layout.add_contact_fragment, container, false, dataBindingComponent);
        binding = new AutoClearedValue<>(this, dataBinding);

        // Do all the stuff to initialize your custom view
binding.get().containerAddContactFrgmt.setMaxHeight(2000);
        return dataBinding.getRoot();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        Log.d(TAG, "In onActivity created  ");

        super.onActivityCreated(savedInstanceState);
    }

}