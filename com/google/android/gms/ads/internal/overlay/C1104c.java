package com.google.android.gms.ads.internal.overlay;

import android.content.Context;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import com.google.android.gms.internal.jw;
import com.google.android.gms.internal.no;

@jw
/* renamed from: com.google.android.gms.ads.internal.overlay.c */
public class C1104c {
    public final int f4549a;
    public final LayoutParams f4550b;
    public final ViewGroup f4551c;
    public final Context f4552d;

    public C1104c(no noVar) {
        this.f4550b = noVar.getLayoutParams();
        ViewParent parent = noVar.getParent();
        this.f4552d = noVar.m7257g();
        if (parent == null || !(parent instanceof ViewGroup)) {
            throw new zza("Could not get the parent of the WebView for an overlay.");
        }
        this.f4551c = (ViewGroup) parent;
        this.f4549a = this.f4551c.indexOfChild(noVar.m7247b());
        this.f4551c.removeView(noVar.m7247b());
        noVar.m7246a(true);
    }
}
