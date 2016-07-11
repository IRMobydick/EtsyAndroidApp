package com.etsy.android.uikit.share;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.interfaces.AppreciationPhotoLike;
import org.apache.commons.lang3.StringUtils;

public class SocialShareAppreciationPhotoBrokerFragment extends SocialShareBrokerFragment {
    private static final int MAX_TITLE_LENGTH = 35;
    private AppreciationPhotoLike mAppreciationPhoto;

    protected void readArguments(@NonNull Bundle bundle) {
        super.readArguments(bundle);
        this.mAppreciationPhoto = (AppreciationPhotoLike) bundle.getSerializable(ResponseConstants.APPRECIATION_PHOTO);
        if (this.mAppreciationPhoto == null) {
            EtsyDebug.m1894a(new IllegalArgumentException("No appreciation photo specified"));
            return;
        }
        String listingTitle = this.mAppreciationPhoto.getListingTitle();
        if (listingTitle == null) {
            listingTitle = StringUtils.EMPTY;
        } else if (listingTitle.length() > MAX_TITLE_LENGTH) {
            listingTitle = listingTitle.substring(0, 32) + "...";
        }
        this.mSubject = String.format(getString(R.review_share_message), new Object[]{this.mAppreciationPhoto.getShopName()}) + listingTitle;
        this.mText = String.format(getString(R.review_share_body), new Object[]{this.mAppreciationPhoto.getShopName()}) + " " + this.mAppreciationPhoto.getShortenedShareUrl().getShortUrl();
        if (this.mAppreciationPhoto.getShortenedShareUrl().isShortened()) {
            this.mShareUrl = this.mAppreciationPhoto.getShortenedShareUrl();
        }
        this.mUrl = this.mAppreciationPhoto.getShortenedShareUrl().getShortUrl();
        this.mImageUrl = this.mAppreciationPhoto.getShareImageUrl();
    }

    public View onCreateHeaderView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.fragment_social_share_appreciation_photo_header, viewGroup, false);
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        ImageView imageView = (ImageView) view.findViewById(R.appreciation_photo);
        if (imageView != null) {
            getImageBatch().m1568a(this.mAppreciationPhoto.getShareImageUrl(), imageView);
        }
    }
}
