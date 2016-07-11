package org.parceler;

import android.os.Parcelable;

public final class Parcels {
    private static final ix f5585a;
    private static final NullParcelable f5586b;

    static {
        f5585a = new ix();
        f5586b = new NullParcelable(null);
        f5585a.a(NonParcelRepository.a());
    }

    public static <T> Parcelable m7494a(T t) {
        if (t == null) {
            return f5586b;
        }
        return m7493a(t.getClass(), t);
    }

    public static <T> Parcelable m7493a(Class<? extends T> cls, T t) {
        if (t == null) {
            return f5586b;
        }
        return f5585a.a(cls).a(t);
    }

    public static <T> T m7495a(Parcelable parcelable) {
        if (parcelable == null) {
            return null;
        }
        return ((ax) parcelable).getParcel();
    }
}
