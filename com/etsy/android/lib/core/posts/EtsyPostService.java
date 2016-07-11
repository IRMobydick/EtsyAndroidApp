package com.etsy.android.lib.core.posts;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.NonNull;
import com.etsy.android.lib.core.aj;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.util.CrashUtil;
import com.etsy.android.lib.util.NetworkUtils;

public class EtsyPostService extends IntentService {
    public static final String ACTION_RUN_NEXT = "ACTION_RUN_NEXT";
    private static final String TAG;
    private final EtsyPostManager mPostManager;

    static {
        TAG = EtsyDebug.m1891a(EtsyPostService.class);
    }

    public EtsyPostService() {
        super(EtsyPostService.class.toString());
        this.mPostManager = aj.m1101a().m1124j();
    }

    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            EtsyDebug.m1914c(TAG, "onHandleIntent %s", intent.toString());
        }
        if (intent != null && intent.getAction() != null && intent.getAction().equals(ACTION_RUN_NEXT)) {
            runNext();
        }
    }

    private void runNext() {
        EtsyDebug.m1912c(TAG, "runNext STARTED");
        EtsyPostManager b = this.mPostManager.m1667b();
        while (b != null) {
            if (NetworkUtils.m3107a().m3114b()) {
                long a = b.m1669a();
                PersistentRequest b2 = b.m1670b();
                if (b2 != null) {
                    try {
                        if (b2.getPostInfo() != null) {
                            b2.getPostInfo().incrementAttempt();
                        }
                        b2.setPersisted(true);
                        if (b2.getVersionCode() > b.m1671c()) {
                            b2.onUpgraded(b.m1671c(), b2.getVersionCode());
                        }
                        if (b2.isValidRequest()) {
                            runPost(a, b2);
                        } else {
                            EtsyDebug.m1914c(TAG, "runNext - invalid post %s type %s - skipping", Long.valueOf(a), b2.getClass());
                            removePost(a);
                            repostIfPossible(b2);
                        }
                    } catch (Exception e) {
                        b = e;
                        EtsyDebug.m1917d(TAG, "runNext ERROR", (Throwable) b);
                        if (b2 != null) {
                            removePost(a);
                            if (b2.onPersistentResult(getApplicationContext(), null)) {
                                repostIfPossible(b2);
                            }
                        }
                        CrashUtil.m3037a().m3045a((Throwable) b);
                    } finally {
                        EtsyDebug.m1912c(TAG, "runNext --- CHECKING FOR MORE");
                        this.mPostManager.m1667b();
                    }
                } else {
                    EtsyDebug.m1920e(TAG, "runNext - bad post removing %s", Long.valueOf(a));
                    removePost(a);
                }
                EtsyDebug.m1912c(TAG, "runNext --- CHECKING FOR MORE");
                b = this.mPostManager.m1667b();
            } else {
                EtsyDebug.m1912c(TAG, "runNext ABORTED - no network available");
                return;
            }
        }
        EtsyDebug.m1912c(TAG, "runNext FINISHED - nothing more to run");
    }

    private <Result> void runPost(long j, @NonNull PersistentRequest<?, Result> persistentRequest) {
        EtsyDebug.m1914c(TAG, "runPost id:%s", Long.valueOf(j));
        if (persistentRequest.getRequest() != null) {
            if (persistentRequest.onPersistentResult(getApplicationContext(), persistentRequest.onPersistentRun(aj.m1101a().m1123i()))) {
                repostIfPossible(persistentRequest);
            }
        } else {
            EtsyDebug.m1920e(TAG, "runPost - bad post removing %s", Long.valueOf(j));
            if (persistentRequest.onPersistentResult(getApplicationContext(), null)) {
                EtsyDebug.m1920e(TAG, "runPost - requested retry, but can't honor because request is missing/corrupted", Long.valueOf(j));
            }
        }
        removePost(j);
    }

    private <Result> void repostIfPossible(@NonNull PersistentRequest<?, Result> persistentRequest) {
        if (persistentRequest.getRequest() != null && persistentRequest.getPostInfo() != null) {
            persistentRequest.getPostInfo().calculateNextRetryTime();
            if (persistentRequest.getPostInfo().shouldTryAgain()) {
                long nextRunAfterTime = persistentRequest.getPostInfo().getNextRunAfterTime();
                this.mPostManager.m1665a((PersistentRequest) persistentRequest, persistentRequest.getPostInfo().canRunNow(nextRunAfterTime), nextRunAfterTime);
            }
        }
    }

    private void removePost(long j) {
        this.mPostManager.m1662a(j);
    }
}
