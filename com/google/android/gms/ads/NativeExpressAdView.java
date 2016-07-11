package com.google.android.gms.ads;

import android.content.Context;
import android.support.annotation.RequiresPermission;
import android.util.AttributeSet;
import com.google.android.gms.ads.purchase.b;
import com.google.android.gms.ads.purchase.d;
import com.google.android.gms.internal.jw;

@jw
public final class NativeExpressAdView extends g {
    public NativeExpressAdView(Context context) {
        super(context, 2);
    }

    public NativeExpressAdView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 2);
    }

    public NativeExpressAdView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i, 2);
    }

    public /* bridge */ /* synthetic */ void destroy() {
        super.destroy();
    }

    public /* bridge */ /* synthetic */ a getAdListener() {
        return super.getAdListener();
    }

    public /* bridge */ /* synthetic */ f getAdSize() {
        return super.getAdSize();
    }

    public /* bridge */ /* synthetic */ String getAdUnitId() {
        return super.getAdUnitId();
    }

    public /* bridge */ /* synthetic */ b getInAppPurchaseListener() {
        return super.getInAppPurchaseListener();
    }

    public /* bridge */ /* synthetic */ String getMediationAdapterClassName() {
        return super.getMediationAdapterClassName();
    }

    public C1131j getVideoController() {
        return this.zzpl.m5905n();
    }

    public C1132l getVideoOptions() {
        return this.zzpl.m5907p();
    }

    public /* bridge */ /* synthetic */ boolean isLoading() {
        return super.isLoading();
    }

    @RequiresPermission("android.permission.INTERNET")
    public /* bridge */ /* synthetic */ void loadAd(d dVar) {
        super.loadAd(dVar);
    }

    public /* bridge */ /* synthetic */ void pause() {
        super.pause();
    }

    public /* bridge */ /* synthetic */ void resume() {
        super.resume();
    }

    public /* bridge */ /* synthetic */ void setAdListener(a aVar) {
        super.setAdListener(aVar);
    }

    public /* bridge */ /* synthetic */ void setAdSize(f fVar) {
        super.setAdSize(fVar);
    }

    public /* bridge */ /* synthetic */ void setAdUnitId(String str) {
        super.setAdUnitId(str);
    }

    public /* bridge */ /* synthetic */ void setInAppPurchaseListener(b bVar) {
        super.setInAppPurchaseListener(bVar);
    }

    public /* bridge */ /* synthetic */ void setPlayStorePurchaseParams(d dVar, String str) {
        super.setPlayStorePurchaseParams(dVar, str);
    }

    public void setVideoOptions(C1132l c1132l) {
        this.zzpl.m5885a(c1132l);
    }
}
