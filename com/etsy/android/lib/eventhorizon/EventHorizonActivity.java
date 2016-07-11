package com.etsy.android.lib.eventhorizon;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import com.etsy.android.lib.R;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.uikit.BaseActivity;
import com.etsy.android.uikit.util.TabletSupportUtil;
import org.json.JSONObject;

@SuppressLint({"Registered"})
public class EventHorizonActivity extends BaseActivity implements EventHorizonAdapter {
    TabletSupportUtil mTabletSupportUtil;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.event_horizon_activity);
        getAppBarHelper().setTitle(EventHorizon.m1754b());
        this.mTabletSupportUtil = new TabletSupportUtil(this);
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        beginTransaction.add(R.container_event_horizon, new EventHorizonFragment(), "event_horizon");
        beginTransaction.commit();
    }

    public void onSelectEvent(JSONObject jSONObject) {
        EtsyDebug.m1906b(EventHorizonActivity.class.getName(), "onSelectEvent " + jSONObject.toString());
        Fragment newInstance = EventHorizonDetailsFragment.newInstance(jSONObject);
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Object obj = supportFragmentManager.findFragmentByTag("event_horizon_detail") != null ? 1 : null;
        boolean a = this.mTabletSupportUtil.m5621a();
        int i = a ? R.container_event_horizon_detail : R.container_event_horizon;
        FragmentTransaction beginTransaction = supportFragmentManager.beginTransaction();
        if (obj == null && a) {
            beginTransaction.add(i, newInstance, "event_horizon_detail");
        } else {
            beginTransaction.replace(i, newInstance, "event_horizon_detail");
        }
        if (!a) {
            beginTransaction.addToBackStack(null);
        }
        beginTransaction.commit();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
