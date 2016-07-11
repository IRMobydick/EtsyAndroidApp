package com.etsy.android.ui.cart.viewholders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.etsy.android.lib.models.apiv3.cart.CartGroupAction;
import com.etsy.android.lib.models.apiv3.cart.CartGroupItem;
import com.etsy.android.lib.models.cardviewelement.BaseMessage;
import com.etsy.android.ui.cart.p015a.CartGroupActionClickHandler;
import com.etsy.android.uikit.util.TrackingOnClickListener;

public class MessageBannerViewHolder extends BaseMessageViewHolder<CartGroupItem> {
    private final TextView mBtnClose;
    private final CartGroupActionClickHandler mClickHandler;

    /* renamed from: com.etsy.android.ui.cart.viewholders.MessageBannerViewHolder.1 */
    class C06071 extends TrackingOnClickListener {
        final /* synthetic */ CartGroupItem f2600a;
        final /* synthetic */ CartGroupAction f2601b;
        final /* synthetic */ MessageBannerViewHolder f2602c;

        C06071(MessageBannerViewHolder messageBannerViewHolder, CartGroupItem cartGroupItem, CartGroupAction cartGroupAction) {
            this.f2602c = messageBannerViewHolder;
            this.f2600a = cartGroupItem;
            this.f2601b = cartGroupAction;
        }

        public void onViewClick(View view) {
            this.f2602c.mClickHandler.m3711a(this.f2600a.getCartAdapterPosition(), this.f2601b);
        }
    }

    public MessageBannerViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, CartGroupActionClickHandler cartGroupActionClickHandler) {
        super(layoutInflater.inflate(2130903343, viewGroup, false));
        this.mClickHandler = cartGroupActionClickHandler;
        this.mBtnClose = (TextView) findViewById(2131756061);
    }

    public void bind(CartGroupItem cartGroupItem) {
        bind((BaseMessage) cartGroupItem.getData());
        CartGroupAction action = cartGroupItem.getAction(CartGroupAction.DISMISS);
        if (action == null || this.mClickHandler == null) {
            this.mBtnClose.setVisibility(8);
            return;
        }
        this.mBtnClose.setVisibility(0);
        this.mBtnClose.setOnClickListener(new C06071(this, cartGroupItem, action));
    }

    protected void setTextColors(int i) {
        super.setTextColors(i);
        this.mBtnClose.setTextColor(i);
    }
}
