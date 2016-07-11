package com.etsy.android.ui.user;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.EditText;
import android.widget.TextView;
import com.appboy.support.ValidationUtils;
import com.etsy.android.iconsy.views.IconView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.aj;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.util.CameraHelper;
import com.etsy.android.lib.util.SharedPreferencesUtility;
import com.etsy.android.lib.util.aa;
import com.etsy.android.lib.util.ai;
import com.etsy.android.lib.util.bl;
import com.etsy.android.ui.EtsyFragment;
import com.etsy.android.ui.nav.Nav;
import com.etsy.android.uikit.ui.dialog.IDialogFragment;
import com.etsy.android.uikit.ui.dialog.IDialogFragment.WindowMode;
import com.etsy.android.uikit.util.FragmentBackstackUtil;
import com.etsy.android.uikit.util.SupportFeedbackUtil;
import com.etsy.android.uikit.util.TabletSupportUtil;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.util.ViewTreeObserverHelper;
import com.etsy.android.uikit.view.ImageAttachmentLayout;
import java.io.File;

public class SupportFeedbackFragment extends EtsyFragment implements CameraHelper, SupportFeedbackUtil, ImageAttachmentLayout {
    private static final String BUNDLE_BUG_MESSAGE = "bug_message";
    private static final String BUNDLE_OTHER_MESSAGE = "other_message";
    private static final String BUNDLE_USER_EMAIL = "user_email";
    private static final String TAG;
    private EditText mBugMessageEditText;
    private TextView mCameraButton;
    private CameraHelper mCameraHelper;
    private OnClickListener mClickListener;
    private ImageAttachmentLayout mHolder;
    private ImageAttachmentLayout mImageAttachment;
    private boolean mImageIsAttaching;
    private boolean mIsReply;
    private boolean mIsTablet;
    private SupportFeedbackFragment mListener;
    private EditText mOtherMessageEditText;
    private IDialogFragment mParentDialog;
    private ProgressDialog mProgressDialog;
    private IconView mSendButton;
    private SupportFeedbackUtil mSupportFeedbackUtil;
    private TextWatcher mTextWatcher;
    private View mUserEmailEditContainer;
    private EditText mUserEmailEditText;
    private View mView;

    /* renamed from: com.etsy.android.ui.user.SupportFeedbackFragment.1 */
    class C08411 implements TextWatcher {
        final /* synthetic */ SupportFeedbackFragment f3480a;

        C08411(SupportFeedbackFragment supportFeedbackFragment) {
            this.f3480a = supportFeedbackFragment;
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            this.f3480a.checkSendButton();
        }

        public void afterTextChanged(Editable editable) {
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
    }

    /* renamed from: com.etsy.android.ui.user.SupportFeedbackFragment.2 */
    class C08422 implements OnGlobalLayoutListener {
        final /* synthetic */ SupportFeedbackFragment f3481a;

        C08422(SupportFeedbackFragment supportFeedbackFragment) {
            this.f3481a = supportFeedbackFragment;
        }

        public void onGlobalLayout() {
            this.f3481a.mView.setMinimumHeight(((IDialogFragment) this.f3481a.getParentFragment()).getMinHeight(WindowMode.LARGE));
            this.f3481a.mView.setMinimumWidth(((IDialogFragment) this.f3481a.getParentFragment()).getMinWidth(WindowMode.LARGE));
            ViewTreeObserverHelper.m5639b(this.f3481a.mView.getViewTreeObserver(), (OnGlobalLayoutListener) this);
        }
    }

    /* renamed from: com.etsy.android.ui.user.SupportFeedbackFragment.3 */
    class C08433 extends TrackingOnClickListener {
        final /* synthetic */ SupportFeedbackFragment f3482a;

        C08433(SupportFeedbackFragment supportFeedbackFragment) {
            this.f3482a = supportFeedbackFragment;
        }

        public void onViewClick(View view) {
            switch (view.getId()) {
                case R.btn_x /*2131755557*/:
                    FragmentActivity activity = this.f3482a.getActivity();
                    if (activity != null) {
                        FragmentBackstackUtil.m5542b(activity.getSupportFragmentManager(), Nav.m4682a(activity));
                    }
                case R.btn_send /*2131755559*/:
                    this.f3482a.send();
                case R.button_image /*2131755633*/:
                    this.f3482a.hideKeyboard();
                    this.f3482a.mCameraHelper.startImagePicker(this.f3482a, (int) R.choose_image);
                default:
            }
        }
    }

    public SupportFeedbackFragment() {
        this.mIsReply = false;
        this.mTextWatcher = new C08411(this);
        this.mClickListener = new C08433(this);
    }

    static {
        TAG = EtsyDebug.m1891a(SupportFeedbackFragment.class);
    }

