package com.etsy.android.ui;

import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.ui.cart.CartActivity;
import java.util.HashMap;

class EtsyWebFragment$CartOverrideWebViewClient$1 extends HashMap<String, Object> {
    final /* synthetic */ EtsyWebFragment this$1;
    final /* synthetic */ EtsyId val$cartId;
    final /* synthetic */ String val$errorMessage;

    EtsyWebFragment$CartOverrideWebViewClient$1(EtsyWebFragment etsyWebFragment, EtsyId etsyId, String str) {
        this.this$1 = etsyWebFragment;
        this.val$cartId = etsyId;
        this.val$errorMessage = str;
        put(CartActivity.INT_CART_ID, this.val$cartId.getId());
        put(ResponseConstants.ERROR_MESSAGE, this.val$errorMessage);
    }
}
