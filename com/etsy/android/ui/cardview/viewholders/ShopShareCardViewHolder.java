package com.etsy.android.ui.cardview.viewholders;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;
import com.etsy.android.iconsy.views.IconView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.EtsyApplication;
import com.etsy.android.lib.core.aq;
import com.etsy.android.lib.core.img.ImageBatch;
import com.etsy.android.lib.logger.ShopShareEventTracker;
import com.etsy.android.lib.models.apiv3.ListingCard;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.shopshare.ShareItem;
import com.etsy.android.lib.models.shopshare.ShopShareCard;
import com.etsy.android.lib.util.SharedPreferencesUtility;
import com.etsy.android.lib.util.ax;
import com.etsy.android.lib.util.bk;
import com.etsy.android.ui.cardview.p014a.ListingCardClickHandler;
import com.etsy.android.ui.cardview.p014a.ShopShareCardClickHandler;
import com.etsy.android.uikit.adapter.AnnotationAdapter;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.util.ViewTreeObserverHelper;
import com.etsy.android.uikit.view.FullImageView;
import com.etsy.android.uikit.view.TaggableImageView;
import com.etsy.android.uikit.viewholder.BaseViewHolder;
import com.etsy.android.uikit.viewholder.ListingCardViewHolder;

public class ShopShareCardViewHolder extends BaseViewHolder<ShopShareCard> {
    private static final String MANAGE_POST_MINIMUM_SOE_VERSION = "2.22.0.39";
    private AnnotationAdapter mAdapter;
    private TextView mCaption;
    private ScrollView mCaptionScrollView;
    private final ShopShareCardClickHandler mCardViewItemClickHandler;
    private Context mContext;
    private TextView mCreateDate;
    private final ImageBatch mImageBatch;
    private TaggableImageView mImageView;
    private LayoutInflater mInflater;
    private IconView mMenuIcon;
    private LinearLayout mShareDetails;
    private FrameLayout mShareListing;
    private ImageView mShopAvatarView;
    private TextView mShopName;
    private boolean mShowMenuIcon;
    private TextView mTagLabel;

    /* renamed from: com.etsy.android.ui.cardview.viewholders.ShopShareCardViewHolder.1 */
    class C05711 extends TrackingOnClickListener {
        final /* synthetic */ ShopShareCard f2322a;
        final /* synthetic */ ShopShareCardViewHolder f2323b;

        C05711(ShopShareCardViewHolder shopShareCardViewHolder, ShopShareCard shopShareCard) {
            this.f2323b = shopShareCardViewHolder;
            this.f2322a = shopShareCard;
        }

        public void onViewClick(View view) {
            ShopShareEventTracker.m2089a("feed.ellipsis.tapped", "shop_shares_feed");
            this.f2323b.getManageShopSharePopup(this.f2322a).showAsDropDown(view);
        }
    }

    /* renamed from: com.etsy.android.ui.cardview.viewholders.ShopShareCardViewHolder.2 */
    class C05722 implements OnGlobalLayoutListener {
        final /* synthetic */ ShopShareCardViewHolder f2324a;

        C05722(ShopShareCardViewHolder shopShareCardViewHolder) {
            this.f2324a = shopShareCardViewHolder;
        }

        public void onGlobalLayout() {
            ViewTreeObserverHelper.m5639b(this.f2324a.mImageView.getViewTreeObserver(), (OnGlobalLayoutListener) this);
            int measuredHeight = this.f2324a.mShareDetails.getMeasuredHeight();
            int measuredWidth = this.f2324a.mImageView.getMeasuredWidth();
            if (measuredHeight > measuredWidth) {
                this.f2324a.mShareDetails.setLayoutParams(new LayoutParams(this.f2324a.mShareDetails.getMeasuredWidth(), measuredWidth));
            }
            if (this.f2324a.mCaption.getHeight() < this.f2324a.mCaptionScrollView.getHeight()) {
                ViewGroup.LayoutParams layoutParams = new LayoutParams(this.f2324a.mShareDetails.getMeasuredWidth(), this.f2324a.mCaption.getHeight());
                layoutParams.setMargins(0, 0, (int) this.f2324a.mImageView.getResources().getDimension(R.margin_large), 0);
                this.f2324a.mCaptionScrollView.setLayoutParams(layoutParams);
            }
        }
    }

    /* renamed from: com.etsy.android.ui.cardview.viewholders.ShopShareCardViewHolder.3 */
    class C05733 implements OnTouchListener {
        final /* synthetic */ ShopShareCardViewHolder f2325a;

