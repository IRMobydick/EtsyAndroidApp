package com.etsy.android.lib.messaging;

import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat.Builder;
import android.text.TextUtils;
import com.appboy.Constants;
import com.etsy.android.lib.core.aj;
import com.etsy.android.lib.core.img.ImageDownloader;
import com.etsy.android.lib.core.img.ImageHelper;
import com.etsy.android.lib.logger.EtsyLogger;
import com.etsy.android.lib.messaging.p011a.BuyerFavoriteNotification;
import com.etsy.android.lib.messaging.p011a.BuyerIPPPurchaseNotification;
import com.etsy.android.lib.messaging.p011a.BuyerLeftReviewNotification;
import com.etsy.android.lib.messaging.p011a.BuyerReviewsAvailableNotification;
import com.etsy.android.lib.messaging.p011a.ConvoNotification;
import com.etsy.android.lib.messaging.p011a.FundingCompleteNotification;
import com.etsy.android.lib.messaging.p011a.LDDeliveryCompleteBuyerNotification;
import com.etsy.android.lib.messaging.p011a.LDDeliveryCompleteSellerNotification;
import com.etsy.android.lib.messaging.p011a.LDDeliveryDelayedNotification;
import com.etsy.android.lib.messaging.p011a.LDDeliverySoonNotification;
import com.etsy.android.lib.messaging.p011a.LDDeliveryStartedNotification;
import com.etsy.android.lib.messaging.p011a.LDPickupDelayedNotification;
import com.etsy.android.lib.messaging.p011a.LDPickupSoonNotification;
import com.etsy.android.lib.messaging.p011a.LDPickupStartedNotification;
import com.etsy.android.lib.messaging.p011a.LDPurchaseCompleteNotification;
import com.etsy.android.lib.messaging.p011a.OrderNotification;
import com.etsy.android.lib.messaging.p011a.SellerPostUpdateNotification;
import com.etsy.android.lib.messaging.p011a.ShippingNotification;
import com.etsy.android.lib.util.NotificationType;
import com.etsy.android.lib.util.ab;
import com.etsy.android.uikit.ui.shop.ShopHomeAdapter;
import com.foresee.sdk.configuration.Configuration;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;

/* renamed from: com.etsy.android.lib.messaging.g */
public class NotificationsUtil {

