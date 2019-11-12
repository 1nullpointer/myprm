package org.myprm.com.app.exp.ui.contact;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.myprm.com.app.exp.R;
import org.myprm.com.app.exp.binding.FragmentDataBindingComponent;
import org.myprm.com.app.exp.databinding.SelectFollowUpSpanBinding;
import org.myprm.com.app.exp.util.AutoClearedValue;
import org.myprm.com.app.exp.vo.MyContact;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

public class SelectFollowUpSpanDialogFragment extends DialogFragment {

    public static final String TAG = "followupdialog.class";


    androidx.databinding.DataBindingComponent dataBindingComponent = new FragmentDataBindingComponent(this);

    AutoClearedValue<SelectFollowUpSpanBinding> binding;

    private MyContact myContact;

    /**
     * Create a new instance of MyDialogFragment, providing "num"
     * as an argument.
     */
    static SelectFollowUpSpanDialogFragment newInstance(ContactViewModel contactViewModel, MyContact myContact) {
        SelectFollowUpSpanDialogFragment selectFollowUpSpanDialogFragment = new SelectFollowUpSpanDialogFragment();
        selectFollowUpSpanDialogFragment.myContact=myContact;

        return selectFollowUpSpanDialogFragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //View v = inflater.inflate(R.layout.doctor_leave_custom_fragment, container, false);

        SelectFollowUpSpanBinding dataBinding = DataBindingUtil.inflate(inflater, R.layout.select_follow_up_span, container, false, dataBindingComponent);
        binding = new AutoClearedValue<>(this, dataBinding);

        // Do all the stuff to initialize your custom view

        return dataBinding.getRoot();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        Log.d(TAG, "In onActivity created  ");

            binding.get().followUpDontRemindMe.setOnClickListener( view -> {
                //TODO : update viewmodel

                //TODO: Recheck NPE on .setFollowUpSpan(java.lang.String)'
                if(null!=myContact){
                    myContact.setFollowUpSpan("0");
                }
                SelectFollowUpSpanDialogFragment.this.dismiss();
            });

            binding.get().followUp2Week.setOnClickListener( view -> {
                //TODO : update viewmodel
                if(null!=myContact){
                    myContact.setFollowUpSpan("1");
                }
                SelectFollowUpSpanDialogFragment.this.dismiss();
            });

            binding.get().followUpMonthly.setOnClickListener( view -> {
                //TODO : update viewmodel
                if(null!=myContact){
                    myContact.setFollowUpSpan("2");
                }
                SelectFollowUpSpanDialogFragment.this.dismiss();
            });
            binding.get().followUpThreeMonths.setOnClickListener( view -> {
                //TODO : update viewmodel
                if(null!=myContact){
                    myContact.setFollowUpSpan("3");
                }
                SelectFollowUpSpanDialogFragment.this.dismiss();
            });
            binding.get().followUpSixMonths.setOnClickListener( view -> {
                //TODO : update viewmodel
                if(null!=myContact){
                    myContact.setFollowUpSpan("4");
                }
                SelectFollowUpSpanDialogFragment.this.dismiss();
            });
            binding.get().followUpYearly.setOnClickListener( view -> {
                //TODO : update viewmodel
                if(null!=myContact){
                    myContact.setFollowUpSpan("5");
                }
                SelectFollowUpSpanDialogFragment.this.dismiss();
            });


        super.onActivityCreated(savedInstanceState);
    }

    }