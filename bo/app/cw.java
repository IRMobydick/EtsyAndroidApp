package bo.app;

import com.appboy.Constants;
import com.appboy.models.ResponseError;
import com.appboy.support.AppboyLogger;
import com.etsy.android.lib.models.cardviewelement.BaseMessage;
import org.json.JSONObject;

public final class cw {
    private static final String f268b;
    public final ResponseError f269a;

    static {
        f268b = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, cw.class.getName()});
    }

    public cw(JSONObject jSONObject) {
        ResponseError responseError;
        JSONObject optJSONObject = jSONObject.optJSONObject(BaseMessage.TYPE_ERROR);
        if (optJSONObject != null) {
            try {
                responseError = new ResponseError(optJSONObject);
            } catch (Throwable e) {
                AppboyLogger.m671w(f268b, "Encountered exception processing ResponseError: " + optJSONObject.toString(), e);
            }
            this.f269a = responseError;
        }
        responseError = null;
        this.f269a = responseError;
    }
}
