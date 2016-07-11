package com.etsy.android.ui.cart.viewholders;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.etsy.android.lib.models.apiv3.cart.CartGroupAction;
import com.etsy.android.lib.models.apiv3.cart.CartGroupItem;
import com.etsy.android.lib.models.apiv3.cart.MessageToSeller;
import com.etsy.android.lib.requests.CartsRequest;
import com.etsy.android.ui.cart.p015a.CartGroupActionClickHandler;
import com.etsy.android.uikit.viewholder.BaseViewHolder;
import com.foresee.sdk.configuration.MeasureConfiguration;
import java.util.HashMap;

public class MessageToSellerViewHolder extends BaseViewHolder<CartGroupItem> {
    private final CartGroupActionClickHandler mClickHandler;
    private final EditText mMessageToSeller;

    /* renamed from: com.etsy.android.ui.cart.viewholders.MessageToSellerViewHolder.1 */
    class C06091 implements OnEditorActionListener {
        final /* synthetic */ CartGroupItem f2606a;
        final /* synthetic */ MessageToSellerViewHolder f2607b;

        C06091(MessageToSellerViewHolder messageToSellerViewHolder, CartGroupItem cartGroupItem) {
            this.f2607b = messageToSellerViewHolder;
            this.f2606a = cartGroupItem;
        }

        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            CartGroupAction action = this.f2606a.getAction(CartsRequest.PARAM_NOTE_TO_SELLER);
            if (action != null) {
                HashMap hashMap = new HashMap();
                hashMap.put(CartsRequest.PARAM_NOTE_TO_SELLER, textView.getText().toString());
                action.setParams(hashMap);
                this.f2607b.mClickHandler.m3711a(this.f2606a.getCartAdapterPosition(), action);
            }
            return false;
        }
    }

    public MessageToSellerViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, CartGroupActionClickHandler cartGroupActionClickHandler) {
        super(layoutInflater.inflate(2130903345, viewGroup, false));
        this.mClickHandler = cartGroupActionClickHandler;
        this.mMessageToSeller = (EditText) findViewById(2131755325);
        this.mMessageToSeller.setHorizontallyScrolling(false);
        this.mMessageToSeller.setMaxLines(MeasureConfiguration.DISABLED);
    }

    public void bind(CartGroupItem cartGroupItem) {
        MessageToSeller messageToSeller = (MessageToSeller) cartGroupItem.getData();
        this.mMessageToSeller.setHint(messageToSeller.getHint());
        this.mMessageToSeller.setText(messageToSeller.getMessage());
        this.mMessageToSeller.setOnEditorActionListener(new C06091(this, cartGroupItem));
    }
}
