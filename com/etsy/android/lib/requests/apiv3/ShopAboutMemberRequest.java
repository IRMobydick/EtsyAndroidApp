package com.etsy.android.lib.requests.apiv3;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.ShopAboutMember;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.requests.EtsyMultipartEntity;
import com.etsy.android.lib.requests.EtsyMultipartEntity.AsyncMultipartRequestCallback;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.requests.EtsyRequest.APIv3Scope;
import com.etsy.android.lib.requests.EtsyRequest.EndpointType;
import com.etsy.android.lib.requests.EtsyRequest.RequestMethod;
import java.io.File;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;

public class ShopAboutMemberRequest extends EtsyRequest<ShopAboutMember> {
    private static final String TAG;

    static {
        TAG = EtsyDebug.m1891a(ShopAboutMemberRequest.class);
    }

    public ShopAboutMemberRequest(String str, RequestMethod requestMethod, EndpointType endpointType) {
        super(str, requestMethod, ShopAboutMember.class, endpointType);
    }

    public static void create(@NonNull EtsyId etsyId, @Nullable File file, @NonNull String str, @NonNull List<String> list, String str2, boolean z, AsyncMultipartRequestCallback asyncMultipartRequestCallback) {
        APIv3Scope.SHOP.setIdentifier(etsyId.getId());
        EtsyRequest shopAboutMemberRequest = new ShopAboutMemberRequest("/members/create", RequestMethod.POST, EndpointType.APIv3);
        shopAboutMemberRequest.setV3Scope(APIv3Scope.SHOP);
        shopAboutMemberRequest.setV3Bespoke(true);
        if (str2 == null) {
            str2 = StringUtils.EMPTY;
        }
        EtsyMultipartEntity etsyMultipartEntity = new EtsyMultipartEntity();
        if (file != null) {
            String str3 = "jpeg";
            String fileExtensionFromUrl = MimeTypeMap.getFileExtensionFromUrl(file.getPath());
            if (fileExtensionFromUrl != null) {
                str3 = MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtensionFromUrl);
            }
            etsyMultipartEntity.addPart(ResponseConstants.FILE, new FileBody(file, str3));
        }
        try {
            etsyMultipartEntity.addPart(ResponseConstants.NAME, new StringBody(str, EtsyMultipartEntity.UTF_8));
            etsyMultipartEntity.addPart(ResponseConstants.ROLE, new StringBody(TextUtils.join(",", list), EtsyMultipartEntity.UTF_8));
            etsyMultipartEntity.addPart(ResponseConstants.BIO, new StringBody(str2, EtsyMultipartEntity.UTF_8));
            etsyMultipartEntity.addPart(ResponseConstants.IS_OWNER, new StringBody(String.valueOf(z), EtsyMultipartEntity.UTF_8));
            etsyMultipartEntity.createMultipartAsync(shopAboutMemberRequest, asyncMultipartRequestCallback);
        } catch (Throwable e) {
            EtsyDebug.m1917d(TAG, "Failed creating shop member image request", e);
            asyncMultipartRequestCallback.onRequestCreationFailed();
        }
    }

    public static ShopAboutMemberRequest update(@NonNull EtsyId etsyId, @NonNull EtsyId etsyId2, @NonNull String str, @NonNull List<String> list, @Nullable String str2, boolean z, @NonNull AsyncMultipartRequestCallback asyncMultipartRequestCallback, @Nullable File file) {
        APIv3Scope.SHOP.setIdentifier(etsyId.getId());
        EtsyRequest shopAboutMemberRequest = new ShopAboutMemberRequest("/about/members/" + etsyId2, RequestMethod.POST, EndpointType.APIv3);
        shopAboutMemberRequest.setV3Scope(APIv3Scope.SHOP);
        if (str2 == null) {
            str2 = StringUtils.EMPTY;
        }
        EtsyMultipartEntity etsyMultipartEntity = new EtsyMultipartEntity();
        if (file != null) {
            try {
                String str3 = "jpeg";
                String fileExtensionFromUrl = MimeTypeMap.getFileExtensionFromUrl(file.getPath());
                if (fileExtensionFromUrl != null) {
                    str3 = MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtensionFromUrl);
                }
                etsyMultipartEntity.addPart(ResponseConstants.FILE, new FileBody(file, str3));
            } catch (Throwable e) {
                EtsyDebug.m1917d(TAG, "Failed creating shop member image request", e);
                asyncMultipartRequestCallback.onRequestCreationFailed();
            }
        }
        etsyMultipartEntity.addPart(ResponseConstants.NAME, new StringBody(str, EtsyMultipartEntity.UTF_8));
        etsyMultipartEntity.addPart(ResponseConstants.ROLE, new StringBody(TextUtils.join(",", list), EtsyMultipartEntity.UTF_8));
        etsyMultipartEntity.addPart(ResponseConstants.BIO, new StringBody(str2, EtsyMultipartEntity.UTF_8));
        etsyMultipartEntity.addPart(ResponseConstants.IS_OWNER, new StringBody(String.valueOf(z), EtsyMultipartEntity.UTF_8));
        etsyMultipartEntity.addPart(ResponseConstants.HAS_PHOTO, new StringBody(Boolean.TRUE.toString(), EtsyMultipartEntity.UTF_8));
        etsyMultipartEntity.createMultipartAsync(shopAboutMemberRequest, asyncMultipartRequestCallback);
        return shopAboutMemberRequest;
    }

    public static ShopAboutMemberRequest delete(@NonNull EtsyId etsyId, @NonNull EtsyId etsyId2) {
        APIv3Scope.SHOP.setIdentifier(etsyId.getId());
        ShopAboutMemberRequest shopAboutMemberRequest = new ShopAboutMemberRequest("/about/members/" + etsyId2.getId(), RequestMethod.DELETE, EndpointType.APIv3);
        shopAboutMemberRequest.setV3Scope(APIv3Scope.SHOP);
        return shopAboutMemberRequest;
    }
}
