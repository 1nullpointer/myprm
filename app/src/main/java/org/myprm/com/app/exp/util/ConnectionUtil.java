package org.myprm.com.app.exp.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import androidx.fragment.app.Fragment;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

/**
 * Created by Rakesh on 8/19/18.
 */

public class ConnectionUtil {

    public static boolean isOnline(Fragment fragment) {
        ConnectivityManager conMgr = (ConnectivityManager) fragment.getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        if (null == conMgr) {
            Toasty.warning(fragment.getActivity(), "No Internet connection!!!", Toast.LENGTH_LONG, true).show();
            return false;
        }

        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();
        if (netInfo == null || !netInfo.isConnected() || !netInfo.isAvailable()) {
            Toasty.warning(fragment.getActivity(), "No Internet connection!", Toast.LENGTH_LONG, true).show();
            return false;
        }


        return true;
    }
}
