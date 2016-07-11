package com.etsy.android.ui.core;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.SimpleOnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.etsy.android.lib.R;
import com.etsy.android.lib.models.BaseModelImage;
import com.etsy.android.lib.util.ab;
import com.etsy.android.ui.EtsyFragment;
import com.etsy.android.uikit.adapter.ClickableImagesPagerAdapter;
import com.etsy.android.uikit.adapter.FullImagesPagerAdapter;
import com.etsy.android.uikit.adapter.ListingThumbnailAdapter;
import com.etsy.android.uikit.view.CustomViewPageIndicator;
import com.viewpagerindicator.CirclePageIndicator;
import java.util.ArrayList;

public class DetailedImageFragment extends EtsyFragment implements ClickableImagesPagerAdapter {
    private FullImagesPagerAdapter mAdapter;
    private ArrayList<BaseModelImage> mImageArray;
    private int mInitialPosition;
    private final SimpleOnPageChangeListener mListener;
    private CirclePageIndicator mPagerIndicator;
    private boolean mShowThumbnails;
    private ListingThumbnailAdapter mThumbnailAdapter;
    private CustomViewPageIndicator mThumbnailIndicator;
    private ViewPager mViewPager;

    /* renamed from: com.etsy.android.ui.core.DetailedImageFragment.1 */
    class C06521 implements CustomViewPageIndicator {
        final /* synthetic */ DetailedImageFragment f2692a;

        C06521(DetailedImageFragment detailedImageFragment) {
            this.f2692a = detailedImageFragment;
        }

        public void m3953a(int i) {
            this.f2692a.mThumbnailIndicator.setCurrentItem(i);
        }
    }

    /* renamed from: com.etsy.android.ui.core.DetailedImageFragment.2 */
    class C06532 extends SimpleOnPageChangeListener {
        final /* synthetic */ DetailedImageFragment f2693a;

        C06532(DetailedImageFragment detailedImageFragment) {
            this.f2693a = detailedImageFragment;
        }

        public void onPageScrolled(int i, float f, int i2) {
            super.onPageScrolled(i, f, i2);
            if (f == 0.0f) {
                this.f2693a.mAdapter.resetZoomAtPosition(i - 1);
                this.f2693a.mAdapter.resetZoomAtPosition(i + 1);
            }
        }
    }

    public DetailedImageFragment() {
        this.mListener = new C06532(this);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        this.mImageArray = new ArrayList((ArrayList) arguments.getSerializable("image_list"));
        this.mInitialPosition = arguments.getInt("position");
        this.mShowThumbnails = arguments.getBoolean("SHOW_THUMBNAILS");
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.fragment_detailed_image, viewGroup, false);
        this.mViewPager = (ViewPager) inflate.findViewById(R.viewpager);
        this.mViewPager.setPageMargin(getResources().getDimensionPixelOffset(R.margin_large));
        this.mPagerIndicator = (CirclePageIndicator) inflate.findViewById(R.vpindicator_listing_images);
        this.mThumbnailIndicator = (CustomViewPageIndicator) inflate.findViewById(R.vpindicator_listing_thumbnails);
        return inflate;
    }

    public void onActivityCreated(Bundle bundle) {
        int i = 1;
        super.onActivityCreated(bundle);
        if (this.mImageArray == null) {
            this.mActivity.finish();
        }
        if (this.mAdapter == null) {
            this.mAdapter = createAdapter();
        }
        ab abVar = new ab(this.mActivity);
        this.mAdapter.setImageSizes(abVar.m3182e(), abVar.m3183f());
        this.mViewPager.setAdapter(this.mAdapter);
        this.mViewPager.setCurrentItem(this.mInitialPosition);
        if (this.mImageArray.size() <= 1) {
            i = 0;
        }
        if (i == 0) {
            this.mPagerIndicator.setVisibility(8);
            this.mThumbnailIndicator.setVisibility(8);
        } else if (this.mShowThumbnails) {
            this.mThumbnailIndicator.setOnPageChangeListener(this.mListener);
            this.mThumbnailIndicator.setViewPager(this.mViewPager);
            if (this.mThumbnailAdapter == null) {
                this.mThumbnailAdapter = new ListingThumbnailAdapter(this.mActivity, getImageBatch());
                this.mThumbnailAdapter.addAll(this.mImageArray);
            }
            this.mThumbnailIndicator.setAdapter(this.mThumbnailAdapter);
            this.mThumbnailIndicator.setIndicatorClickListener(new C06521(this));
            this.mThumbnailIndicator.setVisibility(0);
            this.mPagerIndicator.setVisibility(8);
        } else {
            this.mPagerIndicator.setOnPageChangeListener(this.mListener);
            this.mPagerIndicator.setViewPager(this.mViewPager);
            this.mPagerIndicator.setVisibility(0);
            this.mThumbnailIndicator.setVisibility(8);
        }
    }

    protected FullImagesPagerAdapter createAdapter() {
        FullImagesPagerAdapter fullImagesPagerAdapter = new FullImagesPagerAdapter(this.mActivity, getImageBatch(), getAnalyticsContext(), this);
        fullImagesPagerAdapter.setImages(this.mImageArray);
        return fullImagesPagerAdapter;
    }

    public void onImageClick(int i) {
    }

    protected void addImage(int i, BaseModelImage baseModelImage) {
        if (this.mImageArray == null) {
            this.mImageArray = new ArrayList();
        }
        this.mImageArray.add(i, baseModelImage);
    }

    @NonNull
    public String getTrackingName() {
        return "image_zoom";
    }
}
