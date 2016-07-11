package com.etsy.android.lib.models.cardviewelement;

import com.etsy.android.lib.models.datatypes.EtsyId;

public class SearchPageLink$$PackageHelper {
    public static EtsyId accessSearchPageLink$FG$mTaxonomyId(SearchPageLink searchPageLink) {
        return searchPageLink.mTaxonomyId;
    }

    public static boolean accessSearchPageLink$FG$mIsCategoryPage(SearchPageLink searchPageLink) {
        return searchPageLink.mIsCategoryPage;
    }

    public static void accessSearchPageLink$FS$mTaxonomyId(SearchPageLink searchPageLink, EtsyId etsyId) {
        searchPageLink.mTaxonomyId = etsyId;
    }

    public static void accessSearchPageLink$FS$mIsCategoryPage(SearchPageLink searchPageLink, boolean z) {
        searchPageLink.mIsCategoryPage = z;
    }
}
