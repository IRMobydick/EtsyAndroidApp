package com.etsy.android.lib.models.shopedit.drawableandtextrow;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.models.ShopAbout.Link;
import com.etsy.android.lib.models.shopedit.ShopEditDelegate;
import com.etsy.android.lib.models.shopedit.ShopEditPresentationItem;
import com.etsy.android.uikit.adapter.BaseRecyclerViewAdapter;
import java.util.ArrayList;
import java.util.List;
import org.parceler.Parcel;
import org.parceler.Parcels;

@Parcel
public class ShopEditAddRelatedLinksRow extends ShopEditAddContentRow {
    @NonNull
    RelatedLinksSectionInfo mLinksSectionInfo;

    @Parcel
    public class RelatedLinksSectionInfo {
        @NonNull
        List<Link> mAllLinks;

        RelatedLinksSectionInfo() {
        }

        public RelatedLinksSectionInfo(@NonNull List<Link> list) {
            this.mAllLinks = list;
        }
    }

    public /* bridge */ /* synthetic */ boolean isActionEnabled() {
        return super.isActionEnabled();
    }

    public /* bridge */ /* synthetic */ void restoreComplexStateIfNecessary(Context context) {
        super.restoreComplexStateIfNecessary(context);
    }

    ShopEditAddRelatedLinksRow() {
    }

    public ShopEditAddRelatedLinksRow(@NonNull RelatedLinksSectionInfo relatedLinksSectionInfo, @NonNull Context context) {
        super(R.add_another_link, context);
        this.mLinksSectionInfo = relatedLinksSectionInfo;
    }

    public static void handleEditResult(@NonNull RelatedLinksSectionInfo relatedLinksSectionInfo, int i, @NonNull BaseRecyclerViewAdapter<ShopEditPresentationItem> baseRecyclerViewAdapter, int i2, int i3, @NonNull Intent intent, @NonNull Context context) {
        if (i2 == 1001 && i3 == 1011 && intent.hasExtra("all_related_links")) {
            int i4;
            ShopEditPresentationItem shopEditPresentationItem;
            List list = (List) Parcels.m7495a(intent.getParcelableExtra("all_related_links"));
            List items = baseRecyclerViewAdapter.getItems();
            int i5 = -1;
            for (i4 = i; i4 >= 0; i4--) {
                shopEditPresentationItem = (ShopEditPresentationItem) items.get(i4);
                if (!(shopEditPresentationItem instanceof ShopEditRelatedLinkRow) && !(shopEditPresentationItem instanceof ShopEditAddRelatedLinksRow)) {
                    break;
                }
                i5 = i4;
            }
            if (i5 >= 0 && i5 < items.size()) {
                int size = items.size();
                i4 = -1;
                for (int i6 = i; i6 < size; i6++) {
                    shopEditPresentationItem = (ShopEditPresentationItem) items.get(i6);
                    if (!(shopEditPresentationItem instanceof ShopEditRelatedLinkRow) && !(shopEditPresentationItem instanceof ShopEditAddRelatedLinksRow)) {
                        break;
                    }
                    i4 = i6;
                }
                if (i4 >= 0 && i4 < items.size() && i4 >= i5) {
                    items.subList(i5, i4 + 1).clear();
                    items.addAll(i5, getRelatedLinksSectionItems(list, context));
                    int size2 = relatedLinksSectionInfo.mAllLinks.size();
                    int size3 = list.size();
                    if (size3 == size2) {
                        baseRecyclerViewAdapter.notifyItemChanged(i);
                    } else if (size3 < size2) {
                        baseRecyclerViewAdapter.notifyItemRemoved(i);
                    } else {
                        baseRecyclerViewAdapter.notifyItemInserted(i);
                    }
                }
            }
        }
    }

    public static List<ShopEditPresentationItem> getRelatedLinksSectionItems(@NonNull List<Link> list, @NonNull Context context) {
        List<ShopEditPresentationItem> arrayList = new ArrayList();
        RelatedLinksSectionInfo relatedLinksSectionInfo = new RelatedLinksSectionInfo(list);
        int size = list.size();
        for (int i = 0; i < size; i++) {
            arrayList.add(new ShopEditRelatedLinkRow(relatedLinksSectionInfo, (Link) list.get(i), context));
        }
        arrayList.add(new ShopEditAddRelatedLinksRow(relatedLinksSectionInfo, context));
        return arrayList;
    }

    public void editActionInitiated(int i, @NonNull ShopEditDelegate shopEditDelegate, @NonNull BaseRecyclerViewAdapter<ShopEditPresentationItem> baseRecyclerViewAdapter) {
        shopEditDelegate.addLink(this.mLinksSectionInfo.mAllLinks);
    }

    public void updateWithEditResult(@NonNull ShopEditDelegate shopEditDelegate, @NonNull RecyclerView recyclerView, @NonNull BaseRecyclerViewAdapter<ShopEditPresentationItem> baseRecyclerViewAdapter, int i, int i2, @NonNull Intent intent, @NonNull Context context, int i3) {
        handleEditResult(this.mLinksSectionInfo, i3, baseRecyclerViewAdapter, i, i2, intent, context);
    }
}
