package android.support.design.widget;

import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Build.VERSION;
import android.support.design.C0000R;
import android.support.v4.text.TextDirectionHeuristicsCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.view.View;
import android.view.animation.Interpolator;
import com.etsy.android.lib.R;
import com.etsy.android.uikit.view.FullImageView;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;

final class CollapsingTextHelper {
    private static final boolean DEBUG_DRAW = false;
    private static final Paint DEBUG_DRAW_PAINT;
    private static final boolean USE_SCALING_TEXTURE;
    private boolean mBoundsChanged;
    private final Rect mCollapsedBounds;
    private float mCollapsedDrawX;
    private float mCollapsedDrawY;
    private int mCollapsedShadowColor;
    private float mCollapsedShadowDx;
    private float mCollapsedShadowDy;
    private float mCollapsedShadowRadius;
    private int mCollapsedTextColor;
    private int mCollapsedTextGravity;
    private float mCollapsedTextSize;
    private Typeface mCollapsedTypeface;
    private final RectF mCurrentBounds;
    private float mCurrentDrawX;
    private float mCurrentDrawY;
    private float mCurrentTextSize;
    private Typeface mCurrentTypeface;
    private boolean mDrawTitle;
    private final Rect mExpandedBounds;
    private float mExpandedDrawX;
    private float mExpandedDrawY;
    private float mExpandedFraction;
    private int mExpandedShadowColor;
    private float mExpandedShadowDx;
    private float mExpandedShadowDy;
    private float mExpandedShadowRadius;
    private int mExpandedTextColor;
    private int mExpandedTextGravity;
    private float mExpandedTextSize;
    private Bitmap mExpandedTitleTexture;
    private Typeface mExpandedTypeface;
    private boolean mIsRtl;
    private Interpolator mPositionInterpolator;
    private float mScale;
    private CharSequence mText;
    private final TextPaint mTextPaint;
    private Interpolator mTextSizeInterpolator;
    private CharSequence mTextToDraw;
    private float mTextureAscent;
    private float mTextureDescent;
    private Paint mTexturePaint;
    private boolean mUseTexture;
    private final View mView;

    static {
        USE_SCALING_TEXTURE = VERSION.SDK_INT < 18;
        DEBUG_DRAW_PAINT = null;
        if (DEBUG_DRAW_PAINT != null) {
            DEBUG_DRAW_PAINT.setAntiAlias(true);
            DEBUG_DRAW_PAINT.setColor(-65281);
        }
    }

    public CollapsingTextHelper(View view) {
        this.mExpandedTextGravity = 16;
        this.mCollapsedTextGravity = 16;
        this.mExpandedTextSize = 15.0f;
        this.mCollapsedTextSize = 15.0f;
        this.mView = view;
        this.mTextPaint = new TextPaint(129);
        this.mCollapsedBounds = new Rect();
        this.mExpandedBounds = new Rect();
        this.mCurrentBounds = new RectF();
    }

    void setTextSizeInterpolator(Interpolator interpolator) {
        this.mTextSizeInterpolator = interpolator;
        recalculate();
    }

    void setPositionInterpolator(Interpolator interpolator) {
        this.mPositionInterpolator = interpolator;
        recalculate();
    }

    void setExpandedTextSize(float f) {
        if (this.mExpandedTextSize != f) {
            this.mExpandedTextSize = f;
            recalculate();
        }
    }

    void setCollapsedTextSize(float f) {
        if (this.mCollapsedTextSize != f) {
            this.mCollapsedTextSize = f;
            recalculate();
        }
    }

    void setCollapsedTextColor(int i) {
        if (this.mCollapsedTextColor != i) {
            this.mCollapsedTextColor = i;
            recalculate();
        }
    }

    void setExpandedTextColor(int i) {
        if (this.mExpandedTextColor != i) {
            this.mExpandedTextColor = i;
            recalculate();
        }
    }

    void setExpandedBounds(int i, int i2, int i3, int i4) {
        if (!rectEquals(this.mExpandedBounds, i, i2, i3, i4)) {
            this.mExpandedBounds.set(i, i2, i3, i4);
            this.mBoundsChanged = true;
            onBoundsChanged();
        }
    }

    void setCollapsedBounds(int i, int i2, int i3, int i4) {
        if (!rectEquals(this.mCollapsedBounds, i, i2, i3, i4)) {
            this.mCollapsedBounds.set(i, i2, i3, i4);
            this.mBoundsChanged = true;
            onBoundsChanged();
        }
    }

