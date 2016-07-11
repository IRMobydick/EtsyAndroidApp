package com.etsy.android.lib.convos.adapters;

import android.content.Context;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.convos.ConvoMessageCallbacks;
import com.etsy.android.lib.core.img.ImageBatch;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.logger.ITrackedObject;
import com.etsy.android.lib.models.ConversationImage;
import com.etsy.android.lib.models.ConversationMessage;
import com.etsy.android.lib.models.ConversationUser;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.datatypes.TrackedObject;
import com.etsy.android.lib.util.aj;
import com.etsy.android.lib.util.bh;
import com.etsy.android.uikit.util.EtsyLinkify;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.view.AttachmentThumbnailsView;
import com.etsy.android.uikit.view.MachineTranslationOneClickView;
import java.util.List;

/* renamed from: com.etsy.android.lib.convos.adapters.c */
public class ConvoMessageHelper {
    private final Context f1337a;
    private final float f1338b;
    private ImageBatch f1339c;
    private ConvoMessageCallbacks f1340d;
    private final int f1341e;
    private final int f1342f;
    private final ConversationUser f1343g;
    private String f1344h;
    private EtsyId f1345i;
    private int f1346j;
    private int f1347k;
    private boolean f1348l;

    /* renamed from: com.etsy.android.lib.convos.adapters.c.1 */
    class ConvoMessageHelper extends TrackingOnClickListener {
        final /* synthetic */ ConversationMessage f1329a;
        final /* synthetic */ ConvoMessageHelper f1330b;

        ConvoMessageHelper(ConvoMessageHelper convoMessageHelper, ITrackedObject[] iTrackedObjectArr, ConversationMessage conversationMessage) {
            this.f1330b = convoMessageHelper;
            this.f1329a = conversationMessage;
            super(iTrackedObjectArr);
        }

        public void onViewClick(View view) {
            this.f1330b.f1340d.m998a(this.f1329a.getUserId());
        }
    }

    /* renamed from: com.etsy.android.lib.convos.adapters.c.2 */
    class ConvoMessageHelper extends TrackingOnClickListener {
        final /* synthetic */ ConversationMessage f1331a;
        final /* synthetic */ ConvoMessageHelper f1332b;
        final /* synthetic */ ConvoMessageHelper f1333c;

        ConvoMessageHelper(ConvoMessageHelper convoMessageHelper, ITrackedObject[] iTrackedObjectArr, ConversationMessage conversationMessage, ConvoMessageHelper convoMessageHelper2) {
            this.f1333c = convoMessageHelper;
            this.f1331a = conversationMessage;
            this.f1332b = convoMessageHelper2;
            super(iTrackedObjectArr);
        }

        public void onViewClick(View view) {
            this.f1333c.f1340d.m997a(this.f1331a, this.f1332b);
        }
    }

    /* renamed from: com.etsy.android.lib.convos.adapters.c.3 */
    class ConvoMessageHelper extends TrackingOnClickListener {
        final /* synthetic */ int f1334a;
        final /* synthetic */ List f1335b;
        final /* synthetic */ ConvoMessageHelper f1336c;

        ConvoMessageHelper(ConvoMessageHelper convoMessageHelper, ITrackedObject[] iTrackedObjectArr, int i, List list) {
            this.f1336c = convoMessageHelper;
            this.f1334a = i;
            this.f1335b = list;
            super(iTrackedObjectArr);
        }

        public void onViewClick(View view) {
            if (this.f1336c.f1340d != null) {
                this.f1336c.f1340d.m996a(this.f1334a, this.f1335b);
            }
        }
    }

    public ConvoMessageHelper(Context context, ImageBatch imageBatch, ConvoMessageCallbacks convoMessageCallbacks, ConversationUser conversationUser, EtsyId etsyId, String str) {
        this.f1346j = R.list_item_conversation_message;
        this.f1347k = R.list_item_conversation_message_collapsed;
        this.f1337a = context;
        this.f1339c = imageBatch;
        this.f1340d = convoMessageCallbacks;
        this.f1343g = conversationUser;
        this.f1341e = context.getResources().getDimensionPixelSize(R.conversation_avatar);
        this.f1345i = etsyId;
        this.f1344h = str;
        this.f1338b = context.getResources().getFraction(R.convo_image_attachments_ratio, 1, 1);
        this.f1342f = context.getResources().getDimensionPixelSize(R.padding_medium_large);
        this.f1348l = true;
    }

