package bo.app;

import com.appboy.Constants;
import com.appboy.enums.inappmessage.MessageType;
import com.appboy.models.IInAppMessage;
import com.appboy.models.InAppMessageFull;
import com.appboy.models.InAppMessageHtmlFull;
import com.appboy.models.InAppMessageModal;
import com.appboy.models.InAppMessageSlideup;
import com.appboy.support.AppboyLogger;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.finds.FindsModule;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;
import org.json.JSONArray;
import org.json.JSONObject;

public final class cu {
    private static final String f263d;
    public final JSONArray f264a;
    public final IInAppMessage f265b;
    public final cx f266c;

    static {
        f263d = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, cu.class.getName()});
    }

    public cu(JSONObject jSONObject, ce ceVar) {
        IInAppMessage inAppMessageFull;
        cx cxVar = null;
        JSONArray optJSONArray = jSONObject.optJSONArray(ResponseConstants.FEED);
        if (optJSONArray != null) {
            this.f264a = optJSONArray;
        } else {
            this.f264a = null;
        }
        JSONObject optJSONObject = jSONObject.optJSONObject("inapp");
        if (optJSONObject != null) {
            try {
                MessageType messageType = (MessageType) fg.m339a(optJSONObject, FindsModule.FIELD_TYPE, MessageType.class, null);
                if (messageType != null) {
                    switch (cv.f267a[messageType.ordinal()]) {
                        case Task.NETWORK_STATE_UNMETERED /*1*/:
                            inAppMessageFull = new InAppMessageFull(optJSONObject, ceVar);
                            break;
                        case Task.NETWORK_STATE_ANY /*2*/:
                            inAppMessageFull = new InAppMessageModal(optJSONObject, ceVar);
                            break;
                        case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                            inAppMessageFull = new InAppMessageSlideup(optJSONObject, ceVar);
                            break;
                        case CommonStatusCodes.SIGN_IN_REQUIRED /*4*/:
                            inAppMessageFull = new InAppMessageHtmlFull(optJSONObject, ceVar);
                            break;
                        default:
                            AppboyLogger.m664e(f263d, "Unknown in-app message type.  Not parsing in-app message JSON: " + optJSONObject.toString());
                            inAppMessageFull = null;
                            break;
                    }
                }
                AppboyLogger.m666i(f263d, "In-app message type was null.  Not parsing in-app message JSON: " + optJSONObject.toString());
                inAppMessageFull = null;
            } catch (Throwable e) {
                AppboyLogger.m671w(f263d, "Encountered JSONException processing In-app message: " + optJSONObject.toString(), e);
                inAppMessageFull = null;
            } catch (Throwable e2) {
                AppboyLogger.m671w(f263d, "Encountered Exception processing In-app message: " + optJSONObject.toString(), e2);
            }
            this.f265b = inAppMessageFull;
            optJSONObject = jSONObject.optJSONObject("config");
            if (optJSONObject != null) {
                try {
                    cxVar = new cx(optJSONObject);
                } catch (Throwable e22) {
                    AppboyLogger.m671w(f263d, "Encountered JSONException processing server config: " + optJSONObject.toString(), e22);
                } catch (Throwable e222) {
                    AppboyLogger.m671w(f263d, "Encountered Exception processing server config: " + optJSONObject.toString(), e222);
                }
            }
            this.f266c = cxVar;
        }
        inAppMessageFull = null;
        this.f265b = inAppMessageFull;
        optJSONObject = jSONObject.optJSONObject("config");
        if (optJSONObject != null) {
            cxVar = new cx(optJSONObject);
        }
        this.f266c = cxVar;
    }
}
