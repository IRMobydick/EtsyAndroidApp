package com.etsy.android.lib.util;

/* compiled from: ShakeDetector */
class be {
    private final bd f2023a;
    private bc f2024b;
    private bc f2025c;
    private int f2026d;
    private int f2027e;

    be() {
        this.f2023a = new bd();
    }

    void m3319a(long j, boolean z) {
        m3318a(j - 500000000);
        bc a = this.f2023a.m3315a();
        a.f2019a = j;
        a.f2020b = z;
        a.f2021c = null;
        if (this.f2025c != null) {
            this.f2025c.f2021c = a;
        }
        this.f2025c = a;
        if (this.f2024b == null) {
            this.f2024b = a;
        }
        this.f2026d++;
        if (z) {
            this.f2027e++;
        }
    }

    void m3317a() {
        while (this.f2024b != null) {
            bc bcVar = this.f2024b;
            this.f2024b = bcVar.f2021c;
            this.f2023a.m3316a(bcVar);
        }
        this.f2025c = null;
        this.f2026d = 0;
        this.f2027e = 0;
    }

    void m3318a(long j) {
        while (this.f2026d >= 4 && this.f2024b != null && j - this.f2024b.f2019a > 0) {
            bc bcVar = this.f2024b;
            if (bcVar.f2020b) {
                this.f2027e--;
            }
            this.f2026d--;
            this.f2024b = bcVar.f2021c;
            if (this.f2024b == null) {
                this.f2025c = null;
            }
            this.f2023a.m3316a(bcVar);
        }
    }

    boolean m3320b() {
        return this.f2025c != null && this.f2024b != null && this.f2025c.f2019a - this.f2024b.f2019a >= 250000000 && this.f2027e >= (this.f2026d >> 1) + (this.f2026d >> 2);
    }
}
