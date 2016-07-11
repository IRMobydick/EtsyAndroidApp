package com.etsy.android.uikit.share;

import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.apiv3.ActivityFeedEntity;
import com.etsy.android.lib.models.apiv3.Collection;
import com.etsy.android.lib.models.apiv3.ListingCollection;
import com.etsy.android.lib.models.interfaces.BasicListingLike;
import com.etsy.android.uikit.view.ListingFullImageView;

public class SocialShareListingBrokerFragment extends SocialShareBrokerFragment {
    private ListingCollection mCollection;
    private boolean mFundOnEtsyEnabled;
    private BasicListingLike mListing;

    public SocialShareListingBrokerFragment() {
        this.mFundOnEtsyEnabled = false;
    }

    protected void readArguments(@NonNull Bundle bundle) {
        super.readArguments(bundle);
        this.mListing = (BasicListingLike) bundle.getSerializable(ActivityFeedEntity.LISTING);
        this.mCollection = (ListingCollection) bundle.getSerializable(Collection.TYPE_COLLECTION);
        if (this.mListing == null) {
            EtsyDebug.m1894a(new IllegalArgumentException("No listing specified"));
            return;
        }
        if (this.mCollection != null) {
            this.mUrl = this.mCollection.getUrl();
            this.mText = getString(R.social_share_collection_message, this.mCollection.getUrl());
            this.mSubject = getString(R.share_collection_subject);
        } else {
            this.mUrl = this.mListing.getUrl();
            this.mText = getString(R.social_share_listing_message, this.mListing.getUrl());
            this.mSubject = getString(R.share_listing_subject);
        }
        this.mImageUrl = this.mListing.getListingImage().get4to3ImageUrlForPixelWidth(0);
    }

    private String getFoeShareText(BasicListingLike basicListingLike, boolean z) {
        String substring;
        int i = 100;
        int length = basicListingLike.getTitle().length();
        if (z) {
            String title = basicListingLike.getTitle();
            if (length <= 100) {
                i = length;
            }
            substring = title.substring(0, i);
        } else {
            substring = basicListingLike.getTitle();
        }
        return getString(R.fund_share_listing_subject, substring, basicListingLike.getUrl());
    }

    public View onCreateHeaderView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.fragment_social_share_listing_header, viewGroup, false);
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        showListing(view);
        TextView textView = (TextView) view.findViewById(R.social_share_title);
        if (this.mCollection != null) {
            textView.setText(R.share_prompt_collection_message);
        } else {
            textView.setText(R.share_prompt_listing_message);
        }
    }

    public void showListing(View view) {
        if (view != null) {
            TextView textView = (TextView) view.findViewById(R.listing_title);
            ListingFullImageView listingFullImageView = (ListingFullImageView) view.findViewById(R.listing_image);
            listingFullImageView.setUseStandardRatio(true);
            if (this.mListing == null) {
                textView.setVisibility(8);
                listingFullImageView.setVisibility(8);
                return;
            }
            textView.setVisibility(0);
            listingFullImageView.setVisibility(0);
            textView.setText(this.mListing.getTitle());
            if (this.mImageUrl != null) {
                listingFullImageView.setImageInfo(this.mListing.getListingImage(), getImageBatch());
            } else {
                listingFullImageView.setVisibility(8);
            }
        }
    }

    public void onIntentItemClick(int i) {
        ResolveInfo resolveInfo = (ResolveInfo) this.mAdapter.getItem(i);
        if (this.mCollection == null) {
            if (ShareBrokerFragment.isTwitter(resolveInfo)) {
                this.mText = getString(R.share_listing_twitter, this.mUrl);
            } else if (ShareBrokerFragment.isGmail(resolveInfo)) {
                this.mSubject = getString(R.share_listing_email_subject);
                this.mText = getString(R.share_listing_email_message, this.mUrl);
            } else if (ShareBrokerFragment.isPinterest(resolveInfo) || ShareBrokerFragment.isGooglePlus(resolveInfo)) {
                this.mText = getString(R.share_listing_pinterest);
            } else if (ShareBrokerFragment.isHangouts(resolveInfo)) {
                this.mText = getString(R.share_listing_hangouts, this.mUrl);
            }
        } else if (ShareBrokerFragment.isTwitter(resolveInfo)) {
            this.mText = getString(R.share_collection_twitter, this.mUrl);
        } else if (ShareBrokerFragment.isGmail(resolveInfo)) {
            this.mSubject = getString(R.share_collection_email_subject);
            this.mText = getString(R.share_collection_email_message, this.mUrl);
        } else if (ShareBrokerFragment.isPinterest(resolveInfo) || ShareBrokerFragment.isGooglePlus(resolveInfo)) {
            this.mText = getString(R.share_collection_pinterest);
        }
        super.onIntentItemClick(i);
    }
}
