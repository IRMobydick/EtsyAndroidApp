package com.etsy.android.ui.cart;

import android.support.annotation.Nullable;
import android.util.SparseArray;
import com.etsy.android.lib.models.Cart;
import com.etsy.android.lib.models.CartListing;
import com.etsy.android.lib.models.Listing;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.foresee.sdk.configuration.MeasureConfiguration;
import java.util.List;

/* renamed from: com.etsy.android.ui.cart.p */
public class CartHelper {
    private static final SparseArray<Integer[]> f2512a;

    static {
        f2512a = new SparseArray();
    }

    @Nullable
    public static Listing m3857a(EtsyId etsyId, List<Listing> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (((Listing) list.get(i)).getListingId().equals(etsyId)) {
                return (Listing) list.get(i);
            }
        }
        return null;
    }

    public static Integer[] m3859a(int i) {
        if (f2512a.get(i) != null) {
            return (Integer[]) f2512a.get(i);
        }
        Object obj = new Integer[i];
        for (int i2 = 0; i2 < obj.length; i2++) {
            obj[i2] = Integer.valueOf(i2 + 1);
        }
        f2512a.put(i, obj);
        return obj;
    }

    public static String m3858a(Cart cart, List<CartListing> list) {
        int i;
        int i2;
        if (cart.getMaxShippingTime() == -1 || cart.getMinShippingTime() == -1) {
            i = MeasureConfiguration.DISABLED;
            i2 = 0;
            for (CartListing listingId : list) {
                int processingDaysMax;
                Listing a = CartHelper.m3857a(listingId.getListingId(), cart.getListings());
                if (a.getProcessingDaysMax() > i2) {
                    processingDaysMax = a.getProcessingDaysMax();
                } else {
                    processingDaysMax = i2;
                }
                if (a.getProcessingDaysMin() < i) {
                    i2 = a.getProcessingDaysMin();
                } else {
                    i2 = i;
                }
                i = i2;
                i2 = processingDaysMax;
            }
            cart.setMinShippingTime(i);
            cart.setMaxShippingTime(i2);
        } else {
            i = cart.getMinShippingTime();
            i2 = cart.getMaxShippingTime();
        }
        if (i <= 0 && i2 <= 0) {
            return null;
        }
        if (i == i2) {
            return String.valueOf(i);
        }
        if (i == -1 || i2 == -1) {
            return String.valueOf(Math.max(i, i2));
        }
        return i + " \u2014 " + i2;
    }
}
