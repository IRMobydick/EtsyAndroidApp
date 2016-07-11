package com.etsy.android.uikit.image;

import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import com.etsy.android.lib.R;
import com.google.android.gms.common.ConnectionResult;
import java.io.Serializable;

public class CropImageUtil {

    public class Options implements Serializable {
        private static final long serialVersionUID = 3820789588888749574L;
        @StringRes
        private int mHelpTextId;
        @LayoutRes
        private int mLayoutId;
        private int mMaxHeight;
        private int mMaxWidth;
        private int mMinHeight;
        private int mMinWidth;

        public Options() {
            this.mMaxHeight = ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED;
            this.mMaxWidth = ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED;
            this.mMinHeight = 640;
            this.mMinWidth = 640;
            this.mLayoutId = R.fragment_cropimage;
            this.mHelpTextId = R.crop_photo_help_text;
        }

        public Options setLayoutId(@LayoutRes int i) {
            if (i <= 0) {
                throw new IllegalArgumentException("Layout id must be greater than 0.");
            }
            this.mLayoutId = i;
            return this;
        }

        @LayoutRes
        public int getLayoutId() {
            return this.mLayoutId;
        }

        public Options setHelpTextId(@StringRes int i) {
            this.mHelpTextId = i;
            return this;
        }

        @StringRes
        public int getHelpTextId() {
            return this.mHelpTextId;
        }

        public Options setMaxHeight(int i) {
            if (i <= 0) {
                throw new IllegalArgumentException("Maximum height must be greater than 0.");
            }
            this.mMaxHeight = i;
            return this;
        }

        public int getMaxHeight() {
            return this.mMaxHeight;
        }

        public Options setMaxWidth(int i) {
            if (i <= 0) {
                throw new IllegalArgumentException("Maximum width must be greater than 0.");
            }
            this.mMaxWidth = i;
            return this;
        }

        public int getMaxWidth() {
            return this.mMaxWidth;
        }

        public Options setMinHeight(int i) {
            if (i <= 0) {
                throw new IllegalArgumentException("Minimum height must be greater than 0.");
            }
            this.mMinHeight = i;
            return this;
        }

        public int getMinHeight() {
            return this.mMinHeight;
        }

        public Options setMinWidth(int i) {
            if (i <= 0) {
                throw new IllegalArgumentException("Minimum width must be greater than 0.");
            }
            this.mMinWidth = i;
            return this;
        }

        public int getMinWidth() {
            return this.mMinWidth;
        }
    }

    public static float m5350a(float f, float f2, float f3, float f4) {
        float f5 = f2 - f;
        float f6 = f4 - f3;
        return (f5 * f5) + (f6 * f6);
    }
}
