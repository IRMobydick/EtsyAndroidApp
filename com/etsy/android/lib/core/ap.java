package com.etsy.android.lib.core;

import android.os.AsyncTask;
import android.os.Build.VERSION;

/* compiled from: ThreadedAsyncTaskHelper */
public class ap {
    public static <T> void m1142a(AsyncTask<T, ?, ?> asyncTask, T... tArr) {
        if (VERSION.SDK_INT < 11) {
            asyncTask.execute(tArr);
        } else {
            asyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, tArr);
        }
    }
}
