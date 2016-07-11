package com.etsy.android.ui.cart.viewholders;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.etsy.android.iconsy.views.IconDrawable;
import com.etsy.android.lib.R;
import com.etsy.android.lib.models.apiv3.cart.CartGroupAction;
import com.etsy.android.lib.models.apiv3.cart.CartGroupItem;
import com.etsy.android.lib.models.apiv3.cart.SimpleKVPCartGroupItem;
import com.etsy.android.lib.util.fonts.EtsyFontIcons;
import com.etsy.android.ui.cart.p015a.CartGroupActionClickHandler;
import com.etsy.android.uikit.util.TrackingOnCheckedChangeListener;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.viewholder.BaseViewHolder;
import java.util.HashMap;

public class PaymentApplyGiftCardViewHolder extends BaseViewHolder<CartGroupItem> {
    private final CheckBox mCheckboxGiftCardApplied;
    private final CartGroupActionClickHandler mClickHandler;
    private final TextView mText;

    /* renamed from: com.etsy.android.ui.cart.viewholders.PaymentApplyGiftCardViewHolder.1 */
    class C06121 extends TrackingOnCheckedChangeListener {
        final /* synthetic */ CartGroupItem f2612a;
        final /* synthetic */ PaymentApplyGiftCardViewHolder f2613b;

        C06121(PaymentApplyGiftCardViewHolder paymentApplyGiftCardViewHolder, CartGroupItem cartGroupItem) {
            this.f2613b = paymentApplyGiftCardViewHolder;
            this.f2612a = cartGroupItem;
        }

        public void onViewCheckedChanged(CompoundButton compoundButton, boolean z) {
            if (this.f2613b.mClickHandler != null) {
                CartGroupAction action = this.f2612a.getAction(CartGroupAction.SHOULD_USE_GIFTCARD);
                if (action != null) {
                    HashMap hashMap = new HashMap();
                    hashMap.put("should_use_gift_card", String.valueOf(z));
                    action.setParams(hashMap);
                    this.f2613b.mClickHandler.m3711a(this.f2612a.getCartAdapterPosition(), action);
                }
            }
        }
    }

    /* renamed from: com.etsy.android.ui.cart.viewholders.PaymentApplyGiftCardViewHolder.2 */
    class C06132 extends TrackingOnClickListener {
        final /* synthetic */ PaymentApplyGiftCardViewHolder f2614a;

        C06132(PaymentApplyGiftCardViewHolder paymentApplyGiftCardViewHolder) {
            this.f2614a = paymentApplyGiftCardViewHolder;
        }

        public void onViewClick(View view) {
            this.f2614a.mCheckboxGiftCardApplied.setChecked(this.f2614a.mCheckboxGiftCardApplied.isChecked());
        }
    }

    public PaymentApplyGiftCardViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, CartGroupActionClickHandler cartGroupActionClickHandler) {
        super(layoutInflater.inflate(2130903346, viewGroup, false));
        this.mClickHandler = cartGroupActionClickHandler;
        this.mText = (TextView) findViewById(2131756065);
        Resources resources = this.itemView.getResources();
        int dimensionPixelSize = resources.getDimensionPixelSize(R.text_view_icon_drawable_small);
        int dimensionPixelSize2 = resources.getDimensionPixelSize(R.padding_small);
        Drawable a = IconDrawable.m775a(resources).m780a(EtsyFontIcons.GIFT).m779a(resources.getColor(R.brand_orange)).m778a((float) dimensionPixelSize).m777a();
        a.setBounds(0, 0, dimensionPixelSize, dimensionPixelSize);
        this.mText.setCompoundDrawablePadding(dimensionPixelSize2);
        this.mText.setCompoundDrawables(a, null, null, null);
        this.mCheckboxGiftCardApplied = (CheckBox) findViewById(2131756064);
    }

    public void bind(CartGroupItem cartGroupItem) {
        SimpleKVPCartGroupItem simpleKVPCartGroupItem = (SimpleKVPCartGroupItem) cartGroupItem.getData();
        if (simpleKVPCartGroupItem != null) {
            this.mText.setText(simpleKVPCartGroupItem.getFormattedMoneyString());
        }
        this.mCheckboxGiftCardApplied.setOnCheckedChangeListener(new C06121(this, cartGroupItem));
        this.mText.setOnClickListener(new C06132(this));
    }
}
