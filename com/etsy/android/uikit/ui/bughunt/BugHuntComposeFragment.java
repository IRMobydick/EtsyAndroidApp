package com.etsy.android.uikit.ui.bughunt;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.aj;
import com.etsy.android.lib.core.img.ImageHelper;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.bughunt.IssueResult;
import com.etsy.android.lib.requests.BugHuntRequest;
import com.etsy.android.lib.requests.BugHuntRequestPost;
import com.etsy.android.lib.requests.EtsyMultipartEntity.AsyncMultipartRequestCallback;
import com.etsy.android.lib.util.CameraHelper;
import com.etsy.android.lib.util.SharedPreferencesUtility;
import com.etsy.android.lib.util.ai;
import com.etsy.android.lib.util.bl;
import com.etsy.android.uikit.ui.core.BaseFragment;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.view.ImageAttachmentLayout;
import com.google.android.gms.gcm.Task;
import java.io.File;

public class BugHuntComposeFragment extends BaseFragment implements AsyncMultipartRequestCallback<IssueResult, BugHuntRequest>, CameraHelper, ImageAttachmentLayout {
    private static final String BUNDLE_BUG_MESSAGE = "bug_message";
    private static final String BUNDLE_USERNAME = "username";
    private BugHuntActivity mBugHuntActivity;
    private ImageButton mCameraButton;
    private CameraHelper mCameraHelper;
    private OnClickListener mClickListener;
    private EditText mDescriptionEditText;
    private ImageAttachmentLayout mImageAttachment;
    private View mLoadingIndicator;
    private BroadcastReceiver mPostReceiver;
    private View mScreenshotMessage;
    private TextView mScreenshotPrompt;
    private View mUsernameContainer;
    private EditText mUsernameEditText;
    private View mView;

    /* renamed from: com.etsy.android.uikit.ui.bughunt.BugHuntComposeFragment.1 */
    class C09601 extends BroadcastReceiver {
        final /* synthetic */ BugHuntComposeFragment f4049a;

        C09601(BugHuntComposeFragment bugHuntComposeFragment) {
            this.f4049a = bugHuntComposeFragment;
        }

        public void onReceive(Context context, Intent intent) {
            if (intent.getBooleanExtra(BugHuntRequestPost.EXTRA_WAS_SUCCESS, false)) {
                this.f4049a.mView.setVisibility(0);
                this.f4049a.mLoadingIndicator.setVisibility(8);
                this.f4049a.mBugHuntActivity.displayFab(true);
                bl.m3365b(this.f4049a.getActivity(), R.supportfeedback_message_send_success);
                this.f4049a.mImageAttachment.clear();
                this.f4049a.mBugHuntActivity.setupLeaderboard();
                return;
            }
            this.f4049a.mView.setVisibility(0);
            this.f4049a.mLoadingIndicator.setVisibility(8);
            this.f4049a.mBugHuntActivity.displayFab(true);
            bl.m3365b(this.f4049a.getActivity(), R.supportfeedback_message_send_error_);
        }
    }

    /* renamed from: com.etsy.android.uikit.ui.bughunt.BugHuntComposeFragment.2 */
    class C09612 extends TrackingOnClickListener {
        final /* synthetic */ BugHuntComposeFragment f4050a;

        C09612(BugHuntComposeFragment bugHuntComposeFragment) {
            this.f4050a = bugHuntComposeFragment;
        }

        public void onViewClick(View view) {
            this.f4050a.hideKeyboard();
            this.f4050a.mCameraHelper.startImagePicker(this.f4050a, R.choose_image, null);
        }
    }

    public BugHuntComposeFragment() {
        this.mPostReceiver = new C09601(this);
        this.mClickListener = new C09612(this);
    }