        C05733(ShopShareCardViewHolder shopShareCardViewHolder) {
            this.f2325a = shopShareCardViewHolder;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (this.f2325a.mCaption.getHeight() > this.f2325a.mCaptionScrollView.getHeight()) {
                view.getParent().requestDisallowInterceptTouchEvent(true);
            } else {
                view.getParent().requestDisallowInterceptTouchEvent(false);
            }
            return false;
        }
    }

    /* renamed from: com.etsy.android.ui.cardview.viewholders.ShopShareCardViewHolder.4 */
    class C05744 implements OnClickListener {
        final /* synthetic */ ShopShareCard f2326a;
        final /* synthetic */ PopupWindow f2327b;
        final /* synthetic */ ShopShareCardViewHolder f2328c;

        C05744(ShopShareCardViewHolder shopShareCardViewHolder, ShopShareCard shopShareCard, PopupWindow popupWindow) {
            this.f2328c = shopShareCardViewHolder;
            this.f2326a = shopShareCard;
            this.f2327b = popupWindow;
        }

        public void onClick(View view) {
            ShopShareEventTracker.m2089a("feed.manage_post.tapped", "shop_shares_feed");
            this.f2328c.mCardViewItemClickHandler.m3639c(this.f2326a);
            this.f2327b.dismiss();
        }
    }

    /* renamed from: com.etsy.android.ui.cardview.viewholders.ShopShareCardViewHolder.5 */
    class C05755 implements OnClickListener {
        final /* synthetic */ ShopShareCard f2329a;
        final /* synthetic */ PopupWindow f2330b;
        final /* synthetic */ ShopShareCardViewHolder f2331c;

        C05755(ShopShareCardViewHolder shopShareCardViewHolder, ShopShareCard shopShareCard, PopupWindow popupWindow) {
            this.f2331c = shopShareCardViewHolder;
            this.f2329a = shopShareCard;
            this.f2330b = popupWindow;
        }

        public void onClick(View view) {
            ShopShareEventTracker.m2089a("feed.block_this_post.tapped", "shop_shares_feed");
            this.f2331c.mCardViewItemClickHandler.m3633a(this.f2331c.getAdapterPosition(), this.f2329a);
            this.f2330b.dismiss();
        }
    }

    /* renamed from: com.etsy.android.ui.cardview.viewholders.ShopShareCardViewHolder.6 */
    class C05766 implements OnClickListener {
        final /* synthetic */ ShopShareCard f2332a;
        final /* synthetic */ PopupWindow f2333b;
        final /* synthetic */ ShopShareCardViewHolder f2334c;

        C05766(ShopShareCardViewHolder shopShareCardViewHolder, ShopShareCard shopShareCard, PopupWindow popupWindow) {
            this.f2334c = shopShareCardViewHolder;
            this.f2332a = shopShareCard;
            this.f2333b = popupWindow;
        }

        public void onClick(View view) {
            ShopShareEventTracker.m2089a("feed.block_seller_posts.tapped", "shop_shares_feed");
            this.f2334c.mCardViewItemClickHandler.m3637b(this.f2334c.getAdapterPosition(), this.f2332a);
            this.f2333b.dismiss();
        }
    }

