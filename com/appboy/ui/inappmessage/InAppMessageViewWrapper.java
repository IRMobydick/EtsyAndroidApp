package com.appboy.ui.inappmessage;

import android.content.Context;
import android.os.Build.VERSION;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.FrameLayout;
import com.appboy.Constants;
import com.appboy.enums.inappmessage.DismissType;
import com.appboy.enums.inappmessage.SlideFrom;
import com.appboy.models.IInAppMessage;
import com.appboy.models.IInAppMessageHtml;
import com.appboy.models.IInAppMessageImmersive;
import com.appboy.models.InAppMessageSlideup;
import com.appboy.models.MessageButton;
import com.appboy.support.AppboyLogger;
import com.appboy.ui.inappmessage.listeners.IInAppMessageViewLifecycleListener;
import com.appboy.ui.inappmessage.listeners.SimpleSwipeDismissTouchListener;
import com.appboy.ui.inappmessage.listeners.SwipeDismissTouchListener.DismissCallbacks;
import com.appboy.ui.inappmessage.listeners.TouchAwareSwipeDismissTouchListener;
import com.appboy.ui.inappmessage.listeners.TouchAwareSwipeDismissTouchListener.ITouchListener;
import com.appboy.ui.support.AnimationUtils;
import com.appboy.ui.support.ViewUtils;
import com.etsy.android.uikit.view.FullImageView;
import java.util.List;

public class InAppMessageViewWrapper {
    private static final String TAG;
    private List<View> mButtons;
    private View mClickableInAppMessageView;
    private View mCloseButton;
    private final Animation mClosingAnimation;
    private Runnable mDismissRunnable;
    private final IInAppMessage mInAppMessage;
    private final View mInAppMessageView;
    private final IInAppMessageViewLifecycleListener mInAppMessageViewLifecycleListener;
    private boolean mIsAnimatingClose;
    private final Animation mOpeningAnimation;

    /* renamed from: com.appboy.ui.inappmessage.InAppMessageViewWrapper.1 */
    class C04121 implements OnClickListener {
        C04121() {
        }

        public void onClick(View view) {
            if (InAppMessageViewWrapper.this.mInAppMessage instanceof IInAppMessageImmersive) {
                IInAppMessageImmersive iInAppMessageImmersive = (IInAppMessageImmersive) InAppMessageViewWrapper.this.mInAppMessage;
                if (iInAppMessageImmersive.getMessageButtons() == null || iInAppMessageImmersive.getMessageButtons().size() == 0) {
                    InAppMessageViewWrapper.this.mInAppMessageViewLifecycleListener.onClicked(new InAppMessageCloser(InAppMessageViewWrapper.this), InAppMessageViewWrapper.this.mInAppMessageView, InAppMessageViewWrapper.this.mInAppMessage);
                    return;
                }
                return;
            }
            InAppMessageViewWrapper.this.mInAppMessageViewLifecycleListener.onClicked(new InAppMessageCloser(InAppMessageViewWrapper.this), InAppMessageViewWrapper.this.mInAppMessageView, InAppMessageViewWrapper.this.mInAppMessage);
        }
    }

    /* renamed from: com.appboy.ui.inappmessage.InAppMessageViewWrapper.2 */
    class C04132 implements OnClickListener {
        C04132() {
        }

        public void onClick(View view) {
            IInAppMessageImmersive iInAppMessageImmersive = (IInAppMessageImmersive) InAppMessageViewWrapper.this.mInAppMessage;
            for (int i = 0; i < InAppMessageViewWrapper.this.mButtons.size(); i++) {
                if (view.getId() == ((View) InAppMessageViewWrapper.this.mButtons.get(i)).getId()) {
                    InAppMessageViewWrapper.this.mInAppMessageViewLifecycleListener.onButtonClicked(new InAppMessageCloser(InAppMessageViewWrapper.this), (MessageButton) iInAppMessageImmersive.getMessageButtons().get(i), iInAppMessageImmersive);
                    return;
                }
            }
        }
    }

