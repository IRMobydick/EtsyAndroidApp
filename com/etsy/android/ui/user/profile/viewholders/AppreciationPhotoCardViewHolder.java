package com.etsy.android.ui.user.profile.viewholders;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.img.ImageBatch;
import com.etsy.android.lib.logger.ITrackedObject;
import com.etsy.android.lib.models.apiv3.AppreciationPhoto;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.ui.nav.EtsyEventTracker;
import com.etsy.android.ui.nav.Nav;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.view.ImageViewWithAspectRatio;
import com.etsy.android.uikit.viewholder.BaseViewHolder;

public class AppreciationPhotoCardViewHolder extends BaseViewHolder<AppreciationPhoto> {
    private final View mClickRegion;
    private final ImageBatch mImageBatch;
    private final String mPageInView;
    private final ImageViewWithAspectRatio mPhoto;

    /* renamed from: com.etsy.android.ui.user.profile.viewholders.AppreciationPhotoCardViewHolder.1 */
    class C08931 extends TrackingOnClickListener {
        final /* synthetic */ AppreciationPhoto f3615a;
        final /* synthetic */ String f3616b;
        final /* synthetic */ AppreciationPhotoCardViewHolder f3617c;

        C08931(AppreciationPhotoCardViewHolder appreciationPhotoCardViewHolder, ITrackedObject[] iTrackedObjectArr, AppreciationPhoto appreciationPhoto, String str) {
            this.f3617c = appreciationPhotoCardViewHolder;
            this.f3615a = appreciationPhoto;
            this.f3616b = str;
            super(iTrackedObjectArr);
        }

        public void onViewClick(View view) {
            EtsyId transactionId = this.f3615a.getTransactionId();
            EtsyEventTracker.m4591h(transactionId, this.f3616b);
            Nav.m4682a((FragmentActivity) view.getContext()).m4683a().m4526f(transactionId);
        }
    }

    public AppreciationPhotoCardViewHolder(@NonNull View view, boolean z, @NonNull String str) {
        super(view);
        this.mClickRegion = view.findViewById(R.click_region);
        this.mPhoto = (ImageViewWithAspectRatio) view.findViewById(R.appreciation_photo);
        this.mImageBatch = new ImageBatch();
        if (z) {
            this.itemView.getLayoutParams().width = this.itemView.getResources().getDimensionPixelSize(2131361898);
        }
        this.mPageInView = str;
    }

    public void bind(@NonNull AppreciationPhoto appreciationPhoto) {
        this.mPhoto.setImageInfo(appreciationPhoto, this.mImageBatch);
        String str = this.mPageInView;
        this.mClickRegion.setOnClickListener(new C08931(this, new ITrackedObject[]{appreciationPhoto}, appreciationPhoto, str));
    }
}
