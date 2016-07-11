package com.etsy.android.ui.nav;

import android.app.ActivityManager;
import android.app.ActivityManager.RecentTaskInfo;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import com.appboy.Constants;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.logger.EtsyLogger;
import com.etsy.android.lib.logger.EventTracker;
import com.etsy.android.lib.logger.LiveActivityCounter;
import com.etsy.android.lib.logger.p010a.EtsyGraphite;
import com.etsy.android.lib.messaging.EtsyAction;
import com.etsy.android.lib.messaging.EtsyEntity;
import com.etsy.android.lib.messaging.EtsyNotification;
import com.etsy.android.lib.messaging.EtsyRoute;
import com.etsy.android.lib.messaging.NotificationsUtil;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.TaxonomyNode;
import com.etsy.android.lib.models.apiv3.ListingShippingDetails;
import com.etsy.android.lib.ui.nav.BaseNotificationActivity;
import com.etsy.android.lib.util.CrashUtil;
import com.etsy.android.lib.util.NotificationType;
import com.etsy.android.lib.util.ad;
import com.etsy.android.ui.search.v2.MutableSearchOptions;
import com.etsy.android.uikit.nav.ActivityNavigator.AnimationMode;
import com.etsy.android.util.AppboyUtil;
import com.etsy.android.util.EtsyBuildHelper;
import com.google.android.gms.p019a.C1074a;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import org.apache.commons.lang3.StringUtils;

public class NotificationActivity extends BaseNotificationActivity {
    public static final String ETSY_DEEPLINK_PARAM = "deeplink";
    public static final String ETSY_DEFERRED_PARAM = "deferred";
    private static final String TAG;

    /* renamed from: com.etsy.android.ui.nav.NotificationActivity.1 */
    class C07741 extends HashMap<String, String> {
        C07741() {
            put("android.intent.action.VIEW", "external_url");
            put("android.nfc.action.NDEF_DISCOVERED", "nfc");
            put("com.etsy.android.action.NOTIFICATION", "notifications");
        }
    }

