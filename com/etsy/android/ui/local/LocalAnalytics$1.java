package com.etsy.android.ui.local;

import com.etsy.android.lib.models.ResponseConstants;
import java.util.HashMap;

final class LocalAnalytics$1 extends HashMap<String, Object> {
    final /* synthetic */ double val$latitude;
    final /* synthetic */ double val$longitude;

    LocalAnalytics$1(double d, double d2) {
        this.val$latitude = d;
        this.val$longitude = d2;
        put(ResponseConstants.LAT, Double.valueOf(this.val$latitude));
        put(ResponseConstants.LON, Double.valueOf(this.val$longitude));
    }
}
