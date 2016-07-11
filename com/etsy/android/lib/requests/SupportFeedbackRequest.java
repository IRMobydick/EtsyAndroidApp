package com.etsy.android.lib.requests;

import com.etsy.android.lib.config.InstallInfo;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.AppBuild;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.EmptyResult;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.requests.EtsyMultipartEntity.AsyncMultipartRequestCallback;
import com.etsy.android.lib.requests.EtsyRequest.RequestMethod;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;

public class SupportFeedbackRequest<Result extends BaseModel> extends EtsyRequest<Result> {
    private static final String TAG;
    private static final long serialVersionUID = 884692901503678371L;

    static {
        TAG = EtsyDebug.m1891a(SupportFeedbackRequest.class);
    }

    public SupportFeedbackRequest(String str, RequestMethod requestMethod, Class<Result> cls) {
        super(str, requestMethod, cls);
    }

    public static void createNewSupportFeedback(String str, String str2, String str3, List<File> list, AsyncMultipartRequestCallback asyncMultipartRequestCallback) {
        EtsyRequest supportFeedbackRequest = new SupportFeedbackRequest("/support/feedback", RequestMethod.POST, EmptyResult.class);
        if (list == null || list.size() <= 0) {
            Map hashMap = new HashMap();
            hashMap.put(ResponseConstants.PLATFORM, AppBuild.ANDROID_PLATFORM);
            hashMap.put("version", InstallInfo.m919a().m929f());
            hashMap.put("user_message", str);
            hashMap.put("system_message", str2);
            hashMap.put("user_email", str3);
            hashMap.put("app_name", InstallInfo.m919a().m933j());
            supportFeedbackRequest.addParams(hashMap);
            asyncMultipartRequestCallback.onRequestCreated(supportFeedbackRequest);
            return;
        }
        try {
            EtsyMultipartEntity etsyMultipartEntity = new EtsyMultipartEntity();
            etsyMultipartEntity.addPart(ResponseConstants.PLATFORM, new StringBody(AppBuild.ANDROID_PLATFORM));
            etsyMultipartEntity.addPart("version", new StringBody(InstallInfo.m919a().m929f(), EtsyMultipartEntity.UTF_8));
            etsyMultipartEntity.addPart("user_message", new StringBody(str, EtsyMultipartEntity.UTF_8));
            etsyMultipartEntity.addPart("system_message", new StringBody(str2, EtsyMultipartEntity.UTF_8));
            etsyMultipartEntity.addPart("user_email", new StringBody(str3, EtsyMultipartEntity.UTF_8));
            etsyMultipartEntity.addPart("app_name", new StringBody(InstallInfo.m919a().m933j(), EtsyMultipartEntity.UTF_8));
            for (int i = 0; i < list.size(); i++) {
                etsyMultipartEntity.addPart("attachments[]", new FileBody((File) list.get(i)));
            }
            etsyMultipartEntity.createMultipartAsync(supportFeedbackRequest, asyncMultipartRequestCallback);
        } catch (Throwable e) {
            EtsyDebug.m1917d(TAG, "Failed to encode multipart for support feedback", e);
            asyncMultipartRequestCallback.onRequestCreationFailed();
        }
    }
}
