package com.etsy.android.ui.cardview.p014a;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import com.etsy.android.lib.logger.AnalyticsTracker;
import com.etsy.android.lib.logger.EtsyLogger;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.TaxonomyNode;
import com.etsy.android.lib.models.apiv3.TaxonomyCategory;
import com.etsy.android.ui.nav.Nav;
import com.etsy.android.uikit.viewholder.a.b;
import java.util.HashMap;

/* renamed from: com.etsy.android.ui.cardview.a.a */
public class CategoryCardClickHandler extends b<TaxonomyCategory> {
    public CategoryCardClickHandler(String str, FragmentActivity fragmentActivity, @NonNull AnalyticsTracker analyticsTracker) {
        super(str, fragmentActivity, analyticsTracker);
    }

    public void m3568a(TaxonomyCategory taxonomyCategory) {
        m3567b(taxonomyCategory);
    }

    private void m3567b(TaxonomyCategory taxonomyCategory) {
        TaxonomyNode buildTaxonomyNode = taxonomyCategory.buildTaxonomyNode();
        if (buildTaxonomyNode != null) {
            if (this.b != null) {
                HashMap hashMap = new HashMap();
                hashMap.put(ResponseConstants.TAXONOMY_ID, buildTaxonomyNode.getTaxonomyNodeId());
                EtsyLogger.m1966a().m1997b("homescreen_tapped_category", this.b, hashMap);
            }
            Nav.m4682a(this.c).m4683a().m4462a(buildTaxonomyNode, null);
        }
    }
}