    /* renamed from: com.appboy.ui.inappmessage.InAppMessageViewWrapper.3 */
    class C04143 implements OnClickListener {
        C04143() {
        }

        public void onClick(View view) {
            InAppMessageViewWrapper.this.mInAppMessageViewLifecycleListener.onDismissed(InAppMessageViewWrapper.this.mInAppMessageView, InAppMessageViewWrapper.this.mInAppMessage);
            InAppMessageViewWrapper.this.close();
        }
    }

    /* renamed from: com.appboy.ui.inappmessage.InAppMessageViewWrapper.4 */
    class C04154 implements Runnable {
        C04154() {
        }

        public void run() {
            InAppMessageViewWrapper.this.mInAppMessageViewLifecycleListener.onDismissed(InAppMessageViewWrapper.this.mInAppMessageView, InAppMessageViewWrapper.this.mInAppMessage);
            InAppMessageViewWrapper.this.close();
        }
    }

    /* renamed from: com.appboy.ui.inappmessage.InAppMessageViewWrapper.5 */
    class C04165 implements DismissCallbacks {
        C04165() {
        }

        public boolean canDismiss(Object obj) {
            return true;
        }

        public void onDismiss(View view, Object obj) {
            InAppMessageViewWrapper.this.mInAppMessageViewLifecycleListener.onDismissed(InAppMessageViewWrapper.this.mInAppMessageView, InAppMessageViewWrapper.this.mInAppMessage);
            InAppMessageViewWrapper.this.mInAppMessage.setAnimateOut(false);
            InAppMessageViewWrapper.this.close();
        }
    }

    /* renamed from: com.appboy.ui.inappmessage.InAppMessageViewWrapper.6 */
    class C04176 implements ITouchListener {
        C04176() {
        }

        public void onTouchStartedOrContinued() {
            InAppMessageViewWrapper.this.mInAppMessageView.removeCallbacks(InAppMessageViewWrapper.this.mDismissRunnable);
        }

        public void onTouchEnded() {
            if (InAppMessageViewWrapper.this.mInAppMessage.getDismissType() == DismissType.AUTO_DISMISS) {
                InAppMessageViewWrapper.this.addDismissRunnable();
            }
        }
    }

    /* renamed from: com.appboy.ui.inappmessage.InAppMessageViewWrapper.7 */
    class C04187 implements AnimationListener {
        C04187() {
        }

        public void onAnimationStart(Animation animation) {
        }

        public void onAnimationEnd(Animation animation) {
            if (InAppMessageViewWrapper.this.mInAppMessage.getDismissType() == DismissType.AUTO_DISMISS) {
                InAppMessageViewWrapper.this.addDismissRunnable();
            }
            AppboyLogger.m662d(InAppMessageViewWrapper.TAG, "In-app message animated into view.");
            InAppMessageViewWrapper.this.mInAppMessageViewLifecycleListener.afterOpened(InAppMessageViewWrapper.this.mInAppMessageView, InAppMessageViewWrapper.this.mInAppMessage);
        }

        public void onAnimationRepeat(Animation animation) {
        }
    }

    /* renamed from: com.appboy.ui.inappmessage.InAppMessageViewWrapper.8 */
    class C04198 implements AnimationListener {
        C04198() {
        }

        public void onAnimationStart(Animation animation) {
        }

        public void onAnimationEnd(Animation animation) {
            InAppMessageViewWrapper.this.mInAppMessageView.clearAnimation();
            InAppMessageViewWrapper.this.mInAppMessageView.setVisibility(8);
            ViewUtils.removeViewFromParent(InAppMessageViewWrapper.this.mInAppMessageView);
            InAppMessageViewWrapper.this.mInAppMessageViewLifecycleListener.afterClosed(InAppMessageViewWrapper.this.mInAppMessage);
        }

