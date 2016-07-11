package roboguice.test.shadow;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import com.xtremelabs.robolectric.internal.Implementation;
import com.xtremelabs.robolectric.internal.Implements;
import com.xtremelabs.robolectric.shadows.ShadowActivity;

@Implements(FragmentActivity.class)
public class ShadowFragmentActivity extends ShadowActivity {
    @Implementation
    public FragmentManager getSupportFragmentManager() {
        return new 1(this);
    }
}
