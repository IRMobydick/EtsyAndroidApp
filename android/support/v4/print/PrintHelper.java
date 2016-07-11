package android.support.v4.print;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build.VERSION;

public final class PrintHelper {
    public static final int COLOR_MODE_COLOR = 2;
    public static final int COLOR_MODE_MONOCHROME = 1;
    public static final int ORIENTATION_LANDSCAPE = 1;
    public static final int ORIENTATION_PORTRAIT = 2;
    public static final int SCALE_MODE_FILL = 2;
    public static final int SCALE_MODE_FIT = 1;
    PrintHelperVersionImpl mImpl;

    public interface OnPrintFinishCallback {
        void onFinish();
    }

    interface PrintHelperVersionImpl {
        int getColorMode();

        int getOrientation();

        int getScaleMode();

        void printBitmap(String str, Bitmap bitmap, OnPrintFinishCallback onPrintFinishCallback);

        void printBitmap(String str, Uri uri, OnPrintFinishCallback onPrintFinishCallback);

        void setColorMode(int i);

        void setOrientation(int i);

        void setScaleMode(int i);
    }

    final class PrintHelperKitkatImpl implements PrintHelperVersionImpl {
        private final PrintHelperKitkat mPrintHelper;

        /* renamed from: android.support.v4.print.PrintHelper.PrintHelperKitkatImpl.1 */
        class C01411 implements android.support.v4.print.PrintHelperKitkat.OnPrintFinishCallback {
            final /* synthetic */ OnPrintFinishCallback val$callback;

            C01411(OnPrintFinishCallback onPrintFinishCallback) {
                this.val$callback = onPrintFinishCallback;
            }

            public void onFinish() {
                this.val$callback.onFinish();
            }
        }

        /* renamed from: android.support.v4.print.PrintHelper.PrintHelperKitkatImpl.2 */
        class C01422 implements android.support.v4.print.PrintHelperKitkat.OnPrintFinishCallback {
            final /* synthetic */ OnPrintFinishCallback val$callback;

            C01422(OnPrintFinishCallback onPrintFinishCallback) {
                this.val$callback = onPrintFinishCallback;
            }

            public void onFinish() {
                this.val$callback.onFinish();
            }
        }

        PrintHelperKitkatImpl(Context context) {
            this.mPrintHelper = new PrintHelperKitkat(context);
        }

        public void setScaleMode(int i) {
            this.mPrintHelper.setScaleMode(i);
        }

        public int getScaleMode() {
            return this.mPrintHelper.getScaleMode();
        }

        public void setColorMode(int i) {
            this.mPrintHelper.setColorMode(i);
        }

        public int getColorMode() {
            return this.mPrintHelper.getColorMode();
        }

        public void setOrientation(int i) {
            this.mPrintHelper.setOrientation(i);
        }

        public int getOrientation() {
            return this.mPrintHelper.getOrientation();
        }

        public void printBitmap(String str, Bitmap bitmap, OnPrintFinishCallback onPrintFinishCallback) {
            android.support.v4.print.PrintHelperKitkat.OnPrintFinishCallback onPrintFinishCallback2 = null;
            if (onPrintFinishCallback != null) {
                onPrintFinishCallback2 = new C01411(onPrintFinishCallback);
            }
            this.mPrintHelper.printBitmap(str, bitmap, onPrintFinishCallback2);
        }

        public void printBitmap(String str, Uri uri, OnPrintFinishCallback onPrintFinishCallback) {
            android.support.v4.print.PrintHelperKitkat.OnPrintFinishCallback onPrintFinishCallback2 = null;
            if (onPrintFinishCallback != null) {
                onPrintFinishCallback2 = new C01422(onPrintFinishCallback);
            }
            this.mPrintHelper.printBitmap(str, uri, onPrintFinishCallback2);
        }
    }

    final class PrintHelperStubImpl implements PrintHelperVersionImpl {
        int mColorMode;
        int mOrientation;
        int mScaleMode;

        private PrintHelperStubImpl() {
            this.mScaleMode = PrintHelper.SCALE_MODE_FILL;
            this.mColorMode = PrintHelper.SCALE_MODE_FILL;
            this.mOrientation = PrintHelper.SCALE_MODE_FIT;
        }

        public void setScaleMode(int i) {
            this.mScaleMode = i;
        }

        public int getColorMode() {
            return this.mColorMode;
        }

        public void setColorMode(int i) {
            this.mColorMode = i;
        }

        public void setOrientation(int i) {
            this.mOrientation = i;
        }

        public int getOrientation() {
            return this.mOrientation;
        }

        public int getScaleMode() {
            return this.mScaleMode;
        }

        public void printBitmap(String str, Bitmap bitmap, OnPrintFinishCallback onPrintFinishCallback) {
        }

        public void printBitmap(String str, Uri uri, OnPrintFinishCallback onPrintFinishCallback) {
        }
    }

    public static boolean systemSupportsPrint() {
        if (VERSION.SDK_INT >= 19) {
            return true;
        }
        return false;
    }

    public PrintHelper(Context context) {
        if (systemSupportsPrint()) {
            this.mImpl = new PrintHelperKitkatImpl(context);
        } else {
            this.mImpl = new PrintHelperStubImpl();
        }
    }

    public void setScaleMode(int i) {
        this.mImpl.setScaleMode(i);
    }

    public int getScaleMode() {
        return this.mImpl.getScaleMode();
    }

    public void setColorMode(int i) {
        this.mImpl.setColorMode(i);
    }

    public int getColorMode() {
        return this.mImpl.getColorMode();
    }

    public void setOrientation(int i) {
        this.mImpl.setOrientation(i);
    }

    public int getOrientation() {
        return this.mImpl.getOrientation();
    }

    public void printBitmap(String str, Bitmap bitmap) {
        this.mImpl.printBitmap(str, bitmap, null);
    }

    public void printBitmap(String str, Bitmap bitmap, OnPrintFinishCallback onPrintFinishCallback) {
        this.mImpl.printBitmap(str, bitmap, onPrintFinishCallback);
    }

    public void printBitmap(String str, Uri uri) {
        this.mImpl.printBitmap(str, uri, null);
    }

    public void printBitmap(String str, Uri uri, OnPrintFinishCallback onPrintFinishCallback) {
        this.mImpl.printBitmap(str, uri, onPrintFinishCallback);
    }
}
