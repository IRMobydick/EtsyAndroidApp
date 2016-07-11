package com.etsy.android.ui.user;

import com.etsy.android.lib.core.EtsyRequestJob;
import com.etsy.android.lib.core.EtsyResult;
import com.etsy.android.lib.models.Receipt;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.requests.UsersRequest;
import com.etsy.android.lib.util.ac;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

/* renamed from: com.etsy.android.ui.user.f */
class PurchasesFragment extends EtsyRequestJob<Receipt> {
    final /* synthetic */ PurchasesFragment f3596a;

    private PurchasesFragment(PurchasesFragment purchasesFragment) {
        this.f3596a = purchasesFragment;
    }

    protected EtsyRequest<Receipt> m5057a() {
        EtsyRequest<Receipt> findAllUserBuyerReceipts = UsersRequest.findAllUserBuyerReceipts();
        Map hashMap = new HashMap();
        hashMap.put("limit", "4");
        hashMap.put("offset", this.f3596a.mOffset + StringUtils.EMPTY);
        hashMap.put("fields", "receipt_id,grandtotal,currency_code,was_paid,was_shipped,creation_tsz,is_in_person,shipments,shipped_tsz");
        hashMap.put("includes", "Transactions(transaction_id,quantity,is_gift_card,title,price,currency_code,is_feedback_mutable)/MainImage(url_170x135),Transactions(transaction_id)/GiftCardDesign(url_150x119),Transactions(transaction_id)/UserReview(rating),Seller(login_name)/Profile(image_url_75x75,first_name,last_name,login_name),Seller(login_name)/Shops(shop_name,icon_url_fullxfull)");
        findAllUserBuyerReceipts.addParams(hashMap);
        return findAllUserBuyerReceipts;
    }

    protected void m5058a(EtsyResult<Receipt> etsyResult) {
        access$312(this.f3596a, ac.m3185a(etsyResult, this.f3596a.mResultAdapter, this.f3596a));
    }
}
