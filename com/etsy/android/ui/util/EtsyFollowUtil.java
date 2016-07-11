package com.etsy.android.ui.util;

import android.os.AsyncTask;
import com.etsy.android.contentproviders.EtsyDatabaseUtil;
import com.etsy.android.contentproviders.EtsyProvider;
import com.etsy.android.lib.core.EtsyResult;
import com.etsy.android.lib.core.ap;
import com.etsy.android.lib.models.User;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.uikit.util.FollowUtil;

/* renamed from: com.etsy.android.ui.util.l */
public class EtsyFollowUtil extends FollowUtil {
    final /* synthetic */ EtsyFollowUtil f3718a;

    /* renamed from: com.etsy.android.ui.util.l.1 */
    class EtsyFollowUtil extends AsyncTask<Void, Void, Void> {
        final /* synthetic */ boolean f3711a;
        final /* synthetic */ boolean f3712b;
        final /* synthetic */ EtsyFollowUtil f3713c;

        EtsyFollowUtil(EtsyFollowUtil etsyFollowUtil, boolean z, boolean z2) {
            this.f3713c = etsyFollowUtil;
            this.f3711a = z;
            this.f3712b = z2;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m5131a((Void[]) objArr);
        }

        protected Void m5131a(Void... voidArr) {
            EtsyDatabaseUtil.m752d(this.f3713c.f3718a.a, this.f3713c.c, this.f3711a);
            if (this.f3712b) {
                this.f3713c.m5138i();
            }
            return null;
        }
    }

    public EtsyFollowUtil(EtsyFollowUtil etsyFollowUtil, EtsyId etsyId, boolean z, FollowUtil followUtil) {
        this.f3718a = etsyFollowUtil;
        super(etsyFollowUtil, etsyId, z, followUtil);
    }

    protected void b_() {
        m5136a(this.d, false);
    }

    protected void m5139a(EtsyResult<User> etsyResult) {
        super.m5133a((EtsyResult) etsyResult);
        if (etsyResult == null || !etsyResult.m1049a() || etsyResult.m1054e() <= 0) {
            m5138i();
        } else {
            m5136a(this.d, true);
        }
    }

    private void m5136a(boolean z, boolean z2) {
        ap.m1142a(new EtsyFollowUtil(this, z, z2), new Void[0]);
    }

    private void m5138i() {
        this.f3718a.a.getContentResolver().notifyChange(EtsyProvider.f1095a, null);
    }
}
