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

import org.myprm.com.app.exp.ui.contact.AddContactFragment;
import org.myprm.com.app.exp.ui.contact.ContactDetailsFragment;
import org.myprm.com.app.exp.ui.contact.ContactListFragment;
import org.myprm.com.app.exp.ui.contact.InfoFragment;
import org.myprm.com.app.exp.ui.contact.ReminderFragment;
import org.myprm.com.app.exp.ui.contact.TimelineFragment;
import org.myprm.com.app.exp.ui.home.HomeFragment;
import org.myprm.com.app.exp.ui.settings.SettingsFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBuildersModule {


    @ContributesAndroidInjector
    abstract AddContactFragment contributeAddContactFragment();

    @ContributesAndroidInjector
    abstract ContactListFragment contributeContactListFragment();

    @ContributesAndroidInjector
    abstract ContactDetailsFragment contributeContactDetailsFragment();

    @ContributesAndroidInjector
    abstract TimelineFragment contributeTimelineFragment();

    @ContributesAndroidInjector
    abstract ReminderFragment contributeReminderFragment();

    @ContributesAndroidInjector
    abstract InfoFragment contributeInfoFragment();

    @ContributesAndroidInjector
    abstract SettingsFragment contributeSettingsFragment();

    @ContributesAndroidInjector
    abstract HomeFragment contributeHomeFragment();
}
