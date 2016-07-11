package com.etsy.android.iconsy.views;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetrics;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.text.TextPaint;
import android.util.SparseArray;
import android.util.SparseIntArray;
import com.etsy.android.iconsy.AbstractFontIcon;
import com.etsy.android.iconsy.TypefaceCache;
import com.etsy.android.lib.R;
import com.etsy.android.ui.dialog.EtsyDialogFragment;
import com.etsy.android.uikit.view.FullImageView;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;
import java.util.HashMap;

public class IconDrawable extends Drawable {
    private static final boolean DEBUG = false;
    private final Rect mCopiedBounds;
    private int mGravity;
    private IconDrawable mIconState;
    private boolean mMutated;
    private HashMap<String, SparseArray<SparseIntArray>> mSizeCache;
    private final Rect mTextMeas;
    private TextPaint mTextPaint;

    public IconDrawable() {
        this(null);
    }

    public IconDrawable(IconDrawable iconDrawable) {
        this.mSizeCache = new HashMap();
        this.mIconState = new IconDrawable(this, iconDrawable);
        this.mTextPaint = new TextPaint();
        this.mCopiedBounds = new Rect();
        this.mTextMeas = new Rect();
    }

    public ConstantState getConstantState() {
        return this.mIconState;
    }

    public Drawable mutate() {
        if (!this.mMutated && super.mutate() == this) {
            if (this.mIconState.f1127b != null) {
                this.mIconState.f1127b = new TextPaint(this.mIconState.f1127b);
            } else {
                this.mIconState.f1127b = new TextPaint(1);
                this.mIconState.f1127b.setTextAlign(Align.LEFT);
            }
            this.mMutated = true;
        }
        return this;
    }

    public void draw(Canvas canvas) {
        int alpha = this.mIconState.f1127b.getAlpha();
        this.mIconState.f1127b.setAlpha(modulateAlpha(alpha, this.mIconState.f1128c));
        this.mIconState.f1127b.setColorFilter(this.mIconState.f1129d);
        copyBounds(this.mCopiedBounds);
        canvas.drawText(this.mIconState.m788d(), calculateHorizontalOffset(this.mCopiedBounds, this.mIconState.m786b()), calculateVerticalOffset(this.mCopiedBounds), this.mIconState.f1127b);
        this.mIconState.f1127b.setAlpha(alpha);
    }

    private float calculateXHeight() {
        Rect rect = new Rect();
        this.mIconState.f1127b.getTextBounds(EtsyDialogFragment.OPT_X_BUTTON, 0, 1, rect);
        return (float) rect.height();
    }

    private float calculateHorizontalOffset(Rect rect, float f) {
        switch (this.mGravity & 7) {
            case Task.NETWORK_STATE_UNMETERED /*1*/:
                return ((((float) rect.width()) - f) / 2.0f) + ((float) rect.left);
            case CommonStatusCodes.INVALID_ACCOUNT /*5*/:
                return (((float) rect.width()) - f) + ((float) rect.left);
            default:
                return (float) rect.left;
        }
    }

    private float calculateVerticalOffset(Rect rect) {
        this.mTextPaint.getTextBounds(this.mIconState.m788d(), 0, 1, this.mTextMeas);
        switch (this.mGravity & 112) {
            case CommonStatusCodes.CANCELED /*16*/:
                return (calculateXHeight() + ((float) rect.centerY())) - 2.0f;
            case R.AppCompatTheme_panelMenuListTheme /*80*/:
                return (float) (rect.bottom - this.mTextMeas.bottom);
            default:
                return (float) (rect.top - this.mTextMeas.top);
        }
    }

