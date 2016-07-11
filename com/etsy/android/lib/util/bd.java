package com.etsy.android.lib.util;

/* compiled from: ShakeDetector */
class bd {
    private bc f2022a;

    bd() {
    }

    bc m3315a() {
        bc bcVar = this.f2022a;
        if (bcVar == null) {
            return new bc();
        }
        this.f2022a = bcVar.f2021c;
        return bcVar;
    }

    void m3316a(bc bcVar) {
        bcVar.f2021c = this.f2022a;
        this.f2022a = bcVar;
    }
}
