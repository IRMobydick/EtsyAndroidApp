package android.support.design.widget;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.Nullable;
import android.support.design.C0000R;
import android.view.ViewTreeObserver.OnPreDrawListener;

abstract class FloatingActionButtonImpl {
    static final int[] EMPTY_STATE_SET;
    static final int[] FOCUSED_ENABLED_STATE_SET;
    static final int[] PRESSED_ENABLED_STATE_SET;
    static final int SHOW_HIDE_ANIM_DURATION = 200;
    CircularBorderDrawable mBorderDrawable;
    Drawable mContentBackground;
    float mElevation;
    private OnPreDrawListener mPreDrawListener;
    float mPressedTranslationZ;
    Drawable mRippleDrawable;
    final ShadowViewDelegate mShadowViewDelegate;
    Drawable mShapeDrawable;
    private final Rect mTmpRect;
    final VisibilityAwareImageButton mView;

    interface InternalVisibilityChangedListener {
        void onHidden();

        void onShown();
    }

    /* renamed from: android.support.design.widget.FloatingActionButtonImpl.1 */
    class C00211 implements OnPreDrawListener {
        C00211() {
        }

        public boolean onPreDraw() {
            FloatingActionButtonImpl.this.onPreDraw();
            return true;
        }
    }

    abstract float getElevation();

    abstract void getPadding(Rect rect);

    abstract void hide(@Nullable InternalVisibilityChangedListener internalVisibilityChangedListener, boolean z);

    abstract void jumpDrawableToCurrentState();

    abstract void onCompatShadowChanged();

    abstract void onDrawableStateChanged(int[] iArr);

    abstract void onElevationChanged(float f);

    abstract void onTranslationZChanged(float f);

    abstract void setBackgroundDrawable(ColorStateList colorStateList, Mode mode, int i, int i2);

    abstract void setBackgroundTintList(ColorStateList colorStateList);

    abstract void setBackgroundTintMode(Mode mode);

    abstract void setRippleColor(int i);

    abstract void show(@Nullable InternalVisibilityChangedListener internalVisibilityChangedListener, boolean z);

    static {
        PRESSED_ENABLED_STATE_SET = new int[]{16842919, 16842910};
        FOCUSED_ENABLED_STATE_SET = new int[]{16842908, 16842910};
        EMPTY_STATE_SET = new int[0];
    }

    FloatingActionButtonImpl(VisibilityAwareImageButton visibilityAwareImageButton, ShadowViewDelegate shadowViewDelegate) {
        this.mTmpRect = new Rect();
        this.mView = visibilityAwareImageButton;
        this.mShadowViewDelegate = shadowViewDelegate;
    }

    final void setElevation(float f) {
        if (this.mElevation != f) {
            this.mElevation = f;
            onElevationChanged(f);
        }
    }

    final void setPressedTranslationZ(float f) {
        if (this.mPressedTranslationZ != f) {
            this.mPressedTranslationZ = f;
            onTranslationZChanged(f);
        }
    }

    final Drawable getContentBackground() {
        return this.mContentBackground;
    }

    final void updatePadding() {
        Rect rect = this.mTmpRect;
        getPadding(rect);
        onPaddingUpdated(rect);
        this.mShadowViewDelegate.setShadowPadding(rect.left, rect.top, rect.right, rect.bottom);
    }

    void onPaddingUpdated(Rect rect) {
    }

    void onAttachedToWindow() {
        if (requirePreDrawListener()) {
            ensurePreDrawListener();
            this.mView.getViewTreeObserver().addOnPreDrawListener(this.mPreDrawListener);
        }
    }

    void onDetachedFromWindow() {
        if (this.mPreDrawListener != null) {
            this.mView.getViewTreeObserver().removeOnPreDrawListener(this.mPreDrawListener);
            this.mPreDrawListener = null;
        }
    }

    boolean requirePreDrawListener() {
        return false;
    }

    CircularBorderDrawable createBorderDrawable(int i, ColorStateList colorStateList) {
        Resources resources = this.mView.getResources();
        CircularBorderDrawable newCircularDrawable = newCircularDrawable();
        newCircularDrawable.setGradientColors(resources.getColor(C0000R.color.design_fab_stroke_top_outer_color), resources.getColor(C0000R.color.design_fab_stroke_top_inner_color), resources.getColor(C0000R.color.design_fab_stroke_end_inner_color), resources.getColor(C0000R.color.design_fab_stroke_end_outer_color));
        newCircularDrawable.setBorderWidth((float) i);
        newCircularDrawable.setBorderTint(colorStateList);
        return newCircularDrawable;
    }

    CircularBorderDrawable newCircularDrawable() {
        return new CircularBorderDrawable();
    }

    void onPreDraw() {
    }

    private void ensurePreDrawListener() {
        if (this.mPreDrawListener == null) {
            this.mPreDrawListener = new C00211();
        }
    }

    GradientDrawable createShapeDrawable() {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(1);
        gradientDrawable.setColor(-1);
        return gradientDrawable;
    }
}
