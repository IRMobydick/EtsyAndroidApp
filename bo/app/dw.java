package bo.app;

import com.appboy.Constants;
import java.util.Map;

public final class dw implements Runnable {
    private static final String f353a;
    private final eb f354b;
    private final bc f355c;
    private final bc f356d;
    private final Map<String, String> f357e;
    private final C0342i f358f;
    private final eu f359g;
    private final ey f360h;
    private final ce f361i;

    static {
        f353a = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, dw.class.getName()});
    }

    public dw(eb ebVar, C0341g c0341g, C0342i c0342i, bc bcVar, bc bcVar2, eu euVar, ce ceVar, ey eyVar) {
        this.f354b = ebVar;
        this.f355c = bcVar;
        this.f356d = bcVar2;
        this.f357e = c0341g.m416a();
        this.f358f = c0342i;
        this.f359g = euVar;
        this.f361i = ceVar;
        this.f360h = eyVar;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
        r11 = this;
        r3 = 0;
        r6 = 1;
        r7 = 0;
        r0 = r11.f354b;	 Catch:{ Exception -> 0x01bc }
        r0 = r0.m206b();	 Catch:{ Exception -> 0x01bc }
        r1 = bo.app.ff.m336a(r0);	 Catch:{ Exception -> 0x01bc }
        r0 = bo.app.dx.f362a;	 Catch:{ Exception -> 0x01bc }
        r2 = r11.f354b;	 Catch:{ Exception -> 0x01bc }
        r2 = r2.m203a();	 Catch:{ Exception -> 0x01bc }
        r2 = r2.ordinal();	 Catch:{ Exception -> 0x01bc }
        r0 = r0[r2];	 Catch:{ Exception -> 0x01bc }
        switch(r0) {
            case 1: goto L_0x01ab;
            case 2: goto L_0x01e3;
            default: goto L_0x001e;
        };	 Catch:{ Exception -> 0x01bc }
    L_0x001e:
        r0 = f353a;	 Catch:{ Exception -> 0x01bc }
        r1 = "Received a request with an unknown Http verb: [%s]";
        r2 = 1;
        r2 = new java.lang.Object[r2];	 Catch:{ Exception -> 0x01bc }
        r4 = 0;
        r5 = r11.f354b;	 Catch:{ Exception -> 0x01bc }
        r5 = r5.m203a();	 Catch:{ Exception -> 0x01bc }
        r2[r4] = r5;	 Catch:{ Exception -> 0x01bc }
        r1 = java.lang.String.format(r1, r2);	 Catch:{ Exception -> 0x01bc }
        com.appboy.support.AppboyLogger.m670w(r0, r1);	 Catch:{ Exception -> 0x01bc }
        r0 = r3;
    L_0x0036:
        if (r0 == 0) goto L_0x029f;
    L_0x0038:
        r8 = r0.f238a;	 Catch:{ Exception -> 0x01bc }
        r0 = r0.f239b;	 Catch:{ Exception -> 0x01bc }
        if (r0 == 0) goto L_0x0206;
    L_0x003e:
        r0 = r0.f269a;	 Catch:{ Exception -> 0x01bc }
    L_0x0040:
        if (r0 != 0) goto L_0x0209;
    L_0x0042:
        r0 = r11.f354b;	 Catch:{ Exception -> 0x01bc }
        r1 = r11.f356d;	 Catch:{ Exception -> 0x01bc }
        r0.m204a(r1);	 Catch:{ Exception -> 0x01bc }
    L_0x0049:
        if (r8 == 0) goto L_0x0195;
    L_0x004b:
        r0 = r11.f357e;	 Catch:{ Exception -> 0x01bc }
        r1 = "X-Appboy-User-Identifier";
        r2 = r0.get(r1);	 Catch:{ Exception -> 0x01bc }
        r2 = (java.lang.String) r2;	 Catch:{ Exception -> 0x01bc }
        r0 = r8.f264a;	 Catch:{ Exception -> 0x01bc }
        if (r0 == 0) goto L_0x024a;
    L_0x0059:
        r0 = r6;
    L_0x005a:
        if (r0 == 0) goto L_0x00d5;
    L_0x005c:
        r0 = r11.f359g;	 Catch:{ JSONException -> 0x025b }
        r1 = r8.f264a;	 Catch:{ JSONException -> 0x025b }
        if (r2 != 0) goto L_0x024d;
    L_0x0062:
        r4 = "";
    L_0x0064:
        r5 = r0.f413b;	 Catch:{ JSONException -> 0x025b }
        r9 = "uid";
        r10 = "";
        r5 = r5.getString(r9, r10);	 Catch:{ JSONException -> 0x025b }
        r4 = r5.equals(r4);	 Catch:{ JSONException -> 0x025b }
        if (r4 == 0) goto L_0x0265;
    L_0x0074:
        r3 = bo.app.eu.f412a;	 Catch:{ JSONException -> 0x025b }
        r4 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x025b }
        r5 = "Updating offline feed for user with id: ";
        r4.<init>(r5);	 Catch:{ JSONException -> 0x025b }
        r4 = r4.append(r2);	 Catch:{ JSONException -> 0x025b }
        r4 = r4.toString();	 Catch:{ JSONException -> 0x025b }
        com.appboy.support.AppboyLogger.m666i(r3, r4);	 Catch:{ JSONException -> 0x025b }
        r4 = bo.app.fd.m330a();	 Catch:{ JSONException -> 0x025b }
        r3 = r0.f413b;	 Catch:{ JSONException -> 0x025b }
        r3 = r3.edit();	 Catch:{ JSONException -> 0x025b }
        if (r1 == 0) goto L_0x009a;
    L_0x0094:
        r9 = r1.length();	 Catch:{ JSONException -> 0x025b }
        if (r9 != 0) goto L_0x0250;
    L_0x009a:
        r9 = "cards";
        r3.remove(r9);	 Catch:{ JSONException -> 0x025b }
    L_0x009f:
        r9 = "cards_timestamp";
        r3.putLong(r9, r4);	 Catch:{ JSONException -> 0x025b }
        r3.apply();	 Catch:{ JSONException -> 0x025b }
        r3 = r0.f414c;	 Catch:{ JSONException -> 0x025b }
        r9 = bo.app.eu.m274a(r1);	 Catch:{ JSONException -> 0x025b }
        r3.retainAll(r9);	 Catch:{ JSONException -> 0x025b }
        r3 = r0.f414c;	 Catch:{ JSONException -> 0x025b }
        r9 = bo.app.ev.VIEWED_CARDS;	 Catch:{ JSONException -> 0x025b }
        r0.m278a(r3, r9);	 Catch:{ JSONException -> 0x025b }
        r3 = r0.f415d;	 Catch:{ JSONException -> 0x025b }
        r9 = bo.app.eu.m274a(r1);	 Catch:{ JSONException -> 0x025b }
        r3.retainAll(r9);	 Catch:{ JSONException -> 0x025b }
        r3 = r0.f415d;	 Catch:{ JSONException -> 0x025b }
        r9 = bo.app.ev.READ_CARDS;	 Catch:{ JSONException -> 0x025b }
        r0.m278a(r3, r9);	 Catch:{ JSONException -> 0x025b }
        r3 = 0;
        r0 = r0.m276a(r1, r2, r3, r4);	 Catch:{ JSONException -> 0x025b }
    L_0x00cc:
        if (r0 == 0) goto L_0x00d5;
    L_0x00ce:
        r1 = r11.f356d;	 Catch:{ JSONException -> 0x025b }
        r2 = com.appboy.events.FeedUpdatedEvent.class;
        r1.m31a(r0, r2);	 Catch:{ JSONException -> 0x025b }
    L_0x00d5:
        r0 = r8.f266c;	 Catch:{ Exception -> 0x01bc }
        if (r0 == 0) goto L_0x028c;
    L_0x00d9:
        r0 = r6;
    L_0x00da:
        if (r0 == 0) goto L_0x0176;
    L_0x00dc:
        r0 = r11.f360h;	 Catch:{ Exception -> 0x01bc }
        r1 = r8.f266c;	 Catch:{ Exception -> 0x01bc }
        r2 = r0.f430d;	 Catch:{ Exception -> 0x01bc }
        monitor-enter(r2);	 Catch:{ Exception -> 0x01bc }
        r3 = r1.f278i;	 Catch:{ all -> 0x028f }
        if (r3 == 0) goto L_0x00f6;
    L_0x00e7:
        r3 = r0.m295a();	 Catch:{ all -> 0x028f }
        if (r3 != 0) goto L_0x00f6;
    L_0x00ed:
        r3 = r0.f428b;	 Catch:{ all -> 0x028f }
        r4 = bo.app.du.f349a;	 Catch:{ all -> 0x028f }
        r5 = bo.app.du.class;
        r3.m31a(r4, r5);	 Catch:{ all -> 0x028f }
    L_0x00f6:
        r0.f432f = r1;	 Catch:{ all -> 0x028f }
        monitor-exit(r2);	 Catch:{ all -> 0x028f }
        r0 = r0.f429c;	 Catch:{ Exception -> 0x0292 }
        r0 = r0.edit();	 Catch:{ Exception -> 0x0292 }
        r2 = r1.f271b;	 Catch:{ Exception -> 0x0292 }
        if (r2 == 0) goto L_0x0113;
    L_0x0103:
        r2 = "blacklisted_events";
        r3 = new org.json.JSONArray;	 Catch:{ Exception -> 0x0292 }
        r4 = r1.f271b;	 Catch:{ Exception -> 0x0292 }
        r3.<init>(r4);	 Catch:{ Exception -> 0x0292 }
        r3 = r3.toString();	 Catch:{ Exception -> 0x0292 }
        r0.putString(r2, r3);	 Catch:{ Exception -> 0x0292 }
    L_0x0113:
        r2 = r1.f272c;	 Catch:{ Exception -> 0x0292 }
        if (r2 == 0) goto L_0x0127;
    L_0x0117:
        r2 = "blacklisted_attributes";
        r3 = new org.json.JSONArray;	 Catch:{ Exception -> 0x0292 }
        r4 = r1.f272c;	 Catch:{ Exception -> 0x0292 }
        r3.<init>(r4);	 Catch:{ Exception -> 0x0292 }
        r3 = r3.toString();	 Catch:{ Exception -> 0x0292 }
        r0.putString(r2, r3);	 Catch:{ Exception -> 0x0292 }
    L_0x0127:
        r2 = r1.f273d;	 Catch:{ Exception -> 0x0292 }
        if (r2 == 0) goto L_0x013b;
    L_0x012b:
        r2 = "blacklisted_purchases";
        r3 = new org.json.JSONArray;	 Catch:{ Exception -> 0x0292 }
        r4 = r1.f273d;	 Catch:{ Exception -> 0x0292 }
        r3.<init>(r4);	 Catch:{ Exception -> 0x0292 }
        r3 = r3.toString();	 Catch:{ Exception -> 0x0292 }
        r0.putString(r2, r3);	 Catch:{ Exception -> 0x0292 }
    L_0x013b:
        r2 = "config_time";
        r4 = r1.f270a;	 Catch:{ Exception -> 0x0292 }
        r0.putLong(r2, r4);	 Catch:{ Exception -> 0x0292 }
        r2 = "location_enabled";
        r3 = r1.f275f;	 Catch:{ Exception -> 0x0292 }
        r0.putBoolean(r2, r3);	 Catch:{ Exception -> 0x0292 }
        r2 = "location_enabled_set";
        r3 = r1.f274e;	 Catch:{ Exception -> 0x0292 }
        r0.putBoolean(r2, r3);	 Catch:{ Exception -> 0x0292 }
        r2 = "location_time";
        r4 = r1.f276g;	 Catch:{ Exception -> 0x0292 }
        r0.putLong(r2, r4);	 Catch:{ Exception -> 0x0292 }
        r2 = "location_distance";
        r3 = r1.f277h;	 Catch:{ Exception -> 0x0292 }
        r0.putFloat(r2, r3);	 Catch:{ Exception -> 0x0292 }
        r2 = "piq_enabled";
        r1 = r1.f278i;	 Catch:{ Exception -> 0x0292 }
        r0.putBoolean(r2, r1);	 Catch:{ Exception -> 0x0292 }
        r0.commit();	 Catch:{ Exception -> 0x0292 }
    L_0x0168:
        r0 = r11.f355c;	 Catch:{ Exception -> 0x01bc }
        r1 = new bo.app.bh;	 Catch:{ Exception -> 0x01bc }
        r2 = r8.f266c;	 Catch:{ Exception -> 0x01bc }
        r1.<init>(r2);	 Catch:{ Exception -> 0x01bc }
        r2 = bo.app.bh.class;
        r0.m31a(r1, r2);	 Catch:{ Exception -> 0x01bc }
    L_0x0176:
        r0 = r8.f265b;	 Catch:{ Exception -> 0x01bc }
        if (r0 == 0) goto L_0x029c;
    L_0x017a:
        r0 = r6;
    L_0x017b:
        if (r0 == 0) goto L_0x0195;
    L_0x017d:
        r1 = r11.f356d;	 Catch:{ Exception -> 0x01bc }
        r2 = new com.appboy.events.InAppMessageEvent;	 Catch:{ Exception -> 0x01bc }
        r3 = r8.f265b;	 Catch:{ Exception -> 0x01bc }
        r0 = r11.f357e;	 Catch:{ Exception -> 0x01bc }
        r4 = "X-Appboy-User-Identifier";
        r0 = r0.get(r4);	 Catch:{ Exception -> 0x01bc }
        r0 = (java.lang.String) r0;	 Catch:{ Exception -> 0x01bc }
        r2.<init>(r3, r0);	 Catch:{ Exception -> 0x01bc }
        r0 = com.appboy.events.InAppMessageEvent.class;
        r1.m31a(r2, r0);	 Catch:{ Exception -> 0x01bc }
    L_0x0195:
        r0 = r11.f354b;	 Catch:{ Exception -> 0x01bc }
        r1 = r11.f355c;	 Catch:{ Exception -> 0x01bc }
        r0.m207b(r1);	 Catch:{ Exception -> 0x01bc }
        r0 = r11.f355c;	 Catch:{ Exception -> 0x01bc }
        r1 = new bo.app.be;	 Catch:{ Exception -> 0x01bc }
        r2 = r11.f354b;	 Catch:{ Exception -> 0x01bc }
        r1.<init>(r2);	 Catch:{ Exception -> 0x01bc }
        r2 = bo.app.be.class;
        r0.m31a(r1, r2);	 Catch:{ Exception -> 0x01bc }
    L_0x01aa:
        return;
    L_0x01ab:
        r0 = new bo.app.cl;	 Catch:{ Exception -> 0x01bc }
        r2 = r11.f358f;	 Catch:{ Exception -> 0x01bc }
        r4 = r11.f357e;	 Catch:{ Exception -> 0x01bc }
        r1 = r2.m489a(r1, r4);	 Catch:{ Exception -> 0x01bc }
        r2 = r11.f361i;	 Catch:{ Exception -> 0x01bc }
        r0.<init>(r1, r2);	 Catch:{ Exception -> 0x01bc }
        goto L_0x0036;
    L_0x01bc:
        r0 = move-exception;
        r1 = f353a;
        r2 = "Experienced exception processing API response. Failing task.";
        com.appboy.support.AppboyLogger.m671w(r1, r2, r0);
    L_0x01c4:
        r0 = r11.f354b;
        r1 = r11.f356d;
        r2 = new com.appboy.models.ResponseError;
        r3 = com.appboy.enums.ErrorType.UNRECOGNIZED_ERROR;
        r4 = "An error occurred during request processing, resulting in no valid response being received. Check the error log for more details.";
        r2.<init>(r3, r4);
        r0.m205a(r1, r2);
        r0 = r11.f355c;
        r1 = new bo.app.bd;
        r2 = r11.f354b;
        r1.<init>(r2);
        r2 = bo.app.bd.class;
        r0.m31a(r1, r2);
        goto L_0x01aa;
    L_0x01e3:
        r0 = r11.f354b;	 Catch:{ Exception -> 0x01bc }
        r2 = r0.m220e();	 Catch:{ Exception -> 0x01bc }
        if (r2 != 0) goto L_0x01f5;
    L_0x01eb:
        r0 = f353a;	 Catch:{ Exception -> 0x01bc }
        r1 = "Could not parse request parameters for put request to [%s], canceling request.";
        com.appboy.support.AppboyLogger.m664e(r0, r1);	 Catch:{ Exception -> 0x01bc }
        r0 = r3;
        goto L_0x0036;
    L_0x01f5:
        r0 = new bo.app.cl;	 Catch:{ Exception -> 0x01bc }
        r4 = r11.f358f;	 Catch:{ Exception -> 0x01bc }
        r5 = r11.f357e;	 Catch:{ Exception -> 0x01bc }
        r1 = r4.m490a(r1, r5, r2);	 Catch:{ Exception -> 0x01bc }
        r2 = r11.f361i;	 Catch:{ Exception -> 0x01bc }
        r0.<init>(r1, r2);	 Catch:{ Exception -> 0x01bc }
        goto L_0x0036;
    L_0x0206:
        r0 = r3;
        goto L_0x0040;
    L_0x0209:
        r1 = r0.getType();	 Catch:{ Exception -> 0x01bc }
        r2 = com.appboy.enums.ErrorType.NO_DEVICE_IDENTIFIER;	 Catch:{ Exception -> 0x01bc }
        if (r1 != r2) goto L_0x0221;
    L_0x0211:
        r1 = f353a;	 Catch:{ Exception -> 0x01bc }
        r2 = "No device identifier. This should never happen. Please contact support@appboy.com";
        com.appboy.support.AppboyLogger.m664e(r1, r2);	 Catch:{ Exception -> 0x01bc }
    L_0x0218:
        r1 = r11.f354b;	 Catch:{ Exception -> 0x01bc }
        r2 = r11.f356d;	 Catch:{ Exception -> 0x01bc }
        r1.m205a(r2, r0);	 Catch:{ Exception -> 0x01bc }
        goto L_0x0049;
    L_0x0221:
        r2 = com.appboy.enums.ErrorType.INVALID_API_KEY;	 Catch:{ Exception -> 0x01bc }
        if (r1 != r2) goto L_0x022d;
    L_0x0225:
        r1 = f353a;	 Catch:{ Exception -> 0x01bc }
        r2 = "Invalid API key! Please update the API key in the appboy.xml file.";
        com.appboy.support.AppboyLogger.m664e(r1, r2);	 Catch:{ Exception -> 0x01bc }
        goto L_0x0218;
    L_0x022d:
        r2 = com.appboy.enums.ErrorType.UNRECOGNIZED_ERROR;	 Catch:{ Exception -> 0x01bc }
        if (r1 != r2) goto L_0x0218;
    L_0x0231:
        r1 = f353a;	 Catch:{ Exception -> 0x01bc }
        r2 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x01bc }
        r4 = "Unrecognized server error: ";
        r2.<init>(r4);	 Catch:{ Exception -> 0x01bc }
        r4 = r0.getMessage();	 Catch:{ Exception -> 0x01bc }
        r2 = r2.append(r4);	 Catch:{ Exception -> 0x01bc }
        r2 = r2.toString();	 Catch:{ Exception -> 0x01bc }
        com.appboy.support.AppboyLogger.m664e(r1, r2);	 Catch:{ Exception -> 0x01bc }
        goto L_0x0218;
    L_0x024a:
        r0 = r7;
        goto L_0x005a;
    L_0x024d:
        r4 = r2;
        goto L_0x0064;
    L_0x0250:
        r9 = "cards";
        r10 = r1.toString();	 Catch:{ JSONException -> 0x025b }
        r3.putString(r9, r10);	 Catch:{ JSONException -> 0x025b }
        goto L_0x009f;
    L_0x025b:
        r0 = move-exception;
        r0 = f353a;	 Catch:{ Exception -> 0x01bc }
        r1 = "Unable to update/publish feed.";
        com.appboy.support.AppboyLogger.m670w(r0, r1);	 Catch:{ Exception -> 0x01bc }
        goto L_0x00d5;
    L_0x0265:
        r0 = bo.app.eu.f412a;	 Catch:{ JSONException -> 0x025b }
        r1 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x025b }
        r4 = "The received cards are for user ";
        r1.<init>(r4);	 Catch:{ JSONException -> 0x025b }
        r1 = r1.append(r2);	 Catch:{ JSONException -> 0x025b }
        r2 = " and the current user is ";
        r1 = r1.append(r2);	 Catch:{ JSONException -> 0x025b }
        r1 = r1.append(r5);	 Catch:{ JSONException -> 0x025b }
        r2 = " , the cards will be discarded and no changes will be made.";
        r1 = r1.append(r2);	 Catch:{ JSONException -> 0x025b }
        r1 = r1.toString();	 Catch:{ JSONException -> 0x025b }
        com.appboy.support.AppboyLogger.m666i(r0, r1);	 Catch:{ JSONException -> 0x025b }
        r0 = r3;
        goto L_0x00cc;
    L_0x028c:
        r0 = r7;
        goto L_0x00da;
    L_0x028f:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ Exception -> 0x01bc }
        throw r0;	 Catch:{ Exception -> 0x01bc }
    L_0x0292:
        r0 = move-exception;
        r1 = bo.app.ey.f427a;	 Catch:{ Exception -> 0x01bc }
        r2 = "Could not persist server config to shared preferences.";
        com.appboy.support.AppboyLogger.m671w(r1, r2, r0);	 Catch:{ Exception -> 0x01bc }
        goto L_0x0168;
    L_0x029c:
        r0 = r7;
        goto L_0x017b;
    L_0x029f:
        r0 = f353a;	 Catch:{ Exception -> 0x01bc }
        r1 = "Api response was null, failing task.";
        com.appboy.support.AppboyLogger.m670w(r0, r1);	 Catch:{ Exception -> 0x01bc }
        goto L_0x01c4;
        */
        throw new UnsupportedOperationException("Method not decompiled: bo.app.dw.run():void");
    }
}
