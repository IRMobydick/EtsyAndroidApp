package com.etsy.android.lib.requests.apiv3;

import android.support.annotation.NonNull;
import android.webkit.MimeTypeMap;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.ShopAboutImage;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.requests.EtsyMultipartEntity;
import com.etsy.android.lib.requests.EtsyMultipartEntity.AsyncMultipartRequestCallback;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.requests.EtsyRequest.APIv3Scope;
import com.etsy.android.lib.requests.EtsyRequest.EndpointType;
import com.etsy.android.lib.requests.EtsyRequest.RequestMethod;
import java.io.File;
import java.io.UnsupportedEncodingException;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;

public class ShopAboutImageRequest extends EtsyRequest<ShopAboutImage> {
    public ShopAboutImageRequest(String str, RequestMethod requestMethod) {
        super(str, requestMethod, ShopAboutImage.class, EndpointType.APIv3);
    }

    public static ShopAboutImageRequest create(EtsyId etsyId, @NonNull File file, String str, AsyncMultipartRequestCallback asyncMultipartRequestCallback) {
        APIv3Scope.SHOP.setIdentifier(etsyId.getId());
        EtsyRequest shopAboutImageRequest = new ShopAboutImageRequest("/images/about", RequestMethod.POST);
        shopAboutImageRequest.setV3Scope(APIv3Scope.SHOP);
        shopAboutImageRequest.setV3Bespoke(true);
        try {
            EtsyMultipartEntity etsyMultipartEntity = new EtsyMultipartEntity();
            etsyMultipartEntity.addPart(ResponseConstants.CAPTION, new StringBody(str, EtsyMultipartEntity.UTF_8));
            String str2 = "jpeg";
            String fileExtensionFromUrl = MimeTypeMap.getFileExtensionFromUrl(file.getPath());
            if (fileExtensionFromUrl != null) {
                str2 = MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtensionFromUrl);
            }
            etsyMultipartEntity.addPart(ResponseConstants.FILE, new FileBody(file, str2));
            etsyMultipartEntity.createMultipartAsync(shopAboutImageRequest, asyncMultipartRequestCallback);
        } catch (UnsupportedEncodingException e) {
            asyncMultipartRequestCallback.onRequestCreationFailed();
        }
        return shopAboutImageRequest;
    }

    public static ShopAboutImageRequest update(EtsyId etsyId, EtsyId etsyId2) {
        APIv3Scope.SHOP.setIdentifier(etsyId.getId());
        ShopAboutImageRequest shopAboutImageRequest = new ShopAboutImageRequest("/images/about/" + etsyId2.getId(), RequestMethod.PUT);
        shopAboutImageRequest.setV3Scope(APIv3Scope.SHOP);
        return shopAboutImageRequest;
    }

    public static ShopAboutImageRequest delete(EtsyId etsyId, EtsyId etsyId2) {
        APIv3Scope.SHOP.setIdentifier(etsyId.getId());
        ShopAboutImageRequest shopAboutImageRequest = new ShopAboutImageRequest("/images/about/" + etsyId2.getId(), RequestMethod.DELETE);
        shopAboutImageRequest.setV3Scope(APIv3Scope.SHOP);
        return shopAboutImageRequest;
    }
}
