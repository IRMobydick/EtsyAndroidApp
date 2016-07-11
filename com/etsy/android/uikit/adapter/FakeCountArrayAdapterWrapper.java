package com.etsy.android.uikit.adapter;

import com.etsy.android.lib.models.BaseModel;
import java.util.Collection;

/* renamed from: com.etsy.android.uikit.adapter.j */
public class FakeCountArrayAdapterWrapper<T extends BaseModel, A extends BaseModelArrayAdapter<T>> {
    private A f3935a;
    private int f3936b;

    public FakeCountArrayAdapterWrapper(A a) {
        this.f3935a = a;
    }

    public A m5310a() {
        return this.f3935a;
    }

    public void m5313a(A a) {
        this.f3935a = a;
    }

    public void m5312a(T t) {
        this.f3935a.remove(t);
        this.f3936b--;
    }

    public void m5314b() {
        this.f3935a.clear();
        this.f3936b = 0;
    }

    public void m5315b(T t) {
        int count = this.f3936b - this.f3935a.getCount();
        this.f3935a.add(t);
        if (this.f3936b - this.f3935a.getCount() > count) {
            this.f3936b++;
        }
    }

    public void m5311a(int i, Collection<? extends T> collection) {
        int i2 = this.f3936b + i;
        this.f3935a.addAll((Collection) collection);
        this.f3936b = i2;
    }

    public int m5316c() {
        return this.f3936b;
    }

    public BaseModel m5309a(int i) {
        return this.f3935a.getItem(i);
    }

    public void m5318d() {
        this.f3935a.notifyDataSetChanged();
    }

    public int m5319e() {
        return this.f3935a.getCount();
    }

    public int m5317c(T t) {
        return this.f3935a.getPosition(t);
    }

    public boolean m5320f() {
        return this.f3935a.isEmpty();
    }
}
