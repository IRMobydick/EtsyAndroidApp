package com.etsy.android.lib.toolbar;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.img.ImageHelper;
import java.util.List;

public class TakeScreenshotTask extends AsyncTask<Void, Void, String> {
    protected FragmentActivity mContext;
    private Bitmap mScreenshot;

    public TakeScreenshotTask(FragmentActivity fragmentActivity) {
        this.mContext = fragmentActivity;
        View decorView = fragmentActivity.getWindow().getDecorView();
        int[] iArr = new int[2];
        decorView.getLocationOnScreen(iArr);
        Bitmap a = ImageHelper.m1629a(decorView, 1, fragmentActivity.getResources().getColor(R.background_main_v2));
        Canvas canvas = new Canvas(a);
        Paint paint = new Paint();
        List<Fragment> fragments = fragmentActivity.getSupportFragmentManager().getFragments();
        if (fragments != null) {
            for (Fragment fragment : fragments) {
                if (fragment instanceof DialogFragment) {
                    decorView = ((DialogFragment) fragment).getDialog().getWindow().getDecorView();
                    int[] iArr2 = new int[2];
                    decorView.getLocationOnScreen(iArr2);
                    Bitmap a2 = ImageHelper.m1629a(decorView, 1, fragmentActivity.getResources().getColor(R.background_main_v2));
                    canvas.drawBitmap(a2, (float) (iArr2[0] - iArr[0]), (float) (iArr2[1] - iArr[1]), paint);
                    a2.recycle();
                }
            }
        }
        this.mScreenshot = a;
    }

    protected String doInBackground(Void... voidArr) {
        String a = ImageHelper.m1636a(this.mContext, this.mScreenshot);
        this.mScreenshot.recycle();
        return a;
    }
}
