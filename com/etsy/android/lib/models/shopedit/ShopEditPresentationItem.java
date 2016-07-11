package com.etsy.android.lib.models.shopedit;

import android.content.Context;
import android.support.annotation.NonNull;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public interface ShopEditPresentationItem {
    public static final int TYPE_BRANDING_IMAGE = 1;
    public static final int TYPE_FAQ_ROW = 7;
    public static final int TYPE_ICON_AND_TEXT_ROW = 6;
    public static final int TYPE_IMAGE_AND_DESCRIPTION_ROW = 5;
    public static final int TYPE_SECTION_BOTTOM_SPACER = 9;
    public static final int TYPE_SECTION_HEADING_ROW = 2;
    public static final int TYPE_STRUCTURED_POLICIES_PROMO_ROW = 8;
    public static final int TYPE_SWITCH_ROW = 4;
    public static final int TYPE_TEXT_AND_DESCRIPTION_ROW = 3;
    public static final int TYPE_VIDEO_BANNER = 10;

    @Retention(RetentionPolicy.SOURCE)
    public @interface ShopEditViewType {
    }

    int getViewType();

    void restoreComplexStateIfNecessary(@NonNull Context context);
}
