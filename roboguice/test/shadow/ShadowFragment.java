package roboguice.test.shadow;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import com.xtremelabs.robolectric.internal.Implementation;
import com.xtremelabs.robolectric.internal.Implements;

@Implements(Fragment.class)
public class ShadowFragment {
    protected FragmentActivity activity;
    protected View view;

    @Implementation
    public FragmentActivity getActivity() {
        return this.activity;
    }

    @Implementation
    public View getView() {
        return this.view;
    }

    @Implementation
    public void onViewCreated(View view, Bundle bundle) {
        this.view = view;
    }

    @Implementation
    public void onAttach(Activity activity) {
        this.activity = (FragmentActivity) activity;
    }
}
