package com.etsy.android.lib.convos;

import android.content.Context;
import android.content.Intent;
import com.etsy.android.lib.R;
import com.etsy.android.lib.convos.Draft.Status;
import com.etsy.android.lib.convos.contentprovider.ConvoDatabaseUtil;
import com.etsy.android.lib.core.EtsyResult;
import com.etsy.android.lib.core.posts.EtsyRequestPost;
import com.etsy.android.lib.models.EmptyResult;
import com.etsy.android.lib.models.cardviewelement.BaseMessage;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.requests.ConversationRequest;
import com.etsy.android.uikit.util.AdHocEventCompatBuilder;
import org.apache.commons.lang3.StringUtils;

public class ConvoPost extends EtsyRequestPost<EmptyResult> {
    private static final long serialVersionUID = -3928129936998012582L;
    private long mConvoId;
    private String mToUsername;

    public ConvoPost(ConversationRequest<EmptyResult> conversationRequest, Draft draft) {
        super(conversationRequest);
        this.mConvoId = draft.getConvoId();
        this.mToUsername = draft.getUserName();
    }

    public int getVersionCode() {
        return 1;
    }

    public void onAdded(Context context) {
        ConvoDatabaseUtil.m973a(context, new EtsyId(this.mConvoId), Status.SENDING);
        context.sendOrderedBroadcast(m943a("com.etsy.android.convos.MESSAGE_SENDING"), null);
    }

    public void onSuccess(Context context, EtsyResult<EmptyResult> etsyResult) {
        ConvoDatabaseUtil.m980c(context, this.mConvoId);
        context.sendOrderedBroadcast(m943a("com.etsy.android.convos.MESSAGE_SENT"), null);
        new AdHocEventCompatBuilder("conversations_new_sent").m5515a("conversations").m5517a();
    }

    public boolean onError(Context context, EtsyResult<EmptyResult> etsyResult) {
        String c;
        ConvoDatabaseUtil.m973a(context, new EtsyId(this.mConvoId), Status.FAILED);
        Intent a = m943a("com.etsy.android.convos.MESSAGE_FAILED_TO_SEND");
        String str = BaseMessage.TYPE_ERROR;
        if (etsyResult != null) {
            c = etsyResult.m1052c();
        } else {
            c = context.getString(R.convo_send_error_message, new Object[]{StringUtils.EMPTY});
        }
        a.putExtra(str, c);
        context.sendOrderedBroadcast(a, null);
        return false;
    }

    private Intent m943a(String str) {
        Intent intent = new Intent(str);
        intent.putExtra("message_addressee", this.mToUsername);
        intent.putExtra("convo_id", this.mConvoId);
        return intent;
    }
}
