package com.etsy.android.lib.models;

import android.os.Parcel;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.datatypes.EtsyId$$PackageHelper;
import java.util.ArrayList;
import java.util.List;
import org.parceler.ax;

public class TaxonomyNode$$Parcelable implements android.os.Parcelable, ax<TaxonomyNode> {
    public static final ao CREATOR;
    private TaxonomyNode taxonomyNode$$6;

    static {
        CREATOR = new ao();
    }

    public TaxonomyNode$$Parcelable(Parcel parcel) {
        TaxonomyNode taxonomyNode;
        if (parcel.readInt() == -1) {
            taxonomyNode = null;
        } else {
            taxonomyNode = readcom_etsy_android_lib_models_TaxonomyNode(parcel);
        }
        this.taxonomyNode$$6 = taxonomyNode;
    }

    public TaxonomyNode$$Parcelable(TaxonomyNode taxonomyNode) {
        this.taxonomyNode$$6 = taxonomyNode;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.taxonomyNode$$6 == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_TaxonomyNode(this.taxonomyNode$$6, parcel, i);
    }

    private TaxonomyNode readcom_etsy_android_lib_models_TaxonomyNode(Parcel parcel) {
        List list;
        EtsyId etsyId;
        boolean z;
        TaxonomyNode taxonomyNode = null;
        TaxonomyNode taxonomyNode2 = new TaxonomyNode();
        int readInt = parcel.readInt();
        if (readInt < 0) {
            list = null;
        } else {
            Object obj;
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < readInt; i++) {
                if (parcel.readInt() == -1) {
                    obj = null;
                } else {
                    obj = readcom_etsy_android_lib_models_TaxonomyNode(parcel);
                }
                arrayList.add(obj);
            }
            obj = arrayList;
        }
        taxonomyNode2.mChildren = list;
        taxonomyNode2.mParentPath = parcel.readString();
        if (parcel.readInt() == -1) {
            etsyId = null;
        } else {
            etsyId = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        taxonomyNode2.mTaxonomyNodeId = etsyId;
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        taxonomyNode2.mIsSuppliesTopLevel = z;
        taxonomyNode2.mName = parcel.readString();
        taxonomyNode2.mPath = parcel.readString();
        if (parcel.readInt() != -1) {
            taxonomyNode = readcom_etsy_android_lib_models_TaxonomyNode(parcel);
        }
        taxonomyNode2.mParent = taxonomyNode;
        taxonomyNode2.mLongName = parcel.readString();
        return taxonomyNode2;
    }

    private EtsyId readcom_etsy_android_lib_models_datatypes_EtsyId(Parcel parcel) {
        EtsyId etsyId = new EtsyId();
        EtsyId$$PackageHelper.accessEtsyId$FS$mId(etsyId, parcel.readString());
        return etsyId;
    }

    private void writecom_etsy_android_lib_models_TaxonomyNode(TaxonomyNode taxonomyNode, Parcel parcel, int i) {
        if (taxonomyNode.mChildren == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(taxonomyNode.mChildren.size());
            for (TaxonomyNode taxonomyNode2 : taxonomyNode.mChildren) {
                if (taxonomyNode2 == null) {
                    parcel.writeInt(-1);
                } else {
                    parcel.writeInt(1);
                    writecom_etsy_android_lib_models_TaxonomyNode(taxonomyNode2, parcel, i);
                }
            }
        }
        parcel.writeString(taxonomyNode.mParentPath);
        if (taxonomyNode.mTaxonomyNodeId == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_datatypes_EtsyId(taxonomyNode.mTaxonomyNodeId, parcel, i);
        }
        parcel.writeInt(taxonomyNode.mIsSuppliesTopLevel ? 1 : 0);
        parcel.writeString(taxonomyNode.mName);
        parcel.writeString(taxonomyNode.mPath);
        if (taxonomyNode.mParent == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_TaxonomyNode(taxonomyNode.mParent, parcel, i);
        }
        parcel.writeString(taxonomyNode.mLongName);
    }

    private void writecom_etsy_android_lib_models_datatypes_EtsyId(EtsyId etsyId, Parcel parcel, int i) {
        parcel.writeString(EtsyId$$PackageHelper.accessEtsyId$FG$mId(etsyId));
    }

    public int describeContents() {
        return 0;
    }

    public TaxonomyNode getParcel() {
        return this.taxonomyNode$$6;
    }
}
