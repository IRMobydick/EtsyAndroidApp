package com.google.android.gms.auth.api.signin.internal;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.SignInAccount;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.api.Status;

@KeepName
public class SignInHubActivity extends FragmentActivity {
    private C1147h zzacV;
    private SignInConfiguration zzacW;
    private boolean zzacX;
    private int zzacY;
    private Intent zzacZ;

    private void zza(int i, Intent intent) {
        if (intent != null) {
            SignInAccount signInAccount = (SignInAccount) intent.getParcelableExtra("signInAccount");
            if (signInAccount != null && signInAccount.zzpp() != null) {
                GoogleSignInAccount zzpp = signInAccount.zzpp();
                this.zzacV.m6275b(zzpp, this.zzacW.zzpA());
                intent.removeExtra("signInAccount");
                intent.putExtra("googleSignInAccount", zzpp);
                this.zzacX = true;
                this.zzacY = i;
                this.zzacZ = intent;
                zzpB();
                return;
            } else if (intent.hasExtra("errorCode")) {
                zzbc(intent.getIntExtra("errorCode", 8));
                return;
            }
        }
        zzbc(8);
    }

    private void zzbc(int i) {
        Parcelable status = new Status(i);
        Intent intent = new Intent();
        intent.putExtra("googleSignInStatus", status);
        setResult(0, intent);
        finish();
    }

    private void zzj(Intent intent) {
        intent.setPackage(GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_PACKAGE);
        intent.putExtra("config", this.zzacW);
        try {
            startActivityForResult(intent, 40962);
        } catch (ActivityNotFoundException e) {
            Log.w("AuthSignInClient", "Could not launch sign in Intent. Google Play Service is probably being updated...");
            zzbc(8);
        }
    }

    private void zzpB() {
        getSupportLoaderManager().initLoader(0, null, new C1146a());
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return true;
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        setResult(0);
        switch (i) {
            case 40962:
                zza(i2, intent);
            default:
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.zzacV = C1147h.m6267a((Context) this);
        Intent intent = getIntent();
        if (!"com.google.android.gms.auth.GOOGLE_SIGN_IN".equals(intent.getAction())) {
            String str = "AuthSignInClient";
            String str2 = "Unknown action: ";
            String valueOf = String.valueOf(intent.getAction());
            Log.e(str, valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
            finish();
        }
        this.zzacW = (SignInConfiguration) intent.getParcelableExtra("config");
        if (this.zzacW == null) {
            Log.e("AuthSignInClient", "Activity started with invalid configuration.");
            setResult(0);
            finish();
        } else if (bundle == null) {
            zzj(new Intent("com.google.android.gms.auth.GOOGLE_SIGN_IN"));
        } else {
            this.zzacX = bundle.getBoolean("signingInGoogleApiClients");
            if (this.zzacX) {
                this.zzacY = bundle.getInt("signInResultCode");
                this.zzacZ = (Intent) bundle.getParcelable("signInResultData");
                zzpB();
            }
        }
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("signingInGoogleApiClients", this.zzacX);
        if (this.zzacX) {
            bundle.putInt("signInResultCode", this.zzacY);
            bundle.putParcelable("signInResultData", this.zzacZ);
        }
    }
}
