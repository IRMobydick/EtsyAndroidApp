package com.google.android.gms.ads;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import com.google.android.gms.ads.internal.client.C1089r;
import com.google.android.gms.ads.internal.util.client.C1129c;
import com.google.android.gms.internal.zzgz;

public class AdActivity extends Activity {
    public static final String CLASS_NAME = "com.google.android.gms.ads.AdActivity";
    public static final String SIMPLE_CLASS_NAME = "AdActivity";
    private zzgz zzpc;

    private void zzaW() {
        if (this.zzpc != null) {
            try {
                this.zzpc.zzaW();
            } catch (Throwable e) {
                C1129c.m6193d("Could not forward setContentViewSet to ad overlay:", e);
            }
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        try {
            this.zzpc.onActivityResult(i, i2, intent);
        } catch (Throwable e) {
            C1129c.m6193d("Could not forward onActivityResult to ad overlay:", e);
        }
        super.onActivityResult(i, i2, intent);
    }

    public void onBackPressed() {
        boolean z = true;
        try {
            if (this.zzpc != null) {
                z = this.zzpc.zzgq();
            }
        } catch (Throwable e) {
            C1129c.m6193d("Could not forward onBackPressed to ad overlay:", e);
        }
        if (z) {
            super.onBackPressed();
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.zzpc = C1089r.m5953b().m5950b((Activity) this);
        if (this.zzpc == null) {
            C1129c.m6192d("Could not create ad overlay.");
            finish();
            return;
        }
        try {
            this.zzpc.onCreate(bundle);
        } catch (Throwable e) {
            C1129c.m6193d("Could not forward onCreate to ad overlay:", e);
            finish();
        }
    }

    protected void onDestroy() {
        try {
            if (this.zzpc != null) {
                this.zzpc.onDestroy();
            }
        } catch (Throwable e) {
            C1129c.m6193d("Could not forward onDestroy to ad overlay:", e);
        }
        super.onDestroy();
    }

    protected void onPause() {
        try {
            if (this.zzpc != null) {
                this.zzpc.onPause();
            }
        } catch (Throwable e) {
            C1129c.m6193d("Could not forward onPause to ad overlay:", e);
            finish();
        }
        super.onPause();
    }

    protected void onRestart() {
        super.onRestart();
        try {
            if (this.zzpc != null) {
                this.zzpc.onRestart();
            }
        } catch (Throwable e) {
            C1129c.m6193d("Could not forward onRestart to ad overlay:", e);
            finish();
        }
    }

    protected void onResume() {
        super.onResume();
        try {
            if (this.zzpc != null) {
                this.zzpc.onResume();
            }
        } catch (Throwable e) {
            C1129c.m6193d("Could not forward onResume to ad overlay:", e);
            finish();
        }
    }

    protected void onSaveInstanceState(Bundle bundle) {
        try {
            if (this.zzpc != null) {
                this.zzpc.onSaveInstanceState(bundle);
            }
        } catch (Throwable e) {
            C1129c.m6193d("Could not forward onSaveInstanceState to ad overlay:", e);
            finish();
        }
        super.onSaveInstanceState(bundle);
    }

    protected void onStart() {
        super.onStart();
        try {
            if (this.zzpc != null) {
                this.zzpc.onStart();
            }
        } catch (Throwable e) {
            C1129c.m6193d("Could not forward onStart to ad overlay:", e);
            finish();
        }
    }

    protected void onStop() {
        try {
            if (this.zzpc != null) {
                this.zzpc.onStop();
            }
        } catch (Throwable e) {
            C1129c.m6193d("Could not forward onStop to ad overlay:", e);
            finish();
        }
        super.onStop();
    }

    public void setContentView(int i) {
        super.setContentView(i);
        zzaW();
    }

    public void setContentView(View view) {
        super.setContentView(view);
        zzaW();
    }

    public void setContentView(View view, LayoutParams layoutParams) {
        super.setContentView(view, layoutParams);
        zzaW();
    }
}
