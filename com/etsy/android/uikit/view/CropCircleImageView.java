package com.etsy.android.uikit.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import com.etsy.android.lib.R;

public class CropCircleImageView extends CropImageView {
    private Bitmap mBitmapOverlay;
    private Paint mCircleTransparentPaint;
    private Paint mDarkBackgroundPaint;
    private Canvas mOverlayDrawingCanvas;

    public CropCircleImageView(Context context) {
        super(context);
        init();
    }

    public CropCircleImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public CropCircleImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        Resources resources = getResources();
        Paint paint = new Paint();
        paint.setColor(resources.getColor(R.transparent));
        paint.setXfermode(new PorterDuffXfermode(Mode.CLEAR));
        this.mCircleTransparentPaint = paint;
        paint = new Paint();
        paint.setColor(resources.getColor(R.black_transparent));
        this.mDarkBackgroundPaint = paint;
        this.mOverlayDrawingCanvas = new Canvas();
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        if (!(i == i3 && i2 == i4)) {
            Bitmap createBitmap = Bitmap.createBitmap(i, i2, Config.ARGB_8888);
            this.mOverlayDrawingCanvas.setBitmap(createBitmap);
            this.mOverlayDrawingCanvas.drawRect(0.0f, 0.0f, (float) i, (float) i2, this.mDarkBackgroundPaint);
            this.mOverlayDrawingCanvas.drawCircle((float) (i / 2), (float) (i2 / 2), (float) (i / 2), this.mCircleTransparentPaint);
            this.mBitmapOverlay = createBitmap;
        }
        super.onSizeChanged(i, i2, i3, i4);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(this.mBitmapOverlay, 0.0f, 0.0f, null);
    }
}
