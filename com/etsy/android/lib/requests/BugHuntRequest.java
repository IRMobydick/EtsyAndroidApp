package com.etsy.android.lib.requests;

import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.apiv3.bughunt.IssueResult;
import com.etsy.android.lib.requests.EtsyRequest.EndpointType;
import com.etsy.android.lib.requests.EtsyRequest.RequestMethod;

public class BugHuntRequest extends EtsyRequest<IssueResult> {
    private static final String TAG;
    private static final String URL = "/bughunt/report";

    static {
        TAG = EtsyDebug.m1891a(BugHuntRequest.class);
    }

    public BugHuntRequest() {
        super(URL, RequestMethod.POST, IssueResult.class, EndpointType.APIv3);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void createIssue(java.lang.String r7, java.lang.String r8, java.util.List<java.io.File> r9, com.etsy.android.lib.requests.EtsyMultipartEntity.AsyncMultipartRequestCallback<com.etsy.android.lib.models.apiv3.bughunt.IssueResult, com.etsy.android.lib.requests.BugHuntRequest> r10) {
        /*
        r6 = 0;
        r1 = new com.etsy.android.lib.requests.BugHuntRequest;
        r1.<init>();
        r0 = r7.length();
        r2 = 255; // 0xff float:3.57E-43 double:1.26E-321;
        if (r0 <= r2) goto L_0x0027;
    L_0x000e:
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r2 = 252; // 0xfc float:3.53E-43 double:1.245E-321;
        r2 = r7.substring(r6, r2);
        r0 = r0.append(r2);
        r2 = "...";
        r0 = r0.append(r2);
        r7 = r0.toString();
    L_0x0027:
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r2 = android.os.Build.DEVICE;
        r0 = r0.append(r2);
        r2 = " ";
        r0 = r0.append(r2);
        r2 = android.os.Build.MODEL;
        r0 = r0.append(r2);
        r0 = r0.toString();
        r2 = r0.length();
        r3 = 128; // 0x80 float:1.794E-43 double:6.32E-322;
        if (r2 <= r3) goto L_0x0050;
    L_0x004a:
        r2 = 127; // 0x7f float:1.78E-43 double:6.27E-322;
        r0 = r0.substring(r6, r2);
    L_0x0050:
        r2 = new com.etsy.android.lib.requests.EtsyMultipartEntity;
        r2.<init>();
        r3 = com.etsy.android.lib.core.aj.m1101a();	 Catch:{ UnsupportedEncodingException -> 0x00c4 }
        r3 = r3.m1118d();	 Catch:{ UnsupportedEncodingException -> 0x00c4 }
        if (r3 == 0) goto L_0x0075;
    L_0x005f:
        r3 = "user_id";
        r4 = new org.apache.http.entity.mime.content.StringBody;	 Catch:{ UnsupportedEncodingException -> 0x00c4 }
        r5 = com.etsy.android.lib.core.aj.m1101a();	 Catch:{ UnsupportedEncodingException -> 0x00c4 }
        r5 = r5.m1125k();	 Catch:{ UnsupportedEncodingException -> 0x00c4 }
        r5 = r5.getId();	 Catch:{ UnsupportedEncodingException -> 0x00c4 }
        r4.<init>(r5);	 Catch:{ UnsupportedEncodingException -> 0x00c4 }
        r2.addPart(r3, r4);	 Catch:{ UnsupportedEncodingException -> 0x00c4 }
    L_0x0075:
        r3 = "message";
        r4 = new org.apache.http.entity.mime.content.StringBody;	 Catch:{ UnsupportedEncodingException -> 0x00c4 }
        r4.<init>(r7);	 Catch:{ UnsupportedEncodingException -> 0x00c4 }
        r2.addPart(r3, r4);	 Catch:{ UnsupportedEncodingException -> 0x00c4 }
        r3 = "platform";
        r4 = new org.apache.http.entity.mime.content.StringBody;	 Catch:{ UnsupportedEncodingException -> 0x00c4 }
        r4.<init>(r8);	 Catch:{ UnsupportedEncodingException -> 0x00c4 }
        r2.addPart(r3, r4);	 Catch:{ UnsupportedEncodingException -> 0x00c4 }
        r3 = "platform_version";
        r4 = new org.apache.http.entity.mime.content.StringBody;	 Catch:{ UnsupportedEncodingException -> 0x00c4 }
        r5 = android.os.Build.VERSION.RELEASE;	 Catch:{ UnsupportedEncodingException -> 0x00c4 }
        r4.<init>(r5);	 Catch:{ UnsupportedEncodingException -> 0x00c4 }
        r2.addPart(r3, r4);	 Catch:{ UnsupportedEncodingException -> 0x00c4 }
        r3 = "device_type";
        r4 = new org.apache.http.entity.mime.content.StringBody;	 Catch:{ UnsupportedEncodingException -> 0x00c4 }
        r4.<init>(r0);	 Catch:{ UnsupportedEncodingException -> 0x00c4 }
        r2.addPart(r3, r4);	 Catch:{ UnsupportedEncodingException -> 0x00c4 }
        r0 = "etsy_version";
        r3 = new org.apache.http.entity.mime.content.StringBody;	 Catch:{ UnsupportedEncodingException -> 0x00c4 }
        r4 = com.etsy.android.lib.config.InstallInfo.m919a();	 Catch:{ UnsupportedEncodingException -> 0x00c4 }
        r4 = r4.m929f();	 Catch:{ UnsupportedEncodingException -> 0x00c4 }
        r3.<init>(r4);	 Catch:{ UnsupportedEncodingException -> 0x00c4 }
        r2.addPart(r0, r3);	 Catch:{ UnsupportedEncodingException -> 0x00c4 }
    L_0x00b1:
        if (r9 == 0) goto L_0x00c0;
    L_0x00b3:
        r0 = r9.size();
        if (r0 <= 0) goto L_0x00c0;
    L_0x00b9:
        r0 = r9.size();
        switch(r0) {
            case 1: goto L_0x00ef;
            case 2: goto L_0x00de;
            case 3: goto L_0x00cd;
            default: goto L_0x00c0;
        };
    L_0x00c0:
        r2.createMultipartAsync(r1, r10);
        return;
    L_0x00c4:
        r0 = move-exception;
        r0 = TAG;
        r3 = "Encoding Exception";
        com.etsy.android.lib.logger.EtsyDebug.m1919e(r0, r3);
        goto L_0x00b1;
    L_0x00cd:
        r3 = "image03";
        r4 = new org.apache.http.entity.mime.content.FileBody;
        r0 = 2;
        r0 = r9.get(r0);
        r0 = (java.io.File) r0;
        r4.<init>(r0);
        r2.addPart(r3, r4);
    L_0x00de:
        r3 = "image02";
        r4 = new org.apache.http.entity.mime.content.FileBody;
        r0 = 1;
        r0 = r9.get(r0);
        r0 = (java.io.File) r0;
        r4.<init>(r0);
        r2.addPart(r3, r4);
    L_0x00ef:
        r3 = "image01";
        r4 = new org.apache.http.entity.mime.content.FileBody;
        r0 = r9.get(r6);
        r0 = (java.io.File) r0;
        r4.<init>(r0);
        r2.addPart(r3, r4);
        goto L_0x00c0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.etsy.android.lib.requests.BugHuntRequest.createIssue(java.lang.String, java.lang.String, java.util.List, com.etsy.android.lib.requests.EtsyMultipartEntity$AsyncMultipartRequestCallback):void");
    }
}
