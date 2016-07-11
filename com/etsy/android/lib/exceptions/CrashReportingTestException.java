package com.etsy.android.lib.exceptions;

public class CrashReportingTestException extends RuntimeException {
    public String getMessage() {
        return "This is an exception which was generated to test Crash Reporting";
    }
}
