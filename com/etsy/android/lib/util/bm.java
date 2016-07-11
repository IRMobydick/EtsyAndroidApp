package com.etsy.android.lib.util;

import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.Cart;
import com.etsy.android.lib.models.CartListing;
import com.etsy.android.lib.models.Option;
import com.etsy.android.lib.models.Variation;
import com.etsy.android.lib.models.datatypes.EtsyId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

/* compiled from: VariationUtil */
public class bm {
    private static final String f2032a;
    private static ConcurrentHashMap<Long, Map<Long, Variation>> f2033b;

    static {
        f2032a = EtsyDebug.m1891a(bm.class);
        f2033b = new ConcurrentHashMap();
    }

    public static Map<Long, Variation> m3373a(EtsyId etsyId) {
        return (Map) f2033b.get(Long.valueOf(etsyId.getIdAsLong()));
    }

    public static void m3375a(EtsyId etsyId, List<Variation> list) {
        if (list != null) {
            HashMap hashMap = new HashMap();
            for (Variation variation : list) {
                hashMap.put(Long.valueOf(variation.getPropertyId()), variation);
            }
            f2033b.put(Long.valueOf(etsyId.getIdAsLong()), hashMap);
        }
    }

    public static void m3374a() {
        f2033b.clear();
    }

    public static Variation m3370a(EtsyId etsyId, long j) {
        Map a = m3373a(etsyId);
        if (a != null) {
            return (Variation) a.get(Long.valueOf(j));
        }
        return null;
    }

    public static Variation m3371a(List<Variation> list, long j) {
        if (list != null) {
            for (Variation variation : list) {
                if (variation.getPropertyId() == j) {
                    return variation;
                }
            }
        }
        return null;
    }

    public static String m3372a(List<Variation> list) {
        JSONObject jSONObject = new JSONObject();
        for (Variation variation : list) {
            if (variation.hasOptionSet()) {
                try {
                    jSONObject.put(variation.getPropertyId() + StringUtils.EMPTY, variation.getValueId());
                } catch (Throwable e) {
                    EtsyDebug.m1917d(f2032a, "error parsing variation to JSON", e);
                }
            }
        }
        return jSONObject.toString();
    }

    public static boolean m3377a(Variation variation, Option option) {
        if (variation == null || option == null || variation.getValueId() != option.getValueId()) {
            return false;
        }
        return true;
    }

    public static long m3369a(Cart cart, CartListing cartListing, Variation variation, Option option) {
        if (!(variation == null || option == null)) {
            if (cartListing.getVariation(variation.getPropertyId()) == null) {
                return -404;
            }
            if (m3377a(cartListing.getVariation(variation.getPropertyId()), option)) {
                return -100;
            }
            long b = m3380b(cart, cartListing, variation, option);
            if (b > 0) {
                return b;
            }
            if (m3379a(cartListing.getSelectedVariations(), variation.getPropertyId(), option)) {
                return -200;
            }
        }
        return 0;
    }

    public static boolean m3379a(Map<Long, Variation> map, long j, Option option) {
        if (!(map == null || j <= 0 || option == null)) {
            Variation variation = (Variation) map.get(Long.valueOf(j));
            if (variation != null) {
                variation.setOption(option);
                return true;
            }
        }
        return false;
    }

    public static boolean m3378a(Map<Long, Variation> map, long j, long j2, String str) {
        if (map != null) {
            Variation variation = (Variation) map.get(Long.valueOf(j));
            if (variation != null) {
                variation.setOption(str, j2);
                return true;
            }
        }
        return false;
    }

    private static long m3380b(Cart cart, CartListing cartListing, Variation variation, Option option) {
        List<CartListing> cartListings = cart.getCartListings();
        if (cartListings != null) {
            for (CartListing cartListing2 : cartListings) {
                if (m3376a(cartListing, cartListing2)) {
                    Map selectedVariations = cartListing2.getSelectedVariations();
                    Map selectedVariations2 = cartListing.getSelectedVariations();
                    if (!(selectedVariations2 == null || selectedVariations == null)) {
                        Variation variation2 = (Variation) selectedVariations.get(Long.valueOf(variation.getPropertyId()));
                        if (variation2 != null && variation2.getValueId() == option.getValueId()) {
                            Object obj;
                            for (Variation variation22 : selectedVariations2.values()) {
                                if (variation22.getPropertyId() != variation.getPropertyId()) {
                                    Variation variation3 = (Variation) selectedVariations.get(Long.valueOf(variation22.getPropertyId()));
                                    if (!(variation3 == null || variation3.getValueId() == variation22.getValueId())) {
                                        obj = null;
                                        break;
                                    }
                                }
                            }
                            int i = 1;
                            if (obj != null) {
                                return cartListing2.getListingCustomizationId();
                            }
                        }
                    }
                }
            }
        }
        return 0;
    }

    private static boolean m3376a(CartListing cartListing, CartListing cartListing2) {
        return cartListing.getListingId() == cartListing2.getListingId() && cartListing.getListingCustomizationId() != cartListing2.getListingCustomizationId();
    }
}
