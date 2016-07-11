package com.facebook.internal;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.etsy.android.lib.models.cardviewelement.BaseMessage;
import com.facebook.FacebookException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.FacebookSdk;
import com.facebook.login.DefaultAudience;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: NativeProtocol */
public final class ah {
    private static final ak f4310a;
    private static List<ak> f4311b;
    private static Map<String, List<ak>> f4312c;
    private static AtomicBoolean f4313d;
    private static final List<Integer> f4314e;

    static {
        f4310a = new ai(null);
        f4311b = m5766e();
        f4312c = m5767f();
        f4313d = new AtomicBoolean(false);
        f4314e = Arrays.asList(new Integer[]{Integer.valueOf(20141218), Integer.valueOf(20141107), Integer.valueOf(20141028), Integer.valueOf(20141001), Integer.valueOf(20140701), Integer.valueOf(20140324), Integer.valueOf(20140204), Integer.valueOf(20131107), Integer.valueOf(20130618), Integer.valueOf(20130502), Integer.valueOf(20121101)});
    }

    private static List<ak> m5766e() {
        List<ak> arrayList = new ArrayList();
        arrayList.add(f4310a);
        arrayList.add(new al(null));
        return arrayList;
    }

    private static Map<String, List<ak>> m5767f() {
        Map<String, List<ak>> hashMap = new HashMap();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new aj(null));
        hashMap.put("com.facebook.platform.action.request.OGACTIONPUBLISH_DIALOG", f4311b);
        hashMap.put("com.facebook.platform.action.request.FEED_DIALOG", f4311b);
        hashMap.put("com.facebook.platform.action.request.LIKE_DIALOG", f4311b);
        hashMap.put("com.facebook.platform.action.request.APPINVITES_DIALOG", f4311b);
        hashMap.put("com.facebook.platform.action.request.MESSAGE_DIALOG", arrayList);
        hashMap.put("com.facebook.platform.action.request.OGMESSAGEPUBLISH_DIALOG", arrayList);
        return hashMap;
    }

    static Intent m5745a(Context context, Intent intent, ak akVar) {
        if (intent == null) {
            return null;
        }
        ResolveInfo resolveActivity = context.getPackageManager().resolveActivity(intent, 0);
        if (resolveActivity == null) {
            return null;
        }
        if (akVar.a(context, resolveActivity.activityInfo.packageName)) {
            return intent;
        }
        return null;
    }

    static Intent m5756b(Context context, Intent intent, ak akVar) {
        if (intent == null) {
            return null;
        }
        ResolveInfo resolveService = context.getPackageManager().resolveService(intent, 0);
        if (resolveService == null) {
            return null;
        }
        if (akVar.a(context, resolveService.serviceInfo.packageName)) {
            return intent;
        }
        return null;
    }

    public static Intent m5748a(Context context, String str, Collection<String> collection, String str2, boolean z, boolean z2, DefaultAudience defaultAudience) {
        for (ak akVar : f4311b) {
            Intent putExtra = new Intent().setClassName(akVar.a(), "com.facebook.katana.ProxyAuth").putExtra("client_id", str);
            if (!aq.a(collection)) {
                putExtra.putExtra("scope", TextUtils.join(",", collection));
            }
            if (!aq.a(str2)) {
                putExtra.putExtra("e2e", str2);
            }
            putExtra.putExtra("response_type", "token,signed_request");
            putExtra.putExtra("return_scopes", "true");
            if (z2) {
                putExtra.putExtra("default_audience", defaultAudience.getNativeProtocolAudience());
            }
            putExtra.putExtra("legacy_override", "v2.3");
            if (z) {
                putExtra.putExtra("auth_type", "rerequest");
            }
            Intent a = m5745a(context, putExtra, akVar);
            if (a != null) {
                return a;
            }
        }
        return null;
    }

    public static final int m5739a() {
        return ((Integer) f4314e.get(0)).intValue();
    }

    private static Intent m5746a(Context context, String str, String str2) {
        List<ak> list = (List) f4312c.get(str2);
        if (list == null) {
            return null;
        }
        Intent intent = null;
        for (ak akVar : list) {
            intent = m5745a(context, new Intent().setAction(str).setPackage(akVar.a()).addCategory("android.intent.category.DEFAULT"), akVar);
            if (intent != null) {
                return intent;
            }
        }
        return intent;
    }

    public static boolean m5754a(int i) {
        return f4314e.contains(Integer.valueOf(i)) && i >= 20140701;
    }

    public static Intent m5747a(Context context, String str, String str2, int i, Bundle bundle) {
        Intent a = m5746a(context, "com.facebook.platform.PLATFORM_ACTIVITY", str2);
        if (a == null) {
            return null;
        }
        m5753a(a, str, str2, i, bundle);
        return a;
    }

    public static void m5753a(Intent intent, String str, String str2, int i, Bundle bundle) {
        String h = FacebookSdk.m5786h();
        String i2 = FacebookSdk.m5787i();
        intent.putExtra("com.facebook.platform.protocol.PROTOCOL_VERSION", i).putExtra("com.facebook.platform.protocol.PROTOCOL_ACTION", str2).putExtra("com.facebook.platform.extra.APPLICATION_ID", h);
        if (m5754a(i)) {
            Bundle bundle2 = new Bundle();
            bundle2.putString("action_id", str);
            aq.a(bundle2, "app_name", i2);
            intent.putExtra("com.facebook.platform.protocol.BRIDGE_ARGS", bundle2);
            if (bundle == null) {
                bundle = new Bundle();
            }
            intent.putExtra("com.facebook.platform.protocol.METHOD_ARGS", bundle);
            return;
        }
        intent.putExtra("com.facebook.platform.protocol.CALL_ID", str);
        if (!aq.a(i2)) {
            intent.putExtra("com.facebook.platform.extra.APPLICATION_NAME", i2);
        }
        intent.putExtras(bundle);
    }

    public static Intent m5749a(Intent intent, Bundle bundle, FacebookException facebookException) {
        UUID b = m5758b(intent);
        if (b == null) {
            return null;
        }
        Intent intent2 = new Intent();
        intent2.putExtra("com.facebook.platform.protocol.PROTOCOL_VERSION", m5740a(intent));
        Bundle bundle2 = new Bundle();
        bundle2.putString("action_id", b.toString());
        if (facebookException != null) {
            bundle2.putBundle(BaseMessage.TYPE_ERROR, m5750a(facebookException));
        }
        intent2.putExtra("com.facebook.platform.protocol.BRIDGE_ARGS", bundle2);
        if (bundle == null) {
            return intent2;
        }
        intent2.putExtra("com.facebook.platform.protocol.RESULT_ARGS", bundle);
        return intent2;
    }

    public static Intent m5744a(Context context) {
        for (ak akVar : f4311b) {
            Intent b = m5756b(context, new Intent("com.facebook.platform.PLATFORM_SERVICE").setPackage(akVar.a()).addCategory("android.intent.category.DEFAULT"), akVar);
            if (b != null) {
                return b;
            }
        }
        return null;
    }

    public static int m5740a(Intent intent) {
        return intent.getIntExtra("com.facebook.platform.protocol.PROTOCOL_VERSION", 0);
    }

    public static UUID m5758b(Intent intent) {
        UUID uuid = null;
        if (intent != null) {
            String string;
            if (m5754a(m5740a(intent))) {
                Bundle bundleExtra = intent.getBundleExtra("com.facebook.platform.protocol.BRIDGE_ARGS");
                if (bundleExtra != null) {
                    string = bundleExtra.getString("action_id");
                } else {
                    Object obj = uuid;
                }
            } else {
                string = intent.getStringExtra("com.facebook.platform.protocol.CALL_ID");
            }
            if (string != null) {
                try {
                    uuid = UUID.fromString(string);
                } catch (IllegalArgumentException e) {
                }
            }
        }
        return uuid;
    }

    public static Bundle m5761c(Intent intent) {
        if (m5754a(m5740a(intent))) {
            return intent.getBundleExtra("com.facebook.platform.protocol.BRIDGE_ARGS");
        }
        return null;
    }

    public static Bundle m5763d(Intent intent) {
        if (m5754a(m5740a(intent))) {
            return intent.getBundleExtra("com.facebook.platform.protocol.METHOD_ARGS");
        }
        return intent.getExtras();
    }

    public static Bundle m5765e(Intent intent) {
        int a = m5740a(intent);
        Bundle extras = intent.getExtras();
        return (!m5754a(a) || extras == null) ? extras : extras.getBundle("com.facebook.platform.protocol.RESULT_ARGS");
    }

    public static boolean m5768f(Intent intent) {
        Bundle c = m5761c(intent);
        if (c != null) {
            return c.containsKey(BaseMessage.TYPE_ERROR);
        }
        return intent.hasExtra("com.facebook.platform.status.ERROR_TYPE");
    }

    public static Bundle m5769g(Intent intent) {
        if (!m5768f(intent)) {
            return null;
        }
        Bundle c = m5761c(intent);
        if (c != null) {
            return c.getBundle(BaseMessage.TYPE_ERROR);
        }
        return intent.getExtras();
    }

    public static FacebookException m5751a(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        String string = bundle.getString("error_type");
        if (string == null) {
            string = bundle.getString("com.facebook.platform.status.ERROR_TYPE");
        }
        String string2 = bundle.getString("error_description");
        if (string2 == null) {
            string2 = bundle.getString("com.facebook.platform.status.ERROR_DESCRIPTION");
        }
        if (string == null || !string.equalsIgnoreCase("UserCanceled")) {
            return new FacebookException(string2);
        }
        return new FacebookOperationCanceledException(string2);
    }

    public static Bundle m5750a(FacebookException facebookException) {
        if (facebookException == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        bundle.putString("error_description", facebookException.toString());
        if (!(facebookException instanceof FacebookOperationCanceledException)) {
            return bundle;
        }
        bundle.putString("error_type", "UserCanceled");
        return bundle;
    }

    public static int m5755b(int i) {
        return m5742a(f4311b, new int[]{i});
    }

    public static int m5741a(String str, int[] iArr) {
        return m5742a((List) f4312c.get(str), iArr);
    }

    private static int m5742a(List<ak> list, int[] iArr) {
        m5759b();
        if (list == null) {
            return -1;
        }
        for (ak b : list) {
            int a = m5743a(b.b(), m5739a(), iArr);
            if (a != -1) {
                return a;
            }
        }
        return -1;
    }

    public static void m5759b() {
        if (f4313d.compareAndSet(false, true)) {
            FacebookSdk.m5782d().execute(new 1());
        }
    }

    private static TreeSet<Integer> m5757b(ak akVar) {
        Throwable th;
        Cursor cursor = null;
        TreeSet<Integer> treeSet = new TreeSet();
        ContentResolver contentResolver = FacebookSdk.m5784f().getContentResolver();
        String[] strArr = new String[]{"version"};
        Uri c = m5760c(akVar);
        try {
            Cursor query;
            if (FacebookSdk.m5784f().getPackageManager().resolveContentProvider(akVar.a() + ".provider.PlatformProvider", 0) != null) {
                query = contentResolver.query(c, strArr, null, null, null);
                if (query != null) {
                    while (query.moveToNext()) {
                        try {
                            treeSet.add(Integer.valueOf(query.getInt(query.getColumnIndex("version"))));
                        } catch (Throwable th2) {
                            cursor = query;
                            th = th2;
                        }
                    }
                }
            } else {
                query = null;
            }
            if (query != null) {
                query.close();
            }
            return treeSet;
        } catch (Throwable th3) {
            th = th3;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public static int m5743a(TreeSet<Integer> treeSet, int i, int[] iArr) {
        int length = iArr.length - 1;
        Iterator descendingIterator = treeSet.descendingIterator();
        int i2 = -1;
        int i3 = length;
        while (descendingIterator.hasNext()) {
            int intValue = ((Integer) descendingIterator.next()).intValue();
            length = Math.max(i2, intValue);
            i2 = i3;
            while (i2 >= 0 && iArr[i2] > intValue) {
                i2--;
            }
            if (i2 < 0) {
                return -1;
            }
            if (iArr[i2] != intValue) {
                i3 = i2;
                i2 = length;
            } else if (i2 % 2 == 0) {
                return Math.min(length, i);
            } else {
                return -1;
            }
        }
        return -1;
    }

    private static Uri m5760c(ak akVar) {
        return Uri.parse("content://" + akVar.a() + ".provider.PlatformProvider/versions");
    }
}