    /* renamed from: com.etsy.android.ui.nav.NotificationActivity.2 */
    /* synthetic */ class C07752 {
        static final /* synthetic */ int[] f3168a;

        static {
            f3168a = new int[EtsyEntity.values().length];
            try {
                f3168a[EtsyEntity.TRACK_ORDER.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f3168a[EtsyEntity.CONVO.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f3168a[EtsyEntity.CONVO_DEEPLINK.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f3168a[EtsyEntity.LISTING.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f3168a[EtsyEntity.LISTINGS_SIMILAR.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f3168a[EtsyEntity.PEOPLE.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f3168a[EtsyEntity.SHOP.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                f3168a[EtsyEntity.ORDERS.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                f3168a[EtsyEntity.BROWSE.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                f3168a[EtsyEntity.SHOP_POLICY.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                f3168a[EtsyEntity.SHOP_ABOUT.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
            try {
                f3168a[EtsyEntity.SHOP_REVIEWS.ordinal()] = 12;
            } catch (NoSuchFieldError e12) {
            }
            try {
                f3168a[EtsyEntity.RECEIPT.ordinal()] = 13;
            } catch (NoSuchFieldError e13) {
            }
            try {
                f3168a[EtsyEntity.EXPLORE_REVIEW.ordinal()] = 14;
            } catch (NoSuchFieldError e14) {
            }
            try {
                f3168a[EtsyEntity.PURCHASES.ordinal()] = 15;
            } catch (NoSuchFieldError e15) {
            }
            try {
                f3168a[EtsyEntity.TREASURY.ordinal()] = 16;
            } catch (NoSuchFieldError e16) {
            }
            try {
                f3168a[EtsyEntity.CART.ordinal()] = 17;
            } catch (NoSuchFieldError e17) {
            }
            try {
                f3168a[EtsyEntity.LOCAL.ordinal()] = 18;
            } catch (NoSuchFieldError e18) {
            }
            try {
                f3168a[EtsyEntity.LOCAL_STORE.ordinal()] = 19;
            } catch (NoSuchFieldError e19) {
            }
            try {
                f3168a[EtsyEntity.LOCAL_EVENT.ordinal()] = 20;
            } catch (NoSuchFieldError e20) {
            }
            try {
                f3168a[EtsyEntity.LOCAL_EVENT_DIRECT.ordinal()] = 21;
            } catch (NoSuchFieldError e21) {
            }
            try {
                f3168a[EtsyEntity.TAXONOMY_CATEGORY.ordinal()] = 22;
            } catch (NoSuchFieldError e22) {
            }
            try {
                f3168a[EtsyEntity.SEARCH.ordinal()] = 23;
            } catch (NoSuchFieldError e23) {
            }
            try {
                f3168a[EtsyEntity.MARKET.ordinal()] = 24;
            } catch (NoSuchFieldError e24) {
            }
            try {
                f3168a[EtsyEntity.COMPOSE_REVIEW.ordinal()] = 25;
            } catch (NoSuchFieldError e25) {
            }
            try {
                f3168a[EtsyEntity.APPRECIATION_PHOTO_LANDING_PAGE.ordinal()] = 26;
            } catch (NoSuchFieldError e26) {
            }
            try {
                f3168a[EtsyEntity.FEATURED.ordinal()] = 27;
            } catch (NoSuchFieldError e27) {
            }
            try {
                f3168a[EtsyEntity.LISTING_LANDING_PAGE.ordinal()] = 28;
            } catch (NoSuchFieldError e28) {
            }
            try {
                f3168a[EtsyEntity.BUY_GIFT_CARD.ordinal()] = 29;
            } catch (NoSuchFieldError e29) {
            }
            try {
                f3168a[EtsyEntity.CREATE_GIFT_CARD.ordinal()] = 30;
            } catch (NoSuchFieldError e30) {
            }
            try {
                f3168a[EtsyEntity.HOMESCREEN.ordinal()] = 31;
            } catch (NoSuchFieldError e31) {
            }
        }
    }

    static {
        TAG = EtsyDebug.m1891a(NotificationActivity.class);
    }

    public NotificationActivity() {
        super(new C07741());
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onCreate(android.os.Bundle r11) {
        /*
        r10 = this;
        r2 = 0;
        r9 = 1;
        super.onCreate(r11);
        r0 = r10.getWindow();
        r1 = 2131623967; // 0x7f0e001f float:1.88751E38 double:1.053162172E-314;
        r0.setBackgroundDrawableResource(r1);
        r6 = r10.getIntent();
        if (r6 == 0) goto L_0x039b;
    L_0x0015:
        r0 = r6.getData();
        r1 = com.etsy.android.lib.messaging.EtsyRoute.m2266a(r0);
        r0 = r6.getAction();
        r3 = "external";
        r4 = r10.determineSourceType(r0, r3);
        r10.trackIntent(r6, r4, r1);
        if (r1 != 0) goto L_0x00a1;
    L_0x002c:
        r0 = com.yozio.android.Yozio.m7485a(r6);
        r0 = com.etsy.android.util.DeepLinkUtil.m5705b(r0);
        if (r0 == 0) goto L_0x007d;
    L_0x0036:
        r0 = com.etsy.android.lib.messaging.EtsyRoute.m2266a(r0);
        r10.trackYozioIntent(r6);
        r3 = r0;
    L_0x003e:
        r0 = r10.getApplicationContext();
        r1 = r6.getData();
        com.google.ads.conversiontracking.C1058a.m5804a(r0, r1);
        if (r3 == 0) goto L_0x0396;
    L_0x004b:
        r5 = r10.getRouteBundle(r3);
        r0 = "referrer_bundle";
        r1 = r10.addReferrerData(r6);
        r5.putBundle(r0, r1);
        r0 = "source_type";
        r5.putString(r0, r4);
        r0 = r10.getActivityNavigator();
        r0 = r0.m4445a(r5);
        r1 = new com.etsy.android.lib.models.datatypes.EtsyDeepLinkId;
        r1.<init>();
        r7 = com.etsy.android.ui.nav.NotificationActivity.C07752.f3168a;
        r8 = r3.m2275c();
        r8 = r8.ordinal();
        r7 = r7[r8];
        switch(r7) {
            case 2: goto L_0x00ac;
            case 3: goto L_0x00b1;
            case 4: goto L_0x00b9;
            case 5: goto L_0x00ce;
            case 6: goto L_0x00d6;
            case 7: goto L_0x00e4;
            case 8: goto L_0x011c;
            case 9: goto L_0x012d;
            case 10: goto L_0x0135;
            case 11: goto L_0x0160;
            case 12: goto L_0x018b;
            case 13: goto L_0x019e;
            case 14: goto L_0x019e;
            case 15: goto L_0x01f1;
            case 16: goto L_0x01fd;
            case 17: goto L_0x0207;
            case 18: goto L_0x0213;
            case 19: goto L_0x021a;
            case 20: goto L_0x021a;
            case 21: goto L_0x021a;
            case 22: goto L_0x0228;
            case 23: goto L_0x024c;
            case 24: goto L_0x024c;
            case 25: goto L_0x02e1;
            case 26: goto L_0x02ed;
            case 27: goto L_0x02f9;
            case 28: goto L_0x032e;
            case 29: goto L_0x0388;
            case 30: goto L_0x0388;
            case 31: goto L_0x038d;
            default: goto L_0x0079;
        };
    L_0x0079:
        r10.showHome(r6);
    L_0x007c:
        return;
    L_0x007d:
        r3 = com.etsy.android.lib.logger.EtsyLogger.m1966a();
        r5 = "Route";
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r7 = "No route found for: ";
        r7 = r0.append(r7);
        r0 = r6.getData();
        if (r0 != 0) goto L_0x00a3;
    L_0x0094:
        r0 = "null";
    L_0x0096:
        r0 = r7.append(r0);
        r0 = r0.toString();
        r3.m1996b(r5, r0);
    L_0x00a1:
        r3 = r1;
        goto L_0x003e;
    L_0x00a3:
        r0 = r6.getData();
        r0 = r0.toString();
        goto L_0x0096;
    L_0x00ac:
        r1 = "convo";
        com.etsy.android.lib.logger.EventTracker.m2040c(r1, r4);
    L_0x00b1:
        r1 = r3.m2276d();
        r0.m4532g(r1);
        goto L_0x007c;
    L_0x00b9:
        r1 = "listing";
        com.etsy.android.lib.logger.EventTracker.m2040c(r1, r4);
        r1 = new com.etsy.android.lib.models.datatypes.EtsyId;
        r2 = r3.m2276d();
        r1.<init>(r2);
        r0.m4470a(r1, r5);
        r10.finish();
        goto L_0x007c;
    L_0x00ce:
        r1 = r3.m2276d();
        r0.m4519d(r1);
        goto L_0x007c;
    L_0x00d6:
        r2 = r3.m2276d();
        r1.checkIdTypeAndSet(r2);
        r0.m4527f(r1, r5);
        r10.finish();
        goto L_0x007c;
    L_0x00e4:
        r2 = com.etsy.android.lib.util.bf.m3325a(r6, r4);
        if (r2 == 0) goto L_0x010d;
    L_0x00ea:
        r1 = com.etsy.android.lib.util.bf.m3326a(r3);
        if (r1 == 0) goto L_0x0105;
    L_0x00f0:
        r1 = r3.m2276d();
        r2 = new com.etsy.android.uikit.ui.shop.a;
        r3 = r3.m2278f();
        r2.<init>(r9, r3);
        r0.m4486a(r1, r5, r2);
    L_0x0100:
        r10.finish();
        goto L_0x007c;
    L_0x0105:
        r1 = r3.m2276d();
        r0.m4485a(r1, r5);
        goto L_0x0100;
    L_0x010d:
        r2 = r3.m2276d();
        r1.checkIdTypeAndSet(r2);
        r0.m4502b(r1, r5);
        r10.finish();
        goto L_0x007c;
    L_0x011c:
        r1 = new com.etsy.android.lib.models.datatypes.EtsyId;
        r2 = r3.m2276d();
        r1.<init>(r2);
        r0.m4531g(r1, r5);
        r10.finish();
        goto L_0x007c;
    L_0x012d:
        r0.m4540n();
        r10.finish();
        goto L_0x007c;
    L_0x0135:
        r2 = com.etsy.android.lib.util.bf.m3325a(r6, r4);
        if (r2 == 0) goto L_0x014d;
    L_0x013b:
        r1 = r3.m2276d();
        r2 = new com.etsy.android.uikit.ui.shop.a;
        r3 = 3;
        r2.<init>(r3);
        r0.m4486a(r1, r5, r2);
        r10.finish();
        goto L_0x007c;
    L_0x014d:
        r2 = r3.m2276d();
        r1.checkIdTypeAndSet(r2);
        r0 = r0.m4529g();
        r0.m4512c(r1, r5);
        r10.finish();
        goto L_0x007c;
    L_0x0160:
        r2 = com.etsy.android.lib.util.bf.m3325a(r6, r4);
        if (r2 == 0) goto L_0x0178;
    L_0x0166:
        r1 = r3.m2276d();
        r2 = new com.etsy.android.uikit.ui.shop.a;
        r3 = 2;
        r2.<init>(r3);
        r0.m4486a(r1, r5, r2);
        r10.finish();
        goto L_0x007c;
    L_0x0178:
        r2 = r3.m2276d();
        r1.checkIdTypeAndSet(r2);
        r0 = r0.m4529g();
        r0.m4518d(r1, r5);
        r10.finish();
        goto L_0x007c;
    L_0x018b:
        r2 = r3.m2276d();
        r1.checkIdTypeAndSet(r2);
        r0 = r0.m4529g();
        r0.m4523e(r1, r5);
        r10.finish();
        goto L_0x007c;
    L_0x019e:
        r1 = r3.m2277e();
        if (r1 == 0) goto L_0x01b7;
    L_0x01a4:
        r1 = com.etsy.android.ui.nav.NotificationActivity.C07752.f3168a;
        r2 = r3.m2277e();
        r2 = r2.m2275c();
        r2 = r2.ordinal();
        r1 = r1[r2];
        switch(r1) {
            case 1: goto L_0x01ce;
            default: goto L_0x01b7;
        };
    L_0x01b7:
        r1 = "receipt";
        com.etsy.android.lib.logger.EventTracker.m2040c(r1, r4);
        r0 = r0.m4529g();
        r1 = new com.etsy.android.lib.models.datatypes.EtsyId;
        r2 = r3.m2276d();
        r1.<init>(r2);
        r0.m4517d(r1);
        goto L_0x007c;
    L_0x01ce:
        r1 = new com.etsy.android.lib.models.datatypes.EtsyId;
        r2 = r3.m2276d();
        r1.<init>(r2);
        r2 = new com.etsy.android.lib.models.datatypes.EtsyId;
        r3 = r3.m2277e();
        r3 = r3.m2276d();
        r2.<init>(r3);
        r0.m4503b(r1, r2);
        r0 = com.etsy.android.lib.util.NotificationType.SHIPPING;
        com.etsy.android.lib.messaging.NotificationsUtil.m2305b(r10, r0);
        r10.finish();
        goto L_0x007c;
    L_0x01f1:
        r0 = r0.m4529g();
        r0.m4548v();
        r10.finish();
        goto L_0x007c;
    L_0x01fd:
        r0 = "treasury";
        com.etsy.android.lib.logger.EventTracker.m2040c(r0, r4);
        r10.showHome(r6);
        goto L_0x007c;
    L_0x0207:
        r10.finish();
        r0 = r0.m4529g();
        r0.m4541o();
        goto L_0x007c;
    L_0x0213:
        r1 = "local";
        r0.m4514c(r1);
        goto L_0x007c;
    L_0x021a:
        r1 = new com.etsy.android.lib.models.datatypes.EtsyId;
        r2 = r3.m2276d();
        r1.<init>(r2);
        r0.m4476a(r1, r9);
        goto L_0x007c;
    L_0x0228:
        r1 = com.etsy.android.lib.config.EtsyConfig.m837a();
        r1 = r1.m869d();
        r4 = com.etsy.android.lib.config.EtsyConfigKeys.cr;
        r1 = r1.m885c(r4);
        if (r1 == 0) goto L_0x024c;
    L_0x0238:
        r1 = r3.m2276d();
        r1 = com.etsy.android.lib.models.TaxonomyNode.pathFromWebUrlToAPIFormat(r1);
        r2 = r3.m2278f();
        r0.m4492a(r1, r2);
        r10.finish();
        goto L_0x007c;
    L_0x024c:
        r1 = r3.m2275c();
        r4 = com.etsy.android.lib.messaging.EtsyEntity.SEARCH;
        r1 = r1.equals(r4);
        if (r1 == 0) goto L_0x02a6;
    L_0x0258:
        r1 = "q";
        r1 = r3.m2272a(r1);
        r2 = r3.m2276d();
        r4 = "shops";
        r4 = r2.startsWith(r4);
        if (r4 != 0) goto L_0x0272;
    L_0x026a:
        r4 = "people";
        r4 = r2.startsWith(r4);
        if (r4 == 0) goto L_0x027a;
    L_0x0272:
        r0.m4540n();
        r10.finish();
        goto L_0x007c;
    L_0x027a:
        r4 = r2.length();
        if (r4 <= 0) goto L_0x02b6;
    L_0x0280:
        r4 = android.text.TextUtils.isEmpty(r1);
        if (r4 == 0) goto L_0x02b6;
    L_0x0286:
        r4 = com.etsy.android.lib.config.EtsyConfig.m837a();
        r4 = r4.m869d();
        r6 = com.etsy.android.lib.config.EtsyConfigKeys.cr;
        r4 = r4.m885c(r6);
        if (r4 == 0) goto L_0x02b6;
    L_0x0296:
        r1 = com.etsy.android.lib.models.TaxonomyNode.pathFromWebUrlToAPIFormat(r2);
        r2 = r3.m2278f();
        r0.m4492a(r1, r2);
        r10.finish();
        goto L_0x007c;
    L_0x02a6:
        r1 = r3.m2275c();
        r4 = com.etsy.android.lib.messaging.EtsyEntity.MARKET;
        r1 = r1.equals(r4);
        if (r1 == 0) goto L_0x03a3;
    L_0x02b2:
        r1 = r3.m2276d();
    L_0x02b6:
        if (r1 != 0) goto L_0x02ba;
    L_0x02b8:
        r1 = "";
    L_0x02ba:
        r2 = "-";
        r4 = " ";
        r1 = r1.replace(r2, r4);
        r2 = "_";
        r4 = " ";
        r1 = r1.replace(r2, r4);
        r1 = r10.decodeQueryParameter(r1);
        r2 = "anchor_listing_id";
        r4 = r3.m2272a(r2);
        r2 = r10.getSearchOptions(r3);
        r3 = r10.getSearchTaxonomyNode(r3);
        r0.m4488a(r1, r2, r3, r4, r5);
        goto L_0x007c;
    L_0x02e1:
        r2 = r3.m2276d();
        r1.checkIdTypeAndSet(r2);
        r0.m4522e(r1);
        goto L_0x007c;
    L_0x02ed:
        r2 = r3.m2276d();
        r1.checkIdTypeAndSet(r2);
        r0.m4526f(r1);
        goto L_0x007c;
    L_0x02f9:
        r4 = r3.m2276d();
        r1 = android.text.TextUtils.isEmpty(r4);
        if (r1 != 0) goto L_0x0329;
    L_0x0303:
        r2 = 0;
        r1 = r3.m2278f();
        r5 = "view_draft_content";
        r1 = r1.get(r5);
        r1 = (java.lang.String) r1;
        r5 = android.text.TextUtils.isEmpty(r1);
        if (r5 != 0) goto L_0x03a0;
    L_0x0316:
        r1 = java.lang.Boolean.valueOf(r1);
        r1 = r1.booleanValue();
    L_0x031e:
        r2 = "anchor_listing_id";
        r2 = r3.m2272a(r2);
        r0.m4491a(r4, r2, r1);
        goto L_0x007c;
    L_0x0329:
        r10.showHome(r6);
        goto L_0x007c;
    L_0x032e:
        r4 = "";
        r2 = "";
        r1 = "";
        r5 = "api_path";
        r5 = r3.m2272a(r5);	 Catch:{ UnsupportedEncodingException -> 0x0375 }
        r7 = "UTF-8";
        r4 = java.net.URLDecoder.decode(r5, r7);	 Catch:{ UnsupportedEncodingException -> 0x0375 }
        r5 = "title";
        r5 = r3.m2272a(r5);	 Catch:{ UnsupportedEncodingException -> 0x0375 }
        r7 = "UTF-8";
        r2 = java.net.URLDecoder.decode(r5, r7);	 Catch:{ UnsupportedEncodingException -> 0x0375 }
        r5 = "event_name";
        r3 = r3.m2272a(r5);	 Catch:{ UnsupportedEncodingException -> 0x0375 }
        r5 = "UTF-8";
        r1 = java.net.URLDecoder.decode(r3, r5);	 Catch:{ UnsupportedEncodingException -> 0x0375 }
        r3 = r4;
    L_0x0359:
        r4 = android.text.TextUtils.isEmpty(r3);
        if (r4 != 0) goto L_0x0383;
    L_0x035f:
        r4 = new com.etsy.android.lib.models.homescreen.LandingPageLink;
        r4.<init>(r2);
        r4.setApiPath(r3);
        r2 = android.text.TextUtils.isEmpty(r1);
        if (r2 != 0) goto L_0x0370;
    L_0x036d:
        r4.setEventName(r1);
    L_0x0370:
        r0.m4478a(r4);
        goto L_0x007c;
    L_0x0375:
        r3 = move-exception;
        r3 = r4;
        r4 = com.etsy.android.lib.logger.EtsyLogger.m1966a();
        r5 = TAG;
        r7 = "Couldn't decode listing landing page query parameter";
        r4.m1996b(r5, r7);
        goto L_0x0359;
    L_0x0383:
        r10.showHome(r6);
        goto L_0x007c;
    L_0x0388:
        r0.m4533h();
        goto L_0x007c;
    L_0x038d:
        r1 = r3.m2276d();
        r0.m4514c(r1);
        goto L_0x007c;
    L_0x0396:
        r10.showHome(r6);
        goto L_0x007c;
    L_0x039b:
        r10.showHome(r2);
        goto L_0x007c;
    L_0x03a0:
        r1 = r2;
        goto L_0x031e;
    L_0x03a3:
        r1 = r2;
        goto L_0x02b6;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.etsy.android.ui.nav.NotificationActivity.onCreate(android.os.Bundle):void");
    }

    private EtsyActivityNavigator getActivityNavigator() {
        boolean z = false;
        EtsyActivityNavigator a = Nav.m4682a((FragmentActivity) this).m4683a().m4446a(AnimationMode.DEFAULT);
        if (getIntent() != null && getIntent().getBooleanExtra("NAV_INTERNAL_LINK", false)) {
            z = true;
        }
        if (getIntent() != null) {
            String stringExtra = getIntent().getStringExtra(Constants.APPBOY_PUSH_TITLE_KEY);
            if (!TextUtils.isEmpty(stringExtra)) {
                a.m4447a(stringExtra);
            }
        }
        if (z) {
            a.m4446a(AnimationMode.SLIDE_RIGHT);
        }
        if (LiveActivityCounter.m2052c() && !z) {
            a.m4529g();
        }
        return a;
    }

    private Bundle getRouteBundle(EtsyRoute etsyRoute) {
        Bundle bundle = new Bundle();
        if (etsyRoute.m2274b() != null) {
            bundle.putString(EtsyAction.ACTION_TYPE_NAME, etsyRoute.m2274b().getName());
        }
        return bundle;
    }

    private Bundle addReferrerData(Intent intent) {
        Bundle bundle = new Bundle();
        Uri data = intent.getData();
        if (data != null && "1".equals(data.getQueryParameter(ETSY_DEEPLINK_PARAM))) {
            if (data.getQueryParameter("r") != null) {
                bundle.putString(".ref", data.getQueryParameter("r"));
            }
            if (data.getQueryParameter("g") != null) {
                bundle.putString("originating_page_guid", data.getQueryParameter("g"));
            }
            if (data.getQueryParameter("uaid") != null) {
                bundle.putString("originating_uaid", data.getQueryParameter("uaid"));
            }
            for (String str : data.getQueryParameterNames()) {
                if (!(str.equals("r") || str.equals("g") || str.equals("uaid"))) {
                    bundle.putString(str, data.getQueryParameter(str));
                }
            }
        }
        if (!bundle.containsKey(".ref")) {
            Uri referrer = new ActivityCompat().getReferrer(this);
            if (referrer != null) {
                if (referrer.getScheme().equals("http") || referrer.getScheme().equals(com.adjust.sdk.Constants.SCHEME)) {
                    bundle.putString(".ref", referrer.toString());
                } else if (referrer.getScheme().equals("android-app")) {
                    C1074a a = C1074a.m5846a(referrer);
                    if ("com.google.appcrawler".equals(a.m5850b())) {
                        AppboyUtil.m5692b();
                    } else {
                        if (a.m5851c() != null) {
                            bundle.putString(".ref", a.m5851c().toString());
                        }
                        bundle.putString(".ref_package", a.m5850b());
                    }
                }
            }
        }
        if (!bundle.containsKey(".ref")) {
            Intent findLastIntent = findLastIntent();
            if (!(findLastIntent == null || findLastIntent.getComponent() == null || findLastIntent.getComponent().equals(getComponentName()))) {
                bundle.putString(".ref", findLastIntent.getDataString());
                bundle.putString(".ref_package", findLastIntent.getComponent().getPackageName());
            }
        }
        bundle.putString(".loc", intent.getDataString());
        return bundle;
    }

    private Intent findLastIntent() {
        List recentTasks = ((ActivityManager) getSystemService("activity")).getRecentTasks(5, 0);
        return recentTasks.size() > 0 ? ((RecentTaskInfo) recentTasks.get(0)).baseIntent : null;
    }

    private void showHome(Intent intent) {
        Bundle bundle = new Bundle();
        if (intent != null) {
            bundle.putBundle("referrer_bundle", addReferrerData(intent));
        }
        getActivityNavigator().m4498b(bundle);
        finish();
    }

    protected void trackIntent(Intent intent, String str, EtsyRoute etsyRoute) {
        if (str.equals("external_url")) {
            Uri data = intent.getData();
            if (data == null || data.getScheme() == null) {
                EtsyGraphite.m1807a("route.unknown");
                return;
            }
            EtsyGraphite.m1807a("route." + data.getScheme());
            if (!ad.m3190a(data.getScheme())) {
                return;
            }
            if ("1".equals(data.getQueryParameter(ETSY_DEEPLINK_PARAM))) {
                EtsyEventTracker.m4600l();
                EtsyGraphite.m1807a("deeplink.custom");
            } else if ("1".equals(data.getQueryParameter(ETSY_DEFERRED_PARAM))) {
                EtsyEventTracker.m4598k();
                EtsyGraphite.m1807a("deeplink.custom.deferred");
            }
        } else if (str.equals("notifications")) {
            try {
                String str2;
                EtsyNotification a = NotificationsUtil.m2299a(NotificationType.fromString(intent.getExtras().getString(Constants.APPBOY_PUSH_TITLE_KEY)));
                if (etsyRoute == null) {
                    str2 = StringUtils.EMPTY;
                } else {
                    str2 = etsyRoute.m2275c().getName();
                }
                EventTracker.m2017a(a, str2);
                a.m2113g();
                EtsyGraphite.m1807a("route.notification");
            } catch (Exception e) {
                if (EtsyBuildHelper.m5709d()) {
                    throw e;
                }
                CrashUtil.m3037a().m3045a(new Throwable("Notification Intent extras are null. Source type: " + str + " Intent data: " + intent.getDataString()));
            }
        }
    }

    protected void trackYozioIntent(Intent intent) {
        if (intent.getData() != null) {
            EtsyGraphite.m1807a("deeplink.yozio");
            EtsyEventTracker.m4600l();
            return;
        }
        EtsyGraphite.m1807a("deeplink.yozio.deferred");
        EtsyEventTracker.m4598k();
    }

    private MutableSearchOptions getSearchOptions(EtsyRoute etsyRoute) {
        MutableSearchOptions mutableSearchOptions = null;
        if (etsyRoute.m2275c().equals(EtsyEntity.SEARCH)) {
            Object a = etsyRoute.m2272a(ResponseConstants.LOCATION);
            String a2 = etsyRoute.m2272a("ship_to");
            Object a3 = etsyRoute.m2272a(ResponseConstants.MIN_PRICE);
            Object a4 = etsyRoute.m2272a(ResponseConstants.MAX_PRICE);
            String a5 = etsyRoute.m2272a("marketplace");
            Object a6 = etsyRoute.m2272a(ListingShippingDetails.ETSY_ASAP);
            if (!(TextUtils.isEmpty(a) && TextUtils.isEmpty(a2) && TextUtils.isEmpty(a3) && TextUtils.isEmpty(a4) && TextUtils.isEmpty(a5) && TextUtils.isEmpty(a6))) {
                mutableSearchOptions = new MutableSearchOptions();
                if (!TextUtils.isEmpty(a)) {
                    mutableSearchOptions.m4775h().m4898a(2, decodeQueryParameter(a));
                }
                if (!TextUtils.isEmpty(a2)) {
                    mutableSearchOptions.m4760a(a2.toUpperCase(Locale.US), new Locale(Locale.getDefault().getLanguage(), a2).getDisplayCountry());
                }
                if (!TextUtils.isEmpty(a3)) {
                    mutableSearchOptions.m4757a(Integer.parseInt(a3));
                }
                if (!TextUtils.isEmpty(a4)) {
                    mutableSearchOptions.m4764b(Integer.parseInt(a4));
                }
                if (!TextUtils.isEmpty(a6)) {
                    mutableSearchOptions.m4766b(Boolean.valueOf(a6).booleanValue());
                }
                if (!TextUtils.isEmpty(a5)) {
                    mutableSearchOptions.m4768c(a5);
                }
            }
        }
        return mutableSearchOptions;
    }

    private TaxonomyNode getSearchTaxonomyNode(EtsyRoute etsyRoute) {
        if (!etsyRoute.m2275c().equals(EtsyEntity.SEARCH)) {
            return null;
        }
        String a = etsyRoute.m2272a(ResponseConstants.TAXONOMY_ID);
        if (TextUtils.isEmpty(a)) {
            return null;
        }
        return new TaxonomyNode(a, StringUtils.EMPTY);
    }

    private String decodeQueryParameter(String str) {
        try {
            str = URLDecoder.decode(str, com.adjust.sdk.Constants.ENCODING);
        } catch (UnsupportedEncodingException e) {
            EtsyLogger.m1966a().m1996b(TAG, "Couldn't decode query parameter: " + str);
        }
        return str;
    }
}
