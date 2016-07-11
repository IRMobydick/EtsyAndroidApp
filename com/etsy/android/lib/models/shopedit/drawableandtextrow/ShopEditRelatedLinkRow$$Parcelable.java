package com.etsy.android.lib.models.shopedit.drawableandtextrow;

import android.os.Parcel;
import com.etsy.android.lib.models.ShopAbout$Link$$PackageHelper;
import com.etsy.android.lib.models.ShopAbout.Link;
import com.etsy.android.lib.models.shopedit.drawableandtextrow.ShopEditAddRelatedLinksRow.RelatedLinksSectionInfo;
import com.etsy.android.lib.util.as;
import java.util.ArrayList;
import java.util.List;
import org.parceler.ax;

public class ShopEditRelatedLinkRow$$Parcelable implements android.os.Parcelable, ax<ShopEditRelatedLinkRow> {
    public static final ShopEditRelatedLinkRow$$Parcelable CREATOR;
    private ShopEditRelatedLinkRow shopEditRelatedLinkRow$$0;

    static {
        CREATOR = new ShopEditRelatedLinkRow$$Parcelable();
    }

    public ShopEditRelatedLinkRow$$Parcelable(Parcel parcel) {
        ShopEditRelatedLinkRow shopEditRelatedLinkRow;
        if (parcel.readInt() == -1) {
            shopEditRelatedLinkRow = null;
        } else {
            shopEditRelatedLinkRow = m2887x1d070976(parcel);
        }
        this.shopEditRelatedLinkRow$$0 = shopEditRelatedLinkRow;
    }

    public ShopEditRelatedLinkRow$$Parcelable(ShopEditRelatedLinkRow shopEditRelatedLinkRow) {
        this.shopEditRelatedLinkRow$$0 = shopEditRelatedLinkRow;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.shopEditRelatedLinkRow$$0 == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        m2889xf9b80bff(this.shopEditRelatedLinkRow$$0, parcel, i);
    }

    private ShopEditRelatedLinkRow m2887x1d070976(Parcel parcel) {
        RelatedLinksSectionInfo relatedLinksSectionInfo;
        Link link = null;
        ShopEditRelatedLinkRow shopEditRelatedLinkRow = new ShopEditRelatedLinkRow();
        if (parcel.readInt() == -1) {
            relatedLinksSectionInfo = null;
        } else {
            relatedLinksSectionInfo = m2886x3bd9fb2d(parcel);
        }
        shopEditRelatedLinkRow.mLinksSectionInfo = relatedLinksSectionInfo;
        if (parcel.readInt() != -1) {
            link = readcom_etsy_android_lib_models_ShopAbout$Link(parcel);
        }
        shopEditRelatedLinkRow.mLink = link;
        shopEditRelatedLinkRow.mText = new as().m3265a(parcel);
        return shopEditRelatedLinkRow;
    }

    private RelatedLinksSectionInfo m2886x3bd9fb2d(Parcel parcel) {
        List list = null;
        RelatedLinksSectionInfo relatedLinksSectionInfo = new RelatedLinksSectionInfo();
        int readInt = parcel.readInt();
        if (readInt >= 0) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < readInt; i++) {
                Object obj;
                if (parcel.readInt() == -1) {
                    obj = null;
                } else {
                    obj = readcom_etsy_android_lib_models_ShopAbout$Link(parcel);
                }
                arrayList.add(obj);
            }
            Object obj2 = arrayList;
        }
        relatedLinksSectionInfo.mAllLinks = list;
        return relatedLinksSectionInfo;
    }

    private Link readcom_etsy_android_lib_models_ShopAbout$Link(Parcel parcel) {
        Link link = new Link();
        ShopAbout$Link$$PackageHelper.accessShopAbout$Link$FS$mUrl(link, parcel.readString());
        ShopAbout$Link$$PackageHelper.accessShopAbout$Link$FS$mTitle(link, parcel.readString());
        return link;
    }

    private void m2889xf9b80bff(ShopEditRelatedLinkRow shopEditRelatedLinkRow, Parcel parcel, int i) {
        if (shopEditRelatedLinkRow.mLinksSectionInfo == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            m2888xd0954636(shopEditRelatedLinkRow.mLinksSectionInfo, parcel, i);
        }
        if (shopEditRelatedLinkRow.mLink == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_ShopAbout$Link(shopEditRelatedLinkRow.mLink, parcel, i);
        }
        new as().m3266a(shopEditRelatedLinkRow.mText, parcel);
    }

    private void m2888xd0954636(RelatedLinksSectionInfo relatedLinksSectionInfo, Parcel parcel, int i) {
        if (relatedLinksSectionInfo.mAllLinks == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(relatedLinksSectionInfo.mAllLinks.size());
        for (Link link : relatedLinksSectionInfo.mAllLinks) {
            if (link == null) {
                parcel.writeInt(-1);
            } else {
                parcel.writeInt(1);
                writecom_etsy_android_lib_models_ShopAbout$Link(link, parcel, i);
            }
        }
    }

    private void writecom_etsy_android_lib_models_ShopAbout$Link(Link link, Parcel parcel, int i) {
        parcel.writeString(ShopAbout$Link$$PackageHelper.accessShopAbout$Link$FG$mUrl(link));
        parcel.writeString(ShopAbout$Link$$PackageHelper.accessShopAbout$Link$FG$mTitle(link));
    }

    public int describeContents() {
        return 0;
    }

    public ShopEditRelatedLinkRow getParcel() {
        return this.shopEditRelatedLinkRow$$0;
    }
}
