package com.etsy.android.lib.core.img;

import android.support.annotation.NonNull;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.webkit.URLUtil;
import android.widget.ImageView;
import com.android.volley.toolbox.ImageLoader.ImageContainer;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.apiv3.Image;
import com.etsy.android.lib.models.apiv3.Image.Source;
import com.etsy.android.lib.util.ab;
import com.etsy.android.uikit.util.ViewTreeObserverHelper;
import java.lang.ref.SoftReference;
import java.util.Collection;
import java.util.WeakHashMap;

/* renamed from: com.etsy.android.lib.core.img.a */
public class ImageBatch {
    private static final String f1605a;
    private final WeakHashMap<ImageView, SoftReference<ImageContainer>> f1606b;

    /* renamed from: com.etsy.android.lib.core.img.a.1 */
    class ImageBatch implements OnGlobalLayoutListener {
        final /* synthetic */ ImageView f1602a;
        final /* synthetic */ Image f1603b;
        final /* synthetic */ ImageBatch f1604c;

        ImageBatch(ImageBatch imageBatch, ImageView imageView, Image image) {
            this.f1604c = imageBatch;
            this.f1602a = imageView;
            this.f1603b = image;
        }

        public void onGlobalLayout() {
            ViewTreeObserverHelper.m5639b(this.f1602a.getViewTreeObserver(), (OnGlobalLayoutListener) this);
            String b = ImageBatch.m1561b(this.f1603b, this.f1602a);
            if (this.f1604c.m1563b(b, this.f1602a)) {
                this.f1604c.m1565a(new ImageDownloadRequest(b, this.f1602a));
            }
        }
    }

    static {
        f1605a = EtsyDebug.m1891a(ImageBatch.class);
    }

    public ImageBatch() {
        this.f1606b = new WeakHashMap();
    }

    public void m1568a(String str, ImageView imageView) {
        if (m1563b(str, imageView)) {
            m1565a(new ImageDownloadRequest(str, imageView));
        }
    }

    public void m1569a(String str, ImageView imageView, int i) {
        if (m1563b(str, imageView)) {
            m1565a(new ImageDownloadRequest(str, imageView).m1602d(i));
        }
    }

    public void m1567a(@NonNull Image image, @NonNull ImageView imageView) {
        ViewTreeObserverHelper.m5636a(imageView.getViewTreeObserver(), new ImageBatch(this, imageView, image));
    }

    public static String m1561b(Image image, ImageView imageView) {
        return ImageBatch.m1557a(imageView.getMeasuredWidth(), imageView.getMeasuredHeight(), image);
    }

    public static String m1557a(int i, int i2, Image image) {
        int a = ab.m3172a(i);
        int a2 = ab.m3172a(i2);
        for (Source source : image.getSources()) {
            if (a <= source.getWidth() && a2 <= source.getHeight()) {
                return source.getUrl();
            }
        }
        return image.getUrl();
    }

    public void m1570a(String str, ImageView imageView, int i, int i2) {
        if (m1563b(str, imageView)) {
            m1565a(new ImageDownloadRequest(str, imageView).m1595a(i, i2));
        }
    }

    public void m1571a(String str, ImageView imageView, int i, int i2, int i3) {
        if (m1563b(str, imageView)) {
            m1565a(new ImageDownloadRequest(str, imageView).m1595a(i, i2).m1602d(i3));
        }
    }

    public void m1576b(String str, ImageView imageView, int i, int i2, int i3) {
        if (m1563b(str, imageView)) {
            m1565a(new RoundedImageDownloadRequest(str, imageView, i).m1595a(i2, i3));
        }
    }

    public void m1574b(String str, ImageView imageView, int i) {
        if (m1563b(str, imageView)) {
            m1565a(new RoundedImageDownloadRequest(str, imageView, i / 2).m1595a(i, i));
        }
    }

    public void m1572a(String str, ImageDownloadListener imageDownloadListener, int i) {
        if (m1563b(str, null)) {
            m1565a(new RoundedImageDownloadRequest(str, imageDownloadListener, i / 2).m1595a(i, i));
        }
    }

    public void m1575b(String str, ImageView imageView, int i, int i2) {
        if (m1563b(str, imageView)) {
            m1565a(new RoundedImageDownloadRequest(str, imageView, i / 2).m1595a(i, i).m1602d(i2));
        }
    }

    private boolean m1563b(String str, ImageView imageView) {
        if (str != null && URLUtil.isValidUrl(str)) {
            return true;
        }
        if (imageView != null) {
            imageView.setImageResource(17170445);
        }
        return false;
    }

    public void m1565a(ImageDownloadRequest imageDownloadRequest) {
        m1566a(imageDownloadRequest, false);
    }

    public void m1566a(ImageDownloadRequest imageDownloadRequest, boolean z) {
        if (imageDownloadRequest != null) {
            ImageContainer imageContainer;
            m1562b(imageDownloadRequest);
            ImageView c = imageDownloadRequest.m1599c();
            if (c != null) {
                SoftReference softReference = (SoftReference) this.f1606b.get(c);
                if (softReference != null) {
                    imageContainer = (ImageContainer) softReference.get();
                    if (imageContainer != null) {
                        imageContainer.cancelRequest();
                        this.f1606b.remove(c);
                    }
                }
            }
            if (imageDownloadRequest.m1593a() != null) {
                imageDownloadRequest.m1596a(new ImageBatch(this, imageDownloadRequest.m1593a()));
            }
            try {
                ImageDownloader a = ImageDownloader.m1609a();
                boolean b = a.m1618b(imageDownloadRequest);
                if (z && imageDownloadRequest.m1604f()) {
                    imageDownloadRequest.m1605g();
                }
                imageContainer = a.m1615a(imageDownloadRequest);
                if (!b) {
                    if (imageContainer != null) {
                        this.f1606b.put(c, new SoftReference(imageContainer));
                    } else if (c != null) {
                        c.setImageResource(17170445);
                    }
                }
            } catch (Throwable e) {
                EtsyDebug.m1917d(f1605a, "Error getting image from ImageDownloadRequest", e);
            }
        }
    }

    public boolean m1573a(String str, int i, int i2) {
        return ImageDownloader.m1609a().m1617a(str, i, i2);
    }

    private void m1562b(@NonNull ImageDownloadRequest imageDownloadRequest) {
        if (EtsyDebug.m1903a() && imageDownloadRequest.m1598b() != null && imageDownloadRequest.m1598b().contains("fullxfull")) {
            EtsyDebug.m1916d(f1605a, "DATA WARNING: Fetching fullxfull image: " + imageDownloadRequest.m1598b());
        }
    }

    public void m1564a() {
        m1559a(this.f1606b.values());
        this.f1606b.clear();
    }

    private void m1559a(Collection<SoftReference<ImageContainer>> collection) {
        for (SoftReference softReference : collection) {
            if (softReference != null) {
                ImageContainer imageContainer = (ImageContainer) softReference.get();
                if (imageContainer != null) {
                    imageContainer.cancelRequest();
                }
            }
        }
    }
}
