package com.etsy.android.ui.view.viewholders;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableLayout.LayoutParams;
import android.widget.TableRow;
import android.widget.TextView;
import com.etsy.android.iconsy.views.IconDrawable;
import com.etsy.android.iconsy.views.IconView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.img.ImageBatch;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.logger.ITrackedObject;
import com.etsy.android.lib.models.IFullImage;
import com.etsy.android.lib.models.apiv3.Collection;
import com.etsy.android.lib.models.apiv3.ShopCard;
import com.etsy.android.lib.models.apiv3.ShopIcon;
import com.etsy.android.lib.models.apiv3.TreasuryV3;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.interfaces.ListingLike;
import com.etsy.android.lib.util.bh;
import com.etsy.android.lib.util.fonts.EtsyFontIcons;
import com.etsy.android.lib.util.fonts.StandardFontIcon;
import com.etsy.android.ui.nav.Nav;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.view.FullImageView;
import com.etsy.android.uikit.view.ListingFullImageView;
import com.etsy.android.uikit.view.RatingIconView;
import java.util.ArrayList;
import java.util.List;

public class ProfileCardViewHolder extends ViewHolder {
    private static final int IMAGES_PER_GRID_ROW = 2;
    private final ImageView mAvatar;
    private final int mAvatarCornerRadius;
    private final int mAvatarSize;
    private final View mClickRegion;
    private final ImageBatch mImageBatch;
    private final ViewGroup mImageLayout;
    private List<ListingFullImageView> mImages;
    private final boolean mIsGrid;
    private final Drawable mLocalEventLocationIcon;
    private final Drawable mLocationIcon;
    private final int mNumItems;
    private final RatingIconView mRating;
    private final TextView mRatingCount;
    private final TextView mRatingTitle;
    private final TextView mSubTitle;
    private final TextView mTitle;
    private final IconView mTitleIcon;

    /* renamed from: com.etsy.android.ui.view.viewholders.ProfileCardViewHolder.1 */
    class C09111 extends TrackingOnClickListener {
        final /* synthetic */ Collection f3847a;
        final /* synthetic */ ProfileCardViewHolder f3848b;

        C09111(ProfileCardViewHolder profileCardViewHolder, ITrackedObject[] iTrackedObjectArr, Collection collection) {
            this.f3848b = profileCardViewHolder;
            this.f3847a = collection;
            super(iTrackedObjectArr);
        }

        public void onViewClick(View view) {
            Nav.m4682a((FragmentActivity) view.getContext()).m4683a().m4464a(this.f3847a);
        }
    }

    /* renamed from: com.etsy.android.ui.view.viewholders.ProfileCardViewHolder.2 */
    class C09122 extends TrackingOnClickListener {
        final /* synthetic */ ShopCard f3849a;
        final /* synthetic */ ProfileCardViewHolder f3850b;

        C09122(ProfileCardViewHolder profileCardViewHolder, AnalyticsLogAttribute analyticsLogAttribute, EtsyId etsyId, ShopCard shopCard) {
            this.f3850b = profileCardViewHolder;
            this.f3849a = shopCard;
            super(analyticsLogAttribute, etsyId);
        }

        public void onViewClick(View view) {
            if (this.f3849a.getUserId() == null || !this.f3849a.getUserId().hasId()) {
                Nav.m4682a((FragmentActivity) view.getContext()).m4683a().m4501b(this.f3849a.getShopId());
            } else {
                Nav.m4682a((FragmentActivity) view.getContext()).m4683a().m4473a(this.f3849a.getShopId(), this.f3849a.getUserId());
            }
        }
    }

    /* renamed from: com.etsy.android.ui.view.viewholders.ProfileCardViewHolder.3 */
    class C09133 extends TrackingOnClickListener {
        final /* synthetic */ TreasuryV3 f3851a;
        final /* synthetic */ ProfileCardViewHolder f3852b;

        C09133(ProfileCardViewHolder profileCardViewHolder, ITrackedObject[] iTrackedObjectArr, TreasuryV3 treasuryV3) {
            this.f3852b = profileCardViewHolder;
            this.f3851a = treasuryV3;
            super(iTrackedObjectArr);
        }

        public void onViewClick(View view) {
            Nav.m4682a((FragmentActivity) view.getContext()).m4683a().m4506b(this.f3851a.getId());
        }
    }