    void onBoundsChanged() {
        boolean z = this.mCollapsedBounds.width() > 0 && this.mCollapsedBounds.height() > 0 && this.mExpandedBounds.width() > 0 && this.mExpandedBounds.height() > 0;
        this.mDrawTitle = z;
    }

    void setExpandedTextGravity(int i) {
        if (this.mExpandedTextGravity != i) {
            this.mExpandedTextGravity = i;
            recalculate();
        }
    }

    int getExpandedTextGravity() {
        return this.mExpandedTextGravity;
    }

    void setCollapsedTextGravity(int i) {
        if (this.mCollapsedTextGravity != i) {
            this.mCollapsedTextGravity = i;
            recalculate();
        }
    }

    int getCollapsedTextGravity() {
        return this.mCollapsedTextGravity;
    }

    void setCollapsedTextAppearance(int i) {
        TypedArray obtainStyledAttributes = this.mView.getContext().obtainStyledAttributes(i, C0000R.styleable.TextAppearance);
        if (obtainStyledAttributes.hasValue(C0000R.styleable.TextAppearance_android_textColor)) {
            this.mCollapsedTextColor = obtainStyledAttributes.getColor(C0000R.styleable.TextAppearance_android_textColor, this.mCollapsedTextColor);
        }
        if (obtainStyledAttributes.hasValue(C0000R.styleable.TextAppearance_android_textSize)) {
            this.mCollapsedTextSize = (float) obtainStyledAttributes.getDimensionPixelSize(C0000R.styleable.TextAppearance_android_textSize, (int) this.mCollapsedTextSize);
        }
        this.mCollapsedShadowColor = obtainStyledAttributes.getInt(C0000R.styleable.TextAppearance_android_shadowColor, 0);
        this.mCollapsedShadowDx = obtainStyledAttributes.getFloat(C0000R.styleable.TextAppearance_android_shadowDx, 0.0f);
        this.mCollapsedShadowDy = obtainStyledAttributes.getFloat(C0000R.styleable.TextAppearance_android_shadowDy, 0.0f);
        this.mCollapsedShadowRadius = obtainStyledAttributes.getFloat(C0000R.styleable.TextAppearance_android_shadowRadius, 0.0f);
        obtainStyledAttributes.recycle();
        if (VERSION.SDK_INT >= 16) {
            this.mCollapsedTypeface = readFontFamilyTypeface(i);
        }
        recalculate();
    }

    void setExpandedTextAppearance(int i) {
        TypedArray obtainStyledAttributes = this.mView.getContext().obtainStyledAttributes(i, C0000R.styleable.TextAppearance);
        if (obtainStyledAttributes.hasValue(C0000R.styleable.TextAppearance_android_textColor)) {
            this.mExpandedTextColor = obtainStyledAttributes.getColor(C0000R.styleable.TextAppearance_android_textColor, this.mExpandedTextColor);
        }
        if (obtainStyledAttributes.hasValue(C0000R.styleable.TextAppearance_android_textSize)) {
            this.mExpandedTextSize = (float) obtainStyledAttributes.getDimensionPixelSize(C0000R.styleable.TextAppearance_android_textSize, (int) this.mExpandedTextSize);
        }
        this.mExpandedShadowColor = obtainStyledAttributes.getInt(C0000R.styleable.TextAppearance_android_shadowColor, 0);
        this.mExpandedShadowDx = obtainStyledAttributes.getFloat(C0000R.styleable.TextAppearance_android_shadowDx, 0.0f);
        this.mExpandedShadowDy = obtainStyledAttributes.getFloat(C0000R.styleable.TextAppearance_android_shadowDy, 0.0f);
        this.mExpandedShadowRadius = obtainStyledAttributes.getFloat(C0000R.styleable.TextAppearance_android_shadowRadius, 0.0f);
        obtainStyledAttributes.recycle();
        if (VERSION.SDK_INT >= 16) {
            this.mExpandedTypeface = readFontFamilyTypeface(i);
        }
        recalculate();
    }

