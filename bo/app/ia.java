package bo.app;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build.VERSION;
import android.provider.ContactsContract.Contacts;
import android.provider.MediaStore.Video.Thumbnails;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import android.webkit.MimeTypeMap;
import com.appboy.models.InAppMessageBase;
import com.etsy.android.lib.models.ResponseConstants.Includes;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public final class ia implements ic {
    protected final Context f753a;
    protected final int f754b;
    protected final int f755c;

    public ia(Context context) {
        this(context, (byte) 0);
    }

    private ia(Context context, byte b) {
        this.f753a = context.getApplicationContext();
        this.f754b = InAppMessageBase.INAPP_MESSAGE_DURATION_DEFAULT_MILLIS;
        this.f755c = 20000;
    }

    public final InputStream m525a(String str, Object obj) {
        boolean z = true;
        String c;
        switch (ib.f756a[id.m526a(str).ordinal()]) {
            case Task.NETWORK_STATE_UNMETERED /*1*/:
            case Task.NETWORK_STATE_ANY /*2*/:
                return m523a(str);
            case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                c = id.FILE.m529c(str);
                String mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(str));
                if (mimeTypeFromExtension == null || !mimeTypeFromExtension.startsWith("video/")) {
                    z = false;
                }
                if (!z) {
                    return new hc(new BufferedInputStream(new FileInputStream(c), AccessibilityNodeInfoCompat.ACTION_PASTE), (int) new File(c).length());
                }
                if (VERSION.SDK_INT < 8) {
                    return null;
                }
                Bitmap createVideoThumbnail = ThumbnailUtils.createVideoThumbnail(c, 2);
                if (createVideoThumbnail == null) {
                    return null;
                }
                OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                createVideoThumbnail.compress(CompressFormat.PNG, 0, byteArrayOutputStream);
                return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            case CommonStatusCodes.SIGN_IN_REQUIRED /*4*/:
                ContentResolver contentResolver = this.f753a.getContentResolver();
                Uri parse = Uri.parse(str);
                c = this.f753a.getContentResolver().getType(parse);
                boolean z2 = c != null && c.startsWith("video/");
                if (z2) {
                    Bitmap thumbnail = Thumbnails.getThumbnail(contentResolver, Long.valueOf(parse.getLastPathSegment()).longValue(), 1, null);
                    if (thumbnail != null) {
                        OutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                        thumbnail.compress(CompressFormat.PNG, 0, byteArrayOutputStream2);
                        return new ByteArrayInputStream(byteArrayOutputStream2.toByteArray());
                    }
                } else if (str.startsWith("content://com.android.contacts/")) {
                    ContentResolver contentResolver2 = this.f753a.getContentResolver();
                    return VERSION.SDK_INT >= 14 ? Contacts.openContactPhotoInputStream(contentResolver2, parse, true) : Contacts.openContactPhotoInputStream(contentResolver2, parse);
                }
                return contentResolver.openInputStream(parse);
            case CommonStatusCodes.INVALID_ACCOUNT /*5*/:
                return this.f753a.getAssets().open(id.ASSETS.m529c(str));
            case CommonStatusCodes.RESOLUTION_REQUIRED /*6*/:
                return this.f753a.getResources().openRawResource(Integer.parseInt(id.DRAWABLE.m529c(str)));
            default:
                throw new UnsupportedOperationException(String.format("UIL doesn't support scheme(protocol) by default [%s]. You should implement this support yourself (BaseImageDownloader.getStreamFromOtherSource(...))", new Object[]{str}));
        }
    }

    private InputStream m523a(String str) {
        Object obj = null;
        HttpURLConnection b = m524b(str);
        int i = 0;
        while (b.getResponseCode() / 100 == 3 && i < 5) {
            b = m524b(b.getHeaderField(Includes.LOCATION));
            i++;
        }
        Object inputStream;
        try {
            inputStream = b.getInputStream();
            if (b.getResponseCode() == Callback.DEFAULT_DRAG_ANIMATION_DURATION) {
                obj = 1;
            }
            if (obj != null) {
                return new hc(new BufferedInputStream(inputStream, AccessibilityNodeInfoCompat.ACTION_PASTE), b.getContentLength());
            }
            in.m560a(inputStream);
            throw new IOException("Image request failed with response code " + b.getResponseCode());
        } catch (Object inputStream2) {
            Closeable errorStream = b.getErrorStream();
            while (true) {
                try {
                    if (errorStream.read(new byte[AccessibilityNodeInfoCompat.ACTION_PASTE], 0, AccessibilityNodeInfoCompat.ACTION_PASTE) == -1) {
                        break;
                    }
                } catch (IOException e) {
                    throw inputStream2;
                } finally {
                    in.m560a(errorStream);
                }
            }
            throw inputStream2;
        }
    }

    private HttpURLConnection m524b(String str) {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(Uri.encode(str, "@#&=*+-_.,:!?()/~'%")).openConnection();
        httpURLConnection.setConnectTimeout(this.f754b);
        httpURLConnection.setReadTimeout(this.f755c);
        return httpURLConnection;
    }
}
