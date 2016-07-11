package com.appboy;

import android.app.Notification;
import android.content.Context;
import android.os.Bundle;
import com.appboy.configuration.XmlAppConfigurationProvider;

public interface IAppboyNotificationFactory {
    Notification createNotification(XmlAppConfigurationProvider xmlAppConfigurationProvider, Context context, Bundle bundle, Bundle bundle2);
}
