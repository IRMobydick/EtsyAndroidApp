package com.appboy.models.cards;

import bo.app.bv;
import bo.app.eu;
import bo.app.fg;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.finds.FindsModule;
import org.json.JSONObject;

public final class ShortNewsCard extends Card {
    private final String f985j;
    private final String f986k;
    private final String f987l;
    private final String f988m;
    private final String f989n;

    public ShortNewsCard(JSONObject jSONObject) {
        this(jSONObject, null, null);
    }

    public ShortNewsCard(JSONObject jSONObject, bv bvVar, eu euVar) {
        super(jSONObject, bvVar, euVar);
        this.f985j = jSONObject.getString(ResponseConstants.DESCRIPTION);
        this.f986k = jSONObject.getString(ResponseConstants.IMAGE);
        this.f987l = fg.m340a(jSONObject, FindsModule.FIELD_TITLE);
        this.f988m = fg.m340a(jSONObject, ResponseConstants.URL);
        this.f989n = fg.m340a(jSONObject, "domain");
    }

    public final String getDescription() {
        return this.f985j;
    }

    public final String getImageUrl() {
        return this.f986k;
    }

    public final String getTitle() {
        return this.f987l;
    }

    public final String getUrl() {
        return this.f988m;
    }

    public final String getDomain() {
        return this.f989n;
    }

    public final String toString() {
        return "ShortNewsCard{mId='" + this.c + '\'' + ", mViewed='" + this.d + '\'' + ", mCreated='" + this.f + '\'' + ", mUpdated='" + this.g + '\'' + ", mDescription='" + this.f985j + '\'' + ", mImageUrl='" + this.f986k + '\'' + ", mTitle='" + this.f987l + '\'' + ", mUrl='" + this.f988m + '\'' + ", mDomain='" + this.f989n + '\'' + "}";
    }
}