    public ProfileCardViewHolder(View view, ImageBatch imageBatch, int i, boolean z) {
        super(view);
        this.mImages = new ArrayList(0);
        this.mImageBatch = imageBatch;
        this.mNumItems = i;
        this.mClickRegion = view.findViewById(R.click_region);
        this.mTitle = (TextView) view.findViewById(R.title);
        this.mTitleIcon = (IconView) view.findViewById(R.title_icon);
        this.mSubTitle = (TextView) view.findViewById(R.subtitle);
        this.mRating = (RatingIconView) view.findViewById(R.rating);
        this.mRatingCount = (TextView) view.findViewById(R.rating_count);
        this.mRatingTitle = (TextView) view.findViewById(R.rating_title);
        this.mImageLayout = (ViewGroup) view.findViewById(R.image_layout);
        this.mAvatar = (ImageView) view.findViewById(R.avatar);
        Resources resources = view.getResources();
        this.mAvatarSize = resources.getDimensionPixelOffset(R.card_avatar_small);
        this.mAvatarCornerRadius = resources.getDimensionPixelOffset(R.gen_avatar_corners_small);
        IconDrawable a = IconDrawable.m775a(resources).m780a(EtsyFontIcons.LOCATION).m779a(resources.getColor(R.light_grey)).m778a((float) resources.getDimensionPixelSize(R.text_medium));
        this.mLocationIcon = a.m777a();
        a.m779a(resources.getColor(R.blue));
        this.mLocalEventLocationIcon = a.m777a();
        if (this.mImageLayout instanceof TableLayout) {
            this.mImages = configureImageGridLayout((TableLayout) this.mImageLayout, this.mNumItems);
        } else if (this.mImageLayout instanceof LinearLayout) {
            this.mImages = configureImageRowLayout((LinearLayout) this.mImageLayout, this.mNumItems);
        }
        this.mIsGrid = z;
    }

    private static List<ListingFullImageView> configureImageRowLayout(LinearLayout linearLayout, int i) {
        Context context = linearLayout.getContext();
        List<ListingFullImageView> arrayList = new ArrayList(i);
        for (int i2 = 0; i2 < i; i2++) {
            View freshListingImageView = freshListingImageView(context, false);
            linearLayout.addView(freshListingImageView);
            arrayList.add(freshListingImageView);
        }
        return arrayList;
    }

    private static List<ListingFullImageView> configureImageGridLayout(TableLayout tableLayout, int i) {
        Context context = tableLayout.getContext();
        int i2 = (i + 1) / IMAGES_PER_GRID_ROW;
        List<ListingFullImageView> arrayList = new ArrayList(i);
        for (int i3 = 0; i3 < i2; i3++) {
            View tableRow = new TableRow(context);
            View freshListingImageView = freshListingImageView(context, true);
            tableRow.addView(freshListingImageView);
            arrayList.add(freshListingImageView);
            freshListingImageView = freshListingImageView(context, true);
            tableRow.addView(freshListingImageView);
            tableLayout.addView(tableRow, new LayoutParams(-1, -2, FullImageView.ASPECT_RATIO_SQUARE));
            arrayList.add(freshListingImageView);
        }
        return arrayList;
    }

    private static ListingFullImageView freshListingImageView(Context context, boolean z) {
        ListingFullImageView listingFullImageView = new ListingFullImageView(context);
        listingFullImageView.setScaleType(ScaleType.CENTER_CROP);
        listingFullImageView.setUseStandardRatio(true);
        if (z) {
            listingFullImageView.setLayoutParams(new TableRow.LayoutParams(0, 0, FullImageView.ASPECT_RATIO_SQUARE));
        } else {
            listingFullImageView.setLayoutParams(new LinearLayout.LayoutParams(0, 0, FullImageView.ASPECT_RATIO_SQUARE));
        }
        return listingFullImageView;
    }

