package com.etsy.android.ui.core.listingpanel;

import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.EtsyJobBuilder;
import com.etsy.android.lib.core.EtsyJobResponse;
import com.etsy.android.lib.core.EtsyResult;
import com.etsy.android.lib.core.img.ImageBatch;
import com.etsy.android.lib.logger.ITrackedObject;
import com.etsy.android.lib.logger.ad;
import com.etsy.android.lib.models.Listing;
import com.etsy.android.lib.models.ReceiptReview;
import com.etsy.android.lib.models.Review;
import com.etsy.android.lib.models.Shop;
import com.etsy.android.lib.models.apiv3.TranslatedReview;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.requests.apiv3.TranslatedReviewRequest;
import com.etsy.android.lib.util.aj;
import com.etsy.android.lib.util.bh;
import com.etsy.android.ui.nav.EtsyEventTracker;
import com.etsy.android.ui.nav.Nav;
import com.etsy.android.uikit.BaseActivity;
import com.etsy.android.uikit.adapter.ReviewAdapter;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.util.ViewTreeObserverHelper;
import com.etsy.android.uikit.view.RatingIconView;
import com.etsy.android.uikit.viewholder.ReviewViewHolder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* renamed from: com.etsy.android.ui.core.listingpanel.e */
public class ListingPanelFeedback extends ListingPanelBase implements ReviewViewHolder {
    protected ImageBatch f2841k;
    private TextView f2842l;
    private RatingIconView f2843m;
    private ReviewAdapter f2844n;
    private ArrayList<ReviewViewHolder> f2845o;

    /* renamed from: com.etsy.android.ui.core.listingpanel.e.1 */
    class ListingPanelFeedback extends TrackingOnClickListener {
        final /* synthetic */ Shop f2826a;
        final /* synthetic */ ListingPanelFeedback f2827b;

        ListingPanelFeedback(ListingPanelFeedback listingPanelFeedback, ITrackedObject[] iTrackedObjectArr, Shop shop) {
            this.f2827b = listingPanelFeedback;
            this.f2826a = shop;
            super(iTrackedObjectArr);
        }

        public void onViewClick(View view) {
            Nav.m4681a(this.f2827b.a).m4529g().m4461a(this.f2826a);
        }
    }

    /* renamed from: com.etsy.android.ui.core.listingpanel.e.2 */
    class ListingPanelFeedback implements OnGlobalLayoutListener {
        final /* synthetic */ View f2828a;
        final /* synthetic */ int f2829b;
        final /* synthetic */ ListingPanelFeedback f2830c;

        ListingPanelFeedback(ListingPanelFeedback listingPanelFeedback, View view, int i) {
            this.f2830c = listingPanelFeedback;
            this.f2828a = view;
            this.f2829b = i;
        }

        public void onGlobalLayout() {
            LayoutParams layoutParams = (LayoutParams) this.f2830c.d.getLayoutParams();
            int measuredHeight = this.f2828a.getMeasuredHeight();
            layoutParams.height = (measuredHeight - this.f2829b) + layoutParams.height;
            this.f2830c.m4024a(layoutParams.height);
            this.f2830c.d.requestLayout();
            ViewTreeObserverHelper.m5639b(this.f2828a.getViewTreeObserver(), (OnGlobalLayoutListener) this);
        }
    }

    /* renamed from: com.etsy.android.ui.core.listingpanel.e.3 */
    class ListingPanelFeedback implements EtsyJobResponse {
        final /* synthetic */ View f2831a;
        final /* synthetic */ int f2832b;
        final /* synthetic */ Review f2833c;
        final /* synthetic */ ListingPanelFeedback f2834d;

        ListingPanelFeedback(ListingPanelFeedback listingPanelFeedback, View view, int i, Review review) {
            this.f2834d = listingPanelFeedback;
            this.f2831a = view;
            this.f2832b = i;
            this.f2833c = review;
        }

        public void m4055a(int i, String str, EtsyResult etsyResult) {
            this.f2834d.m4065b(this.f2831a);
            this.f2834d.f2844n.onTranslationError(this.f2832b);
            this.f2834d.f2844n.bindMachineTranslationArea((ReviewViewHolder) this.f2834d.f2845o.get(this.f2832b), this.f2833c, this.f2832b);
        }
    }

