package com.etsy.android.ui.dialog;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.etsy.android.lib.R;
import com.etsy.android.uikit.ui.dialog.PermissionDeniedDialogFragment;

public class BOEPermissionDeniedDialogFragment extends PermissionDeniedDialogFragment {
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        ((ImageView) view.findViewById(R.app_icon)).setImageResource(2130837904);
        ((TextView) view.findViewById(R.permission_message)).setText(R.local_permission_location_denied);
        ((TextView) view.findViewById(R.permission_go_to_settings_line)).setText(R.etsy_settings);
    }
}
