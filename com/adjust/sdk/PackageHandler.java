package com.adjust.sdk;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;

public class PackageHandler extends HandlerThread implements IPackageHandler {
    private static final String PACKAGE_QUEUE_FILENAME = "AdjustIoPackageQueue";
    private static final String PACKAGE_QUEUE_NAME = "Package queue";
    private IActivityHandler activityHandler;
    private Context context;
    private final InternalHandler internalHandler;
    private AtomicBoolean isSending;
    private ILogger logger;
    private List<ActivityPackage> packageQueue;
    private boolean paused;
    private IRequestHandler requestHandler;

    final class InternalHandler extends Handler {
        private static final int ADD = 2;
        private static final int INIT = 1;
        private static final int SEND_FIRST = 4;
        private static final int SEND_NEXT = 3;
        private final WeakReference<PackageHandler> packageHandlerReference;

        protected InternalHandler(Looper looper, PackageHandler packageHandler) {
            super(looper);
            this.packageHandlerReference = new WeakReference(packageHandler);
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            PackageHandler packageHandler = (PackageHandler) this.packageHandlerReference.get();
            if (packageHandler != null) {
                switch (message.arg1) {
                    case INIT /*1*/:
                        packageHandler.initInternal();
                    case ADD /*2*/:
                        packageHandler.addInternal((ActivityPackage) message.obj);
                    case SEND_NEXT /*3*/:
                        packageHandler.sendNextInternal();
                    case SEND_FIRST /*4*/:
                        packageHandler.sendFirstInternal();
                    default:
                }
            }
        }
    }

    public PackageHandler(IActivityHandler iActivityHandler, Context context, boolean z) {
        super(Constants.LOGTAG, 1);
        setDaemon(true);
        start();
        this.internalHandler = new InternalHandler(getLooper(), this);
        this.logger = AdjustFactory.getLogger();
        init(iActivityHandler, context, z);
        Message obtain = Message.obtain();
        obtain.arg1 = 1;
        this.internalHandler.sendMessage(obtain);
    }

    public void init(IActivityHandler iActivityHandler, Context context, boolean z) {
        this.activityHandler = iActivityHandler;
        this.context = context;
        this.paused = z;
    }

    public void addPackage(ActivityPackage activityPackage) {
        Message obtain = Message.obtain();
        obtain.arg1 = 2;
        obtain.obj = activityPackage;
        this.internalHandler.sendMessage(obtain);
    }

    public void sendFirstPackage() {
        Message obtain = Message.obtain();
        obtain.arg1 = 4;
        this.internalHandler.sendMessage(obtain);
    }

    public void sendNextPackage() {
        Message obtain = Message.obtain();
        obtain.arg1 = 3;
        this.internalHandler.sendMessage(obtain);
    }

    public void closeFirstPackage() {
        this.isSending.set(false);
    }

    public void pauseSending() {
        this.paused = true;
    }

    public void resumeSending() {
        this.paused = false;
    }

    public void finishedTrackingActivity(JSONObject jSONObject) {
        this.activityHandler.finishedTrackingActivity(jSONObject);
    }

    private void initInternal() {
        this.requestHandler = AdjustFactory.getRequestHandler(this);
        this.isSending = new AtomicBoolean();
        readPackageQueue();
    }

    private void addInternal(ActivityPackage activityPackage) {
        if (!activityPackage.getActivityKind().equals(ActivityKind.CLICK) || this.packageQueue.isEmpty()) {
            this.packageQueue.add(activityPackage);
        } else {
            this.packageQueue.add(1, activityPackage);
        }
        this.logger.debug("Added package %d (%s)", Integer.valueOf(this.packageQueue.size()), activityPackage);
        this.logger.verbose("%s", activityPackage.getExtendedString());
        writePackageQueue();
    }

    private void sendFirstInternal() {
        if (!this.packageQueue.isEmpty()) {
            if (this.paused) {
                this.logger.debug("Package handler is paused", new Object[0]);
            } else if (this.isSending.getAndSet(true)) {
                this.logger.verbose("Package handler is already sending", new Object[0]);
            } else {
                this.requestHandler.sendPackage((ActivityPackage) this.packageQueue.get(0));
            }
        }
    }

    private void sendNextInternal() {
        this.packageQueue.remove(0);
        writePackageQueue();
        this.isSending.set(false);
        sendFirstInternal();
    }

    private void readPackageQueue() {
        this.packageQueue = (List) Util.readObject(this.context, PACKAGE_QUEUE_FILENAME, PACKAGE_QUEUE_NAME);
        if (this.packageQueue != null) {
            this.logger.debug("Package handler read %d packages", Integer.valueOf(this.packageQueue.size()));
            return;
        }
        this.packageQueue = new ArrayList();
    }

    private void writePackageQueue() {
        Util.writeObject(this.packageQueue, this.context, PACKAGE_QUEUE_FILENAME, PACKAGE_QUEUE_NAME);
        this.logger.debug("Package handler wrote %d packages", Integer.valueOf(this.packageQueue.size()));
    }

    public static Boolean deletePackageQueue(Context context) {
        return Boolean.valueOf(context.deleteFile(PACKAGE_QUEUE_FILENAME));
    }
}