    /* renamed from: com.etsy.android.lib.messaging.g.1 */
    /* synthetic */ class NotificationsUtil {
        static final /* synthetic */ int[] f1900a;

        static {
            f1900a = new int[NotificationType.values().length];
            try {
                f1900a[NotificationType.SHIPPING.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f1900a[NotificationType.BUYER_REVIEW_AVAILABLE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f1900a[NotificationType.CONVO.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f1900a[NotificationType.BUYER_FAVORITES_SHOP.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f1900a[NotificationType.BUYER_FAVORITE.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f1900a[NotificationType.BUYER_LEFT_REVIEW.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f1900a[NotificationType.SOLD_ORDER.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                f1900a[NotificationType.BUYER_IPP_PURCHASE.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                f1900a[NotificationType.SELLER_POSTS_UPDATE.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                f1900a[NotificationType.FUNDING_ENDED.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                f1900a[NotificationType.LD_PURCHASE_COMPLETE.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
            try {
                f1900a[NotificationType.LD_PICKUP_STARTED.ordinal()] = 12;
            } catch (NoSuchFieldError e12) {
            }
            try {
                f1900a[NotificationType.LD_PICKUP_SOON.ordinal()] = 13;
            } catch (NoSuchFieldError e13) {
            }
            try {
                f1900a[NotificationType.LD_PICKUP_DELAYED.ordinal()] = 14;
            } catch (NoSuchFieldError e14) {
            }
            try {
                f1900a[NotificationType.LD_DELIVERY_STARTED.ordinal()] = 15;
            } catch (NoSuchFieldError e15) {
            }
            try {
                f1900a[NotificationType.LD_DELIVERY_SOON.ordinal()] = 16;
            } catch (NoSuchFieldError e16) {
            }
            try {
                f1900a[NotificationType.LD_DELIVERY_DELAYED.ordinal()] = 17;
            } catch (NoSuchFieldError e17) {
            }
            try {
                f1900a[NotificationType.LD_DELIVERY_COMPLETE_BUYER.ordinal()] = 18;
            } catch (NoSuchFieldError e18) {
            }
            try {
                f1900a[NotificationType.LD_DELIVERY_COMPLETE_SELLER.ordinal()] = 19;
            } catch (NoSuchFieldError e19) {
            }
        }
    }

    public static void m2301a(Context context, NotificationIntentDelegate notificationIntentDelegate, Bundle bundle, String str, String str2, String str3) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Configuration.NOTIFICATION_LAYOUT_NAME);
        if (NotificationsUtil.m2304a(bundle, str3)) {
            EtsyEntity fromPushNotificationId = EtsyEntity.fromPushNotificationId(str2);
            EtsyNotification a = NotificationsUtil.m2299a(NotificationType.fromString(bundle.getString(Constants.APPBOY_PUSH_TITLE_KEY)));
            Builder a2 = a.m2100a(context, notificationIntentDelegate, fromPushNotificationId, str3, str, bundle);
            if (a2 != null) {
                notificationManager.notify(a.m2111e(), a2.build());
            }
        }
    }

    protected static boolean m2304a(Bundle bundle, String str) {
        Object string = bundle.getString("u");
        if (TextUtils.isEmpty(string)) {
            return true;
        }
        boolean equalsIgnoreCase = string.equalsIgnoreCase(aj.m1101a().m1126m().getId());
        if (equalsIgnoreCase) {
            return equalsIgnoreCase;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Wrong target user ");
        stringBuilder.append(String.format("target_user=%s ", new Object[]{string}));
        stringBuilder.append(String.format("user_id=%s ", new Object[]{r3}));
        stringBuilder.append(String.format("type=%s ", new Object[]{bundle.getString(Constants.APPBOY_PUSH_TITLE_KEY)}));
        stringBuilder.append(String.format("object_id=%s ", new Object[]{str}));
        EtsyLogger.m1966a().m1986a("PushNotification", stringBuilder.toString());
        return equalsIgnoreCase;
    }

    public static EtsyNotification m2299a(NotificationType notificationType) {
        switch (NotificationsUtil.f1900a[notificationType.ordinal()]) {
            case Task.NETWORK_STATE_UNMETERED /*1*/:
                return ShippingNotification.m2249n();
            case Task.NETWORK_STATE_ANY /*2*/:
                return BuyerReviewsAvailableNotification.m2164n();
            case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                return ConvoNotification.m2170n();
            case CommonStatusCodes.SIGN_IN_REQUIRED /*4*/:
            case CommonStatusCodes.INVALID_ACCOUNT /*5*/:
                return BuyerFavoriteNotification.m2132n();
            case CommonStatusCodes.RESOLUTION_REQUIRED /*6*/:
                return BuyerLeftReviewNotification.m2155n();
            case CommonStatusCodes.NETWORK_ERROR /*7*/:
                return OrderNotification.m2236n();
            case CommonStatusCodes.INTERNAL_ERROR /*8*/:
                return BuyerIPPPurchaseNotification.m2140n();
            case CommonStatusCodes.SERVICE_INVALID /*9*/:
                return SellerPostUpdateNotification.m2243n();
            case CommonStatusCodes.DEVELOPER_ERROR /*10*/:
                return FundingCompleteNotification.m2176n();
            case CommonStatusCodes.LICENSE_CHECK_FAILED /*11*/:
                return LDPurchaseCompleteNotification.m2230n();
            case ShopHomeAdapter.TYPE_BUTTON_BLUE_WITH_DESCRIPTION /*12*/:
                return LDPickupStartedNotification.m2224n();
            case CommonStatusCodes.ERROR /*13*/:
                return LDPickupSoonNotification.m2218n();
            case CommonStatusCodes.INTERRUPTED /*14*/:
                return LDPickupDelayedNotification.m2212n();
            case CommonStatusCodes.TIMEOUT /*15*/:
                return LDDeliveryStartedNotification.m2206n();
            case CommonStatusCodes.CANCELED /*16*/:
                return LDDeliverySoonNotification.m2200n();
            case CommonStatusCodes.API_NOT_CONNECTED /*17*/:
                return LDDeliveryDelayedNotification.m2194n();
            case ConnectionResult.SERVICE_UPDATING /*18*/:
                return LDDeliveryCompleteBuyerNotification.m2182n();
            case ConnectionResult.SERVICE_MISSING_PERMISSION /*19*/:
                return LDDeliveryCompleteSellerNotification.m2188n();
            default:
                return EtsyNotification.m2097a();
        }
    }

    public static void m2306b(NotificationType notificationType) {
        EtsyNotification a = NotificationsUtil.m2299a(notificationType);
        if (a != null) {
            a.m2113g();
        }
    }

    public static void m2303a(Bundle bundle) {
        Object string = bundle.getString(Constants.APPBOY_PUSH_TITLE_KEY);
        if (!TextUtils.isEmpty(string)) {
            EtsyNotification a = NotificationsUtil.m2299a(NotificationType.fromString(string));
            if (a != null) {
                a.m2113g();
            }
        }
    }

    public static void m2300a(Context context) {
        ((NotificationManager) context.getSystemService(Configuration.NOTIFICATION_LAYOUT_NAME)).cancelAll();
        for (NotificationType b : NotificationType.values()) {
            NotificationsUtil.m2306b(b);
        }
    }

    public static void m2302a(Context context, NotificationType notificationType) {
        ((NotificationManager) context.getSystemService(Configuration.NOTIFICATION_LAYOUT_NAME)).cancel(notificationType.getId());
    }

    public static void m2305b(Context context, NotificationType notificationType) {
        NotificationsUtil.m2302a(context, notificationType);
        NotificationsUtil.m2306b(notificationType);
    }

    protected static Bitmap m2297a(Context context, String str) {
        ab abVar = new ab(context);
        int a = (int) abVar.m3178a(64.0f);
        int a2 = (int) abVar.m3178a(64.0f);
        Bitmap a3 = NotificationsUtil.m2298a(context, str, a, a2);
        if (a3 == null || a3.getWidth() >= a || a3.getHeight() >= a2) {
            return a3;
        }
        return ImageHelper.m1627a(a3, a, a2);
    }

    protected static Bitmap m2298a(Context context, String str, int i, int i2) {
        ImageDownloader a = ImageDownloader.m1609a();
        a.m1616a(context);
        return a.m1614a(str, i, i2, false, false);
    }
}
