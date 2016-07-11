package com.etsy.android.ui.local.marketdetails;

import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.models.ListingImage;
import com.etsy.android.lib.models.LocalMarket;
import com.etsy.android.lib.models.LocalStoreImage;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.util.p013b.LocalMarketFormatter;
import com.etsy.android.ui.local.LocalMarketCardUtil;
import com.etsy.android.uikit.share.ShareBrokerFragment;
import com.etsy.android.uikit.share.SocialShareBrokerFragment;
import java.util.ArrayList;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcels;

public class SocialShareLocalMarketBrokerFragment extends SocialShareBrokerFragment {
    private LocalMarket mLocalMarket;

    protected void readArguments(@NonNull Bundle bundle) {
        super.readArguments(bundle);
        this.mLocalMarket = (LocalMarket) Parcels.m7495a(bundle.getParcelable(ResponseConstants.LOCAL_MARKET));
        this.mUrl = StringUtils.EMPTY;
        this.mImageUrl = StringUtils.EMPTY;
        if (this.mLocalMarket != null) {
            this.mUrl = this.mLocalMarket.getDetailsUrl();
            if (!this.mLocalMarket.isWholesaleStore() && this.mLocalMarket.getAttendeeListingImages().size() > 0) {
                this.mImageUrl = ((ListingImage) this.mLocalMarket.getAttendeeListingImages().get(0)).getImageUrl();
            } else if (this.mLocalMarket.getStore() != null && this.mLocalMarket.getStore().getImages().size() > 0) {
                this.mImageUrl = ((LocalStoreImage) this.mLocalMarket.getStore().getImages().get(0)).getImageUrl();
            }
        }
    }

    public View onCreateHeaderView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(2130903334, viewGroup, false);
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        setupMarketCard(view);
        setupShareSheet(view);
    }

    private void setupMarketCard(View view) {
        TextView textView = (TextView) view.findViewById(2131756027);
        FrameLayout frameLayout = (FrameLayout) view.findViewById(2131756028);
        FrameLayout frameLayout2 = (FrameLayout) view.findViewById(2131756029);
        TextView textView2 = (TextView) view.findViewById(2131756033);
        TextView textView3 = (TextView) view.findViewById(2131756030);
        Resources resources = view.getResources();
        view.getContext();
        boolean isWholesaleStore = this.mLocalMarket.isWholesaleStore();
        int integer = resources.getInteger(2131558459);
        if (isWholesaleStore) {
            textView.setVisibility(8);
        } else {
            textView.setText(LocalMarketFormatter.m3303b(this.mLocalMarket));
        }
        Object arrayList = new ArrayList();
        if (isWholesaleStore) {
            arrayList.addAll(this.mLocalMarket.getStore().getImages());
        } else {
            arrayList.addAll(this.mLocalMarket.getAttendeeListingImages());
        }
        LocalMarketCardUtil.m4385a(frameLayout, frameLayout2, getImageBatch(), arrayList, isWholesaleStore, integer);
        textView2.setText(this.mLocalMarket.getMarketTypeLabelString(resources));
        textView3.setText(this.mLocalMarket.getTitle());
    }

    private void setupShareSheet(View view) {
        TextView textView = (TextView) view.findViewById(R.social_share_message);
        ((TextView) view.findViewById(R.social_share_title)).setText(R.local_share_prompt_title);
        textView.setText(R.local_share_prompt_message);
        textView.setVisibility(0);
    }

    public void onIntentItemClick(int i) {
        ResolveInfo resolveInfo = (ResolveInfo) this.mAdapter.getItem(i);
        if (this.mLocalMarket != null) {
            if (ShareBrokerFragment.isFacebook(resolveInfo)) {
                this.mText = null;
                this.mSubject = null;
                this.mImageUrl = null;
            } else if (this.mLocalMarket.isWholesaleStore()) {
                this.mText = getString(R.local_share_store_text, this.mLocalMarket.getCity(), this.mLocalMarket.getTitle(), this.mUrl);
                this.mSubject = getString(R.local_share_store_email_subject);
                if (ShareBrokerFragment.isGoogleEmail(resolveInfo)) {
                    this.mText = getString(R.local_share_store_email_text, this.mLocalMarket.getTitle(), this.mLocalMarket.getCity(), this.mUrl);
                }
            } else {
                if (this.mLocalMarket.hasCity()) {
                    this.mText = getString(R.local_share_event_text, this.mLocalMarket.getTitle(), this.mLocalMarket.getCity(), this.mUrl);
                } else {
                    this.mText = getString(R.local_share_event_text_no_city, this.mLocalMarket.getTitle(), this.mUrl);
                }
                this.mSubject = getString(R.local_share_event_email_subject);
            }
            if (ShareBrokerFragment.isTwitter(resolveInfo)) {
                this.mText += " " + getString(R.local_irl_hashtag);
            }
        }
        super.onIntentItemClick(i);
    }
}
