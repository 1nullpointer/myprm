package org.myprm.com.app.exp.ui.contact;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ContactDetailsViewPagerAdapter extends FragmentStateAdapter {
    private static final int CARD_ITEM_SIZE = 3;

    public ContactDetailsViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override public Fragment createFragment(int position) {

        if(position == 0 ){
        return new TimelineFragment();
        }else if(position == 1){
            return new ReminderFragment();

        }else if(position == 2){
            return new InfoFragment();
        }

        return new TimelineFragment();

    }

    @Override public int getItemCount() {
        return CARD_ITEM_SIZE;
    }
}