package org.myprm.com.app.exp.repository.impl;

import androidx.lifecycle.LiveData;


import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.apache.commons.collections4.CollectionUtils;
import org.myprm.com.app.exp.db.MyPrmDao;
import org.myprm.com.app.exp.db.MyPrmDb;
import org.myprm.com.app.exp.repository.EventRepoI;
import org.myprm.com.app.exp.util.Constants;
import org.myprm.com.app.exp.vo.MyContact;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class MyPrmServiceFirebaseImpl implements EventRepoI {

    private static final String TAG = "MyPrmServiceImpl";
    private static final DatabaseReference EVENT_LIST_REF =
            FirebaseDatabase.getInstance().getReference();
    private final MyPrmDb db;
    private final MyPrmDao myPrmDao;
    private Retrofit retrofit;


    @Inject
    public MyPrmServiceFirebaseImpl(MyPrmDb db, MyPrmDao myPrmDao, Retrofit retrofit) {
        this.db = db;
        this.myPrmDao = myPrmDao;
        this.retrofit=retrofit;

    }


    //TODO:IMP Exception handling
    //TODO:Check if any performance impact for use of instance variables for storing the list



    private Observable<DataSnapshot> contactListObservable(String userId){
        return Observable.create(emitter -> {
            EVENT_LIST_REF.child(Constants.CONTACT_LIST).child(userId).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    Log.d(TAG, " datasnapshot is: " + dataSnapshot);

                    emitter.onNext(dataSnapshot);

                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    Log.w(TAG, " Failed to read value.", error.toException());
                }
            });
        });
    }


    @Override
    public LiveData<List<MyContact>> getMyContactList(String userId) {
        List<MyContact> myContactList = new ArrayList<>();
        contactListObservable(userId).subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(dataSnapshot -> {
                            Log.d(TAG, "getCustListByBiz2 " + dataSnapshot);
                            for(DataSnapshot data1 : dataSnapshot.getChildren()){
                                Log.d(TAG, "getCustListByBiz2  data1: " + data1);
                                    Log.d(TAG, "getCustListByBiz2 data1.getKey() :" + data1.getKey());

                                    try{
                                        MyContact myContact = data1.getValue(MyContact.class);

                                        Log.d(TAG, "myCOntact " + myContact);
                                        myContactList.add(myContact);

                                    }catch(Exception e){
                                        Log.e(TAG," firebaseObservable getMyContactList Error  " + e.getMessage());

                                    }
                                }
                            Log.d(TAG, "getMyContactList myContactList: " + myContactList);
                    saveMyContactsResult(myContactList);
                        }
                );

        LiveData<List<MyContact>> myContactsListFromDb = myPrmDao.getMyContacts();
        Log.d(TAG, "getCustListByBizId : Data : " + myContactsListFromDb.getValue());

        return myContactsListFromDb;
    }


    protected void saveMyContactsResult(List<MyContact> myContactList) {

        Log.d(TAG, "In save callResult saveMyContacts" + myContactList);

        db.beginTransaction();
        try {
            Log.d(TAG, "In save callResult saveWaitListCallResult 2" + myContactList);
            if(CollectionUtils.isNotEmpty(myContactList) && null!=myContactList.get(0)){
                Log.i(TAG,"Updating Customers "+myContactList);
                myPrmDao.insertMyContacts(myContactList);
            }else{
                Log.i(TAG,"Customer List is null . SKip Insert!");
            }

            db.setTransactionSuccessful();
        } finally {
            Log.d(TAG, "    saveWaitListCallResult Update Completed " );
            db.endTransaction();
            myContactList.clear();

        }
    }

    @Override
    public void addNewContact(MyContact myContact,String userId){

        Log.d(TAG,"In updateCustomerWaitList " + myContact +" "+ userId);
        String key = EVENT_LIST_REF.child("myContacts").child(userId).push().getKey();

        Log.d(TAG,"In addNewContact taskId " + key);
        myContact.setContactId(key);
        Map<String, Object> myContactMap = myContact.toMap();
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/myContacts/" +userId+"/"+key, myContactMap);
        EVENT_LIST_REF.updateChildren(childUpdates);

    }

    @Override
    public LiveData<List<MyContact>> getContactList(){

        return myPrmDao.getMyContacts();
    }

    }
