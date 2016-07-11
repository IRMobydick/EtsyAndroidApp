package com.appboy.ui.inappmessage;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import com.appboy.Appboy;
import com.appboy.AppboyImageUtils;
import com.appboy.Constants;
import com.appboy.IAppboyNavigator;
import com.appboy.enums.inappmessage.ClickAction;
import com.appboy.enums.inappmessage.SlideFrom;
import com.appboy.events.IEventSubscriber;
import com.appboy.events.InAppMessageEvent;
import com.appboy.models.IInAppMessage;
import com.appboy.models.IInAppMessageHtml;
import com.appboy.models.IInAppMessageImmersive;
import com.appboy.models.InAppMessageFull;
import com.appboy.models.InAppMessageHtmlFull;
import com.appboy.models.InAppMessageModal;
import com.appboy.models.InAppMessageSlideup;
import com.appboy.models.MessageButton;
import com.appboy.models.outgoing.AppboyProperties;
import com.appboy.push.AppboyNotificationUtils;
import com.appboy.support.AppboyLogger;
import com.appboy.support.BundleUtils;
import com.appboy.ui.AppboyNavigator;
import com.appboy.ui.C0401R;
import com.appboy.ui.actions.ActionFactory;
import com.appboy.ui.actions.IAction;
import com.appboy.ui.inappmessage.listeners.IHtmlInAppMessageActionListener;
import com.appboy.ui.inappmessage.listeners.IInAppMessageManagerListener;
import com.appboy.ui.inappmessage.listeners.IInAppMessageViewLifecycleListener;
import com.appboy.ui.inappmessage.listeners.IInAppMessageWebViewClientListener;
import com.appboy.ui.inappmessage.views.AppboyInAppMessageFullView;
import com.appboy.ui.inappmessage.views.AppboyInAppMessageHtmlFullView;
import com.appboy.ui.inappmessage.views.AppboyInAppMessageModalView;
import com.appboy.ui.inappmessage.views.AppboyInAppMessageSlideupView;
import com.appboy.ui.support.AnimationUtils;
import com.appboy.ui.support.FrescoLibraryUtils;
import com.appboy.ui.support.StringUtils;
import com.appboy.ui.support.ViewUtils;
import com.appboy.ui.support.WebContentUtils;
import com.etsy.android.uikit.view.FullImageView;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.request.ImageRequest;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicBoolean;

public final class AppboyInAppMessageManager {
    private static final String HTML_IAM_CUSTOM_EVENT_NAME_KEY = "name";
    private static final String TAG;
    private static volatile AppboyInAppMessageManager sInstance;
    private Activity mActivity;
    private Context mApplicationContext;
    private boolean mCanUseFresco;
    private IInAppMessage mCarryoverInAppMessageBase;
    private IHtmlInAppMessageActionListener mCustomHtmlInAppMessageActionListener;
    private IInAppMessageAnimationFactory mCustomInAppMessageAnimationFactory;
    private IInAppMessageManagerListener mCustomInAppMessageManagerListener;
    private IInAppMessageViewFactory mCustomInAppMessageViewFactory;
    private final IAppboyNavigator mDefaultAppboyNavigator;
    private IHtmlInAppMessageActionListener mDefaultHtmlInAppMessageActionListener;
    private IInAppMessageManagerListener mDefaultInAppMessageManagerListener;
    private AtomicBoolean mDisplayingInAppMessage;
    private IInAppMessageAnimationFactory mInAppMessageAnimationFactory;
    private final Stack<IInAppMessage> mInAppMessageBaseStack;
    private IEventSubscriber<InAppMessageEvent> mInAppMessageEventSubscriber;
    private IInAppMessageViewFactory mInAppMessageFullViewFactory;
    private IInAppMessageViewFactory mInAppMessageHtmlFullViewFactory;
    private IInAppMessageViewFactory mInAppMessageModalViewFactory;
    private IInAppMessageViewFactory mInAppMessageSlideupViewFactory;
    private final IInAppMessageViewLifecycleListener mInAppMessageViewLifecycleListener;
    private InAppMessageViewWrapper mInAppMessageViewWrapper;
    private final IInAppMessageWebViewClientListener mInAppMessageWebViewClientListener;

