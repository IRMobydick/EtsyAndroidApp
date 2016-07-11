package com.etsy.android.ui.util;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.EtsyRequestJob;
import com.etsy.android.lib.core.EtsyResult;
import com.etsy.android.lib.models.EtsyError;
import com.etsy.android.lib.models.apiv3.Collection;
import com.etsy.android.lib.requests.CollectionRequest;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.util.bh;
import com.etsy.android.lib.util.bl;
import com.etsy.android.ui.nav.EtsyEventTracker;
import com.etsy.android.ui.user.auth.RegisterFragment;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.List;

/* renamed from: com.etsy.android.ui.util.d */
class CollectionUtil extends EtsyRequestJob<Collection> {
    String f3673a;
    String f3674c;
    boolean f3675d;
    CollectionUtil f3676e;
    ProgressDialog f3677f;
    Reference<Activity> f3678g;
    final /* synthetic */ CollectionUtil f3679h;

    public CollectionUtil(CollectionUtil collectionUtil, Activity activity, String str, String str2, boolean z, CollectionUtil collectionUtil2) {
        this.f3679h = collectionUtil;
        this.f3674c = str;
        this.f3673a = str2;
        this.f3675d = z;
        this.f3676e = collectionUtil2;
        this.f3678g = new WeakReference(activity);
    }

    protected void b_() {
        this.f3677f = bl.m3364b((Context) this.f3678g.get(), this.f3679h.f3646a.getString(R.updating));
        this.f3677f.show();
    }

    protected EtsyRequest<Collection> m5100a() {
        EtsyRequest editCollection = CollectionRequest.editCollection(this.f3674c, this.f3673a, this.f3675d);
        EtsyEventTracker.m4557a(this.f3674c, this.f3673a, this.f3675d ? RegisterFragment.GENDER_NAME_PRIVATE : Collection.PRIVACY_LEVEL_PUBLIC, this.f3679h.f3648c);
        return editCollection;
    }

    protected void m5101a(EtsyResult etsyResult) {
        if (this.f3677f != null) {
            this.f3677f.dismiss();
        }
        if (etsyResult.m1049a() && etsyResult.m1056g() != null && this.f3676e != null) {
            this.f3676e.onCollectionEdited((Collection) etsyResult.m1056g().get(0));
        } else if (this.f3676e != null) {
            String c = etsyResult.m1052c();
            if (bh.m3340a(c)) {
                etsyResult.m1063n();
                List d = etsyResult.m1053d();
                if (!(d == null || d.isEmpty())) {
                    c = ((EtsyError) d.get(0)).getName();
                }
            }
            this.f3676e.onCollectionError(c);
        }
    }
}
