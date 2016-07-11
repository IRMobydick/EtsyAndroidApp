package com.etsy.android.uikit.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.Path.FillType;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

public class HoleyOverlayDrawable extends Drawable {
    private Point mHolePosition;
    private int mHoleRadius;
    private Paint mPaint;
    private Path mPath;

    public HoleyOverlayDrawable(Context context, int i, Point point, int i2) {
        this.mHolePosition = point;
        this.mHoleRadius = i2;
        this.mPaint = new Paint();
        this.mPaint.setColor(context.getResources().getColor(i));
        this.mPaint.setAntiAlias(true);
        this.mPaint.setStyle(Style.FILL);
        this.mPath = new Path();
        this.mPath.setFillType(FillType.EVEN_ODD);
    }

    public void draw(Canvas canvas) {
        this.mPath.reset();
        this.mPath.addRect(new RectF(getBounds()), Direction.CW);
        this.mPath.addCircle((float) this.mHolePosition.x, (float) this.mHolePosition.y, (float) this.mHoleRadius, Direction.CCW);
        canvas.drawPath(this.mPath, this.mPaint);
    }

    public int getHoleRadius() {
        return this.mHoleRadius;
    }

    public void setHoleRadius(int i) {
        this.mHoleRadius = i;
        invalidateSelf();
    }

    public void setAlpha(int i) {
    }

    public void setColorFilter(ColorFilter colorFilter) {
    }

    public int getOpacity() {
        return -3;
    }
}