    public static SupportFeedbackFragment newInstance() {
        return new SupportFeedbackFragment();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mCameraHelper = new CameraHelper(getActivity().getApplicationContext(), bundle, this);
        this.mSupportFeedbackUtil = new SupportFeedbackUtil(this.mActivity, this);
        this.mIsTablet = new TabletSupportUtil(getActivity()).m5621a();
        if (!this.mIsTablet) {
            setHasOptionsMenu(true);
        }
        if (bundle != null) {
        }
        if (getActivity() instanceof SupportFeedbackFragment) {
            this.mListener = (SupportFeedbackFragment) getActivity();
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mView = layoutInflater.inflate(R.fragment_supportfeedback_compose, null);
        this.mCameraButton = (TextView) this.mView.findViewById(R.button_image);
        this.mCameraButton.setOnClickListener(this.mClickListener);
        this.mImageAttachment = (ImageAttachmentLayout) this.mView.findViewById(R.linear_supportfeedback_attachments);
        this.mImageAttachment.setImageAttachmentCallback(this);
        this.mBugMessageEditText = (EditText) this.mView.findViewById(R.edit_bug_message);
        this.mBugMessageEditText.addTextChangedListener(this.mTextWatcher);
        this.mOtherMessageEditText = (EditText) this.mView.findViewById(R.edit_other_message);
        this.mOtherMessageEditText.addTextChangedListener(this.mTextWatcher);
        this.mUserEmailEditText = (EditText) this.mView.findViewById(R.edit_useremail);
        this.mUserEmailEditText.addTextChangedListener(this.mTextWatcher);
        this.mUserEmailEditContainer = this.mView.findViewById(R.edit_useremail_container);
        this.mProgressDialog = bl.m3364b(getActivity(), getResources().getString(R.supportfeedback_message_sending_v2));
        checkEmailView();
        return this.mView;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (bundle != null) {
            if (bundle.containsKey(BUNDLE_BUG_MESSAGE) && bundle.get(BUNDLE_BUG_MESSAGE) != null) {
                this.mBugMessageEditText.setText(bundle.getCharSequence(BUNDLE_BUG_MESSAGE));
            }
            if (bundle.containsKey(BUNDLE_OTHER_MESSAGE) && bundle.get(BUNDLE_OTHER_MESSAGE) != null) {
                this.mOtherMessageEditText.setText(bundle.getCharSequence(BUNDLE_OTHER_MESSAGE));
            }
            if (bundle.containsKey(BUNDLE_USER_EMAIL) && bundle.get(BUNDLE_USER_EMAIL) != null) {
                this.mUserEmailEditText.setText(bundle.getCharSequence(BUNDLE_USER_EMAIL));
            }
        }
        if (getParentFragment() instanceof IDialogFragment) {
            this.mParentDialog = (IDialogFragment) getParentFragment();
            this.mParentDialog.hideHeader();
            this.mParentDialog.setWindowMode(WindowMode.LARGE);
            setupHeaderView();
        }
    }

    private void setupHeaderView() {
        this.mView.findViewById(R.supportfeedback_header).setVisibility(0);
        this.mSendButton = (IconView) this.mView.findViewById(R.btn_send);
        this.mSendButton.setOnClickListener(this.mClickListener);
        this.mSendButton.setEnabled(validateMessage());
        this.mView.findViewById(R.btn_x).setOnClickListener(this.mClickListener);
        setHeaderViewHeight();
    }

    public void setHeaderViewHeight() {
        this.mView.getViewTreeObserver().addOnGlobalLayoutListener(new C08422(this));
    }

    public void onResume() {
        super.onResume();
    }

    public void onFragmentResume() {
        super.onFragmentResume();
    }

    public void onSaveInstanceState(Bundle bundle) {
        this.mCameraHelper.saveState(bundle);
        bundle.putCharSequence(BUNDLE_BUG_MESSAGE, this.mBugMessageEditText.getText());
        bundle.putCharSequence(BUNDLE_OTHER_MESSAGE, this.mOtherMessageEditText.getText());
        bundle.putCharSequence(BUNDLE_USER_EMAIL, this.mUserEmailEditText.getText());
        super.onSaveInstanceState(bundle);
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.mIsTablet && this.mParentDialog != null) {
            setHeaderViewHeight();
        }
        layoutImageAttachments();
    }

    private void layoutImageAttachments() {
        this.mImageAttachment.refreshRatio();
        this.mImageAttachment.calcSizes(1073741824);
        this.mImageAttachment.requestLayout();
    }

