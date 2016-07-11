package com.etsy.android.lib.core;

import org.apache.commons.lang3.StringUtils;

/* compiled from: PermissionScope */
public class ag {
    private String f1418a;

    public ag(String[] strArr) {
        this.f1418a = StringUtils.EMPTY;
        for (int i = 0; i < strArr.length; i++) {
            this.f1418a += strArr[i];
            if (i < strArr.length - 1) {
                this.f1418a += "+";
            }
        }
    }

    public String m1090a() {
        return this.f1418a;
    }
}
