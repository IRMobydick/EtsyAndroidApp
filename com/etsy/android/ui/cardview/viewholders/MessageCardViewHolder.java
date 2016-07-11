package com.etsy.android.ui.cardview.viewholders;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.models.homescreen.MessageCard;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.viewholder.BaseViewHolder;
import com.etsy.android.uikit.viewholder.a.b;

public class MessageCardViewHolder extends BaseViewHolder<MessageCard> {
    ImageView mImageView;
    View mLink;
    private b mListener;
    TextView mTextDescription;
    TextView mTextLinkTitle;
    TextView mTextTitle;

    /* renamed from: com.etsy.android.ui.cardview.viewholders.MessageCardViewHolder.1 */
    class C05601 extends TrackingOnClickListener {
        final /* synthetic */ MessageCard f2298a;
        final /* synthetic */ MessageCardViewHolder f2299b;

        C05601(MessageCardViewHolder messageCardViewHolder, MessageCard messageCard) {
            this.f2299b = messageCardViewHolder;
            this.f2298a = messageCard;
        }

        public void onViewClick(View view) {
            if (this.f2299b.mListener != null) {
                this.f2299b.mListener.a(this.f2298a);
            }
        }
    }

    public MessageCardViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, b bVar) {
        super(layoutInflater.inflate(2130903298, viewGroup, false));
        this.mTextTitle = (TextView) findViewById(R.txt_title);
        this.mTextDescription = (TextView) findViewById(2131755949);
        this.mImageView = (ImageView) findViewById(R.image);
        this.mTextLinkTitle = (TextView) findViewById(2131755943);
        this.mLink = findViewById(2131755950);
        this.mListener = bVar;
    }

    public void bind(MessageCard messageCard) {
        int image = messageCard.getImage();
        if (image == 1) {
            this.mImageView.setVisibility(0);
            this.mImageView.setImageResource(2130837866);
        } else if (image == 2) {
            this.mImageView.setVisibility(0);
            this.mImageView.setImageResource(R.empty_activity);
        } else if (image == 5) {
            this.mImageView.setVisibility(0);
            this.mImageView.setImageResource(R.empty_basket);
        } else if (image == 4) {
            this.mImageView.setVisibility(0);
            this.mImageView.setImageResource(R.empty_shelves);
        } else {
            this.mImageView.setVisibility(8);
        }
        if (TextUtils.isEmpty(messageCard.getTitle())) {
            this.mTextTitle.setVisibility(8);
        } else {
            this.mTextTitle.setVisibility(0);
            this.mTextTitle.setText(messageCard.getTitle());
        }
        if (messageCard.getDescription() == null) {
            this.mTextDescription.setVisibility(8);
        } else {
            this.mTextDescription.setVisibility(0);
            this.mTextDescription.setText(messageCard.getDescription());
        }
        if (TextUtils.isEmpty(messageCard.getLinkTitle()) || TextUtils.isEmpty(messageCard.getLink())) {
            this.mTextLinkTitle.setVisibility(8);
        } else {
            this.mTextLinkTitle.setVisibility(0);
            this.mTextLinkTitle.setText(messageCard.getLinkTitle());
        }
        this.mLink.setOnClickListener(new C05601(this, messageCard));
    }

    public void recycle() {
        this.mImageView.setImageDrawable(null);
    }
}
