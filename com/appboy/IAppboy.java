package com.appboy;

import android.app.Activity;
import android.widget.ImageView;
import com.appboy.enums.SocialNetwork;
import com.appboy.events.FeedUpdatedEvent;
import com.appboy.events.IEventSubscriber;
import com.appboy.events.InAppMessageEvent;
import com.appboy.events.SubmitFeedbackFailed;
import com.appboy.events.SubmitFeedbackSucceeded;
import com.appboy.models.outgoing.AppboyProperties;
import java.math.BigDecimal;

public interface IAppboy {
    AppboyUser changeUser(String str);

    boolean closeSession(Activity activity);

    void fetchAndRenderImage(String str, ImageView imageView);

    void fetchAndRenderImage(String str, ImageView imageView, boolean z);

    IAppboyNavigator getAppboyNavigator();

    String getAppboyPushMessageRegistrationId();

    AppboyUser getCurrentUser();

    String getInstallTrackingId();

    boolean logCustomEvent(String str);

    boolean logCustomEvent(String str, AppboyProperties appboyProperties);

    boolean logFeedCardClick(String str);

    boolean logFeedCardImpression(String str);

    boolean logFeedDisplayed();

    boolean logFeedbackDisplayed();

    boolean logPurchase(String str, int i);

    boolean logPurchase(String str, String str2, BigDecimal bigDecimal);

    boolean logPurchase(String str, String str2, BigDecimal bigDecimal, int i);

    boolean logPurchase(String str, String str2, BigDecimal bigDecimal, int i, AppboyProperties appboyProperties);

    boolean logPurchase(String str, String str2, BigDecimal bigDecimal, AppboyProperties appboyProperties);

    boolean logPushNotificationActionClicked(String str, String str2);

    boolean logPushNotificationOpened(String str);

    boolean logShare(SocialNetwork socialNetwork);

    boolean openSession(Activity activity);

    void registerAppboyGcmMessages(String str);

    void registerAppboyPushMessages(String str);

    <T> void removeSingleSubscription(IEventSubscriber<T> iEventSubscriber, Class<T> cls);

    void requestFeedRefresh();

    void requestFeedRefreshFromCache();

    void requestImmediateDataFlush();

    void requestInAppMessageRefresh();

    void setAppboyNavigator(IAppboyNavigator iAppboyNavigator);

    boolean submitFeedback(String str, String str2, boolean z);

    void subscribeToFeedUpdates(IEventSubscriber<FeedUpdatedEvent> iEventSubscriber);

    void subscribeToFeedbackRequestEvents(IEventSubscriber<SubmitFeedbackSucceeded> iEventSubscriber, IEventSubscriber<SubmitFeedbackFailed> iEventSubscriber2);

    void subscribeToNewInAppMessages(IEventSubscriber<InAppMessageEvent> iEventSubscriber);

    void unregisterAppboyPushMessages();
}
