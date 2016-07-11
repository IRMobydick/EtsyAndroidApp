package com.etsy.android.ui.cardview.p014a;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import com.etsy.android.lib.logger.AnalyticsTracker;
import com.etsy.android.lib.models.homescreen.CategoryRecommendationCard;
import com.etsy.android.ui.nav.Nav;
import com.etsy.android.uikit.viewholder.a.b;

/* renamed from: com.etsy.android.ui.cardview.a.b */
public class CategoryRecCardClickHandler extends b<CategoryRecommendationCard> {
    public CategoryRecCardClickHandler(String str, FragmentActivity fragmentActivity, @NonNull AnalyticsTracker analyticsTracker) {
        super(str, fragmentActivity, analyticsTracker);
    }

    public void m3570a(CategoryRecommendationCard categoryRecommendationCard) {
        m3572b(categoryRecommendationCard);
    }

    public void m3572b(CategoryRecommendationCard categoryRecommendationCard) {
        if (categoryRecommendationCard.getListingLink() != null) {
            Nav.m4682a(this.c).m4683a().m4478a(categoryRecommendationCard.getListingLink());
        }
    }
}
