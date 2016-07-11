package com.etsy.android.ui.cardview.viewholders;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.img.ImageBatch;
import com.etsy.android.lib.logger.ITrackedObject;
import com.etsy.android.lib.models.ListingImage;
import com.etsy.android.lib.models.Variation;
import com.etsy.android.lib.models.apiv3.Image;
import com.etsy.android.lib.models.apiv3.ShopCard;
import com.etsy.android.lib.models.apiv3.cart.CartListing;
import com.etsy.android.lib.models.apiv3.cart.SavedCart;
import com.etsy.android.ui.cardview.p014a.SavedCartClickHandler;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.view.ListingFullImageView;
import com.etsy.android.uikit.viewholder.BaseViewHolder;
import java.util.List;

public class SavedCartListingCardViewHolder extends BaseViewHolder<SavedCart> {
    private View cartLoadingView;
    private ImageView headerSellerAvatar;
    private TextView headerSellerName;
    Button mBtnAddToCart;
    Button mBtnMoveToFavorites;
    private SavedCartClickHandler mClickListener;
    private ImageBatch mImageBatch;
    ListingFullImageView mListingImage;
    ViewGroup mListingItem;
    TextView mListingPrice;
    TextView mListingTitle;
    View mPlusShipping;
    TextView mQuantity;
    View mRemoveButton;
    View mUnavailableMessage;
    View mUnavailableMessageBottom;
    View mUnavailableMessageTop;
    TextView mVariation1Name;
    TextView mVariation2Name;
    View mVariationsAndQuantity;
    private View menuView;

    /* renamed from: com.etsy.android.ui.cardview.viewholders.SavedCartListingCardViewHolder.1 */
    class C05611 extends TrackingOnClickListener {
        final /* synthetic */ ShopCard f2300a;
        final /* synthetic */ SavedCartListingCardViewHolder f2301b;

        C05611(SavedCartListingCardViewHolder savedCartListingCardViewHolder, ITrackedObject[] iTrackedObjectArr, ShopCard shopCard) {
            this.f2301b = savedCartListingCardViewHolder;
            this.f2300a = shopCard;
            super(iTrackedObjectArr);
        }

        public void onViewClick(View view) {
            if (this.f2301b.mClickListener != null) {
                this.f2301b.mClickListener.m3612a(this.f2300a.getShopId());
            }
        }
    }

    /* renamed from: com.etsy.android.ui.cardview.viewholders.SavedCartListingCardViewHolder.2 */
    class C05622 extends TrackingOnClickListener {
        final /* synthetic */ CartListing f2302a;
        final /* synthetic */ SavedCartListingCardViewHolder f2303b;

        C05622(SavedCartListingCardViewHolder savedCartListingCardViewHolder, ITrackedObject[] iTrackedObjectArr, CartListing cartListing) {
            this.f2303b = savedCartListingCardViewHolder;
            this.f2302a = cartListing;
            super(iTrackedObjectArr);
        }

        public void onViewClick(View view) {
            if (this.f2303b.mClickListener != null) {
                this.f2303b.mClickListener.m3609a(this.f2302a);
            }
        }
    }

    /* renamed from: com.etsy.android.ui.cardview.viewholders.SavedCartListingCardViewHolder.3 */
    class C05633 extends TrackingOnClickListener {
        final /* synthetic */ SavedCart f2304a;
        final /* synthetic */ SavedCartListingCardViewHolder f2305b;

        C05633(SavedCartListingCardViewHolder savedCartListingCardViewHolder, ITrackedObject[] iTrackedObjectArr, SavedCart savedCart) {
            this.f2305b = savedCartListingCardViewHolder;
            this.f2304a = savedCart;
            super(iTrackedObjectArr);
        }

        public void onViewClick(View view) {
            if (this.f2305b.mClickListener != null) {
                this.f2305b.mClickListener.m3615c(this.f2304a, this.f2305b.getAdapterPosition());
            }
        }
    }

