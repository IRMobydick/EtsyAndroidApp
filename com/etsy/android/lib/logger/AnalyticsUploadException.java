package com.etsy.android.lib.logger;

import org.apache.commons.lang3.StringUtils;

public class AnalyticsUploadException extends Exception {
    String message;

    public AnalyticsUploadException(String str) {
        this.message = StringUtils.EMPTY;
        this.message = str;
    }

    public String getMessage() {
        return super.getMessage() + " Error in analytics upload process. " + this.message;
    }
}
