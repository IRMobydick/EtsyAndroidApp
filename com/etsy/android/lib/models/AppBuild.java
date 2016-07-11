package com.etsy.android.lib.models;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.support.annotation.Nullable;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.text.TextUtils;
import com.etsy.android.lib.core.aq;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.models.apiv3.editable.EditableListingV3;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;
import java.util.HashMap;
import org.apache.commons.lang3.StringUtils;

public class AppBuild extends BaseModel {
    public static final String ANDROID_PLATFORM = "android";
    public static final String APK_EXTENSION = ".apk";
    public static final String APK_TEMP_DIR = "etsy/tmp";
    public static final String VARIANT_MASTER = "master";
    private static final long serialVersionUID = 8582916799134364810L;
    private boolean mActive;
    private String mApiDownloadUrl;
    private String mAppName;
    private long mBuildId;
    private String mBuildNotes;
    private String mBundleId;
    private String mDirectDownloadUrl;
    private String mLastUpdatedString;
    private String mS3DownloadUrl;
    private String mVariant;
    private String mVersion;

    public AppBuild() {
        this.mAppName = StringUtils.EMPTY;
        this.mBundleId = StringUtils.EMPTY;
        this.mVariant = StringUtils.EMPTY;
        this.mVersion = StringUtils.EMPTY;
        this.mLastUpdatedString = StringUtils.EMPTY;
        this.mApiDownloadUrl = StringUtils.EMPTY;
        this.mS3DownloadUrl = StringUtils.EMPTY;
        this.mDirectDownloadUrl = StringUtils.EMPTY;
        this.mBuildNotes = StringUtils.EMPTY;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                Object obj = -1;
                switch (currentName.hashCode()) {
                    case -1703187024:
                        if (currentName.equals("build_notes")) {
                            obj = 9;
                            break;
                        }
                        break;
                    case -1479583240:
                        if (currentName.equals("bundle_id")) {
                            obj = 1;
                            break;
                        }
                        break;
                    case -1430655860:
                        if (currentName.equals("build_id")) {
                            obj = 4;
                            break;
                        }
                        break;
                    case -1422950650:
                        if (currentName.equals(EditableListingV3.LISTING_STATE_ACTIVE)) {
                            obj = 10;
                            break;
                        }
                        break;
                    case -1112643122:
                        if (currentName.equals("direct_download_url")) {
                            obj = 8;
                            break;
                        }
                        break;
                    case 236785797:
                        if (currentName.equals("variant")) {
                            obj = 2;
                            break;
                        }
                        break;
                    case 338699282:
                        if (currentName.equals("last_updated")) {
                            obj = 5;
                            break;
                        }
                        break;
                    case 351608024:
                        if (currentName.equals("version")) {
                            obj = 3;
                            break;
                        }
                        break;
                    case 562006263:
                        if (currentName.equals("s3_download_url")) {
                            obj = 7;
                            break;
                        }
                        break;
                    case 789694394:
                        if (currentName.equals("v3_download_url")) {
                            obj = 6;
                            break;
                        }
                        break;
                    case 1167648233:
                        if (currentName.equals("app_name")) {
                            obj = null;
                            break;
                        }
                        break;
                }
                switch (obj) {
                    case Task.NETWORK_STATE_CONNECTED /*0*/:
                        this.mAppName = BaseModel.parseString(jsonParser);
                        break;
                    case Task.NETWORK_STATE_UNMETERED /*1*/:
                        this.mBundleId = BaseModel.parseString(jsonParser);
                        break;
                    case Task.NETWORK_STATE_ANY /*2*/:
                        this.mVariant = BaseModel.parseString(jsonParser);
                        break;
                    case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                        this.mVersion = BaseModel.parseString(jsonParser);
                        break;
                    case CommonStatusCodes.SIGN_IN_REQUIRED /*4*/:
                        this.mBuildId = jsonParser.getValueAsLong();
                        break;
                    case CommonStatusCodes.INVALID_ACCOUNT /*5*/:
                        this.mLastUpdatedString = BaseModel.parseString(jsonParser);
                        break;
                    case CommonStatusCodes.RESOLUTION_REQUIRED /*6*/:
                        this.mApiDownloadUrl = BaseModel.parseStringURL(jsonParser);
                        break;
                    case CommonStatusCodes.NETWORK_ERROR /*7*/:
                        this.mS3DownloadUrl = BaseModel.parseStringURL(jsonParser);
                        break;
                    case CommonStatusCodes.INTERNAL_ERROR /*8*/:
                        this.mDirectDownloadUrl = BaseModel.parseStringURL(jsonParser);
                        break;
                    case CommonStatusCodes.SERVICE_INVALID /*9*/:
                        this.mBuildNotes = BaseModel.parseString(jsonParser);
                        break;
                    case CommonStatusCodes.DEVELOPER_ERROR /*10*/:
                        this.mActive = jsonParser.getValueAsBoolean();
                        break;
                    default:
                        jsonParser.skipChildren();
                        break;
                }
            }
        }
    }

    public boolean isMasterVariant() {
        return VARIANT_MASTER.equalsIgnoreCase(this.mVariant);
    }

    public String getVersion() {
        return this.mVersion;
    }

    public String getS3Url() {
        return this.mS3DownloadUrl;
    }

    public String getFilename() {
        return this.mVersion + APK_EXTENSION;
    }

    @SuppressLint({"DefaultLocale"})
    public String toString() {
        return String.format("Name: %s, Version: %s, ID; %d, Notes: %s", new Object[]{this.mAppName, this.mVersion, Long.valueOf(this.mBuildId), this.mBuildNotes});
    }

    public boolean isValidUpdatedBuild(Context context) {
        if (!this.mActive || TextUtils.isEmpty(this.mS3DownloadUrl)) {
            return false;
        }
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS);
            if (packageInfo == null) {
                return false;
            }
            if (aq.m1144a(this.mVersion).m1145a(aq.m1144a(packageInfo.versionName)) > 0) {
                return true;
            }
            return false;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    @Nullable
    public HashMap<AnalyticsLogAttribute, Object> getTrackingParameters() {
        HashMap<AnalyticsLogAttribute, Object> hashMap = new HashMap(1);
        hashMap.put(AnalyticsLogAttribute.UPGRADE_APP_BUILD_ID, Long.valueOf(this.mBuildId));
        return hashMap;
    }
}