    public ShopShareCardViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, ShopShareCardClickHandler shopShareCardClickHandler, ImageBatch imageBatch, int i, ListingCardClickHandler listingCardClickHandler) {
        super(layoutInflater.inflate(i, viewGroup, false));
        this.mShowMenuIcon = true;
        this.mInflater = layoutInflater;
        this.mImageBatch = imageBatch;
        this.mContext = viewGroup.getContext();
        this.mCardViewItemClickHandler = shopShareCardClickHandler;
        this.mShareListing = (FrameLayout) findViewById(2131755973);
        this.mShareDetails = (LinearLayout) findViewById(2131755969);
        this.mCreateDate = (TextView) findViewById(R.create_date);
        this.mMenuIcon = (IconView) findViewById(R.menu_icon);
        this.mCaption = (TextView) findViewById(R.caption);
        this.mShopName = (TextView) findViewById(R.shop_name);
        this.mShopAvatarView = (ImageView) findViewById(R.shop_avatar);
        this.mTagLabel = (TextView) findViewById(2131755972);
        this.mCaptionScrollView = (ScrollView) findViewById(2131755970);
        this.mImageView = (TaggableImageView) findViewById(R.shop_share_image);
        if (this.mShareListing != null) {
            this.mShareListing.setTag(new ListingCardViewHolder(layoutInflater, viewGroup, listingCardClickHandler, imageBatch, false, false));
        }
    }

    public ShopShareCardViewHolder setShowMenuIcon(boolean z) {
        this.mShowMenuIcon = z;
        return this;
    }

    public void bind(ShopShareCard shopShareCard) {
        super.bind(shopShareCard);
        ShareItem shareItem = shopShareCard.getShareItem();
        this.mImageBatch.m1567a(shareItem.getPrimaryMedia().getImage(), this.mImageView.getImageView());
        this.mImageBatch.m1568a(shopShareCard.getShopAvatarUrl(), this.mShopAvatarView);
        this.mAdapter = new AnnotationAdapter(this.mContext, this.mImageView.getImageView(), shareItem);
        this.mImageView.setAdapter(this.mAdapter);
        this.mImageView.setListener(new ShopShareCardViewHolder(this, shopShareCard));
        this.mCreateDate.setText(bk.m3349a(this.mAdapter.getContext(), Long.valueOf(shareItem.getCreateDate().getTime())));
        this.mCaption.setText(shareItem.getText());
        this.mMenuIcon.setOnClickListener(new C05711(this, shopShareCard));
        OnClickListener shopShareCardViewHolder = new ShopShareCardViewHolder(this, shopShareCard);
        this.mShopAvatarView.setOnClickListener(shopShareCardViewHolder);
        this.mShopName.setOnClickListener(shopShareCardViewHolder);
        if (shouldShowMenuIcon(shopShareCard)) {
            this.mMenuIcon.setVisibility(0);
        } else {
            this.mMenuIcon.setVisibility(8);
        }
        this.mShopName.setText(shopShareCard.getShopDisplayName());
        if (this.mTagLabel != null) {
            this.mTagLabel.setText(R.tag_label);
        }
        if (this.mShareListing != null) {
            ListingCardViewHolder listingCardViewHolder = (ListingCardViewHolder) this.mShareListing.getTag();
            listingCardViewHolder.bind((ListingCard) shareItem.getPrimaryAnnotation().getObject(), true, shopShareCard.isObjectOwnedByUser(SharedPreferencesUtility.m3152k(this.mAdapter.getContext())));
            View view = listingCardViewHolder.itemView;
            view.setLayoutParams(new FrameLayout.LayoutParams(-1, -2));
            ((CardView) view).setCardElevation(0.0f);
            if (view.getParent() != null) {
                ((ViewGroup) view.getParent()).removeView(view);
            }
            this.mShareListing.addView(view);
            ViewTreeObserverHelper.m5636a(this.mImageView.getViewTreeObserver(), new C05722(this));
            this.mCaptionScrollView.setOnTouchListener(new C05733(this));
        }
    }

    public void recycle() {
        if (this.mShareDetails != null) {
            this.mCaption.setText(null);
            ViewGroup.LayoutParams layoutParams = new LayoutParams(this.mShareDetails.getMeasuredWidth(), 0, FullImageView.ASPECT_RATIO_SQUARE);
            layoutParams.setMargins(0, 0, (int) this.mImageView.getResources().getDimension(R.margin_large), 0);
            this.mCaptionScrollView.setLayoutParams(layoutParams);
        }
    }

    private boolean isOwnedByUser(ShopShareCard shopShareCard) {
        EtsyId ownerId = shopShareCard.getOwnerId();
        return ownerId != null && ownerId.getId().equals(SharedPreferencesUtility.m3152k(this.mAdapter.getContext()).getId());
    }

    private boolean shouldShowMenuIcon(ShopShareCard shopShareCard) {
        EtsyApplication etsyApplication = (EtsyApplication) this.mContext.getApplicationContext();
        return this.mShowMenuIcon && (!isOwnedByUser(shopShareCard) || (etsyApplication.isSOEInstalled() && aq.m1144a(MANAGE_POST_MINIMUM_SOE_VERSION).m1145a(aq.m1144a(etsyApplication.getSOEInstalledVersion())) <= 0));
    }

    private PopupWindow getManageShopSharePopup(ShopShareCard shopShareCard) {
        View inflate = this.mInflater.inflate(2130903450, null);
        PopupWindow a = ax.m3278a(inflate);
        if (isOwnedByUser(shopShareCard)) {
            View findViewById = inflate.findViewById(2131756358);
            View findViewById2 = inflate.findViewById(2131756359);
            View findViewById3 = inflate.findViewById(2131756360);
            findViewById.setVisibility(0);
            findViewById2.setVisibility(8);
            findViewById3.setVisibility(8);
            inflate.findViewById(2131756358).setOnClickListener(new C05744(this, shopShareCard, a));
        } else {
            inflate.findViewById(2131756359).setOnClickListener(new C05755(this, shopShareCard, a));
            inflate.findViewById(2131756360).setOnClickListener(new C05766(this, shopShareCard, a));
        }
        return a;
    }
}
