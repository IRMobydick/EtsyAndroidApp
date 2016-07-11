package com.etsy.android.lib.models.cardviewelement;

import com.etsy.android.uikit.cardview.ICardViewElement;
import org.parceler.Parcel;

@Parcel
public class LoadingCardViewElement implements ICardViewElement {
    public int getViewType() {
        return 1;
    }
}
