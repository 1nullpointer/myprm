package org.myprm.com.app.exp.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


import org.myprm.com.app.exp.vo.MyContact;

import java.util.List;



@Dao
public interface MyPrmDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMyContacts(List<MyContact> myContactList);

    @Query("SELECT * FROM mycontact")
    LiveData<List<MyContact>> getMyContacts();

}
