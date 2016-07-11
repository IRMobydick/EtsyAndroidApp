package com.etsy.android.lib.requests;

import com.adjust.sdk.Constants;
import com.android.volley.Request.Priority;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.requests.EtsyRequest.RequestMethod;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPOutputStream;

public class AnalyticsRequest<Result extends BaseModel> extends EtsyRequest<Result> {
    public static final String JSON_LOGS_KEY = "JsonLogs";
    private static final Priority REQUEST_PRIORITY;
    private static final long serialVersionUID = 2999587330599125267L;

    static {
        REQUEST_PRIORITY = Priority.LOW;
    }

    public AnalyticsRequest(String str, RequestMethod requestMethod, Class<Result> cls) {
        super(str, requestMethod, cls);
        setPriority(REQUEST_PRIORITY);
    }

    public static AnalyticsRequest uploadData(String str) {
        AnalyticsRequest analyticsRequest = new AnalyticsRequest("/analytics/uploadData/", RequestMethod.POST, null);
        Map hashMap = new HashMap();
        hashMap.put(JSON_LOGS_KEY, str);
        analyticsRequest.addParams(hashMap);
        return analyticsRequest;
    }

    public static AnalyticsRequest uploadDataCompressed(String str) {
        AnalyticsRequest analyticsRequest = new AnalyticsRequest("/analytics/uploadCompressedData/", RequestMethod.POST, null);
        try {
            String generateBoundaryString = generateBoundaryString();
            byte[] bytes = ("\r\n--" + generateBoundaryString + "--\r\n").getBytes(Constants.ENCODING);
            analyticsRequest.setContentType("multipart/form-data; boundary=" + generateBoundaryString);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("--").append(generateBoundaryString).append("\r\n").append("Content-Disposition: form-data; name=\"JsonLogs\"; filename=\"logs\"").append("\r\n").append("Content-Type: application/x-zip-compressed").append("\r\n\r\n");
            byteArrayOutputStream.write(stringBuilder.toString().getBytes(Constants.ENCODING));
            byteArrayOutputStream.write(compressLogs(str));
            byteArrayOutputStream.write(bytes);
            analyticsRequest.setPayload(byteArrayOutputStream.toByteArray());
            byteArrayOutputStream.close();
        } catch (Throwable e) {
            EtsyDebug.m1917d("ANALYTICS", "Error setting form data ", e);
        }
        return analyticsRequest;
    }

    static String generateBoundaryString() {
        return Long.toHexString(System.nanoTime());
    }

    public static AnalyticsRequest uploadDataCompressedBase64(String str) {
        AnalyticsRequest analyticsRequest = new AnalyticsRequest("/analytics/uploadCompressedDataBase64/", RequestMethod.POST, null);
        Map hashMap = new HashMap();
        hashMap.put(JSON_LOGS_KEY, compressLogsBase64(str));
        analyticsRequest.addParams(hashMap);
        return analyticsRequest;
    }

    private static byte[] compressLogs(String str) {
        GZIPOutputStream gZIPOutputStream = null;
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            Writer outputStreamWriter = new OutputStreamWriter(new GZIPOutputStream(byteArrayOutputStream), Constants.ENCODING);
            outputStreamWriter.write(str);
            outputStreamWriter.close();
            byteArrayOutputStream.flush();
            byte[] toByteArray = byteArrayOutputStream.toByteArray();
            if (null == null) {
                return toByteArray;
            }
            try {
                gZIPOutputStream.close();
                byteArrayOutputStream.close();
                return toByteArray;
            } catch (Throwable e) {
                EtsyDebug.m1917d("GZIP IO Exception", "GZIP IOException", e);
                return toByteArray;
            }
        } catch (Throwable e2) {
            EtsyDebug.m1917d("GZIP IO Exception", "GZIP IOException", e2);
            if (null != null) {
                try {
                    gZIPOutputStream.close();
                    byteArrayOutputStream.close();
                } catch (Throwable e22) {
                    EtsyDebug.m1917d("GZIP IO Exception", "GZIP IOException", e22);
                    return null;
                }
            }
            return null;
        } catch (Throwable th) {
            if (null != null) {
                try {
                    gZIPOutputStream.close();
                    byteArrayOutputStream.close();
                } catch (Throwable e3) {
                    EtsyDebug.m1917d("GZIP IO Exception", "GZIP IOException", e3);
                }
            }
        }
    }

    private static String compressLogsBase64(String str) {
        return str;
    }
}
