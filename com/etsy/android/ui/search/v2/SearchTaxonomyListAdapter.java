package com.etsy.android.ui.search.v2;

import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.ViewGroup;
import com.etsy.android.lib.R;
import com.etsy.android.lib.config.EtsyFeatureFlags;
import com.etsy.android.lib.models.TaxonomyNode;
import com.etsy.android.uikit.adapter.BaseRecyclerViewAdapter;
import com.etsy.android.uikit.cardview.ICardViewElement;
import java.util.List;

public class SearchTaxonomyListAdapter extends BaseRecyclerViewAdapter<ICardViewElement> {
    private static final int VIEW_TYPE_MORE_NAV_FOE = 4;
    private static final int VIEW_TYPE_NAV_GIFT_CARD_CREATE = 3;
    private static final int VIEW_TYPE_TAXONOMY = 1;
    private static final int VIEW_TYPE_TAXONOMY_HEADER = 2;
    private final am mListener;

    public SearchTaxonomyListAdapter(Context context, List<TaxonomyNode> list, am amVar) {
        super(context, null);
        this.mListener = amVar;
        addItem(new ak(this, R.all_categories));
        for (TaxonomyNode addItem : list) {
            addItem(addItem);
        }
        if (EtsyFeatureFlags.m915b()) {
            addItem(new ak(this, R.new_search_more_ways_to_shop));
            addItem(new ah(this));
        }
    }

    protected int getListItemViewType(int i) {
        ICardViewElement iCardViewElement = (ICardViewElement) getItem(i);
        if (iCardViewElement.getClass().equals(TaxonomyNode.class)) {
            return VIEW_TYPE_TAXONOMY;
        }
        if (iCardViewElement.getClass().equals(ak.class)) {
            return VIEW_TYPE_TAXONOMY_HEADER;
        }
        if (iCardViewElement.getClass().equals(aj.class)) {
            return VIEW_TYPE_MORE_NAV_FOE;
        }
        return iCardViewElement.getClass().equals(ah.class) ? VIEW_TYPE_NAV_GIFT_CARD_CREATE : 0;
    }

    public ViewHolder onCreateListItemViewHolder(ViewGroup viewGroup, int i) {
        switch (i) {
            case VIEW_TYPE_TAXONOMY /*1*/:
                return new al(this, this.mInflater.inflate(2130903269, viewGroup, false));
            case VIEW_TYPE_TAXONOMY_HEADER /*2*/:
                return new ag(this.mInflater, viewGroup);
            case VIEW_TYPE_NAV_GIFT_CARD_CREATE /*3*/:
            case VIEW_TYPE_MORE_NAV_FOE /*4*/:
                return new ai(this, viewGroup);
            default:
                return null;
        }
    }

    public ViewHolder onCreateHeaderViewHolder(ViewGroup viewGroup, int i) {
        return null;
    }

    public ViewHolder onCreateFooterViewHolder(ViewGroup viewGroup, int i) {
        return null;
    }

    protected void onBindListItemViewHolder(ViewHolder viewHolder, int i) {
        switch (getListItemViewType(i)) {
            case VIEW_TYPE_TAXONOMY /*1*/:
                ((al) viewHolder).m4886a((TaxonomyNode) getItem(i));
            case VIEW_TYPE_TAXONOMY_HEADER /*2*/:
                ((ag) viewHolder).m4884a((ak) getItem(i));
            case VIEW_TYPE_NAV_GIFT_CARD_CREATE /*3*/:
            case VIEW_TYPE_MORE_NAV_FOE /*4*/:
                ((ai) viewHolder).m4885a();
            default:
        }
    }
}
