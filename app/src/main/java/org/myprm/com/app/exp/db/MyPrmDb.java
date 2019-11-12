package org.myprm.com.app.exp.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import org.myprm.com.app.exp.vo.MyContact;

/**
 * Created by Rakesh on 11/27/17.
 */

@Database(entities = {MyContact.class},version = 1 , exportSchema = false)
@TypeConverters({Converters.class})
public abstract class MyPrmDb extends RoomDatabase{

    abstract public MyPrmDao myPrmDao();

}
