package com.etsy.android.uikit.share;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.etsy.android.iconsy.views.IconDrawable;
import com.etsy.android.lib.R;
import com.etsy.android.lib.util.fonts.EtsyFontIcons;
import com.etsy.android.uikit.view.FullImageView;

public class SocialShareView extends RelativeLayout {
    private boolean mAllowDismiss;
    private boolean mAnimating;
    private TextView mMessage;
    private int mPopupHeight;
    private View mSharePopupContainer;
    private SocialSharePopup mToast;

    /* renamed from: com.etsy.android.uikit.share.SocialShareView.1 */
    class C09541 implements Runnable {
        final /* synthetic */ long f4021a;
        final /* synthetic */ SocialShareView f4022b;

        /* renamed from: com.etsy.android.uikit.share.SocialShareView.1.1 */
        class C09531 extends AnimatorListenerAdapter {
            final /* synthetic */ C09541 f4020a;

            C09531(C09541 c09541) {
                this.f4020a = c09541;
            }

            public void onAnimationEnd(Animator animator) {
                this.f4020a.f4022b.mAnimating = false;
                this.f4020a.f4022b.setVisibility(0);
            }
        }

        C09541(SocialShareView socialShareView, long j) {
            this.f4022b = socialShareView;
            this.f4021a = j;
        }

        public void run() {
            this.f4022b.animate().cancel();
            this.f4022b.animate().translationY(0.0f).alpha(FullImageView.ASPECT_RATIO_SQUARE).setDuration(this.f4021a).setListener(new C09531(this));
        }
    }

    /* renamed from: com.etsy.android.uikit.share.SocialShareView.2 */
    class C09552 extends AnimatorListenerAdapter {
        final /* synthetic */ SocialShareView f4023a;

        C09552(SocialShareView socialShareView) {
            this.f4023a = socialShareView;
        }

        public void onAnimationEnd(Animator animator) {
            this.f4023a.mAnimating = false;
            this.f4023a.setVisibility(8);
        }
    }

    /* renamed from: com.etsy.android.uikit.share.SocialShareView.3 */
    class C09563 implements Runnable {
        final /* synthetic */ SocialShareView f4024a;

        C09563(SocialShareView socialShareView) {
            this.f4024a = socialShareView;
        }

        public void run() {
            this.f4024a.mAllowDismiss = true;
        }
    }

    class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR;
        String f4025a;

        /* renamed from: com.etsy.android.uikit.share.SocialShareView.SavedState.1 */
        final class C09571 implements Creator<SavedState> {
            C09571() {
            }

            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return m5385a(parcel);
            }

            public /* synthetic */ Object[] newArray(int i) {
                return m5386a(i);
            }

            public SavedState m5385a(Parcel parcel) {
                return new SavedState(null);
            }

            public SavedState[] m5386a(int i) {
                return new SavedState[i];
            }
        }

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public void writeToParcel(@NonNull Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeString(this.f4025a);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.f4025a = parcel.readString();
        }

        static {
            CREATOR = new C09571();
        }
    }

    public SocialShareView(Context context) {
        super(context);
        this.mAnimating = false;
    }

    public SocialShareView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mAnimating = false;
    }

    public SocialShareView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mAnimating = false;
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mSharePopupContainer = findViewById(R.popup_container);
        this.mMessage = (TextView) findViewById(R.message);
        ((ImageView) findViewById(R.share_icon)).setImageDrawable(IconDrawable.m775a(getResources()).m779a(getResources().getColor(R.white)).m780a(EtsyFontIcons.SHARE).m778a(getResources().getDimension(R.fixed_large)).m777a());
    }

    void setSocialShareToast(SocialSharePopup socialSharePopup) {
        this.mToast = socialSharePopup;
    }

    void setMessage(CharSequence charSequence) {
        this.mMessage.setText(charSequence);
    }

    void setOnShareListener(OnClickListener onClickListener) {
        this.mSharePopupContainer.setOnClickListener(onClickListener);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.mAllowDismiss) {
            this.mToast.m5411c();
        }
        return false;
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.mPopupHeight = findViewById(R.popup_container).getMeasuredHeight();
        hide();
    }

    public void show() {
        setAlpha(FullImageView.ASPECT_RATIO_SQUARE);
        setTranslationY(0.0f);
        onViewShown();
    }

    public void hide() {
        setAlpha(0.0f);
        setTranslationY((float) this.mPopupHeight);
        this.mAllowDismiss = false;
    }

    public void animateIn(long j) {
        if (!this.mAnimating) {
            this.mAnimating = true;
            post(new C09541(this, j));
            onViewShown();
        }
    }

    public void animateOut(long j) {
        if (!this.mAnimating) {
            this.mAnimating = true;
            animate().cancel();
            animate().translationY((float) this.mPopupHeight).alpha(0.0f).setDuration(j).setListener(new C09552(this));
            this.mAllowDismiss = false;
        }
    }

    private void onViewShown() {
        postDelayed(new C09563(this), 2000);
    }

    public Parcelable onSaveInstanceState() {
        Parcelable savedState = new SavedState(super.onSaveInstanceState());
        savedState.f4025a = this.mMessage.getText().toString();
        return savedState;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            SavedState savedState = (SavedState) parcelable;
            super.onRestoreInstanceState(savedState.getSuperState());
            setMessage(savedState.f4025a);
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }
}
