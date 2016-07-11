package com.etsy.android.uikit.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.ColorInt;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.etsy.android.lib.R;

public class DiagonalStrikeImageView extends ImageView {
    @ColorInt
    private int mLineColor;
    private int mLineWidth;
    private Paint mPaint;

    public DiagonalStrikeImageView(Context context) {
        super(context);
        init(context, null);
    }

    public DiagonalStrikeImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet);
    }

    public DiagonalStrikeImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet);
    }

    @TargetApi(21)
    public DiagonalStrikeImageView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init(context, attributeSet);
    }

    private void init(Context context, AttributeSet attributeSet) {
        Resources resources = context.getResources();
        this.mLineColor = resources.getColor(R.red);
        this.mLineWidth = resources.getDimensionPixelSize(R.double_divider_height);
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(attributeSet, R.DiagonalStrikeImageView, 0, 0);
            try {
                this.mLineColor = obtainStyledAttributes.getColor(R.DiagonalStrikeImageView_diagonalLineColor, this.mLineColor);
                this.mLineWidth = obtainStyledAttributes.getDimensionPixelSize(R.DiagonalStrikeImageView_diagonalLineWidth, this.mLineWidth);
            } finally {
                obtainStyledAttributes.recycle();
            }
        }
        this.mPaint = new Paint();
        this.mPaint.setAntiAlias(true);
        this.mPaint.setColor(this.mLineColor);
        this.mPaint.setStrokeWidth((float) this.mLineWidth);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(0.0f, (float) getHeight(), (float) getWidth(), 0.0f, this.mPaint);
    }
}
