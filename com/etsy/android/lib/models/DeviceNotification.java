package com.etsy.android.lib.models;

import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.finds.FindsModule;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import org.apache.commons.lang3.StringUtils;

public class DeviceNotification extends BaseModel {
    public static final String FIELD_EMAIL_AVAILABLE = "field_email_available";
    public static final String FIELD_EMAIL_ENABLED = "field_email_enabled";
    public static final String FIELD_NOTIFICATION_ID = "notification_id";
    public static final String FIELD_PHONE_AVAILABLE = "phone_available";
    public static final String FIELD_PHONE_ENABLED = "phone_enabled";
    private boolean mEmailAvailable;
    private boolean mEmailEnabled;
    private String mNotificationId;
    private boolean mPhoneAvailable;
    private boolean mPhoneEnabled;
    private String mText;

    public DeviceNotification() {
        this.mText = StringUtils.EMPTY;
        this.mNotificationId = StringUtils.EMPTY;
        this.mPhoneAvailable = false;
        this.mPhoneEnabled = false;
        this.mEmailAvailable = false;
        this.mEmailEnabled = false;
        EtsyDebug.m1912c("Scott", "DeviceNotification was constructed");
    }

    public void copySettings(DeviceNotification deviceNotification) {
        setNotificationId(deviceNotification.getNotificationId());
        setText(deviceNotification.getText());
        setPhoneEnabled(deviceNotification.isPhoneEnabled());
        setPhoneAvailable(deviceNotification.isPhoneAvailable());
        setEmailAvailable(deviceNotification.isEmailAvailable());
        setEmailEnabled(deviceNotification.isEmailEnabled());
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (FindsModule.FIELD_TEXT.equals(currentName)) {
                    this.mText = BaseModel.parseString(jsonParser);
                } else if (FIELD_NOTIFICATION_ID.equals(currentName)) {
                    this.mNotificationId = BaseModel.parseString(jsonParser);
                } else if (FIELD_PHONE_AVAILABLE.equals(currentName)) {
                    this.mPhoneAvailable = jsonParser.getBooleanValue();
                } else if (FIELD_PHONE_ENABLED.equals(currentName)) {
                    this.mPhoneEnabled = jsonParser.getBooleanValue();
                } else if (FIELD_EMAIL_AVAILABLE.equals(currentName)) {
                    this.mEmailAvailable = jsonParser.getBooleanValue();
                } else if (FIELD_EMAIL_ENABLED.equals(currentName)) {
                    this.mEmailEnabled = jsonParser.getBooleanValue();
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }

    public boolean isEmailEnabled() {
        return this.mEmailEnabled;
    }

    public void setEmailEnabled(boolean z) {
        this.mEmailEnabled = z;
    }

    public String getText() {
        return this.mText;
    }

    public void setText(String str) {
        this.mText = str;
    }

    public String getNotificationId() {
        return this.mNotificationId;
    }

    public void setNotificationId(String str) {
        this.mNotificationId = str;
    }

    public boolean isPhoneAvailable() {
        return this.mPhoneAvailable;
    }

    public void setPhoneAvailable(boolean z) {
        this.mPhoneAvailable = z;
    }

    public boolean isPhoneEnabled() {
        return this.mPhoneEnabled;
    }

    public void setPhoneEnabled(boolean z) {
        this.mPhoneEnabled = z;
    }

    public boolean isEmailAvailable() {
        return this.mEmailAvailable;
    }

    public void setEmailAvailable(boolean z) {
        this.mEmailAvailable = z;
    }
}
