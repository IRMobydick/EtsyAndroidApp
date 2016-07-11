package com.google.android.gms.common.data;

import android.os.Bundle;
import java.util.Iterator;

public abstract class AbstractDataBuffer<T> implements DataBuffer<T> {
    protected final DataHolder zzamz;

    protected AbstractDataBuffer(DataHolder dataHolder) {
        this.zzamz = dataHolder;
        if (this.zzamz != null) {
            this.zzamz.zzu(this);
        }
    }

    @Deprecated
    public final void close() {
        release();
    }

    public abstract T get(int i);

    public int getCount() {
        return this.zzamz == null ? 0 : this.zzamz.getCount();
    }

    @Deprecated
    public boolean isClosed() {
        return this.zzamz == null || this.zzamz.isClosed();
    }

    public Iterator<T> iterator() {
        return new zzb(this);
    }

    public void release() {
        if (this.zzamz != null) {
            this.zzamz.close();
        }
    }

    public Iterator<T> singleRefIterator() {
        return new zzg(this);
    }

    public Bundle zzsO() {
        return this.zzamz.zzsO();
    }
}
