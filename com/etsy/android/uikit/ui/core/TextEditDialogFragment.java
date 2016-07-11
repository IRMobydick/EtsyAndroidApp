package com.etsy.android.uikit.ui.core;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import com.etsy.android.lib.R;
import com.etsy.android.uikit.view.CharacterCounterView;
import org.apache.commons.lang3.StringUtils;

public abstract class TextEditDialogFragment extends TrackingBaseDialogFragment {
    protected TextView mAfterDecimalText;
    protected TextView mBeforeDecimalText;
    protected CharacterCounterView mCharacterCounter;
    protected EditText mEditText;
    private TextView mErrorText;
    private View mHelpButton;
    private TextView mHelpSubtext;
    protected boolean mIsNumberText;

    public TextEditDialogFragment() {
        this.mIsNumberText = false;
    }

    @LayoutRes
    protected int getLayoutRes() {
        return this.mIsNumberText ? R.fragment_text_edit_numeric : R.fragment_text_edit_sentences;
    }

    public View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(getLayoutRes(), viewGroup, false);
        this.mEditText = (EditText) inflate.findViewById(R.edit_text);
        this.mHelpSubtext = (TextView) inflate.findViewById(R.help_subtext);
        this.mErrorText = (TextView) inflate.findViewById(R.error_text);
        this.mHelpButton = inflate.findViewById(R.help_button);
        this.mCharacterCounter = (CharacterCounterView) inflate.findViewById(R.character_counter);
        if (this.mIsNumberText) {
            this.mBeforeDecimalText = (TextView) inflate.findViewById(R.before_decimal_text);
            this.mAfterDecimalText = (TextView) inflate.findViewById(R.after_decimal_text);
        }
        return inflate;
    }

    protected void setEditableText(String str) {
        this.mEditText.setText(StringUtils.EMPTY);
        if (str != null) {
            this.mEditText.append(str);
        }
    }

    protected void setHintText(int i) {
        this.mEditText.setHint(i);
    }

    protected void setHelpSubtext(String str) {
        this.mHelpSubtext.setText(str);
        this.mHelpSubtext.setVisibility(0);
    }

    protected void setError(String str) {
        this.mErrorText.setText(str);
        this.mErrorText.setVisibility(0);
    }

    protected void setMaxChars(int i) {
        this.mCharacterCounter.setObservable(this.mEditText);
        this.mCharacterCounter.setMaxChars(i);
        this.mCharacterCounter.setVisibility(0);
    }

    protected void enableHelpDialogButton(OnClickListener onClickListener) {
        this.mHelpButton.setVisibility(0);
        this.mHelpButton.setOnClickListener(onClickListener);
    }

    protected String getEditedText() {
        return this.mEditText.getText().toString().trim();
    }
}
