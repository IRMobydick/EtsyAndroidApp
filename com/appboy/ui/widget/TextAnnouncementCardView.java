package com.appboy.ui.widget;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.appboy.Constants;
import com.appboy.models.cards.TextAnnouncementCard;
import com.appboy.ui.C0401R;
import com.appboy.ui.actions.ActionFactory;
import com.appboy.ui.actions.IAction;

public class TextAnnouncementCardView extends BaseCardView<TextAnnouncementCard> {
    private static final String TAG;
    private IAction mCardAction;
    private final TextView mDescription;
    private final TextView mDomain;
    private final TextView mTitle;

    /* renamed from: com.appboy.ui.widget.TextAnnouncementCardView.1 */
    class C04341 implements OnClickListener {
        final /* synthetic */ TextAnnouncementCard val$card;

        C04341(TextAnnouncementCard textAnnouncementCard) {
            this.val$card = textAnnouncementCard;
        }

        public void onClick(View view) {
            BaseCardView.handleCardClick(TextAnnouncementCardView.this.mContext, this.val$card, TextAnnouncementCardView.this.mCardAction, TextAnnouncementCardView.TAG);
        }
    }

    static {
        TAG = String.format("%s.%s", new Object[]{Constants.APPBOY, TextAnnouncementCardView.class.getName()});
    }

    public TextAnnouncementCardView(Context context) {
        this(context, null);
    }

    public TextAnnouncementCardView(Context context, TextAnnouncementCard textAnnouncementCard) {
        super(context);
        this.mTitle = (TextView) findViewById(C0401R.id.com_appboy_text_announcement_card_title);
        this.mDescription = (TextView) findViewById(C0401R.id.com_appboy_text_announcement_card_description);
        this.mDomain = (TextView) findViewById(C0401R.id.com_appboy_text_announcement_card_domain);
        if (textAnnouncementCard != null) {
            setCard(textAnnouncementCard);
        }
        safeSetBackground(getResources().getDrawable(C0401R.drawable.com_appboy_card_background));
    }

    protected int getLayoutResource() {
        return C0401R.layout.com_appboy_text_announcement_card;
    }

    public void onSetCard(TextAnnouncementCard textAnnouncementCard) {
        this.mTitle.setText(textAnnouncementCard.getTitle());
        this.mDescription.setText(textAnnouncementCard.getDescription());
        setOptionalTextView(this.mDomain, textAnnouncementCard.getDomain());
        this.mCardAction = ActionFactory.createUriAction(getContext(), textAnnouncementCard.getUrl());
        setOnClickListener(new C04341(textAnnouncementCard));
    }
}
