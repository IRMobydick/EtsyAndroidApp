package com.etsy.android.uikit.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Outline;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.Shape;
import android.support.annotation.ColorInt;
import android.support.annotation.DimenRes;
import android.util.AttributeSet;
import android.util.StateSet;
import android.widget.CheckBox;
import com.etsy.android.iconsy.views.IconDrawable;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.EtsyApplication;
import com.etsy.android.lib.util.aa;
import com.etsy.android.lib.util.fonts.StandardFontIcon;

public class RoundStrokedCheckBox extends CheckBox {
    @ColorInt
    private int backgroundColor;
    @ColorInt
    private int drawableColor;
    @DimenRes
    private int drawableInset;
    @ColorInt
    private int strokeColor;
    @DimenRes
    private int strokeWidth;

    /* renamed from: com.etsy.android.uikit.view.RoundStrokedCheckBox.1 */
    class C10231 extends ShapeDrawable {
        final /* synthetic */ RoundStrokedCheckBox f4219a;

        C10231(RoundStrokedCheckBox roundStrokedCheckBox, Shape shape) {
            this.f4219a = roundStrokedCheckBox;
            super(shape);
        }

        public void setBounds(int i, int i2, int i3, int i4) {
            Rect rect = new Rect(i, i2, i3, i4);
            rect.inset(this.f4219a.strokeWidth / 2, this.f4219a.strokeWidth / 2);
            super.setBounds(rect.left, rect.top, rect.right, rect.bottom);
        }

        @TargetApi(21)
        public void getOutline(Outline outline) {
            if (aa.m3170d()) {
                outline.setOval(getBounds());
            }
        }
    }

    public RoundStrokedCheckBox(Context context) {
        super(context);
        init(null, 0);
    }

    public RoundStrokedCheckBox(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(attributeSet, 0);
    }

    public RoundStrokedCheckBox(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(attributeSet, i);
    }

    private void init(AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.RoundStrokedCheckBox);
        this.strokeWidth = obtainStyledAttributes.getDimensionPixelSize(R.RoundStrokedCheckBox_unselectedStrokeWidth, 2);
        this.strokeColor = obtainStyledAttributes.getColor(R.RoundStrokedCheckBox_unselectedStrokeColor, getResources().getColor(R.white));
        this.backgroundColor = obtainStyledAttributes.getColor(R.RoundStrokedCheckBox_selectedBackgroundColor, getResources().getColor(17170444));
        this.drawableColor = obtainStyledAttributes.getColor(R.RoundStrokedCheckBox_selectedDrawableColor, getResources().getColor(R.white));
        this.drawableInset = obtainStyledAttributes.getDimensionPixelSize(R.RoundStrokedCheckBox_selectedDrawableInset, 0);
        obtainStyledAttributes.recycle();
        Drawable backgroundDrawable = getBackgroundDrawable();
        backgroundDrawable.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
        setBackgroundDrawable(backgroundDrawable);
        setButtonDrawable(null);
    }

    private Drawable getBackgroundDrawable() {
        Drawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{16843518}, getCheckedDrawable());
        stateListDrawable.addState(StateSet.WILD_CARD, getDefaultDrawable());
        return stateListDrawable;
    }

    private Drawable getDefaultDrawable() {
        Drawable c10231 = new C10231(this, new OvalShape());
        c10231.getPaint().setColor(this.strokeColor);
        c10231.getPaint().setStrokeWidth((float) this.strokeWidth);
        c10231.getPaint().setStyle(Style.STROKE);
        c10231.getPaint().setAntiAlias(true);
        return c10231;
    }

    private Drawable getCheckedDrawable() {
        ShapeDrawable shapeDrawable = new ShapeDrawable(new OvalShape());
        shapeDrawable.getPaint().setColor(this.backgroundColor);
        shapeDrawable.getPaint().setStyle(Style.FILL);
        shapeDrawable.getPaint().setAntiAlias(true);
        IconDrawable a = IconDrawable.m775a(EtsyApplication.get().getResources()).m780a(StandardFontIcon.CHECK).m779a(this.drawableColor).m784e(17).m777a();
        Drawable layerDrawable = new LayerDrawable(new Drawable[]{shapeDrawable, a});
        layerDrawable.setLayerInset(1, this.drawableInset + (this.strokeWidth / 2), this.drawableInset + this.strokeWidth, this.drawableInset, this.drawableInset);
        return layerDrawable;
    }
}
