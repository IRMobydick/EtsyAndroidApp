package com.etsy.android.lib.requests.apiv3;

import com.etsy.android.lib.core.ad;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.EmptyResult;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.editable.EditableShareItem;
import com.etsy.android.lib.models.finds.FindsModule;
import com.etsy.android.lib.models.shopshare.ShareAnnotation;
import com.etsy.android.lib.models.shopshare.ShareItem;
import com.etsy.android.lib.requests.EtsyMultipartEntity;
import com.etsy.android.lib.requests.EtsyMultipartEntity.AsyncMultipartRequestCallback;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.requests.EtsyRequest.APIv3Scope;
import com.etsy.android.lib.requests.EtsyRequest.EndpointType;
import com.etsy.android.lib.requests.EtsyRequest.RequestMethod;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;

public class ShareItemRequest<R extends BaseModel> extends EtsyRequest<R> {
    private static final String TAG;

    static {
        TAG = EtsyDebug.m1891a(ShareItemRequest.class);
    }

    public ShareItemRequest(String str, RequestMethod requestMethod, Class<R> cls) {
        super(str, requestMethod, cls, EndpointType.APIv3);
    }

    public static ShareItemRequest<ShareItem> create(EtsyId etsyId, EditableShareItem editableShareItem, AsyncMultipartRequestCallback asyncMultipartRequestCallback) {
        APIv3Scope.SHOP.setIdentifier(etsyId.getId());
        EtsyRequest shareItemRequest = new ShareItemRequest("/shares", RequestMethod.POST, ShareItem.class);
        try {
            ShareAnnotation primaryAnnotation = editableShareItem.getPrimaryAnnotation();
            Map hashMap = new HashMap();
            hashMap.put(ResponseConstants.OBJECT_ID, Long.valueOf(primaryAnnotation.getObjectId().getIdAsLong()));
            hashMap.put(ResponseConstants.OBJECT_TYPE, Integer.valueOf(primaryAnnotation.getObjectType()));
            hashMap.put(ResponseConstants.COORD_X, Integer.valueOf(primaryAnnotation.getCoordX()));
            hashMap.put(ResponseConstants.COORD_Y, Integer.valueOf(primaryAnnotation.getCoordY()));
            List arrayList = new ArrayList();
            arrayList.add(hashMap);
            shareItemRequest.setV3Scope(APIv3Scope.SHOP);
            EtsyMultipartEntity etsyMultipartEntity = new EtsyMultipartEntity();
            etsyMultipartEntity.addPart(FindsModule.FIELD_TEXT, new StringBody(editableShareItem.getText(), EtsyMultipartEntity.UTF_8));
            etsyMultipartEntity.addPart(ResponseConstants.ANNOTATIONS, new StringBody(ad.m1081a().m1083b().writeValueAsString(arrayList)));
            etsyMultipartEntity.addPart(ResponseConstants.MEDIA, new FileBody(editableShareItem.getFile()));
            etsyMultipartEntity.createMultipartAsync(shareItemRequest, asyncMultipartRequestCallback);
        } catch (JsonProcessingException e) {
            asyncMultipartRequestCallback.onRequestCreationFailed();
            return shareItemRequest;
        } catch (UnsupportedEncodingException e2) {
            asyncMultipartRequestCallback.onRequestCreationFailed();
            return shareItemRequest;
        }
        return shareItemRequest;
    }

    public static ShareItemRequest<EmptyResult> delete(EtsyId etsyId, EtsyId etsyId2) {
        APIv3Scope.SHOP.setIdentifier(etsyId.getId());
        ShareItemRequest<EmptyResult> shareItemRequest = new ShareItemRequest("/shares/" + etsyId2.getId(), RequestMethod.DELETE, EmptyResult.class);
        shareItemRequest.setV3Scope(APIv3Scope.SHOP);
        return shareItemRequest;
    }

    public static ShareItemRequest<ShareItem> update(EtsyId etsyId, ShareItem shareItem) {
        APIv3Scope.SHOP.setIdentifier(etsyId.getId());
        ShareItemRequest<ShareItem> shareItemRequest = new ShareItemRequest("/shares/" + shareItem.getShareId(), RequestMethod.PUT, ShareItem.class);
        try {
            ShareAnnotation primaryAnnotation = shareItem.getPrimaryAnnotation();
            HashMap hashMap = new HashMap();
            hashMap.put(ResponseConstants.OBJECT_ID, Long.valueOf(primaryAnnotation.getObjectId().getIdAsLong()));
            hashMap.put(ResponseConstants.OBJECT_TYPE, Integer.valueOf(primaryAnnotation.getObjectType()));
            hashMap.put(ResponseConstants.COORD_X, Integer.valueOf(primaryAnnotation.getCoordX()));
            hashMap.put(ResponseConstants.COORD_Y, Integer.valueOf(primaryAnnotation.getCoordY()));
            List arrayList = new ArrayList();
            arrayList.add(hashMap);
            shareItemRequest.setV3Scope(APIv3Scope.SHOP);
            shareItemRequest.setContentType(JSON_CONTENT_TYPE);
            Map hashMap2 = new HashMap();
            hashMap2.put(FindsModule.FIELD_TEXT, shareItem.getText());
            hashMap2.put(ResponseConstants.ANNOTATIONS, ad.m1081a().m1083b().writeValueAsString(arrayList));
            shareItemRequest.addBodyParams(hashMap2);
        } catch (Throwable e) {
            EtsyDebug.m1917d(TAG, "Problem parsing annotations to JSON", e);
        }
        return shareItemRequest;
    }
}
