package com.etsy.android.lib.convos;

import android.view.View.OnClickListener;
import com.etsy.android.lib.models.Conversation;

/* renamed from: com.etsy.android.lib.convos.j */
public interface ConvoSelectionCallbacks {
    boolean isShowingConvo();

    boolean isTwoPane();

    void onItemSelected(Conversation conversation, boolean z);

    void onShowConvo();

    void onShowEmpty();

    void onShowErrorView(OnClickListener onClickListener);
}
