package com.etsy.android.lib.models.cardviewelement;

import android.support.annotation.NonNull;
import com.etsy.android.lib.models.BaseFieldModel;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.finds.FindsModule;
import com.etsy.android.lib.models.homescreen.MessageCard;
import com.fasterxml.jackson.core.JsonParser;
import java.util.ArrayList;
import java.util.List;
import org.parceler.Parcel;

@Parcel
public class Page extends BaseFieldModel {
    private static final long serialVersionUID = -1681648073126542330L;
    protected List<ListSection> mListSections;
    protected MessageCard mMessageCard;
    protected BaseModel mMetadata;
    protected String mTitle;

    public Page() {
        this.mListSections = new ArrayList();
    }

    protected boolean parseField(JsonParser jsonParser, @NonNull String str) {
        if (FindsModule.FIELD_TITLE.equals(str)) {
            this.mTitle = BaseModel.parseString(jsonParser);
            return true;
        } else if (ListSection.TYPE_MESSAGE_CARD.equals(str)) {
            List parseArray = BaseModel.parseArray(jsonParser, MessageCard.class);
            if (parseArray.size() > 0) {
                this.mMessageCard = (MessageCard) parseArray.get(0);
            }
            return true;
        } else if (!ResponseConstants.LIST.equals(str)) {
            return false;
        } else {
            this.mListSections = BaseModel.parseArray(jsonParser, ListSection.class);
            return true;
        }
    }

    public String getTitle() {
        return this.mTitle;
    }

    public List<ListSection> getListSections() {
        return this.mListSections;
    }

    public MessageCard getMessageCard() {
        return this.mMessageCard;
    }
}
