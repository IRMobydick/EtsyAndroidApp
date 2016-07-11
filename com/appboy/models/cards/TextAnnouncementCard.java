package com.appboy.models.cards;

import bo.app.bv;
import bo.app.eu;
import bo.app.fg;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.finds.FindsModule;
import org.json.JSONObject;

public final class TextAnnouncementCard extends Card {
    private final String f990j;
    private final String f991k;
    private final String f992l;
    private final String f993m;

    public TextAnnouncementCard(JSONObject jSONObject) {
        this(jSONObject, null, null);
    }

    public TextAnnouncementCard(JSONObject jSONObject, bv bvVar, eu euVar) {
        super(jSONObject, bvVar, euVar);
        this.f991k = fg.m340a(jSONObject, FindsModule.FIELD_TITLE);
        this.f990j = jSONObject.getString(ResponseConstants.DESCRIPTION);
        this.f992l = fg.m340a(jSONObject, ResponseConstants.URL);
        this.f993m = fg.m340a(jSONObject, "domain");
    }

    public final String getDescription() {
        return this.f990j;
    }

    public final String getTitle() {
        return this.f991k;
    }

    public final String getUrl() {
        return this.f992l;
    }

    public final String getDomain() {
        return this.f993m;
    }

    public final String toString() {
        return "TextAnnouncementCard{mId='" + this.c + '\'' + ", mViewed='" + this.d + '\'' + ", mCreated='" + this.f + '\'' + ", mUpdated='" + this.g + '\'' + ", mDescription='" + this.f990j + '\'' + ", mTitle='" + this.f991k + '\'' + ", mUrl='" + this.f992l + '\'' + ", mDomain='" + this.f993m + '\'' + "}";
    }
}
