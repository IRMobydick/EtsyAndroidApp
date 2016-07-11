package com.etsy.android.lib.models;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.util.List;

public class NotificationSettings extends BaseModel {
    private static final String FIELD_DEVICE_NOTIFICATIONS = "device_notifications";
    private static final String FIELD_EMAIL_NOTIFICATIONS = "email_notifications";
    private List<DeviceNotification> mDeviceNotifications;

    public NotificationSettings() {
        this.mDeviceNotifications = null;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (currentName.equals(FIELD_DEVICE_NOTIFICATIONS)) {
                    this.mDeviceNotifications = BaseModel.parseArray(jsonParser, DeviceNotification.class);
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }

    public List<DeviceNotification> getDeviceNotifications() {
        return this.mDeviceNotifications;
    }

    public void setDeviceNotifications(List<DeviceNotification> list) {
        this.mDeviceNotifications = list;
    }

    public void updateDeviceNotification(DeviceNotification deviceNotification) {
        for (DeviceNotification deviceNotification2 : this.mDeviceNotifications) {
            if (deviceNotification2.getNotificationId().equals(deviceNotification.getNotificationId())) {
                deviceNotification2.copySettings(deviceNotification);
                return;
            }
        }
    }
}
