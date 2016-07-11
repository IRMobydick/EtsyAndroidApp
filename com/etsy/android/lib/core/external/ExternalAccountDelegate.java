package com.etsy.android.lib.core.external;

import android.app.Activity;
import android.content.Intent;
import com.etsy.android.lib.core.external.p006a.FacebookAccountHelper;
import com.etsy.android.lib.core.external.p007b.GoogleAccountHelper;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.logger.EtsyLogger;
import com.etsy.android.lib.util.ExternalAccountUtil;
import com.etsy.android.lib.util.ExternalAccountUtil.AccountType;
import com.etsy.android.lib.util.ae;
import java.util.ArrayList;

public class ExternalAccountDelegate {
    protected static final String f1470a;
    private Activity f1471b;
    private ArrayList<ExternalAccountHelper> f1472c;
    private GoogleAccountHelper f1473d;
    private FacebookAccountHelper f1474e;

    public class NotEnabledException extends RuntimeException {
        private AccountType mAccountType;

        public NotEnabledException(AccountType accountType) {
            super("Requested social service not enabled: " + accountType);
            this.mAccountType = accountType;
        }

        public AccountType getAccountType() {
            return this.mAccountType;
        }
    }

    static {
        f1470a = EtsyDebug.m1891a(ExternalAccountDelegate.class);
    }

    public ExternalAccountDelegate(Activity activity) {
        this.f1471b = activity;
        this.f1472c = new ArrayList();
        if (ExternalAccountUtil.m3103c(AccountType.GOOGLE)) {
            this.f1472c.add(m1185h());
        }
        if (ExternalAccountUtil.m3103c(AccountType.FACEBOOK)) {
            this.f1472c.add(m1186i());
        }
    }

    public void m1187a() {
        for (ExternalAccountHelper b : m1202g()) {
            b.m1224b();
        }
    }

    public void m1193b() {
        for (ExternalAccountHelper c : m1202g()) {
            c.m1226c();
        }
    }

    public void m1188a(int i, int i2, Intent intent) {
        for (ExternalAccountHelper a : m1202g()) {
            a.m1219a(i, i2, intent);
        }
    }

    public void m1189a(ExternalAccountListener externalAccountListener) {
        for (ExternalAccountHelper a : m1202g()) {
            a.m1220a(externalAccountListener);
        }
    }

    public void m1194b(ExternalAccountListener externalAccountListener) {
        for (ExternalAccountHelper b : m1202g()) {
            b.m1225b(externalAccountListener);
        }
    }

    public void m1196c() {
        for (ExternalAccountHelper g : m1202g()) {
            g.m1230g();
        }
    }

    public void m1190a(AccountType accountType) {
        for (ExternalAccountHelper externalAccountHelper : m1202g()) {
            if (externalAccountHelper.m1223a(accountType)) {
                externalAccountHelper.m1227d();
                return;
            }
        }
        throw new NotEnabledException(accountType);
    }

    public void m1199d() {
        m1195b(null);
    }

    public void m1195b(AccountType accountType) {
        for (ExternalAccountHelper externalAccountHelper : m1202g()) {
            if (accountType == null || externalAccountHelper.m1223a(accountType)) {
                externalAccountHelper.m1228e();
            }
        }
    }

    public void m1200e() {
        m1197c(null);
    }

    public void m1197c(AccountType accountType) {
        Object obj = null;
        for (ExternalAccountHelper externalAccountHelper : m1202g()) {
            Object obj2;
            if (accountType == null || externalAccountHelper.m1223a(accountType)) {
                externalAccountHelper.m1229f();
                obj2 = 1;
            } else {
                obj2 = obj;
            }
            obj = obj2;
        }
        if (obj == null) {
            throw new NotEnabledException(accountType);
        }
    }

    public ExternalAccountProfile m1201f() {
        ExternalAccountProfile externalAccountProfile = null;
        for (ExternalAccountHelper a : m1202g()) {
            ExternalAccountProfile a2;
            try {
                a2 = a.m1218a();
                if (a2 != null) {
                    return a2;
                }
                externalAccountProfile = a2;
            } catch (Exception e) {
                EtsyLogger.m1966a().m1986a(f1470a, e.getMessage());
                a2 = externalAccountProfile;
            }
        }
        return externalAccountProfile;
    }

    public void m1191a(AccountType accountType, ae aeVar, boolean z) {
        m1198d(accountType).m1221a(aeVar, z);
    }

    public void m1192a(AccountType accountType, String str, ae aeVar) {
        m1198d(accountType).m1222a(str, aeVar);
    }

    public Iterable<ExternalAccountHelper> m1202g() {
        return this.f1472c;
    }

    public ExternalAccountHelper m1198d(AccountType accountType) {
        NotEnabledException notEnabledException = new NotEnabledException(accountType);
        if (!ExternalAccountUtil.m3103c(accountType)) {
            throw notEnabledException;
        } else if (accountType == AccountType.GOOGLE) {
            return m1185h();
        } else {
            if (accountType == AccountType.FACEBOOK) {
                return m1186i();
            }
            throw notEnabledException;
        }
    }

    private GoogleAccountHelper m1185h() {
        if (this.f1473d == null) {
            this.f1473d = new GoogleAccountHelper(this.f1471b);
        }
        return this.f1473d;
    }

    private FacebookAccountHelper m1186i() {
        if (this.f1474e == null) {
            this.f1474e = new FacebookAccountHelper(this.f1471b);
        }
        return this.f1474e;
    }
}
