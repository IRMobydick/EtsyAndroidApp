package com.foresee.mobileReplay.http;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import com.etsy.android.lib.models.AppBuild;
import com.etsy.android.lib.requests.HttpUtil;
import com.foresee.mobileReplay.util.FsProperties;
import com.google.gson.e;
import com.google.gson.i;
import com.google.gson.n;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

public class BlacklistCheck {
    private static e gson;
    private String blacklistUrlTemplate;
    private ConnectivityManager connectivityManager;

    static {
        gson = new e();
    }

    @Inject
    public BlacklistCheck(ConnectivityManager connectivityManager, @Named("BLACKLIST_URL_TEMPLATE") String str) {
        this.connectivityManager = connectivityManager;
        this.blacklistUrlTemplate = str;
    }

    public Void checkBlacklistService(String str, BlacklistCallback blacklistCallback) {
        Log.d("FORESEE_BLACKLIST", "Requesting Blacklist");
        NetworkInfo activeNetworkInfo = this.connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null || !((activeNetworkInfo.getType() == 1 || activeNetworkInfo.getType() == 0) && activeNetworkInfo.isConnected())) {
            Log.d("FORESEE_BLACKLIST", "No network connection: recording will be suspended");
            blacklistCallback.blacklistUnavailable();
        } else {
            AsyncHttpRequest asyncHttpRequest = new AsyncHttpRequest(StringUtils.EMPTY, new 1(this, blacklistCallback));
            try {
                String format = String.format(this.blacklistUrlTemplate, new Object[]{URLEncoder.encode(str, HttpUtil.CONTENT_ENCODING)});
                Log.v("FORESEE_BLACKLIST", String.format("Executing blacklist check: %s", new Object[]{format}));
                asyncHttpRequest.execute(new String[]{format});
            } catch (Throwable e) {
                Log.e("FORESEE_BLACKLIST", e.getMessage(), e);
                blacklistCallback.blacklistServiceCallback(false);
            }
        }
        return null;
    }

    boolean isForeSeeSdkBlacklisted(String str) {
        Log.d("FORESEE_BLACKLIST", String.format("Checking ForeSee SDK API level blacklisting: %s", new Object[]{FsProperties.instance().getVersion()}));
        try {
            Type type = new 2(this).getType();
            i iVar = (i) ((HashMap) gson.a((n) ((HashMap) gson.a(str, type)).get(AppBuild.ANDROID_PLATFORM), type)).get("replay_version");
            if (iVar != null) {
                ArrayList arrayList = (ArrayList) gson.a(iVar, new 3(this).getType());
                if (arrayList != null && arrayList.size() > 0) {
                    String version = FsProperties.instance().getVersion();
                    for (int i = 0; i < arrayList.size(); i++) {
                        if (doesVersionMatch(version, (String) arrayList.get(i))) {
                            return true;
                        }
                    }
                    return false;
                }
            }
            return false;
        } catch (Exception e) {
            Log.w("FORESEE_BLACKLIST", String.format("Error decoding blacklist JSON: %s", new Object[]{e.toString()}));
            return false;
        }
    }

    boolean isAndroidSdkLevelBlacklisted(int i, String str) {
        Log.d("FORESEE_BLACKLIST", String.format("Checking Android API level blacklisting: %s", new Object[]{Integer.valueOf(i)}));
        try {
            Type type = new 4(this).getType();
            HashMap hashMap = (HashMap) gson.a(str, type);
            if (hashMap == null) {
                return false;
            }
            i iVar = (i) ((HashMap) gson.a((n) hashMap.get(AppBuild.ANDROID_PLATFORM), type)).get("os_version");
            if (iVar != null) {
                Iterator it = ((ArrayList) gson.a(iVar, new 5(this).getType())).iterator();
                while (it.hasNext()) {
                    Operator parse;
                    int parseInt;
                    String str2 = (String) it.next();
                    Operator operator = Operator.EQUALS;
                    Matcher matcher = Pattern.compile("([<>=]+)([\\d]+)").matcher(str2);
                    if (matcher.matches()) {
                        parse = Operator.parse(matcher.group(1));
                        parseInt = Integer.parseInt(matcher.group(2));
                    } else {
                        Matcher matcher2 = Pattern.compile("([\\d]+)").matcher(str2);
                        if (matcher2.matches()) {
                            parseInt = Integer.parseInt(matcher2.group(1));
                            parse = operator;
                        } else {
                            parseInt = -1;
                            parse = operator;
                        }
                    }
                    Log.v("FORESEE_BLACKLIST", String.format("Blacklist: %s %d", new Object[]{parse.name(), Integer.valueOf(parseInt)}));
                    if (parse.evaluate(i, parseInt)) {
                        return true;
                    }
                }
                return false;
            }
            Log.w("FORESEE_BLACKLIST", "Android SDK level blacklisting information not present in blacklist file");
            return false;
        } catch (Exception e) {
            Log.w("FORESEE_BLACKLIST", String.format("Error decoding blacklist JSON: %s", new Object[]{e.toString()}));
            return false;
        }
    }

    private boolean doesVersionMatch(String str, String str2) {
        int i;
        String substring;
        if (str2.substring(0, 1).compareTo("<") == 0) {
            i = -1;
            substring = str2.substring(1);
        } else if (str2.substring(0, 1).compareTo(">") == 0) {
            substring = str2.substring(1);
            i = 1;
        } else if (str.compareTo(str2) == 0) {
            return true;
        } else {
            return false;
        }
        String[] split = str.split("\\.");
        String[] split2 = substring.split("\\.");
        int max = Math.max(split.length, split2.length);
        int[] iArr = new int[max];
        int[] iArr2 = new int[max];
        max = 0;
        while (max < split.length) {
            try {
                iArr[max] = Integer.parseInt(split[max]);
                max++;
            } catch (NumberFormatException e) {
                Log.w("FORESEE_BLACKLIST", String.format("Error decoding version number %s or %s", new Object[]{str, str2}));
                return false;
            }
        }
        for (max = 0; max < split2.length; max++) {
            iArr2[max] = Integer.parseInt(split2[max]);
        }
        max = 0;
        int i2 = 0;
        while (max < Math.min(iArr2.length, iArr.length)) {
            i2 = iArr[max] - iArr2[max];
            if ((i2 < 0 && i > 0) || (i2 > 0 && i < 0)) {
                System.out.println(String.format("Version number %s doesn't match %s at index %d (%d/%d)", new Object[]{str, str2, Integer.valueOf(max), Integer.valueOf(i2), Integer.valueOf(i)}));
                return false;
            } else if (i2 > 0 && i > 0) {
                return true;
            } else {
                if (i2 < 0 && i < 0) {
                    return true;
                }
                max++;
            }
        }
        if (i2 == 0) {
            return false;
        }
        System.out.println(String.format("Version number %s matches %s", new Object[]{str, str2}));
        return true;
    }
}
