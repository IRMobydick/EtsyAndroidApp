package bo.app;

import com.etsy.android.ui.dialog.EtsyDialogFragment;

public final class hh {
    public final int f710a;
    public final int f711b;

    public hh(int i, int i2) {
        this.f710a = i;
        this.f711b = i2;
    }

    public hh(int i, int i2, int i3) {
        if (i3 % 180 == 0) {
            this.f710a = i;
            this.f711b = i2;
            return;
        }
        this.f710a = i2;
        this.f711b = i;
    }

    public final String toString() {
        return this.f710a + EtsyDialogFragment.OPT_X_BUTTON + this.f711b;
    }
}
