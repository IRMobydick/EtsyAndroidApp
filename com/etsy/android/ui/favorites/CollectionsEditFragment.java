package com.etsy.android.ui.favorites;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.logger.ITrackedObject;
import com.etsy.android.lib.models.apiv3.Collection;
import com.etsy.android.lib.util.ai;
import com.etsy.android.lib.util.bl;
import com.etsy.android.ui.EtsyFragment;
import com.etsy.android.ui.dialog.EtsyTrioDialogFragment;
import com.etsy.android.ui.nav.Nav;
import com.etsy.android.ui.user.auth.RegisterFragment;
import com.etsy.android.ui.util.CollectionUtil;
import com.etsy.android.uikit.ui.dialog.IDialogFragment;
import com.etsy.android.uikit.util.TrackingOnClickListener;

public class CollectionsEditFragment extends EtsyFragment implements CollectionUtil {
    Collection mCollection;
    RelativeLayout mCollectionForm;
    EditText mCollectionNameField;
    private CollectionUtil mCollectionUtils;
    private View mDeleteButton;
    RadioButton mPrivateButton;
    RadioButton mPublicButton;
    private Button mSaveButton;

    /* renamed from: com.etsy.android.ui.favorites.CollectionsEditFragment.1 */
    class C06771 extends TrackingOnClickListener {
        final /* synthetic */ CollectionsEditFragment f2940a;

        C06771(CollectionsEditFragment collectionsEditFragment, ITrackedObject... iTrackedObjectArr) {
            this.f2940a = collectionsEditFragment;
            super(iTrackedObjectArr);
        }

        public void onViewClick(View view) {
            this.f2940a.goBack();
        }
    }

    /* renamed from: com.etsy.android.ui.favorites.CollectionsEditFragment.2 */
    class C06782 extends TrackingOnClickListener {
        final /* synthetic */ CollectionsEditFragment f2941a;

        C06782(CollectionsEditFragment collectionsEditFragment, ITrackedObject... iTrackedObjectArr) {
            this.f2941a = collectionsEditFragment;
            super(iTrackedObjectArr);
        }

        public void onViewClick(View view) {
            this.f2941a.mSaveButton.setEnabled(false);
            this.f2941a.mCollectionNameField.clearFocus();
            ai.m3225a(this.f2941a.getActivity(), this.f2941a.mCollectionNameField);
            this.f2941a.mCollectionUtils.m5081a(this.f2941a.mActivity, this.f2941a, this.f2941a.mCollection.getKey(), this.f2941a.mCollection.isTypeFavorites() ? null : this.f2941a.mCollectionNameField.getText().toString(), this.f2941a.mPrivateButton.isChecked());
        }
    }

    /* renamed from: com.etsy.android.ui.favorites.CollectionsEditFragment.3 */
    class C06793 extends TrackingOnClickListener {
        final /* synthetic */ CollectionsEditFragment f2942a;

        C06793(CollectionsEditFragment collectionsEditFragment, ITrackedObject... iTrackedObjectArr) {
            this.f2942a = collectionsEditFragment;
            super(iTrackedObjectArr);
        }

        public void onViewClick(View view) {
            this.f2942a.confirmDelete();
        }
    }

    /* renamed from: com.etsy.android.ui.favorites.CollectionsEditFragment.4 */
    class C06804 implements EtsyTrioDialogFragment {
        final /* synthetic */ CollectionsEditFragment f2943a;

        C06804(CollectionsEditFragment collectionsEditFragment) {
            this.f2943a = collectionsEditFragment;
        }

        public void m4224a() {
            this.f2943a.deleteCollection();
        }

        public void m4225b() {
        }

        public void m4226c() {
        }
    }

