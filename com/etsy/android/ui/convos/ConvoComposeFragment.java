package com.etsy.android.ui.convos;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.appboy.support.ValidationUtils;
import com.etsy.android.lib.R;
import com.etsy.android.lib.convos.ConvoHelperDeprecated;
import com.etsy.android.lib.convos.Draft;
import com.etsy.android.lib.convos.MessageValidator;
import com.etsy.android.lib.convos.SendListener;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.util.CameraHelper;
import com.etsy.android.lib.util.NetworkUtils;
import com.etsy.android.lib.util.ai;
import com.etsy.android.lib.util.bh;
import com.etsy.android.lib.util.bl;
import com.etsy.android.ui.EtsyFragment;
import com.etsy.android.ui.nav.Nav;
import com.etsy.android.uikit.adapter.RecentContactsAdapter;
import com.etsy.android.uikit.ui.dialog.IDialogFragment;
import com.etsy.android.uikit.ui.dialog.IDialogFragment.WindowMode;
import com.etsy.android.uikit.util.FragmentBackstackUtil;
import com.etsy.android.uikit.util.TabletSupportUtil;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.util.ViewTreeObserverHelper;
import com.etsy.android.uikit.view.ContactsAutoComplete;
import com.etsy.android.uikit.view.ImageAttachmentLayout;
import java.io.File;

public class ConvoComposeFragment extends EtsyFragment implements ConvoHelperDeprecated, CameraHelper, ImageAttachmentLayout {
    private static final String TAG;
    private TextView mCameraButton;
    private CameraHelper mCameraHelper;
    private TrackingOnClickListener mClickListener;
    private int mConvoId;
    private Draft mDraft;
    private ImageAttachmentLayout mHolder;
    private ImageAttachmentLayout mImageAttachment;
    private boolean mImageIsAttaching;
    private boolean mIsReply;
    private boolean mIsTablet;
    private SendListener mListener;
    private EditText mMessageEditText;
    private IDialogFragment mParentDialog;
    private ProgressDialog mProgressDialog;
    private View mSendButton;
    private EditText mSubjectEditText;
    private TextWatcher mTextWatcher;
    private AutoCompleteTextView mUserNameEditText;
    private View mView;

    /* renamed from: com.etsy.android.ui.convos.ConvoComposeFragment.1 */
    class C06261 extends TrackingOnClickListener {
        final /* synthetic */ ConvoComposeFragment f2644a;

        C06261(ConvoComposeFragment convoComposeFragment) {
            this.f2644a = convoComposeFragment;
        }

        public void onViewClick(View view) {
            switch (view.getId()) {
                case R.btn_x /*2131755557*/:
                    if (this.f2644a.getActivity() != null) {
                        FragmentBackstackUtil.m5542b(this.f2644a.getFragmentManager(), Nav.m4682a(this.f2644a.getActivity()));
                    }
                case R.btn_send /*2131755559*/:
                    this.f2644a.send();
                case R.button_image /*2131755633*/:
                    this.f2644a.hideKeyboard();
                    this.f2644a.mCameraHelper.startImagePicker(this.f2644a, (int) R.choose_image);
                default:
            }
        }
    }

    /* renamed from: com.etsy.android.ui.convos.ConvoComposeFragment.2 */
    class C06272 implements OnItemClickListener {
        final /* synthetic */ ConvoComposeFragment f2645a;

        C06272(ConvoComposeFragment convoComposeFragment) {
            this.f2645a = convoComposeFragment;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            this.f2645a.mSubjectEditText.requestFocus();
        }
    }

    /* renamed from: com.etsy.android.ui.convos.ConvoComposeFragment.3 */
    class C06283 implements TextWatcher {
        final /* synthetic */ ConvoComposeFragment f2646a;

        C06283(ConvoComposeFragment convoComposeFragment) {
            this.f2646a = convoComposeFragment;
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            this.f2646a.checkSendButton();
        }

        public void afterTextChanged(Editable editable) {
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
    }

    /* renamed from: com.etsy.android.ui.convos.ConvoComposeFragment.4 */
    class C06294 implements OnGlobalLayoutListener {
        final /* synthetic */ ConvoComposeFragment f2647a;

        C06294(ConvoComposeFragment convoComposeFragment) {
            this.f2647a = convoComposeFragment;
        }

        public void onGlobalLayout() {
            this.f2647a.mView.setMinimumHeight(((IDialogFragment) this.f2647a.getParentFragment()).getMinHeight(WindowMode.LARGE));
            this.f2647a.mView.setMinimumWidth(((IDialogFragment) this.f2647a.getParentFragment()).getMinWidth(WindowMode.LARGE));
            ViewTreeObserverHelper.m5639b(this.f2647a.mView.getViewTreeObserver(), (OnGlobalLayoutListener) this);
        }
    }

