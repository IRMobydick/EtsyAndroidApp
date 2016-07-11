package com.adjust.sdk;

import android.content.Context;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

public class AdjustFactory {
    private static IActivityHandler activityHandler;
    private static IAttributionHandler attributionHandler;
    private static ILogger logger;
    private static HttpsURLConnection mockHttpsURLConnection;
    private static IPackageHandler packageHandler;
    private static IRequestHandler requestHandler;
    private static long sessionInterval;
    private static long subsessionInterval;
    private static long timerInterval;
    private static long timerStart;

    static {
        packageHandler = null;
        requestHandler = null;
        attributionHandler = null;
        activityHandler = null;
        logger = null;
        mockHttpsURLConnection = null;
        timerInterval = -1;
        timerStart = -1;
        sessionInterval = -1;
        subsessionInterval = -1;
    }

    public static IPackageHandler getPackageHandler(ActivityHandler activityHandler, Context context, boolean z) {
        if (packageHandler == null) {
            return new PackageHandler(activityHandler, context, z);
        }
        packageHandler.init(activityHandler, context, z);
        return packageHandler;
    }

    public static IRequestHandler getRequestHandler(IPackageHandler iPackageHandler) {
        if (requestHandler == null) {
            return new RequestHandler(iPackageHandler);
        }
        requestHandler.init(iPackageHandler);
        return requestHandler;
    }

    public static ILogger getLogger() {
        if (logger == null) {
            logger = new Logger();
        }
        return logger;
    }

    public static long getTimerInterval() {
        if (timerInterval == -1) {
            return 60000;
        }
        return timerInterval;
    }

    public static long getTimerStart() {
        if (timerStart == -1) {
            return 0;
        }
        return timerStart;
    }

    public static long getSessionInterval() {
        if (sessionInterval == -1) {
            return 1800000;
        }
        return sessionInterval;
    }

    public static long getSubsessionInterval() {
        if (subsessionInterval == -1) {
            return 1000;
        }
        return subsessionInterval;
    }

    public static IActivityHandler getActivityHandler(AdjustConfig adjustConfig) {
        if (activityHandler == null) {
            return ActivityHandler.getInstance(adjustConfig);
        }
        activityHandler.init(adjustConfig);
        return activityHandler;
    }

    public static IAttributionHandler getAttributionHandler(IActivityHandler iActivityHandler, ActivityPackage activityPackage, boolean z, boolean z2) {
        if (attributionHandler == null) {
            return new AttributionHandler(iActivityHandler, activityPackage, z, z2);
        }
        attributionHandler.init(iActivityHandler, activityPackage, z, z2);
        return attributionHandler;
    }

    public static HttpsURLConnection getHttpsURLConnection(URL url) {
        if (mockHttpsURLConnection == null) {
            return (HttpsURLConnection) url.openConnection();
        }
        return mockHttpsURLConnection;
    }

    public static void setPackageHandler(IPackageHandler iPackageHandler) {
        packageHandler = iPackageHandler;
    }

    public static void setRequestHandler(IRequestHandler iRequestHandler) {
        requestHandler = iRequestHandler;
    }

    public static void setLogger(ILogger iLogger) {
        logger = iLogger;
    }

    public static void setTimerInterval(long j) {
        timerInterval = j;
    }

    public static void setTimerStart(long j) {
        timerStart = j;
    }

    public static void setSessionInterval(long j) {
        sessionInterval = j;
    }

    public static void setSubsessionInterval(long j) {
        subsessionInterval = j;
    }

    public static void setActivityHandler(IActivityHandler iActivityHandler) {
        activityHandler = iActivityHandler;
    }

    public static void setAttributionHandler(IAttributionHandler iAttributionHandler) {
        attributionHandler = iAttributionHandler;
    }

    public static void setMockHttpsURLConnection(HttpsURLConnection httpsURLConnection) {
        mockHttpsURLConnection = httpsURLConnection;
    }
}
