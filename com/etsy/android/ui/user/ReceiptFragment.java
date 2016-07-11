package com.etsy.android.ui.user;

import com.etsy.android.lib.config.EtsyConfigKeys;
import com.etsy.android.lib.core.EtsyRequestJob;
import com.etsy.android.lib.core.EtsyResult;
import com.etsy.android.lib.models.Receipt;
import com.etsy.android.lib.models.ResponseConstants.Includes;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.requests.ReceiptsRequest;
import com.etsy.android.lib.util.bh;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

/* renamed from: com.etsy.android.ui.user.g */
class ReceiptFragment extends EtsyRequestJob<Receipt> {
    boolean f3597a;
    final /* synthetic */ ReceiptFragment f3598c;

    public ReceiptFragment(ReceiptFragment receiptFragment, boolean z) {
        this.f3598c = receiptFragment;
        this.f3597a = z;
    }

    protected void b_() {
        this.f3598c.showLoadingView();
    }

    protected EtsyRequest<Receipt> m5060a() {
        EtsyRequest<Receipt> receipt = ReceiptsRequest.getReceipt(this.f3598c.mReceiptId);
        Map hashMap = new HashMap();
        Object obj = "Transactions(is_feedback_mutable,feedback_open_date,transaction_id,title,listing_id,quantity,price,shipping_cost,currency_code,is_gift_card,gift_card_info" + (this.f3598c.getConfigMap().m885c(EtsyConfigKeys.f1208a) ? ",variations" : StringUtils.EMPTY) + ")/MainImage(url_75x75,url_170x135),Transactions(transaction_id)/GiftCardDesign(url_150x119),Transactions(transaction_id)/UserReview/AppreciationPhoto(url_fullxfull,is_seller_approved,status)," + "Buyer(" + "user_id,login_name" + ")/" + "Profile(image_url_75x75,city,first_name,last_name,login_name)/Country" + "," + Includes.COUPON + "," + Receipt.ADDRESS_INCLUDES + "," + "Location(map_android,location_name)";
        if (this.f3597a) {
            obj = obj + ",Seller(user_id,login_name)/Profile(image_url_75x75,city,first_name,last_name,login_name)/Country,Seller(user_id)/Shops(shop_id,shop_name,creation_tsz,title,listing_active_count,icon_url_fullxfull)";
        }
        hashMap.put("includes", obj);
        receipt.addParams(hashMap);
        return receipt;
    }

    protected void m5061a(EtsyResult<Receipt> etsyResult) {
        if (etsyResult != null && etsyResult.m1049a() && etsyResult.m1058i()) {
            this.f3598c.mReceipt = (Receipt) etsyResult.m1056g().get(0);
            this.f3598c.populateViews(this.f3598c.mReceipt);
            this.f3598c.showListView();
        } else if (this.f3597a && etsyResult != null && bh.m3340a(etsyResult.m1052c()) && etsyResult.m1052c().contains("is not a valid user_id")) {
            m707g().m1697a(this.f3598c, new ReceiptFragment(this.f3598c, false));
        } else {
            this.f3598c.showErrorView();
        }
    }
}
