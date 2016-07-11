package com.etsy.android.uikit.icon;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Drawable;
import com.etsy.android.uikit.view.FullImageView;

public class RoundedCornerDrawable extends Drawable {
    private int mBitmapHeight;
    private RectF mBitmapRect;
    private int mBitmapWidth;
    private RectF mBounds;
    private float mCornerRadius;
    private RectF mDrawingRect;
    private float mPadding;
    private Paint mPaint;
    private BitmapShader mShader;
    private Matrix mShaderMatrix;

    public RoundedCornerDrawable(Bitmap bitmap) {
        this.mBounds = new RectF();
        this.mDrawingRect = new RectF();
        this.mBitmapRect = new RectF();
        this.mShaderMatrix = new Matrix();
        init(bitmap, 5.0f, 0.0f);
    }

    public RoundedCornerDrawable(Bitmap bitmap, float f) {
        this.mBounds = new RectF();
        this.mDrawingRect = new RectF();
        this.mBitmapRect = new RectF();
        this.mShaderMatrix = new Matrix();
        init(bitmap, f, 0.0f);
    }

    public RoundedCornerDrawable(Bitmap bitmap, float f, float f2) {
        this.mBounds = new RectF();
        this.mDrawingRect = new RectF();
        this.mBitmapRect = new RectF();
        this.mShaderMatrix = new Matrix();
        init(bitmap, f, f2);
    }

    private void init(Bitmap bitmap, float f, float f2) {
        this.mCornerRadius = f;
        this.mPadding = f2;
        this.mBitmapWidth = bitmap.getWidth();
        this.mBitmapHeight = bitmap.getHeight();
        this.mBitmapRect.set(0.0f, 0.0f, (float) this.mBitmapWidth, (float) this.mBitmapHeight);
        this.mShader = new BitmapShader(bitmap, TileMode.CLAMP, TileMode.CLAMP);
        this.mShader.setLocalMatrix(this.mShaderMatrix);
        this.mPaint = new Paint();
        this.mPaint.setAntiAlias(true);
        this.mPaint.setShader(this.mShader);
    }

    public void draw(Canvas canvas) {
        canvas.drawRoundRect(this.mDrawingRect, this.mCornerRadius, this.mCornerRadius, this.mPaint);
    }

    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.mBounds.set(rect);
        this.mShaderMatrix.set(null);
        float f = ((float) this.mBitmapWidth) - (this.mPadding * 2.0f);
        float f2 = ((float) this.mBitmapHeight) - (this.mPadding * 2.0f);
        if (((float) this.mBitmapWidth) > f || ((float) this.mBitmapHeight) > f2) {
            f = Math.min(f / ((float) this.mBitmapWidth), f2 / ((float) this.mBitmapHeight));
        } else {
            f = FullImageView.ASPECT_RATIO_SQUARE;
        }
        this.mShaderMatrix.setScale(f, f);
        this.mShaderMatrix.postTranslate(this.mPadding, this.mPadding);
        this.mDrawingRect.set(this.mBitmapRect);
        this.mShaderMatrix.mapRect(this.mDrawingRect);
        this.mShader.setLocalMatrix(this.mShaderMatrix);
    }

    public void setAlpha(int i) {
        this.mPaint.setAlpha(i);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.mPaint.setColorFilter(colorFilter);
    }

    public int getOpacity() {
        return -3;
    }

    public int getIntrinsicHeight() {
        return this.mBitmapHeight;
    }

    public int getIntrinsicWidth() {
        return this.mBitmapWidth;
    }

    public float getCornerRadius() {
        return this.mCornerRadius;
    }

    public void setCornerRadius(float f) {
        this.mCornerRadius = f;
        invalidateSelf();
    }

    public float getPadding() {
        return this.mPadding;
    }

    public void setPadding(float f) {
        this.mPadding = f;
        invalidateSelf();
    }
}
