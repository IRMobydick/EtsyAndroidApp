package com.etsy.android.ui.adapters;

import android.app.Activity;
import android.content.res.Resources;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.img.ImageBatch;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.logger.ad;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.FavoriteList;
import com.etsy.android.lib.models.Treasury;
import com.etsy.android.lib.models.User;
import com.etsy.android.lib.models.apiv3.Collection;
import com.etsy.android.lib.models.interfaces.ShopLike;
import com.etsy.android.ui.util.EtsyCardUtil;
import com.etsy.android.uikit.BaseActivity;
import com.etsy.android.uikit.adapter.AdvancedModelArrayAdapter;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

public class CardListAdapter extends AdvancedModelArrayAdapter<BaseModel> {
    private static final String TAG;
    Reference<Fragment> mCallingFragment;
    private EtsyCardUtil mCardUtil;
    private int mItemCountIntResource;
    private int mNumCardsShown;
    private int mNumItemsPerCard;
    private boolean mRemoveFirstRowTopPadding;
    private boolean mShowRatingsOnShop;

    static {
        TAG = EtsyDebug.m1891a(CardListAdapter.class);
    }

    public CardListAdapter(FragmentActivity fragmentActivity, int i, ImageBatch imageBatch, @NonNull ad adVar) {
        super(fragmentActivity, i, imageBatch);
        this.mShowRatingsOnShop = false;
        this.mRemoveFirstRowTopPadding = false;
        this.mCallingFragment = null;
        this.mCardUtil = new EtsyCardUtil(fragmentActivity, imageBatch, adVar);
        this.mItemCountIntResource = 2131558402;
        setItemCounts();
    }

    public CardListAdapter(Fragment fragment, BaseActivity baseActivity, int i, ImageBatch imageBatch, @NonNull ad adVar) {
        this(baseActivity, i, imageBatch, adVar);
        this.mCallingFragment = new WeakReference(fragment);
    }

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        setItemCounts();
    }

    private void setItemCounts() {
        Resources resources = getContext().getResources();
        this.mNumItemsPerCard = resources.getInteger(this.mItemCountIntResource);
        this.mNumCardsShown = resources.getInteger(2131558435);
    }

    public void setItemCountIntResource(int i) {
        this.mItemCountIntResource = i;
    }

    public void setShowRatingsOnShop(boolean z) {
        this.mShowRatingsOnShop = z;
    }

    public void setRemoveFirstRowTopPadding(boolean z) {
        this.mRemoveFirstRowTopPadding = z;
    }

    public int getItemViewType(int i) {
        if (getContext().getResources().getConfiguration().orientation == 1) {
            return super.getItemViewType(i) + 0;
        }
        return super.getItemViewType(i) + 3;
    }

    public int getRealCount() {
        if (!this.mHasFooter || this.mNumCardsShown >= super.getRealCount()) {
            return super.getRealCount();
        }
        return this.mNumCardsShown;
    }

    public View getViewDefault(int i, View view, ViewGroup viewGroup) {
        EtsyCardUtil etsyCardUtil;
        Fragment fragment = null;
        if (view == null) {
            view = getLayoutInflater().inflate(getLayoutId(), null, false);
            EtsyCardUtil a = EtsyCardUtil.m5105a(view);
            view.setTag(a);
            etsyCardUtil = a;
        } else {
            etsyCardUtil = (EtsyCardUtil) view.getTag();
        }
        BaseModel item = getItem(i);
        if (this.mRemoveFirstRowTopPadding) {
            if (i == 0) {
                view.setPadding(view.getPaddingLeft(), 0, view.getPaddingRight(), view.getPaddingBottom());
            } else {
                view.setPadding(view.getPaddingLeft(), getContext().getResources().getDimensionPixelSize(R.margin_medium_large), view.getPaddingRight(), view.getPaddingBottom());
            }
        }
        if (item instanceof ShopLike) {
            this.mCardUtil.m5120a(etsyCardUtil, (ShopLike) item, this.mNumItemsPerCard, this.mShowRatingsOnShop);
        } else if (item instanceof Treasury) {
            this.mCardUtil.m5117a(etsyCardUtil, (Treasury) item, this.mNumItemsPerCard);
        } else if (item instanceof User) {
            this.mCardUtil.m5118a(etsyCardUtil, (User) item, this.mNumItemsPerCard);
        } else if (item instanceof Collection) {
            EtsyCardUtil etsyCardUtil2 = this.mCardUtil;
            Collection collection = (Collection) item;
            int i2 = this.mNumItemsPerCard;
            if (this.mCallingFragment != null) {
                fragment = (Fragment) this.mCallingFragment.get();
            }
            etsyCardUtil2.m5119a(etsyCardUtil, collection, i2, fragment);
        } else if (item instanceof FavoriteList) {
            this.mCardUtil.m5116a(etsyCardUtil, (FavoriteList) item, this.mNumItemsPerCard);
        } else {
            throw new ClassCastException("CardListAdapter cannot be used with unsupported BaseModel types.");
        }
        return view;
    }

    @CallSuper
    public void refreshActivity(Activity activity) {
        super.refreshActivity(activity);
        this.mCardUtil.m5115a(activity);
    }
}