    private Typeface readFontFamilyTypeface(int i) {
        TypedArray obtainStyledAttributes = this.mView.getContext().obtainStyledAttributes(i, new int[]{16843692});
        try {
            String string = obtainStyledAttributes.getString(0);
            if (string != null) {
                Typeface create = Typeface.create(string, 0);
                return create;
            }
            obtainStyledAttributes.recycle();
            return null;
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    void setCollapsedTypeface(Typeface typeface) {
        if (this.mCollapsedTypeface != typeface) {
            this.mCollapsedTypeface = typeface;
            recalculate();
        }
    }

    void setExpandedTypeface(Typeface typeface) {
        if (this.mExpandedTypeface != typeface) {
            this.mExpandedTypeface = typeface;
            recalculate();
        }
    }

    void setTypefaces(Typeface typeface) {
        this.mExpandedTypeface = typeface;
        this.mCollapsedTypeface = typeface;
        recalculate();
    }

    Typeface getCollapsedTypeface() {
        return this.mCollapsedTypeface != null ? this.mCollapsedTypeface : Typeface.DEFAULT;
    }

    Typeface getExpandedTypeface() {
        return this.mExpandedTypeface != null ? this.mExpandedTypeface : Typeface.DEFAULT;
    }

    void setExpansionFraction(float f) {
        float constrain = MathUtils.constrain(f, 0.0f, (float) FullImageView.ASPECT_RATIO_SQUARE);
        if (constrain != this.mExpandedFraction) {
            this.mExpandedFraction = constrain;
            calculateCurrentOffsets();
        }
    }

    float getExpansionFraction() {
        return this.mExpandedFraction;
    }

    float getCollapsedTextSize() {
        return this.mCollapsedTextSize;
    }

    float getExpandedTextSize() {
        return this.mExpandedTextSize;
    }

    private void calculateCurrentOffsets() {
        calculateOffsets(this.mExpandedFraction);
    }

    private void calculateOffsets(float f) {
        interpolateBounds(f);
        this.mCurrentDrawX = lerp(this.mExpandedDrawX, this.mCollapsedDrawX, f, this.mPositionInterpolator);
        this.mCurrentDrawY = lerp(this.mExpandedDrawY, this.mCollapsedDrawY, f, this.mPositionInterpolator);
        setInterpolatedTextSize(lerp(this.mExpandedTextSize, this.mCollapsedTextSize, f, this.mTextSizeInterpolator));
        if (this.mCollapsedTextColor != this.mExpandedTextColor) {
            this.mTextPaint.setColor(blendColors(this.mExpandedTextColor, this.mCollapsedTextColor, f));
        } else {
            this.mTextPaint.setColor(this.mCollapsedTextColor);
        }
        this.mTextPaint.setShadowLayer(lerp(this.mExpandedShadowRadius, this.mCollapsedShadowRadius, f, null), lerp(this.mExpandedShadowDx, this.mCollapsedShadowDx, f, null), lerp(this.mExpandedShadowDy, this.mCollapsedShadowDy, f, null), blendColors(this.mExpandedShadowColor, this.mCollapsedShadowColor, f));
        ViewCompat.postInvalidateOnAnimation(this.mView);
    }

    private void calculateBaseOffsets() {
        int i;
        int i2 = 1;
        float f = 0.0f;
        float f2 = this.mCurrentTextSize;
        calculateUsingTextSize(this.mCollapsedTextSize);
        float measureText = this.mTextToDraw != null ? this.mTextPaint.measureText(this.mTextToDraw, 0, this.mTextToDraw.length()) : 0.0f;
        int i3 = this.mCollapsedTextGravity;
        if (this.mIsRtl) {
            i = 1;
        } else {
            i = 0;
        }
        i = GravityCompat.getAbsoluteGravity(i3, i);
        switch (i & 112) {
            case R.AppCompatTheme_homeAsUpIndicator /*48*/:
                this.mCollapsedDrawY = ((float) this.mCollapsedBounds.top) - this.mTextPaint.ascent();
                break;
            case R.AppCompatTheme_panelMenuListTheme /*80*/:
                this.mCollapsedDrawY = (float) this.mCollapsedBounds.bottom;
                break;
            default:
                this.mCollapsedDrawY = (((this.mTextPaint.descent() - this.mTextPaint.ascent()) / 2.0f) - this.mTextPaint.descent()) + ((float) this.mCollapsedBounds.centerY());
                break;
        }
        switch (i & GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK) {
            case Task.NETWORK_STATE_UNMETERED /*1*/:
                this.mCollapsedDrawX = ((float) this.mCollapsedBounds.centerX()) - (measureText / 2.0f);
                break;
            case CommonStatusCodes.INVALID_ACCOUNT /*5*/:
                this.mCollapsedDrawX = ((float) this.mCollapsedBounds.right) - measureText;
                break;
            default:
                this.mCollapsedDrawX = (float) this.mCollapsedBounds.left;
                break;
        }
        calculateUsingTextSize(this.mExpandedTextSize);
        if (this.mTextToDraw != null) {
            f = this.mTextPaint.measureText(this.mTextToDraw, 0, this.mTextToDraw.length());
        }
        int i4 = this.mExpandedTextGravity;
        if (!this.mIsRtl) {
            i2 = 0;
        }
        i4 = GravityCompat.getAbsoluteGravity(i4, i2);
        switch (i4 & 112) {
            case R.AppCompatTheme_homeAsUpIndicator /*48*/:
                this.mExpandedDrawY = ((float) this.mExpandedBounds.top) - this.mTextPaint.ascent();
                break;
            case R.AppCompatTheme_panelMenuListTheme /*80*/:
                this.mExpandedDrawY = (float) this.mExpandedBounds.bottom;
                break;
            default:
                this.mExpandedDrawY = (((this.mTextPaint.descent() - this.mTextPaint.ascent()) / 2.0f) - this.mTextPaint.descent()) + ((float) this.mExpandedBounds.centerY());
                break;
        }
        switch (i4 & GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK) {
            case Task.NETWORK_STATE_UNMETERED /*1*/:
                this.mExpandedDrawX = ((float) this.mExpandedBounds.centerX()) - (f / 2.0f);
                break;
            case CommonStatusCodes.INVALID_ACCOUNT /*5*/:
                this.mExpandedDrawX = ((float) this.mExpandedBounds.right) - f;
                break;
            default:
                this.mExpandedDrawX = (float) this.mExpandedBounds.left;
                break;
        }
        clearTexture();
        setInterpolatedTextSize(f2);
    }

    private void interpolateBounds(float f) {
        this.mCurrentBounds.left = lerp((float) this.mExpandedBounds.left, (float) this.mCollapsedBounds.left, f, this.mPositionInterpolator);
        this.mCurrentBounds.top = lerp(this.mExpandedDrawY, this.mCollapsedDrawY, f, this.mPositionInterpolator);
        this.mCurrentBounds.right = lerp((float) this.mExpandedBounds.right, (float) this.mCollapsedBounds.right, f, this.mPositionInterpolator);
        this.mCurrentBounds.bottom = lerp((float) this.mExpandedBounds.bottom, (float) this.mCollapsedBounds.bottom, f, this.mPositionInterpolator);
    }

    public void draw(Canvas canvas) {
        int save = canvas.save();
        if (this.mTextToDraw != null && this.mDrawTitle) {
            float f;
            float f2 = this.mCurrentDrawX;
            float f3 = this.mCurrentDrawY;
            int i = (!this.mUseTexture || this.mExpandedTitleTexture == null) ? 0 : 1;
            float f4;
            if (i != 0) {
                f = this.mTextureAscent * this.mScale;
                f4 = this.mTextureDescent * this.mScale;
            } else {
                f = this.mTextPaint.ascent() * this.mScale;
                f4 = this.mTextPaint.descent() * this.mScale;
            }
            if (i != 0) {
                f3 += f;
            }
            if (this.mScale != FullImageView.ASPECT_RATIO_SQUARE) {
                canvas.scale(this.mScale, this.mScale, f2, f3);
            }
            if (i != 0) {
                canvas.drawBitmap(this.mExpandedTitleTexture, f2, f3, this.mTexturePaint);
            } else {
                canvas.drawText(this.mTextToDraw, 0, this.mTextToDraw.length(), f2, f3, this.mTextPaint);
            }
        }
        canvas.restoreToCount(save);
    }

    private boolean calculateIsRtl(CharSequence charSequence) {
        int i = 1;
        if (ViewCompat.getLayoutDirection(this.mView) != 1) {
            i = 0;
        }
        return (i != 0 ? TextDirectionHeuristicsCompat.FIRSTSTRONG_RTL : TextDirectionHeuristicsCompat.FIRSTSTRONG_LTR).isRtl(charSequence, 0, charSequence.length());
    }

    private void setInterpolatedTextSize(float f) {
        calculateUsingTextSize(f);
        boolean z = USE_SCALING_TEXTURE && this.mScale != FullImageView.ASPECT_RATIO_SQUARE;
        this.mUseTexture = z;
        if (this.mUseTexture) {
            ensureExpandedTexture();
        }
        ViewCompat.postInvalidateOnAnimation(this.mView);
    }

    private void calculateUsingTextSize(float f) {
        boolean z = true;
        if (this.mText != null) {
            float width;
            float f2;
            boolean z2;
            if (isClose(f, this.mCollapsedTextSize)) {
                width = (float) this.mCollapsedBounds.width();
                float f3 = this.mCollapsedTextSize;
                this.mScale = FullImageView.ASPECT_RATIO_SQUARE;
                if (this.mCurrentTypeface != this.mCollapsedTypeface) {
                    this.mCurrentTypeface = this.mCollapsedTypeface;
                    f2 = width;
                    width = f3;
                    z2 = true;
                } else {
                    f2 = width;
                    width = f3;
                    z2 = false;
                }
            } else {
                f2 = (float) this.mExpandedBounds.width();
                width = this.mExpandedTextSize;
                if (this.mCurrentTypeface != this.mExpandedTypeface) {
                    this.mCurrentTypeface = this.mExpandedTypeface;
                    z2 = true;
                } else {
                    z2 = false;
                }
                if (isClose(f, this.mExpandedTextSize)) {
                    this.mScale = FullImageView.ASPECT_RATIO_SQUARE;
                } else {
                    this.mScale = f / this.mExpandedTextSize;
                }
            }
            if (f2 > 0.0f) {
                if (this.mCurrentTextSize != width || this.mBoundsChanged || r0) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                this.mCurrentTextSize = width;
                this.mBoundsChanged = false;
            }
            if (this.mTextToDraw == null || r0) {
                this.mTextPaint.setTextSize(this.mCurrentTextSize);
                this.mTextPaint.setTypeface(this.mCurrentTypeface);
                TextPaint textPaint = this.mTextPaint;
                if (this.mScale == FullImageView.ASPECT_RATIO_SQUARE) {
                    z = false;
                }
                textPaint.setLinearText(z);
                CharSequence ellipsize = TextUtils.ellipsize(this.mText, this.mTextPaint, f2, TruncateAt.END);
                if (!TextUtils.equals(ellipsize, this.mTextToDraw)) {
                    this.mTextToDraw = ellipsize;
                    this.mIsRtl = calculateIsRtl(this.mTextToDraw);
                }
            }
        }
    }

    private void ensureExpandedTexture() {
        if (this.mExpandedTitleTexture == null && !this.mExpandedBounds.isEmpty() && !TextUtils.isEmpty(this.mTextToDraw)) {
            calculateOffsets(0.0f);
            this.mTextureAscent = this.mTextPaint.ascent();
            this.mTextureDescent = this.mTextPaint.descent();
            int round = Math.round(this.mTextPaint.measureText(this.mTextToDraw, 0, this.mTextToDraw.length()));
            int round2 = Math.round(this.mTextureDescent - this.mTextureAscent);
            if (round > 0 && round2 > 0) {
                this.mExpandedTitleTexture = Bitmap.createBitmap(round, round2, Config.ARGB_8888);
                new Canvas(this.mExpandedTitleTexture).drawText(this.mTextToDraw, 0, this.mTextToDraw.length(), 0.0f, ((float) round2) - this.mTextPaint.descent(), this.mTextPaint);
                if (this.mTexturePaint == null) {
                    this.mTexturePaint = new Paint(3);
                }
            }
        }
    }

    public void recalculate() {
        if (this.mView.getHeight() > 0 && this.mView.getWidth() > 0) {
            calculateBaseOffsets();
            calculateCurrentOffsets();
        }
    }

    void setText(CharSequence charSequence) {
        if (charSequence == null || !charSequence.equals(this.mText)) {
            this.mText = charSequence;
            this.mTextToDraw = null;
            clearTexture();
            recalculate();
        }
    }

    CharSequence getText() {
        return this.mText;
    }

    private void clearTexture() {
        if (this.mExpandedTitleTexture != null) {
            this.mExpandedTitleTexture.recycle();
            this.mExpandedTitleTexture = null;
        }
    }

    private static boolean isClose(float f, float f2) {
        return Math.abs(f - f2) < 0.001f;
    }

    int getExpandedTextColor() {
        return this.mExpandedTextColor;
    }

    int getCollapsedTextColor() {
        return this.mCollapsedTextColor;
    }

    private static int blendColors(int i, int i2, float f) {
        float f2 = FullImageView.ASPECT_RATIO_SQUARE - f;
        return Color.argb((int) ((((float) Color.alpha(i)) * f2) + (((float) Color.alpha(i2)) * f)), (int) ((((float) Color.red(i)) * f2) + (((float) Color.red(i2)) * f)), (int) ((((float) Color.green(i)) * f2) + (((float) Color.green(i2)) * f)), (int) ((f2 * ((float) Color.blue(i))) + (((float) Color.blue(i2)) * f)));
    }

    private static float lerp(float f, float f2, float f3, Interpolator interpolator) {
        if (interpolator != null) {
            f3 = interpolator.getInterpolation(f3);
        }
        return AnimationUtils.lerp(f, f2, f3);
    }

    private static boolean rectEquals(Rect rect, int i, int i2, int i3, int i4) {
        return rect.left == i && rect.top == i2 && rect.right == i3 && rect.bottom == i4;
    }
}
