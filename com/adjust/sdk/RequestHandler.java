package com.adjust.sdk;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import java.lang.ref.WeakReference;
import java.util.Locale;
import org.json.JSONObject;

public class RequestHandler extends HandlerThread implements IRequestHandler {
    private InternalHandler internalHandler;
    private ILogger logger;
    private IPackageHandler packageHandler;

    final class InternalHandler extends Handler {
        private static final int SEND = 72400;
        private final WeakReference<RequestHandler> requestHandlerReference;

        protected InternalHandler(Looper looper, RequestHandler requestHandler) {
            super(looper);
            this.requestHandlerReference = new WeakReference(requestHandler);
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            RequestHandler requestHandler = (RequestHandler) this.requestHandlerReference.get();
            if (requestHandler != null) {
                switch (message.arg1) {
                    case SEND /*72400*/:
                        requestHandler.sendInternal((ActivityPackage) message.obj);
                    default:
                }
            }
        }
    }

    public RequestHandler(IPackageHandler iPackageHandler) {
        super(Constants.LOGTAG, 1);
        setDaemon(true);
        start();
        this.logger = AdjustFactory.getLogger();
        this.internalHandler = new InternalHandler(getLooper(), this);
        init(iPackageHandler);
    }

    public void init(IPackageHandler iPackageHandler) {
        this.packageHandler = iPackageHandler;
    }

    public void sendPackage(ActivityPackage activityPackage) {
        Message obtain = Message.obtain();
        obtain.arg1 = 72400;
        obtain.obj = activityPackage;
        this.internalHandler.sendMessage(obtain);
    }

    private void sendInternal(ActivityPackage activityPackage) {
        try {
            requestFinished(Util.readHttpResponse(Util.createPOSTHttpsURLConnection(Constants.BASE_URL + activityPackage.getPath(), activityPackage.getClientSdk(), activityPackage.getParameters())));
        } catch (Throwable e) {
            sendNextPackage(activityPackage, "Failed to encode parameters", e);
        } catch (Throwable e2) {
            closePackage(activityPackage, "Request timed out", e2);
        } catch (Throwable e22) {
            closePackage(activityPackage, "Request failed", e22);
        } catch (Throwable e222) {
            sendNextPackage(activityPackage, "Runtime exception", e222);
        }
    }

    private void requestFinished(JSONObject jSONObject) {
        if (jSONObject == null) {
            this.packageHandler.closeFirstPackage();
            return;
        }
        this.packageHandler.finishedTrackingActivity(jSONObject);
        this.packageHandler.sendNextPackage();
    }

    private void closePackage(ActivityPackage activityPackage, String str, Throwable th) {
        String failureMessage = activityPackage.getFailureMessage();
        String reasonString = getReasonString(str, th);
        this.logger.error("%s. (%s) Will retry later", failureMessage, reasonString);
        this.packageHandler.closeFirstPackage();
    }

    private void sendNextPackage(ActivityPackage activityPackage, String str, Throwable th) {
        String failureMessage = activityPackage.getFailureMessage();
        String reasonString = getReasonString(str, th);
        this.logger.error("%s. (%s)", failureMessage, reasonString);
        this.packageHandler.sendNextPackage();
    }

    private String getReasonString(String str, Throwable th) {
        if (th != null) {
            return String.format(Locale.US, "%s: %s", new Object[]{str, th});
        }
        return String.format(Locale.US, "%s", new Object[]{str});
    }
}