    /* renamed from: com.appboy.ui.inappmessage.AppboyInAppMessageManager.13 */
    /* synthetic */ class AnonymousClass13 {
        static final /* synthetic */ int[] $SwitchMap$com$appboy$enums$inappmessage$ClickAction;
        static final /* synthetic */ int[] $SwitchMap$com$appboy$ui$inappmessage$InAppMessageOperation;

        static {
            $SwitchMap$com$appboy$enums$inappmessage$ClickAction = new int[ClickAction.values().length];
            try {
                $SwitchMap$com$appboy$enums$inappmessage$ClickAction[ClickAction.NEWS_FEED.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$appboy$enums$inappmessage$ClickAction[ClickAction.URI.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$appboy$enums$inappmessage$ClickAction[ClickAction.NONE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            $SwitchMap$com$appboy$ui$inappmessage$InAppMessageOperation = new int[InAppMessageOperation.values().length];
            try {
                $SwitchMap$com$appboy$ui$inappmessage$InAppMessageOperation[InAppMessageOperation.DISPLAY_NOW.ordinal()] = 1;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$appboy$ui$inappmessage$InAppMessageOperation[InAppMessageOperation.DISPLAY_LATER.ordinal()] = 2;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$appboy$ui$inappmessage$InAppMessageOperation[InAppMessageOperation.DISCARD.ordinal()] = 3;
            } catch (NoSuchFieldError e6) {
            }
        }
    }

    /* renamed from: com.appboy.ui.inappmessage.AppboyInAppMessageManager.1 */
    class C04021 implements Runnable {
        final /* synthetic */ IInAppMessage val$inAppMessage;

        C04021(IInAppMessage iInAppMessage) {
            this.val$inAppMessage = iInAppMessage;
        }

        public void run() {
            new AsyncInAppMessageDisplayer().execute(new IInAppMessage[]{this.val$inAppMessage});
        }
    }

    /* renamed from: com.appboy.ui.inappmessage.AppboyInAppMessageManager.2 */
    class C04032 implements Runnable {
        final /* synthetic */ IInAppMessageHtml val$inAppMessageHtml;

        C04032(IInAppMessageHtml iInAppMessageHtml) {
            this.val$inAppMessageHtml = iInAppMessageHtml;
        }

        public void run() {
            if (this.val$inAppMessageHtml != null) {
                WebContentUtils.clearInAppMessageLocalAssets(this.val$inAppMessageHtml);
            }
        }
    }

    /* renamed from: com.appboy.ui.inappmessage.AppboyInAppMessageManager.3 */
    class C04043 implements IEventSubscriber<InAppMessageEvent> {
        C04043() {
        }

        public void trigger(InAppMessageEvent inAppMessageEvent) {
            if (!AppboyInAppMessageManager.this.getInAppMessageManagerListener().onInAppMessageReceived(inAppMessageEvent.getInAppMessage())) {
                AppboyInAppMessageManager.this.addInAppMessage(inAppMessageEvent.getInAppMessage());
            }
        }
    }

    /* renamed from: com.appboy.ui.inappmessage.AppboyInAppMessageManager.4 */
    class C04054 implements IInAppMessageManagerListener {
        C04054() {
        }

        public boolean onInAppMessageReceived(IInAppMessage iInAppMessage) {
            return false;
        }

        public InAppMessageOperation beforeInAppMessageDisplayed(IInAppMessage iInAppMessage) {
            return InAppMessageOperation.DISPLAY_NOW;
        }

        public boolean onInAppMessageClicked(IInAppMessage iInAppMessage, InAppMessageCloser inAppMessageCloser) {
            return false;
        }

        public boolean onInAppMessageButtonClicked(MessageButton messageButton, InAppMessageCloser inAppMessageCloser) {
            return false;
        }

        public void onInAppMessageDismissed(IInAppMessage iInAppMessage) {
        }
    }

    /* renamed from: com.appboy.ui.inappmessage.AppboyInAppMessageManager.5 */
    class C04065 implements IHtmlInAppMessageActionListener {
        C04065() {
        }

        public void onCloseClicked(IInAppMessage iInAppMessage, String str, Bundle bundle) {
        }

        public boolean onNewsfeedClicked(IInAppMessage iInAppMessage, String str, Bundle bundle) {
            return false;
        }

        public boolean onCustomEventFired(IInAppMessage iInAppMessage, String str, Bundle bundle) {
            return false;
        }

        public boolean onOtherUrlAction(IInAppMessage iInAppMessage, String str, Bundle bundle) {
            return false;
        }
    }

    /* renamed from: com.appboy.ui.inappmessage.AppboyInAppMessageManager.6 */
    class C04076 implements IInAppMessageViewFactory {
        C04076() {
        }

        public View createInAppMessageView(Activity activity, IInAppMessage iInAppMessage) {
            InAppMessageSlideup inAppMessageSlideup = (InAppMessageSlideup) iInAppMessage;
            AppboyInAppMessageSlideupView appboyInAppMessageSlideupView = (AppboyInAppMessageSlideupView) activity.getLayoutInflater().inflate(C0401R.layout.com_appboy_inappmessage_slideup, null);
            appboyInAppMessageSlideupView.inflateStubViews();
            if (AppboyInAppMessageManager.this.mCanUseFresco) {
                appboyInAppMessageSlideupView.setMessageSimpleDrawee(iInAppMessage.getImageUrl());
            } else {
                appboyInAppMessageSlideupView.setMessageImageView(inAppMessageSlideup.getBitmap());
            }
            appboyInAppMessageSlideupView.setMessageBackgroundColor(inAppMessageSlideup.getBackgroundColor());
            appboyInAppMessageSlideupView.setMessage(inAppMessageSlideup.getMessage());
            appboyInAppMessageSlideupView.setMessageTextColor(inAppMessageSlideup.getMessageTextColor());
            appboyInAppMessageSlideupView.setMessageIcon(inAppMessageSlideup.getIcon(), inAppMessageSlideup.getIconColor(), inAppMessageSlideup.getIconBackgroundColor());
            appboyInAppMessageSlideupView.setMessageChevron(inAppMessageSlideup.getChevronColor(), inAppMessageSlideup.getClickAction());
            appboyInAppMessageSlideupView.resetMessageMargins(iInAppMessage.getImageDownloadSuccessful());
            return appboyInAppMessageSlideupView;
        }
    }

    /* renamed from: com.appboy.ui.inappmessage.AppboyInAppMessageManager.7 */
    class C04087 implements IInAppMessageViewFactory {
        C04087() {
        }

        public View createInAppMessageView(Activity activity, IInAppMessage iInAppMessage) {
            InAppMessageModal inAppMessageModal = (InAppMessageModal) iInAppMessage;
            AppboyInAppMessageModalView appboyInAppMessageModalView = (AppboyInAppMessageModalView) activity.getLayoutInflater().inflate(C0401R.layout.com_appboy_inappmessage_modal, null);
            appboyInAppMessageModalView.inflateStubViews();
            if (AppboyInAppMessageManager.this.mCanUseFresco) {
                appboyInAppMessageModalView.setMessageSimpleDrawee(iInAppMessage.getImageUrl());
            } else {
                appboyInAppMessageModalView.setMessageImageView(inAppMessageModal.getBitmap());
            }
            appboyInAppMessageModalView.setMessageBackgroundColor(iInAppMessage.getBackgroundColor());
            appboyInAppMessageModalView.setMessage(iInAppMessage.getMessage());
            appboyInAppMessageModalView.setMessageTextColor(iInAppMessage.getMessageTextColor());
            appboyInAppMessageModalView.setMessageHeaderText(inAppMessageModal.getHeader());
            appboyInAppMessageModalView.setMessageHeaderTextColor(inAppMessageModal.getHeaderTextColor());
            appboyInAppMessageModalView.setModalFrameColor(inAppMessageModal.getModalFrameColor());
            appboyInAppMessageModalView.setMessageIcon(iInAppMessage.getIcon(), iInAppMessage.getIconColor(), iInAppMessage.getIconBackgroundColor());
            appboyInAppMessageModalView.setMessageButtons(inAppMessageModal.getMessageButtons());
            appboyInAppMessageModalView.setMessageCloseButtonColor(inAppMessageModal.getCloseButtonColor());
            appboyInAppMessageModalView.resetMessageMargins(iInAppMessage.getImageDownloadSuccessful());
            return appboyInAppMessageModalView;
        }
    }

    /* renamed from: com.appboy.ui.inappmessage.AppboyInAppMessageManager.8 */
    class C04098 implements IInAppMessageViewFactory {
        C04098() {
        }

        public View createInAppMessageView(Activity activity, IInAppMessage iInAppMessage) {
            InAppMessageFull inAppMessageFull = (InAppMessageFull) iInAppMessage;
            AppboyInAppMessageFullView appboyInAppMessageFullView = (AppboyInAppMessageFullView) activity.getLayoutInflater().inflate(C0401R.layout.com_appboy_inappmessage_full, null);
            appboyInAppMessageFullView.inflateStubViews();
            if (AppboyInAppMessageManager.this.mCanUseFresco) {
                appboyInAppMessageFullView.setMessageSimpleDrawee(iInAppMessage.getImageUrl());
            } else {
                appboyInAppMessageFullView.setMessageImageView(iInAppMessage.getBitmap());
            }
            appboyInAppMessageFullView.setMessageBackgroundColor(inAppMessageFull.getBackgroundColor());
            appboyInAppMessageFullView.setMessage(inAppMessageFull.getMessage());
            appboyInAppMessageFullView.setMessageTextColor(inAppMessageFull.getMessageTextColor());
            appboyInAppMessageFullView.setMessageHeaderText(inAppMessageFull.getHeader());
            appboyInAppMessageFullView.setMessageHeaderTextColor(inAppMessageFull.getHeaderTextColor());
            appboyInAppMessageFullView.setMessageButtons(inAppMessageFull.getMessageButtons());
            appboyInAppMessageFullView.setMessageCloseButtonColor(inAppMessageFull.getCloseButtonColor());
            appboyInAppMessageFullView.resetMessageMargins(iInAppMessage.getImageDownloadSuccessful());
            return appboyInAppMessageFullView;
        }
    }

    /* renamed from: com.appboy.ui.inappmessage.AppboyInAppMessageManager.9 */
    class C04109 implements IInAppMessageViewFactory {
        C04109() {
        }

        public View createInAppMessageView(Activity activity, IInAppMessage iInAppMessage) {
            AppboyInAppMessageHtmlFullView appboyInAppMessageHtmlFullView = (AppboyInAppMessageHtmlFullView) activity.getLayoutInflater().inflate(C0401R.layout.com_appboy_inappmessage_html_full, null);
            appboyInAppMessageHtmlFullView.setWebViewContent(iInAppMessage.getMessage(), ((InAppMessageHtmlFull) iInAppMessage).getLocalAssetsDirectoryUrl());
            appboyInAppMessageHtmlFullView.setInAppMessageWebViewClient(new InAppMessageWebViewClient(iInAppMessage, AppboyInAppMessageManager.this.mInAppMessageWebViewClientListener));
            return appboyInAppMessageHtmlFullView;
        }
    }

    class AsyncInAppMessageDisplayer extends AsyncTask<IInAppMessage, Integer, IInAppMessage> {

        /* renamed from: com.appboy.ui.inappmessage.AppboyInAppMessageManager.AsyncInAppMessageDisplayer.1 */
        class C04111 implements Runnable {
            final /* synthetic */ IInAppMessage val$inAppMessage;

            C04111(IInAppMessage iInAppMessage) {
                this.val$inAppMessage = iInAppMessage;
            }

            public void run() {
                AppboyLogger.m662d(AppboyInAppMessageManager.TAG, "Displaying in-app message.");
                AppboyInAppMessageManager.this.displayInAppMessage(this.val$inAppMessage);
            }
        }

        AsyncInAppMessageDisplayer() {
        }

        protected IInAppMessage doInBackground(IInAppMessage... iInAppMessageArr) {
            try {
                boolean prepareInAppMessageWithHtml;
                AppboyLogger.m662d(AppboyInAppMessageManager.TAG, "Starting asynchronous in-app message preparation.");
                IInAppMessage iInAppMessage = iInAppMessageArr[0];
                if (iInAppMessage instanceof InAppMessageHtmlFull) {
                    prepareInAppMessageWithHtml = prepareInAppMessageWithHtml(iInAppMessage);
                } else {
                    String imageUrl = iInAppMessage.getImageUrl();
                    if (StringUtils.isNullOrBlank(imageUrl)) {
                        AppboyLogger.m670w(AppboyInAppMessageManager.TAG, "In-app message has no image URL. Not downloading image from URL.");
                        return iInAppMessage;
                    } else if (AppboyInAppMessageManager.this.mCanUseFresco) {
                        prepareInAppMessageWithHtml = prepareInAppMessageWithFresco(iInAppMessage, imageUrl);
                    } else {
                        prepareInAppMessageWithHtml = prepareInAppMessageWithBitmapDownload(iInAppMessage, imageUrl);
                    }
                }
                if (prepareInAppMessageWithHtml) {
                    return iInAppMessage;
                }
                return null;
            } catch (Throwable e) {
                AppboyLogger.m665e(AppboyInAppMessageManager.TAG, "Error running AsyncInAppMessageDisplayer", e);
                return null;
            }
        }

        protected void onPostExecute(IInAppMessage iInAppMessage) {
            try {
                if (AppboyInAppMessageManager.this.mActivity == null) {
                    AppboyLogger.m664e(AppboyInAppMessageManager.TAG, "No activity is currently registered to receive in-app messages. Doing nothing.");
                    return;
                }
                AppboyLogger.m662d(AppboyInAppMessageManager.TAG, "Finished asynchronous in-app message preparation. Attempting to display in-app message.");
                if (iInAppMessage != null) {
                    AppboyInAppMessageManager.this.mActivity.runOnUiThread(new C04111(iInAppMessage));
                    return;
                }
                AppboyLogger.m664e(AppboyInAppMessageManager.TAG, "Cannot display the in-app message because the in-app message was null.");
                AppboyInAppMessageManager.this.mDisplayingInAppMessage.set(false);
            } catch (Throwable e) {
                AppboyLogger.m665e(AppboyInAppMessageManager.TAG, "Error running onPostExecute", e);
            }
        }

        boolean prepareInAppMessageWithHtml(IInAppMessage iInAppMessage) {
            if (AppboyInAppMessageManager.this.mApplicationContext == null) {
                AppboyLogger.m670w(AppboyInAppMessageManager.TAG, "Can't store HTML in-app message assets because cached application context is null.");
                return false;
            } else if (AppboyInAppMessageManager.this.mActivity == null) {
                AppboyLogger.m664e(AppboyInAppMessageManager.TAG, "Can't store HTML in-app message assets because activity is null.");
                return false;
            } else {
                InAppMessageHtmlFull inAppMessageHtmlFull = (InAppMessageHtmlFull) iInAppMessage;
                String localHtmlUrlFromRemoteUrl = WebContentUtils.getLocalHtmlUrlFromRemoteUrl(AppboyInAppMessageManager.this.mApplicationContext.getCacheDir(), inAppMessageHtmlFull.getAssetsZipRemoteUrl());
                if (StringUtils.isNullOrBlank(localHtmlUrlFromRemoteUrl)) {
                    AppboyLogger.m670w(AppboyInAppMessageManager.TAG, String.format("Download of html content to local directory failed for remote url: %s . Returned local url is: %s", new Object[]{inAppMessageHtmlFull.getAssetsZipRemoteUrl(), localHtmlUrlFromRemoteUrl}));
                    return false;
                }
                AppboyLogger.m662d(AppboyInAppMessageManager.TAG, "Local url for html in-app message is " + localHtmlUrlFromRemoteUrl);
                inAppMessageHtmlFull.setLocalAssetsDirectoryUrl(localHtmlUrlFromRemoteUrl);
                return true;
            }
        }

        private boolean prepareInAppMessageWithFresco(IInAppMessage iInAppMessage, String str) {
            DataSource prefetchToDiskCache = Fresco.getImagePipeline().prefetchToDiskCache(ImageRequest.fromUri(str), new Object());
            do {
            } while (!prefetchToDiskCache.isFinished());
            if (!prefetchToDiskCache.hasFailed()) {
                iInAppMessage.setImageDownloadSuccessful(true);
                prefetchToDiskCache.close();
                return true;
            } else if (prefetchToDiskCache.getFailureCause() == null) {
                AppboyLogger.m670w(AppboyInAppMessageManager.TAG, "Fresco disk prefetch failed with a null cause.");
                return false;
            } else {
                AppboyLogger.m670w(AppboyInAppMessageManager.TAG, "Fresco disk prefetch failed with cause: " + prefetchToDiskCache.getFailureCause().getMessage() + " with image url: " + str);
                return false;
            }
        }

        boolean prepareInAppMessageWithBitmapDownload(IInAppMessage iInAppMessage, String str) {
            if (iInAppMessage.getBitmap() == null) {
                iInAppMessage.setBitmap(AppboyImageUtils.downloadImageBitmap(str));
                if (iInAppMessage.getBitmap() == null) {
                    return false;
                }
                iInAppMessage.setImageDownloadSuccessful(true);
                return true;
            }
            AppboyLogger.m666i(AppboyInAppMessageManager.TAG, "In-app message already contains image bitmap. Not downloading image from URL.");
            return true;
        }
    }

    public AppboyInAppMessageManager() {
        this.mInAppMessageBaseStack = new Stack();
        this.mDefaultAppboyNavigator = new AppboyNavigator();
        this.mDisplayingInAppMessage = new AtomicBoolean(false);
        this.mDefaultInAppMessageManagerListener = new C04054();
        this.mDefaultHtmlInAppMessageActionListener = new C04065();
        this.mInAppMessageSlideupViewFactory = new C04076();
        this.mInAppMessageModalViewFactory = new C04087();
        this.mInAppMessageFullViewFactory = new C04098();
        this.mInAppMessageHtmlFullViewFactory = new C04109();
        this.mInAppMessageAnimationFactory = new IInAppMessageAnimationFactory() {
            private final int mShortAnimationDurationMillis;
            private final long sSlideupAnimationDurationMillis;

            {
                this.sSlideupAnimationDurationMillis = 400;
                this.mShortAnimationDurationMillis = Resources.getSystem().getInteger(17694720);
            }

            public Animation getOpeningAnimation(IInAppMessage iInAppMessage) {
                Animation alphaAnimation;
                if (!(iInAppMessage instanceof InAppMessageSlideup)) {
                    alphaAnimation = new AlphaAnimation(0.0f, FullImageView.ASPECT_RATIO_SQUARE);
                } else if (((InAppMessageSlideup) iInAppMessage).getSlideFrom() == SlideFrom.TOP) {
                    alphaAnimation = AnimationUtils.createVerticalAnimation(-1.0f, 0.0f, 400, false);
                } else {
                    alphaAnimation = AnimationUtils.createVerticalAnimation(FullImageView.ASPECT_RATIO_SQUARE, 0.0f, 400, false);
                }
                return AnimationUtils.setAnimationParams(alphaAnimation, (long) this.mShortAnimationDurationMillis, true);
            }

            public Animation getClosingAnimation(IInAppMessage iInAppMessage) {
                Animation alphaAnimation;
                if (!(iInAppMessage instanceof InAppMessageSlideup)) {
                    alphaAnimation = new AlphaAnimation(FullImageView.ASPECT_RATIO_SQUARE, 0.0f);
                } else if (((InAppMessageSlideup) iInAppMessage).getSlideFrom() == SlideFrom.TOP) {
                    alphaAnimation = AnimationUtils.createVerticalAnimation(0.0f, -1.0f, 400, false);
                } else {
                    alphaAnimation = AnimationUtils.createVerticalAnimation(0.0f, FullImageView.ASPECT_RATIO_SQUARE, 400, false);
                }
                return AnimationUtils.setAnimationParams(alphaAnimation, (long) this.mShortAnimationDurationMillis, false);
            }
        };
        this.mInAppMessageViewLifecycleListener = new IInAppMessageViewLifecycleListener() {
            public void beforeOpened(View view, IInAppMessage iInAppMessage) {
                AppboyLogger.m662d(AppboyInAppMessageManager.TAG, "InAppMessageViewWrapper.IInAppMessageViewLifecycleListener.beforeOpened called.");
                iInAppMessage.logImpression();
            }

            public void afterOpened(View view, IInAppMessage iInAppMessage) {
                AppboyLogger.m662d(AppboyInAppMessageManager.TAG, "InAppMessageViewWrapper.IInAppMessageViewLifecycleListener.afterOpened called.");
            }

            public void beforeClosed(View view, IInAppMessage iInAppMessage) {
                AppboyLogger.m662d(AppboyInAppMessageManager.TAG, "InAppMessageViewWrapper.IInAppMessageViewLifecycleListener.beforeClosed called.");
            }

            public void afterClosed(IInAppMessage iInAppMessage) {
                AppboyInAppMessageManager.this.mInAppMessageViewWrapper = null;
                AppboyLogger.m662d(AppboyInAppMessageManager.TAG, "InAppMessageViewWrapper.IInAppMessageViewLifecycleListener.afterClosed called.");
                AppboyInAppMessageManager.this.mDisplayingInAppMessage.set(false);
                if (iInAppMessage instanceof IInAppMessageHtml) {
                    AppboyInAppMessageManager.this.startClearHtmlInAppMessageAssetsThread((IInAppMessageHtml) iInAppMessage);
                }
            }

            public void onClicked(InAppMessageCloser inAppMessageCloser, View view, IInAppMessage iInAppMessage) {
                AppboyLogger.m662d(AppboyInAppMessageManager.TAG, "InAppMessageViewWrapper.IInAppMessageViewLifecycleListener.onClicked called.");
                if (iInAppMessage.getClickAction() != ClickAction.NONE) {
                    iInAppMessage.logClick();
                }
                if (!AppboyInAppMessageManager.this.getInAppMessageManagerListener().onInAppMessageClicked(iInAppMessage, inAppMessageCloser)) {
                    performInAppMessageClicked(iInAppMessage, inAppMessageCloser);
                }
            }

            public void onButtonClicked(InAppMessageCloser inAppMessageCloser, MessageButton messageButton, IInAppMessageImmersive iInAppMessageImmersive) {
                AppboyLogger.m662d(AppboyInAppMessageManager.TAG, "InAppMessageViewWrapper.IInAppMessageViewLifecycleListener.onButtonClicked called.");
                if (messageButton.getClickAction() != ClickAction.NONE) {
                    iInAppMessageImmersive.logButtonClick(messageButton);
                }
                if (!AppboyInAppMessageManager.this.getInAppMessageManagerListener().onInAppMessageButtonClicked(messageButton, inAppMessageCloser)) {
                    performInAppMessageButtonClicked(messageButton, iInAppMessageImmersive, inAppMessageCloser);
                }
            }

            public void onDismissed(View view, IInAppMessage iInAppMessage) {
                AppboyLogger.m662d(AppboyInAppMessageManager.TAG, "InAppMessageViewWrapper.IInAppMessageViewLifecycleListener.onDismissed called.");
                AppboyInAppMessageManager.this.getInAppMessageManagerListener().onInAppMessageDismissed(iInAppMessage);
            }

            private void performInAppMessageButtonClicked(MessageButton messageButton, IInAppMessage iInAppMessage, InAppMessageCloser inAppMessageCloser) {
                performClickAction(messageButton.getClickAction(), iInAppMessage, inAppMessageCloser, messageButton.getUri());
            }

            private void performInAppMessageClicked(IInAppMessage iInAppMessage, InAppMessageCloser inAppMessageCloser) {
                performClickAction(iInAppMessage.getClickAction(), iInAppMessage, inAppMessageCloser, iInAppMessage.getUri());
            }

            private void performClickAction(ClickAction clickAction, IInAppMessage iInAppMessage, InAppMessageCloser inAppMessageCloser, Uri uri) {
                if (AppboyInAppMessageManager.this.mActivity == null) {
                    AppboyLogger.m670w(AppboyInAppMessageManager.TAG, "Can't perform click action because the cached activity is null.");
                    return;
                }
                switch (AnonymousClass13.$SwitchMap$com$appboy$enums$inappmessage$ClickAction[clickAction.ordinal()]) {
                    case Task.NETWORK_STATE_UNMETERED /*1*/:
                        iInAppMessage.setAnimateOut(false);
                        inAppMessageCloser.close(false);
                        AppboyInAppMessageManager.this.getAppboyNavigator().gotoNewsFeed(AppboyInAppMessageManager.this.mActivity, BundleUtils.mapToBundle(iInAppMessage.getExtras()));
                    case Task.NETWORK_STATE_ANY /*2*/:
                        iInAppMessage.setAnimateOut(false);
                        inAppMessageCloser.close(false);
                        ActionFactory.createUriAction(AppboyInAppMessageManager.this.mActivity, uri.toString()).execute(AppboyInAppMessageManager.this.mActivity);
                    case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                        inAppMessageCloser.close(true);
                    default:
                        inAppMessageCloser.close(false);
                }
            }
        };
        this.mInAppMessageWebViewClientListener = new IInAppMessageWebViewClientListener() {
            public void onCloseAction(IInAppMessage iInAppMessage, String str, Bundle bundle) {
                AppboyLogger.m662d(AppboyInAppMessageManager.TAG, "IInAppMessageWebViewClientListener.onCloseAction called.");
                AppboyInAppMessageManager.this.hideCurrentInAppMessage(true, true);
                AppboyInAppMessageManager.this.getHtmlInAppMessageActionListener().onCloseClicked(iInAppMessage, str, bundle);
            }

            public void onNewsfeedAction(IInAppMessage iInAppMessage, String str, Bundle bundle) {
                AppboyLogger.m662d(AppboyInAppMessageManager.TAG, "IInAppMessageWebViewClientListener.onNewsfeedAction called.");
                if (AppboyInAppMessageManager.this.mActivity == null) {
                    AppboyLogger.m670w(AppboyInAppMessageManager.TAG, "Can't perform news feed action because the cached activity is null.");
                    return;
                }
                logHtmlInAppMessageClick(iInAppMessage, bundle);
                if (!AppboyInAppMessageManager.this.getHtmlInAppMessageActionListener().onNewsfeedClicked(iInAppMessage, str, bundle)) {
                    AppboyInAppMessageManager.this.hideCurrentInAppMessage(false);
                    Bundle mapToBundle = BundleUtils.mapToBundle(iInAppMessage.getExtras());
                    mapToBundle.putAll(bundle);
                    AppboyInAppMessageManager.this.getAppboyNavigator().gotoNewsFeed(AppboyInAppMessageManager.this.mActivity, mapToBundle);
                }
            }

            public void onCustomEventAction(IInAppMessage iInAppMessage, String str, Bundle bundle) {
                AppboyLogger.m662d(AppboyInAppMessageManager.TAG, "IInAppMessageWebViewClientListener.onCustomEventAction called.");
                if (AppboyInAppMessageManager.this.mApplicationContext == null) {
                    AppboyLogger.m670w(AppboyInAppMessageManager.TAG, "Can't perform custom event action because the cached application context is null.");
                } else if (!AppboyInAppMessageManager.this.getHtmlInAppMessageActionListener().onCustomEventFired(iInAppMessage, str, bundle)) {
                    String parseCustomEventNameFromQueryBundle = AppboyInAppMessageManager.this.parseCustomEventNameFromQueryBundle(bundle);
                    if (!StringUtils.isNullOrBlank(parseCustomEventNameFromQueryBundle)) {
                        Appboy.getInstance(AppboyInAppMessageManager.this.mApplicationContext).logCustomEvent(parseCustomEventNameFromQueryBundle, AppboyInAppMessageManager.this.parsePropertiesFromQueryBundle(bundle));
                    }
                }
            }

            public void onOtherUrlAction(IInAppMessage iInAppMessage, String str, Bundle bundle) {
                boolean z = false;
                AppboyLogger.m662d(AppboyInAppMessageManager.TAG, "IInAppMessageWebViewClientListener.onOtherUrlAction called.");
                if (AppboyInAppMessageManager.this.mActivity == null) {
                    AppboyLogger.m670w(AppboyInAppMessageManager.TAG, "Can't perform other url action because the cached activity is null.");
                    return;
                }
                logHtmlInAppMessageClick(iInAppMessage, bundle);
                if (!AppboyInAppMessageManager.this.getHtmlInAppMessageActionListener().onOtherUrlAction(iInAppMessage, str, bundle)) {
                    IAction createViewUriAction;
                    AppboyInAppMessageManager.this.hideCurrentInAppMessage(false);
                    if (bundle.containsKey(InAppMessageWebViewClient.QUERY_NAME_EXTERNAL_OPEN)) {
                        z = Boolean.parseBoolean(bundle.getString(InAppMessageWebViewClient.QUERY_NAME_EXTERNAL_OPEN));
                    }
                    if (z) {
                        Bundle mapToBundle = BundleUtils.mapToBundle(iInAppMessage.getExtras());
                        mapToBundle.putAll(bundle);
                        createViewUriAction = ActionFactory.createViewUriAction(str, mapToBundle);
                    } else {
                        createViewUriAction = ActionFactory.createUriAction(AppboyInAppMessageManager.this.mActivity, str);
                    }
                    if (createViewUriAction != null) {
                        createViewUriAction.execute(AppboyInAppMessageManager.this.mActivity);
                    }
                }
            }

            private void logHtmlInAppMessageClick(IInAppMessage iInAppMessage, Bundle bundle) {
                if (bundle == null || !bundle.containsKey(InAppMessageWebViewClient.QUERY_NAME_BUTTON_ID)) {
                    iInAppMessage.logClick();
                } else {
                    ((InAppMessageHtmlFull) iInAppMessage).logButtonClick(bundle.getString(InAppMessageWebViewClient.QUERY_NAME_BUTTON_ID));
                }
            }
        };
    }

    static {
        TAG = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, AppboyInAppMessageManager.class.getName()});
        sInstance = null;
    }

    public static AppboyInAppMessageManager getInstance() {
        if (sInstance == null) {
            synchronized (AppboyInAppMessageManager.class) {
                if (sInstance == null) {
                    sInstance = new AppboyInAppMessageManager();
                }
            }
        }
        return sInstance;
    }

    public void registerInAppMessageManager(Activity activity) {
        this.mActivity = activity;
        if (this.mActivity != null && this.mApplicationContext == null) {
            this.mApplicationContext = this.mActivity.getApplicationContext();
        }
        this.mCanUseFresco = FrescoLibraryUtils.canUseFresco(activity.getApplicationContext());
        if (this.mCarryoverInAppMessageBase != null) {
            AppboyLogger.m662d(TAG, "Displaying carryover in-app message.");
            this.mCarryoverInAppMessageBase.setAnimateIn(false);
            displayInAppMessage(this.mCarryoverInAppMessageBase);
            this.mCarryoverInAppMessageBase = null;
        }
        this.mInAppMessageEventSubscriber = createInAppMessageEventSubscriber();
        Appboy.getInstance(activity).subscribeToNewInAppMessages(this.mInAppMessageEventSubscriber);
    }

    public void unregisterInAppMessageManager(Activity activity) {
        if (this.mInAppMessageViewWrapper != null) {
            ViewUtils.removeViewFromParent(this.mInAppMessageViewWrapper.getInAppMessageView());
            if (this.mInAppMessageViewWrapper.getIsAnimatingClose()) {
                this.mInAppMessageViewWrapper.callAfterClosed();
                this.mCarryoverInAppMessageBase = null;
            } else {
                this.mCarryoverInAppMessageBase = this.mInAppMessageViewWrapper.getInAppMessage();
            }
            this.mInAppMessageViewWrapper = null;
        } else {
            this.mCarryoverInAppMessageBase = null;
        }
        Appboy.getInstance(activity).removeSingleSubscription(this.mInAppMessageEventSubscriber, InAppMessageEvent.class);
        this.mActivity = null;
    }

    public void setCustomInAppMessageManagerListener(IInAppMessageManagerListener iInAppMessageManagerListener) {
        this.mCustomInAppMessageManagerListener = iInAppMessageManagerListener;
    }

    public void setCustomHtmlInAppMessageActionListener(IHtmlInAppMessageActionListener iHtmlInAppMessageActionListener) {
        this.mCustomHtmlInAppMessageActionListener = iHtmlInAppMessageActionListener;
    }

    public void setCustomInAppMessageAnimationFactory(IInAppMessageAnimationFactory iInAppMessageAnimationFactory) {
        this.mCustomInAppMessageAnimationFactory = iInAppMessageAnimationFactory;
    }

    public void setCustomInAppMessageViewFactory(IInAppMessageViewFactory iInAppMessageViewFactory) {
        this.mCustomInAppMessageViewFactory = iInAppMessageViewFactory;
    }

    public void addInAppMessage(IInAppMessage iInAppMessage) {
        this.mInAppMessageBaseStack.push(iInAppMessage);
        requestDisplayInAppMessage();
    }

    public boolean requestDisplayInAppMessage() {
        try {
            if (this.mActivity == null) {
                AppboyLogger.m664e(TAG, "No activity is currently registered to receive in-app messages. Doing nothing.");
                return false;
            } else if (!this.mDisplayingInAppMessage.compareAndSet(false, true)) {
                AppboyLogger.m662d(TAG, "A in-app message is currently being displayed. Ignoring request to display in-app message.");
                return false;
            } else if (this.mInAppMessageBaseStack.isEmpty()) {
                AppboyLogger.m662d(TAG, "The in-app message stack is empty. No in-app message will be displayed.");
                this.mDisplayingInAppMessage.set(false);
                return false;
            } else {
                IInAppMessage iInAppMessage = (IInAppMessage) this.mInAppMessageBaseStack.pop();
                switch (AnonymousClass13.$SwitchMap$com$appboy$ui$inappmessage$InAppMessageOperation[getInAppMessageManagerListener().beforeInAppMessageDisplayed(iInAppMessage).ordinal()]) {
                    case Task.NETWORK_STATE_UNMETERED /*1*/:
                        AppboyLogger.m662d(TAG, "The IInAppMessageManagerListener method beforeInAppMessageDisplayed returned DISPLAY_NOW. The in-app message will be displayed.");
                        this.mActivity.runOnUiThread(new C04021(iInAppMessage));
                        return true;
                    case Task.NETWORK_STATE_ANY /*2*/:
                        AppboyLogger.m662d(TAG, "The IInAppMessageManagerListener method beforeInAppMessageDisplayed returned DISPLAY_LATER. The in-app message will be pushed back onto the stack.");
                        this.mInAppMessageBaseStack.push(iInAppMessage);
                        this.mDisplayingInAppMessage.set(false);
                        return false;
                    case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                        AppboyLogger.m662d(TAG, "The IInAppMessageManagerListener method beforeInAppMessageDisplayed returned DISCARD. The in-app message will not be displayed and will not be put back on the stack.");
                        this.mDisplayingInAppMessage.set(false);
                        return false;
                    default:
                        AppboyLogger.m664e(TAG, "The IInAppMessageManagerListener method beforeInAppMessageDisplayed returned null instead of a InAppMessageOperation. Ignoring the in-app message. Please check the IInAppMessageStackBehaviour implementation.");
                        this.mDisplayingInAppMessage.set(false);
                        return false;
                }
            }
        } catch (Throwable e) {
            AppboyLogger.m665e(TAG, "Error running requestDisplayInAppMessage", e);
            return false;
        }
    }

    public void hideCurrentInAppMessage(boolean z, boolean z2) {
        InAppMessageViewWrapper inAppMessageViewWrapper = this.mInAppMessageViewWrapper;
        if (inAppMessageViewWrapper != null && z2) {
            inAppMessageViewWrapper.callOnDismissed();
        }
        hideCurrentInAppMessage(z);
    }

    public void hideCurrentInAppMessage(boolean z) {
        InAppMessageViewWrapper inAppMessageViewWrapper = this.mInAppMessageViewWrapper;
        if (inAppMessageViewWrapper != null) {
            IInAppMessage inAppMessage = inAppMessageViewWrapper.getInAppMessage();
            if (inAppMessage != null) {
                inAppMessage.setAnimateOut(z);
            }
            inAppMessageViewWrapper.close();
        }
    }

    private void startClearHtmlInAppMessageAssetsThread(IInAppMessageHtml iInAppMessageHtml) {
        new Thread(new C04032(iInAppMessageHtml)).start();
    }

    private IInAppMessageManagerListener getInAppMessageManagerListener() {
        return this.mCustomInAppMessageManagerListener != null ? this.mCustomInAppMessageManagerListener : this.mDefaultInAppMessageManagerListener;
    }

    private IHtmlInAppMessageActionListener getHtmlInAppMessageActionListener() {
        return this.mCustomHtmlInAppMessageActionListener != null ? this.mCustomHtmlInAppMessageActionListener : this.mDefaultHtmlInAppMessageActionListener;
    }

    private IInAppMessageViewFactory getInAppMessageViewFactory(IInAppMessage iInAppMessage) {
        if (this.mCustomInAppMessageViewFactory != null) {
            return this.mCustomInAppMessageViewFactory;
        }
        if (iInAppMessage instanceof InAppMessageSlideup) {
            return this.mInAppMessageSlideupViewFactory;
        }
        if (iInAppMessage instanceof InAppMessageModal) {
            return this.mInAppMessageModalViewFactory;
        }
        if (iInAppMessage instanceof InAppMessageFull) {
            return this.mInAppMessageFullViewFactory;
        }
        if (iInAppMessage instanceof InAppMessageHtmlFull) {
            return this.mInAppMessageHtmlFullViewFactory;
        }
        return null;
    }

    private boolean displayInAppMessage(IInAppMessage iInAppMessage) {
        try {
            if (this.mActivity == null) {
                AppboyLogger.m664e(TAG, "No activity is currently registered to receive in-app messages. Doing nothing.");
                return false;
            }
            IInAppMessageViewFactory inAppMessageViewFactory = getInAppMessageViewFactory(iInAppMessage);
            if (inAppMessageViewFactory == null) {
                AppboyLogger.m662d(TAG, "ViewFactory from getInAppMessageViewFactory was null.");
                return false;
            }
            View createInAppMessageView = inAppMessageViewFactory.createInAppMessageView(this.mActivity, iInAppMessage);
            if (createInAppMessageView == null) {
                AppboyLogger.m664e(TAG, "The in-app message view returned from the IInAppMessageViewFactory was null. The in-app message will not be displayed and will not be put back on the stack.");
                this.mDisplayingInAppMessage.set(false);
                return false;
            } else if (createInAppMessageView.getParent() != null) {
                AppboyLogger.m664e(TAG, "The in-app message view returned from the IInAppMessageViewFactory already has a parent. This is a sign that the view is being reused. The IInAppMessageViewFactory method createInAppMessageViewmust return a new view without a parent. The in-app message will not be displayed and will not be put back on the stack.");
                this.mDisplayingInAppMessage.set(false);
                return false;
            } else {
                Animation openingAnimation = getInAppMessageAnimationFactory(iInAppMessage).getOpeningAnimation(iInAppMessage);
                Animation closingAnimation = getInAppMessageAnimationFactory(iInAppMessage).getClosingAnimation(iInAppMessage);
                if (createInAppMessageView instanceof IInAppMessageImmersiveView) {
                    AppboyLogger.m662d(TAG, "Creating view wrapper for immersive in-app message.");
                    IInAppMessageImmersiveView iInAppMessageImmersiveView = (IInAppMessageImmersiveView) createInAppMessageView;
                    this.mInAppMessageViewWrapper = new InAppMessageViewWrapper(createInAppMessageView, iInAppMessage, this.mInAppMessageViewLifecycleListener, openingAnimation, closingAnimation, iInAppMessageImmersiveView.getMessageClickableView(), iInAppMessageImmersiveView.getMessageButtonViews(), iInAppMessageImmersiveView.getMessageCloseButtonView());
                } else if (createInAppMessageView instanceof IInAppMessageView) {
                    AppboyLogger.m662d(TAG, "Creating view wrapper for base in-app message.");
                    IInAppMessageView iInAppMessageView = (IInAppMessageView) createInAppMessageView;
                    this.mInAppMessageViewWrapper = new InAppMessageViewWrapper(createInAppMessageView, iInAppMessage, this.mInAppMessageViewLifecycleListener, openingAnimation, closingAnimation, iInAppMessageView.getMessageClickableView());
                } else {
                    AppboyLogger.m662d(TAG, "Creating view wrapper for in-app message.");
                    this.mInAppMessageViewWrapper = new InAppMessageViewWrapper(createInAppMessageView, iInAppMessage, this.mInAppMessageViewLifecycleListener, openingAnimation, closingAnimation, createInAppMessageView);
                }
                this.mInAppMessageViewWrapper.open((FrameLayout) this.mActivity.getWindow().getDecorView().findViewById(16908290));
                return true;
            }
        } catch (Throwable e) {
            AppboyLogger.m665e(TAG, "Error running displayInAppMessage", e);
            return false;
        }
    }

    private IEventSubscriber<InAppMessageEvent> createInAppMessageEventSubscriber() {
        return new C04043();
    }

    private IInAppMessageAnimationFactory getInAppMessageAnimationFactory(IInAppMessage iInAppMessage) {
        if (this.mCustomInAppMessageAnimationFactory != null) {
            return this.mCustomInAppMessageAnimationFactory;
        }
        return this.mInAppMessageAnimationFactory;
    }

    String parseCustomEventNameFromQueryBundle(Bundle bundle) {
        return bundle.getString(HTML_IAM_CUSTOM_EVENT_NAME_KEY);
    }

    AppboyProperties parsePropertiesFromQueryBundle(Bundle bundle) {
        AppboyProperties appboyProperties = new AppboyProperties();
        for (String str : bundle.keySet()) {
            if (!str.equals(HTML_IAM_CUSTOM_EVENT_NAME_KEY)) {
                String bundleOptString = AppboyNotificationUtils.bundleOptString(bundle, str, null);
                if (!StringUtils.isNullOrBlank(bundleOptString)) {
                    appboyProperties.addProperty(str, bundleOptString);
                }
            }
        }
        return appboyProperties;
    }

    private IAppboyNavigator getAppboyNavigator() {
        IAppboyNavigator appboyNavigator = Appboy.getInstance(this.mActivity).getAppboyNavigator();
        return appboyNavigator != null ? appboyNavigator : this.mDefaultAppboyNavigator;
    }

    @Deprecated
    public void registerSlideupManager(Activity activity) {
        getInstance().registerInAppMessageManager(activity);
    }

    @Deprecated
    public void unregisterSlideupManager(Activity activity) {
        getInstance().unregisterInAppMessageManager(activity);
    }

    AsyncInAppMessageDisplayer createAsyncInAppMessageDisplayer() {
        return new AsyncInAppMessageDisplayer();
    }
}