    /* renamed from: com.etsy.android.ui.cardview.viewholders.SavedCartListingCardViewHolder.4 */
    class C05644 extends TrackingOnClickListener {
        final /* synthetic */ SavedCart f2306a;
        final /* synthetic */ SavedCartListingCardViewHolder f2307b;

        C05644(SavedCartListingCardViewHolder savedCartListingCardViewHolder, ITrackedObject[] iTrackedObjectArr, SavedCart savedCart) {
            this.f2307b = savedCartListingCardViewHolder;
            this.f2306a = savedCart;
            super(iTrackedObjectArr);
        }

        public void onViewClick(View view) {
            if (this.f2307b.mClickListener != null) {
                this.f2307b.mClickListener.m3616d(this.f2306a, this.f2307b.getAdapterPosition());
            }
        }
    }

    /* renamed from: com.etsy.android.ui.cardview.viewholders.SavedCartListingCardViewHolder.5 */
    class C05655 extends TrackingOnClickListener {
        final /* synthetic */ SavedCart f2308a;
        final /* synthetic */ SavedCartListingCardViewHolder f2309b;

        C05655(SavedCartListingCardViewHolder savedCartListingCardViewHolder, ITrackedObject[] iTrackedObjectArr, SavedCart savedCart) {
            this.f2309b = savedCartListingCardViewHolder;
            this.f2308a = savedCart;
            super(iTrackedObjectArr);
        }

        public void onViewClick(View view) {
            if (this.f2309b.mClickListener != null) {
                this.f2309b.mClickListener.m3614b(this.f2308a, this.f2309b.getAdapterPosition());
            }
        }
    }

