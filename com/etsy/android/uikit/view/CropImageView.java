package com.etsy.android.uikit.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.etsy.android.lib.R;

public class CropImageView extends ImageView {
    private int mHorizontalLineOneYCoord;
    private int mHorizontalLineTwoYCoord;
    protected int mLineHeight;
    protected int mLineWidth;
    private Paint mPaint;
    private int mVerticalLineOneXCoord;
    private int mVerticalLineTwoXCoord;

    public CropImageView(Context context) {
        super(context);
        init();
    }

    public CropImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public CropImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        this.mPaint = new Paint();
        this.mPaint.setColor(getResources().getColor(R.white));
    }

    protected void onDraw(Canvas canvas) {
        canvas.drawLine((float) this.mVerticalLineOneXCoord, 0.0f, (float) this.mVerticalLineOneXCoord, (float) this.mLineHeight, this.mPaint);
        canvas.drawLine((float) this.mVerticalLineTwoXCoord, 0.0f, (float) this.mVerticalLineTwoXCoord, (float) this.mLineHeight, this.mPaint);
        canvas.drawLine(0.0f, (float) this.mHorizontalLineOneYCoord, (float) this.mLineWidth, (float) this.mHorizontalLineOneYCoord, this.mPaint);
        canvas.drawLine(0.0f, (float) this.mHorizontalLineTwoYCoord, (float) this.mLineWidth, (float) this.mHorizontalLineTwoYCoord, this.mPaint);
        super.onDraw(canvas);
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.mLineWidth = getWidth();
        this.mLineHeight = getHeight();
        this.mVerticalLineOneXCoord = this.mLineWidth / 3;
        this.mVerticalLineTwoXCoord = (this.mLineWidth / 3) * 2;
        this.mHorizontalLineOneYCoord = this.mLineHeight / 3;
        this.mHorizontalLineTwoYCoord = (this.mLineHeight / 3) * 2;
    }
}