    public void onCreateOptionsMenuWithIcons(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.convo_compose_action_bar, menu);
    }

    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem findItem = menu.findItem(R.menu_send_reply);
        if (findItem != null) {
            Drawable icon = findItem.getIcon();
            boolean z = !this.mImageIsAttaching && validateMessage();
            icon.setAlpha(z ? ValidationUtils.APPBOY_STRING_MAX_LENGTH : 75);
            findItem.setEnabled(z);
        }
        super.onPrepareOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.menu_send_reply /*2131756541*/:
                send();
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    @NonNull
    public OnClickListener getOnClickListener() {
        return this.mClickListener;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        this.mCameraHelper.onActivityResult(i, i2, intent);
    }

    public void onDestroy() {
        this.mCameraHelper = null;
        super.onDestroy();
    }

    public void onMessageQueued() {
        this.mProgressDialog.dismiss();
        hideKeyboard();
        bl.m3365b(getActivity(), (int) R.supportfeedback_message_send_success);
        this.mImageAttachment.clear();
        if (this.mListener != null) {
            this.mListener.onMessageSent();
        }
    }

    public void onMessageError() {
        this.mProgressDialog.dismiss();
    }

    public void onNoAvailableActivities() {
        bl.m3365b(this.mActivity, (int) R.no_available_chooser);
    }

    public Object onPreImageSave() {
        ImageAttachmentLayout startLoading = this.mImageAttachment.startLoading();
        this.mImageIsAttaching = true;
        checkSendButton();
        return startLoading;
    }

    public void onImageSaveSuccess(Object obj, Bitmap bitmap, File file) {
        this.mImageAttachment.addBitmap((ImageAttachmentLayout) obj, bitmap, file);
        this.mImageIsAttaching = false;
        checkCanAttachMore();
        checkSendButton();
    }

    public void onImageSaveFail(Object obj, File file) {
        if (this.mActivity != null) {
            bl.m3365b(this.mActivity, (int) R.camera_helper_image_load_error);
        }
        this.mImageAttachment.onAbort((ImageAttachmentLayout) obj, file);
        this.mImageIsAttaching = false;
        checkCanAttachMore();
        checkSendButton();
    }

    public void onRequestCrop(Uri uri, Uri uri2) {
    }

    public void onPermissionGranted() {
        this.mCameraHelper.startImagePicker((Fragment) this, (int) R.choose_image);
    }

    public void onRemove() {
        checkCanAttachMore();
    }

    private String getUserEmail() {
        String f;
        if (aj.m1101a().m1118d()) {
            f = SharedPreferencesUtility.m3142f(this.mContext);
        } else {
            f = this.mUserEmailEditText.getText().toString();
        }
        if (TextUtils.isEmpty(f)) {
            return null;
        }
        return f;
    }

    private void send() {
        this.mProgressDialog = bl.m3364b(getActivity(), getResources().getString(R.supportfeedback_message_sending_v2));
        this.mProgressDialog.show();
        this.mSupportFeedbackUtil.m5567a(this.mBugMessageEditText.getText().toString() + "\n\n" + this.mOtherMessageEditText.getText().toString(), aa.m3166a(getActivity()), getUserEmail(), this.mImageAttachment.getImageFiles());
    }

    private void checkCanAttachMore() {
        if (this.mImageAttachment.hasSpaceAvailable()) {
            this.mCameraButton.setVisibility(0);
        } else {
            this.mCameraButton.setVisibility(8);
        }
    }

    private void checkSendButton() {
        if (this.mSendButton != null) {
            IconView iconView = this.mSendButton;
            boolean z = !this.mImageIsAttaching && validateMessage();
            iconView.setEnabled(z);
            return;
        }
        this.mActivity.invalidateOptionsMenu();
    }

    private void checkEmailView() {
        if (!aj.m1101a().m1118d()) {
            this.mUserEmailEditContainer.setVisibility(0);
            if (this.mUserEmailEditText != null) {
                ai.m3227b(this.mContext, this.mUserEmailEditText);
            }
        } else if (getUserEmail() != null) {
            this.mUserEmailEditContainer.setVisibility(8);
            if (this.mBugMessageEditText != null) {
                ai.m3227b(this.mContext, this.mBugMessageEditText);
            }
        }
    }

    private boolean validateMessage() {
        if (getUserEmail() == null) {
            return false;
        }
        if (TextUtils.isEmpty(this.mBugMessageEditText.getText()) && TextUtils.isEmpty(this.mOtherMessageEditText.getText())) {
            return false;
        }
        return true;
    }

    private void hideKeyboard() {
        ai.m3225a(this.mActivity, this.mView);
    }

    private void focusEditText(EditText editText) {
        editText.clearFocus();
        editText.requestFocus();
        if (getActivity() != null) {
            ai.m3226b(getActivity()).showSoftInput(editText, 0);
        }
    }

    @NonNull
    public String getTrackingName() {
        return "supportfeedback_compose";
    }
}
