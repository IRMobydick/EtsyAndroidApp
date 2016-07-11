package com.google.android.gms.ads.internal.formats;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.android.gms.ads.internal.C1101o;
import com.google.android.gms.ads.internal.client.C1089r;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.internal.jw;
import java.util.List;

@jw
/* renamed from: com.google.android.gms.ads.internal.formats.b */
class C1094b extends RelativeLayout {
    private static final float[] f4490a;
    private final RelativeLayout f4491b;
    private AnimationDrawable f4492c;

    static {
        f4490a = new float[]{5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f};
    }

    public C1094b(Context context, C1093a c1093a) {
        super(context);
        zzaa.zzz(c1093a);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(10);
        layoutParams.addRule(11);
        Drawable shapeDrawable = new ShapeDrawable(new RoundRectShape(f4490a, null, null));
        shapeDrawable.getPaint().setColor(c1093a.m5976c());
        this.f4491b = new RelativeLayout(context);
        this.f4491b.setLayoutParams(layoutParams);
        C1101o.m6043g().m7153a(this.f4491b, shapeDrawable);
        layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        if (!TextUtils.isEmpty(c1093a.m5974a())) {
            LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
            View textView = new TextView(context);
            textView.setLayoutParams(layoutParams2);
            textView.setId(1195835393);
            textView.setTypeface(Typeface.DEFAULT);
            textView.setText(c1093a.m5974a());
            textView.setTextColor(c1093a.m5977d());
            textView.setTextSize((float) c1093a.m5978e());
            textView.setPadding(C1089r.m5951a().m6166a(context, 4), 0, C1089r.m5951a().m6166a(context, 4), 0);
            this.f4491b.addView(textView);
            layoutParams.addRule(1, textView.getId());
        }
        View imageView = new ImageView(context);
        imageView.setLayoutParams(layoutParams);
        imageView.setId(1195835394);
        List<Drawable> b = c1093a.m5975b();
        if (b.size() > 1) {
            this.f4492c = new AnimationDrawable();
            for (Drawable addFrame : b) {
                this.f4492c.addFrame(addFrame, c1093a.m5979f());
            }
            C1101o.m6043g().m7153a(imageView, this.f4492c);
        } else if (b.size() == 1) {
            imageView.setImageDrawable((Drawable) b.get(0));
        }
        this.f4491b.addView(imageView);
        addView(this.f4491b);
    }

    public ViewGroup m5980a() {
        return this.f4491b;
    }

    public void onAttachedToWindow() {
        if (this.f4492c != null) {
            this.f4492c.start();
        }
        super.onAttachedToWindow();
    }
}