    private int calculateSize(Rect rect) {
        int width = rect.width();
        int height = rect.height();
        if (width == 0 && height == 0) {
            return 0;
        }
        SparseArray sparseArray;
        int i;
        SparseArray sparseArray2 = (SparseArray) this.mSizeCache.get(this.mIconState.m785a());
        if (sparseArray2 == null) {
            sparseArray2 = new SparseArray();
            this.mSizeCache.put(this.mIconState.m785a(), sparseArray2);
            sparseArray = sparseArray2;
        } else {
            sparseArray = sparseArray2;
        }
        SparseIntArray sparseIntArray = (SparseIntArray) sparseArray.get(width);
        if (sparseIntArray != null) {
            i = sparseIntArray.get(height, 0);
            if (i > 0) {
                return i;
            }
        }
        this.mTextPaint.set(this.mIconState.f1127b);
        Rect rect2 = new Rect(0, 0, width, height);
        Rect rect3 = new Rect();
        int i2 = 0;
        int min = Math.min(rect.height(), rect.width());
        i = 0;
        int i3 = min;
        while (i2 <= i3) {
            i = (i2 + i3) >>> 1;
            int compareSize = compareSize(rect2, rect3, i);
            if (compareSize < 0) {
                i++;
            } else if (compareSize <= 0) {
                return i;
            } else {
                i3 = i - 1;
                i = i2;
                i2 = i3;
            }
            min = i;
            i = i2;
            i2 = min;
        }
        if (sparseIntArray == null) {
            sparseIntArray = new SparseIntArray();
            sparseArray.put(width, sparseIntArray);
        }
        sparseIntArray.put(height, i);
        return i;
    }

    private int compareSize(Rect rect, Rect rect2, int i) {
        this.mTextPaint.setTextSize((float) i);
        this.mTextPaint.getTextBounds(this.mIconState.m788d(), 0, 1, rect2);
        if (rect2.height() == rect.height() && rect2.width() == rect.width()) {
            return 0;
        }
        if (rect2.height() >= rect.height() || rect2.width() >= rect.width()) {
            return 1;
        }
        return -1;
    }

    private int modulateAlpha(int i, int i2) {
        return (((i2 >>> 7) + i2) * i) >>> 8;
    }

    public int getIntrinsicWidth() {
        if (this.mIconState.f1130e) {
            return -1;
        }
        return Math.round(this.mIconState.m786b());
    }

    public int getIntrinsicHeight() {
        if (this.mIconState.f1130e) {
            return -1;
        }
        FontMetrics c = this.mIconState.m787c();
        return Math.round(c.bottom - c.top);
    }

    public void setAlpha(int i) {
        this.mIconState.f1128c = i;
        invalidateSelf();
    }

    public void setBounds(int i, int i2, int i3, int i4) {
        super.setBounds(i, i2, i3, i4);
        if (this.mIconState.f1130e) {
            Rect rect = new Rect(i, i2, i3, i4);
            if (rect.height() > 0 && rect.width() > 0) {
                this.mIconState.f1127b.setTextSize((float) calculateSize(rect));
            }
        }
    }

    public void setBounds(Rect rect) {
        super.setBounds(rect);
        if (this.mIconState.f1130e && rect.height() > 0 && rect.width() > 0) {
            this.mIconState.f1127b.setTextSize((float) calculateSize(rect));
        }
    }

    public void setDefaultAlpha(int i) {
        this.mIconState.f1127b.setAlpha(i);
        invalidateSelf();
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.mIconState.f1127b.setColorFilter(colorFilter);
        invalidateSelf();
    }

    public void setDefaultColorFilter(ColorFilter colorFilter) {
        this.mIconState.f1129d = colorFilter;
        invalidateSelf();
    }

    public void setIconFont(AbstractFontIcon abstractFontIcon) {
        this.mIconState.f1126a = abstractFontIcon;
        this.mIconState.f1127b.setTypeface(TypefaceCache.m772a().m773a(abstractFontIcon));
        invalidateSelf();
    }

    public void setTextSize(float f) {
        if (f < FullImageView.ASPECT_RATIO_SQUARE) {
            this.mIconState.f1130e = true;
        }
        this.mIconState.f1127b.setTextSize(f);
        invalidateSelf();
    }

    public void setColorId(Resources resources, int i) {
        this.mIconState.f1127b.setColor(resources.getColor(i));
        invalidateSelf();
    }

    public void setColor(int i) {
        this.mIconState.f1127b.setColor(i);
        invalidateSelf();
    }

    public void setGravity(int i) {
        this.mGravity = i;
    }

    public int getOpacity() {
        return -3;
    }
}