    public CollectionsEditFragment() {
        this.mCollectionUtils = null;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mCollection = (Collection) getArguments().getSerializable(Collection.TYPE_COLLECTION);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(2130903201, null);
        TextView textView = (TextView) inflate.findViewById(R.txt_title);
        View findViewById = inflate.findViewById(2131755656);
        this.mCollectionForm = (RelativeLayout) inflate.findViewById(2131755655);
        this.mCollectionNameField = (EditText) inflate.findViewById(2131755657);
        this.mPublicButton = (RadioButton) inflate.findViewById(2131755660);
        this.mPrivateButton = (RadioButton) inflate.findViewById(2131755661);
        this.mSaveButton = (Button) inflate.findViewById(2131755662);
        this.mDeleteButton = inflate.findViewById(R.delete_button);
        this.mCollectionForm.setOnClickListener(new C06771(this, this.mCollection));
        textView.setText(getString(R.collection_edit) + " " + this.mCollection.getName());
        if (this.mCollection.isTypeFavorites()) {
            findViewById.setVisibility(8);
            this.mCollectionNameField.setVisibility(8);
            ((TextView) inflate.findViewById(2131755658)).setText(getString(R.privacy_description_question_favorites));
            this.mPublicButton.setText(getString(R.privacy_description_public_favorites));
            this.mPrivateButton.setText(getString(R.privacy_description_private_favorites));
        } else {
            this.mCollectionNameField.setText(this.mCollection.getName());
        }
        if (this.mCollection.isPrivate()) {
            this.mPublicButton.setChecked(false);
            this.mPrivateButton.setChecked(true);
        } else {
            this.mPublicButton.setChecked(true);
            this.mPrivateButton.setChecked(false);
        }
        this.mSaveButton.setOnClickListener(new C06782(this, this.mCollection));
        if (this.mCollection.isTypeFavorites()) {
            this.mDeleteButton.setVisibility(8);
        } else {
            this.mDeleteButton.setOnClickListener(new C06793(this, this.mCollection));
        }
        return inflate;
    }

    private void deleteCollection() {
        this.mDeleteButton.setEnabled(false);
        this.mCollectionUtils.m5080a(this.mActivity, this, this.mCollection.getKey());
    }

    private void confirmDelete() {
        Nav.m4682a(getActivity()).m4686d().m4412a(new C06804(this), (int) R.delete, (int) R.cancel, 0, getString(R.collection_delete_warning_message));
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (!this.mCollection.isTypeFavorites()) {
            this.mCollection.setName(this.mCollectionNameField.getText().toString());
        }
        if (this.mPublicButton.isChecked()) {
            this.mCollection.setPrivacy(Collection.PRIVACY_LEVEL_PUBLIC);
        } else {
            this.mCollection.setPrivacy(RegisterFragment.GENDER_NAME_PRIVATE);
        }
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (getParentFragment() instanceof IDialogFragment) {
            IDialogFragment iDialogFragment = (IDialogFragment) getParentFragment();
            iDialogFragment.setTitle(this.mActivity.getString(R.collection_edit));
            iDialogFragment.setOkButtonVisibility(8);
        }
        this.mCollectionUtils = new CollectionUtil(getActivity(), this, "list_edit_overlay_open");
    }

    private void setResult(int i, Collection collection) {
        Intent intent = this.mActivity.getIntent();
        intent.putExtra(Collection.TYPE_COLLECTION, collection);
        if (this.mActivity.getParent() == null) {
            this.mActivity.setResult(i, intent);
        } else {
            this.mActivity.getParent().setResult(i, intent);
        }
        this.mActivity.setResult(i, intent);
    }

    public void onCollectionEdited(Collection collection) {
        setResult(612, collection);
        goBack();
    }

    public void onCollectionDeleted() {
        setResult(611, this.mCollection);
        goBack();
    }

    public void onCollectionError(String str) {
        this.mSaveButton.setEnabled(true);
        this.mDeleteButton.setEnabled(true);
        bl.m3356a(getActivity(), str);
    }

    public boolean handleBackPressed() {
        goBack();
        return true;
    }

    private void goBack() {
        this.mActivity.finish();
    }

    @NonNull
    public String getTrackingName() {
        return "list_edit_overlay_open";
    }
}
