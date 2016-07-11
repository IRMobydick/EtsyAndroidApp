package com.etsy.android.ui.convos;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MenuItem;
import com.etsy.android.lib.convos.ConvoStateManager;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.Conversation;
import com.etsy.android.ui.BOENavDrawerActivity;
import com.etsy.android.ui.nav.Nav;
import com.etsy.android.uikit.IEtsyFragment;
import com.etsy.android.uikit.util.TabletSupportUtil;

public class ConvoViewActivity extends BOENavDrawerActivity {
    private static final String TAG;

    static {
        TAG = EtsyDebug.m1891a(ConvoViewActivity.class);
    }

    public void onCreate(Bundle bundle) {
        boolean z = true;
        super.onCreate(bundle);
        Conversation conversation = null;
        if (getIntent() != null) {
            Bundle extras = getIntent().getExtras();
            conversation = (Conversation) extras.getSerializable("conversation");
            z = extras.getBoolean("convo_change_read_state", true);
        }
        if (new TabletSupportUtil(this).m5626f() || conversation == null) {
            finish();
            return;
        }
        setContentView(2130903071);
        if (bundle == null) {
            Nav.m4682a((FragmentActivity) this).m4684b().m4626a("convo_thread_fragment").m4618a(conversation, z);
            setTitle(conversation.getOtherUser().getDisplayName());
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 16908332:
                ConvoStateManager.m1005b(this);
                navigateUpAsBack();
                return false;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    public void onBackPressed() {
        ConvoStateManager.m1005b(this);
        IEtsyFragment iEtsyFragment = (IEtsyFragment) getSupportFragmentManager().findFragmentByTag("convo_thread_fragment");
        if (iEtsyFragment != null) {
            iEtsyFragment.handleBackPressed();
        }
        super.onBackPressed();
    }
}
