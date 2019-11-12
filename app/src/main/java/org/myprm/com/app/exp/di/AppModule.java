/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.myprm.com.app.exp.di;

import android.app.Application;
import androidx.room.Room;

import org.myprm.com.app.exp.db.MyPrmDao;
import org.myprm.com.app.exp.db.MyPrmDb;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


@Module(includes = {ViewModelModule.class})
class AppModule {

    @Singleton @Provides
    MyPrmDb provideDb(Application app) {
        return Room.databaseBuilder(app, MyPrmDb.class,"myPrmBiz.db")
                .fallbackToDestructiveMigration()
                .build();
    }

    @Singleton @Provides
    MyPrmDao provideEventDao(MyPrmDb db) {
        return db.myPrmDao();
    }

    @Provides
    Retrofit retrofitMsg91(){

        //HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        //loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        OkHttpClient client = new OkHttpClient.Builder().build();

        return new Retrofit.Builder()
                .baseUrl("http://api.msg91.com")
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}
