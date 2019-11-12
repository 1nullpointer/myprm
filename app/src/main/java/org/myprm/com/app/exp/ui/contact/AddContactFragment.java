package org.myprm.com.app.exp.ui.contact;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.myprm.com.app.exp.R;
import org.myprm.com.app.exp.binding.FragmentDataBindingComponent;
import org.myprm.com.app.exp.databinding.AddContactFragmentBinding;
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

public class AddContactFragment  extends Fragment implements Injectable {

    public static final String TAG = "AddCtFragment.class";


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


    AutoClearedValue<AddContactFragmentBinding> binding;

    MyContact myContact = new MyContact();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        Log.d(TAG,"In onCreateView Activity");

         AddContactFragmentBinding dataBinding = DataBindingUtil
                .inflate(inflater, R.layout.add_contact_fragment, container, false,
                        dataBindingComponent);
        binding = new AutoClearedValue<>(this, dataBinding);

        setUpButtons();
        return dataBinding.getRoot();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Log.d(TAG, "In onActivityCreated Activity");
        FloatingActionButton addContactFab = (FloatingActionButton) this.getActivity().findViewById(R.id.addContactFab);
        addContactFab.setVisibility(View.INVISIBLE);


        contactViewModel = ViewModelProviders.of(this.getActivity(), viewModelFactory).get(ContactViewModel.class);

