package com.appboy.models.cards;

import bo.app.bv;
import bo.app.eu;
import bo.app.fg;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.finds.FindsModule;
import org.json.JSONObject;

public final class CaptionedImageCard extends Card {
    private final String f966j;
    private final String f967k;
    private final String f968l;
    private final String f969m;
    private final String f970n;
    private final float f971o;

    public CaptionedImageCard(JSONObject jSONObject) {
        this(jSONObject, null, null);
    }

    public CaptionedImageCard(JSONObject jSONObject, bv bvVar, eu euVar) {
        super(jSONObject, bvVar, euVar);
        this.f966j = jSONObject.getString(ResponseConstants.IMAGE);
        this.f967k = jSONObject.getString(FindsModule.FIELD_TITLE);
        this.f968l = jSONObject.getString(ResponseConstants.DESCRIPTION);
        this.f969m = fg.m340a(jSONObject, ResponseConstants.URL);
        this.f970n = fg.m340a(jSONObject, "domain");
        this.f971o = (float) jSONObject.optDouble("aspect_ratio", 0.0d);
    }

    public final String getImageUrl() {
        return this.f966j;
    }

    public final String getTitle() {
        return this.f967k;
    }

    public final String getDescription() {
        return this.f968l;
    }

    public final String getUrl() {
        return this.f969m;
    }

    public final String getDomain() {
        return this.f970n;
    }

    public final float getAspectRatio() {
        return this.f971o;
    }

    public final String toString() {
        return "CaptionedImageCard{mId='" + this.c + '\'' + ", mViewed='" + this.d + '\'' + ", mCreated='" + this.f + '\'' + ", mUpdated='" + this.g + '\'' + ", mImageUrl='" + this.f966j + '\'' + ", mTitle='" + this.f967k + '\'' + ", mDescription='" + this.f968l + '\'' + ", mUrl='" + this.f969m + '\'' + ", mDomain='" + this.f970n + '\'' + ", mAspectRatio='" + this.f971o + '\'' + "}";
    }
}