    private void setListingImageItems(List<? extends ListingLike> list) {
        for (int i = 0; i < this.mNumItems; i++) {
            IFullImage listingImage;
            ListingFullImageView listingFullImageView = (ListingFullImageView) this.mImages.get(i);
            if (list.size() > i) {
                listingImage = ((ListingLike) list.get(i)).getListingImage();
            } else {
                listingImage = null;
            }
            if (listingImage != null) {
                listingFullImageView.setImageInfo(listingImage, this.mImageBatch);
            } else if (i == this.mNumItems - 1 || this.mIsGrid) {
                listingFullImageView.setImageDrawable(null);
                listingFullImageView.setBackgroundResource(R.bg_empty_image);
            } else {
                listingFullImageView.setImageDrawable(null);
                listingFullImageView.setBackgroundResource(R.bg_empty_image_right_divider);
            }
        }
    }

    public void bind(Collection collection) {
        this.mTitle.setText(collection.getName());
        this.mRatingTitle.setVisibility(8);
        this.mRatingCount.setVisibility(8);
        this.mRating.setVisibility(8);
        if (collection.isPrivate()) {
            this.mTitleIcon.setIcon(StandardFontIcon.PRIVATE);
            this.mTitleIcon.setVisibility(0);
        } else {
            this.mTitleIcon.setVisibility(8);
        }
        OnClickListener c09111 = new C09111(this, new ITrackedObject[]{collection}, collection);
        int listingsCount = collection.getListingsCount();
        this.mSubTitle.setText(this.itemView.getContext().getResources().getQuantityString(R.item_titlecase_quantity, listingsCount, new Object[]{bh.m3333a((double) listingsCount)}));
        this.mClickRegion.setOnClickListener(c09111);
        if (this.mNumItems > 0) {
            setListingImageItems(collection.getRepresentativeListings());
        }
    }

    public void bind(ShopCard shopCard) {
        String iconUrl;
        this.mTitle.setText(shopCard.getShopName());
        this.mClickRegion.setOnClickListener(new C09122(this, AnalyticsLogAttribute.SHOP_ID, shopCard.getShopId(), shopCard));
        if (this.mNumItems > 0) {
            setListingImageItems(shopCard.getCardListings());
        }
        this.mAvatar.setVisibility(0);
        if (bh.m3340a(shopCard.getIconUrl(((Integer) ShopIcon.IMG_SIZE_75.first).intValue()))) {
            iconUrl = shopCard.getIconUrl(((Integer) ShopIcon.IMG_SIZE_75.first).intValue());
        } else {
            iconUrl = shopCard.getAvatarUrl();
        }
        this.mImageBatch.m1576b(iconUrl, this.mAvatar, this.mAvatarCornerRadius, this.mAvatarSize, this.mAvatarSize);
        this.mSubTitle.setVisibility(8);
        if (!shopCard.hasRatings() || shopCard.getAverageRating() <= 0.0d) {
            this.mRating.setVisibility(8);
            this.mRatingCount.setVisibility(8);
            this.mRatingTitle.setVisibility(8);
            return;
        }
        this.mRatingCount.setVisibility(0);
        this.mRating.setVisibility(0);
        this.mRating.setRating((float) shopCard.getAverageRating());
        this.mRatingCount.setText("(" + bh.m3333a((double) shopCard.getNumRatings()) + ")");
        this.mRatingTitle.setVisibility(0);
    }

    public void bind(TreasuryV3 treasuryV3) {
        this.mTitle.setText(treasuryV3.getTitle());
        this.mRatingTitle.setVisibility(8);
        this.mRatingCount.setVisibility(8);
        this.mRating.setVisibility(8);
        OnClickListener c09133 = new C09133(this, new ITrackedObject[]{treasuryV3}, treasuryV3);
        this.mSubTitle.setVisibility(0);
        int listingsCount = treasuryV3.getListingsCount();
        this.mSubTitle.setText(this.itemView.getContext().getResources().getQuantityString(R.item_titlecase_quantity, listingsCount, new Object[]{bh.m3333a((double) listingsCount)}));
        this.mClickRegion.setOnClickListener(c09133);
        if (this.mNumItems > 0) {
            setListingImageItems(treasuryV3.getListings());
        }
    }

    public void recycle() {
        this.mSubTitle.setVisibility(0);
        this.mAvatar.setVisibility(8);
        this.mRatingTitle.setVisibility(8);
        this.mRatingCount.setVisibility(8);
        this.mRating.setVisibility(8);
        int size = this.mImages.size();
        for (int i = 0; i < size; i++) {
            ((ListingFullImageView) this.mImages.get(i)).cleanUp();
        }
    }
}