    /* renamed from: com.etsy.android.ui.core.listingpanel.e.4 */
    class ListingPanelFeedback implements EtsyJobResponse<TranslatedReview> {
        final /* synthetic */ View f2835a;
        final /* synthetic */ int f2836b;
        final /* synthetic */ Review f2837c;
        final /* synthetic */ ListingPanelFeedback f2838d;

        ListingPanelFeedback(ListingPanelFeedback listingPanelFeedback, View view, int i, Review review) {
            this.f2838d = listingPanelFeedback;
            this.f2835a = view;
            this.f2836b = i;
            this.f2837c = review;
        }

        public void m4056a(List<TranslatedReview> list, int i, EtsyResult<TranslatedReview> etsyResult) {
            this.f2838d.m4065b(this.f2835a);
            this.f2838d.f2844n.onTranslationSuccess(this.f2836b);
            this.f2837c.setTranslatedReviewMessage(((TranslatedReview) list.get(0)).getTranslatedReview());
            this.f2838d.f2844n.bindMachineTranslationArea((ReviewViewHolder) this.f2838d.f2845o.get(this.f2836b), this.f2837c, this.f2836b);
        }
    }

    /* renamed from: com.etsy.android.ui.core.listingpanel.e.5 */
    class ListingPanelFeedback implements EtsyJobBuilder {
        final /* synthetic */ int f2839a;
        final /* synthetic */ ListingPanelFeedback f2840b;

        ListingPanelFeedback(ListingPanelFeedback listingPanelFeedback, int i) {
            this.f2840b = listingPanelFeedback;
            this.f2839a = i;
        }

        public void m4057a() {
            this.f2840b.f2844n.onTranslationLoading(this.f2839a);
            this.f2840b.f2844n.onBindViewHolder((ViewHolder) this.f2840b.f2845o.get(this.f2839a), this.f2839a);
        }
    }

    public ListingPanelFeedback(Listing listing, BaseActivity baseActivity, @NonNull ad adVar) {
        super(listing, baseActivity, adVar);
        this.f2845o = new ArrayList();
        m4025a(2131756123, 2131756119, 2131756120, 2131756119);
    }

    public void m4063a(ImageBatch imageBatch) {
        this.f2841k = imageBatch;
    }

    protected void m4066c() {
        if (this.j) {
            this.c.findViewById(2131756121).setVisibility(8);
        }
        m4060p();
        m4068o();
    }

    private void m4060p() {
        this.f2843m = (RatingIconView) this.c.findViewById(R.rating);
        if (this.j) {
            this.c.findViewById(2131756121).setVisibility(8);
            this.f2842l = (TextView) this.c.findViewById(2131756155);
            return;
        }
        this.f2842l = (TextView) this.c.findViewById(2131756121);
    }

    protected void m4068o() {
        Shop shop = this.b.getShop();
        if (shop == null || shop.getReceiptReviews().size() <= 0) {
            this.e.setVisibility(8);
            this.d.setVisibility(8);
            this.f2842l.setVisibility(8);
            return;
        }
        this.f2842l.setVisibility(0);
        this.f2842l.setText(this.j ? "(" + bh.m3333a((double) shop.getNumRatings()) + ")" : "(" + bh.m3333a((double) shop.getNumRatings()) + " " + this.c.getResources().getString(R.reviews) + ")");
        if (shop.getAverageRating() > 0.0d) {
            this.f2843m.setRating((float) shop.getAverageRating());
        } else {
            CharSequence quantityString = this.c.getResources().getQuantityString(R.reviews_plurals_no_brackets, shop.getNumRatings(), new Object[]{bh.m3333a((double) shop.getNumRatings())});
            this.f2843m.setVisibility(8);
            this.f2842l.setText(quantityString);
        }
        Collection receiptReviews = shop.getReceiptReviews();
        this.f2844n = new ReviewAdapter(this.a, this.f2841k);
        this.f2844n.setShopOwnerName(bh.m3334a(this.b.getShop().getUser()));
        this.f2844n.setSellerAvatarUrl(shop.getAvatarUrl());
        this.f2844n.addItems(receiptReviews);
        this.f2844n.setOnClickListener(this);
        this.f2844n.setMachineTranslationEnabled(aj.m3237d());
        int i = 0;
        while (i < this.f2844n.getDataItemCount() && i < 3) {
            ReviewViewHolder onCreateListItemViewHolder = this.f2844n.onCreateListItemViewHolder(this.d, (int) ReviewAdapter.LIST_ITEM_WITH_APP_PHOTO);
            this.f2844n.onBindViewHolder(onCreateListItemViewHolder, i);
            this.f2845o.add(onCreateListItemViewHolder);
            this.d.addView(onCreateListItemViewHolder.itemView);
            if (i != this.f2844n.getDataItemCount() - 1) {
                this.d.addView(m4061q());
            }
            i++;
        }
        if (shop.getNumRatings() > 3) {
            this.d.addView(m4061q());
            this.d.addView(m4062r());
        }
        this.d.requestLayout();
    }

