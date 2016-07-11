package com.etsy.android.lib.models.enums;

import com.etsy.android.lib.config.EtsyConfig;
import com.etsy.android.lib.config.EtsyConfigKeys;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;

public enum EtsyReceiptType {
    RECEIPT_TYPE_STANDARD,
    RECEIPT_TYPE_CUSTOM_SHOP,
    RECEIPT_TYPE_GUEST_USER,
    RECEIPT_TYPE_CRAFT;

    public static EtsyReceiptType resolveReceiptType(int i) {
        switch (i) {
            case Task.NETWORK_STATE_CONNECTED /*0*/:
                return RECEIPT_TYPE_STANDARD;
            case Task.NETWORK_STATE_UNMETERED /*1*/:
                return EtsyConfig.m837a().m869d().m885c(EtsyConfigKeys.cp) ? RECEIPT_TYPE_CUSTOM_SHOP : RECEIPT_TYPE_STANDARD;
            case Task.NETWORK_STATE_ANY /*2*/:
                return RECEIPT_TYPE_GUEST_USER;
            case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                return RECEIPT_TYPE_CRAFT;
            default:
                return RECEIPT_TYPE_STANDARD;
        }
    }
}
