package com.etsy.android.ui.finds;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.img.ImageBatch;
import com.etsy.android.lib.logger.AnalyticsTracker;
import com.etsy.android.lib.models.BannerImage;
import com.etsy.android.lib.models.apiv3.ListingCard;
import com.etsy.android.lib.models.cardviewelement.BasicSectionHeader;
import com.etsy.android.lib.models.cardviewelement.FindsHeroBanner;
import com.etsy.android.lib.models.cardviewelement.ListSection;
import com.etsy.android.lib.models.finds.FindsModule;
import com.etsy.android.lib.models.finds.FindsPage;
import com.etsy.android.ui.cardview.BaseViewHolderFactory;
import com.etsy.android.ui.cardview.CardViewFactoryRecyclerViewAdapter;
import com.etsy.android.uikit.cardview.ICardViewElement;
import java.util.ArrayList;
import java.util.List;
import org.parceler.Parcels;

public class FindsRecyclerViewAdapter extends CardViewFactoryRecyclerViewAdapter {
    private static final String SAVE_FINDS_PAGE = "SAVE_FINDS_PAGE";
    private static final String SAVE_SIBLING_COUNTS = "SAVE_SIBLING_COUNTS";
    protected FindsPage mFindsPage;
    protected ArrayList<Integer> mSiblingCounts;

    public FindsRecyclerViewAdapter(Context context, ImageBatch imageBatch, String str, @NonNull AnalyticsTracker analyticsTracker) {
        super(context, imageBatch, str, analyticsTracker);
    }

    protected BaseViewHolderFactory createViewHolderFactory() {
        return new FindsViewHolderFactory(this.mContext, this.mInflater, this.mImageBatch, this, this.mPageInView, this.mViewTracker);
    }

    public void setFindsPage(FindsPage findsPage) {
        int i = 0;
        this.mSiblingCounts = new ArrayList();
        this.mFindsPage = findsPage;
        int i2 = 1;
        BannerImage bannerImage = this.mFindsPage.getBannerImage();
        if (bannerImage != null) {
            FindsHeroBanner findsHeroBanner = new FindsHeroBanner();
            findsHeroBanner.setBannerImage(bannerImage);
            findsHeroBanner.setTitle(this.mFindsPage.getTitle());
            findsHeroBanner.setSubtitle(this.mFindsPage.getSubtitle());
            findsHeroBanner.setViewType(32);
            addItem(findsHeroBanner);
            this.mSiblingCounts.add(Integer.valueOf(0));
            i2 = 0;
        }
        List heroListings = this.mFindsPage.getHeroListings();
        if (!(heroListings == null || heroListings.isEmpty())) {
            ListSection listSection = new ListSection();
            int size = heroListings.size();
            listSection.setItems(heroListings);
            listSection.setHorizontal(false);
            if (i2 != 0) {
                listSection.setHeader(new BasicSectionHeader(this.mFindsPage.getTitle(), this.mFindsPage.getSubtitle()));
                this.mSiblingCounts.add(Integer.valueOf(0));
            }
            addListSection(listSection);
            while (i < heroListings.size()) {
                ((ListingCard) heroListings.get(i)).setViewType(31);
                this.mSiblingCounts.add(Integer.valueOf(size));
                i++;
            }
        }
        for (FindsModule cardViewElements : findsPage.getModules()) {
            List<ICardViewElement> cardViewElements2 = cardViewElements.getCardViewElements(this.mContext.getResources().getBoolean(R.is_phone));
            int size2 = cardViewElements2.size();
            for (ICardViewElement iCardViewElement : cardViewElements2) {
                this.mSiblingCounts.add(Integer.valueOf(size2));
                addItem(iCardViewElement);
            }
        }
        notifyDataSetChanged();
    }

    public int getSiblingCountForPosition(int i) {
        if (i < this.mSiblingCounts.size()) {
            return ((Integer) this.mSiblingCounts.get(i)).intValue();
        }
        return 0;
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putIntegerArrayList(SAVE_SIBLING_COUNTS, this.mSiblingCounts);
        bundle.putParcelable(SAVE_FINDS_PAGE, Parcels.m7494a(this.mFindsPage));
        super.onSaveInstanceState(bundle);
    }

    public boolean onRestoreInstanceState(Bundle bundle) {
        if (bundle != null) {
            this.mSiblingCounts = bundle.getIntegerArrayList(SAVE_SIBLING_COUNTS);
            this.mFindsPage = (FindsPage) Parcels.m7495a(bundle.getParcelable(SAVE_FINDS_PAGE));
        }
        return super.onRestoreInstanceState(bundle);
    }
}
