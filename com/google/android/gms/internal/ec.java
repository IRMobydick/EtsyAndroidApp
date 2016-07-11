package com.google.android.gms.internal;

import android.text.TextUtils;

@jw
public class ec {
    public eb m6460a(ea eaVar) {
        if (eaVar == null) {
            throw new IllegalArgumentException("CSI configuration can't be null. ");
        } else if (!eaVar.m6446a()) {
            lo.m7056e("CsiReporterFactory: CSI is not enabled. No CSI reporter created.");
            return null;
        } else if (eaVar.m6448c() == null) {
            throw new IllegalArgumentException("Context can't be null. Please set up context in CsiConfiguration.");
        } else if (!TextUtils.isEmpty(eaVar.m6449d())) {
            return new eb(eaVar.m6448c(), eaVar.m6449d(), eaVar.m6447b(), eaVar.m6450e());
        } else {
            throw new IllegalArgumentException("AfmaVersion can't be null or empty. Please set up afmaVersion in CsiConfiguration.");
        }
    }
}
