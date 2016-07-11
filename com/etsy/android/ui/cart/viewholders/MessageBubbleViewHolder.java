package com.etsy.android.ui.cart.viewholders;

import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.models.apiv3.cart.CartGroupAction;
import com.etsy.android.lib.models.apiv3.cart.CartGroupItem;
import com.etsy.android.lib.models.apiv3.cart.MessageBubble;
import com.etsy.android.lib.models.cardviewelement.BaseMessage;
import com.etsy.android.lib.util.fonts.EtsyFontIcons;
import com.etsy.android.ui.cart.p015a.CartGroupActionClickHandler;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import java.util.HashMap;

public class MessageBubbleViewHolder extends BaseMessageViewHolder<CartGroupItem> {
    protected static final HashMap<String, String> TYPE_ICON_MAP;
    private final Button mBtnResolution;
    private final CartGroupActionClickHandler mClickHandler;
    private final TextView mIconCharacter;

    /* renamed from: com.etsy.android.ui.cart.viewholders.MessageBubbleViewHolder.1 */
    class C06081 extends TrackingOnClickListener {
        final /* synthetic */ CartGroupItem f2603a;
        final /* synthetic */ CartGroupAction f2604b;
        final /* synthetic */ MessageBubbleViewHolder f2605c;

        C06081(MessageBubbleViewHolder messageBubbleViewHolder, CartGroupItem cartGroupItem, CartGroupAction cartGroupAction) {
            this.f2605c = messageBubbleViewHolder;
            this.f2603a = cartGroupItem;
            this.f2604b = cartGroupAction;
        }

        public void onViewClick(View view) {
            this.f2605c.mClickHandler.m3711a(this.f2603a.getCartAdapterPosition(), this.f2604b);
        }
    }

    static {
        TYPE_ICON_MAP = new HashMap();
        TYPE_ICON_MAP.put(BaseMessage.TYPE_INFO, "i");
        TYPE_ICON_MAP.put(BaseMessage.TYPE_WARNING, "!");
        TYPE_ICON_MAP.put(BaseMessage.TYPE_SUCCESS, EtsyFontIcons.CHECK.toString());
    }

    public MessageBubbleViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, CartGroupActionClickHandler cartGroupActionClickHandler) {
        super(layoutInflater.inflate(2130903344, viewGroup, false));
        this.mClickHandler = cartGroupActionClickHandler;
        this.mIconCharacter = (TextView) findViewById(R.icon);
        this.mIconCharacter.setIncludeFontPadding(false);
        this.mIconCharacter.setTypeface(Typeface.createFromAsset(this.itemView.getResources().getAssets(), EtsyFontIcons.getTypefaceName()));
        this.mBtnResolution = (Button) findViewById(2131756063);
    }

    public void bind(CartGroupItem cartGroupItem) {
        MessageBubble messageBubble = (MessageBubble) cartGroupItem.getData();
        this.mIconCharacter.setText(null);
        bind(messageBubble);
        setIconChar(messageBubble.getType(), messageBubble.getIconChar());
        CartGroupAction action = cartGroupItem.getAction(CartGroupAction.UPDATE_OFFERING);
        if (action == null || this.mClickHandler == null) {
            this.mBtnResolution.setVisibility(8);
            return;
        }
        this.mBtnResolution.setVisibility(0);
        CharSequence spannableString = new SpannableString(action.getDisplayName());
        spannableString.setSpan(new UnderlineSpan(), 0, spannableString.length(), 0);
        this.mBtnResolution.setText(spannableString);
        this.mBtnResolution.setOnClickListener(new C06081(this, cartGroupItem, action));
    }

    protected void setTextColors(int i) {
        super.setTextColors(i);
        this.mBtnResolution.setTextColor(i);
    }

    protected void setIconChar(String str, String str2) {
        CharSequence charSequence;
        if (TextUtils.isEmpty(str2)) {
            charSequence = (String) TYPE_ICON_MAP.get(str);
        } else {
            Object obj = str2;
        }
        this.mIconCharacter.setVisibility(TextUtils.isEmpty(charSequence) ? 8 : 0);
        this.mIconCharacter.setText(charSequence);
    }
}
