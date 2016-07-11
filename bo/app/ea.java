package bo.app;

import android.net.Uri;
import com.appboy.Constants;
import com.appboy.enums.ErrorType;
import com.appboy.events.SubmitFeedbackFailed;
import com.appboy.events.SubmitFeedbackSucceeded;
import com.appboy.models.ResponseError;
import com.appboy.models.outgoing.Feedback;
import com.appboy.support.AppboyLogger;
import org.json.JSONObject;

public final class ea extends dv {
    private static final String f368b;
    private final Feedback f369c;

    static {
        f368b = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, ea.class.getName()});
    }

    public ea(String str, Feedback feedback) {
        super(Uri.parse(str + "feedback"));
        this.f369c = feedback;
    }

    public final ag m235a() {
        return ag.POST;
    }

    public final void m236a(bc bcVar) {
        bcVar.m31a(new SubmitFeedbackSucceeded(this.f369c), SubmitFeedbackSucceeded.class);
    }

    public final boolean m239f() {
        return false;
    }

    public final void m237a(bc bcVar, ResponseError responseError) {
        ErrorType type = responseError.getType();
        String message = responseError.getMessage();
        if (type == ErrorType.REQUIRED_FIELD_MISSING) {
            AppboyLogger.m664e(f368b, String.format("Required Field Missing: %s", new Object[]{message}));
        } else if (type == ErrorType.BAD_INPUT) {
            AppboyLogger.m664e(f368b, String.format("Bad Input: %s", new Object[]{message}));
        }
        bcVar.m31a(new SubmitFeedbackFailed(this.f369c, responseError), SubmitFeedbackFailed.class);
    }

    public final JSONObject m238e() {
        JSONObject e = super.m228e();
        if (e == null) {
            return null;
        }
        try {
            e.put("feedback", this.f369c.forJsonPut());
            return e;
        } catch (Throwable e2) {
            AppboyLogger.m671w(f368b, "Experienced JSONException while retrieving parameters. Returning null.", e2);
            return null;
        }
    }
}