    public ConvoComposeFragment() {
        this.mIsReply = false;
        this.mTextWatcher = new C06283(this);
    }

    static {
        TAG = EtsyDebug.m1891a(ConvoComposeFragment.class);
    }

    public static ConvoComposeFragment newInstance() {
        return new ConvoComposeFragment();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mCameraHelper = new CameraHelper(getActivity().getApplicationContext(), bundle, this);
        this.mIsTablet = new TabletSupportUtil(getActivity()).m5621a();
        if (!this.mIsTablet) {
            setHasOptionsMenu(true);
        }
        if (bundle != null) {
            this.mDraft = ConvoHelperDeprecated.m989b(this.mActivity);
        }
        if (getActivity() instanceof SendListener) {
            this.mListener = (SendListener) getActivity();
        }
        this.mClickListener = new C06261(this);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mView = layoutInflater.inflate(2130903192, null);
        this.mCameraButton = (TextView) this.mView.findViewById(R.button_image);
        this.mCameraButton.setOnClickListener(this.mClickListener);
        this.mImageAttachment = (ImageAttachmentLayout) this.mView.findViewById(2131755632);
        this.mImageAttachment.setImageAttachmentCallback(this);
        this.mMessageEditText = (EditText) this.mView.findViewById(2131755631);
        this.mMessageEditText.addTextChangedListener(this.mTextWatcher);
        this.mUserNameEditText = (ContactsAutoComplete) this.mView.findViewById(2131755629);
        this.mUserNameEditText.setAdapter(new RecentContactsAdapter(getActivity(), getImageBatch()));
        this.mUserNameEditText.addTextChangedListener(this.mTextWatcher);
        this.mUserNameEditText.setFocusableInTouchMode(true);
        this.mUserNameEditText.setOnItemClickListener(new C06272(this));
        this.mSubjectEditText = (EditText) this.mView.findViewById(2131755630);
        this.mSubjectEditText.addTextChangedListener(this.mTextWatcher);
        this.mProgressDialog = bl.m3364b(getActivity(), getResources().getString(R.convo_message_sending_v2));
        setUpReplyOrNewMessage();
        return this.mView;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (getParentFragment() instanceof IDialogFragment) {
            this.mParentDialog = (IDialogFragment) getParentFragment();
            this.mParentDialog.hideHeader();
            this.mParentDialog.setWindowMode(WindowMode.LARGE);
            setupHeaderView();
        }
    }

    private void setupHeaderView() {
        ((ViewStub) this.mView.findViewById(R.convo_header)).setVisibility(0);
        this.mSendButton = this.mView.findViewById(R.btn_send);
        this.mSendButton.setOnClickListener(this.mClickListener);
        this.mSendButton.setEnabled(preValidateMessage());
        this.mView.findViewById(R.btn_x).setOnClickListener(this.mClickListener);
        setHeaderViewHeight();
    }

    public void setHeaderViewHeight() {
        this.mView.getViewTreeObserver().addOnGlobalLayoutListener(new C06294(this));
    }

    public void onResume() {
        super.onResume();
        setTitle();
    }

