package com.etsy.android.ui.cart.viewholders;

import android.content.res.Resources;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.models.cardviewelement.BaseMessage;
import com.etsy.android.uikit.viewholder.BaseViewHolder;
import java.util.HashMap;

public abstract class BaseMessageViewHolder<T> extends BaseViewHolder<T> {
    protected static final HashMap<String, Pair<Integer, Integer>> TYPE_COLORS_MAP;
    protected final ViewGroup mMessageBubble;
    protected final ImageView mPointer;
    protected final TextView mTextMessage;

    static {
        TYPE_COLORS_MAP = new HashMap();
        TYPE_COLORS_MAP.put(BaseMessage.TYPE_INFO, new Pair(Integer.valueOf(R.info), Integer.valueOf(R.dark_grey)));
        TYPE_COLORS_MAP.put(BaseMessage.TYPE_WARNING, new Pair(Integer.valueOf(R.warning), Integer.valueOf(R.dark_grey)));
        TYPE_COLORS_MAP.put(BaseMessage.TYPE_ERROR, new Pair(Integer.valueOf(R.error), Integer.valueOf(R.white)));
        TYPE_COLORS_MAP.put(BaseMessage.TYPE_SUCCESS, new Pair(Integer.valueOf(R.success), Integer.valueOf(R.dark_grey)));
        TYPE_COLORS_MAP.put(BaseMessage.TYPE_NOTIFY, new Pair(Integer.valueOf(R.notify), Integer.valueOf(R.white)));
    }

    public BaseMessageViewHolder(View view) {
        super(view);
        this.mPointer = (ImageView) findViewById(2131756062);
        this.mMessageBubble = (ViewGroup) findViewById(2131756060);
        this.mTextMessage = (TextView) findViewById(2131755325);
    }

    public void bind(BaseMessage baseMessage) {
        this.mTextMessage.setText(baseMessage.getMessage());
        this.mPointer.setVisibility(baseMessage.getHasPointer() ? 0 : 8);
        setType(baseMessage.getType());
    }

    protected void setType(String str) {
        Pair pair;
        Pair pair2 = (Pair) TYPE_COLORS_MAP.get(str);
        if (pair2 == null) {
            pair = (Pair) TYPE_COLORS_MAP.get(BaseMessage.TYPE_INFO);
        } else {
            pair = pair2;
        }
        Resources resources = this.itemView.getResources();
        setTextColors(resources.getColor(((Integer) pair.second).intValue()));
        setBackgroundColors(resources.getColor(((Integer) pair.first).intValue()));
    }

    protected void setTextColors(int i) {
        this.mTextMessage.setTextColor(i);
    }

    protected void setBackgroundColors(int i) {
        this.mPointer.setColorFilter(i);
        DrawableCompat.setTint(DrawableCompat.wrap(this.mMessageBubble.getBackground()), i);
    }
}
