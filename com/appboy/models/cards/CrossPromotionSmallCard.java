package com.appboy.models.cards;

import bo.app.bv;
import bo.app.eu;
import bo.app.fg;
import com.appboy.Constants;
import com.appboy.enums.AppStore;
import com.appboy.support.AppboyLogger;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.finds.FindsModule;
import org.json.JSONObject;

public final class CrossPromotionSmallCard extends Card {
    private static final String f972j;
    private final String f973k;
    private final String f974l;
    private final String f975m;
    private final String f976n;
    private double f977o;
    private int f978p;
    private final double f979q;
    private final String f980r;
    private String f981s;
    private String f982t;
    private AppStore f983u;
    private String f984v;

    static {
        f972j = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, CrossPromotionSmallCard.class.getName()});
    }

    public CrossPromotionSmallCard(JSONObject jSONObject) {
        this(jSONObject, null, null);
    }

    public CrossPromotionSmallCard(JSONObject jSONObject, bv bvVar, eu euVar) {
        super(jSONObject, bvVar, euVar);
        this.f973k = jSONObject.getString(FindsModule.FIELD_TITLE);
        this.f974l = jSONObject.getString(ResponseConstants.SUBTITLE);
        this.f975m = jSONObject.getString(ResponseConstants.CAPTION);
        this.f976n = jSONObject.getString(ResponseConstants.IMAGE);
        try {
            this.f977o = jSONObject.getDouble(ResponseConstants.RATING);
            this.f978p = jSONObject.getInt(ResponseConstants.REVIEWS);
        } catch (Exception e) {
            this.f977o = 0.0d;
            this.f978p = 0;
        }
        if (jSONObject.has("package")) {
            this.f981s = jSONObject.getString("package");
        }
        if (jSONObject.has("kindle_id")) {
            this.f982t = jSONObject.getString("kindle_id");
        }
        this.f979q = jSONObject.getDouble(ResponseConstants.PRICE);
        if (jSONObject.has("display_price")) {
            this.f984v = jSONObject.getString("display_price");
        }
        this.f980r = jSONObject.getString(ResponseConstants.URL);
        if (fg.m340a(jSONObject, "store") != null) {
            try {
                String a = fg.m340a(jSONObject, "store");
                if (a != null) {
                    this.f983u = AppStore.valueOf(AppStore.serverStringToEnumString(a));
                } else {
                    this.f983u = AppStore.GOOGLE_PLAY_STORE;
                }
            } catch (Throwable e2) {
                AppboyLogger.m665e(f972j, "Caught exception creating cross promotion small card Json.", e2);
                this.f983u = AppStore.GOOGLE_PLAY_STORE;
            }
        }
    }

    public final String getTitle() {
        return this.f973k;
    }

    public final String getSubtitle() {
        return this.f974l;
    }

    public final String getCaption() {
        return this.f975m;
    }

    public final String getImageUrl() {
        return this.f976n;
    }

    public final double getRating() {
        return this.f977o;
    }

    public final int getReviewCount() {
        return this.f978p;
    }

    public final double getPrice() {
        return this.f979q;
    }

    public final String getUrl() {
        return this.f980r;
    }

    public final String getPackage() {
        return this.f981s;
    }

    public final String getKindleId() {
        return this.f982t;
    }

    public final AppStore getAppStore() {
        return this.f983u;
    }

    public final String getDisplayPrice() {
        return this.f984v;
    }

    public final String toString() {
        return "CrossPromotionSmallCard{mId='" + this.c + '\'' + ", mViewed='" + this.d + '\'' + ", mCreated='" + this.f + '\'' + ", mUpdated='" + this.g + '\'' + ", mTitle='" + this.f973k + '\'' + ", mSubtitle='" + this.f974l + '\'' + ", mCaption='" + this.f975m + '\'' + ", mImageUrl='" + this.f976n + '\'' + ", mRating=" + this.f977o + ", mReviewCount=" + this.f978p + ", mPrice=" + this.f979q + ", mPackage=" + this.f981s + ", mUrl='" + this.f980r + '\'' + ", mAppStore='" + this.f983u + '\'' + ", mKindleId='" + this.f982t + '\'' + ", mDisplayPrice='" + this.f984v + '\'' + "}";
    }
}
