package com.etsy.android.ui.cart.viewholders;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.etsy.android.lib.core.img.ImageBatch;
import com.etsy.android.lib.models.apiv3.Image;
import com.etsy.android.lib.models.apiv3.ShopCard;
import com.etsy.android.lib.models.apiv3.cart.CartGroupItem;
import com.etsy.android.ui.cardview.p014a.ShopCardClickHandler;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.viewholder.BaseViewHolder;

public class ShopCartHeaderViewHolder extends BaseViewHolder<CartGroupItem> {
    private final TextView mBtnShopPolicies;
    private final ShopCardClickHandler mClickListener;
    private final ImageBatch mImageBatch;
    private final ImageView mImageShopAvatar;
    private final View mTextContactShop;
    private final TextView mTextShopName;

    /* renamed from: com.etsy.android.ui.cart.viewholders.ShopCartHeaderViewHolder.1 */
    class C06191 extends TrackingOnClickListener {
        final /* synthetic */ ShopCard f2625a;
        final /* synthetic */ ShopCartHeaderViewHolder f2626b;

        C06191(ShopCartHeaderViewHolder shopCartHeaderViewHolder, ShopCard shopCard) {
            this.f2626b = shopCartHeaderViewHolder;
            this.f2625a = shopCard;
        }

        public void onViewClick(View view) {
            if (this.f2626b.mClickListener != null) {
                this.f2626b.mClickListener.m3618a(this.f2625a);
            }
        }
    }

    /* renamed from: com.etsy.android.ui.cart.viewholders.ShopCartHeaderViewHolder.2 */
    class C06202 extends TrackingOnClickListener {
        final /* synthetic */ ShopCard f2627a;
        final /* synthetic */ ShopCartHeaderViewHolder f2628b;

        C06202(ShopCartHeaderViewHolder shopCartHeaderViewHolder, ShopCard shopCard) {
            this.f2628b = shopCartHeaderViewHolder;
            this.f2627a = shopCard;
        }

        public void onViewClick(View view) {
            if (this.f2628b.mClickListener != null) {
                this.f2628b.mClickListener.m3622a(this.f2627a.getLoginName());
            }
        }
    }

    /* renamed from: com.etsy.android.ui.cart.viewholders.ShopCartHeaderViewHolder.3 */
    class C06213 extends TrackingOnClickListener {
        final /* synthetic */ ShopCard f2629a;
        final /* synthetic */ ShopCartHeaderViewHolder f2630b;

        C06213(ShopCartHeaderViewHolder shopCartHeaderViewHolder, ShopCard shopCard) {
            this.f2630b = shopCartHeaderViewHolder;
            this.f2629a = shopCard;
        }

        public void onViewClick(View view) {
            if (this.f2630b.mClickListener != null) {
                this.f2630b.mClickListener.m3620a(this.f2629a.getShopId());
            }
        }
    }

    public ShopCartHeaderViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, ImageBatch imageBatch, ShopCardClickHandler shopCardClickHandler) {
        super(layoutInflater.inflate(2130903352, viewGroup, false));
        this.mImageBatch = imageBatch;
        this.mClickListener = shopCardClickHandler;
        this.mTextShopName = (TextView) findViewById(2131755688);
        this.mImageShopAvatar = (ImageView) findViewById(2131756077);
        this.mTextContactShop = findViewById(2131756078);
        this.mBtnShopPolicies = (TextView) findViewById(2131756079);
    }

    public void bind(CartGroupItem cartGroupItem) {
        ShopCard shopCard = (ShopCard) cartGroupItem.getData();
        this.mTextShopName.setText(shopCard.getShopName());
        this.mImageShopAvatar.setImageBitmap(null);
        Image icon = shopCard.getIcon();
        if (icon != null) {
            this.mImageBatch.m1567a(icon, this.mImageShopAvatar);
        } else if (TextUtils.isEmpty(shopCard.getAvatarUrl())) {
            this.mImageShopAvatar.setImageBitmap(null);
        } else {
            this.mImageBatch.m1568a(shopCard.getAvatarUrl(), this.mImageShopAvatar);
        }
        OnClickListener c06191 = new C06191(this, shopCard);
        this.mTextShopName.setOnClickListener(c06191);
        this.mImageShopAvatar.setOnClickListener(c06191);
        this.itemView.setOnClickListener(c06191);
        this.mTextContactShop.setOnClickListener(new C06202(this, shopCard));
        if (cartGroupItem.isTrustSignalsOn()) {
            this.mBtnShopPolicies.setVisibility(0);
            this.mBtnShopPolicies.setOnClickListener(new C06213(this, shopCard));
            return;
        }
        this.mBtnShopPolicies.setVisibility(8);
    }
}
