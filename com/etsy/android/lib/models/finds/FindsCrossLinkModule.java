package com.etsy.android.lib.models.finds;

import com.etsy.android.lib.models.apiv3.FindsCard;
import com.etsy.android.uikit.cardview.ICardViewElement;
import java.util.List;
import org.parceler.Parcel;

@Parcel
public class FindsCrossLinkModule extends FindsModule {

    @Parcel
    public class Page extends FindsCard {
        protected int mType;

        public int getViewType() {
            return this.mType;
        }
    }

    protected void setFromGeneric(FindsModule findsModule) {
        super.setFromGeneric(findsModule);
        this.mPages = findsModule.mPages;
        int i = FindsModule.SMALL_CROSSLINK_TYPE.equals(findsModule.mType) ? 30 : 20;
        for (Page page : this.mPages) {
            page.mType = i;
        }
    }

    public List<? extends ICardViewElement> getCardViewElements() {
        return this.mPages;
    }
}