    public void onFragmentResume() {
        super.onFragmentResume();
        setTitle();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.mCameraHelper.saveState(bundle);
        ConvoHelperDeprecated.m985a(this.mActivity, getDraft());
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
            boolean z = !this.mImageIsAttaching && preValidateMessage();
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

    public void onActivityResult(int i, int i2, Intent intent) {
        this.mCameraHelper.onActivityResult(i, i2, intent);
    }

    private void setUsernameAndRequestSubjectFocus(String str) {
        ((EditText) this.mView.findViewById(2131755629)).setText(str);
        focusEditText(this.mSubjectEditText);
    }

    private void setUpReplyOrNewMessage() {
        if (getArguments() != null) {
            CharSequence string = getArguments().getString("username");
            String string2 = getArguments().getString(ResponseConstants.SUBJECT);
            String string3 = getArguments().getString(ResponseConstants.TRANSLATED_CONVERSATION_MESSAGE);
            this.mIsReply = getArguments().containsKey("convo_id");
            this.mConvoId = getArguments().getInt("convo_id");
            if (bh.m3340a((String) string)) {
                if (this.mIsReply) {
                    string = String.format(getString(R.to_user), new Object[]{string});
                }
                this.mUserNameEditText.setText(string);
                this.mUserNameEditText.setEnabled(false);
                this.mSubjectEditText.requestFocus();
            }
            if (bh.m3340a(string2)) {
                if (this.mIsReply) {
                    string = String.format(getString(R.re_subject), new Object[]{string2});
                } else {
                    Object obj = string2;
                }
                this.mSubjectEditText.setText(string);
                this.mSubjectEditText.setEnabled(false);
                this.mMessageEditText.requestFocus();
            }
            if (bh.m3340a(string3)) {
                this.mMessageEditText.setText(string3);
                this.mMessageEditText.requestFocus();
                this.mMessageEditText.setSelection(string3.length());
            }
        }
        if (this.mDraft != null && this.mDraft.getConvoId() == 0) {
            this.mMessageEditText.setText(this.mDraft.getMessage());
            this.mMessageEditText.setSelection(this.mDraft.getCursorStartPosition(), this.mDraft.getCursorEndPosition());
            this.mUserNameEditText.setText(this.mDraft.getUserName());
            this.mSubjectEditText.setText(this.mDraft.getSubject());
            if (!this.mIsTablet) {
                this.mImageAttachment.setImages(this.mDraft.getImages());
            }
            checkCanAttachMore();
        }
    }

    private Draft getDraft() {
        Draft saveCursorPosition = new Draft().message(this.mMessageEditText.getText().toString()).subject(this.mSubjectEditText.getText().toString()).userName(this.mUserNameEditText.getText().toString()).saveCursorPosition(this.mMessageEditText.getSelectionStart(), this.mMessageEditText.getSelectionEnd());
        if (!this.mIsTablet) {
            saveCursorPosition.images(this.mImageAttachment.getImageFiles());
        }
        return saveCursorPosition;
    }

    private void setTitle() {
        if (!this.mIsTablet) {
            CharSequence string = getResources().getString(R.convo_compose_new_title);
            if (this.mIsReply) {
                string = String.format(getResources().getString(R.convo_compose_reply), new Object[]{getArguments().getString("username")});
            }
            this.mActivity.setTitle(string);
        }
    }

    public void onDestroy() {
        this.mCameraHelper = null;
        super.onDestroy();
    }

    public void onPreSendMessage() {
        this.mProgressDialog = bl.m3364b(getActivity(), getResources().getString(R.convo_message_sending_v2));
        this.mProgressDialog.show();
    }

    public void onMessageSent() {
        this.mProgressDialog.dismiss();
        hideKeyboard();
        bl.m3365b(getActivity(), (int) R.convo_message_send_success);
        this.mImageAttachment.clear();
        ConvoHelperDeprecated.m990c(this.mActivity);
        LocalBroadcastManager.getInstance(this.mActivity).sendBroadcast(new Intent("com.etsy.android.convos.MESSAGE_SENT"));
        if (this.mListener != null) {
            this.mListener.onMessageSent();
        }
    }

    public void onMessageError(String str) {
        this.mProgressDialog.dismiss();
        Toast makeText = Toast.makeText(getActivity(), str, 0);
        makeText.setGravity(16, 0, 0);
        makeText.show();
    }

    public void onNoAvailableActivities() {
        bl.m3365b(this.mActivity, (int) R.no_available_chooser);
    }

    public Object onPreImageSave() {
        this.mImageIsAttaching = true;
        checkSendButton();
        return this.mImageAttachment.startLoading();
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

    private void send() {
        if (NetworkUtils.m3107a().m3114b()) {
            new ConvoHelperDeprecated(this.mActivity, this).m991a(new Draft().userName(this.mUserNameEditText.getText().toString()).subject(this.mSubjectEditText.getText().toString()).message(this.mMessageEditText.getText().toString()).images(this.mImageAttachment.getImageFiles()).convoId((long) this.mConvoId));
        } else {
            bl.m3355a(this.mActivity, (int) R.network_unavailable);
        }
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
            View view = this.mSendButton;
            boolean z = !this.mImageIsAttaching && preValidateMessage();
            view.setEnabled(z);
            return;
        }
        this.mActivity.invalidateOptionsMenu();
    }

    private boolean preValidateMessage() {
        if (this.mUserNameEditText == null || this.mSubjectEditText == null || this.mMessageEditText == null) {
            return false;
        }
        return MessageValidator.m1011b(this.mActivity, this.mUserNameEditText.getText().toString(), this.mSubjectEditText.getText().toString(), this.mMessageEditText.getText().toString());
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

    public void onDestroyView() {
        if (this.mUserNameEditText != null) {
            RecentContactsAdapter recentContactsAdapter = (RecentContactsAdapter) this.mUserNameEditText.getAdapter();
            if (!(recentContactsAdapter == null || recentContactsAdapter.getCursor() == null)) {
                recentContactsAdapter.getCursor().close();
            }
        }
        super.onDestroyView();
    }
}
