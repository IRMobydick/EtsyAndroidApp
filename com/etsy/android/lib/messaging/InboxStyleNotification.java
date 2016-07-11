package com.etsy.android.lib.messaging;

import org.apache.commons.lang3.StringUtils;

/* renamed from: com.etsy.android.lib.messaging.e */
public class InboxStyleNotification {
    protected String f1895a;
    protected String f1896b;
    protected CharSequence f1897c;
    protected String f1898d;
    protected int f1899e;

    public InboxStyleNotification(String str, String str2, CharSequence charSequence, String str3) {
        this.f1896b = StringUtils.EMPTY;
        this.f1897c = StringUtils.EMPTY;
        m2287a(str2);
        this.f1895a = str;
        m2286a(charSequence);
        this.f1898d = str3;
    }

    public String m2284a() {
        return this.f1896b;
    }

    public CharSequence m2288b() {
        return this.f1897c;
    }

    public String m2290c() {
        return this.f1898d;
    }

    public void m2286a(CharSequence charSequence) {
        if (charSequence != null) {
            this.f1897c = charSequence;
        } else {
            this.f1897c = StringUtils.EMPTY;
        }
    }

    public void m2287a(String str) {
        if (str != null) {
            this.f1896b = str;
        } else {
            this.f1896b = StringUtils.EMPTY;
        }
    }

    public void m2289b(String str) {
        this.f1898d = str;
    }

    public int m2291d() {
        return this.f1899e;
    }

    public void m2285a(int i) {
        this.f1899e = i;
    }
}
