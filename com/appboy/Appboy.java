package com.appboy;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Build.VERSION;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.ImageView;
import bo.app.C0333a;
import bo.app.C0334b;
import bo.app.C0335c;
import bo.app.C0337do;
import bo.app.C0340f;
import bo.app.C0341g;
import bo.app.C0347m;
import bo.app.C0350o;
import bo.app.ac;
import bo.app.ao;
import bo.app.ap;
import bo.app.aq;
import bo.app.ar;
import bo.app.as;
import bo.app.at;
import bo.app.au;
import bo.app.av;
import bo.app.aw;
import bo.app.ax;
import bo.app.ay;
import bo.app.az;
import bo.app.bc;
import bo.app.bd;
import bo.app.be;
import bo.app.bh;
import bo.app.bi;
import bo.app.bj;
import bo.app.bm;
import bo.app.br;
import bo.app.bs;
import bo.app.bv;
import bo.app.bw;
import bo.app.bx;
import bo.app.cd;
import bo.app.cj;
import bo.app.ck;
import bo.app.ct;
import bo.app.cy;
import bo.app.da;
import bo.app.dn;
import bo.app.du;
import bo.app.ea;
import bo.app.eu;
import bo.app.ew;
import bo.app.ey;
import bo.app.fb;
import bo.app.fg;
import bo.app.fj;
import bo.app.fk;
import bo.app.gc;
import bo.app.gd;
import bo.app.ge;
import bo.app.gg;
import bo.app.gh;
import bo.app.gi;
import bo.app.gm;
import bo.app.gn;
import bo.app.go;
import bo.app.gq;
import bo.app.hj;
import bo.app.ht;
import bo.app.ia;
import bo.app.ip;
import bo.app.ir;
import com.amazon.device.messaging.ADM;
import com.appboy.configuration.XmlAppConfigurationProvider;
import com.appboy.enums.SocialNetwork;
import com.appboy.enums.inappmessage.MessageType;
import com.appboy.events.FeedUpdatedEvent;
import com.appboy.events.IEventSubscriber;
import com.appboy.events.InAppMessageEvent;
import com.appboy.events.SubmitFeedbackFailed;
import com.appboy.events.SubmitFeedbackSucceeded;
import com.appboy.models.IInAppMessage;
import com.appboy.models.InAppMessageFull;
import com.appboy.models.InAppMessageHtmlFull;
import com.appboy.models.InAppMessageModal;
import com.appboy.models.InAppMessageSlideup;
import com.appboy.models.outgoing.AppboyProperties;
import com.appboy.models.outgoing.Feedback;
import com.appboy.support.AppboyLogger;
import com.appboy.support.PermissionUtils;
import com.appboy.support.ValidationUtils;
import com.etsy.android.lib.models.GiftCard;
import com.etsy.android.lib.models.finds.FindsModule;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

public final class Appboy implements IAppboy, IAppboyUnitySupport {
    private static volatile boolean f839A;
    private static final String f840e;
    private static final Set<String> f841f;
    private static final Set<String> f842g;
    private static volatile Appboy f843h;
    private static final Object f844x;
    private static volatile IAppboyEndpointProvider f845y;
    private static volatile IAppboyNotificationFactory f846z;
    private IAppboyNavigator f847B;
    volatile fk f848a;
    public volatile eu f849b;
    volatile ey f850c;
    final XmlAppConfigurationProvider f851d;
    private final Context f852i;
    private final gn f853j;
    private final az f854k;
    private volatile AppboyUser f855l;
    private volatile C0341g f856m;
    private volatile bc f857n;
    private volatile ThreadPoolExecutor f858o;
    private volatile bv f859p;
    private final C0347m f860q;
    private final cj f861r;
    private final bs f862s;
    private final cd f863t;
    private final br f864u;
    private final Object f865v;
    private final Object f866w;

    public static /* synthetic */ void m647a(ViewTreeObserver viewTreeObserver, OnGlobalLayoutListener onGlobalLayoutListener) {
        if (VERSION.SDK_INT < 16) {
            viewTreeObserver.removeGlobalOnLayoutListener(onGlobalLayoutListener);
        } else {
            viewTreeObserver.removeOnGlobalLayoutListener(onGlobalLayoutListener);
        }
    }

    public static /* synthetic */ void m649a(Appboy appboy) {
        int i = 0;
        int i2 = 1;
        for (String hasPermission : f842g) {
            int i3;
            if (PermissionUtils.hasPermission(appboy.f852i, hasPermission)) {
                i3 = i2;
            } else {
                AppboyLogger.m664e(f840e, String.format("The Appboy SDK requires the permission %s. Check your app manifest.", new Object[]{hasPermission}));
                i3 = 0;
            }
            i2 = i3;
        }
        if (appboy.f851d.getAppboyApiKey().toString().equals(StringUtils.EMPTY)) {
            AppboyLogger.m664e(f840e, "The Appboy SDK requires a non-empty API key. Check your appboy.xml.");
        } else {
            i = i2;
        }
        if (i == 0) {
            AppboyLogger.m664e(f840e, "The Appboy SDK is not integrated correctly. Please visit http://documentation.appboy.com/SDK_Integration/Android");
        }
    }

