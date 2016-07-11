package bo.app;

import android.content.Context;
import android.os.Environment;
import com.etsy.android.lib.models.apiv3.ActivityFeedEntity;
import java.io.File;
import java.io.IOException;
import org.apache.commons.lang3.StringUtils;

public final class is {
    public static File m571a(Context context, boolean z) {
        Object externalStorageState;
        File file = null;
        try {
            externalStorageState = Environment.getExternalStorageState();
        } catch (NullPointerException e) {
            externalStorageState = StringUtils.EMPTY;
        } catch (IncompatibleClassChangeError e2) {
            externalStorageState = StringUtils.EMPTY;
        }
        if (z && "mounted".equals(r1)) {
            int i;
            if (context.checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
                i = 1;
            } else {
                i = 0;
            }
            if (i != 0) {
                file = m570a(context);
            }
        }
        if (file == null) {
            file = context.getCacheDir();
        }
        if (file != null) {
            return file;
        }
        ip.m568c("Can't define system cache directory! '%s' will be used.", "/data/data/" + context.getPackageName() + "/cache/");
        return new File("/data/data/" + context.getPackageName() + "/cache/");
    }

    private static File m570a(Context context) {
        File file = new File(new File(new File(new File(Environment.getExternalStorageDirectory(), "Android"), ActivityFeedEntity.DATA), context.getPackageName()), "cache");
        if (file.exists()) {
            return file;
        }
        if (file.mkdirs()) {
            try {
                new File(file, ".nomedia").createNewFile();
                return file;
            } catch (IOException e) {
                ip.m567b("Can't create \".nomedia\" file in application external cache directory", new Object[0]);
                return file;
            }
        }
        ip.m568c("Unable to create external cache directory", new Object[0]);
        return null;
    }
}
