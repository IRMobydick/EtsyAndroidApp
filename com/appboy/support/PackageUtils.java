package com.appboy.support;

import com.appboy.Constants;

public class PackageUtils {
    private static final String f1041a;
    private static String f1042b;

    static {
        f1041a = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, PackageUtils.class.getName()});
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getResourcePackageName(android.content.Context r5) {
        /*
        r0 = f1042b;
        if (r0 == 0) goto L_0x0007;
    L_0x0004:
        r0 = f1042b;
    L_0x0006:
        return r0;
    L_0x0007:
        r1 = r5.getResources();	 Catch:{ NotFoundException -> 0x004e, NullPointerException -> 0x005d, Exception -> 0x0066 }
        r0 = com.appboy.C0379R.string.resource_for_package_identification;	 Catch:{ Exception -> 0x0014, NotFoundException -> 0x004e, NullPointerException -> 0x005d }
        r0 = r1.getResourcePackageName(r0);	 Catch:{ Exception -> 0x0014, NotFoundException -> 0x004e, NullPointerException -> 0x005d }
        f1042b = r0;	 Catch:{ Exception -> 0x0014, NotFoundException -> 0x004e, NullPointerException -> 0x005d }
        goto L_0x0006;
    L_0x0014:
        r0 = move-exception;
        r0 = r5.getPackageManager();	 Catch:{ NotFoundException -> 0x004e, NullPointerException -> 0x005d, Exception -> 0x0066 }
        r2 = 0;
        r0 = r0.getInstalledPackages(r2);	 Catch:{ NotFoundException -> 0x004e, NullPointerException -> 0x005d, Exception -> 0x0066 }
        r2 = r0.iterator();	 Catch:{ NotFoundException -> 0x004e, NullPointerException -> 0x005d, Exception -> 0x0066 }
    L_0x0022:
        r0 = r2.hasNext();	 Catch:{ NotFoundException -> 0x004e, NullPointerException -> 0x005d, Exception -> 0x0066 }
        if (r0 == 0) goto L_0x0056;
    L_0x0028:
        r0 = r2.next();	 Catch:{ NotFoundException -> 0x004e, NullPointerException -> 0x005d, Exception -> 0x0066 }
        r0 = (android.content.pm.PackageInfo) r0;	 Catch:{ NotFoundException -> 0x004e, NullPointerException -> 0x005d, Exception -> 0x0066 }
        r3 = "string/resource_for_package_identification";
        r4 = 0;
        r0 = r0.packageName;	 Catch:{ NotFoundException -> 0x004e, NullPointerException -> 0x005d, Exception -> 0x0066 }
        r0 = r1.getIdentifier(r3, r4, r0);	 Catch:{ NotFoundException -> 0x004e, NullPointerException -> 0x005d, Exception -> 0x0066 }
        if (r0 == 0) goto L_0x0022;
    L_0x0039:
        r3 = r1.getResourceName(r0);	 Catch:{ Exception -> 0x004c, NotFoundException -> 0x004e, NullPointerException -> 0x005d }
        r4 = "string/resource_for_package_identification";
        r3 = r3.contains(r4);	 Catch:{ Exception -> 0x004c, NotFoundException -> 0x004e, NullPointerException -> 0x005d }
        if (r3 == 0) goto L_0x0022;
    L_0x0045:
        r0 = r1.getResourcePackageName(r0);	 Catch:{ Exception -> 0x004c, NotFoundException -> 0x004e, NullPointerException -> 0x005d }
        f1042b = r0;	 Catch:{ Exception -> 0x004c, NotFoundException -> 0x004e, NullPointerException -> 0x005d }
        goto L_0x0006;
    L_0x004c:
        r0 = move-exception;
        goto L_0x0022;
    L_0x004e:
        r0 = move-exception;
        r0 = f1041a;
        r1 = "Could not find resource for package identification, returning Context#getPackageName().";
        com.appboy.support.AppboyLogger.m666i(r0, r1);
    L_0x0056:
        r0 = r5.getPackageName();
        f1042b = r0;
        goto L_0x0006;
    L_0x005d:
        r0 = move-exception;
        r1 = f1041a;
        r2 = "Got null pointer exception getting resource package name, returning Context#getPackageName().";
        com.appboy.support.AppboyLogger.m665e(r1, r2, r0);
        goto L_0x0056;
    L_0x0066:
        r0 = move-exception;
        r1 = f1041a;
        r2 = "General exception getting resource package name, returning Context#getPackageName().";
        com.appboy.support.AppboyLogger.m665e(r1, r2, r0);
        goto L_0x0056;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appboy.support.PackageUtils.getResourcePackageName(android.content.Context):java.lang.String");
    }
}
