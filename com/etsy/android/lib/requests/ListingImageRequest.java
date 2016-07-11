package com.etsy.android.lib.requests;

import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ListingImage;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.requests.EtsyMultipartEntity.AsyncMultipartRequestCallback;
import com.etsy.android.lib.requests.EtsyRequest.RequestMethod;
import java.io.File;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;

public class ListingImageRequest<Result extends BaseModel> extends EtsyRequest<Result> {
    private static final String TAG;
    private static final long serialVersionUID = 7908092519826473604L;

    static {
        TAG = EtsyDebug.m1891a(ListingImageRequest.class);
    }

    public ListingImageRequest(String str, RequestMethod requestMethod, Class<Result> cls) {
        super(str, requestMethod, cls);
    }

    public static void uploadListingImage(EtsyId etsyId, File file, AsyncMultipartRequestCallback asyncMultipartRequestCallback) {
        EtsyRequest listingImageRequest = new ListingImageRequest("/listings/images", RequestMethod.POST, ListingImage.class);
        try {
            EtsyMultipartEntity etsyMultipartEntity = new EtsyMultipartEntity();
            etsyMultipartEntity.addPart(ResponseConstants.SHOP_ID, new StringBody(etsyId.toString()));
            etsyMultipartEntity.addPart(ResponseConstants.IMAGE, new FileBody(file, "jpeg"));
            etsyMultipartEntity.createMultipartAsync(listingImageRequest, asyncMultipartRequestCallback);
        } catch (Throwable e) {
            EtsyDebug.m1917d(TAG, "Failed to encode multipart for listing image", e);
            asyncMultipartRequestCallback.onRequestCreationFailed();
        }
    }
}
