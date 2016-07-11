package com.appboy.models.cards;

import bo.app.bv;
import bo.app.eu;
import bo.app.fg;
import com.etsy.android.lib.models.ResponseConstants;
import org.json.JSONObject;

public final class BannerImageCard extends Card {
    private final String f962j;
    private final String f963k;
    private final String f964l;
    private final float f965m;

    public BannerImageCard(JSONObject jSONObject) {
        this(jSONObject, null, null);
    }

    public BannerImageCard(JSONObject jSONObject, bv bvVar, eu euVar) {
        super(jSONObject, bvVar, euVar);
        this.f962j = jSONObject.getString(ResponseConstants.IMAGE);
        this.f963k = fg.m340a(jSONObject, ResponseConstants.URL);
        this.f964l = fg.m340a(jSONObject, "domain");
        this.f965m = (float) jSONObject.optDouble("aspect_ratio", 0.0d);
    }

    public final String getImageUrl() {
        return this.f962j;
    }

    public final String getUrl() {
        return this.f963k;
    }

    public final String getDomain() {
        return this.f964l;
    }

    public final float getAspectRatio() {
        return this.f965m;
    }

    public final String toString() {
        return "BannerImageCard{mId='" + this.c + '\'' + ", mViewed='" + this.d + '\'' + ", mCreated='" + this.f + '\'' + ", mUpdated='" + this.g + '\'' + ", mImageUrl='" + this.f962j + '\'' + ", mUrl='" + this.f963k + '\'' + ", mDomain='" + this.f964l + '\'' + ", mAspectRatio='" + this.f965m + '\'' + "}";
    }
}
