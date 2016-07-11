package com.etsy.android.ui.giftcards;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.etsy.android.lib.core.img.ImageBatch;
import com.etsy.android.lib.models.GiftCardDesign;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import java.util.ArrayList;
import java.util.List;

public class GiftCardDesignPagerAdapter extends PagerAdapter {
    TrackingOnClickListener mDesignOnClickListener;
    ImageBatch mImageBatch;
    ArrayList<GiftCardDesign> mItems;

    public GiftCardDesignPagerAdapter(ImageBatch imageBatch, TrackingOnClickListener trackingOnClickListener) {
        this.mItems = new ArrayList();
        this.mImageBatch = imageBatch;
        this.mDesignOnClickListener = trackingOnClickListener;
    }

    public void addItems(List<GiftCardDesign> list) {
        this.mItems.addAll(list);
    }

    public int getCount() {
        return this.mItems.size();
    }

    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        ImageView imageView = (ImageView) View.inflate(viewGroup.getContext(), 2130903555, null);
        viewGroup.addView(imageView);
        imageView.setTag(Integer.valueOf(i));
        imageView.setOnClickListener(this.mDesignOnClickListener);
        this.mImageBatch.m1568a(((GiftCardDesign) this.mItems.get(i)).getUrlBanner(), imageView);
        return imageView;
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((View) obj);
    }

    public float getPageWidth(int i) {
        return 0.94f;
    }

    public GiftCardDesign getItem(int i) {
        return (GiftCardDesign) this.mItems.get(i);
    }
}
