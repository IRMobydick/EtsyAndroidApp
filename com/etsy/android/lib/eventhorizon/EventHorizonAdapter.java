package com.etsy.android.lib.eventhorizon;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.img.ImageBatch;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.uikit.adapter.BaseRecyclerViewAdapter;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class EventHorizonAdapter extends BaseRecyclerViewAdapter<JSONObject> implements OnClickListener {
    private EventHorizonAdapter mEventHorizonNav;

    public class EventHorizonViewHolder extends ViewHolder {
        public JSONObject event;
        public TextView eventLoggerView;
        public TextView eventNameView;
        public TextView timestampView;

        public EventHorizonViewHolder(View view) {
            super(view);
            this.timestampView = (TextView) view.findViewById(R.event_horizon_summary_timestamp);
            this.eventLoggerView = (TextView) view.findViewById(R.event_horizon_summary_eventlogger);
            this.eventNameView = (TextView) view.findViewById(R.event_horizon_summary_eventname);
        }
    }

    protected EventHorizonAdapter(EventHorizonActivity eventHorizonActivity, ImageBatch imageBatch) {
        super(eventHorizonActivity, imageBatch);
        this.mEventHorizonNav = eventHorizonActivity;
    }

    protected int getListItemViewType(int i) {
        return this.mItems.size();
    }

    protected ViewHolder onCreateListItemViewHolder(ViewGroup viewGroup, int i) {
        return new EventHorizonViewHolder(this.mInflater.inflate(R.fragment_event_horizon, viewGroup, false));
    }

    protected void onBindListItemViewHolder(ViewHolder viewHolder, int i) {
        JSONObject jSONObject = (JSONObject) getItem(i);
        EventHorizonViewHolder eventHorizonViewHolder = (EventHorizonViewHolder) viewHolder;
        eventHorizonViewHolder.event = jSONObject;
        try {
            jSONObject = jSONObject.getJSONObject("Value");
            CharSequence string = jSONObject.getString(ResponseConstants.EVENT_NAME);
            CharSequence string2 = jSONObject.getString("event_logger");
            eventHorizonViewHolder.timestampView.setText(DateFormatUtils.format(jSONObject.getLong("timestamp"), "HH:mm:ss"));
            eventHorizonViewHolder.eventLoggerView.setText(string2);
            eventHorizonViewHolder.eventNameView.setText(string);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        eventHorizonViewHolder.itemView.setTag(eventHorizonViewHolder);
        eventHorizonViewHolder.itemView.setOnClickListener(this);
    }

    public void onClick(View view) {
        JSONObject jSONObject = ((EventHorizonViewHolder) view.getTag()).event;
        EtsyDebug.m1906b(EventHorizonAdapter.class.getName(), "onClick event " + jSONObject.toString());
        this.mEventHorizonNav.onSelectEvent(jSONObject);
    }
}
