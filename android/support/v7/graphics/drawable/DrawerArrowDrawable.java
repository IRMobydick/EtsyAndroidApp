package android.support.v7.graphics.drawable;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.FloatRange;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.appcompat.C0234R;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class DrawerArrowDrawable extends Drawable {
    public static final int ARROW_DIRECTION_END = 3;
    public static final int ARROW_DIRECTION_LEFT = 0;
    public static final int ARROW_DIRECTION_RIGHT = 1;
    public static final int ARROW_DIRECTION_START = 2;
    private static final float ARROW_HEAD_ANGLE;
    private float mArrowHeadLength;
    private float mArrowShaftLength;
    private float mBarGap;
    private float mBarLength;
    private int mDirection;
    private float mMaxCutForBarSize;
    private final Paint mPaint;
    private final Path mPath;
    private float mProgress;
    private final int mSize;
    private boolean mSpin;
    private boolean mVerticalMirror;

    @Retention(RetentionPolicy.SOURCE)
    public @interface ArrowDirection {
    }

    static {
        ARROW_HEAD_ANGLE = (float) Math.toRadians(45.0d);
    }

    public DrawerArrowDrawable(Context context) {
        this.mPaint = new Paint();
        this.mPath = new Path();
        this.mVerticalMirror = false;
        this.mDirection = ARROW_DIRECTION_START;
        this.mPaint.setStyle(Style.STROKE);
        this.mPaint.setStrokeJoin(Join.MITER);
        this.mPaint.setStrokeCap(Cap.BUTT);
        this.mPaint.setAntiAlias(true);
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(null, C0234R.styleable.DrawerArrowToggle, C0234R.attr.drawerArrowStyle, C0234R.style.Base_Widget_AppCompat_DrawerArrowToggle);
        setColor(obtainStyledAttributes.getColor(C0234R.styleable.DrawerArrowToggle_color, ARROW_DIRECTION_LEFT));
        setBarThickness(obtainStyledAttributes.getDimension(C0234R.styleable.DrawerArrowToggle_thickness, 0.0f));
        setSpinEnabled(obtainStyledAttributes.getBoolean(C0234R.styleable.DrawerArrowToggle_spinBars, true));
        setGapSize((float) Math.round(obtainStyledAttributes.getDimension(C0234R.styleable.DrawerArrowToggle_gapBetweenBars, 0.0f)));
        this.mSize = obtainStyledAttributes.getDimensionPixelSize(C0234R.styleable.DrawerArrowToggle_drawableSize, ARROW_DIRECTION_LEFT);
        this.mBarLength = (float) Math.round(obtainStyledAttributes.getDimension(C0234R.styleable.DrawerArrowToggle_barLength, 0.0f));
        this.mArrowHeadLength = (float) Math.round(obtainStyledAttributes.getDimension(C0234R.styleable.DrawerArrowToggle_arrowHeadLength, 0.0f));
        this.mArrowShaftLength = obtainStyledAttributes.getDimension(C0234R.styleable.DrawerArrowToggle_arrowShaftLength, 0.0f);
        obtainStyledAttributes.recycle();
    }

    public void setArrowHeadLength(float f) {
        if (this.mArrowHeadLength != f) {
            this.mArrowHeadLength = f;
            invalidateSelf();
        }
    }

    public float getArrowHeadLength() {
        return this.mArrowHeadLength;
    }

    public void setArrowShaftLength(float f) {
        if (this.mArrowShaftLength != f) {
            this.mArrowShaftLength = f;
            invalidateSelf();
        }
    }

    public float getArrowShaftLength() {
        return this.mArrowShaftLength;
    }

    public float getBarLength() {
        return this.mBarLength;
    }

    public void setBarLength(float f) {
        if (this.mBarLength != f) {
            this.mBarLength = f;
            invalidateSelf();
        }
    }

    public void setColor(@ColorInt int i) {
        if (i != this.mPaint.getColor()) {
            this.mPaint.setColor(i);
            invalidateSelf();
        }
    }

    @ColorInt
    public int getColor() {
        return this.mPaint.getColor();
    }

    public void setBarThickness(float f) {
        if (this.mPaint.getStrokeWidth() != f) {
            this.mPaint.setStrokeWidth(f);
            this.mMaxCutForBarSize = (float) (((double) (f / 2.0f)) * Math.cos((double) ARROW_HEAD_ANGLE));
            invalidateSelf();
        }
    }

    public float getBarThickness() {
        return this.mPaint.getStrokeWidth();
    }

    public float getGapSize() {
        return this.mBarGap;
    }

    public void setGapSize(float f) {
        if (f != this.mBarGap) {
            this.mBarGap = f;
            invalidateSelf();
        }
    }

    public void setDirection(int i) {
        if (i != this.mDirection) {
            this.mDirection = i;
            invalidateSelf();
        }
    }

    public boolean isSpinEnabled() {
        return this.mSpin;
    }

    public void setSpinEnabled(boolean z) {
        if (this.mSpin != z) {
            this.mSpin = z;
            invalidateSelf();
        }
    }

    public int getDirection() {
        return this.mDirection;
    }

    public void setVerticalMirror(boolean z) {
        if (this.mVerticalMirror != z) {
            this.mVerticalMirror = z;
            invalidateSelf();
        }
    }

    public void draw(Canvas canvas) {
        int i;
        float f;
        float f2;
        Rect bounds = getBounds();
        switch (this.mDirection) {
            case ARROW_DIRECTION_LEFT /*0*/:
                i = ARROW_DIRECTION_LEFT;
                break;
            case ARROW_DIRECTION_RIGHT /*1*/:
                i = ARROW_DIRECTION_RIGHT;
                break;
            case ARROW_DIRECTION_END /*3*/:
                if (DrawableCompat.getLayoutDirection(this) != 0) {
                    i = ARROW_DIRECTION_LEFT;
                    break;
                } else {
                    i = ARROW_DIRECTION_RIGHT;
                    break;
                }
            default:
                if (DrawableCompat.getLayoutDirection(this) != ARROW_DIRECTION_RIGHT) {
                    i = ARROW_DIRECTION_LEFT;
                    break;
                } else {
                    i = ARROW_DIRECTION_RIGHT;
                    break;
                }
        }
        float lerp = lerp(this.mBarLength, (float) Math.sqrt((double) ((this.mArrowHeadLength * this.mArrowHeadLength) * 2.0f)), this.mProgress);
        float lerp2 = lerp(this.mBarLength, this.mArrowShaftLength, this.mProgress);
        float round = (float) Math.round(lerp(0.0f, this.mMaxCutForBarSize, this.mProgress));
        float lerp3 = lerp(0.0f, ARROW_HEAD_ANGLE, this.mProgress);
        if (i != 0) {
            f = 0.0f;
        } else {
            f = -180.0f;
        }
        if (i != 0) {
            f2 = 180.0f;
        } else {
            f2 = 0.0f;
        }
        f2 = lerp(f, f2, this.mProgress);
        f = (float) Math.round(((double) lerp) * Math.cos((double) lerp3));
        lerp = (float) Math.round(((double) lerp) * Math.sin((double) lerp3));
        this.mPath.rewind();
        lerp3 = lerp(this.mBarGap + this.mPaint.getStrokeWidth(), -this.mMaxCutForBarSize, this.mProgress);
        float f3 = (-lerp2) / 2.0f;
        this.mPath.moveTo(f3 + round, 0.0f);
        this.mPath.rLineTo(lerp2 - (round * 2.0f), 0.0f);
        this.mPath.moveTo(f3, lerp3);
        this.mPath.rLineTo(f, lerp);
        this.mPath.moveTo(f3, -lerp3);
        this.mPath.rLineTo(f, -lerp);
        this.mPath.close();
        canvas.save();
        f = this.mPaint.getStrokeWidth();
        canvas.translate((float) bounds.centerX(), (float) (((double) ((float) ((((int) ((((float) bounds.height()) - (3.0f * f)) - (this.mBarGap * 2.0f))) / 4) * ARROW_DIRECTION_START))) + ((((double) f) * 1.5d) + ((double) this.mBarGap))));
        if (this.mSpin) {
            canvas.rotate(((float) ((i ^ this.mVerticalMirror) != 0 ? -1 : ARROW_DIRECTION_RIGHT)) * f2);
        } else if (i != 0) {
            canvas.rotate(180.0f);
        }
        canvas.drawPath(this.mPath, this.mPaint);
        canvas.restore();
    }

    public void setAlpha(int i) {
        if (i != this.mPaint.getAlpha()) {
            this.mPaint.setAlpha(i);
            invalidateSelf();
        }
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.mPaint.setColorFilter(colorFilter);
        invalidateSelf();
    }

    public int getIntrinsicHeight() {
        return this.mSize;
    }

    public int getIntrinsicWidth() {
        return this.mSize;
    }

    public int getOpacity() {
        return -3;
    }

    @FloatRange(from = 0.0d, to = 1.0d)
    public float getProgress() {
        return this.mProgress;
    }

    public void setProgress(@FloatRange(from = 0.0d, to = 1.0d) float f) {
        if (this.mProgress != f) {
            this.mProgress = f;
            invalidateSelf();
        }
    }

    public final Paint getPaint() {
        return this.mPaint;
    }

    private static float lerp(float f, float f2, float f3) {
        return ((f2 - f) * f3) + f;
    }
}