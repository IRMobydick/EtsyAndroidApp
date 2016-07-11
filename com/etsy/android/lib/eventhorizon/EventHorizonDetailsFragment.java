package com.etsy.android.lib.eventhorizon;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.ResponseConstants;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.json.JSONObject;

public class EventHorizonDetailsFragment extends Fragment {
    private JSONObject mEvent;

    public static EventHorizonDetailsFragment newInstance(JSONObject jSONObject) {
        EventHorizonDetailsFragment eventHorizonDetailsFragment = new EventHorizonDetailsFragment();
        eventHorizonDetailsFragment.mEvent = jSONObject;
        return eventHorizonDetailsFragment;
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.fragment_event_horizon_detail, viewGroup, false);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        try {
            JSONObject jSONObject = this.mEvent.getJSONObject("Value");
            ((TextView) getView().findViewById(R.event_horizon_detail_eventname)).setText(jSONObject.optString(ResponseConstants.EVENT_NAME));
            TextView textView = (TextView) getView().findViewById(R.event_horizon_detail_timestamp);
            long optLong = jSONObject.optLong("timestamp");
            String format = DateFormatUtils.format(optLong, "yyyy-MM-dd HH:mm:ss SSS z");
            textView.setText(Long.toString(optLong) + "  (" + format + ")  (" + DateFormatUtils.formatUTC(optLong, "yyyy-MM-dd HH:mm:ss SSS z") + ")");
            ((TextView) getView().findViewById(R.event_horizon_detail_browserid)).setText(jSONObject.optString("browser_id"));
            ((TextView) getView().findViewById(R.event_horizon_detail_userid)).setText(jSONObject.optString(ResponseConstants.USER_ID));
            ((TextView) getView().findViewById(R.event_horizon_detail_guid)).setText(jSONObject.getString("guid"));
            ((TextView) getView().findViewById(R.event_horizon_detail_pageguid)).setText(jSONObject.getString("page_guid"));
            ((TextView) getView().findViewById(R.event_horizon_detail_eventsource)).setText(jSONObject.getString("event_source"));
            ((TextView) getView().findViewById(R.event_horizon_detail_eventlogger)).setText(jSONObject.getString("event_logger"));
            ((TextView) getView().findViewById(R.event_horizon_detail_ip)).setText(jSONObject.getString("ip"));
            ((TextView) getView().findViewById(R.event_horizon_detail_useragent)).setText(jSONObject.getString("user_agent"));
            ((TextView) getView().findViewById(R.event_horizon_detail_loc)).setText(jSONObject.getString("loc"));
            ((TextView) getView().findViewById(R.event_horizon_detail_ref)).setText(jSONObject.getString("ref"));
            ((TextView) getView().findViewById(R.event_horizon_detail_raw)).setText(jSONObject.toString(2));
        } catch (Throwable e) {
            EtsyDebug.m1917d(EventHorizonDetailsFragment.class.getName(), "failed setting text", e);
        }
    }
}
