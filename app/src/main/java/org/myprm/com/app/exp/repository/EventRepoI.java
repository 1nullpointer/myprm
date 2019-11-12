package org.myprm.com.app.exp.repository;

import androidx.lifecycle.LiveData;

import org.myprm.com.app.exp.vo.MyContact;


import java.util.List;


public interface EventRepoI {

     LiveData<List<MyContact>> getMyContactList(String userId);

     void addNewContact(MyContact myContact,String userId);

     LiveData<List<MyContact>> getContactList();


    }
