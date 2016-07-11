package com.etsy.android.ui.favorites;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.ListingCollection;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.util.ai;
import com.etsy.android.lib.util.bl;
import com.etsy.android.ui.EtsyFragment;
import com.etsy.android.ui.util.CollectionUtil;
import com.etsy.android.uikit.ui.dialog.IDialogFragment;
import com.etsy.android.uikit.util.TrackingOnClickListener;

public class ListingCollectionsCreateFragment extends EtsyFragment implements CollectionUtil {
    private Button mAddButton;
    CollectionUtil mCallback;
    private CollectionUtil mCollectionUtils;
    private int mImageHeight;
    private int mImageWidth;
    private EtsyId mListingId;
    private String mListingImageUrl;

    /* renamed from: com.etsy.android.ui.favorites.ListingCollectionsCreateFragment.1 */
    class C06821 extends TrackingOnClickListener {
        final /* synthetic */ EditText f2945a;
        final /* synthetic */ ListingCollectionsCreateFragment f2946b;

        C06821(ListingCollectionsCreateFragment listingCollectionsCreateFragment, AnalyticsLogAttribute analyticsLogAttribute, EtsyId etsyId, EditText editText) {
            this.f2946b = listingCollectionsCreateFragment;
            this.f2945a = editText;
            super(analyticsLogAttribute, etsyId);
        }

        public void onViewClick(View view) {
            this.f2946b.mAddButton.setEnabled(false);
            this.f2945a.clearFocus();
            ai.m3225a(this.f2946b.getActivity(), this.f2945a);
            this.f2946b.mCollectionUtils.m5082a(this.f2946b.mActivity, this.f2946b, this.f2945a.getText().toString(), false, this.f2946b.mListingId);
        }
    }

    public ListingCollectionsCreateFragment() {
        this.mCallback = null;
        this.mCollectionUtils = null;
    }

    public void setListingCollectionCreationListener(CollectionUtil collectionUtil) {
        this.mCallback = collectionUtil;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        this.mListingId = (EtsyId) arguments.getSerializable(ResponseConstants.LISTING_ID);
        this.mListingImageUrl = arguments.getString(ResponseConstants.LISTING_IMAGE_URL);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Resources resources = getResources();
        this.mImageWidth = resources.getDimensionPixelOffset(R.review_image_width);
        this.mImageHeight = resources.getDimensionPixelOffset(R.review_image_height);
        View inflate = layoutInflater.inflate(2130903195, null);
        ImageView imageView = (ImageView) inflate.findViewById(2131755639);
        EditText editText = (EditText) inflate.findViewById(2131755641);
        this.mAddButton = (Button) inflate.findViewById(2131755642);
        getImageBatch().m1570a(this.mListingImageUrl, imageView, this.mImageWidth, this.mImageHeight);
        this.mAddButton.setOnClickListener(new C06821(this, AnalyticsLogAttribute.LISTING_ID, this.mListingId, editText));
        ai.m3227b(getActivity(), editText);
        return inflate;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (getParentFragment() instanceof IDialogFragment) {
            IDialogFragment iDialogFragment = (IDialogFragment) getParentFragment();
            iDialogFragment.setTitle(this.mActivity.getString(R.collection_create_new));
            iDialogFragment.setOkButtonVisibility(8);
        }
        this.mCollectionUtils = new CollectionUtil(getActivity(), this, "list_create_open");
    }

    public void onListingCollectionAdded(ListingCollection listingCollection) {
        if (this.mCallback != null) {
            this.mCallback.onListingCollectionAdded(listingCollection);
        }
        goBack();
    }

    public void onListingCollectionError(String str) {
        this.mAddButton.setEnabled(true);
        if (this.mCallback != null) {
            this.mCallback.onListingCollectionError(str);
        }
        bl.m3356a(getActivity(), str);
    }

    public boolean handleBackPressed() {
        goBack();
        return true;
    }

    private void goBack() {
        if (getParentFragment() != null && getParentFragment().getChildFragmentManager() != null) {
            getParentFragment().getChildFragmentManager().popBackStack();
        }
    }

    @NonNull
    public String getTrackingName() {
        return "list_create_open";
    }
}