        String android_id = Settings.Secure.getString(this.getActivity().getContentResolver(),
                Settings.Secure.ANDROID_ID);
        Log.d(TAG,"android_id " +android_id);
        contactViewModel.getUserId().setValue(android_id);

    }


        private void setUpButtons(){
        setUpContactNameButton();
        setupTagButton();
        setUpNotesButton();
        setFollowUpDuration();

        setSubmitButton();

        binding.get().addContactBackButton.setOnClickListener( v-> {

            FloatingActionButton addContactFab = (FloatingActionButton) this.getActivity().findViewById(R.id.addContactFab);
            addContactFab.setVisibility(View.VISIBLE);
            this.getActivity().onBackPressed();
        });
    }


    private void setUpContactNameButton(){



        binding.get().enterContactNameEditText.setOnFocusChangeListener( (view,hasFocus) -> {

            if(hasFocus){
                binding.get().enterContactNameEditText.setHint("Jane Smith");
            }else{
                //avoid NPE on activity close
                // TODO:Recheck this part
                if(null == binding.get() || null == binding.get().enterContactName || null== binding.get().enterContactNameEditText){
                    return;
                }
                    binding.get().enterContactNameEditText.setHint("");
            }

        });

        }



        private void setUpNotesButton(){
            binding.get().enterNoteEditText.setOnFocusChangeListener( (view,hasFocus) -> {

            if(hasFocus){
                binding.get().enterNoteEditText.setHint("We got coffee at Starbucks");
            }else{
                //avoid NPE on activity close.
                // TODO:Recheck this part
                if(null == binding.get() || null == binding.get().enterNote || null== binding.get().enterNoteEditText){
                    return;
                }
                    binding.get().enterNoteEditText.setHint("");
            }
        });

    }


    //TODO : Make it dynamic
    private void setupTagButton(){

        binding.get().activityTagGenMeetup.setOnClickListener( view -> {
            binding.get().activityTagGenMeetup.setStrokeColor(ColorStateList.valueOf(getResources().getColor(R.color.blue_5)));
            myContact.setActivityTag(binding.get().activityTagGenMeetup.getTag().toString());

            binding.get().activityTagMsgCall.setStrokeColor(ColorStateList.valueOf(getResources().getColor(R.color.default_button_text_color)));
            binding.get().activityTagCoffeeChat.setStrokeColor(ColorStateList.valueOf(getResources().getColor(R.color.default_button_text_color)));
            binding.get().activityTagLunchDinner.setStrokeColor(ColorStateList.valueOf(getResources().getColor(R.color.default_button_text_color)));
            binding.get().activityTagNetworking.setStrokeColor(ColorStateList.valueOf(getResources().getColor(R.color.default_button_text_color)));
            binding.get().activityTagPartySocial.setStrokeColor(ColorStateList.valueOf(getResources().getColor(R.color.default_button_text_color)));

            hideSoftKeyboard(this.getActivity());

        });

        binding.get().activityTagMsgCall.setOnClickListener( view -> {
            binding.get().activityTagMsgCall.setStrokeColor(ColorStateList.valueOf(getResources().getColor(R.color.blue_5)));
            myContact.setActivityTag(binding.get().activityTagMsgCall.getTag().toString());

            binding.get().activityTagGenMeetup.setStrokeColor(ColorStateList.valueOf(getResources().getColor(R.color.default_button_text_color)));
            binding.get().activityTagCoffeeChat.setStrokeColor(ColorStateList.valueOf(getResources().getColor(R.color.default_button_text_color)));
            binding.get().activityTagLunchDinner.setStrokeColor(ColorStateList.valueOf(getResources().getColor(R.color.default_button_text_color)));
            binding.get().activityTagNetworking.setStrokeColor(ColorStateList.valueOf(getResources().getColor(R.color.default_button_text_color)));
            binding.get().activityTagPartySocial.setStrokeColor(ColorStateList.valueOf(getResources().getColor(R.color.default_button_text_color)));

            hideSoftKeyboard(this.getActivity());

        });

        binding.get().activityTagCoffeeChat.setOnClickListener( view -> {
            binding.get().activityTagCoffeeChat.setStrokeColor(ColorStateList.valueOf(getResources().getColor(R.color.blue_5)));
            myContact.setActivityTag(binding.get().activityTagCoffeeChat.getTag().toString());

            binding.get().activityTagMsgCall.setStrokeColor(ColorStateList.valueOf(getResources().getColor(R.color.default_button_text_color)));
            binding.get().activityTagGenMeetup.setStrokeColor(ColorStateList.valueOf(getResources().getColor(R.color.default_button_text_color)));
            binding.get().activityTagLunchDinner.setStrokeColor(ColorStateList.valueOf(getResources().getColor(R.color.default_button_text_color)));
            binding.get().activityTagNetworking.setStrokeColor(ColorStateList.valueOf(getResources().getColor(R.color.default_button_text_color)));
            binding.get().activityTagPartySocial.setStrokeColor(ColorStateList.valueOf(getResources().getColor(R.color.default_button_text_color)));

            hideSoftKeyboard(this.getActivity());

        });

        binding.get().activityTagLunchDinner.setOnClickListener( view -> {
            binding.get().activityTagLunchDinner.setStrokeColor(ColorStateList.valueOf(getResources().getColor(R.color.blue_5)));
            myContact.setActivityTag(binding.get().activityTagLunchDinner.getTag().toString());

            binding.get().activityTagMsgCall.setStrokeColor(ColorStateList.valueOf(getResources().getColor(R.color.default_button_text_color)));
            binding.get().activityTagCoffeeChat.setStrokeColor(ColorStateList.valueOf(getResources().getColor(R.color.default_button_text_color)));
            binding.get().activityTagGenMeetup.setStrokeColor(ColorStateList.valueOf(getResources().getColor(R.color.default_button_text_color)));
            binding.get().activityTagNetworking.setStrokeColor(ColorStateList.valueOf(getResources().getColor(R.color.default_button_text_color)));
            binding.get().activityTagPartySocial.setStrokeColor(ColorStateList.valueOf(getResources().getColor(R.color.default_button_text_color)));

            hideSoftKeyboard(this.getActivity());

        });

        binding.get().activityTagNetworking.setOnClickListener( view -> {
            binding.get().activityTagNetworking.setStrokeColor(ColorStateList.valueOf(getResources().getColor(R.color.blue_5)));
            myContact.setActivityTag(binding.get().activityTagNetworking.getTag().toString());

            binding.get().activityTagMsgCall.setStrokeColor(ColorStateList.valueOf(getResources().getColor(R.color.default_button_text_color)));
            binding.get().activityTagCoffeeChat.setStrokeColor(ColorStateList.valueOf(getResources().getColor(R.color.default_button_text_color)));
            binding.get().activityTagLunchDinner.setStrokeColor(ColorStateList.valueOf(getResources().getColor(R.color.default_button_text_color)));
            binding.get().activityTagGenMeetup.setStrokeColor(ColorStateList.valueOf(getResources().getColor(R.color.default_button_text_color)));
            binding.get().activityTagPartySocial.setStrokeColor(ColorStateList.valueOf(getResources().getColor(R.color.default_button_text_color)));

            hideSoftKeyboard(this.getActivity());

        });

        binding.get().activityTagPartySocial.setOnClickListener(view -> {
            binding.get().activityTagPartySocial.setStrokeColor(ColorStateList.valueOf(getResources().getColor(R.color.blue_5)));
            myContact.setActivityTag(binding.get().activityTagPartySocial.getTag().toString());

            binding.get().activityTagMsgCall.setStrokeColor(ColorStateList.valueOf(getResources().getColor(R.color.default_button_text_color)));
            binding.get().activityTagCoffeeChat.setStrokeColor(ColorStateList.valueOf(getResources().getColor(R.color.default_button_text_color)));
            binding.get().activityTagLunchDinner.setStrokeColor(ColorStateList.valueOf(getResources().getColor(R.color.default_button_text_color)));
            binding.get().activityTagNetworking.setStrokeColor(ColorStateList.valueOf(getResources().getColor(R.color.default_button_text_color)));
            binding.get().activityTagGenMeetup.setStrokeColor(ColorStateList.valueOf(getResources().getColor(R.color.default_button_text_color)));

            hideSoftKeyboard(this.getActivity());

        });
    }


    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);

        //Avoid NPE
        if(null == activity.getCurrentFocus()){
            return;
        }

        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }


    private void setFollowUpDuration(){

        binding.get().selectReminderSpan.setOnClickListener( v -> {

            SelectFollowUpSpanDialogFragment selectFollowUpSpanDialogFragment = SelectFollowUpSpanDialogFragment.newInstance(contactViewModel,myContact);
            selectFollowUpSpanDialogFragment.show(this.getActivity().getSupportFragmentManager(),"selectFollowUpSpanDialogFragment");

        });

    }

    private void setSubmitButton(){

        binding.get().addToMyCrm.setOnClickListener( view -> {

            myContact.setContactName(binding.get().enterContactNameEditText.getText().toString());
            myContact.setFollowUpNotesId(binding.get().enterNoteEditText.getText().toString());

            Log.d(TAG,"contact Name " +myContact + " "+binding.get().enterContactNameEditText.getText());

            String android_id = Settings.Secure.getString(this.getActivity().getContentResolver(),
                    Settings.Secure.ANDROID_ID);
            contactViewModel.addNewContact(myContact,android_id);

            FloatingActionButton addContactFab = (FloatingActionButton) this.getActivity().findViewById(R.id.addContactFab);
            addContactFab.setVisibility(View.VISIBLE);

            this.getActivity().onBackPressed();

        });

    }

    private void getButtonTag(){

    }

}
