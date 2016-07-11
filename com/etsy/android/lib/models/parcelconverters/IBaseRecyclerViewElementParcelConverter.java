package com.etsy.android.lib.models.parcelconverters;

import android.os.Parcel;
import com.etsy.android.uikit.cardview.IBaseRecyclerViewElement;
import com.etsy.android.uikit.cardview.ICardViewElement;
import java.util.ArrayList;
import java.util.List;
import org.parceler.Parcels;
import org.parceler.av;

public class IBaseRecyclerViewElementParcelConverter implements av<List<IBaseRecyclerViewElement>> {
    public void toParcel(List<IBaseRecyclerViewElement> list, Parcel parcel) {
        if (list == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(list.size());
        for (Object a : list) {
            parcel.writeParcelable(Parcels.m7494a(a), 0);
        }
    }

    public List<IBaseRecyclerViewElement> fromParcel(Parcel parcel) {
        int readInt = parcel.readInt();
        if (readInt < 0) {
            return null;
        }
        List<IBaseRecyclerViewElement> arrayList = new ArrayList();
        for (int i = 0; i < readInt; i++) {
            arrayList.add((ICardViewElement) Parcels.m7495a(parcel.readParcelable(IBaseRecyclerViewElement.class.getClassLoader())));
        }
        return arrayList;
    }
}
