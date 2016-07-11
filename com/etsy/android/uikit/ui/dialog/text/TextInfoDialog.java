package com.etsy.android.uikit.ui.dialog.text;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.models.finds.FindsModule;
import com.etsy.android.uikit.ui.core.TrackingBaseFragment;
import com.etsy.android.uikit.util.EtsyLinkify;

public class TextInfoDialog extends TrackingBaseFragment {
    private String mContent;
    private TextView mText;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mContent = getArguments().getString(FindsModule.FIELD_TEXT);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.fragment_text_info, viewGroup, false);
        this.mText = (TextView) inflate.findViewById(R.text);
        this.mText.setText(this.mContent);
        EtsyLinkify.m5482a(getActivity(), this.mText);
        return inflate;
    }

    @NonNull
    public String getTrackingName() {
        return "popup_help";
    }
}
