package com.etsy.android.lib.requests;

import android.support.annotation.NonNull;
import android.webkit.MimeTypeMap;
import com.etsy.android.lib.models.EmptyResult;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.requests.EtsyMultipartEntity.AsyncMultipartRequestCallback;
import com.etsy.android.lib.requests.EtsyRequest.RequestMethod;
import java.io.File;
import org.apache.http.entity.mime.content.FileBody;

public class UploadUserAvatarRequest extends EtsyRequest<EmptyResult> {
    public UploadUserAvatarRequest() {
        super("/users/__SELF__/avatar", RequestMethod.POST, EmptyResult.class);
    }

    public static void create(@NonNull File file, AsyncMultipartRequestCallback asyncMultipartRequestCallback) {
        EtsyRequest uploadUserAvatarRequest = new UploadUserAvatarRequest();
        EtsyMultipartEntity etsyMultipartEntity = new EtsyMultipartEntity();
        String str = "jpeg";
        String fileExtensionFromUrl = MimeTypeMap.getFileExtensionFromUrl(file.getPath());
        if (fileExtensionFromUrl != null) {
            str = MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtensionFromUrl);
        }
        etsyMultipartEntity.addPart(ResponseConstants.IMAGE, new FileBody(file, str));
        etsyMultipartEntity.createMultipartAsync(uploadUserAvatarRequest, asyncMultipartRequestCallback);
    }
}
