package com.foresee.sdk.configuration;

import com.google.gson.p020a.SerializedName;

public class MaskingConfig {
    @SerializedName(a = "webview_masking")
    private WebViewMaskingConfig webViewMaskingConfig;

    public WebViewMaskingConfig getWebViewMaskingConfig() {
        return this.webViewMaskingConfig;
    }

    public void setWebViewMaskingConfig(WebViewMaskingConfig webViewMaskingConfig) {
        this.webViewMaskingConfig = webViewMaskingConfig;
    }
}
