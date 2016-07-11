package com.etsy.android.uikit.util;

import android.content.res.Resources.NotFoundException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import com.etsy.android.lib.config.EtsyConfig;
import com.etsy.android.lib.config.EtsyConfigKeys;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.logger.ITrackedObject;
import com.etsy.android.lib.logger.ViewClickAnalyticsLog.ViewAction;
import com.etsy.android.lib.logger.ad;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.datatypes.TrackedEtsyId;
import com.etsy.android.lib.models.datatypes.TrackedObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* renamed from: com.etsy.android.uikit.util.u */
public abstract class TrackingViewListener {
    private static final String TAG;
    private ArrayList<ITrackedObject> mEventTrackedObjects;
    private ArrayList<ITrackedObject> mTrackedObjects;

    static {
        TAG = EtsyDebug.m1891a(TrackingViewListener.class);
    }

    public TrackingViewListener(@NonNull AnalyticsLogAttribute analyticsLogAttribute, @Nullable Object obj) {
        this(new TrackedObject(analyticsLogAttribute, obj));
    }

    public TrackingViewListener(@NonNull AnalyticsLogAttribute analyticsLogAttribute, @Nullable EtsyId etsyId) {
        this(new TrackedEtsyId(analyticsLogAttribute, etsyId));
    }

    public TrackingViewListener(ITrackedObject... iTrackedObjectArr) {
        addTrackedObjects(iTrackedObjectArr);
    }

    public void onPreTrack() {
    }

    private void onPostTrack() {
        if (this.mEventTrackedObjects != null) {
            this.mEventTrackedObjects.clear();
        }
    }

    protected final void trackAction(@NonNull View view, @NonNull ViewAction viewAction) {
        EtsyDebug.m1912c(TAG, "trackAction: " + viewAction.name());
        if (EtsyConfig.m837a().m869d() != null && EtsyConfig.m837a().m869d().m885c(EtsyConfigKeys.by)) {
            ad b = AdHocEventCompatBuilder.m5506b(view);
            if (b != null) {
                String viewName = getViewName(view);
                onPreTrack();
                EtsyDebug.m1912c(TAG, "Tracking click on " + viewName + ", in class: " + view.getClass().getSimpleName() + " in: " + b.m1846b());
                b.m1858a(viewName, viewAction, getTrackingParameters());
                onPostTrack();
            }
        }
    }

    public final void addEventTrackedAttribute(@NonNull AnalyticsLogAttribute analyticsLogAttribute, @Nullable Object obj) {
        addTrackedObjects(new TrackedObject(analyticsLogAttribute, obj));
    }

    public final void addEventTrackedAttribute(@NonNull AnalyticsLogAttribute analyticsLogAttribute, @Nullable EtsyId etsyId) {
        addTrackedObjects(new TrackedEtsyId(analyticsLogAttribute, etsyId));
    }

    public final void addEventTrackedObjects(ITrackedObject... iTrackedObjectArr) {
        if (this.mEventTrackedObjects == null) {
            this.mEventTrackedObjects = new ArrayList(iTrackedObjectArr.length);
        } else {
            this.mEventTrackedObjects.ensureCapacity(this.mEventTrackedObjects.size() + iTrackedObjectArr.length);
        }
        for (Object obj : iTrackedObjectArr) {
            if (obj != null) {
                this.mEventTrackedObjects.add(obj);
            }
        }
    }

    private void addTrackedObjects(ITrackedObject... iTrackedObjectArr) {
        if (this.mTrackedObjects == null) {
            this.mTrackedObjects = new ArrayList(iTrackedObjectArr.length);
        } else {
            this.mTrackedObjects.ensureCapacity(this.mTrackedObjects.size() + iTrackedObjectArr.length);
        }
        for (Object obj : iTrackedObjectArr) {
            if (obj != null) {
                this.mTrackedObjects.add(obj);
            }
        }
    }

    @NonNull
    protected final String getViewName(@NonNull View view) {
        EtsyDebug.m1912c(TAG, "getViewName");
        try {
            return view.getResources().getResourceEntryName(view.getId());
        } catch (NotFoundException e) {
            try {
                return view.getResources().getResourceName(view.getId());
            } catch (NotFoundException e2) {
                return view.getClass().getSimpleName();
            }
        }
    }

    @Nullable
    protected final HashMap<AnalyticsLogAttribute, Object> getTrackingParameters() {
        HashMap<AnalyticsLogAttribute, Object> hashMap = null;
        if (this.mTrackedObjects == null && this.mEventTrackedObjects == null) {
            return null;
        }
        HashMap<AnalyticsLogAttribute, Object> hashMap2;
        Iterator it;
        Map trackingParameters;
        if (this.mTrackedObjects != null) {
            hashMap2 = new HashMap(this.mTrackedObjects.size());
            it = this.mTrackedObjects.iterator();
            while (it.hasNext()) {
                trackingParameters = ((ITrackedObject) it.next()).getTrackingParameters();
                if (trackingParameters != null) {
                    hashMap2.putAll(trackingParameters);
                }
            }
            hashMap = hashMap2;
        }
        if (this.mEventTrackedObjects != null) {
            if (hashMap == null) {
                hashMap2 = new HashMap(this.mEventTrackedObjects.size());
            } else {
                hashMap2 = hashMap;
            }
            it = this.mEventTrackedObjects.iterator();
            while (it.hasNext()) {
                trackingParameters = ((ITrackedObject) it.next()).getTrackingParameters();
                if (trackingParameters != null) {
                    hashMap2.putAll(trackingParameters);
                }
            }
        } else {
            hashMap2 = hashMap;
        }
        return hashMap2;
    }
}
