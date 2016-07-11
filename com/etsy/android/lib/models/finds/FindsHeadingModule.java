package com.etsy.android.lib.models.finds;

import com.etsy.android.uikit.cardview.ICardViewElement;
import java.util.ArrayList;
import java.util.List;
import org.parceler.Parcel;

@Parcel
public class FindsHeadingModule extends FindsModule {
    protected void setFromGeneric(FindsModule findsModule) {
        this.mText = findsModule.mText;
    }

    public int getViewType() {
        return 21;
    }

    public List<? extends ICardViewElement> getCardViewElements() {
        List<? extends ICardViewElement> arrayList = new ArrayList();
        arrayList.add(this);
        return arrayList;
    }
}
