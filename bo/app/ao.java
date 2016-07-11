package bo.app;

import android.content.Context;
import android.content.Intent;
import com.appboy.Constants;

public class ao {
    private static final String f109a;
    private final ci f110b;
    private final em f111c;
    private final C0349z f112d;
    private final by f113e;
    private final bv f114f;
    private final Context f115g;
    private final fa f116h;
    private final Intent f117i;
    private final es f118j;
    private final ey f119k;
    private final dp f120l;

    static {
        f109a = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, ao.class.getName()});
    }

    public ao(ci ciVar, em emVar, C0349z c0349z, by byVar, bv bvVar, Context context, fa faVar, es esVar, ey eyVar, dp dpVar) {
        this.f110b = ciVar;
        this.f111c = emVar;
        this.f112d = c0349z;
        this.f113e = byVar;
        this.f114f = bvVar;
        this.f115g = context;
        this.f116h = faVar;
        this.f117i = new Intent(context.getPackageName() + Constants.APPBOY_DATA_SYNC_REQUEST_INTENT);
        this.f118j = esVar;
        this.f119k = eyVar;
        this.f120l = dpVar;
    }
}
