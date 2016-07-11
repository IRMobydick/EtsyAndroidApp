package com.appboy.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import com.appboy.Appboy;
import com.appboy.support.ValidationUtils;
import com.appboy.ui.support.StringUtils;

public class AppboyFeedbackFragment extends Fragment {
    private Button mCancelButton;
    private OnClickListener mCancelListener;
    private EditText mEmailEditText;
    private boolean mErrorMessageShown;
    private FeedbackFinishedListener mFeedbackFinishedListener;
    private CheckBox mIsBugCheckBox;
    private EditText mMessageEditText;
    private int mOriginalSoftInputMode;
    private Button mSendButton;
    private TextWatcher mSendButtonWatcher;
    private OnClickListener mSendListener;

    /* renamed from: com.appboy.ui.AppboyFeedbackFragment.1 */
    class C03881 implements TextWatcher {
        C03881() {
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void afterTextChanged(Editable editable) {
            if (AppboyFeedbackFragment.this.mErrorMessageShown) {
                AppboyFeedbackFragment.this.ensureSendButton();
            }
        }
    }

    /* renamed from: com.appboy.ui.AppboyFeedbackFragment.2 */
    class C03892 implements OnClickListener {
        C03892() {
        }

        public void onClick(View view) {
            AppboyFeedbackFragment.this.hideSoftKeyboard();
            if (AppboyFeedbackFragment.this.mFeedbackFinishedListener != null) {
                AppboyFeedbackFragment.this.mFeedbackFinishedListener.onFeedbackFinished(FeedbackResult.CANCELLED);
            }
            AppboyFeedbackFragment.this.clearData();
        }
    }

    /* renamed from: com.appboy.ui.AppboyFeedbackFragment.3 */
    class C03903 implements OnClickListener {
        C03903() {
        }

        public void onClick(View view) {
            if (AppboyFeedbackFragment.this.ensureSendButton()) {
                AppboyFeedbackFragment.this.hideSoftKeyboard();
                boolean isChecked = AppboyFeedbackFragment.this.mIsBugCheckBox.isChecked();
                String obj = AppboyFeedbackFragment.this.mMessageEditText.getText().toString();
                String obj2 = AppboyFeedbackFragment.this.mEmailEditText.getText().toString();
                if (AppboyFeedbackFragment.this.mFeedbackFinishedListener != null) {
                    obj = AppboyFeedbackFragment.this.mFeedbackFinishedListener.beforeFeedbackSubmitted(obj);
                }
                boolean submitFeedback = Appboy.getInstance(AppboyFeedbackFragment.this.getActivity()).submitFeedback(obj2, obj, isChecked);
                if (AppboyFeedbackFragment.this.mFeedbackFinishedListener != null) {
                    AppboyFeedbackFragment.this.mFeedbackFinishedListener.onFeedbackFinished(submitFeedback ? FeedbackResult.SENT : FeedbackResult.ERROR);
                }
                AppboyFeedbackFragment.this.clearData();
                return;
            }
            AppboyFeedbackFragment.this.mErrorMessageShown = true;
        }
    }

    public interface FeedbackFinishedListener {
        String beforeFeedbackSubmitted(String str);

        void onFeedbackFinished(FeedbackResult feedbackResult);
    }

    public enum FeedbackResult {
        SENT,
        CANCELLED,
        ERROR
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mSendButtonWatcher = new C03881();
        this.mCancelListener = new C03892();
        this.mSendListener = new C03903();
        setRetainInstance(true);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(C0401R.layout.com_appboy_feedback, viewGroup, false);
        this.mCancelButton = (Button) inflate.findViewById(C0401R.id.com_appboy_feedback_cancel);
        this.mSendButton = (Button) inflate.findViewById(C0401R.id.com_appboy_feedback_send);
        this.mIsBugCheckBox = (CheckBox) inflate.findViewById(C0401R.id.com_appboy_feedback_is_bug);
        this.mMessageEditText = (EditText) inflate.findViewById(C0401R.id.com_appboy_feedback_message);
        this.mEmailEditText = (EditText) inflate.findViewById(C0401R.id.com_appboy_feedback_email);
        this.mMessageEditText.addTextChangedListener(this.mSendButtonWatcher);
        this.mEmailEditText.addTextChangedListener(this.mSendButtonWatcher);
        this.mCancelButton.setOnClickListener(this.mCancelListener);
        this.mSendButton.setOnClickListener(this.mSendListener);
        return inflate;
    }

    public void onResume() {
        super.onResume();
        Appboy.getInstance(getActivity()).logFeedbackDisplayed();
        Context activity = getActivity();
        Window window = activity.getWindow();
        this.mOriginalSoftInputMode = window.getAttributes().softInputMode;
        window.setSoftInputMode(16);
        Appboy.getInstance(activity).logFeedbackDisplayed();
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mMessageEditText.removeTextChangedListener(this.mSendButtonWatcher);
        this.mEmailEditText.removeTextChangedListener(this.mSendButtonWatcher);
    }

    public void setFeedbackFinishedListener(FeedbackFinishedListener feedbackFinishedListener) {
        this.mFeedbackFinishedListener = feedbackFinishedListener;
    }

    private boolean validatedMessage() {
        boolean z = (this.mMessageEditText.getText() == null || StringUtils.isNullOrBlank(this.mMessageEditText.getText().toString())) ? false : true;
        if (z) {
            this.mMessageEditText.setError(null);
        } else {
            this.mMessageEditText.setError(getResources().getString(C0401R.string.com_appboy_feedback_form_invalid_message));
        }
        return z;
    }

    private boolean validatedEmail() {
        Object obj = 1;
        boolean z = (this.mEmailEditText.getText() == null || StringUtils.isNullOrBlank(this.mEmailEditText.getText().toString()) || !ValidationUtils.isValidEmailAddress(this.mEmailEditText.getText().toString())) ? false : true;
        if (this.mEmailEditText.getText() == null || !StringUtils.isNullOrBlank(this.mEmailEditText.getText().toString())) {
            obj = null;
        }
        if (z) {
            this.mEmailEditText.setError(null);
        } else if (obj != null) {
            this.mEmailEditText.setError(getResources().getString(C0401R.string.com_appboy_feedback_form_empty_email));
        } else {
            this.mEmailEditText.setError(getResources().getString(C0401R.string.com_appboy_feedback_form_invalid_email));
        }
        return z;
    }

    private boolean ensureSendButton() {
        return validatedMessage() & validatedEmail();
    }

    private void clearData() {
        this.mEmailEditText.setText(org.apache.commons.lang3.StringUtils.EMPTY);
        this.mMessageEditText.setText(org.apache.commons.lang3.StringUtils.EMPTY);
        this.mIsBugCheckBox.setChecked(false);
        this.mErrorMessageShown = false;
        this.mEmailEditText.setError(null);
        this.mMessageEditText.setError(null);
    }

    private void hideSoftKeyboard() {
        Activity activity = getActivity();
        activity.getWindow().setSoftInputMode(this.mOriginalSoftInputMode);
        if (activity.getCurrentFocus() != null) {
            ((InputMethodManager) activity.getSystemService("input_method")).hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        }
    }
}
