package com.etsy.android.lib.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.text.TextUtils;
import com.etsy.android.lib.core.img.ImageHelper;
import java.io.File;

/* renamed from: com.etsy.android.lib.util.i */
class CameraHelper extends CameraHelper {
    final /* synthetic */ CameraHelper f2048c;
    private final Uri f2049d;

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m3391a((Void[]) objArr);
    }

    public CameraHelper(CameraHelper cameraHelper, Context context, Uri uri) {
        this.f2048c = cameraHelper;
        super(cameraHelper, context);
        this.f2049d = uri;
    }

    protected CameraHelper m3391a(Void... voidArr) {
        File a = af.m3197a(this.a);
        Bitmap a2 = ImageHelper.m1625a(this.a, this.f2049d);
        if (a2 == null || TextUtils.isEmpty(ImageHelper.m1637a(a2, a))) {
            a.delete();
            return new CameraHelper(null, null);
        }
        MediaScannerConnection.scanFile(this.a, new String[]{a.getAbsolutePath()}, null, null);
        return new CameraHelper(a, a2);
    }
}