    private View m4061q() {
        return this.a.getLayoutInflater().inflate(2130903465, null);
    }

    private View m4062r() {
        Shop shop = this.b.getShop();
        TextView textView = (TextView) LayoutInflater.from(this.a).inflate(2130903369, null);
        if (shop == null) {
            textView.setVisibility(8);
        } else {
            int numRatings = shop.getNumRatings();
            textView.setText(this.c.getResources().getString(R.see_all_reviews, new Object[]{Integer.valueOf(numRatings)}));
            textView.setOnClickListener(new ListingPanelFeedback(this, new ITrackedObject[]{this.b, shop}, shop));
        }
        return textView;
    }

    public void m4065b(View view) {
        if (!this.j) {
            view.getViewTreeObserver().addOnGlobalLayoutListener(new ListingPanelFeedback(this, view, view.getMeasuredHeight()));
        }
    }

    protected void m4067i() {
        EtsyEventTracker.m4583g();
    }

    public void onAppreciationPhotoClicked(int i) {
        ReceiptReview receiptReview = (ReceiptReview) this.f2844n.getItem(i);
        if (receiptReview != null) {
            EtsyId transactionId = ((Review) receiptReview.getReviews().get(0)).getTransactionId();
            EtsyEventTracker.m4591h(transactionId, "view_listing");
            Nav.m4682a(this.a).m4683a().m4526f(transactionId);
        }
    }

    public void onUnhidePhotoClicked(int i) {
    }

    public void onContactBuyerClicked(int i) {
    }

    public void onListingClicked(int i) {
        if (this.f2844n != null) {
            ReceiptReview receiptReview = (ReceiptReview) this.f2844n.getItem(i);
            if (receiptReview != null) {
                Nav.m4682a(this.a).m4683a().m4467a(((Review) receiptReview.getReviews().get(0)).getListingId());
            }
        }
    }

    public void onShopOwnerClicked(int i) {
    }

    public void onUserClicked(int i) {
        if (this.f2844n != null) {
            ReceiptReview receiptReview = (ReceiptReview) this.f2844n.getItem(i);
            if (receiptReview != null) {
                Nav.m4682a(this.a).m4683a().m4511c(receiptReview.getUserId());
            }
        }
    }

    public void onTranslateReviewClicked(int i) {
        Review review = (Review) ((ReceiptReview) this.f2844n.getItem(i)).getReviews().get(0);
        Shop shop = this.b.getShop();
        if (shop != null) {
            String c = aj.m3235c();
            View view = ((ReviewViewHolder) this.f2845o.get(i)).mMachineTranslationView;
            m4044m().m1697a((Object) this, EtsyJobBuilder.m1307a(TranslatedReviewRequest.getTranslatedReview(shop.getShopId(), review.getTransactionId(), c)).m1318a(new ListingPanelFeedback(this, i)).m1321a(new ListingPanelFeedback(this, view, i, review)).m1320a(new ListingPanelFeedback(this, view, i, review)).m1324a());
        }
    }

    @CallSuper
    public void m4064b() {
        super.m4033b();
        this.f2845o.clear();
    }
}
