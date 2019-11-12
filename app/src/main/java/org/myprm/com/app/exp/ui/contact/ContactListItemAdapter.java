package org.myprm.com.app.exp.ui.contact;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;

import org.apache.commons.lang3.StringUtils;
import org.myprm.com.app.exp.R;
import org.myprm.com.app.exp.databinding.ContactListItemBinding;
import org.myprm.com.app.exp.ui.common.DataBoundListAdapter;
import org.myprm.com.app.exp.util.Objects;
import org.myprm.com.app.exp.vo.MyContact;

import androidx.databinding.DataBindingUtil;

public class ContactListItemAdapter extends DataBoundListAdapter<MyContact, ContactListItemBinding> {

    public static final String TAG = "CListItmAdptr.class";

    private final androidx.databinding.DataBindingComponent dataBindingComponent;
    private final MyContactDetailsClickCallBack myContactDetailsClickCallBack;

    private ContactViewModel contactViewModel;

    private ContactListFragment fragment ;

    public ContactListItemAdapter(androidx.databinding.DataBindingComponent dataBindingComponent, MyContactDetailsClickCallBack myContactDetailsClickCallBack,ContactViewModel contactViewModel,ContactListFragment contactListFragment) {

        Log.d(TAG,"In ContactListItemAdapter List Adapter Activity");

        this.dataBindingComponent = dataBindingComponent;
        this.myContactDetailsClickCallBack=myContactDetailsClickCallBack;
        this.contactViewModel = contactViewModel;

        this.fragment = contactListFragment;
    }

    @Override
    protected ContactListItemBinding createBinding(ViewGroup parent) {
        ContactListItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.contact_list_item,
                        parent, false, dataBindingComponent);

        Log.d(TAG,"ContactListItemBinding  Item ");

        binding.getRoot().setOnClickListener(v -> {
            MyContact myContact = binding.getMyContact();
            Log.d(TAG,"Clicked Item " + myContact.getContactName());
            if (myContact != null && null!=fragment) {

                //createContactDetailsFragment(myContact);

                myContactDetailsClickCallBack.onClick(myContact);
            }
        });

        return binding;
    }

    @Override
    protected void bind(ContactListItemBinding binding, MyContact item) {
        binding.setMyContact(item);
        MyContact myContact = binding.getMyContact();
        if (myContact != null) {

            ColorGenerator generator = ColorGenerator.MATERIAL; // or use DEFAULT
            int color1 = generator.getRandomColor();

            if(StringUtils.isEmpty(item.getContactName())){
                item.setContactName("Random Name");
            }

            TextDrawable drawable2 = TextDrawable.builder()
                    .buildRound(String.valueOf(item.getContactName().charAt(0)), color1);
            binding.contactItemItemIconImg.setImageDrawable(drawable2);
        }
    }

    @Override
    protected boolean areItemsTheSame(MyContact oldItem, MyContact newItem) {
        return Objects.equals(oldItem.getContactId(), newItem.getContactId()) &&
                Objects.equals(oldItem.getContactName(), newItem.getContactName());
    }

    //TODO:check if comparing description affects performance

    @Override
    protected boolean areContentsTheSame(MyContact oldItem, MyContact newItem) {
        return Objects.equals(oldItem.getContactName(), newItem.getContactName()) && Objects.equals(oldItem.getContactId(), newItem.getContactId());
    }

    public interface MyContactDetailsClickCallBack {
        void onClick(MyContact customer);
    }

}