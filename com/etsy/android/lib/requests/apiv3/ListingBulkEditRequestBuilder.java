package com.etsy.android.lib.requests.apiv3;

import android.support.annotation.NonNull;
import com.etsy.android.lib.core.ad;
import com.etsy.android.lib.core.http.body.FormBody;
import com.etsy.android.lib.core.http.request.EtsyApiV3Request;
import com.etsy.android.lib.models.apiv3.ActivityFeedEntity;
import com.etsy.android.lib.models.apiv3.BulkEditPreview;
import com.etsy.android.lib.models.apiv3.BulkEditStatus;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.editable.EditableListing;
import com.etsy.android.lib.models.homescreen.LandingPageLink;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

public class ListingBulkEditRequestBuilder {
    public static EtsyApiV3Request<BulkEditStatus> delete(EtsyId etsyId, Collection<EtsyId> collection) {
        Map hashMap = new HashMap();
        hashMap.put("state_action", "delete");
        return buildRequest(etsyId, collection, hashMap);
    }

    public static EtsyApiV3Request<BulkEditStatus> publish(EtsyId etsyId, Collection<EtsyId> collection) {
        Map hashMap = new HashMap();
        hashMap.put("state_action", EditableListing.REQUEST_PARAM_PUBLISH);
        return buildRequest(etsyId, collection, hashMap);
    }

    public static EtsyApiV3Request<BulkEditPreview> publishPreview(EtsyId etsyId, Collection<EtsyId> collection) {
        Map hashMap = new HashMap();
        hashMap.put("state_action", EditableListing.REQUEST_PARAM_PUBLISH);
        return buildPreviewRequest(etsyId, collection, hashMap);
    }

    public static EtsyApiV3Request<BulkEditStatus> renew(EtsyId etsyId, Collection<EtsyId> collection) {
        Map hashMap = new HashMap();
        hashMap.put("state_action", EditableListing.FIELD_RENEW);
        return buildRequest(etsyId, collection, hashMap);
    }

    public static EtsyApiV3Request<BulkEditPreview> renewPreview(EtsyId etsyId, Collection<EtsyId> collection) {
        Map hashMap = new HashMap();
        hashMap.put("state_action", EditableListing.FIELD_RENEW);
        return buildPreviewRequest(etsyId, collection, hashMap);
    }

    public static EtsyApiV3Request<BulkEditStatus> activate(EtsyId etsyId, Collection<EtsyId> collection) {
        Map hashMap = new HashMap();
        hashMap.put("state_action", "activate");
        return buildRequest(etsyId, collection, hashMap);
    }

    public static EtsyApiV3Request<BulkEditPreview> activatePreview(EtsyId etsyId, Collection<EtsyId> collection) {
        Map hashMap = new HashMap();
        hashMap.put("state_action", "activate");
        return buildPreviewRequest(etsyId, collection, hashMap);
    }

    public static EtsyApiV3Request<BulkEditStatus> deactivate(EtsyId etsyId, Collection<EtsyId> collection) {
        Map hashMap = new HashMap();
        hashMap.put("state_action", "deactivate");
        return buildRequest(etsyId, collection, hashMap);
    }

    private static EtsyApiV3Request<BulkEditStatus> buildRequest(EtsyId etsyId, Collection<EtsyId> collection, Map<String, String> map) {
        return (EtsyApiV3Request) ((EtsyApiV3Request) ((EtsyApiV3Request) EtsyApiV3Request.m1454a(BulkEditStatus.class, String.format("/shop/%d/bulk-edit", new Object[]{Long.valueOf(etsyId.getIdAsLong())})).m1382a(1)).m1383a(buildForm(collection, map))).m1393d();
    }

    private static EtsyApiV3Request<BulkEditPreview> buildPreviewRequest(EtsyId etsyId, Collection<EtsyId> collection, Map<String, String> map) {
        return (EtsyApiV3Request) ((EtsyApiV3Request) ((EtsyApiV3Request) EtsyApiV3Request.m1454a(BulkEditPreview.class, String.format("/shop/%d/bulk-edit/preview", new Object[]{Long.valueOf(etsyId.getIdAsLong())})).m1382a(1)).m1383a(buildForm(collection, map))).m1393d();
    }

    @NonNull
    private static FormBody buildForm(Collection<EtsyId> collection, Map<String, String> map) {
        Iterable arrayList = new ArrayList();
        for (EtsyId id : collection) {
            arrayList.add(id.getId());
        }
        FormBody formBody = new FormBody();
        try {
            ((FormBody) ((FormBody) formBody.m1341b("selection_type", LandingPageLink.PAGE_TYPE_LISTINGS)).m1341b("selection_criteria", StringUtils.join(arrayList, ","))).m1341b(ActivityFeedEntity.DATA, ad.m1081a().m1083b().writeValueAsString(map));
        } catch (JsonProcessingException e) {
        }
        return (FormBody) formBody.m1345f();
    }
}