    public static BugHuntComposeFragment newInstance(String str) {
        BugHuntComposeFragment bugHuntComposeFragment = new BugHuntComposeFragment();
        if (!TextUtils.isEmpty(str)) {
            Bundle bundle = new Bundle();
            bundle.putString("image_uri", str);
            bugHuntComposeFragment.setArguments(bundle);
        }
        return bugHuntComposeFragment;
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mBugHuntActivity = (BugHuntActivity) activity;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mCameraHelper = new CameraHelper(getActivity(), bundle, this);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.fragment_bughunt_compose, viewGroup, false);
        this.mView = inflate.findViewById(R.bughunt_compose_content);
        this.mLoadingIndicator = inflate.findViewById(R.loading_indicator);
        this.mCameraButton = (ImageButton) this.mView.findViewById(R.bughunt_image_add);
        this.mCameraButton.setOnClickListener(this.mClickListener);
        this.mImageAttachment = (ImageAttachmentLayout) this.mView.findViewById(R.bughunt_image_attachments);
        this.mImageAttachment.setImageAttachmentCallback(this);
        this.mUsernameEditText = (EditText) this.mView.findViewById(R.bugbounty_edit_username);
        this.mUsernameContainer = this.mView.findViewById(R.bugbounty_edit_username_container);
        this.mDescriptionEditText = (EditText) this.mView.findViewById(R.edit_bug_message);
        this.mScreenshotPrompt = (TextView) this.mView.findViewById(R.label_bugbounty_screenshot_prompt);
        this.mScreenshotMessage = this.mView.findViewById(R.footer_message);
        checkUsernameView();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BugHuntRequestPost.BROADCAST_BUGHUNT_POST);
        LocalBroadcastManager.getInstance(getActivity().getApplicationContext()).registerReceiver(this.mPostReceiver, intentFilter);
        return inflate;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (bundle != null) {
            if (bundle.containsKey(BUNDLE_BUG_MESSAGE) && bundle.get(BUNDLE_BUG_MESSAGE) != null) {
                this.mDescriptionEditText.setText(bundle.getCharSequence(BUNDLE_BUG_MESSAGE));
            }
            if (bundle.containsKey(BUNDLE_USERNAME) && bundle.get(BUNDLE_USERNAME) != null) {
                this.mUsernameEditText.setText(bundle.getCharSequence(BUNDLE_USERNAME));
            }
        }
        if (getArguments() != null && getArguments().containsKey("image_uri")) {
            String string = getArguments().getString("image_uri");
            ImageAttachmentLayout startLoading = this.mImageAttachment.startLoading();
            if (startLoading != null) {
                Pair b = ImageHelper.m1641b(string);
                this.mImageAttachment.addBitmap(startLoading, ImageHelper.m1633a(string, ((Integer) b.first).intValue(), ((Integer) b.second).intValue()), new File(string));
                checkAttachmentsView();
            }
        }
        if (getUsername() == null) {
            focusEditText(this.mUsernameEditText);
        } else {
            focusEditText(this.mDescriptionEditText);
        }
    }

    public void onResume() {
        super.onResume();
        checkAttachmentsView();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.mCameraHelper.saveState(bundle);
        bundle.putCharSequence(BUNDLE_BUG_MESSAGE, this.mDescriptionEditText.getText());
        bundle.putCharSequence(BUNDLE_USERNAME, this.mUsernameEditText.getText());
    }

    @NonNull
    public OnClickListener getOnClickListener() {
        return this.mClickListener;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        this.mCameraHelper.onActivityResult(i, i2, intent);
    }

    public void onDestroyView() {
        super.onDestroyView();
        LocalBroadcastManager.getInstance(getActivity().getApplicationContext()).unregisterReceiver(this.mPostReceiver);
    }

    public void onDestroy() {
        this.mCameraHelper = null;
        super.onDestroy();
    }

    public void onNoAvailableActivities() {
        bl.m3365b(getActivity(), R.no_available_chooser);
    }

    public Object onPreImageSave() {
        return this.mImageAttachment.startLoading();
    }

    public void onImageSaveSuccess(Object obj, Bitmap bitmap, File file) {
        this.mImageAttachment.addBitmap((ImageAttachmentLayout) obj, bitmap, file);
        checkCanAttachMore();
        checkAttachmentsView();
    }

    public void onImageSaveFail(Object obj, File file) {
        if (getActivity() != null) {
            bl.m3365b(getActivity(), R.camera_helper_image_load_error);
        }
        this.mImageAttachment.onAbort((ImageAttachmentLayout) obj, file);
        checkCanAttachMore();
    }

    public void onRequestCrop(Uri uri, Uri uri2) {
    }

    public void onPermissionGranted() {
        this.mCameraHelper.startImagePicker((Fragment) this, R.choose_image);
    }

    public void onRemove() {
        checkCanAttachMore();
        checkAttachmentsView();
    }

    public void send() {
        if (TextUtils.isEmpty(getUsername())) {
            Toast.makeText(getActivity(), "Check Username or Sign In", 0).show();
        } else if (TextUtils.isEmpty(this.mDescriptionEditText.getText())) {
            this.mDescriptionEditText.setError("Enter Description");
        } else {
            if (!aj.m1101a().m1118d()) {
                SharedPreferencesUtility.m3141e(getActivity(), getUsername());
            }
            this.mLoadingIndicator.setVisibility(0);
            this.mView.setVisibility(8);
            this.mBugHuntActivity.displayFab(false);
            hideKeyboard();
            BugHuntRequest.createIssue(this.mDescriptionEditText.getText().toString(), getResources().getString(R.bughunt_component), this.mImageAttachment.getImageFiles(), this);
        }
    }

    public void onRequestCreated(BugHuntRequest bugHuntRequest) {
        if (aj.m1101a().m1118d()) {
            bugHuntRequest.addHeader(ResponseConstants.USER_ID, aj.m1101a().m1125k().getId());
        }
        aj.m1101a().m1124j().m1663a(new BugHuntRequestPost(bugHuntRequest));
    }

    public void onRequestCreationFailed() {
        if (isVisible() && !isRemoving()) {
            Toast.makeText(getActivity(), "Error Submitting Report", 0).show();
            this.mView.setVisibility(0);
            this.mLoadingIndicator.setVisibility(8);
            this.mBugHuntActivity.displayFab(true);
        }
    }

    private void checkCanAttachMore() {
        if (this.mImageAttachment.hasSpaceAvailable()) {
            this.mCameraButton.setVisibility(0);
        } else {
            this.mCameraButton.setVisibility(8);
        }
    }

    private void checkUsernameView() {
        boolean d = aj.m1101a().m1118d();
        this.mUsernameContainer.setVisibility(d ? 8 : 0);
        CharSequence username = getUsername();
        if (!(d || username == null)) {
            this.mUsernameEditText.setText(username);
        }
        ai.m3227b(getActivity(), username != null ? this.mDescriptionEditText : this.mUsernameEditText);
    }

    private void checkAttachmentsView() {
        int size = this.mImageAttachment.getImageFiles().size();
        switch (size) {
            case Task.NETWORK_STATE_CONNECTED /*0*/:
                this.mScreenshotPrompt.setText(getResources().getString(R.bugbounty_screenshot_prompt));
                this.mScreenshotMessage.setVisibility(0);
                this.mImageAttachment.setVisibility(8);
            case Task.NETWORK_STATE_UNMETERED /*1*/:
                this.mScreenshotPrompt.setText(size + " Screenshot Attached");
                this.mScreenshotMessage.setVisibility(8);
                this.mImageAttachment.setVisibility(0);
            default:
                this.mScreenshotPrompt.setText(size + " Screenshots Attached");
                this.mScreenshotMessage.setVisibility(8);
                this.mImageAttachment.setVisibility(0);
        }
    }

    private String getUsername() {
        String e;
        if (aj.m1101a().m1118d()) {
            e = SharedPreferencesUtility.m3140e(getActivity());
        } else {
            e = SharedPreferencesUtility.m3156n(getActivity());
            if (e == null) {
                e = this.mUsernameEditText.getText().toString();
            }
        }
        if (TextUtils.isEmpty(e)) {
            return null;
        }
        return e;
    }

    private boolean validateMessage() {
        if (getUsername() == null || TextUtils.isEmpty(this.mDescriptionEditText.getText())) {
            return false;
        }
        return true;
    }

    private void hideKeyboard() {
        ai.m3225a(getActivity(), this.mView);
    }

    private void focusEditText(EditText editText) {
        editText.clearFocus();
        editText.requestFocus();
        if (getActivity() != null) {
            ai.m3226b(getActivity()).showSoftInput(editText, 0);
        }
    }
}
