package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.util.client.C1129c;
import com.google.android.gms.ads.mediation.d;
import com.google.android.gms.ads.mediation.e;
import com.google.android.gms.ads.mediation.f;
import com.google.android.gms.ads.mediation.g;
import com.google.android.gms.ads.mediation.h;
import com.google.android.gms.ads.mediation.i;
import com.google.android.gms.ads.mediation.j;
import com.google.android.gms.common.internal.zzaa;

@jw
public final class ia implements e, g, i {
    private final zzgc f5076a;
    private j f5077b;

    public ia(zzgc com_google_android_gms_internal_zzgc) {
        this.f5076a = com_google_android_gms_internal_zzgc;
    }

    public j m6710a() {
        return this.f5077b;
    }

    public void m6711a(d dVar) {
        zzaa.zzdc("onAdLoaded must be called on the main UI thread.");
        C1129c.m6185a("Adapter called onAdLoaded.");
        try {
            this.f5076a.onAdLoaded();
        } catch (Throwable e) {
            C1129c.m6193d("Could not call onAdLoaded.", e);
        }
    }

    public void m6712a(d dVar, int i) {
        zzaa.zzdc("onAdFailedToLoad must be called on the main UI thread.");
        C1129c.m6185a("Adapter called onAdFailedToLoad with error. " + i);
        try {
            this.f5076a.onAdFailedToLoad(i);
        } catch (Throwable e) {
            C1129c.m6193d("Could not call onAdFailedToLoad.", e);
        }
    }

    public void m6713a(f fVar) {
        zzaa.zzdc("onAdLoaded must be called on the main UI thread.");
        C1129c.m6185a("Adapter called onAdLoaded.");
        try {
            this.f5076a.onAdLoaded();
        } catch (Throwable e) {
            C1129c.m6193d("Could not call onAdLoaded.", e);
        }
    }

    public void m6714a(f fVar, int i) {
        zzaa.zzdc("onAdFailedToLoad must be called on the main UI thread.");
        C1129c.m6185a("Adapter called onAdFailedToLoad with error " + i + ".");
        try {
            this.f5076a.onAdFailedToLoad(i);
        } catch (Throwable e) {
            C1129c.m6193d("Could not call onAdFailedToLoad.", e);
        }
    }

    public void m6715a(h hVar) {
        zzaa.zzdc("onAdOpened must be called on the main UI thread.");
        C1129c.m6185a("Adapter called onAdOpened.");
        try {
            this.f5076a.onAdOpened();
        } catch (Throwable e) {
            C1129c.m6193d("Could not call onAdOpened.", e);
        }
    }

    public void m6716a(h hVar, int i) {
        zzaa.zzdc("onAdFailedToLoad must be called on the main UI thread.");
        C1129c.m6185a("Adapter called onAdFailedToLoad with error " + i + ".");
        try {
            this.f5076a.onAdFailedToLoad(i);
        } catch (Throwable e) {
            C1129c.m6193d("Could not call onAdFailedToLoad.", e);
        }
    }

    public void m6717a(h hVar, j jVar) {
        zzaa.zzdc("onAdLoaded must be called on the main UI thread.");
        C1129c.m6185a("Adapter called onAdLoaded.");
        this.f5077b = jVar;
        try {
            this.f5076a.onAdLoaded();
        } catch (Throwable e) {
            C1129c.m6193d("Could not call onAdLoaded.", e);
        }
    }

    public void m6718b(d dVar) {
        zzaa.zzdc("onAdOpened must be called on the main UI thread.");
        C1129c.m6185a("Adapter called onAdOpened.");
        try {
            this.f5076a.onAdOpened();
        } catch (Throwable e) {
            C1129c.m6193d("Could not call onAdOpened.", e);
        }
    }

    public void m6719b(f fVar) {
        zzaa.zzdc("onAdOpened must be called on the main UI thread.");
        C1129c.m6185a("Adapter called onAdOpened.");
        try {
            this.f5076a.onAdOpened();
        } catch (Throwable e) {
            C1129c.m6193d("Could not call onAdOpened.", e);
        }
    }

    public void m6720b(h hVar) {
        zzaa.zzdc("onAdClosed must be called on the main UI thread.");
        C1129c.m6185a("Adapter called onAdClosed.");
        try {
            this.f5076a.onAdClosed();
        } catch (Throwable e) {
            C1129c.m6193d("Could not call onAdClosed.", e);
        }
    }

    public void m6721c(d dVar) {
        zzaa.zzdc("onAdClosed must be called on the main UI thread.");
        C1129c.m6185a("Adapter called onAdClosed.");
        try {
            this.f5076a.onAdClosed();
        } catch (Throwable e) {
            C1129c.m6193d("Could not call onAdClosed.", e);
        }
    }

    public void m6722c(f fVar) {
        zzaa.zzdc("onAdClosed must be called on the main UI thread.");
        C1129c.m6185a("Adapter called onAdClosed.");
        try {
            this.f5076a.onAdClosed();
        } catch (Throwable e) {
            C1129c.m6193d("Could not call onAdClosed.", e);
        }
    }

    public void m6723c(h hVar) {
        zzaa.zzdc("onAdLeftApplication must be called on the main UI thread.");
        C1129c.m6185a("Adapter called onAdLeftApplication.");
        try {
            this.f5076a.onAdLeftApplication();
        } catch (Throwable e) {
            C1129c.m6193d("Could not call onAdLeftApplication.", e);
        }
    }

    public void m6724d(d dVar) {
        zzaa.zzdc("onAdLeftApplication must be called on the main UI thread.");
        C1129c.m6185a("Adapter called onAdLeftApplication.");
        try {
            this.f5076a.onAdLeftApplication();
        } catch (Throwable e) {
            C1129c.m6193d("Could not call onAdLeftApplication.", e);
        }
    }

    public void m6725d(f fVar) {
        zzaa.zzdc("onAdLeftApplication must be called on the main UI thread.");
        C1129c.m6185a("Adapter called onAdLeftApplication.");
        try {
            this.f5076a.onAdLeftApplication();
        } catch (Throwable e) {
            C1129c.m6193d("Could not call onAdLeftApplication.", e);
        }
    }

    public void m6726d(h hVar) {
        zzaa.zzdc("onAdClicked must be called on the main UI thread.");
        j a = m6710a();
        if (a == null) {
            C1129c.m6192d("Could not call onAdClicked since NativeAdMapper is null.");
        } else if (a.b()) {
            C1129c.m6185a("Adapter called onAdClicked.");
            try {
                this.f5076a.onAdClicked();
            } catch (Throwable e) {
                C1129c.m6193d("Could not call onAdClicked.", e);
            }
        } else {
            C1129c.m6185a("Could not call onAdClicked since setOverrideClickHandling is not set to true");
        }
    }

    public void m6727e(d dVar) {
        zzaa.zzdc("onAdClicked must be called on the main UI thread.");
        C1129c.m6185a("Adapter called onAdClicked.");
        try {
            this.f5076a.onAdClicked();
        } catch (Throwable e) {
            C1129c.m6193d("Could not call onAdClicked.", e);
        }
    }

    public void m6728e(f fVar) {
        zzaa.zzdc("onAdClicked must be called on the main UI thread.");
        C1129c.m6185a("Adapter called onAdClicked.");
        try {
            this.f5076a.onAdClicked();
        } catch (Throwable e) {
            C1129c.m6193d("Could not call onAdClicked.", e);
        }
    }
}
