package org.myprm.com.app.exp.binding;

import androidx.databinding.BindingAdapter;
import android.graphics.Bitmap;
import androidx.fragment.app.Fragment;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import org.myprm.com.app.exp.R;

import javax.inject.Inject;

/**
 * Created by Rakesh on 11/25/17.
 */

public class FragmentBindingAdapters {

    public static final String TAG = "FrgmntBndngAdptrs.class";

    final Fragment fragment;

    @Inject
    public FragmentBindingAdapters(Fragment fragment) {

        Log.d(TAG,"In FrgmntBndngAdptrs");

        this.fragment = fragment;
    }

    //TODO : Check https://github.com/bumptech/glide/issues/2413 to fix Glide NPE Error
    @BindingAdapter("imageUrlRoundedCorner")
    public void bindImageUrlRoundedCorner(ImageView imageView, String url) {
        Log.d(TAG,"In FrgmntBndngAdptrs bindImageUrlRoundedCorner url : " + url + " | imageVIew Id : " + imageView);
        /*Glide.with(fragment).load(url).asBitmap().diskCacheStrategy(DiskCacheStrategy.SOURCE).placeholder(R.mipmap.event_item_placeholder2).into(new BitmapImageViewTarget(imageView) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(fragment.getResources(), resource);
                circularBitmapDrawable.setCornerRadius(6f);
                imageView.setImageDrawable(circularBitmapDrawable);
            }});*/

        Glide
                .with(fragment)
                .load(url)
                .circleCrop()
                .placeholder(R.drawable.ic_add_white_24dp)
                .into(imageView);


    }

}
