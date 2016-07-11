package com.etsy.android.ui.search;

import android.content.Context;
import com.etsy.android.lib.R;
import com.etsy.android.uikit.ui.shop.ShopHomeAdapter;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;
import org.apache.commons.lang3.StringUtils;

/* renamed from: com.etsy.android.ui.search.h */
public class SortOrder {
    public static String m4748a(Context context, int i) {
        switch (i) {
            case Task.NETWORK_STATE_CONNECTED /*0*/:
                return context.getResources().getString(R.sort_order_most_recent);
            case Task.NETWORK_STATE_UNMETERED /*1*/:
                return context.getResources().getString(R.sort_order_relevancy);
            case Task.NETWORK_STATE_ANY /*2*/:
                return context.getResources().getString(R.sort_order_highest_price);
            case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                return context.getResources().getString(R.sort_order_lowest_price);
            case CommonStatusCodes.DEVELOPER_ERROR /*10*/:
                return context.getResources().getString(R.sort_order_funding_campaign_end_time);
            case CommonStatusCodes.LICENSE_CHECK_FAILED /*11*/:
                return context.getResources().getString(R.sort_order_funding_campaign_subscriber_count);
            case ShopHomeAdapter.TYPE_BUTTON_BLUE_WITH_DESCRIPTION /*12*/:
                return context.getResources().getString(R.sort_order_funding_campaign_funded_amount);
            case CommonStatusCodes.ERROR /*13*/:
                return context.getResources().getString(R.sort_order_funding_campaign_start_time);
            default:
                return StringUtils.EMPTY;
        }
    }
}