    static {
        f840e = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, Appboy.class.getName()});
        f841f = new HashSet(Arrays.asList(new String[]{"AED", "AFN", "ALL", "AMD", "ANG", "AOA", "ARS", "AUD", "AWG", "AZN", "BAM", "BBD", "BDT", "BGN", "BHD", "BIF", "BMD", "BND", "BOB", "BRL", "BSD", "BTC", "BTN", "BWP", "BYR", "BZD", GiftCard.CURRENCY_CAD, "CDF", "CHF", "CLF", "CLP", "CNY", "COP", "CRC", "CUC", "CUP", "CVE", "CZK", "DJF", "DKK", "DOP", "DZD", "EEK", "EGP", "ERN", "ETB", GiftCard.CURRENCY_EUR, "FJD", "FKP", GiftCard.CURRENCY_GBP, "GEL", "GGP", "GHS", "GIP", "GMD", "GNF", "GTQ", "GYD", "HKD", "HNL", "HRK", "HTG", "HUF", "IDR", "ILS", "IMP", "INR", "IQD", "IRR", "ISK", "JEP", "JMD", "JOD", "JPY", "KES", "KGS", "KHR", "KMF", "KPW", "KRW", "KWD", "KYD", "KZT", "LAK", "LBP", "LKR", "LRD", "LSL", "LTL", "LVL", "LYD", "MAD", "MDL", "MGA", "MKD", "MMK", "MNT", "MOP", "MRO", "MTL", "MUR", "MVR", "MWK", "MXN", "MYR", "MZN", "NAD", "NGN", "NIO", "NOK", "NPR", "NZD", "OMR", "PAB", "PEN", "PGK", "PHP", "PKR", "PLN", "PYG", "QAR", "RON", "RSD", "RUB", "RWF", "SAR", "SBD", "SCR", "SDG", "SEK", "SGD", "SHP", "SLL", "SOS", "SRD", "STD", "SVC", "SYP", "SZL", "THB", "TJS", "TMT", "TND", "TOP", "TRY", "TTD", "TWD", "TZS", "UAH", "UGX", GiftCard.CURRENCY_USD, "UYU", "UZS", "VEF", "VND", "VUV", "WST", "XAF", "XAG", "XAU", "XCD", "XDR", "XOF", "XPD", "XPF", "XPT", "YER", "ZAR", "ZMK", "ZMW", "ZWL"}));
        f842g = new HashSet(Arrays.asList(new String[]{"android.permission.ACCESS_NETWORK_STATE", "android.permission.INTERNET"}));
        f843h = null;
        f844x = new Object();
        f839A = false;
    }

    public static Appboy getInstance(Context context) {
        if (f843h == null) {
            synchronized (Appboy.class) {
                if (f843h == null) {
                    f843h = new Appboy(context);
                }
            }
        }
        return f843h;
    }

    private Appboy(Context context) {
        this.f865v = new Object();
        this.f866w = new Object();
        long nanoTime = System.nanoTime();
        this.f852i = context.getApplicationContext();
        this.f860q = new C0347m(this.f852i);
        this.f851d = new XmlAppConfigurationProvider(this.f852i);
        this.f862s = new bs(this.f852i);
        Executor threadPoolExecutor = new ThreadPoolExecutor(4, 8, 1, TimeUnit.SECONDS, new LinkedBlockingQueue(10));
        this.f854k = new az(threadPoolExecutor);
        int min = Math.min(((int) Runtime.getRuntime().maxMemory()) / 32, AccessibilityNodeInfoCompat.ACTION_DISMISS);
        String str = f840e;
        String.format("Setting maximum in-memory image cache size to %d bytes.", new Object[]{Integer.valueOf(min)});
        gq gqVar = new gq(this.f852i);
        if (!(gqVar.f617c == null && gqVar.f618d == null)) {
            ip.m568c("threadPoolSize(), threadPriority() and tasksProcessingOrder() calls can overlap taskExecutor() and taskExecutorForCachedImages() calls.", new Object[0]);
        }
        gqVar.f622h = 3;
        gqVar.f623i = true;
        gc geVar = new ge();
        if (gqVar.f629o != null) {
            ip.m568c("diskCache() and diskCacheFileNameGenerator() calls overlap each other", new Object[0]);
        }
        gqVar.f630p = geVar;
        if (gqVar.f629o != null) {
            ip.m568c("diskCache(), diskCacheSize() and diskCacheFileCount calls overlap each other", new Object[0]);
        }
        gqVar.f627m = 50;
        if (min <= 0) {
            throw new IllegalArgumentException("memoryCacheSize must be a positive number");
        }
        String gcmSenderId;
        if (gqVar.f628n != null) {
            ip.m568c("memoryCache() and memoryCacheSize() calls overlap each other", new Object[0]);
        }
        gqVar.f625k = min;
        min = hj.f717b;
        if (!(gqVar.f617c == null && gqVar.f618d == null)) {
            ip.m568c("threadPoolSize(), threadPriority() and tasksProcessingOrder() calls can overlap taskExecutor() and taskExecutorForCachedImages() calls.", new Object[0]);
        }
        gqVar.f624j = min;
        gm gmVar = new gm();
        gmVar.f577h = true;
        gmVar.f578i = true;
        gqVar.f633s = gmVar.m441a();
        if (gqVar.f617c == null) {
            gqVar.f617c = gi.m438a(gqVar.f621g, gqVar.f622h, gqVar.f624j);
        } else {
            gqVar.f619e = true;
        }
        if (gqVar.f618d == null) {
            gqVar.f618d = gi.m438a(gqVar.f621g, gqVar.f622h, gqVar.f624j);
        } else {
            gqVar.f620f = true;
        }
        if (gqVar.f629o == null) {
            if (gqVar.f630p == null) {
                gqVar.f630p = new gd();
            }
            gqVar.f629o = gi.m437a(gqVar.f616b, gqVar.f630p, gqVar.f626l, gqVar.f627m);
        }
        if (gqVar.f628n == null) {
            Context context2 = gqVar.f616b;
            min = gqVar.f625k;
            if (min == 0) {
                boolean z;
                ActivityManager activityManager = (ActivityManager) context2.getSystemService("activity");
                int memoryClass = activityManager.getMemoryClass();
                if (VERSION.SDK_INT >= 11) {
                    z = true;
                } else {
                    z = false;
                }
                if (z) {
                    if ((context2.getApplicationInfo().flags & AccessibilityNodeInfoCompat.ACTION_DISMISS) != 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (z) {
                        min = activityManager.getLargeMemoryClass();
                        min = (min * AccessibilityNodeInfoCompat.ACTION_DISMISS) / 8;
                    }
                }
                min = memoryClass;
                min = (min * AccessibilityNodeInfoCompat.ACTION_DISMISS) / 8;
            }
            gqVar.f628n = new gh(min);
        }
        if (gqVar.f623i) {
            gqVar.f628n = new gg(gqVar.f628n, new ir());
        }
        if (gqVar.f631q == null) {
            gqVar.f631q = new ia(gqVar.f616b);
        }
        if (gqVar.f632r == null) {
            gqVar.f632r = new ht(gqVar.f634t);
        }
        if (gqVar.f633s == null) {
            gqVar.f633s = new gm().m441a();
        }
        go goVar = new go((byte) 0);
        this.f853j = gn.m443a();
        this.f853j.m444a(goVar);
        this.f861r = new ck(this.f852i, this.f851d);
        if (cd.m108a(this.f852i, this.f851d)) {
            AppboyLogger.m666i(f840e, "Google Cloud Messaging found.  Setting up Google Cloud Messaging");
            this.f863t = new cd(this.f852i, this.f861r);
            gcmSenderId = this.f851d.getGcmSenderId();
            if (this.f851d.isGcmMessagingRegistrationEnabled() && gcmSenderId != null) {
                cd cdVar = this.f863t;
                String[] strArr = new String[]{gcmSenderId};
                if (cdVar.f235c.m110a() != null) {
                    AppboyLogger.m670w(cd.f233a, "The device is already registered with the GCM server and is eligible to receive GCM messages.");
                } else {
                    gcmSenderId = cd.f233a;
                    gcmSenderId = fj.m351a(strArr, ",");
                    Intent intent = new Intent("com.google.android.c2dm.intent.REGISTER");
                    intent.setPackage("com.google.android.gsf");
                    intent.putExtra("app", PendingIntent.getBroadcast(cdVar.f234b, 0, new Intent(), 0));
                    intent.putExtra("sender", gcmSenderId);
                    cdVar.f234b.startService(intent);
                }
            }
        } else {
            this.f863t = null;
        }
        if (!br.m38a(this.f852i) || cd.m108a(this.f852i, this.f851d)) {
            this.f864u = null;
        } else {
            AppboyLogger.m666i(f840e, "Amazon Device Messaging found.  Setting up Amazon Device Messaging");
            this.f864u = new br(this.f852i, this.f861r);
            br brVar = this.f864u;
            if (brVar.f165b.m110a() != null) {
                AppboyLogger.m666i(br.f163c, "The device is already registered with the ADM server and is eligible to receive ADM messages.");
                AppboyLogger.m666i(br.f163c, "ADM registration id: " + brVar.f165b.m110a());
                brVar.f165b.m111a(brVar.f165b.m110a());
            } else {
                ADM adm = new ADM(brVar.f164a);
                if (adm.isSupported()) {
                    AppboyLogger.m666i(br.f163c, "Registering with ADM server...");
                    adm.startRegister();
                }
            }
        }
        if (this.f863t == null && this.f864u == null) {
            AppboyLogger.m664e(f840e, "Did not find support for Google Cloud Messaging or Amazon Device Messaging");
        }
        fk fkVar = new fk(this.f852i, this.f860q, this.f851d, this.f854k, this.f862s, this.f861r, f839A);
        m648a(fkVar);
        bw a = bw.m65a();
        Executor executor = this.f858o;
        ew ewVar = fkVar.f457i;
        C0350o c0350o = fkVar.f452d;
        bv bvVar = fkVar.f453e;
        if (a.f192b) {
            gcmSenderId = bw.f190a;
        } else {
            executor.execute(new bx(a, ewVar, c0350o, bvVar));
        }
        threadPoolExecutor.execute(new C0333a(this));
        long nanoTime2 = System.nanoTime();
        str = f840e;
        String.format("Appboy loaded in %d ms.", new Object[]{Long.valueOf(TimeUnit.MILLISECONDS.convert(nanoTime2 - nanoTime, TimeUnit.NANOSECONDS))});
    }

    private void m648a(fk fkVar) {
        synchronized (this.f865v) {
            synchronized (this.f866w) {
                this.f848a = fkVar;
                this.f856m = fkVar.f458j;
                this.f859p = fkVar.f453e;
                this.f850c = fkVar.f460l;
                this.f855l = new AppboyUser(fkVar.f449a, fkVar.f453e, this.f860q.m613a(), fkVar.f459k, this.f850c);
                ao aoVar = fkVar.f455g;
                az azVar = fkVar.f451c;
                azVar.m35a(new ar(aoVar), bd.class);
                azVar.m35a(new av(aoVar), bi.class);
                azVar.m35a(new aw(aoVar), bj.class);
                azVar.m35a(new ax(aoVar), bh.class);
                azVar.m35a(new ay(aoVar), Throwable.class);
                azVar.m35a(new aq(aoVar), bm.class);
                azVar.m35a(new ap(aoVar), be.class);
                azVar.m35a(new au(aoVar), du.class);
                azVar.m35a(new as(aoVar), C0337do.class);
                azVar.m35a(new at(aoVar), dn.class);
                C0350o c0350o = fkVar.f452d;
                synchronized (c0350o.f804e) {
                    if (c0350o.f805f) {
                        String str = C0350o.f800a;
                    } else {
                        if (c0350o.f806g != null) {
                            c0350o.f806g.start();
                        }
                        c0350o.f805f = true;
                    }
                }
                this.f857n = fkVar.f451c;
                this.f858o = fkVar.f456h;
                this.f849b = fkVar.f454f;
            }
        }
    }

    public final boolean openSession(Activity activity) {
        boolean z = false;
        synchronized (this.f866w) {
            try {
                cy c = this.f859p.f180b.m80c();
                bv bvVar = this.f859p;
                ct a = bvVar.m59a();
                bvVar.f185g = activity.getClass();
                if (!a.f257d.equals(c)) {
                    z = true;
                }
            } catch (Throwable e) {
                AppboyLogger.m671w(f840e, "Failed to open session.", e);
                m650a(e);
            }
        }
        return z;
    }

    public final boolean closeSession(Activity activity) {
        boolean z = false;
        synchronized (this.f866w) {
            try {
                ct b;
                bv bvVar = this.f859p;
                if (bvVar.f185g == null || activity.getClass().equals(bvVar.f185g)) {
                    b = bvVar.f180b.m79b();
                } else {
                    String str = bv.f179a;
                    b = null;
                }
                if (b != null) {
                    AppboyLogger.m666i(f840e, "Closed session with ID: " + b.f257d);
                    z = true;
                }
            } catch (Throwable e) {
                AppboyLogger.m671w(f840e, "Failed to close session.", e);
                m650a(e);
            }
        }
        return z;
    }

    public final boolean submitFeedback(String str, String str2, boolean z) {
        boolean z2;
        synchronized (this.f866w) {
            try {
                bv bvVar = this.f859p;
                if (str == null || !ValidationUtils.isValidEmailAddress(str)) {
                    throw new IllegalArgumentException("Reply to email address is invalid");
                } else if (fj.m354c(str2)) {
                    throw new IllegalArgumentException("Feedback message cannot be null or blank");
                } else {
                    bvVar.f181c.m615a(new ea(bvVar.f183e.getBaseUrlForRequests(), new Feedback(str2, str, z, bvVar.f182d.m93d(), bvVar.f182d.m91b())));
                    z2 = true;
                }
            } catch (Throwable e) {
                AppboyLogger.m671w(f840e, "Failed to submit feedback: " + str2, e);
                m650a(e);
                z2 = false;
            }
        }
        return z2;
    }

    public final boolean logCustomEvent(String str) {
        boolean logCustomEvent;
        synchronized (this.f866w) {
            logCustomEvent = logCustomEvent(str, null);
        }
        return logCustomEvent;
    }

    public final boolean logCustomEvent(String str, AppboyProperties appboyProperties) {
        boolean z = false;
        synchronized (this.f866w) {
            try {
                if (fj.m354c(str)) {
                    AppboyLogger.m670w(f840e, "The custom event name cannot be null or contain only whitespaces. Ignoring custom event.");
                } else if (this.f850c.m301g().contains(str)) {
                    AppboyLogger.m670w(f840e, String.format("The custom event is a blacklisted custom event: %s. Ignoring custom event.", new Object[]{str}));
                } else {
                    str = ValidationUtils.ensureAppboyFieldLength(str);
                    z = this.f859p.m62a(da.m153a(str, appboyProperties));
                }
            } catch (Throwable e) {
                AppboyLogger.m671w(f840e, "Failed to log custom event: " + str, e);
                m650a(e);
            }
        }
        return z;
    }

    public final boolean logPurchase(String str, int i) {
        boolean z = false;
        synchronized (this.f866w) {
            try {
                if (fj.m354c(str)) {
                    AppboyLogger.m670w(f840e, "The productId is empty, not logging in-app purchase to Appboy.");
                } else if (this.f850c.m303i().contains(str)) {
                    AppboyLogger.m670w(f840e, String.format("The productId is a blacklisted productId: %s, not logging in-app purchase to Appboy.", new Object[]{str}));
                } else if (i < 0) {
                    AppboyLogger.m670w(f840e, "The price is less than zero, not logging in-app purchase to Appboy.");
                } else {
                    str = ValidationUtils.ensureAppboyFieldLength(str);
                    z = this.f859p.m62a(da.m158a(str, GiftCard.CURRENCY_USD, fb.m327a(i), 1, null));
                }
            } catch (Throwable e) {
                AppboyLogger.m671w(f840e, "Failed to log purchase event of " + str, e);
                m650a(e);
            }
        }
        return z;
    }

    public final boolean logPurchase(String str, String str2, BigDecimal bigDecimal) {
        return logPurchase(str, str2, bigDecimal, 1);
    }

    public final boolean logPurchase(String str, String str2, BigDecimal bigDecimal, AppboyProperties appboyProperties) {
        return logPurchase(str, str2, bigDecimal, 1, appboyProperties);
    }

    public final boolean logPurchase(String str, String str2, BigDecimal bigDecimal, int i) {
        return logPurchase(str, str2, bigDecimal, i, null);
    }

    public final boolean logPurchase(String str, String str2, BigDecimal bigDecimal, int i, AppboyProperties appboyProperties) {
        boolean z = false;
        synchronized (this.f866w) {
            try {
                if (fj.m354c(str)) {
                    AppboyLogger.m670w(f840e, "The productId is empty, not logging in-app purchase to Appboy.");
                } else if (this.f850c.m303i().contains(str)) {
                    AppboyLogger.m670w(f840e, String.format("The productId is a blacklisted productId: %s, not logging in-app purchase to Appboy.", new Object[]{str}));
                } else if (str2 == null) {
                    AppboyLogger.m670w(f840e, String.format("The currencyCode is null. Expected one of %s. Not logging in-app purchase to Appboy.", new Object[]{f841f}));
                } else {
                    String toUpperCase = str2.trim().toUpperCase(Locale.US);
                    if (!f841f.contains(toUpperCase)) {
                        AppboyLogger.m670w(f840e, String.format("The currencyCode is invalid. Expected one of %s. Not logging in-app purchase to Appboy.", new Object[]{f841f}));
                    } else if (bigDecimal == null) {
                        AppboyLogger.m670w(f840e, "The price is null. Not logging in-app purchase to Appboy.");
                    } else if (bigDecimal.compareTo(BigDecimal.ZERO) == -1) {
                        AppboyLogger.m670w(f840e, "The price is negative. Not logging in-app purchase to Appboy.");
                    } else if (i <= 0) {
                        AppboyLogger.m670w(f840e, "The requested purchase quantity is less than zero. Not logging in-app purchase to Appboy.");
                    } else if (i > 100) {
                        AppboyLogger.m670w(f840e, "The requested purchase quantity is greater than the maximum of 100. Not logging in-app purchase to Appboy.");
                    } else {
                        str = ValidationUtils.ensureAppboyFieldLength(str);
                        z = this.f859p.m62a(da.m158a(str, toUpperCase, bigDecimal, i, appboyProperties));
                    }
                }
            } catch (Throwable e) {
                AppboyLogger.m671w(f840e, "Failed to log purchase event of " + str, e);
                m650a(e);
            }
        }
        return z;
    }

    public final boolean logShare(SocialNetwork socialNetwork) {
        return false;
    }

    public final boolean logPushNotificationOpened(String str) {
        boolean z = false;
        try {
            if (fj.m354c(str)) {
                AppboyLogger.m670w(f840e, "Campaign ID cannot be null or blank");
            } else {
                z = this.f859p.m62a(da.m164b(str));
            }
        } catch (Throwable e) {
            AppboyLogger.m671w(f840e, "Failed to log opened push.", e);
            m650a(e);
        }
        return z;
    }

    public final boolean logPushNotificationActionClicked(String str, String str2) {
        boolean z = false;
        try {
            if (fj.m354c(str)) {
                AppboyLogger.m670w(f840e, "Campaign ID cannot be null or blank");
            } else if (fj.m354c(str2)) {
                AppboyLogger.m670w(f840e, "Action ID cannot be null or blank");
            } else {
                z = this.f859p.m62a(da.m165b(str, str2));
            }
        } catch (Throwable e) {
            AppboyLogger.m671w(f840e, "Failed to log push notification action clicked.", e);
            m650a(e);
        }
        return z;
    }

    public final boolean logFeedDisplayed() {
        try {
            return this.f859p.m62a(da.m172e());
        } catch (Throwable e) {
            AppboyLogger.m671w(f840e, "Failed to log that the feed was displayed.", e);
            m650a(e);
            return false;
        }
    }

    public final boolean logFeedCardImpression(String str) {
        try {
            if (fj.m354c(str)) {
                AppboyLogger.m664e(f840e, "Card ID cannot be null");
                return false;
            }
            boolean a = this.f859p.m62a(da.m168c(str));
            this.f849b.m277a(str);
            return a;
        } catch (Throwable e) {
            AppboyLogger.m671w(f840e, "Failed to log feed card impression.", e);
            m650a(e);
            return false;
        }
    }

    public final boolean logFeedCardClick(String str) {
        boolean z = false;
        try {
            if (fj.m354c(str)) {
                AppboyLogger.m664e(f840e, "Card ID cannot be null");
            } else {
                z = this.f859p.m62a(da.m170d(str));
            }
        } catch (Throwable e) {
            AppboyLogger.m671w(f840e, "Failed to log feed card clicked.", e);
            m650a(e);
        }
        return z;
    }

    public final boolean logFeedbackDisplayed() {
        try {
            return this.f859p.m62a(da.m175f());
        } catch (Throwable e) {
            AppboyLogger.m671w(f840e, "Failed to log that feedback was displayed.", e);
            m650a(e);
            return false;
        }
    }

    public final void requestFeedRefreshFromCache() {
        this.f858o.execute(new C0334b(this));
    }

    public final void requestFeedRefresh() {
        synchronized (this.f866w) {
            try {
                this.f859p.m60a(ac.f15a);
            } catch (Throwable e) {
                AppboyLogger.m671w(f840e, "Failed to request refresh of feed.", e);
                m650a(e);
            }
        }
    }

    public final void requestInAppMessageRefresh() {
        synchronized (this.f866w) {
            try {
                this.f859p.m60a(ac.f16b);
            } catch (Throwable e) {
                AppboyLogger.m671w(f840e, "Failed to request in-app message refresh.", e);
                m650a(e);
            }
        }
    }

    public final void requestSlideupRefresh() {
        synchronized (this.f866w) {
            AppboyLogger.m670w(f840e, "Deprecated method IAppboy#requestSlideupRefresh() called. Use IAppboy#requestInAppMessageRefresh() instead.");
            try {
                this.f859p.m60a(ac.f16b);
            } catch (Throwable e) {
                AppboyLogger.m671w(f840e, "Failed to request in-app message refresh from requestSlideupRefresh().", e);
                m650a(e);
            }
        }
    }

    public final void requestImmediateDataFlush() {
        synchronized (this.f866w) {
            try {
                this.f859p.m60a(ac.f18d);
            } catch (Throwable e) {
                AppboyLogger.m671w(f840e, "Failed to request data flush.", e);
                m650a(e);
            }
        }
    }

    public final void subscribeToNewInAppMessages(IEventSubscriber<InAppMessageEvent> iEventSubscriber) {
        try {
            this.f854k.m35a((IEventSubscriber) iEventSubscriber, InAppMessageEvent.class);
        } catch (Throwable e) {
            AppboyLogger.m671w(f840e, "Failed to add subscriber to new in-app messages.", e);
            m650a(e);
        }
    }

    public final void subscribeToFeedUpdates(IEventSubscriber<FeedUpdatedEvent> iEventSubscriber) {
        try {
            this.f854k.m35a((IEventSubscriber) iEventSubscriber, FeedUpdatedEvent.class);
        } catch (Throwable e) {
            AppboyLogger.m671w(f840e, "Failed to add subscriber for feed updates.", e);
            m650a(e);
        }
    }

    public final void subscribeToFeedbackRequestEvents(IEventSubscriber<SubmitFeedbackSucceeded> iEventSubscriber, IEventSubscriber<SubmitFeedbackFailed> iEventSubscriber2) {
        if (iEventSubscriber != null) {
            try {
                this.f854k.m35a((IEventSubscriber) iEventSubscriber, SubmitFeedbackSucceeded.class);
            } catch (Throwable e) {
                AppboyLogger.m671w(f840e, "Failed to add subscribers for feedback request events.", e);
                m650a(e);
                return;
            }
        }
        if (iEventSubscriber2 != null) {
            this.f854k.m35a((IEventSubscriber) iEventSubscriber2, SubmitFeedbackFailed.class);
        }
    }

    public final <T> void removeSingleSubscription(IEventSubscriber<T> iEventSubscriber, Class<T> cls) {
        try {
            this.f854k.m36b((IEventSubscriber) iEventSubscriber, (Class) cls);
        } catch (Throwable e) {
            AppboyLogger.m671w(f840e, "Failed to remove " + cls.getName() + " subscriber.", e);
            m650a(e);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.appboy.AppboyUser changeUser(java.lang.String r13) {
        /*
        r12 = this;
        r8 = r12.f865v;
        monitor-enter(r8);
        r0 = bo.app.fj.m353b(r13);	 Catch:{ Exception -> 0x0097 }
        if (r0 == 0) goto L_0x0014;
    L_0x0009:
        r0 = f840e;	 Catch:{ Exception -> 0x0097 }
        r1 = "ArgumentException: userId passed to changeUser was null or empty.  The current user will remain the active user.";
        com.appboy.support.AppboyLogger.m664e(r0, r1);	 Catch:{ Exception -> 0x0097 }
        r0 = r12.f855l;	 Catch:{ Exception -> 0x0097 }
        monitor-exit(r8);	 Catch:{ all -> 0x00b0 }
    L_0x0013:
        return r0;
    L_0x0014:
        r0 = r12.f855l;	 Catch:{ Exception -> 0x0097 }
        r9 = r0.getUserId();	 Catch:{ Exception -> 0x0097 }
        r0 = r9.equals(r13);	 Catch:{ Exception -> 0x0097 }
        if (r0 == 0) goto L_0x0035;
    L_0x0020:
        r0 = f840e;	 Catch:{ Exception -> 0x0097 }
        r1 = "Received request to change current user %s to the same user id. Doing nothing.";
        r2 = 1;
        r2 = new java.lang.Object[r2];	 Catch:{ Exception -> 0x0097 }
        r3 = 0;
        r2[r3] = r13;	 Catch:{ Exception -> 0x0097 }
        r1 = java.lang.String.format(r1, r2);	 Catch:{ Exception -> 0x0097 }
        com.appboy.support.AppboyLogger.m666i(r0, r1);	 Catch:{ Exception -> 0x0097 }
    L_0x0031:
        monitor-exit(r8);	 Catch:{ all -> 0x00b0 }
        r0 = r12.f855l;
        goto L_0x0013;
    L_0x0035:
        r0 = "";
        r10 = r9.equals(r0);	 Catch:{ Exception -> 0x0097 }
        if (r10 == 0) goto L_0x0119;
    L_0x003d:
        r0 = f840e;	 Catch:{ Exception -> 0x0097 }
        r1 = "Changing anonymous user to %s.";
        r2 = 1;
        r2 = new java.lang.Object[r2];	 Catch:{ Exception -> 0x0097 }
        r3 = 0;
        r2[r3] = r13;	 Catch:{ Exception -> 0x0097 }
        r1 = java.lang.String.format(r1, r2);	 Catch:{ Exception -> 0x0097 }
        com.appboy.support.AppboyLogger.m666i(r0, r1);	 Catch:{ Exception -> 0x0097 }
        r0 = r12.f860q;	 Catch:{ Exception -> 0x0097 }
        bo.app.fj.m349a(r13);	 Catch:{ Exception -> 0x0097 }
        r0 = r0.f796a;	 Catch:{ Exception -> 0x0097 }
        r0 = r0.edit();	 Catch:{ Exception -> 0x0097 }
        r1 = "default_user";
        r0.putString(r1, r13);	 Catch:{ Exception -> 0x0097 }
        r1 = "last_user";
        r0.putString(r1, r13);	 Catch:{ Exception -> 0x0097 }
        r0.commit();	 Catch:{ Exception -> 0x0097 }
        r0 = r12.f855l;	 Catch:{ Exception -> 0x0097 }
        r1 = r0.f868a;	 Catch:{ Exception -> 0x0097 }
        monitor-enter(r1);	 Catch:{ Exception -> 0x0097 }
        r2 = r0.f869b;	 Catch:{ all -> 0x0094 }
        r3 = "";
        r2 = r2.equals(r3);	 Catch:{ all -> 0x0094 }
        if (r2 != 0) goto L_0x00b3;
    L_0x0075:
        r2 = r0.f869b;	 Catch:{ all -> 0x0094 }
        r2 = r2.equals(r13);	 Catch:{ all -> 0x0094 }
        if (r2 != 0) goto L_0x00b3;
    L_0x007d:
        r2 = new java.lang.IllegalArgumentException;	 Catch:{ all -> 0x0094 }
        r3 = "setExternalId can not be used to change the external ID of a UserCache from a non-empty value to a new value. Was: [%s], tried to change to: [%s]";
        r4 = 2;
        r4 = new java.lang.Object[r4];	 Catch:{ all -> 0x0094 }
        r5 = 0;
        r0 = r0.f869b;	 Catch:{ all -> 0x0094 }
        r4[r5] = r0;	 Catch:{ all -> 0x0094 }
        r0 = 1;
        r4[r0] = r13;	 Catch:{ all -> 0x0094 }
        r0 = java.lang.String.format(r3, r4);	 Catch:{ all -> 0x0094 }
        r2.<init>(r0);	 Catch:{ all -> 0x0094 }
        throw r2;	 Catch:{ all -> 0x0094 }
    L_0x0094:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ Exception -> 0x0097 }
        throw r0;	 Catch:{ Exception -> 0x0097 }
    L_0x0097:
        r0 = move-exception;
        r1 = f840e;	 Catch:{ all -> 0x00b0 }
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00b0 }
        r3 = "Failed to set external id to: ";
        r2.<init>(r3);	 Catch:{ all -> 0x00b0 }
        r2 = r2.append(r13);	 Catch:{ all -> 0x00b0 }
        r2 = r2.toString();	 Catch:{ all -> 0x00b0 }
        com.appboy.support.AppboyLogger.m671w(r1, r2, r0);	 Catch:{ all -> 0x00b0 }
        r12.m650a(r0);	 Catch:{ all -> 0x00b0 }
        goto L_0x0031;
    L_0x00b0:
        r0 = move-exception;
        monitor-exit(r8);
        throw r0;
    L_0x00b3:
        r0.f869b = r13;	 Catch:{ all -> 0x0094 }
        monitor-exit(r1);	 Catch:{ all -> 0x0094 }
        r0 = r12.f856m;	 Catch:{ Exception -> 0x0097 }
        r0.f525a = r13;	 Catch:{ Exception -> 0x0097 }
    L_0x00ba:
        r0 = r12.f859p;	 Catch:{ Exception -> 0x0097 }
        r1 = 0;
        r0.f185g = r1;	 Catch:{ Exception -> 0x0097 }
        r0 = r0.f180b;	 Catch:{ Exception -> 0x0097 }
        r0.m82e();	 Catch:{ Exception -> 0x0097 }
        r0 = r12.f860q;	 Catch:{ Exception -> 0x0097 }
        bo.app.fj.m349a(r13);	 Catch:{ Exception -> 0x0097 }
        r0 = r0.f796a;	 Catch:{ Exception -> 0x0097 }
        r0 = r0.edit();	 Catch:{ Exception -> 0x0097 }
        r1 = "last_user";
        r0.putString(r1, r13);	 Catch:{ Exception -> 0x0097 }
        r0.commit();	 Catch:{ Exception -> 0x0097 }
        r11 = r12.f848a;	 Catch:{ Exception -> 0x0097 }
        r0 = new bo.app.fk;	 Catch:{ Exception -> 0x0097 }
        r1 = r12.f852i;	 Catch:{ Exception -> 0x0097 }
        r2 = r12.f860q;	 Catch:{ Exception -> 0x0097 }
        r3 = r12.f851d;	 Catch:{ Exception -> 0x0097 }
        r4 = r12.f854k;	 Catch:{ Exception -> 0x0097 }
        r5 = r12.f862s;	 Catch:{ Exception -> 0x0097 }
        r6 = r12.f861r;	 Catch:{ Exception -> 0x0097 }
        r7 = f839A;	 Catch:{ Exception -> 0x0097 }
        r0.<init>(r1, r2, r3, r4, r5, r6, r7);	 Catch:{ Exception -> 0x0097 }
        r12.m648a(r0);	 Catch:{ Exception -> 0x0097 }
        r0 = r12.f848a;	 Catch:{ Exception -> 0x0097 }
        r0 = r0.f450b;	 Catch:{ Exception -> 0x0097 }
        r0.m266c();	 Catch:{ Exception -> 0x0097 }
        r0 = r12.f859p;	 Catch:{ Exception -> 0x0097 }
        r0.m59a();	 Catch:{ Exception -> 0x0097 }
        if (r10 == 0) goto L_0x0146;
    L_0x00fd:
        r0 = r12.f859p;	 Catch:{ Exception -> 0x0097 }
        r1 = bo.app.da.m151a(r13);	 Catch:{ Exception -> 0x0097 }
        r0.m62a(r1);	 Catch:{ Exception -> 0x0097 }
    L_0x0106:
        r0 = r12.f859p;	 Catch:{ Exception -> 0x0097 }
        r1 = bo.app.ac.f15a;	 Catch:{ Exception -> 0x0097 }
        r0.m60a(r1);	 Catch:{ Exception -> 0x0097 }
        r0 = r11.f456h;	 Catch:{ Exception -> 0x0097 }
        r1 = new bo.app.fl;	 Catch:{ Exception -> 0x0097 }
        r1.<init>(r11);	 Catch:{ Exception -> 0x0097 }
        r0.execute(r1);	 Catch:{ Exception -> 0x0097 }
        goto L_0x0031;
    L_0x0119:
        r0 = f840e;	 Catch:{ Exception -> 0x0097 }
        r1 = "Changing current user %s to new user %s.";
        r2 = 2;
        r2 = new java.lang.Object[r2];	 Catch:{ Exception -> 0x0097 }
        r3 = 0;
        r2[r3] = r9;	 Catch:{ Exception -> 0x0097 }
        r3 = 1;
        r2[r3] = r13;	 Catch:{ Exception -> 0x0097 }
        r1 = java.lang.String.format(r1, r2);	 Catch:{ Exception -> 0x0097 }
        com.appboy.support.AppboyLogger.m666i(r0, r1);	 Catch:{ Exception -> 0x0097 }
        r0 = new com.appboy.events.FeedUpdatedEvent;	 Catch:{ Exception -> 0x0097 }
        r1 = new java.util.ArrayList;	 Catch:{ Exception -> 0x0097 }
        r1.<init>();	 Catch:{ Exception -> 0x0097 }
        r3 = 0;
        r4 = bo.app.fd.m330a();	 Catch:{ Exception -> 0x0097 }
        r2 = r13;
        r0.<init>(r1, r2, r3, r4);	 Catch:{ Exception -> 0x0097 }
        r1 = r12.f854k;	 Catch:{ Exception -> 0x0097 }
        r2 = com.appboy.events.FeedUpdatedEvent.class;
        r1.m34a(r0, r2);	 Catch:{ Exception -> 0x0097 }
        goto L_0x00ba;
    L_0x0146:
        r0 = r12.f859p;	 Catch:{ Exception -> 0x0097 }
        r1 = bo.app.da.m154a(r9, r13);	 Catch:{ Exception -> 0x0097 }
        r0.m62a(r1);	 Catch:{ Exception -> 0x0097 }
        goto L_0x0106;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appboy.Appboy.changeUser(java.lang.String):com.appboy.AppboyUser");
    }

    public final AppboyUser getCurrentUser() {
        AppboyUser appboyUser;
        synchronized (this.f865v) {
            appboyUser = this.f855l;
        }
        return appboyUser;
    }

    public final void registerAppboyGcmMessages(String str) {
        registerAppboyPushMessages(str);
    }

    public final void registerAppboyPushMessages(String str) {
        try {
            if (fj.m354c(str)) {
                AppboyLogger.m670w(f840e, "Push registration ID must not be null or blank. Not registering for push messages from Appboy.");
            } else {
                this.f861r.m111a(str);
            }
        } catch (Throwable e) {
            AppboyLogger.m671w(f840e, "Failed to set the registration ID.", e);
            m650a(e);
        }
    }

    public final void unregisterAppboyPushMessages() {
        try {
            this.f861r.m112b();
        } catch (Throwable e) {
            AppboyLogger.m671w(f840e, "Failed to unset the registration ID.", e);
            m650a(e);
        }
    }

    public final String getAppboyPushMessageRegistrationId() {
        try {
            return this.f861r.m110a();
        } catch (Throwable e) {
            AppboyLogger.m671w(f840e, "Failed to get the registration ID.", e);
            m650a(e);
            return null;
        }
    }

    public final String getInstallTrackingId() {
        return this.f862s.m43b();
    }

    public final void fetchAndRenderImage(String str, ImageView imageView) {
        fetchAndRenderImage(str, imageView, false);
    }

    public final void fetchAndRenderImage(String str, ImageView imageView, boolean z) {
        imageView.post(new C0335c(this, str, imageView, z));
    }

    public final IAppboyNavigator getAppboyNavigator() {
        return this.f847B;
    }

    public final void setAppboyNavigator(IAppboyNavigator iAppboyNavigator) {
        this.f847B = iAppboyNavigator;
    }

    private void m650a(Throwable th) {
        try {
            this.f857n.m31a(th, Throwable.class);
        } catch (Throwable e) {
            AppboyLogger.m665e(f840e, "Failed to log throwable.", e);
        }
    }

    public final IInAppMessage deserializeInAppMessageString(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            MessageType messageType = (MessageType) fg.m339a(jSONObject, FindsModule.FIELD_TYPE, MessageType.class, null);
            if (messageType == null) {
                AppboyLogger.m666i(f840e, "In-app message type was null.  Not de-serializing message string: " + str);
                return null;
            }
            switch (C0340f.f435a[messageType.ordinal()]) {
                case Task.NETWORK_STATE_UNMETERED /*1*/:
                    return new InAppMessageFull(jSONObject, this.f859p);
                case Task.NETWORK_STATE_ANY /*2*/:
                    return new InAppMessageModal(jSONObject, this.f859p);
                case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                    return new InAppMessageSlideup(jSONObject, this.f859p);
                case CommonStatusCodes.SIGN_IN_REQUIRED /*4*/:
                    return new InAppMessageHtmlFull(jSONObject, this.f859p);
                default:
                    AppboyLogger.m664e(f840e, "Unknown in-app message type.  Not de-serializing message string: " + str);
                    return null;
            }
            AppboyLogger.m665e(f840e, "Failed to deserialize the in-app message string." + str, e);
            return null;
        } catch (Throwable e) {
            AppboyLogger.m665e(f840e, "Failed to deserialize the in-app message string." + str, e);
            return null;
        }
    }

    public static void setAppboyEndpointProvider(IAppboyEndpointProvider iAppboyEndpointProvider) {
        synchronized (f844x) {
            f845y = iAppboyEndpointProvider;
        }
    }

    public static void clearAppboyEndpointProvider() {
        synchronized (f844x) {
            f845y = null;
        }
    }

    public static Uri getAppboyApiEndpoint(Uri uri) {
        synchronized (f844x) {
            if (f845y != null) {
                try {
                    Uri apiEndpoint = f845y.getApiEndpoint(uri);
                    if (apiEndpoint != null) {
                        return apiEndpoint;
                    }
                } catch (Exception e) {
                    AppboyLogger.m664e(f840e, "Caught exception trying to get an Appboy API endpoint from the AppboyEndpointProvider.  Using the original URI");
                }
            }
            return uri;
        }
    }

    public static Uri getAppboyResourceEndpoint(Uri uri) {
        synchronized (f844x) {
            if (f845y != null) {
                try {
                    Uri resourceEndpoint = f845y.getResourceEndpoint(uri);
                    if (resourceEndpoint != null) {
                        return resourceEndpoint;
                    }
                } catch (Exception e) {
                    AppboyLogger.m664e(f840e, "Caught exception trying to get an Appboy resource endpoint from the AppboyEndpointProvider.  Using the original URI");
                }
            }
            return uri;
        }
    }

    public static void setCustomAppboyNotificationFactory(IAppboyNotificationFactory iAppboyNotificationFactory) {
        f846z = iAppboyNotificationFactory;
    }

    public static IAppboyNotificationFactory getCustomAppboyNotificationFactory() {
        return f846z;
    }

    public static boolean configure(Context context, String str) {
        if (f843h == null) {
            synchronized (Appboy.class) {
                if (f843h == null) {
                    Editor a;
                    if (str == null) {
                        AppboyLogger.m666i(f840e, "Appboy.configure called with a null appboyApiKey; unsetting any cached override api key.");
                        a = C0347m.m612a(context);
                        a.remove("com_appboy_api_key");
                        a.apply();
                    } else if (str.equals(StringUtils.EMPTY)) {
                        AppboyLogger.m664e(f840e, "Appboy.configure called with an empty string; no action will be taken.  Configure with null to clear an override api key.");
                    } else {
                        AppboyLogger.m666i(f840e, "Appboy.configure called with an appboyApiKey; caching the api key to override the appboy.xml value when initializing Appboy.");
                        a = C0347m.m612a(context);
                        a.putString("com_appboy_api_key", str);
                        a.apply();
                    }
                    return true;
                }
            }
        }
        AppboyLogger.m664e(f840e, "The custom Appboy API key was not set by configure since getInstance() has already been called.");
        return false;
    }

    public static boolean disableAllAppboyNetworkRequests() {
        if (f843h == null) {
            synchronized (Appboy.class) {
                if (f843h != null) {
                } else if (f839A) {
                    AppboyLogger.m666i(f840e, "Appboy network requests already disabled.");
                    return true;
                } else {
                    AppboyLogger.m666i(f840e, "Appboy network requests now disabled.");
                    f839A = true;
                    return true;
                }
            }
        }
        AppboyLogger.m664e(f840e, "Attempt to disable network requests will have no effect since getInstance() has already been called.");
        return false;
    }
}
