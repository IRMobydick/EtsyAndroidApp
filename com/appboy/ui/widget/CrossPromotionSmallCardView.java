package com.appboy.ui.widget;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.appboy.Constants;
import com.appboy.models.cards.CrossPromotionSmallCard;
import com.appboy.ui.C0401R;
import com.appboy.ui.actions.GooglePlayAppDetailsAction;
import com.appboy.ui.actions.IAction;
import com.appboy.ui.support.StringUtils;
import com.etsy.android.uikit.view.FullImageView;
import com.facebook.drawee.view.SimpleDraweeView;
import java.text.NumberFormat;
import java.util.Locale;

public class CrossPromotionSmallCardView extends BaseCardView<CrossPromotionSmallCard> {
    private static final String TAG;
    private final float mAspectRatio;
    private final TextView mCaption;
    private SimpleDraweeView mDrawee;
    private ImageView mImage;
    private final Button mPrice;
    private IAction mPriceAction;
    private final TextView mReviewCount;
    private final StarRatingView mStarRating;
    private final TextView mSubtitle;
    private final TextView mTitle;

    /* renamed from: com.appboy.ui.widget.CrossPromotionSmallCardView.1 */
    class C04321 implements OnClickListener {
        final /* synthetic */ CrossPromotionSmallCard val$card;

        C04321(CrossPromotionSmallCard crossPromotionSmallCard) {
            this.val$card = crossPromotionSmallCard;
        }

        public void onClick(View view) {
            BaseCardView.handleCardClick(CrossPromotionSmallCardView.this.mContext, this.val$card, CrossPromotionSmallCardView.this.mPriceAction, CrossPromotionSmallCardView.TAG);
        }
    }

    static {
        TAG = String.format("%s.%s", new Object[]{Constants.APPBOY, CrossPromotionSmallCardView.class.getName()});
    }

    public CrossPromotionSmallCardView(Context context) {
        this(context, null);
    }

    public CrossPromotionSmallCardView(Context context, CrossPromotionSmallCard crossPromotionSmallCard) {
        super(context);
        this.mAspectRatio = FullImageView.ASPECT_RATIO_SQUARE;
        this.mTitle = (TextView) findViewById(C0401R.id.com_appboy_cross_promotion_small_card_title);
        this.mSubtitle = (TextView) findViewById(C0401R.id.com_appboy_cross_promotion_small_card_subtitle);
        this.mReviewCount = (TextView) findViewById(C0401R.id.com_appboy_cross_promotion_small_card_review_count);
        this.mCaption = (TextView) findViewById(C0401R.id.com_appboy_cross_promotion_small_card_recommendation_tab);
        this.mStarRating = (StarRatingView) findViewById(C0401R.id.com_appboy_cross_promotion_small_card_star_rating);
        this.mPrice = (Button) findViewById(C0401R.id.com_appboy_cross_promotion_small_card_price);
        if (canUseFresco()) {
            this.mDrawee = (SimpleDraweeView) getProperViewFromInflatedStub(C0401R.id.com_appboy_cross_promotion_small_card_drawee_stub);
        } else {
            this.mImage = (ImageView) getProperViewFromInflatedStub(C0401R.id.com_appboy_cross_promotion_small_card_imageview_stub);
        }
        if (crossPromotionSmallCard != null) {
            setCard(crossPromotionSmallCard);
        }
    }

    protected int getLayoutResource() {
        return C0401R.layout.com_appboy_cross_promotion_small_card;
    }

    public void onSetCard(CrossPromotionSmallCard crossPromotionSmallCard) {
        this.mTitle.setText(crossPromotionSmallCard.getTitle());
        if (crossPromotionSmallCard.getSubtitle() == null || crossPromotionSmallCard.getSubtitle().toUpperCase(Locale.getDefault()).equals("NULL")) {
            this.mSubtitle.setVisibility(8);
        } else {
            this.mSubtitle.setText(crossPromotionSmallCard.getSubtitle().toUpperCase(Locale.getDefault()));
        }
        this.mCaption.setText(crossPromotionSmallCard.getCaption().toUpperCase(Locale.getDefault()));
        if (crossPromotionSmallCard.getRating() <= 0.0d) {
            this.mReviewCount.setVisibility(8);
            this.mStarRating.setVisibility(8);
        } else {
            this.mReviewCount.setText(String.format("(%s)", new Object[]{NumberFormat.getInstance().format((long) crossPromotionSmallCard.getReviewCount())}));
            this.mStarRating.setRating((float) crossPromotionSmallCard.getRating());
        }
        if (StringUtils.isNullOrBlank(crossPromotionSmallCard.getDisplayPrice())) {
            this.mPrice.setText(getPriceString(crossPromotionSmallCard.getPrice()));
        } else {
            this.mPrice.setText(crossPromotionSmallCard.getDisplayPrice());
        }
        this.mPriceAction = new GooglePlayAppDetailsAction(crossPromotionSmallCard.getPackage(), false, crossPromotionSmallCard.getAppStore(), crossPromotionSmallCard.getKindleId());
        this.mPrice.setOnClickListener(new C04321(crossPromotionSmallCard));
        if (canUseFresco()) {
            setSimpleDraweeToUrl(this.mDrawee, crossPromotionSmallCard.getImageUrl(), FullImageView.ASPECT_RATIO_SQUARE, true);
        } else {
            setImageViewToUrl(this.mImage, crossPromotionSmallCard.getImageUrl(), FullImageView.ASPECT_RATIO_SQUARE);
        }
    }

    private String getPriceString(double d) {
        if (d == 0.0d) {
            return this.mContext.getString(C0401R.string.com_appboy_recommendation_free);
        }
        return NumberFormat.getCurrencyInstance(Locale.US).format(d);
    }
}
