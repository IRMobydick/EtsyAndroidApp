package com.etsy.android.lib.models.cardviewelement;

import android.os.Parcel;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.homescreen.MessageCard;
import com.etsy.android.lib.models.homescreen.MessageCard$$PackageHelper;
import com.etsy.android.lib.models.parcelconverters.IBaseRecyclerViewElementParcelConverter;
import java.util.ArrayList;
import java.util.List;
import org.parceler.ax;

public class Page$$Parcelable implements android.os.Parcelable, ax<Page> {
    public static final Page$$Parcelable CREATOR;
    private Page page$$3;

    static {
        CREATOR = new Page$$Parcelable();
    }

    public Page$$Parcelable(Parcel parcel) {
        Page page;
        if (parcel.readInt() == -1) {
            page = null;
        } else {
            page = readcom_etsy_android_lib_models_cardviewelement_Page(parcel);
        }
        this.page$$3 = page;
    }

    public Page$$Parcelable(Page page) {
        this.page$$3 = page;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.page$$3 == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_cardviewelement_Page(this.page$$3, parcel, i);
    }

    private Page readcom_etsy_android_lib_models_cardviewelement_Page(Parcel parcel) {
        MessageCard messageCard;
        List list = null;
        Page page = new Page();
        page.mMetadata = (BaseModel) parcel.readSerializable();
        page.mTitle = parcel.readString();
        if (parcel.readInt() == -1) {
            messageCard = null;
        } else {
            messageCard = readcom_etsy_android_lib_models_homescreen_MessageCard(parcel);
        }
        page.mMessageCard = messageCard;
        int readInt = parcel.readInt();
        if (readInt >= 0) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < readInt; i++) {
                Object obj;
                if (parcel.readInt() == -1) {
                    obj = null;
                } else {
                    obj = readcom_etsy_android_lib_models_cardviewelement_ListSection(parcel);
                }
                arrayList.add(obj);
            }
            Object obj2 = arrayList;
        }
        page.mListSections = list;
        return page;
    }

    private MessageCard readcom_etsy_android_lib_models_homescreen_MessageCard(Parcel parcel) {
        boolean z = true;
        MessageCard messageCard = new MessageCard();
        MessageCard$$PackageHelper.accessMessageCard$FS$mLinkTitle(messageCard, parcel.readString());
        MessageCard$$PackageHelper.accessMessageCard$FS$mDeepLinkUrl(messageCard, parcel.readString());
        MessageCard$$PackageHelper.accessMessageCard$FS$mTitle(messageCard, parcel.readString());
        MessageCard$$PackageHelper.accessMessageCard$FS$mImageType(messageCard, parcel.readInt());
        if (parcel.readInt() != 1) {
            z = false;
        }
        MessageCard$$PackageHelper.accessMessageCard$FS$mTryAgain(messageCard, z);
        MessageCard$$PackageHelper.accessMessageCard$FS$mLink(messageCard, parcel.readString());
        MessageCard$$PackageHelper.accessMessageCard$FS$mDescription(messageCard, parcel.readString());
        return messageCard;
    }

    private ListSection readcom_etsy_android_lib_models_cardviewelement_ListSection(Parcel parcel) {
        BasicSectionHeader basicSectionHeader;
        boolean z = true;
        ListSection listSection = new ListSection();
        if (parcel.readInt() != 1) {
            z = false;
        }
        listSection.mIsHorizontal = z;
        if (parcel.readInt() == -1) {
            basicSectionHeader = null;
        } else {
            basicSectionHeader = m2591x7fb48a11(parcel);
        }
        listSection.mHeader = basicSectionHeader;
        listSection.mItems = new IBaseRecyclerViewElementParcelConverter().fromParcel(parcel);
        listSection.mPageLink = (PageLink) parcel.readSerializable();
        listSection.mLayoutState = parcel.readParcelable(Page$$Parcelable.class.getClassLoader());
        return listSection;
    }

    private BasicSectionHeader m2591x7fb48a11(Parcel parcel) {
        BasicSectionHeader basicSectionHeader = new BasicSectionHeader();
        basicSectionHeader.mViewType = parcel.readInt();
        basicSectionHeader.title = parcel.readString();
        basicSectionHeader.subtitle = parcel.readString();
        return basicSectionHeader;
    }

    private void writecom_etsy_android_lib_models_cardviewelement_Page(Page page, Parcel parcel, int i) {
        parcel.writeSerializable(page.mMetadata);
        parcel.writeString(page.mTitle);
        if (page.mMessageCard == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_homescreen_MessageCard(page.mMessageCard, parcel, i);
        }
        if (page.mListSections == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(page.mListSections.size());
        for (ListSection listSection : page.mListSections) {
            if (listSection == null) {
                parcel.writeInt(-1);
            } else {
                parcel.writeInt(1);
                writecom_etsy_android_lib_models_cardviewelement_ListSection(listSection, parcel, i);
            }
        }
    }

    private void writecom_etsy_android_lib_models_homescreen_MessageCard(MessageCard messageCard, Parcel parcel, int i) {
        parcel.writeString(MessageCard$$PackageHelper.accessMessageCard$FG$mLinkTitle(messageCard));
        parcel.writeString(MessageCard$$PackageHelper.accessMessageCard$FG$mDeepLinkUrl(messageCard));
        parcel.writeString(MessageCard$$PackageHelper.accessMessageCard$FG$mTitle(messageCard));
        parcel.writeInt(MessageCard$$PackageHelper.accessMessageCard$FG$mImageType(messageCard));
        parcel.writeInt(MessageCard$$PackageHelper.accessMessageCard$FG$mTryAgain(messageCard) ? 1 : 0);
        parcel.writeString(MessageCard$$PackageHelper.accessMessageCard$FG$mLink(messageCard));
        parcel.writeString(MessageCard$$PackageHelper.accessMessageCard$FG$mDescription(messageCard));
    }

    private void writecom_etsy_android_lib_models_cardviewelement_ListSection(ListSection listSection, Parcel parcel, int i) {
        parcel.writeInt(listSection.mIsHorizontal ? 1 : 0);
        if (listSection.mHeader == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            m2592x3265be9a(listSection.mHeader, parcel, i);
        }
        new IBaseRecyclerViewElementParcelConverter().toParcel(listSection.mItems, parcel);
        parcel.writeSerializable(listSection.mPageLink);
        parcel.writeParcelable(listSection.mLayoutState, i);
    }

    private void m2592x3265be9a(BasicSectionHeader basicSectionHeader, Parcel parcel, int i) {
        parcel.writeInt(basicSectionHeader.mViewType);
        parcel.writeString(basicSectionHeader.title);
        parcel.writeString(basicSectionHeader.subtitle);
    }

    public int describeContents() {
        return 0;
    }

    public Page getParcel() {
        return this.page$$3;
    }
}
