package com.etsy.android.lib.models.cardviewelement;

import android.os.Parcelable;
import java.util.List;

public class ListSection$$PackageHelper {
    public static boolean accessListSection$FG$mIsHorizontal(ListSection listSection) {
        return listSection.mIsHorizontal;
    }

    public static BasicSectionHeader accessListSection$FG$mHeader(ListSection listSection) {
        return listSection.mHeader;
    }

    public static PageLink accessListSection$FG$mPageLink(ListSection listSection) {
        return listSection.mPageLink;
    }

    public static Parcelable accessListSection$FG$mLayoutState(ListSection listSection) {
        return listSection.mLayoutState;
    }

    public static List accessListSection$FG$mItems(ListSection listSection) {
        return listSection.mItems;
    }

    public static void accessListSection$FS$mIsHorizontal(ListSection listSection, boolean z) {
        listSection.mIsHorizontal = z;
    }

    public static void accessListSection$FS$mHeader(ListSection listSection, BasicSectionHeader basicSectionHeader) {
        listSection.mHeader = basicSectionHeader;
    }

    public static void accessListSection$FS$mPageLink(ListSection listSection, PageLink pageLink) {
        listSection.mPageLink = pageLink;
    }

    public static void accessListSection$FS$mLayoutState(ListSection listSection, Parcelable parcelable) {
        listSection.mLayoutState = parcelable;
    }

    public static void accessListSection$FS$mItems(ListSection listSection, List list) {
        listSection.mItems = list;
    }
}