        public void onAnimationRepeat(Animation animation) {
        }
    }

    /* renamed from: com.appboy.ui.inappmessage.InAppMessageViewWrapper.9 */
    class C04209 extends SimpleSwipeDismissTouchListener {
        private final long sSwipeAnimationDurationMillis;

        C04209(Context context) {
            super(context);
            this.sSwipeAnimationDurationMillis = 400;
        }

        public void onSwipeLeft() {
            animateAndClose(AnimationUtils.createHorizontalAnimation(0.0f, -1.0f, 400, false));
        }

        public void onSwipeRight() {
            animateAndClose(AnimationUtils.createHorizontalAnimation(0.0f, FullImageView.ASPECT_RATIO_SQUARE, 400, false));
        }

        private void animateAndClose(Animation animation) {
            InAppMessageViewWrapper.this.mInAppMessageViewLifecycleListener.onDismissed(InAppMessageViewWrapper.this.mInAppMessageView, InAppMessageViewWrapper.this.mInAppMessage);
            InAppMessageViewWrapper.this.preClose();
            InAppMessageViewWrapper.this.mInAppMessageView.clearAnimation();
            InAppMessageViewWrapper.this.mInAppMessageView.setAnimation(animation);
            animation.startNow();
            InAppMessageViewWrapper.this.mInAppMessageView.invalidate();
            InAppMessageViewWrapper.this.mInAppMessage.setAnimateOut(false);
            InAppMessageViewWrapper.this.performClose();
        }
    }

