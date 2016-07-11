package com.etsy.android.lib.requests.apiv3;

import android.support.annotation.NonNull;
import android.webkit.MimeTypeMap;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.ShopIconV3;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.requests.EtsyMultipartEntity;
import com.etsy.android.lib.requests.EtsyMultipartEntity.AsyncMultipartRequestCallback;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.requests.EtsyRequest.APIv3Scope;
import com.etsy.android.lib.requests.EtsyRequest.EndpointType;
import com.etsy.android.lib.requests.EtsyRequest.RequestMethod;
import java.io.File;
import org.apache.http.entity.mime.content.FileBody;

public class ShopIconRequest extends EtsyRequest<ShopIconV3> {
    public ShopIconRequest(String str, RequestMethod requestMethod) {
        super(str, requestMethod, ShopIconV3.class, EndpointType.APIv3);
    }

    public static void create(EtsyId etsyId, @NonNull File file, AsyncMultipartRequestCallback asyncMultipartRequestCallback) {
        APIv3Scope.SHOP.setIdentifier(etsyId.getId());
        EtsyRequest shopIconRequest = new ShopIconRequest("/images/icon", RequestMethod.POST);
        shopIconRequest.setV3Scope(APIv3Scope.SHOP);
        shopIconRequest.setV3Bespoke(true);
        EtsyMultipartEntity etsyMultipartEntity = new EtsyMultipartEntity();
        String str = "jpeg";
        String fileExtensionFromUrl = MimeTypeMap.getFileExtensionFromUrl(file.getPath());
        if (fileExtensionFromUrl != null) {
            str = MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtensionFromUrl);
        }
        etsyMultipartEntity.addPart(ResponseConstants.FILE, new FileBody(file, str));
        etsyMultipartEntity.createMultipartAsync(shopIconRequest, asyncMultipartRequestCallback);
    }
}
