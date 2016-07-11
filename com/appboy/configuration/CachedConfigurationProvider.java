package com.appboy.configuration;

import android.content.Context;
import com.appboy.Constants;
import com.appboy.support.PackageUtils;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CachedConfigurationProvider {
    private static final String f815a;
    private final Context f816b;
    protected final Map<String, Object> mConfigurationCache;

    static {
        f815a = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, CachedConfigurationProvider.class.getName()});
    }

    public CachedConfigurationProvider(Context context) {
        this.f816b = context;
        this.mConfigurationCache = Collections.synchronizedMap(new HashMap());
    }

    protected boolean getBooleanValue(String str, boolean z) {
        if (this.mConfigurationCache.containsKey(str)) {
            return ((Boolean) this.mConfigurationCache.get(str)).booleanValue();
        }
        boolean readBooleanResourceValue = readBooleanResourceValue(str, z);
        this.mConfigurationCache.put(str, Boolean.valueOf(readBooleanResourceValue));
        return readBooleanResourceValue;
    }

    protected String getStringValue(String str, String str2) {
        if (this.mConfigurationCache.containsKey(str)) {
            return (String) this.mConfigurationCache.get(str);
        }
        String readStringResourceValue = readStringResourceValue(str, str2);
        this.mConfigurationCache.put(str, readStringResourceValue);
        return readStringResourceValue;
    }

    public int getIntValue(String str, int i) {
        if (this.mConfigurationCache.containsKey(str)) {
            return ((Integer) this.mConfigurationCache.get(str)).intValue();
        }
        int readIntegerResourceValue = readIntegerResourceValue(str, i);
        this.mConfigurationCache.put(str, Integer.valueOf(readIntegerResourceValue));
        return readIntegerResourceValue;
    }

    protected int readIntegerResourceValue(String str, int i) {
        if (str == null) {
            return i;
        }
        int identifier = this.f816b.getResources().getIdentifier(str, "integer", PackageUtils.getResourcePackageName(this.f816b));
        if (identifier != 0) {
            return this.f816b.getResources().getInteger(identifier);
        }
        String str2 = f815a;
        String.format("Unable to find the integer configuration value with key %s. Using default value '%d'.", new Object[]{str, Integer.valueOf(i)});
        return i;
    }

    protected boolean readBooleanResourceValue(String str, boolean z) {
        if (str == null) {
            return z;
        }
        int identifier = this.f816b.getResources().getIdentifier(str, "bool", PackageUtils.getResourcePackageName(this.f816b));
        if (identifier != 0) {
            return this.f816b.getResources().getBoolean(identifier);
        }
        String str2 = f815a;
        String.format("Unable to find the boolean configuration value with key %s. Using default value '%b'.", new Object[]{str, Boolean.valueOf(z)});
        return z;
    }

    protected String readStringResourceValue(String str, String str2) {
        if (str == null) {
            return str2;
        }
        int identifier = this.f816b.getResources().getIdentifier(str, "string", PackageUtils.getResourcePackageName(this.f816b));
        if (identifier != 0) {
            return this.f816b.getResources().getString(identifier);
        }
        String str3 = f815a;
        String.format("Unable to find the boolean configuration value with key %s. Using default value '%s'.", new Object[]{str, str2});
        return str2;
    }

    protected String[] readStringArrayResourceValue(String str, String[] strArr) {
        if (str == null) {
            return strArr;
        }
        int identifier = this.f816b.getResources().getIdentifier(str, "array", PackageUtils.getResourcePackageName(this.f816b));
        if (identifier != 0) {
            return this.f816b.getResources().getStringArray(identifier);
        }
        String str2 = f815a;
        String.format("Unable to find the string array configuration value with key %s. Using default value '%s'.", new Object[]{str, Arrays.toString(strArr)});
        return strArr;
    }
}
