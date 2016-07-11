package com.etsy.android.ui.giftcards;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import com.etsy.android.lib.core.img.ImageBatch;
import com.etsy.android.lib.models.GiftCardDesign;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import java.util.List;

public class GiftCardDesignSelecterView extends LinearLayout implements OnPageChangeListener {
    private TrackingOnClickListener mDesignOnClickListener;
    private GiftCardDesignPagerAdapter mGiftCardDesignAdapter;
    private ImageBatch mImageBatch;
    private ViewPager mViewPagerGiftCardDesigns;

    /* renamed from: com.etsy.android.ui.giftcards.GiftCardDesignSelecterView.1 */
    class C07061 extends TrackingOnClickListener {
        final /* synthetic */ GiftCardDesignSelecterView f2988a;

        C07061(GiftCardDesignSelecterView giftCardDesignSelecterView) {
            this.f2988a = giftCardDesignSelecterView;
        }

        public void onViewClick(View view) {
            Object tag = view.getTag();
            if (tag != null) {
                this.f2988a.mViewPagerGiftCardDesigns.setCurrentItem(((Integer) tag).intValue());
            }
        }
    }

    public GiftCardDesignSelecterView(Context context) {
        super(context);
        this.mDesignOnClickListener = new C07061(this);
        initViews(context);
    }

    public GiftCardDesignSelecterView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mDesignOnClickListener = new C07061(this);
        initViews(context);
    }

    public GiftCardDesignSelecterView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mDesignOnClickListener = new C07061(this);
        initViews(context);
    }

    private void initViews(Context context) {
        this.mImageBatch = new ImageBatch();
        inflate(context, 2130903556, this);
        this.mViewPagerGiftCardDesigns = (ViewPager) findViewById(2131756489);
    }

    public void setGiftCardDesigns(List<GiftCardDesign> list) {
        this.mGiftCardDesignAdapter = new GiftCardDesignPagerAdapter(this.mImageBatch, this.mDesignOnClickListener);
        this.mGiftCardDesignAdapter.addItems(list);
        this.mViewPagerGiftCardDesigns.addOnPageChangeListener(this);
        this.mViewPagerGiftCardDesigns.setAdapter(this.mGiftCardDesignAdapter);
        for (int i = 0; i < list.size(); i++) {
            if (((GiftCardDesign) list.get(i)).getId() == 4) {
                this.mViewPagerGiftCardDesigns.setCurrentItem(i);
            }
        }
    }

    public int getSelectedGiftCardId() {
        return this.mGiftCardDesignAdapter.getItem(this.mViewPagerGiftCardDesigns.getCurrentItem()).getId();
    }

    public void onPageScrolled(int i, float f, int i2) {
    }

    public void onPageSelected(int i) {
        this.mGiftCardDesignAdapter.getItem(this.mViewPagerGiftCardDesigns.getCurrentItem());
    }

    public void onPageScrollStateChanged(int i) {
    }
}
