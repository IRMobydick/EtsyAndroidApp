package com.etsy.android.lib.models.apiv3;

import android.text.Html;
import com.etsy.android.lib.core.http.request.EtsyApiV3Request;
import com.etsy.android.lib.models.Listing;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.requests.EtsyRequest;

public class TranslatedListing extends Listing {
    public String getDescription() {
        return String.valueOf(Html.fromHtml(this.mDescription));
    }

    public boolean isMachineTranslated() {
        return true;
    }

    public static EtsyApiV3Request<TranslatedListing> createRequestBuilder(EtsyId etsyId, String str) {
        return (EtsyApiV3Request) new EtsyApiV3Request(TranslatedListing.class, "/public/translations/listings/" + etsyId).m1385a(EtsyRequest.PARAM_LANGUAGE, str);
    }
}
