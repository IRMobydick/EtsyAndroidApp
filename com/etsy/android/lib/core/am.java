package com.etsy.android.lib.core;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import org.apache.commons.lang3.StringUtils;
import org.scribe.model.Token;

/* compiled from: SharedPreferencesStorage */
public class am extends BaseTokenStorage {
    private SharedPreferences f1445a;

    public am(Context context) {
        this.f1445a = context.getSharedPreferences("EtsyPrefs", 4);
    }

    public Token m1138a(String str) {
        String string = this.f1445a.getString(str + "etsyAccessToken", StringUtils.EMPTY);
        String string2 = this.f1445a.getString(str + "etsyAccessSecret", StringUtils.EMPTY);
        if (string.equals(StringUtils.EMPTY) || string2.equals(StringUtils.EMPTY)) {
            return null;
        }
        return new Token(string, string2);
    }

    public void m1139a(String str, Token token) {
        Editor edit = this.f1445a.edit();
        edit.putString(str + "etsyAccessToken", token.getToken());
        edit.putString(str + "etsyAccessSecret", token.getSecret());
        edit.commit();
    }

    public boolean m1140b(String str) {
        boolean z = false;
        if (this.f1445a.contains(str + "etsyAccessToken") && this.f1445a.contains(str + "etsyAccessToken")) {
            z = true;
        }
        Editor edit = this.f1445a.edit();
        edit.remove(str + "etsyAccessToken");
        edit.remove(str + "etsyAccessToken");
        edit.commit();
        return z;
    }
}
