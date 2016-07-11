package com.etsy.android.lib.models.apiv3;

public class ListingState {
    public static final int ACTIVE = 0;
    public static final int DRAFT = 3;
    public static final int EXPIRED = 5;
    public static final int INACTIVE = 1;
    public static final int REMOVED = 4;
    public static final int SOLDOUT = 2;

    public static boolean isActive(int i) {
        return i == 0;
    }
}
