package com.etsy.android.lib.core.img;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build.VERSION;
import android.provider.MediaStore.Images.Media;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.util.Pair;
import android.view.View;
import com.etsy.android.lib.config.EtsyConfig;
import com.etsy.android.lib.config.EtsyConfigKeys;
import com.etsy.android.lib.core.EtsyApplication;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.util.ab;
import com.etsy.android.lib.util.af;
import com.etsy.android.lib.util.ba;
import com.etsy.android.ui.dialog.EtsyDialogFragment;
import com.etsy.android.uikit.view.FullImageView;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.commons.lang3.StringUtils;

/* renamed from: com.etsy.android.lib.core.img.g */
public class ImageHelper {
    private static final String f1630a;
    private static final boolean f1631b;

    static {
        f1630a = EtsyDebug.m1891a(ImageHelper.class);
        f1631b = VERSION.SDK_INT >= 16;
    }

    @Nullable
    public static Bitmap m1628a(@Nullable Bitmap bitmap, int i, int i2, int i3) {
        if (bitmap == null) {
            return null;
        }
        if (i2 == -1) {
            i2 = bitmap.getWidth();
        }
        if (i3 == -1) {
            i3 = bitmap.getHeight();
        }
        if (!(i2 == bitmap.getWidth() && i3 == bitmap.getHeight())) {
            bitmap = Bitmap.createScaledBitmap(bitmap, i2, i3, true);
        }
        Bitmap createBitmap = Bitmap.createBitmap(i2, i3, Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, i2, i3);
        RectF rectF = new RectF(rect);
        float f = (float) i;
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(-12434878);
        canvas.drawRoundRect(rectF, f, f, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return createBitmap;
    }

    @NonNull
    public static Bitmap m1624a(int i, int i2, int i3, int i4) {
        Bitmap createBitmap = Bitmap.createBitmap(i2, i3, Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        RectF rectF = new RectF(new Rect(0, 0, i2, i3));
        float f = (float) i4;
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(i);
        canvas.drawRoundRect(rectF, f, f, paint);
        return createBitmap;
    }

    public static boolean m1638a(@Nullable View view) {
        if (view != null && view.getWidth() > 0 && view.getHeight() > 0) {
            return true;
        }
        return false;
    }

    @NonNull
    public static Bitmap m1629a(@NonNull View view, int i, int i2) {
        float f = FullImageView.ASPECT_RATIO_SQUARE / ((float) i);
        int width = view.getWidth();
        int height = view.getHeight();
        EtsyDebug.m1914c(f1630a, "drawViewToBitmap to scale:%s width:%d height:%d", Float.valueOf(f), Integer.valueOf(width), Integer.valueOf(height));
        if (!ImageHelper.m1638a(view)) {
            ab abVar = new ab(view.getContext());
            width = abVar.m3182e();
            height = abVar.m3183f();
        }
        Bitmap createBitmap = Bitmap.createBitmap((int) (((float) width) * f), (int) (((float) height) * f), Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Drawable background = view.getBackground();
        if (background != null) {
            background.setBounds(new Rect(0, 0, width, height));
            background.draw(canvas);
        } else {
            canvas.drawColor(i2);
        }
        if (i > 1) {
            canvas.scale(f, f);
        }
        view.draw(canvas);
        return createBitmap;
    }

    @NonNull
    public static String m1636a(@NonNull Context context, @NonNull Bitmap bitmap) {
        return ImageHelper.m1637a(bitmap, ImageHelper.m1634a(context));
    }

    @NonNull
    public static String m1637a(@NonNull Bitmap bitmap, @NonNull File file) {
        try {
            OutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
            try {
                bitmap.compress(CompressFormat.JPEG, 70, bufferedOutputStream);
                bufferedOutputStream.flush();
            } catch (Throwable e) {
                EtsyDebug.m1917d(f1630a, "saveBitmapToFile IO ERROR!", e);
                EtsyDebug.m1914c(f1630a, "saveBitmapToFile %s", file.getAbsolutePath());
                return file.getAbsolutePath();
            } finally {
                bufferedOutputStream.close();
            }
            EtsyDebug.m1914c(f1630a, "saveBitmapToFile %s", file.getAbsolutePath());
            return file.getAbsolutePath();
        } catch (Throwable e2) {
            EtsyDebug.m1917d(f1630a, "saveBitmapToFile IO ERROR!", e2);
            return StringUtils.EMPTY;
        }
    }

    @Nullable
    @WorkerThread
    @TargetApi(16)
    public static Bitmap m1625a(Context context, @Nullable Uri uri) {
        if (uri == null) {
            return null;
        }
        if (uri.getScheme().equals(ResponseConstants.FILE)) {
            return ImageHelper.m1630a(new File(uri.getPath()));
        }
        if (!uri.getScheme().equals("content")) {
            return null;
        }
        Cursor query = context.getContentResolver().query(uri, null, null, null, null);
        if (query == null || !query.moveToFirst()) {
            return ImageHelper.m1640b(context, uri);
        }
        Bitmap b;
        if (EtsyDebug.m1903a()) {
            EtsyDebug.m1912c(f1630a, DatabaseUtils.dumpCursorToString(query));
        }
        int columnIndex = query.getColumnIndex("_data");
        int columnIndex2 = query.getColumnIndex("orientation");
        if (columnIndex < 0 || columnIndex2 < 0) {
            b = ImageHelper.m1640b(context, uri);
        } else {
            String string = query.getString(columnIndex);
            columnIndex2 = query.getInt(columnIndex2);
            if (string == null || !new File(string).exists()) {
                b = ImageHelper.m1640b(context, uri);
            } else {
                b = ImageHelper.m1632a(string, columnIndex2);
            }
        }
        query.close();
        return b;
    }

    @Nullable
    private static Bitmap m1640b(Context context, Uri uri) {
        try {
            InputStream openInputStream = context.getContentResolver().openInputStream(uri);
            File a = af.m3197a(context);
            af.m3203a(openInputStream, a);
            openInputStream.close();
            return ImageHelper.m1630a(a);
        } catch (Throwable e) {
            EtsyDebug.m1907b(f1630a, "File not found in decodeFromStream", e);
            return null;
        } catch (Throwable e2) {
            EtsyDebug.m1907b(f1630a, "Null pointer in decodeFromStream", e2);
            return null;
        } catch (Throwable e22) {
            EtsyDebug.m1907b(f1630a, "IOException in decodeFromStream", e22);
            return null;
        }
    }

    @Nullable
    public static Bitmap m1630a(@NonNull File file) {
        return ImageHelper.m1632a(file.getAbsolutePath(), ImageHelper.m1639b(file));
    }

    public static Bitmap m1632a(String str, int i) {
        Bitmap a = ImageHelper.m1631a(str);
        if (a != null) {
            return ImageHelper.m1626a(a, i);
        }
        return a;
    }

    @NonNull
    private static Bitmap m1626a(@NonNull Bitmap bitmap, int i) {
        if (i <= 0) {
            return bitmap;
        }
        Matrix matrix = new Matrix();
        matrix.postRotate((float) i);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    @Nullable
    public static Bitmap m1631a(String str) {
        return ImageHelper.m1633a(str, (int) ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED, (int) ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED);
    }

    @Nullable
    public static Bitmap m1633a(String str, int i, int i2) {
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        options.inJustDecodeBounds = false;
        options.inSampleSize = ImageHelper.m1623a(options, i, i2);
        options.inPurgeable = true;
        options.inInputShareable = true;
        return BitmapFactory.decodeFile(str, options);
    }

    @NonNull
    public static Bitmap m1627a(@NonNull Bitmap bitmap, int i, int i2) {
        int round;
        int i3;
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();
        float f = ((float) height) / (((float) width) * FullImageView.ASPECT_RATIO_SQUARE);
        if (height > width) {
            round = Math.round(f * ((float) i));
            i3 = i;
        } else {
            i3 = Math.round(((float) (i2 * 1)) / f);
            round = i2;
        }
        EtsyDebug.m1912c(f1630a, "getScaledBitmap: Original: " + width + EtsyDialogFragment.OPT_X_BUTTON + height + ", desired: " + i + EtsyDialogFragment.OPT_X_BUTTON + i2 + ", scaled: " + i3 + EtsyDialogFragment.OPT_X_BUTTON + round);
        Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap, i3, round, true);
        EtsyDebug.m1912c(f1630a, "getScaledBitmap: " + (bitmap == createScaledBitmap ? "using original" : "created copy"));
        return createScaledBitmap;
    }

    @NonNull
    public static Pair<Integer, Integer> m1641b(String str) {
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        return new Pair(Integer.valueOf(options.outWidth), Integer.valueOf(options.outHeight));
    }

    public static int m1623a(@NonNull Options options, int i, int i2) {
        int i3;
        int i4 = options.outHeight;
        int i5 = options.outWidth;
        if (i4 <= i2 && i5 <= i) {
            i3 = 1;
        } else if (i5 > i4) {
            i3 = ImageHelper.m1622a(i4, i2);
        } else {
            i3 = ImageHelper.m1622a(i5, i);
        }
        i3 = Math.max(1, ImageHelper.m1621a(i3));
        EtsyDebug.m1912c(f1630a, "calculateInSampleSize: original: " + i5 + EtsyDialogFragment.OPT_X_BUTTON + i4 + ", requested: " + i + EtsyDialogFragment.OPT_X_BUTTON + i2 + ", sampleSize: " + i3);
        if (!EtsyConfig.m837a().m869d().m885c(EtsyConfigKeys.bx)) {
            return i3;
        }
        int i6 = i4 / i3;
        if (i5 / i3 <= ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED && i6 <= ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED) {
            return i3;
        }
        if (i5 > i4) {
            i3 = ImageHelper.m1622a(i5, (int) ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED);
        } else {
            i3 = ImageHelper.m1622a(i4, (int) ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED);
        }
        return ImageHelper.m1621a(i3);
    }

    private static int m1622a(int i, int i2) {
        return Math.round(((float) i) / ((float) i2));
    }

    private static int m1621a(int i) {
        if (i < 0) {
            return 0;
        }
        int i2 = i - 1;
        i2 |= i2 >> 1;
        i2 |= i2 >> 2;
        i2 |= i2 >> 4;
        i2 |= i2 >> 8;
        return (i2 | (i2 >> 16)) + 1;
    }

    public static int m1639b(@NonNull File file) {
        try {
            switch (new ExifInterface(file.getAbsolutePath()).getAttributeInt("Orientation", 1)) {
                case Task.NETWORK_STATE_CONNECTED /*0*/:
                    return ImageHelper.m1643c(file);
                case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                    return 180;
                case CommonStatusCodes.RESOLUTION_REQUIRED /*6*/:
                    return 90;
                case CommonStatusCodes.INTERNAL_ERROR /*8*/:
                    return 270;
                default:
                    return 0;
            }
        } catch (Throwable e) {
            EtsyDebug.m1917d(f1630a, "Error decoding file for exif", e);
            return 0;
        }
    }

    public static int m1643c(@NonNull File file) {
        long length = file.length();
        Cursor query = EtsyApplication.get().getContentResolver().query(Media.EXTERNAL_CONTENT_URI, new String[]{"orientation", "_size"}, null, null, "date_added desc");
        if (query == null) {
            return 0;
        }
        int columnIndex;
        query.moveToFirst();
        while (!query.isAfterLast()) {
            columnIndex = query.getColumnIndex("orientation");
            if (length == query.getLong(query.getColumnIndex("_size"))) {
                columnIndex = query.getInt(columnIndex);
                query.close();
                break;
            }
            query.moveToNext();
        }
        columnIndex = 0;
        query.close();
        return columnIndex;
    }

    public static File m1634a(Context context) {
        String valueOf = String.valueOf(System.currentTimeMillis());
        try {
            valueOf = ba.m3310a(valueOf);
        } catch (Throwable e) {
            EtsyDebug.m1917d(f1630a, "Encoding error with MD5 of timestamp", e);
        } catch (Throwable e2) {
            EtsyDebug.m1917d(f1630a, "Error with MD5 of timestamp", e2);
        }
        return ImageHelper.m1635a(context, valueOf);
    }

    private static File m1635a(@NonNull Context context, String str) {
        File b = ImageHelper.m1642b(context, "blurry_img_cache");
        if (!b.exists()) {
            b.mkdirs();
        }
        return new File(b, str);
    }

    private static File m1642b(@NonNull Context context, String str) {
        return new File(context.getCacheDir(), str);
    }
}
