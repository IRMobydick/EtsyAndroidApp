package com.appboy.ui.widget;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import com.appboy.Constants;
import com.appboy.models.cards.CaptionedImageCard;
import com.appboy.ui.C0401R;
import com.appboy.ui.actions.ActionFactory;
import com.appboy.ui.actions.IAction;
import com.facebook.drawee.view.SimpleDraweeView;

public class CaptionedImageCardView extends BaseCardView<CaptionedImageCard> {
    private static final String TAG;
    private float mAspectRatio;
    private IAction mCardAction;
    private final TextView mDescription;
    private final TextView mDomain;
    private SimpleDraweeView mDrawee;
    private ImageView mImage;
    private final TextView mTitle;

    /* renamed from: com.appboy.ui.widget.CaptionedImageCardView.1 */
    class C04311 implements OnClickListener {
        final /* synthetic */ CaptionedImageCard val$card;

        C04311(CaptionedImageCard captionedImageCard) {
            this.val$card = captionedImageCard;
        }

        public void onClick(View view) {
            BaseCardView.handleCardClick(CaptionedImageCardView.this.mContext, this.val$card, CaptionedImageCardView.this.mCardAction, CaptionedImageCardView.TAG);
        }
    }

    static {
        TAG = String.format("%s.%s", new Object[]{Constants.APPBOY, CaptionedImageCardView.class.getName()});
    }

    public CaptionedImageCardView(Context context) {
        this(context, null);
    }

    public CaptionedImageCardView(Context context, CaptionedImageCard captionedImageCard) {
        super(context);
        this.mAspectRatio = 1.3333334f;
        if (canUseFresco()) {
            this.mDrawee = (SimpleDraweeView) getProperViewFromInflatedStub(C0401R.id.com_appboy_captioned_image_card_drawee_stub);
        } else {
            this.mImage = (ImageView) getProperViewFromInflatedStub(C0401R.id.com_appboy_captioned_image_card_imageview_stub);
            this.mImage.setScaleType(ScaleType.CENTER_CROP);
            this.mImage.setAdjustViewBounds(true);
        }
        this.mTitle = (TextView) findViewById(C0401R.id.com_appboy_captioned_image_title);
        this.mDescription = (TextView) findViewById(C0401R.id.com_appboy_captioned_image_description);
        this.mDomain = (TextView) findViewById(C0401R.id.com_appboy_captioned_image_card_domain);
        if (captionedImageCard != null) {
            setCard(captionedImageCard);
        }
        safeSetBackground(getResources().getDrawable(C0401R.drawable.com_appboy_card_background));
    }

    protected int getLayoutResource() {
        return C0401R.layout.com_appboy_captioned_image_card;
    }

    public void onSetCard(CaptionedImageCard captionedImageCard) {
        this.mTitle.setText(captionedImageCard.getTitle());
        this.mDescription.setText(captionedImageCard.getDescription());
        setOptionalTextView(this.mDomain, captionedImageCard.getDomain());
        this.mCardAction = ActionFactory.createUriAction(getContext(), captionedImageCard.getUrl());
        boolean z = false;
        if (captionedImageCard.getAspectRatio() != 0.0f) {
            this.mAspectRatio = captionedImageCard.getAspectRatio();
            z = true;
        }
        setOnClickListener(new C04311(captionedImageCard));
        if (canUseFresco()) {
            setSimpleDraweeToUrl(this.mDrawee, captionedImageCard.getImageUrl(), this.mAspectRatio, z);
        } else {
            setImageViewToUrl(this.mImage, captionedImageCard.getImageUrl(), this.mAspectRatio, z);
        }
    }
}
