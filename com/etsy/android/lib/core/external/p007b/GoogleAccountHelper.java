package com.etsy.android.lib.core.external.p007b;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.RequiresPermission;
import android.widget.Toast;
import com.etsy.android.lib.R;
import com.etsy.android.lib.util.ExternalAccountUtil;
import com.google.android.gms.auth.C1144a;
import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.GooglePlayServicesAvailabilityException;
import com.google.android.gms.auth.UserRecoverableAuthException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.plus.C1164c;
import java.io.IOException;

/* renamed from: com.etsy.android.lib.core.external.b.b */
class GoogleAccountHelper extends AsyncTask<Void, Void, String> {
    final /* synthetic */ GoogleAccountHelper f1511a;
    private final boolean f1512b;
    private String f1513c;
    private boolean f1514d;
    private boolean f1515e;

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m1292a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m1293a((String) obj);
    }

    @RequiresPermission("android.permission.GET_ACCOUNTS")
    public GoogleAccountHelper(GoogleAccountHelper googleAccountHelper, boolean z) {
        this.f1511a = googleAccountHelper;
        this.f1512b = z;
        this.f1513c = C1164c.f5558g.m7456c(googleAccountHelper.f1508n);
    }

    protected String m1292a(Void... voidArr) {
        if (isCancelled()) {
            return null;
        }
        return m1291b(this.f1513c);
    }

    private String m1291b(String str) {
        String str2 = null;
        try {
            Bundle bundle = new Bundle();
            bundle.putBoolean("suppressProgressScreen", true);
            str2 = C1144a.m6265a(this.f1511a.a, str, this.f1511a.m1280a(this.f1512b), bundle);
        } catch (GooglePlayServicesAvailabilityException e) {
            if (!isCancelled()) {
                GooglePlayServicesUtil.getErrorDialog(e.getConnectionStatusCode(), this.f1511a.a, 1).show();
            }
        } catch (UserRecoverableAuthException e2) {
            if (!isCancelled()) {
                this.f1511a.a.startActivityForResult(e2.getIntent(), 1);
            }
        } catch (IOException e3) {
            this.f1514d = true;
        } catch (GoogleAuthException e4) {
            ExternalAccountUtil.m3100b();
            this.f1515e = true;
        }
        return str2;
    }

    protected void m1293a(String str) {
        super.onPostExecute(str);
        if (this.f1514d || this.f1515e) {
            this.f1511a.m1278k();
            Toast.makeText(this.f1511a.a, this.f1511a.a.getString(this.f1514d ? R.network_unavailable : R.account_error), 0).show();
        } else if (str != null) {
            this.f1511a.m1265a(str);
        }
    }
}
