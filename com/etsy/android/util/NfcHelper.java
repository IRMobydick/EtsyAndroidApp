package com.etsy.android.util;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcAdapter.CreateNdefMessageCallback;
import android.nfc.NfcManager;

public class NfcHelper {
    public static boolean m5681a(Context context) {
        NfcManager nfcManager = (NfcManager) context.getSystemService("nfc");
        return (nfcManager == null || nfcManager.getDefaultAdapter() == null) ? false : true;
    }

    public static NdefMessage m5678a(Uri uri) {
        String str = EtsyBuildHelper.m5707b() ? "com.etsy.android.beta" : "com.etsy.android";
        return new NdefMessage(new NdefRecord[]{NdefRecord.createUri(uri), NdefRecord.createApplicationRecord(str)});
    }

    public static void m5679a(Activity activity, Uri uri) {
        if (m5681a((Context) activity)) {
            NfcAdapter.getDefaultAdapter(activity).setNdefPushMessage(m5678a(uri), activity, new Activity[0]);
        }
    }

    public static void m5680a(CreateNdefMessageCallback createNdefMessageCallback, Activity activity) {
        if (m5681a((Context) activity)) {
            NfcAdapter.getDefaultAdapter(activity).setNdefPushMessageCallback(createNdefMessageCallback, activity, new Activity[0]);
        }
    }
}
