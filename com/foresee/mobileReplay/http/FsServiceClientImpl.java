package com.foresee.mobileReplay.http;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import android.util.Log;
import com.etsy.android.lib.models.ResponseConstants;
import com.foresee.mobileReplay.data.BandwidthMonitor;
import com.foresee.mobileReplay.util.FsProperties;
import com.foresee.mobileReplay.util.LogUtil;
import com.google.inject.Inject;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;

public class FsServiceClientImpl implements FsServiceClient {
    public static final String CONTENT_TYPE = "application/json";
    @Inject
    BandwidthMonitor bandwidthMonitor;
    private ConnectivityManager connectivityManager;

    public void setConnectivityManager(ConnectivityManager connectivityManager) {
        this.connectivityManager = connectivityManager;
    }

    public void setBandwidthMonitor(BandwidthMonitor bandwidthMonitor) {
        this.bandwidthMonitor = bandwidthMonitor;
    }

    public Void submitCapture(String str, String str2, File file, Callback callback, String str3) {
        NetworkInfo activeNetworkInfo = this.connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null || !((activeNetworkInfo.getType() == 1 || activeNetworkInfo.getType() == 0) && activeNetworkInfo.isConnected())) {
            callback.onFailure(true);
        } else {
            String.format("%s_%s.zip", new Object[]{str.toString(), str2});
            String urlBase = FsProperties.instance().getUrlBase();
            HttpClient defaultHttpClient = new DefaultHttpClient();
            try {
                StringBody stringBody = new StringBody(str3);
                LogUtil.d("Http request to %s", new Object[]{String.format("%s/mobile/data/%s/%s", new Object[]{urlBase, str, str2})});
                HttpUriRequest httpPost = new HttpPost(urlBase);
                MultipartEntity multipartEntity = new MultipartEntity();
                multipartEntity.addPart(ResponseConstants.FILE, new FileBody(file, "application/vnd.foresee.replay-v1+zip"));
                multipartEntity.addPart("customerId", stringBody);
                httpPost.setEntity(multipartEntity);
                try {
                    handleResponse(defaultHttpClient.execute(httpPost), callback);
                } catch (Throwable e) {
                    if (e.getClass().equals(HttpHostConnectException.class)) {
                        callback.onFailure(true);
                    } else {
                        Log.e("FORESEE_CAPTURE", e.getMessage(), e);
                        callback.onFailure(false);
                    }
                }
            } catch (UnsupportedEncodingException e2) {
                Log.e("FORESEE_CAPTURE", "Data submission failed: Error encoding customer ID to multipart body.");
                callback.onFailure(false);
            }
        }
        return null;
    }

    public Void submitEvents(String str, String str2, String str3, Callback callback) {
        NetworkInfo activeNetworkInfo = this.connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null || !((activeNetworkInfo.getType() == 1 || activeNetworkInfo.getType() == 0) && activeNetworkInfo.isConnected())) {
            callback.onFailure(true);
        } else {
            new HttpPostClient().executeJson(String.format("%s/mobile/event/%s/%s", new Object[]{FsProperties.instance().getUrlBase(), str, str2}), str3, callback, CONTENT_TYPE, false);
        }
        return null;
    }

    private void handleResponse(HttpResponse httpResponse, Callback callback) {
        switch (httpResponse.getStatusLine().getStatusCode()) {
            case Callback.DEFAULT_DRAG_ANIMATION_DURATION /*200*/:
            case 201:
            case 202:
            case 204:
                callback.onSuccess();
            default:
                try {
                    httpResponse.getEntity().writeTo(new ByteArrayOutputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.e("FORESEE_CAPTURE", String.format("Service call returned code: %d\n%s", new Object[]{Integer.valueOf(r1), r2.toString()}));
                callback.onFailure(false);
        }
    }
}