    public SavedCartListingCardViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, SavedCartClickHandler savedCartClickHandler, ImageBatch imageBatch) {
        super(layoutInflater.inflate(2130903299, viewGroup, false));
        this.mClickListener = savedCartClickHandler;
        this.mImageBatch = imageBatch;
        this.headerSellerName = (TextView) findViewById(2131755419);
        this.headerSellerAvatar = (ImageView) findViewById(2131755418);
        this.menuView = findViewById(2131755420);
        this.menuView.setVisibility(8);
        this.cartLoadingView = findViewById(2131755371);
        this.mRemoveButton = findViewById(2131755430);
        this.mBtnAddToCart = (Button) findViewById(2131756345);
        this.mBtnMoveToFavorites = (Button) findViewById(2131756346);
        this.mListingItem = (ViewGroup) findViewById(2131755431);
        this.mListingTitle = (TextView) findViewById(2131755440);
        this.mListingPrice = (TextView) findViewById(2131755441);
        this.mPlusShipping = findViewById(2131755951);
        this.mListingImage = (ListingFullImageView) findViewById(2131755429);
        this.mVariationsAndQuantity = findViewById(2131755952);
        this.mVariation1Name = (TextView) findViewById(2131755953);
        this.mVariation2Name = (TextView) findViewById(2131755954);
        this.mQuantity = (TextView) findViewById(2131755955);
        this.mUnavailableMessageTop = findViewById(2131755956);
        this.mUnavailableMessage = findViewById(2131755957);
        this.mUnavailableMessageBottom = findViewById(2131755958);
    }

    public void bind(SavedCart savedCart) {
        int i = 8;
        CartListing cartListing = savedCart.getCartListing();
        if (cartListing != null) {
            int i2;
            if (savedCart.getViewState().isLoading()) {
                this.cartLoadingView.setVisibility(0);
                this.cartLoadingView.setBackgroundColor(this.itemView.getResources().getColor(2131623980));
            } else {
                this.cartLoadingView.setVisibility(8);
            }
            ShopCard shopCard = savedCart.getShopCard();
            bindHeader(shopCard);
            OnClickListener c05611 = new C05611(this, new ITrackedObject[]{savedCart, shopCard}, shopCard);
            this.headerSellerName.setOnClickListener(c05611);
            this.headerSellerAvatar.setOnClickListener(c05611);
            boolean isAvailable = savedCart.isAvailable();
            this.mUnavailableMessage.setVisibility(isAvailable ? 8 : 0);
            View view = this.mUnavailableMessageTop;
            if (isAvailable) {
                i2 = 8;
            } else {
                i2 = 0;
            }
            view.setVisibility(i2);
            view = this.mUnavailableMessageBottom;
            if (isAvailable) {
                i2 = 8;
            } else {
                i2 = 0;
            }
            view.setVisibility(i2);
            bindListing(cartListing, new C05622(this, new ITrackedObject[]{savedCart, cartListing}, cartListing));
            Button button = this.mBtnAddToCart;
            if (isAvailable) {
                i = 0;
            }
            button.setVisibility(i);
            this.mBtnAddToCart.setOnClickListener(new C05633(this, new ITrackedObject[]{savedCart, cartListing}, savedCart));
            this.mRemoveButton.setOnClickListener(new C05644(this, new ITrackedObject[]{savedCart, cartListing}, savedCart));
            this.mBtnMoveToFavorites.setOnClickListener(new C05655(this, new ITrackedObject[]{savedCart, cartListing}, savedCart));
        }
    }

    protected void bindListing(CartListing cartListing, TrackingOnClickListener trackingOnClickListener) {
        int i;
        if (!TextUtils.isEmpty(cartListing.getTitle())) {
            this.mListingTitle.setText(cartListing.getTitle());
        }
        this.mListingItem.setOnClickListener(trackingOnClickListener);
        this.mListingPrice.setText(cartListing.getItemPriceString());
        boolean z = (cartListing.isDigital() || cartListing.isGiftCard()) ? false : true;
        View view = this.mPlusShipping;
        if (z) {
            i = 0;
        } else {
            i = 8;
        }
        view.setVisibility(i);
        ListingImage listingImage = (ListingImage) cartListing.getListingImage();
        if (listingImage != null) {
            this.mListingImage.setClickable(true);
            this.mListingImage.setVisibility(0);
            this.mListingImage.setOnClickListener(trackingOnClickListener);
            this.mListingImage.setImageInfo(listingImage, this.mImageBatch);
        }
        this.mVariationsAndQuantity.setVisibility(8);
        this.mVariation1Name.setVisibility(8);
        this.mVariation2Name.setVisibility(8);
        this.mQuantity.setVisibility(8);
        List selectedVariations = cartListing.getSelectedVariations();
        if (cartListing.getPurchaseQuantity() > 1 || (selectedVariations != null && selectedVariations.size() > 0)) {
            Variation variation;
            this.mVariationsAndQuantity.setVisibility(0);
            if (cartListing.getPurchaseQuantity() > 1) {
                this.mQuantity.setVisibility(0);
                this.mQuantity.setText(this.mQuantity.getResources().getString(R.quantity_sub, new Object[]{String.valueOf(cartListing.getPurchaseQuantity())}));
            }
            if (selectedVariations.size() > 0) {
                this.mVariation1Name.setVisibility(0);
                variation = (Variation) selectedVariations.get(0);
                this.mVariation1Name.setText(variation.getFormattedName() + ": " + variation.getFormattedValue());
            }
            if (selectedVariations.size() > 1) {
                this.mVariation2Name.setVisibility(0);
                variation = (Variation) selectedVariations.get(1);
                this.mVariation2Name.setText(variation.getFormattedName() + ": " + variation.getFormattedValue());
            }
        }
    }

    private void bindHeader(ShopCard shopCard) {
        this.headerSellerName.setText(shopCard.getShopName());
        Image icon = shopCard.getIcon();
        if (icon != null) {
            this.mImageBatch.m1567a(icon, this.headerSellerAvatar);
        } else if (TextUtils.isEmpty(shopCard.getAvatarUrl())) {
            this.headerSellerAvatar.setImageBitmap(null);
        } else {
            this.mImageBatch.m1568a(shopCard.getAvatarUrl(), this.headerSellerAvatar);
        }
        this.menuView.setVisibility(4);
    }
}
