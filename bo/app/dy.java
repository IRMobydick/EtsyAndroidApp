package bo.app;

import android.net.Uri;
import com.appboy.Constants;
import com.appboy.enums.ErrorType;
import com.appboy.models.ResponseError;
import com.appboy.support.AppboyLogger;
import com.etsy.android.lib.models.apiv3.ActivityFeedEntity;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public final class dy extends dv {
    private static final String f363b;
    private final List<aj> f364c;

    static {
        f363b = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, dy.class.getName()});
    }

    public dy(String str, int i) {
        super(Uri.parse(str + ActivityFeedEntity.DATA));
        this.f364c = new ArrayList();
        switch (dz.f365a[i - 1]) {
            case Task.NETWORK_STATE_UNMETERED /*1*/:
                this.f364c.add(aj.INAPP);
                this.f364c.add(aj.FEED);
            case Task.NETWORK_STATE_ANY /*2*/:
                this.f364c.add(aj.INAPP);
            case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                this.f364c.add(aj.FEED);
            default:
        }
    }

    public final ag m230a() {
        return ag.POST;
    }

    public final void m231a(bc bcVar) {
    }

    public final void m232a(bc bcVar, ResponseError responseError) {
        ErrorType type = responseError.getType();
        if (type == ErrorType.REQUIRED_FIELD_MISSING) {
            AppboyLogger.m664e(f363b, String.format("Required Field Missing: %s", new Object[]{responseError.getMessage()}));
        } else if (type == ErrorType.BAD_INPUT) {
            AppboyLogger.m664e(f363b, String.format("Bad Input: %s", new Object[]{responseError.getMessage()}));
        }
    }

    public final boolean m234f() {
        boolean z;
        if (this.f364c == null || this.f364c.size() == 0) {
            z = true;
        } else {
            z = false;
        }
        return z && super.m229f();
    }

    public final JSONObject m233e() {
        JSONObject e = super.m228e();
        if (e == null) {
            return null;
        }
        try {
            e.put("only_respond_with", fg.m342a(this.f364c));
            return e;
        } catch (Throwable e2) {
            AppboyLogger.m671w(f363b, "Experienced JSONException while retrieving parameters. Returning null.", e2);
            return null;
        }
    }
}
