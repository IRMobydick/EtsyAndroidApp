package com.foresee.mobileReplay.recorder;

import android.app.Activity;
import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BlurMaskFilter;
import android.graphics.BlurMaskFilter.Blur;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.support.v4.internal.view.SupportMenu;
import android.util.Log;
import android.util.Pair;
import android.view.Display;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.AbsListView.OnScrollListener;
import com.foresee.mobileReplay.data.PersistenceException;
import com.foresee.mobileReplay.data.SessionRepository;
import com.foresee.mobileReplay.domain.DeviceRotationEventData;
import com.foresee.mobileReplay.domain.DeviceRotationEventData.RotationType;
import com.foresee.mobileReplay.domain.DiffSet;
import com.foresee.mobileReplay.domain.PageChangeEventData;
import com.foresee.mobileReplay.domain.SessionEvent;
import com.foresee.mobileReplay.domain.SessionEvents;
import com.foresee.mobileReplay.imageDiff.DiffingParamStrategy;
import com.foresee.mobileReplay.interfaceActivity.InterfaceActivityHandler;
import com.foresee.mobileReplay.jobQueue.DeleteImageJob;
import com.foresee.mobileReplay.jobQueue.EndSessionJob;
import com.foresee.mobileReplay.jobQueue.ImageDiffJob;
import com.foresee.mobileReplay.jobQueue.ImageJob;
import com.foresee.mobileReplay.jobQueue.JobQueueService;
import com.foresee.mobileReplay.perfLog.OperationTimer;
import com.foresee.mobileReplay.recorder.ScheduledCaptureStrategy.StrategyContext;
import com.foresee.mobileReplay.recorder.ViewCaptureRunnable.ViewCaptureRunnableContext;
import com.foresee.mobileReplay.touchTracking.EventStorage;
import com.foresee.mobileReplay.touchTracking.TouchHandler;
import com.foresee.sdk.instrumentation.AutoPageChangeDisabled;
import com.foresee.sdk.instrumentation.CaptureRateOverride;
import com.foresee.sdk.instrumentation.ForeSeeScrollListener;
import com.foresee.sdk.instrumentation.GlobalActivityListener;
import com.foresee.sdk.instrumentation.SessionReplayPage;
import com.google.inject.Inject;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScreenRecorder implements CaptureStrategyContext, EventPublisher, StrategyContext, ScheduledHandsOnCaptureStrategy.StrategyContext, ScreenRecorderContext, ViewCaptureRunnableContext, EventStorage {
    public static final int ATTACHED_STATE_CHECK_INTERVAL = 50;
    public static final int DEFAULT_CAPTURE_FREQ = 250;
    public static final int DEFAULT_IN_TOUCH_CAPTURE_FREQ = 2000;
    public static final int MAX_READY_WAIT_TIME = 2000;
    public static final int ORIENTATION_SAMPLING_FREQ = 500;
    public static final double ORIENTATION_SAMPLING_THRESHOLD = 10.0d;
    public static final int TARGET_SIZE = 1049000;
    public static final int TOUCH_FREQ = 50;
    @Inject
    private InterfaceActivityHandler activityHandler;
    private Application application;
    private int captureFrequency;
    private ScheduledCaptureRateChangeListener captureRateListener;
    private ThreadSafeCaptureStateProxy captureStateProxy;
    private CaptureStrategy captureStrategy;
    private Activity currentActivity;
    private Bitmap currentBitmap;
    private String currentCaptureName;
    private Pair<Float, Float> currentDims;
    private String currentPageTitle;
    private RotationType currentRotation;
    private List<DiffSet> diffSets;
    private DiffingParamStrategy diffingParamStrategy;
    private float downSampleRatio;
    private JobQueueService jobQueueService;
    public Paint maskPaint;
    private Paint maskVisualizerPaint;
    private boolean maskingDebugEnabled;
    private MaskingPolicy maskingPolicy;
    private View rootView;
    private OnScrollListener scrollListener;
    private SessionEvents sessionEvents;
    private String sessionGroupId;
    private String sessionId;
    private SessionRepository sessionRepository;
    private OnTouchListener touchEventListener;
    private TouchHandler touchHandler;
    private ViewCaptureRunnable viewCaptureRunnable;
    private HierarchyWalker walker;
    private WindowManager windowManager;

    @Inject
    public ScreenRecorder(SessionRepository sessionRepository, JobQueueService jobQueueService, float f, MaskingPolicy maskingPolicy, DiffingParamStrategy diffingParamStrategy) {
        this.currentRotation = RotationType.UNDEFINED;
        this.sessionEvents = new SessionEvents();
        this.diffSets = new ArrayList();
        this.captureFrequency = DEFAULT_CAPTURE_FREQ;
        this.sessionRepository = sessionRepository;
        this.jobQueueService = jobQueueService;
        this.downSampleRatio = f;
        this.maskingPolicy = maskingPolicy;
        this.diffingParamStrategy = diffingParamStrategy;
        this.captureStateProxy = new ThreadSafeCaptureStateProxy(new PendingCaptureState(), this, false);
        this.currentBitmap = null;
        this.maskPaint = new Paint();
        this.maskPaint.setColor(-7829368);
        this.maskPaint.setMaskFilter(new BlurMaskFilter(3.0f, Blur.INNER));
        this.maskPaint.setAntiAlias(true);
        this.maskVisualizerPaint = new Paint();
        this.maskVisualizerPaint.setColor(SupportMenu.CATEGORY_MASK);
        this.maskVisualizerPaint.setMaskFilter(new BlurMaskFilter(3.0f, Blur.INNER));
        this.maskVisualizerPaint.setAntiAlias(true);
    }

    public void attach(Activity activity, String str, String str2) {
        this.currentActivity = activity;
        this.application = this.currentActivity.getApplication();
        this.windowManager = (WindowManager) this.application.getSystemService("window");
        this.sessionGroupId = str;
        this.sessionId = str2;
        if (this.currentActivity instanceof CaptureRateOverride) {
            int captureRate = ((CaptureRateOverride) this.currentActivity).getCaptureRate();
            if (captureRate <= 0) {
                captureRate = DEFAULT_CAPTURE_FREQ;
            }
            this.captureFrequency = captureRate;
        } else {
            this.captureFrequency = DEFAULT_CAPTURE_FREQ;
        }
        this.captureStrategy = new LayoutAwareCaptureStrategy(this, this, this.walker);
        this.captureStateProxy.attachActivity(activity);
    }

    public void detach(Activity activity) {
        if (this.currentActivity != null) {
            this.captureStrategy.onDetach();
            this.captureStateProxy.detachActivity();
            stopRecording();
            this.currentActivity = null;
            this.captureStrategy.detach();
        }
    }

    public Void startRecording() {
        if (this.currentActivity != null) {
            startRecording(this.currentActivity);
        }
        return null;
    }

    private void startRecording(Activity activity) {
        this.rootView = activity.getWindow().getDecorView().getRootView();
        if (this.activityHandler == null) {
            this.activityHandler = new InterfaceActivityHandler();
        }
        this.activityHandler.addOnScrollListener(this.scrollListener);
        this.rootView.getViewTreeObserver().addOnScrollChangedListener(this.activityHandler);
        if (this.captureStrategy instanceof GlobalActivityListener) {
            this.activityHandler.setForeSeeScrollListener((ForeSeeScrollListener) this.captureStrategy);
        }
        if (this.touchHandler == null) {
            this.touchHandler = new TouchHandler(this.sessionGroupId, this.sessionId, TOUCH_FREQ, this);
        } else {
            this.touchHandler.updateSessionIDs(this.sessionGroupId, this.sessionId);
        }
        this.touchHandler.addTouchListener(this.touchEventListener);
        if (this.walker == null) {
            this.walker = new HierarchyWalker(this.rootView, this.touchHandler, this.activityHandler, this.maskingPolicy);
        }
        this.captureStrategy.setWalker(this.walker);
        this.captureStrategy.setRootView(this.rootView);
        this.captureStrategy.requestWalk();
        this.captureStrategy.startCapture();
        this.rootView.setOnTouchListener(this.touchHandler);
    }

    public void stopRecording() {
        if (this.rootView != null) {
            this.rootView.setOnTouchListener(null);
        }
        if (this.walker != null) {
            this.walker.cleanUp();
        }
        if (this.captureStrategy != null) {
            this.captureStrategy.stopCapture();
        }
        if (this.touchHandler != null) {
            this.touchHandler.removeTouchListener(this.touchEventListener);
        }
    }

    public void abortRecording() {
        this.walker.cleanUp();
        this.captureStrategy.stopCapture();
    }

    public void reset() {
        this.currentCaptureName = null;
        this.currentRotation = RotationType.UNDEFINED;
        this.currentBitmap = null;
    }

    public SessionEvents getSessionEvents() {
        return this.sessionEvents;
    }

    public void persistSessionEvents() {
        try {
            this.sessionRepository.persistSessionEvents(this.sessionGroupId, this.sessionId, this.sessionEvents);
        } catch (PersistenceException e) {
            Log.e("FORESEE_DATA_CAPS", "There was an error persisting session events. Session will be deleted");
            this.sessionRepository.storageError();
        }
    }

    public void publishSessionEvent(String str, String str2, SessionEvent sessionEvent) {
        this.sessionEvents.getEvents(str, str2).add(sessionEvent);
    }

    public void publishPageEvent(String str, String str2, long j) {
        postPageEvent(str, str2, j, true);
    }

    public void publishPageEvent(String str) {
        if (this.currentActivity != null) {
            postPageEvent(str, this.currentActivity.getLocalClassName(), new Date().getTime(), false);
        }
    }

    private void postPageEvent(String str, String str2, long j, boolean z) {
        Log.d("FORESEE_CAPTURE", String.format("PageEvent (%s)", new Object[]{str}));
        publishSessionEvent(this.sessionGroupId, this.sessionId, new SessionEvent(new PageChangeEventData(str, str2, z), j));
    }

    private void requestCapture(View view, CaptureViewCallback captureViewCallback) {
        if (view.hasWindowFocus()) {
            this.captureStateProxy.requestCapture(view, captureViewCallback);
        }
    }

    public boolean captureView(View view, CaptureViewCallback captureViewCallback) {
        try {
            Bitmap createBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Config.ARGB_8888);
            OperationTimer operationTimer = new OperationTimer("Image capture", this.sessionId);
            operationTimer.start();
            this.viewCaptureRunnable = new ViewCaptureRunnable(this.sessionGroupId, this.sessionId, view, createBitmap, this.walker, new 1(this, operationTimer, view, captureViewCallback), this);
            return view.post(this.viewCaptureRunnable);
        } catch (OutOfMemoryError e) {
            Log.e("FORESEE_CAPTURE", "ViewCaptureRunnable - out of memory error");
            return false;
        }
    }

    public void onOrientationCaptured(String str, String str2, SessionEvent<DeviceRotationEventData> sessionEvent) {
        if (sessionEvent != null) {
            DeviceRotationEventData deviceRotationEventData = (DeviceRotationEventData) sessionEvent.getEventData().getData();
            if (deviceRotationEventData.getOrientation() != this.currentRotation) {
                publishSessionEvent(str, str2, sessionEvent);
                this.currentRotation = deviceRotationEventData.getOrientation();
            }
        }
    }

    public void onViewCaptured(String str, String str2, Bitmap bitmap, long j, MaskSet maskSet, View view) {
        if (view != this.rootView) {
            Log.e("FORESEE_CAPTURE", "Capture: view!=rootView");
        }
        if (bitmap != null && view == this.rootView) {
            float calculateScaleFactor = calculateScaleFactor(bitmap, TARGET_SIZE);
            Rect[] webMasks = maskSet.getWebMasks();
            int length = webMasks.length;
            int i = 0;
            while (i < length) {
                Rect rect = webMasks[i];
                if (rect == null || !rect.equals(WebViewMask.SKIP_MASK)) {
                    i++;
                } else {
                    Log.d("FORESEE_MASKING", "Skip mask!");
                    return;
                }
            }
            Bitmap scaleBitmap = scaleBitmap(bitmap, calculateScaleFactor);
            this.walker.applyRects(new Canvas(scaleBitmap), maskSet.getNativeMasks(), this.maskPaint, calculateScaleFactor);
            Bitmap copy = scaleBitmap.copy(Config.ARGB_8888, true);
            this.walker.applyRects(new Canvas(copy), maskSet.getWebMasks(), this.maskPaint, calculateScaleFactor);
            String format = String.format("%d.jpg", new Object[]{Long.valueOf(j)});
            if (scaleBitmap != null && this.currentBitmap != null && !scaleBitmap.sameAs(this.currentBitmap)) {
                Log.d("FORESEE_EXP_DEBUG", "Capture changed");
                this.sessionRepository.saveImage(str, str2, copy, format);
                this.jobQueueService.enqueueJob(new ImageDiffJob(str, str2, this.currentCaptureName, format, calculateScaleFactor, this.diffingParamStrategy.getTolerance(), this.diffingParamStrategy.getDiffRegionSize()));
                if (this.currentCaptureName != null) {
                    this.jobQueueService.enqueueJob(new DeleteImageJob(str, str2, this.currentCaptureName));
                }
                this.currentCaptureName = format;
                this.currentBitmap.recycle();
                this.currentBitmap = scaleBitmap;
            } else if (this.currentBitmap == null) {
                this.sessionRepository.saveImage(str, str2, copy, format);
                this.jobQueueService.enqueueJob(new ImageJob(str, str2, format, calculateScaleFactor));
                this.jobQueueService.enqueueJob(new DeleteImageJob(str, str2, format));
                this.currentBitmap = scaleBitmap;
            }
            applyMasksToVisualizer(view, bitmap, maskSet);
            copy.recycle();
        }
    }

    private void applyMasksToVisualizer(View view, Bitmap bitmap, MaskSet maskSet) {
        if (this.maskingDebugEnabled) {
            Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth() / 2, bitmap.getHeight() / 2, Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            this.walker.applyRects(canvas, maskSet.getNativeMasks(), this.maskVisualizerPaint);
            this.walker.applyRects(canvas, maskSet.getWebMasks(), this.maskVisualizerPaint);
            view.post(new 2(this, view, createBitmap));
        }
    }

    public void cancelCapture() {
        if (this.viewCaptureRunnable != null) {
            this.viewCaptureRunnable.cancel();
        }
    }

    public Void requestReady(Activity activity) {
        Log.d("FORESEE_CAPTURE", String.format("requestReady for activity: %s", new Object[]{activity.getLocalClassName()}));
        View rootView = activity.getWindow().getDecorView().getRootView();
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1);
        scheduledThreadPoolExecutor.schedule(new 3(this, rootView, scheduledThreadPoolExecutor), 0, TimeUnit.MILLISECONDS);
        return null;
    }

    public boolean recordPageChange(long j) {
        String resolvePageTitle = resolvePageTitle(this.currentActivity);
        String localClassName = this.currentActivity.getLocalClassName();
        boolean shouldLogPageChange = shouldLogPageChange(this.currentActivity, resolvePageTitle);
        if (shouldLogPageChange) {
            this.currentPageTitle = resolvePageTitle;
            publishPageEvent(resolvePageTitle, localClassName, j);
        }
        return shouldLogPageChange;
    }

    private Bitmap scaleBitmap(Bitmap bitmap, float f) {
        Matrix matrix = new Matrix();
        matrix.postScale(f, f);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    private float calculateScaleFactor(Bitmap bitmap, int i) {
        return this.downSampleRatio;
    }

    private static String resolvePageTitle(Activity activity) {
        String localClassName = activity.getLocalClassName();
        if (!(activity instanceof SessionReplayPage)) {
            return localClassName;
        }
        String sessionReplayPageName = ((SessionReplayPage) activity).sessionReplayPageName();
        return (sessionReplayPageName == null || sessionReplayPageName.isEmpty()) ? localClassName : sessionReplayPageName;
    }

    private boolean shouldLogPageChange(Activity activity, String str) {
        if ((activity instanceof AutoPageChangeDisabled) || (this.currentPageTitle != null && this.currentPageTitle.equals(str))) {
            return false;
        }
        return true;
    }

    public List<DiffSet> getDiffSets() {
        return this.diffSets;
    }

    public void endSession(String str, String str2) {
        persistSessionEvents();
        this.jobQueueService.enqueueJob(new EndSessionJob(str, str2));
        this.sessionEvents.getEventsMap().remove(this.sessionEvents.getKeyForSession(str, str2));
        reset();
    }

    public void maskView(View view) {
        this.maskingPolicy.maskView(view);
    }

    public void unmaskView(View view) {
        this.maskingPolicy.unmaskView(view);
    }

    public void setCaptureRate(int i) {
        if (this.captureRateListener != null) {
            this.captureFrequency = i;
            this.captureRateListener.onCaptureRateChanged(i);
        }
    }

    public void resetCaptureRate() {
        setCaptureRate(DEFAULT_CAPTURE_FREQ);
    }

    public int getCaptureRate() {
        return this.captureFrequency;
    }

    public void setCaptureRateChangeListener(ScheduledCaptureRateChangeListener scheduledCaptureRateChangeListener) {
        this.captureRateListener = scheduledCaptureRateChangeListener;
    }

    public void requestCapture() {
        requestCapture(this.rootView, new 4(this));
    }

    public void setTouchEventListener(OnTouchListener onTouchListener) {
        if (this.touchHandler != null) {
            this.touchHandler.removeTouchListener(this.touchEventListener);
        }
        this.touchEventListener = onTouchListener;
    }

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        if (this.activityHandler != null) {
            this.activityHandler.removeOnScrollListener(this.scrollListener);
        }
        this.scrollListener = onScrollListener;
    }

    public int getInTouchCaptureRate() {
        return MAX_READY_WAIT_TIME;
    }

    public int getRotation() {
        return this.windowManager.getDefaultDisplay().getRotation();
    }

    public Point getDisplayDimensions() {
        Display defaultDisplay = this.windowManager.getDefaultDisplay();
        Point point = new Point();
        if (VERSION.SDK_INT < 13) {
            point.set(defaultDisplay.getWidth(), defaultDisplay.getHeight());
        } else {
            defaultDisplay.getSize(point);
        }
        return point;
    }

    public List<WeakReference<View>> getMaskedViews() {
        return this.maskingPolicy.getMaskedViews();
    }

    public void onInterfaceActivity() {
        this.captureStrategy.onInterfaceActivity();
    }

    public void fragmentViewCreated(View view) {
        if (this.walker != null) {
            this.walker.fragmentViewCreated(view);
        }
    }

    public void setMaskingDebugEnabled(boolean z) {
        this.maskingDebugEnabled = z;
    }

    public void onMaskInvalidation() {
    }
}
