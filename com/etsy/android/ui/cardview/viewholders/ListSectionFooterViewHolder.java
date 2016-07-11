package com.etsy.android.ui.cardview.viewholders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.models.cardviewelement.PageLink;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.viewholder.BaseViewHolder;
import com.etsy.android.uikit.viewholder.a.b;

public class ListSectionFooterViewHolder extends BaseViewHolder<PageLink> {
    b mCardClickHandler;
    TextView mTextLink;

    /* renamed from: com.etsy.android.ui.cardview.viewholders.ListSectionFooterViewHolder.1 */
    class C05521 extends TrackingOnClickListener {
        final /* synthetic */ PageLink f2282a;
        final /* synthetic */ ListSectionFooterViewHolder f2283b;

        C05521(ListSectionFooterViewHolder listSectionFooterViewHolder, PageLink pageLink) {
            this.f2283b = listSectionFooterViewHolder;
            this.f2282a = pageLink;
        }

        public void onViewClick(View view) {
            if (this.f2283b.mCardClickHandler != null) {
                this.f2283b.mCardClickHandler.a(this.f2282a);
            }
        }
    }

    public ListSectionFooterViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, b bVar, boolean z) {
        super(layoutInflater.inflate(getLayoutForOrientation(z), viewGroup, false));
        if (z) {
            getRootView().getLayoutParams().width = this.itemView.getResources().getDimensionPixelSize(R.horizontal_listing_card_section_item_width);
            getRootView().getLayoutParams().height = this.itemView.getResources().getDimensionPixelSize(2131362274);
        }
        this.mTextLink = (TextView) findViewById(2131755943);
        this.mCardClickHandler = bVar;
    }

    private static int getLayoutForOrientation(boolean z) {
        return z ? 2130903295 : 2130903302;
    }

    public void bind(PageLink pageLink) {
        this.mTextLink.setText(pageLink.getLinkTitle());
        this.itemView.setOnClickListener(new C05521(this, pageLink));
    }
}
