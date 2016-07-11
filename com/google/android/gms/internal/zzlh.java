package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.os.Message;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.GeolocationPermissions.Callback;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.webkit.WebStorage.QuotaUpdater;
import android.webkit.WebView;
import android.webkit.WebView.WebViewTransport;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.etsy.android.lib.convos.Draft;
import com.google.android.gms.ads.internal.C1101o;
import com.google.android.gms.ads.internal.overlay.zzd;
import com.google.android.gms.ads.internal.util.client.C1129c;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;

@TargetApi(11)
@jw
public class zzlh extends WebChromeClient {
    private final no zzBb;

    public zzlh(no noVar) {
        this.zzBb = noVar;
    }

    private final Context zza(WebView webView) {
        if (!(webView instanceof no)) {
            return webView.getContext();
        }
        no noVar = (no) webView;
        Context f = noVar.m7256f();
        return f == null ? noVar.getContext() : f;
    }

    private static void zza(Builder builder, String str, JsResult jsResult) {
        builder.setMessage(str).setPositiveButton(17039370, new 3(jsResult)).setNegativeButton(17039360, new 2(jsResult)).setOnCancelListener(new 1(jsResult)).create().show();
    }

    private static void zza(Context context, Builder builder, String str, String str2, JsPromptResult jsPromptResult) {
        View linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(1);
        View textView = new TextView(context);
        textView.setText(str);
        View editText = new EditText(context);
        editText.setText(str2);
        linearLayout.addView(textView);
        linearLayout.addView(editText);
        builder.setView(linearLayout).setPositiveButton(17039370, new 6(jsPromptResult, editText)).setNegativeButton(17039360, new 5(jsPromptResult)).setOnCancelListener(new 4(jsPromptResult)).create().show();
    }

    private final boolean zzkh() {
        return C1101o.m6041e().m7113a(this.zzBb.getContext().getPackageManager(), this.zzBb.getContext().getPackageName(), "android.permission.ACCESS_FINE_LOCATION") || C1101o.m6041e().m7113a(this.zzBb.getContext().getPackageManager(), this.zzBb.getContext().getPackageName(), "android.permission.ACCESS_COARSE_LOCATION");
    }

    public final void onCloseWindow(WebView webView) {
        if (webView instanceof no) {
            zzd i = ((no) webView).m7259i();
            if (i == null) {
                C1129c.m6192d("Tried to close an AdWebView not associated with an overlay.");
                return;
            } else {
                i.close();
                return;
            }
        }
        C1129c.m6192d("Tried to close a WebView that wasn't an AdWebView.");
    }

    public final boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        String valueOf = String.valueOf(consoleMessage.message());
        String valueOf2 = String.valueOf(consoleMessage.sourceId());
        valueOf = new StringBuilder((String.valueOf(valueOf).length() + 19) + String.valueOf(valueOf2).length()).append("JS: ").append(valueOf).append(" (").append(valueOf2).append(Draft.IMAGE_DELIMITER).append(consoleMessage.lineNumber()).append(")").toString();
        if (valueOf.contains("Application Cache")) {
            return super.onConsoleMessage(consoleMessage);
        }
        switch (7.a[consoleMessage.messageLevel().ordinal()]) {
            case Task.NETWORK_STATE_UNMETERED /*1*/:
                C1129c.m6188b(valueOf);
                break;
            case Task.NETWORK_STATE_ANY /*2*/:
                C1129c.m6192d(valueOf);
                break;
            case CommonStatusCodes.SERVICE_DISABLED /*3*/:
            case CommonStatusCodes.SIGN_IN_REQUIRED /*4*/:
                C1129c.m6190c(valueOf);
                break;
            case CommonStatusCodes.INVALID_ACCOUNT /*5*/:
                C1129c.m6185a(valueOf);
                break;
            default:
                C1129c.m6190c(valueOf);
                break;
        }
        return super.onConsoleMessage(consoleMessage);
    }

    public final boolean onCreateWindow(WebView webView, boolean z, boolean z2, Message message) {
        WebViewTransport webViewTransport = (WebViewTransport) message.obj;
        WebView webView2 = new WebView(webView.getContext());
        webView2.setWebViewClient(this.zzBb.m7262l());
        webViewTransport.setWebView(webView2);
        message.sendToTarget();
        return true;
    }

    public final void onExceededDatabaseQuota(String str, String str2, long j, long j2, long j3, QuotaUpdater quotaUpdater) {
        long j4 = 5242880 - j3;
        if (j4 <= 0) {
            quotaUpdater.updateQuota(j);
            return;
        }
        if (j == 0) {
            if (j2 > j4 || j2 > 1048576) {
                j2 = 0;
            }
        } else if (j2 == 0) {
            j2 = Math.min(Math.min(131072, j4) + j, 1048576);
        } else {
            if (j2 <= Math.min(1048576 - j, j4)) {
                j += j2;
            }
            j2 = j;
        }
        quotaUpdater.updateQuota(j2);
    }

    public final void onGeolocationPermissionsShowPrompt(String str, Callback callback) {
        if (callback != null) {
            callback.invoke(str, zzkh(), true);
        }
    }

    public final void onHideCustomView() {
        zzd i = this.zzBb.m7259i();
        if (i == null) {
            C1129c.m6192d("Could not get ad overlay when hiding custom view.");
        } else {
            i.zzgo();
        }
    }

    public final boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
        return zza(zza(webView), str, str2, null, jsResult, null, false);
    }

    public final boolean onJsBeforeUnload(WebView webView, String str, String str2, JsResult jsResult) {
        return zza(zza(webView), str, str2, null, jsResult, null, false);
    }

    public final boolean onJsConfirm(WebView webView, String str, String str2, JsResult jsResult) {
        return zza(zza(webView), str, str2, null, jsResult, null, false);
    }

    public final boolean onJsPrompt(WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult) {
        return zza(zza(webView), str, str2, str3, null, jsPromptResult, true);
    }

    public final void onReachedMaxAppCacheSize(long j, long j2, QuotaUpdater quotaUpdater) {
        long j3 = 131072 + j;
        if (5242880 - j2 < j3) {
            quotaUpdater.updateQuota(0);
        } else {
            quotaUpdater.updateQuota(j3);
        }
    }

    public final void onShowCustomView(View view, CustomViewCallback customViewCallback) {
        zza(view, -1, customViewCallback);
    }

    protected final void zza(View view, int i, CustomViewCallback customViewCallback) {
        zzd i2 = this.zzBb.m7259i();
        if (i2 == null) {
            C1129c.m6192d("Could not get ad overlay when showing custom view.");
            customViewCallback.onCustomViewHidden();
            return;
        }
        i2.zza(view, customViewCallback);
        i2.setRequestedOrientation(i);
    }

    protected boolean zza(Context context, String str, String str2, String str3, JsResult jsResult, JsPromptResult jsPromptResult, boolean z) {
        try {
            Builder builder = new Builder(context);
            builder.setTitle(str);
            if (z) {
                zza(context, builder, str2, str3, jsPromptResult);
            } else {
                zza(builder, str2, jsResult);
            }
        } catch (Throwable e) {
            C1129c.m6193d("Fail to display Dialog.", e);
        }
        return true;
    }
}
