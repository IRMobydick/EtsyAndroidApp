package com.appboy.ui.widget;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.appboy.Constants;
import com.appboy.models.cards.ShortNewsCard;
import com.appboy.ui.C0401R;
import com.appboy.ui.actions.ActionFactory;
import com.appboy.ui.actions.IAction;
import com.etsy.android.uikit.view.FullImageView;
import com.facebook.drawee.view.SimpleDraweeView;

public class ShortNewsCardView extends BaseCardView<ShortNewsCard> {
    private static final String TAG;
    private final float mAspectRatio;
    private IAction mCardAction;
    private final TextView mDescription;
    private final TextView mDomain;
    private SimpleDraweeView mDrawee;
    private ImageView mImage;
    private final TextView mTitle;

    /* renamed from: com.appboy.ui.widget.ShortNewsCardView.1 */
    class C04331 implements OnClickListener {
        final /* synthetic */ ShortNewsCard val$card;

        C04331(ShortNewsCard shortNewsCard) {
            this.val$card = shortNewsCard;
        }

        public void onClick(View view) {
            BaseCardView.handleCardClick(ShortNewsCardView.this.mContext, this.val$card, ShortNewsCardView.this.mCardAction, ShortNewsCardView.TAG);
        }
    }

    static {
        TAG = String.format("%s.%s", new Object[]{Constants.APPBOY, ShortNewsCardView.class.getName()});
    }

    public ShortNewsCardView(Context context) {
        this(context, null);
    }

    public ShortNewsCardView(Context context, ShortNewsCard shortNewsCard) {
        super(context);
        this.mAspectRatio = FullImageView.ASPECT_RATIO_SQUARE;
        this.mDescription = (TextView) findViewById(C0401R.id.com_appboy_short_news_card_description);
        this.mTitle = (TextView) findViewById(C0401R.id.com_appboy_short_news_card_title);
        this.mDomain = (TextView) findViewById(C0401R.id.com_appboy_short_news_card_domain);
        if (canUseFresco()) {
            this.mDrawee = (SimpleDraweeView) getProperViewFromInflatedStub(C0401R.id.com_appboy_short_news_card_drawee_stub);
        } else {
            this.mImage = (ImageView) getProperViewFromInflatedStub(C0401R.id.com_appboy_short_news_card_imageview_stub);
        }
        if (shortNewsCard != null) {
            setCard(shortNewsCard);
        }
        safeSetBackground(getResources().getDrawable(C0401R.drawable.com_appboy_card_background));
    }

    protected int getLayoutResource() {
        return C0401R.layout.com_appboy_short_news_card;
    }

    public void onSetCard(ShortNewsCard shortNewsCard) {
        this.mDescription.setText(shortNewsCard.getDescription());
        setOptionalTextView(this.mTitle, shortNewsCard.getTitle());
        setOptionalTextView(this.mDomain, shortNewsCard.getDomain());
        this.mCardAction = ActionFactory.createUriAction(getContext(), shortNewsCard.getUrl());
        setOnClickListener(new C04331(shortNewsCard));
        if (canUseFresco()) {
            setSimpleDraweeToUrl(this.mDrawee, shortNewsCard.getImageUrl(), FullImageView.ASPECT_RATIO_SQUARE, true);
        } else {
            setImageViewToUrl(this.mImage, shortNewsCard.getImageUrl(), FullImageView.ASPECT_RATIO_SQUARE);
        }
    }
}
