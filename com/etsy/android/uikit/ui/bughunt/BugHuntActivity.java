package com.etsy.android.uikit.ui.bughunt;

import android.animation.Animator;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import com.etsy.android.lib.R;
import com.etsy.android.lib.util.ai;
import com.etsy.android.uikit.nav.TrackingBaseActivity;
import com.etsy.android.uikit.p016a.EtsyAnimator;
import com.etsy.android.uikit.util.HardwareAnimatorListener;
import com.etsy.android.uikit.view.FullImageView;

public class BugHuntActivity extends TrackingBaseActivity implements OnClickListener {
    private static final String ARG_MODE = "mode";
    private static final int MODE_LEADERBOARD = 0;
    private static final int MODE_SUBMIT_BUG = 1;
    FloatingActionButton mFab;
    int mMode;

    /* renamed from: com.etsy.android.uikit.ui.bughunt.BugHuntActivity.1 */
    class C09581 implements OnClickListener {
        final /* synthetic */ Toolbar f4045a;
        final /* synthetic */ BugHuntActivity f4046b;

        C09581(BugHuntActivity bugHuntActivity, Toolbar toolbar) {
            this.f4046b = bugHuntActivity;
            this.f4045a = toolbar;
        }

        public void onClick(View view) {
            if (this.f4046b.mMode == BugHuntActivity.MODE_SUBMIT_BUG) {
                this.f4046b.setupLeaderboard();
                ai.m3225a(this.f4046b.getApplicationContext(), this.f4045a);
            }
        }
    }

    /* renamed from: com.etsy.android.uikit.ui.bughunt.BugHuntActivity.2 */
    class C09592 extends HardwareAnimatorListener {
        final /* synthetic */ boolean f4047a;
        final /* synthetic */ BugHuntActivity f4048b;

        C09592(BugHuntActivity bugHuntActivity, View view, boolean z) {
            this.f4048b = bugHuntActivity;
            this.f4047a = z;
            super(view);
        }

        public void onAnimationStart(Animator animator) {
            if (this.f4047a) {
                this.f4048b.mFab.setVisibility(BugHuntActivity.MODE_LEADERBOARD);
            }
        }

        public void onAnimationEnd(Animator animator) {
            if (!this.f4047a) {
                this.f4048b.mFab.setVisibility(8);
            }
        }
    }

    protected int getGraphikTheme() {
        return R.bughunt_theme;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.activity_bughunt);
        Toolbar toolbar = (Toolbar) findViewById(R.app_bar_toolbar);
        toolbar.setNavigationOnClickListener(new C09581(this, toolbar));
        this.mFab = (FloatingActionButton) findViewById(R.fab_bug);
        this.mFab.setOnClickListener(this);
        if (bundle != null) {
            this.mMode = bundle.getInt(ARG_MODE, MODE_LEADERBOARD);
            switch (this.mMode) {
                case MODE_LEADERBOARD /*0*/:
                    setupLeaderboard();
                case MODE_SUBMIT_BUG /*1*/:
                    setupSubmitBug(null);
                default:
            }
        } else if (getIntent() == null || !getIntent().hasExtra("image_uri")) {
            setupLeaderboard();
        } else {
            setupSubmitBug(getIntent().getStringExtra("image_uri"));
        }
    }

    protected void onSaveInstanceState(Bundle bundle) {
        bundle.putInt(ARG_MODE, this.mMode);
        super.onSaveInstanceState(bundle);
    }

    public void setupLeaderboard() {
        this.mMode = MODE_LEADERBOARD;
        getAppBarHelper().setTitle("Leaderboard");
        getAppBarHelper().setNavigationIcon(getResources().getDrawable(R.ic_bug));
        this.mFab.setImageResource(R.ic_add);
        displayFab(true);
        BugHuntNav.m5421a((FragmentActivity) this).m5425b();
    }

    public void setupSubmitBug(String str) {
        this.mMode = MODE_SUBMIT_BUG;
        getAppBarHelper().setTitle("Submit Bug");
        getAppBarHelper().setNavigationIcon(R.ic_close_white_24dp);
        Drawable drawable = getResources().getDrawable(R.ic_menu_send);
        drawable.setColorFilter(-1, Mode.SRC_IN);
        this.mFab.setImageDrawable(drawable);
        displayFab(true);
        BugHuntNav.m5421a((FragmentActivity) this).m5426b(str);
    }

    public void onBackPressed() {
        if (this.mMode == MODE_SUBMIT_BUG) {
            setupLeaderboard();
        } else {
            super.onBackPressed();
        }
    }

    public void onClick(View view) {
        switch (this.mMode) {
            case MODE_LEADERBOARD /*0*/:
                setupSubmitBug(null);
            case MODE_SUBMIT_BUG /*1*/:
                BugHuntNav.m5421a((FragmentActivity) this).m5427c().send();
            default:
        }
    }

    public void displayFab(boolean z) {
        float f;
        float f2;
        float f3 = FullImageView.ASPECT_RATIO_SQUARE;
        EtsyAnimator a = EtsyAnimator.m5285a(this.mFab);
        if (z) {
            f = 0.1f;
        } else {
            f = FullImageView.ASPECT_RATIO_SQUARE;
        }
        if (z) {
            f2 = FullImageView.ASPECT_RATIO_SQUARE;
        } else {
            f2 = 0.1f;
        }
        EtsyAnimator a2 = a.m5288a(f, f2);
        if (z) {
            f2 = 0.1f;
        } else {
            f2 = FullImageView.ASPECT_RATIO_SQUARE;
        }
        if (!z) {
            f3 = 0.1f;
        }
        a2.m5291b(f2, f3).m5290a(new C09592(this, this.mFab, z)).m5293c();
    }
}
