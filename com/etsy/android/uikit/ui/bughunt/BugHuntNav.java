package com.etsy.android.uikit.ui.bughunt;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import com.etsy.android.lib.R;

/* renamed from: com.etsy.android.uikit.ui.bughunt.a */
public class BugHuntNav {
    private FragmentActivity f4053a;

    protected BugHuntNav(FragmentActivity fragmentActivity) {
        this.f4053a = fragmentActivity;
    }

    public static BugHuntNav m5421a(FragmentActivity fragmentActivity) {
        return new BugHuntNav(fragmentActivity);
    }

    public void m5423a() {
        m5422c(null);
    }

    public void m5424a(String str) {
        m5422c(str);
    }

    private void m5422c(@Nullable String str) {
        Intent intent = new Intent(this.f4053a, BugHuntActivity.class);
        if (!TextUtils.isEmpty(str)) {
            intent.putExtra("image_uri", str);
        }
        this.f4053a.startActivity(intent);
        this.f4053a.overridePendingTransition(R.nav_bottom_enter, R.nav_bottom_exit);
    }

    public void m5425b() {
        FragmentManager supportFragmentManager = this.f4053a.getSupportFragmentManager();
        if (supportFragmentManager.findFragmentByTag("statsFragment") == null) {
            FragmentTransaction beginTransaction = supportFragmentManager.beginTransaction();
            beginTransaction.replace(R.fragment_container, BugHuntLeaderboardFragment.newInstance(), "statsFragment");
            beginTransaction.setCustomAnimations(R.nav_frag_bottom_enter, R.nav_frag_bottom_exit);
            beginTransaction.commit();
        }
    }

    public void m5426b(String str) {
        FragmentManager supportFragmentManager = this.f4053a.getSupportFragmentManager();
        if (supportFragmentManager.findFragmentByTag("composeFragment") == null) {
            FragmentTransaction beginTransaction = supportFragmentManager.beginTransaction();
            beginTransaction.replace(R.fragment_container, BugHuntComposeFragment.newInstance(str), "composeFragment");
            beginTransaction.setCustomAnimations(R.nav_frag_bottom_enter, R.nav_frag_bottom_exit);
            beginTransaction.commit();
        }
    }

    public BugHuntComposeFragment m5427c() {
        return (BugHuntComposeFragment) this.f4053a.getSupportFragmentManager().findFragmentByTag("composeFragment");
    }
}