    public int m949a() {
        return this.f1346j;
    }

    public int m952b() {
        return this.f1347k;
    }

    public ConvoMessageHelper m950a(View view) {
        ConvoMessageHelper convoMessageHelper = new ConvoMessageHelper();
        convoMessageHelper.f1349a = (ImageView) view.findViewById(R.convo_user_img);
        convoMessageHelper.f1350b = (TextView) view.findViewById(R.convo_user_name_text);
        convoMessageHelper.f1351c = (TextView) view.findViewById(R.convo_time_text);
        convoMessageHelper.f1352d = (TextView) view.findViewById(R.convo_user_msg_text);
        convoMessageHelper.f1353e = (AttachmentThumbnailsView) view.findViewById(R.linear_convo_images);
        convoMessageHelper.f1353e.setImageBatch(this.f1339c);
        convoMessageHelper.f1354f = (MachineTranslationOneClickView) view.findViewById(R.machine_translation_one_click);
        convoMessageHelper.f1355g = (TextView) view.findViewById(R.translate_button);
        convoMessageHelper.f1356h = view;
        return convoMessageHelper;
    }

    public void m951a(ConvoMessageHelper convoMessageHelper, ConversationMessage conversationMessage) {
        String str;
        if (conversationMessage.getUserId().equals(this.f1345i)) {
            str = this.f1344h;
            convoMessageHelper.f1350b.setText(R.convo_author_you);
        } else {
            str = this.f1343g.getAvatarUrl();
            convoMessageHelper.f1349a.setOnClickListener(new ConvoMessageHelper(this, new ITrackedObject[]{conversationMessage}, conversationMessage));
            convoMessageHelper.f1350b.setText(this.f1343g.getDisplayName());
            convoMessageHelper.f1355g.setOnClickListener(new ConvoMessageHelper(this, new ITrackedObject[]{conversationMessage}, conversationMessage, convoMessageHelper));
        }
        this.f1339c.m1574b(str, convoMessageHelper.f1349a, this.f1341e);
    }

    public void m953b(ConvoMessageHelper convoMessageHelper, ConversationMessage conversationMessage) {
        convoMessageHelper.f1351c.setText(DateUtils.getRelativeTimeSpanString(conversationMessage.getCreationDate()));
        convoMessageHelper.f1352d.setText(conversationMessage.getMessage());
        EtsyLinkify.m5483a(this.f1337a, convoMessageHelper.f1352d, this.f1348l);
    }

    public void m954c(ConvoMessageHelper convoMessageHelper, ConversationMessage conversationMessage) {
        if (aj.m3230a(conversationMessage) && !conversationMessage.getUserId().equals(this.f1345i) && this.f1340d.m999a()) {
            convoMessageHelper.f1354f.showButtonElements();
            if (bh.m3340a(conversationMessage.getTranslatedMessage())) {
                convoMessageHelper.f1354f.setTranslatedStateWithString(conversationMessage.getTranslatedMessage());
            } else {
                convoMessageHelper.f1354f.setUntranslatedState();
            }
            convoMessageHelper.f1354f.hideSpinner();
            convoMessageHelper.f1354f.hideErrorMessage();
            return;
        }
        convoMessageHelper.f1354f.hideAllElements();
    }

    public void m955d(ConvoMessageHelper convoMessageHelper, ConversationMessage conversationMessage) {
        convoMessageHelper.f1353e.clear();
        convoMessageHelper.f1353e.setVisibility(8);
        if (conversationMessage.getHasImages()) {
            List images = conversationMessage.getImages();
            int size = images.size();
            for (int i = 0; i < size; i++) {
                if (convoMessageHelper.f1353e.addImage(((ConversationImage) images.get(i)).getUrl75x75(), new ConvoMessageHelper(this, new ITrackedObject[]{conversationMessage, new TrackedObject(AnalyticsLogAttribute.URL, ((ConversationImage) images.get(i)).getUrlFullxFull())}, i, images))) {
                    convoMessageHelper.f1353e.setVisibility(0);
                }
            }
        }
    }
}
