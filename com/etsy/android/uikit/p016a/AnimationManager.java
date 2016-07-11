package com.etsy.android.uikit.p016a;

import android.animation.Animator;
import android.content.Context;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.etsy.android.uikit.a.a */
public final class AnimationManager {
    final Reference<Context> f3899a;
    final List<Animator> f3900b;
    final List<EtsyAnimator> f3901c;

    public AnimationManager(Context context) {
        this.f3899a = new WeakReference(context);
        this.f3900b = new ArrayList();
        this.f3901c = new ArrayList();
    }

    public void m5283a() {
        for (Animator animator : this.f3900b) {
            animator.removeAllListeners();
            if (animator.isRunning()) {
                animator.cancel();
            }
        }
        this.f3900b.clear();
        for (EtsyAnimator d : this.f3901c) {
            d.m5294d();
        }
        this.f3901c.clear();
    }

    public Animator m5282a(Animator animator) {
        this.f3900b.add(animator);
        return animator;
    }
}
