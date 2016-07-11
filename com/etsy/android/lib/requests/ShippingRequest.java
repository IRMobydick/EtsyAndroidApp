package com.etsy.android.lib.requests;

import android.content.Context;
import android.text.TextUtils;
import com.etsy.android.lib.core.ad;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.Country;
import com.etsy.android.lib.models.EmptyResult;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.ShippingTemplateEntry;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.editable.EditableListing;
import com.etsy.android.lib.models.editable.EditableShippingTemplate;
import com.etsy.android.lib.models.editable.EditableShippingTemplateEntry;
import com.etsy.android.lib.requests.EtsyRequest.APIv3Scope;
import com.etsy.android.lib.requests.EtsyRequest.EndpointType;
import com.etsy.android.lib.requests.EtsyRequest.RequestMethod;
import com.etsy.android.lib.util.SharedPreferencesUtility;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShippingRequest<Result extends BaseModel> extends EtsyRequest<Result> {
    public static final String INCLUDES_ENTRIES = "Entries,Entries/OriginCountry,Entries/DestinationCountry,Entries/DestinationRegion";
    private static final String TAG;

    static {
        TAG = EtsyDebug.m1891a(ShippingRequest.class);
    }

    public ShippingRequest(String str, RequestMethod requestMethod, Class<Result> cls) {
        super(str, requestMethod, cls);
    }

    public ShippingRequest(String str, RequestMethod requestMethod, Class<Result> cls, EndpointType endpointType) {
        super(str, requestMethod, cls, endpointType);
    }

    public static ShippingRequest<EditableShippingTemplate> getTemplatesForUser() {
        ShippingRequest<EditableShippingTemplate> shippingRequest = new ShippingRequest("/users/__SELF__/shipping/templates", RequestMethod.GET, EditableShippingTemplate.class);
        shippingRequest.addParams(getParamsWithIncludes());
        return shippingRequest;
    }

    public static ShippingRequest<EditableShippingTemplate> getTemplate(EtsyId etsyId) {
        ShippingRequest<EditableShippingTemplate> shippingRequest = new ShippingRequest("/shipping/templates/" + etsyId.toString(), RequestMethod.GET, EditableShippingTemplate.class);
        Map paramsWithIncludes = getParamsWithIncludes();
        paramsWithIncludes.put(EditableListing.FIELD_SHIPPING_TEMPLATE_ID, etsyId.toString());
        shippingRequest.addParams(paramsWithIncludes);
        return shippingRequest;
    }

    public static ShippingRequest<EditableShippingTemplate> createTemplate(Context context, Country country, String str, int i, int i2, List<? extends ShippingTemplateEntry> list) {
        EtsyRequest shippingRequest = new ShippingRequest("/shipping/profiles", RequestMethod.POST, EditableShippingTemplate.class, EndpointType.APIv3);
        setupV3ShopEndpoint(context, shippingRequest);
        shippingRequest.setContentType(JSON_CONTENT_TYPE);
        HashMap hashMap = new HashMap();
        hashMap.put(ResponseConstants.NAME, str);
        hashMap.put("origin_country_id", Integer.valueOf(country.getCountryId()));
        hashMap.put("min_processing_days", Integer.valueOf(i));
        hashMap.put("max_processing_days", Integer.valueOf(i2));
        addEntries(hashMap, list);
        try {
            shippingRequest.setPayload(ad.m1081a().m1083b().writeValueAsBytes(hashMap));
        } catch (Throwable e) {
            EtsyDebug.m1917d(TAG, "Problem parsing map to JSON", e);
        }
        return shippingRequest;
    }

    public static ShippingRequest<EditableShippingTemplate> updateTemplate(Context context, EtsyId etsyId, Country country, String str, int i, int i2, List<EditableShippingTemplateEntry> list) {
        EtsyRequest shippingRequest = new ShippingRequest("/shipping/profiles/" + etsyId.toString(), RequestMethod.PUT, EditableShippingTemplate.class, EndpointType.APIv3);
        setupV3ShopEndpoint(context, shippingRequest);
        shippingRequest.setContentType(JSON_CONTENT_TYPE);
        HashMap hashMap = new HashMap();
        hashMap.put(ResponseConstants.NAME, str);
        hashMap.put("origin_country_id", Integer.valueOf(country.getCountryId()));
        hashMap.put("min_processing_days", Integer.valueOf(i));
        hashMap.put("max_processing_days", Integer.valueOf(i2));
        addEntries(hashMap, list);
        try {
            shippingRequest.setPayload(ad.m1081a().m1083b().writeValueAsBytes(hashMap));
        } catch (Throwable e) {
            EtsyDebug.m1917d(TAG, "Problem parsing map to JSON", e);
        }
        return shippingRequest;
    }

    private static void addEntries(HashMap<String, Object> hashMap, List<? extends ShippingTemplateEntry> list) {
        if (list != null) {
            ArrayList arrayList = new ArrayList(list.size());
            for (ShippingTemplateEntry shippingTemplateEntry : list) {
                if (shippingTemplateEntry != null) {
                    arrayList.add(flattenEntry(shippingTemplateEntry));
                }
            }
            hashMap.put("entries", arrayList);
        }
    }

    public static HashMap<String, Object> flattenEntry(ShippingTemplateEntry shippingTemplateEntry) {
        HashMap<String, Object> hashMap = new HashMap();
        hashMap.put("shipping_profile_entry_id", TextUtils.isEmpty(shippingTemplateEntry.getId().getId()) ? null : shippingTemplateEntry.getId().getId());
        hashMap.put("primary_cost", shippingTemplateEntry.getPrimaryCost());
        hashMap.put("secondary_cost", shippingTemplateEntry.getSecondaryCost());
        hashMap.put("destination_region_id", shippingTemplateEntry.isDestinationRegionSet() ? Long.valueOf(shippingTemplateEntry.getDestinationRegionId().getIdAsLong()) : String.valueOf(0));
        hashMap.put("destination_country_id", shippingTemplateEntry.isDestinationCountrySet() ? Long.valueOf(shippingTemplateEntry.getDestinationCountryId().getIdAsLong()) : String.valueOf(0));
        return hashMap;
    }

    public static ShippingRequest<EmptyResult> deleteTemplate(Context context, EtsyId etsyId) {
        EtsyRequest shippingRequest = new ShippingRequest("/shipping/profiles/" + etsyId.toString(), RequestMethod.DELETE, EmptyResult.class, EndpointType.APIv3);
        setupV3ShopEndpoint(context, shippingRequest);
        return shippingRequest;
    }

    private static HashMap<String, String> getParamsWithIncludes() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("includes", INCLUDES_ENTRIES);
        return hashMap;
    }

    private static void setupV3ShopEndpoint(Context context, EtsyRequest etsyRequest) {
        APIv3Scope.SHOP.setIdentifier(SharedPreferencesUtility.m3152k(context).getId());
        etsyRequest.setV3Scope(APIv3Scope.SHOP);
    }
}
