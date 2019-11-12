package org.myprm.com.app.exp.binding;

import androidx.databinding.DataBindingComponent;
import androidx.fragment.app.Fragment;

/**
 * Created by Rakesh on 11/25/17.
 */

public class FragmentDataBindingComponent implements DataBindingComponent {
    private final FragmentBindingAdapters adapter;

    public FragmentDataBindingComponent(Fragment fragment) {
        this.adapter = new FragmentBindingAdapters(fragment);
    }

    @Override
    public FragmentBindingAdapters getFragmentBindingAdapters() {
        return adapter;
    }
}
