package com.etsy.android.ui.cart.viewholders;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.etsy.android.iconsy.AbstractFontIcon;
import com.etsy.android.iconsy.views.IconDrawable;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.img.ImageBatch;
import com.etsy.android.lib.models.IFullImage;
import com.etsy.android.lib.models.Variation;
import com.etsy.android.lib.models.apiv3.cart.CartGroupAction;
import com.etsy.android.lib.models.apiv3.cart.CartGroupItem;
import com.etsy.android.lib.models.apiv3.cart.CartListing;
import com.etsy.android.lib.util.fonts.EtsyFontIcons;
import com.etsy.android.ui.cart.p015a.CartListingClickHandler;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.view.FullImageView;
import com.etsy.android.uikit.view.ListingFullImageView;
import com.etsy.android.uikit.viewholder.BaseViewHolder;
import java.util.List;

public class CartListingViewHolder extends BaseViewHolder<CartGroupItem> {
    private final float ALPHA_FULL;
    private final float ALPHA_HALF;
    private final boolean mAvailable;
    private final Button mBtnRemove;
    private final Button mBtnSaveForLater;
    private CartListingClickHandler mClickHandler;
    private TextView mDescription;
    private final ListingFullImageView mImage;
    private ImageBatch mImageBatch;
    private final TextView mItemPrice;
    private final TextView mQuantity;
    private final TextView mTitle;
    private final TextView mTotalPrice;
    private final TextView mTxtCustomOrder;
    private final TextView mTxtDigitalDownload;
    private final TextView mTxtRegistry;
    private View mVariation1Row;
    private TextView mVariation1Title;
    private TextView mVariation1Value;
    private View mVariation2Row;
    private TextView mVariation2Title;
    private TextView mVariation2Value;
    private View mVariationsAndQuantity;

    /* renamed from: com.etsy.android.ui.cart.viewholders.CartListingViewHolder.1 */
    class C06031 extends TrackingOnClickListener {
        final /* synthetic */ CartListing f2592a;
        final /* synthetic */ CartListingViewHolder f2593b;

        C06031(CartListingViewHolder cartListingViewHolder, CartListing cartListing) {
            this.f2593b = cartListingViewHolder;
            this.f2592a = cartListing;
        }

        public void onViewClick(View view) {
            this.f2593b.mClickHandler.m3715a(this.f2592a);
        }
    }

    /* renamed from: com.etsy.android.ui.cart.viewholders.CartListingViewHolder.2 */
    class C06042 extends TrackingOnClickListener {
        final /* synthetic */ CartGroupItem f2594a;
        final /* synthetic */ CartListingViewHolder f2595b;

        C06042(CartListingViewHolder cartListingViewHolder, CartGroupItem cartGroupItem) {
            this.f2595b = cartListingViewHolder;
            this.f2594a = cartGroupItem;
        }

        public void onViewClick(View view) {
            this.f2595b.mClickHandler.m3711a(this.f2594a.getCartAdapterPosition(), this.f2594a.getAction(CartGroupAction.REMOVE_CART_LISTING));
        }
    }

    /* renamed from: com.etsy.android.ui.cart.viewholders.CartListingViewHolder.3 */
    class C06053 extends TrackingOnClickListener {
        final /* synthetic */ CartGroupItem f2596a;
        final /* synthetic */ CartListingViewHolder f2597b;

        C06053(CartListingViewHolder cartListingViewHolder, CartGroupItem cartGroupItem) {
            this.f2597b = cartListingViewHolder;
            this.f2596a = cartGroupItem;
        }

        public void onViewClick(View view) {
            this.f2597b.mClickHandler.m3711a(this.f2596a.getCartAdapterPosition(), this.f2596a.getAction(CartGroupAction.SAVE_CART_LISTING));
        }
    }

