package com.etsy.android.ui.cart.viewholders;

import android.view.View;
import com.etsy.android.lib.models.apiv3.cart.CartGroupItem;
import com.etsy.android.ui.cart.p015a.CartGroupActionClickHandler;
import com.etsy.android.uikit.view.FullImageView;
import com.etsy.android.uikit.viewholder.BaseViewHolder;

public abstract class CartGroupItemViewHolder extends BaseViewHolder<CartGroupItem> {
    protected final float ALPHA_FULL;
    protected final float ALPHA_HALF;
    protected final CartGroupActionClickHandler mClickHandler;

    public abstract void bind(CartGroupItem cartGroupItem);

    public CartGroupItemViewHolder(View view, CartGroupActionClickHandler cartGroupActionClickHandler) {
        super(view);
        this.ALPHA_HALF = 0.5f;
        this.ALPHA_FULL = FullImageView.ASPECT_RATIO_SQUARE;
        this.mClickHandler = cartGroupActionClickHandler;
    }
}
