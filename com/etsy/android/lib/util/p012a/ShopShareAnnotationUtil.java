package com.etsy.android.lib.util.p012a;

import android.content.Context;
import android.widget.ImageView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.models.shopshare.ShareAnnotation.Coordinates;

/* renamed from: com.etsy.android.lib.util.a.a */
public class ShopShareAnnotationUtil {
    public static Coordinates m3162a(Coordinates coordinates, int i, int i2, ImageView imageView) {
        int i3 = 0;
        int width = i != 0 ? (coordinates.f1904x * imageView.getWidth()) / i : 0;
        if (i2 != 0) {
            i3 = (coordinates.f1905y * imageView.getHeight()) / i2;
        }
        return new Coordinates(width, i3);
    }

    public static int m3161a(Context context) {
        return ((int) context.getResources().getDimension(R.shop_share_tag_width)) / -2;
    }

    public static int m3163b(Context context) {
        return 0;
    }
}
