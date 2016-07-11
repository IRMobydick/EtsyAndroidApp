package com.etsy.android.uikit.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import com.etsy.android.lib.R;
import com.etsy.android.uikit.util.GraphikUtil;
import org.apache.commons.lang3.StringUtils;

public class BadgeDrawable extends Drawable {
    private ColorStateList mBadgeColorStateList;
    private Paint mBadgePaint;
    private String mBadgeText;
    private CircleType mCircleType;
    private int mHeight;
    private int mPadding;
    private CirclePosition mPosition;
    private RectF mRoundedRect;
    private Paint mStrokePaint;
    private ColorStateList mTextColorStateList;
    private Paint mTextPaint;
    private float mTextSize;
    private Rect mTxtRect;
    private int mWidth;
    private boolean mWillDraw;

    public enum CirclePosition {
        FULL,
        TOP_RIGHT
    }

    public enum CircleType {
        CIRCLE,
        ROUNDED_RECT
    }

    public BadgeDrawable(Context context, int i, int i2) {
        this.mTxtRect = new Rect();
        this.mBadgeText = StringUtils.EMPTY;
        this.mWillDraw = false;
        this.mPosition = CirclePosition.FULL;
        this.mCircleType = CircleType.CIRCLE;
        Resources resources = context.getResources();
        this.mTextSize = resources.getDimension(R.default_badge_text_size);
        this.mBadgeColorStateList = resources.getColorStateList(i);
        this.mTextColorStateList = resources.getColorStateList(i2);
        this.mBadgePaint = new Paint();
        this.mBadgePaint.setColor(this.mBadgeColorStateList.getDefaultColor());
        this.mBadgePaint.setAntiAlias(true);
        this.mBadgePaint.setStyle(Style.FILL);
        this.mTextPaint = new Paint();
        this.mTextPaint.setColor(this.mTextColorStateList.getDefaultColor());
        if (!GraphikUtil.m5546a(context, this.mTextPaint, R.typeface_bold)) {
            this.mTextPaint.setTypeface(Typeface.DEFAULT_BOLD);
        }
        this.mTextPaint.setTextSize(this.mTextSize);
        this.mTextPaint.setAntiAlias(true);
        this.mTextPaint.setTextAlign(Align.CENTER);
        this.mRoundedRect = new RectF();
    }

    public void draw(Canvas canvas) {
        if (this.mWillDraw) {
            float min;
            Rect bounds = getBounds();
            float f = (float) (bounds.right - bounds.left);
            float f2 = (float) (bounds.bottom - bounds.top);
            if (this.mPosition == CirclePosition.TOP_RIGHT) {
                min = ((Math.min(f, f2) / 2.0f) - FullImageView.ASPECT_RATIO_SQUARE) / 2.0f;
                f = (f - min) - FullImageView.ASPECT_RATIO_SQUARE;
                f2 = min + FullImageView.ASPECT_RATIO_SQUARE;
            } else {
                f2 = Math.min(f, f2) / 2.0f;
                f = f2;
                min = f2;
            }
            if (this.mPadding > 0) {
                min -= (float) this.mPadding;
            }
            this.mTextPaint.getTextBounds(this.mBadgeText, 0, this.mBadgeText.length(), this.mTxtRect);
            float f3 = (float) (this.mTxtRect.bottom - this.mTxtRect.top);
            float f4 = ((f3 / 2.0f) - ((float) this.mTxtRect.bottom)) + f2;
            if (this.mCircleType == CircleType.CIRCLE) {
                canvas.drawCircle(f, f2, min, this.mBadgePaint);
                if (this.mStrokePaint != null) {
                    canvas.drawCircle(f, f2, min, this.mStrokePaint);
                }
            } else if (this.mCircleType == CircleType.ROUNDED_RECT) {
                RectF rectF = this.mRoundedRect;
                rectF.set(this.mTxtRect);
                rectF.top = f2 - (f3 * 1.5f);
                rectF.bottom = f2 + (f3 * 1.5f);
                rectF.left -= f3;
                rectF.right += f3 - 2.0f;
                rectF.top -= (float) this.mPadding;
                rectF.bottom += (float) this.mPadding;
                rectF.left -= (float) this.mPadding;
                rectF.right += (float) this.mPadding;
                canvas.drawRoundRect(rectF, min, min, this.mBadgePaint);
                if (this.mStrokePaint != null) {
                    canvas.drawRoundRect(rectF, min, min, this.mStrokePaint);
                }
            }
            canvas.drawText(this.mBadgeText, f, f4, this.mTextPaint);
        }
    }

    public void setCount(int i) {
        this.mBadgeText = Integer.toString(i);
        this.mWillDraw = i > 0;
        invalidateSelf();
    }

    public void setBadgeText(String str) {
        this.mBadgeText = str;
        this.mWillDraw = true;
        invalidateSelf();
    }

    public void setAlpha(int i) {
    }

    public void setColorFilter(ColorFilter colorFilter) {
    }

    public int getOpacity() {
        return 0;
    }

    public String getBadgeText() {
        return this.mBadgeText;
    }

    public int getIntrinsicWidth() {
        return this.mWidth > 0 ? this.mWidth : super.getIntrinsicWidth();
    }

    public int getIntrinsicHeight() {
        return this.mHeight > 0 ? this.mHeight : super.getIntrinsicHeight();
    }

    protected void onBoundsChange(Rect rect) {
        this.mHeight = rect.height();
        this.mWidth = rect.width();
    }

    protected boolean onStateChange(int[] iArr) {
        this.mTextPaint.setColor(this.mTextColorStateList.getColorForState(iArr, this.mTextColorStateList.getDefaultColor()));
        this.mBadgePaint.setColor(this.mBadgeColorStateList.getColorForState(iArr, this.mBadgeColorStateList.getDefaultColor()));
        return true;
    }

    public void setPadding(int i) {
        this.mPadding = i;
        invalidateSelf();
    }

    public void setBadgeTextSize(int i) {
        this.mTextPaint.setTextSize((float) i);
        invalidateSelf();
    }

    public void setStroke(int i, int i2) {
        this.mStrokePaint = new Paint();
        this.mStrokePaint.setColor(i2);
        this.mStrokePaint.setStrokeWidth((float) i);
        this.mStrokePaint.setStyle(Style.STROKE);
        this.mStrokePaint.setAntiAlias(true);
        invalidateSelf();
    }

    public CircleType getCircleType() {
        return this.mCircleType;
    }

    public void setCircleType(CircleType circleType) {
        this.mCircleType = circleType;
    }
}