    static {
        TAG = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, InAppMessageViewWrapper.class.getName()});
    }

    public InAppMessageViewWrapper(View view, IInAppMessage iInAppMessage, IInAppMessageViewLifecycleListener iInAppMessageViewLifecycleListener, Animation animation, Animation animation2, View view2) {
        this.mInAppMessageView = view;
        this.mInAppMessage = iInAppMessage;
        this.mInAppMessageViewLifecycleListener = iInAppMessageViewLifecycleListener;
        this.mIsAnimatingClose = false;
        if (view2 != null) {
            this.mClickableInAppMessageView = view2;
        } else {
            this.mClickableInAppMessageView = this.mInAppMessageView;
        }
        if (VERSION.SDK_INT >= 12 && (this.mInAppMessage instanceof InAppMessageSlideup)) {
            OnTouchListener touchAwareSwipeDismissTouchListener = new TouchAwareSwipeDismissTouchListener(view, null, createDismissCallbacks());
            touchAwareSwipeDismissTouchListener.setTouchListener(createTouchAwareListener());
            this.mClickableInAppMessageView.setOnTouchListener(touchAwareSwipeDismissTouchListener);
        } else if (this.mInAppMessage instanceof InAppMessageSlideup) {
            this.mClickableInAppMessageView.setOnTouchListener(getSimpleSwipeListener());
        }
        this.mOpeningAnimation = animation;
        this.mClosingAnimation = animation2;
        this.mClickableInAppMessageView.setOnClickListener(createClickListener());
    }

    public InAppMessageViewWrapper(View view, IInAppMessage iInAppMessage, IInAppMessageViewLifecycleListener iInAppMessageViewLifecycleListener, Animation animation, Animation animation2, View view2, List<View> list, View view3) {
        this(view, iInAppMessage, iInAppMessageViewLifecycleListener, animation, animation2, view2);
        if (view3 != null) {
            this.mCloseButton = view3;
            this.mCloseButton.setOnClickListener(createCloseInAppMessageClickListener());
        }
        if (list != null) {
            this.mButtons = list;
            for (View onClickListener : this.mButtons) {
                onClickListener.setOnClickListener(createButtonClickListener());
            }
        }
    }

    public void open(FrameLayout frameLayout) {
        this.mInAppMessageViewLifecycleListener.beforeOpened(this.mInAppMessageView, this.mInAppMessage);
        addViewToLayout(frameLayout);
        display();
    }

    public boolean getIsAnimatingClose() {
        return this.mIsAnimatingClose;
    }

    public void callAfterClosed() {
        this.mInAppMessageViewLifecycleListener.afterClosed(this.mInAppMessage);
    }

    public void callOnDismissed() {
        this.mInAppMessageViewLifecycleListener.onDismissed(this.mInAppMessageView, this.mInAppMessage);
    }

    private void preClose() {
        this.mInAppMessageView.removeCallbacks(this.mDismissRunnable);
        this.mInAppMessageViewLifecycleListener.beforeClosed(this.mInAppMessageView, this.mInAppMessage);
    }

    private void performClose() {
        if (this.mInAppMessage.getAnimateOut()) {
            this.mIsAnimatingClose = true;
            setAndStartAnimation(false);
            return;
        }
        ViewUtils.removeViewFromParent(this.mInAppMessageView);
        this.mInAppMessageViewLifecycleListener.afterClosed(this.mInAppMessage);
    }

    public void close() {
        preClose();
        performClose();
    }

    public View getInAppMessageView() {
        return this.mInAppMessageView;
    }

    public IInAppMessage getInAppMessage() {
        return this.mInAppMessage;
    }

    private OnClickListener createClickListener() {
        return new C04121();
    }

    private OnClickListener createButtonClickListener() {
        return new C04132();
    }

    private OnClickListener createCloseInAppMessageClickListener() {
        return new C04143();
    }

    private void addViewToLayout(FrameLayout frameLayout) {
        AppboyLogger.m662d(TAG, "Adding In-app message view to root FrameLayout.");
        LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2);
        if (this.mInAppMessage instanceof InAppMessageSlideup) {
            layoutParams.gravity = ((InAppMessageSlideup) this.mInAppMessage).getSlideFrom() == SlideFrom.TOP ? 48 : 80;
        }
        if ((this.mInAppMessage instanceof IInAppMessageImmersive) || (this.mInAppMessage instanceof IInAppMessageHtml)) {
            this.mInAppMessageView.setFocusableInTouchMode(true);
            this.mInAppMessageView.requestFocus();
        }
        frameLayout.addView(this.mInAppMessageView, layoutParams);
    }

    private void display() {
        if (this.mInAppMessage.getAnimateIn()) {
            AppboyLogger.m662d(TAG, "In-app message view will animate into the visible area.");
            setAndStartAnimation(true);
            return;
        }
        AppboyLogger.m662d(TAG, "In-app message view will be placed instantly into the visible area.");
        if (this.mInAppMessage.getDismissType() == DismissType.AUTO_DISMISS) {
            addDismissRunnable();
        }
        this.mInAppMessageViewLifecycleListener.afterOpened(this.mInAppMessageView, this.mInAppMessage);
    }

    private void addDismissRunnable() {
        if (this.mDismissRunnable == null) {
            this.mDismissRunnable = new C04154();
            this.mInAppMessageView.postDelayed(this.mDismissRunnable, (long) this.mInAppMessage.getDurationInMilliseconds());
        }
    }

    private DismissCallbacks createDismissCallbacks() {
        return new C04165();
    }

    private ITouchListener createTouchAwareListener() {
        return new C04176();
    }

    private void setAndStartAnimation(boolean z) {
        Animation animation;
        if (z) {
            animation = this.mOpeningAnimation;
        } else {
            animation = this.mClosingAnimation;
        }
        animation.setAnimationListener(createAnimationListener(z));
        this.mInAppMessageView.clearAnimation();
        this.mInAppMessageView.setAnimation(animation);
        animation.startNow();
        this.mInAppMessageView.invalidate();
    }

    private AnimationListener createAnimationListener(boolean z) {
        return z ? new C04187() : new C04198();
    }

    private SimpleSwipeDismissTouchListener getSimpleSwipeListener() {
        return new C04209(this.mInAppMessageView.getContext());
    }
}
