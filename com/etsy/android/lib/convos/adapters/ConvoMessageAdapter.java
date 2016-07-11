package com.etsy.android.lib.convos.adapters;

import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.img.ImageBatch;
import com.etsy.android.lib.models.ConversationMessage;
import com.etsy.android.uikit.adapter.CollapsibleBaseArrayAdapter;
import com.etsy.android.uikit.util.TrackingOnClickListener;

public class ConvoMessageAdapter extends CollapsibleBaseArrayAdapter<ConversationMessage> {
    private final ConvoMessageHelper mHelper;

    /* renamed from: com.etsy.android.lib.convos.adapters.ConvoMessageAdapter.1 */
    class C04411 extends TrackingOnClickListener {
        final /* synthetic */ ConvoMessageAdapter f1312a;

        C04411(ConvoMessageAdapter convoMessageAdapter) {
            this.f1312a = convoMessageAdapter;
        }

        public void onViewClick(View view) {
            this.f1312a.setCollapsed(false);
        }
    }

    public ConvoMessageAdapter(FragmentActivity fragmentActivity, ImageBatch imageBatch, ConvoMessageHelper convoMessageHelper) {
        super(fragmentActivity, convoMessageHelper.m949a(), imageBatch);
        this.mHelper = convoMessageHelper;
        setCollapsed(true);
    }

    public View getNormalView(int i, View view, ViewGroup viewGroup) {
        ConvoMessageHelper convoMessageHelper;
        if (view == null) {
            view = getLayoutInflater().inflate(getLayoutId(), null);
            ConvoMessageHelper a = this.mHelper.m950a(view);
            view.setTag(a);
            convoMessageHelper = a;
        } else {
            convoMessageHelper = (ConvoMessageHelper) view.getTag();
        }
        ConversationMessage conversationMessage = (ConversationMessage) getItem(i);
        this.mHelper.m951a(convoMessageHelper, conversationMessage);
        this.mHelper.m953b(convoMessageHelper, conversationMessage);
        this.mHelper.m955d(convoMessageHelper, conversationMessage);
        this.mHelper.m954c(convoMessageHelper, conversationMessage);
        return view;
    }

    public View getCollapsedView(int i, View view, ViewGroup viewGroup) {
        ConvoMessageAdapter createCollapsedMessagesHolder;
        if (view == null) {
            view = getLayoutInflater().inflate(this.mHelper.m952b(), null);
            createCollapsedMessagesHolder = createCollapsedMessagesHolder(view);
            view.setTag(createCollapsedMessagesHolder);
        } else {
            createCollapsedMessagesHolder = (ConvoMessageAdapter) view.getTag();
        }
        int collapsedItemCount = getCollapsedItemCount();
        createCollapsedMessagesHolder.f1327a.setText(getActivityContext().getResources().getQuantityString(R.convo_messages_show_collapsed, collapsedItemCount, new Object[]{Integer.valueOf(collapsedItemCount)}));
        createCollapsedMessagesHolder.f1328b.setOnClickListener(new C04411(this));
        return view;
    }

    private ConvoMessageAdapter createCollapsedMessagesHolder(View view) {
        ConvoMessageAdapter convoMessageAdapter = new ConvoMessageAdapter();
        convoMessageAdapter.f1327a = (TextView) view.findViewById(R.convo_msg_count);
        convoMessageAdapter.f1328b = view;
        return convoMessageAdapter;
    }

    public void onTranslationLoading(ConvoMessageHelper convoMessageHelper) {
        convoMessageHelper.f1354f.showSpinner();
    }

    public void onTranslationSuccess(ConvoMessageHelper convoMessageHelper, ConversationMessage conversationMessage) {
        convoMessageHelper.f1354f.hideSpinner();
        this.mHelper.m954c(convoMessageHelper, conversationMessage);
    }

    public void onTranslationError(ConvoMessageHelper convoMessageHelper) {
        convoMessageHelper.f1354f.hideSpinner();
        convoMessageHelper.f1354f.showErrorMessage();
    }
}