    public CartListingViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, ImageBatch imageBatch, CartListingClickHandler cartListingClickHandler, boolean z) {
        super(layoutInflater.inflate(2130903339, viewGroup, false));
        this.ALPHA_HALF = 0.5f;
        this.ALPHA_FULL = FullImageView.ASPECT_RATIO_SQUARE;
        this.mImageBatch = imageBatch;
        this.mClickHandler = cartListingClickHandler;
        this.mAvailable = z;
        this.mTitle = (TextView) findViewById(2131755687);
        this.mImage = (ListingFullImageView) findViewById(2131755075);
        this.mDescription = (TextView) findViewById(2131756040);
        this.mTotalPrice = (TextView) findViewById(2131756053);
        this.mItemPrice = (TextView) findViewById(2131756054);
        this.mVariationsAndQuantity = findViewById(2131755952);
        this.mVariation1Row = findViewById(2131756044);
        this.mVariation2Row = findViewById(2131756047);
        this.mVariation1Title = (TextView) findViewById(2131756045);
        this.mVariation2Title = (TextView) findViewById(2131756048);
        this.mQuantity = (TextView) findViewById(2131756052);
        this.mVariation1Value = (TextView) findViewById(2131756046);
        this.mVariation2Value = (TextView) findViewById(2131756049);
        this.mBtnSaveForLater = (Button) findViewById(2131755448);
        this.mBtnRemove = (Button) findViewById(2131756055);
        this.mTxtCustomOrder = (TextView) findViewById(2131756043);
        this.mTxtRegistry = (TextView) findViewById(2131756042);
        this.mTxtDigitalDownload = (TextView) findViewById(2131756041);
        if (this.mAvailable) {
            this.mImage.setAlpha(FullImageView.ASPECT_RATIO_SQUARE);
            this.mTitle.setAlpha(FullImageView.ASPECT_RATIO_SQUARE);
            return;
        }
        this.mImage.setAlpha(0.5f);
        this.mTitle.setAlpha(0.5f);
        this.mVariationsAndQuantity.setVisibility(8);
    }

    public void bind(CartGroupItem cartGroupItem) {
        CartListing cartListing = (CartListing) cartGroupItem.getData();
        this.mTitle.setText(cartListing.getTitle());
        IFullImage listingImage = cartListing.getListingImage();
        if (listingImage != null) {
            this.mImage.setImageInfo(listingImage, this.mImageBatch);
        }
        bindItemDecorators(cartListing);
        if (this.mAvailable) {
            bindQuantityAndVariations(cartListing);
        }
        if (this.mClickHandler != null) {
            this.itemView.setOnClickListener(new C06031(this, cartListing));
            this.mBtnRemove.setOnClickListener(new C06042(this, cartGroupItem));
            this.mBtnSaveForLater.setOnClickListener(new C06053(this, cartGroupItem));
        }
        if (cartGroupItem.isTrustSignalsOn()) {
            this.mDescription.setVisibility(0);
            this.mDescription.setText(cartListing.getDescription());
            return;
        }
        this.mDescription.setVisibility(8);
    }

    protected void bindQuantityAndVariations(CartListing cartListing) {
        this.mVariationsAndQuantity.setVisibility(0);
        this.mVariation1Row.setVisibility(8);
        this.mVariation2Row.setVisibility(8);
        List selectedVariations = cartListing.getSelectedVariations();
        this.mTotalPrice.setText(cartListing.getTotalPriceString());
        if (cartListing.getPurchaseQuantity() > 1) {
            this.mItemPrice.setVisibility(0);
            this.mItemPrice.setText(this.itemView.getResources().getString(R.cart_listing_item_price, new Object[]{cartListing.getItemPriceString()}));
        } else {
            this.mItemPrice.setVisibility(8);
        }
        this.mQuantity.setText(String.valueOf(cartListing.getPurchaseQuantity()));
        this.mVariationsAndQuantity.setVisibility(0);
        if (cartListing.getPurchaseQuantity() > 1 || (selectedVariations != null && selectedVariations.size() > 0)) {
            Variation variation;
            if (selectedVariations.size() > 0) {
                this.mVariation1Row.setVisibility(0);
                variation = (Variation) selectedVariations.get(0);
                this.mVariation1Title.setText(variation.getFormattedName());
                this.mVariation1Value.setText(variation.getFormattedValue());
            }
            if (selectedVariations.size() > 1) {
                this.mVariation2Row.setVisibility(0);
                variation = (Variation) selectedVariations.get(1);
                this.mVariation2Title.setText(variation.getFormattedName());
                this.mVariation2Value.setText(variation.getFormattedValue());
            }
        }
    }

    protected void bindItemDecorators(CartListing cartListing) {
        if (cartListing.isCustomOrder()) {
            initTextViewIconDrawable(this.mTxtCustomOrder, EtsyFontIcons.CUSTOMIZE);
            this.mTxtCustomOrder.setVisibility(0);
        } else {
            this.mTxtCustomOrder.setVisibility(8);
        }
        if (TextUtils.isEmpty(cartListing.getRegistryString())) {
            this.mTxtRegistry.setVisibility(8);
        } else {
            initTextViewIconDrawable(this.mTxtRegistry, EtsyFontIcons.GIFT);
            this.mTxtRegistry.setText(cartListing.getRegistryString());
            this.mTxtRegistry.setVisibility(0);
        }
        initTextViewIconDrawable(this.mTxtDigitalDownload, EtsyFontIcons.DOWNLOAD_CLOUD);
        this.mTxtDigitalDownload.setVisibility(0);
    }

    private void initTextViewIconDrawable(TextView textView, AbstractFontIcon abstractFontIcon) {
        if (textView.getCompoundDrawables()[0] == null) {
            Resources resources = textView.getResources();
            int dimensionPixelSize = resources.getDimensionPixelSize(R.text_view_icon_drawable_medium);
            int dimensionPixelSize2 = resources.getDimensionPixelSize(R.padding_small);
            Drawable a = IconDrawable.m775a(resources).m780a(abstractFontIcon).m779a(resources.getColor(R.brand_orange)).m778a((float) dimensionPixelSize).m777a();
            a.setBounds(0, 0, dimensionPixelSize, dimensionPixelSize);
            textView.setCompoundDrawablePadding(dimensionPixelSize2);
            textView.setCompoundDrawables(a, null, null, null);
        }
    }
}
