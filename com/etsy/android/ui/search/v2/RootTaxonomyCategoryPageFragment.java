package com.etsy.android.ui.search.v2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.etsy.android.lib.models.TaxonomyNode;
import com.etsy.android.uikit.ui.core.TrackingBaseFragment;

public class RootTaxonomyCategoryPageFragment extends TrackingBaseFragment implements am {
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View searchTaxonomyListLayout = new SearchTaxonomyListLayout(getActivity());
        searchTaxonomyListLayout.setListener(this);
        return searchTaxonomyListLayout;
    }

    public void onTaxonomySelected(TaxonomyNode taxonomyNode) {
        SearchV2Activity.addCategoryPageFragment(getActivity(), taxonomyNode, null);
    }
}
