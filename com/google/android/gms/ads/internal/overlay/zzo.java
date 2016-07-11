package com.google.android.gms.ads.internal.overlay;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageButton;
import com.google.android.gms.ads.internal.client.C1089r;
import com.google.android.gms.internal.jw;

@jw
public class zzo extends FrameLayout implements OnClickListener {
    private final ImageButton zzHZ;
    private final q zzIa;

    public zzo(Context context, int i, q qVar) {
        super(context);
        this.zzIa = qVar;
        setOnClickListener(this);
        this.zzHZ = new ImageButton(context);
        this.zzHZ.setImageResource(17301527);
        this.zzHZ.setBackgroundColor(0);
        this.zzHZ.setOnClickListener(this);
        this.zzHZ.setPadding(0, 0, 0, 0);
        this.zzHZ.setContentDescription("Interstitial close button");
        int a = C1089r.m5951a().m6166a(context, i);
        addView(this.zzHZ, new LayoutParams(a, a, 17));
    }

    public void onClick(View view) {
        if (this.zzIa != null) {
            this.zzIa.zzgp();
        }
    }

    public void zza(boolean z, boolean z2) {
        if (!z2) {
            this.zzHZ.setVisibility(0);
        } else if (z) {
            this.zzHZ.setVisibility(4);
        } else {
            this.zzHZ.setVisibility(8);
        }
    }
}
