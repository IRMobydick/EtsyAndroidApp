package com.etsy.android.lib.models.enums;

import com.etsy.android.lib.util.bh;

public enum WhoMade {
    Owner("i_did"),
    Employee("collective"),
    SomeoneElse("someone_else");
    
    private String mJsonParam;

    private WhoMade(String str) {
        this.mJsonParam = str;
    }

    public String toString() {
        return this.mJsonParam;
    }

    public static WhoMade getEnumForJson(String str) {
        if (!bh.m3340a(str)) {
            return null;
        }
        String trim = str.trim();
        if (Owner.mJsonParam.equalsIgnoreCase(trim)) {
            return Owner;
        }
        if (Employee.mJsonParam.equalsIgnoreCase(trim)) {
            return Employee;
        }
        if (SomeoneElse.mJsonParam.equalsIgnoreCase(trim)) {
            return SomeoneElse;
        }
        return null;
    }
}
