package com.appboy.ui.widget;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.appboy.Constants;
import com.appboy.models.cards.BannerImageCard;
import com.appboy.support.AppboyLogger;
import com.appboy.ui.C0401R;
import com.appboy.ui.actions.ActionFactory;
import com.appboy.ui.actions.IAction;
import com.facebook.drawee.view.SimpleDraweeView;

public class BannerImageCardView extends BaseCardView<BannerImageCard> {
    private static final String TAG;
    private float mAspectRatio;
    private IAction mCardAction;
    private SimpleDraweeView mDrawee;
    private ImageView mImage;

    /* renamed from: com.appboy.ui.widget.BannerImageCardView.1 */
    class C04281 implements OnClickListener {
        final /* synthetic */ BannerImageCard val$card;

        C04281(BannerImageCard bannerImageCard) {
            this.val$card = bannerImageCard;
        }

        public void onClick(View view) {
            if (BannerImageCardView.this.mCardAction != null) {
                AppboyLogger.m662d(BannerImageCardView.TAG, String.format("Logged click for card %s", new Object[]{this.val$card.getId()}));
                this.val$card.logClick();
                BannerImageCardView.this.mCardAction.execute(BannerImageCardView.this.mContext);
            }
        }
    }

    static {
        TAG = String.format("%s.%s", new Object[]{Constants.APPBOY, BannerImageCardView.class.getName()});
    }

    public BannerImageCardView(Context context) {
        this(context, null);
    }

    public BannerImageCardView(Context context, BannerImageCard bannerImageCard) {
        super(context);
        this.mAspectRatio = 6.0f;
        if (canUseFresco()) {
            this.mDrawee = (SimpleDraweeView) getProperViewFromInflatedStub(C0401R.id.com_appboy_banner_image_card_drawee_stub);
        } else {
            this.mImage = (ImageView) getProperViewFromInflatedStub(C0401R.id.com_appboy_banner_image_card_imageview_stub);
            this.mImage.setScaleType(ScaleType.CENTER_CROP);
            this.mImage.setAdjustViewBounds(true);
        }
        if (bannerImageCard != null) {
            setCard(bannerImageCard);
        }
        safeSetBackground(getResources().getDrawable(C0401R.drawable.com_appboy_card_background));
    }

    protected int getLayoutResource() {
        return C0401R.layout.com_appboy_banner_image_card;
    }

    public void onSetCard(BannerImageCard bannerImageCard) {
        boolean z = false;
        if (bannerImageCard.getAspectRatio() != 0.0f) {
            this.mAspectRatio = bannerImageCard.getAspectRatio();
            z = true;
        }
        if (canUseFresco()) {
            setSimpleDraweeToUrl(this.mDrawee, bannerImageCard.getImageUrl(), this.mAspectRatio, z);
        } else {
            setImageViewToUrl(this.mImage, bannerImageCard.getImageUrl(), this.mAspectRatio, z);
        }
        this.mCardAction = ActionFactory.createUriAction(getContext(), bannerImageCard.getUrl());
        setOnClickListener(new C04281(bannerImageCard));
    }
}
