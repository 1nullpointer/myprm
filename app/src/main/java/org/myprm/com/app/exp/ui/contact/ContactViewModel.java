package org.myprm.com.app.exp.ui.contact;

import android.util.Log;

import org.myprm.com.app.exp.repository.impl.MyPrmServiceFirebaseImpl;
import org.myprm.com.app.exp.util.AbsentLiveData;
import org.myprm.com.app.exp.vo.MyContact;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

public class ContactViewModel extends ViewModel {

    public static final String TAG = "ContactVM.class";

    public final MutableLiveData<Map<String,Object>> searchQueryMap = new MutableLiveData<>();

    private final MutableLiveData<String> userId = new MutableLiveData<>() ;

    public LiveData<List<MyContact>> getContactList() {
        return contactList;
    }

    private final LiveData<List<MyContact>> contactList;

    MyPrmServiceFirebaseImpl myPrmServiceFirebase;

    @Inject
    public ContactViewModel(MyPrmServiceFirebaseImpl myPrmServiceFirebase) {

        Log.d(TAG, "In ContactViewModel ");

        this.myPrmServiceFirebase = myPrmServiceFirebase;

        contactList = Transformations.switchMap(userId, input -> {
            if (input.isEmpty()) {

                Log.d(TAG, "bizId custList Executing nothing here ");

                return AbsentLiveData.create();
            } else {

                Log.d(TAG, "contact Id List " + input);
                return myPrmServiceFirebase.getMyContactList(input);
            }

        });


    }


    public void addNewContact(MyContact myContact,String userId){
        myPrmServiceFirebase.addNewContact(myContact,userId);

    }
    public MutableLiveData<String> getUserId() {
        return userId;
    }



    public LiveData<List<MyContact>> getMyContactList(){
        return myPrmServiceFirebase.getContactList();
    }

}