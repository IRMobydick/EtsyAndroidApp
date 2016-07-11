package bo.app;

import android.net.Uri;
import com.appboy.Constants;
import com.appboy.support.AppboyLogger;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public final class ff {
    private static final String f445a;

    static {
        f445a = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, ff.class.getName()});
    }

    public static URI m336a(Uri uri) {
        try {
            return new URI(uri.toString());
        } catch (URISyntaxException e) {
            AppboyLogger.m664e(f445a, String.format("Could not create URI from uri [%s]", new Object[]{uri.toString()}));
            return null;
        }
    }

    public static URL m337a(URI uri) {
        try {
            return new URL(uri.toString());
        } catch (MalformedURLException e) {
            AppboyLogger.m664e(f445a, String.format("Unable to parse URI [%s]", new Object[]{e.getMessage()}));
            return null;
        }
    }
}
