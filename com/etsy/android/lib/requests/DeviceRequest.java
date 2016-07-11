package com.etsy.android.lib.requests;

import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.DeviceNotification;
import com.etsy.android.lib.models.EmptyResult;
import com.etsy.android.lib.models.NotificationSettings;
import com.etsy.android.lib.requests.EtsyRequest.RequestMethod;
import java.util.HashMap;
import java.util.Map;

public class DeviceRequest<Result extends BaseModel> extends EtsyRequest<Result> {
    private static final long serialVersionUID = -5949608446584375387L;

    public DeviceRequest(String str, RequestMethod requestMethod, Class<Result> cls) {
        super(str, requestMethod, cls);
    }

    public static DeviceRequest<EmptyResult> registerDevice() {
        return new DeviceRequest("/device", RequestMethod.POST, EmptyResult.class);
    }

    public static DeviceRequest<EmptyResult> unregisterDevice(String str) {
        return new DeviceRequest("/device/" + str, RequestMethod.DELETE, EmptyResult.class);
    }

    public static DeviceRequest<NotificationSettings> getNotificationSettings(String str) {
        return new DeviceRequest("/device/" + str + "/notification_settings", RequestMethod.GET, NotificationSettings.class);
    }

    public static DeviceRequest<BaseModel> updateNotificationSetting(String str, DeviceNotification deviceNotification) {
        DeviceRequest<BaseModel> deviceRequest = new DeviceRequest("/device/" + str + "/notification_settings/", RequestMethod.PUT, BaseModel.class);
        Map hashMap = new HashMap();
        hashMap.put("device_udid", str);
        hashMap.put("notification_type", deviceNotification.getNotificationId());
        hashMap.put("notification_enabled", Boolean.toString(deviceNotification.isPhoneEnabled()));
        deviceRequest.addParams(hashMap);
        return deviceRequest;
    }

    public static DeviceRequest<BaseModel> updateNotificationSetting(String str, String str2, boolean z) {
        DeviceRequest<BaseModel> deviceRequest = new DeviceRequest("/device/" + str + "/notification_settings/", RequestMethod.PUT, BaseModel.class);
        Map hashMap = new HashMap();
        hashMap.put("device_udid", str);
        hashMap.put("notification_type", str2);
        hashMap.put("notification_enabled", Boolean.toString(z));
        deviceRequest.addParams(hashMap);
        return deviceRequest;
    }
}
