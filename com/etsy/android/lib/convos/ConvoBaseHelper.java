package com.etsy.android.lib.convos;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import com.etsy.android.lib.R;

/* renamed from: com.etsy.android.lib.convos.a */
public class ConvoBaseHelper {
    public static void m946a(FragmentManager fragmentManager) {
        Fragment findFragmentById = fragmentManager.findFragmentById(R.fragment_detail_container);
        if (findFragmentById != null) {
            fragmentManager.beginTransaction().remove(findFragmentById).commitAllowingStateLoss();
        }
    }

    public static boolean m947b(FragmentManager fragmentManager) {
        return fragmentManager.findFragmentById(R.fragment_detail_container) != null;
    }
}
