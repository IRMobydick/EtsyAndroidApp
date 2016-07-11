package com.appboy.ui.support;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import com.appboy.Constants;
import com.appboy.support.AppboyLogger;
import com.appboy.support.PackageUtils;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;

public class FrescoLibraryUtils {
    public static final String FRESCO_ENABLED = "com_appboy_enable_fresco_library_use";
    private static final String TAG;
    private static final String[] USED_FRESCO_CLASSES;
    private static boolean mCanUseFresco;
    private static boolean mCanUseFrescoSet;

    /* renamed from: com.appboy.ui.support.FrescoLibraryUtils.1 */
    final class C04271 extends BaseControllerListener<ImageInfo> {
        final /* synthetic */ float val$aspectRatio;
        final /* synthetic */ boolean val$respectAspectRatio;
        final /* synthetic */ SimpleDraweeView val$simpleDraweeView;

        /* renamed from: com.appboy.ui.support.FrescoLibraryUtils.1.1 */
        class C04261 implements Runnable {
            final /* synthetic */ float val$imageAspectRatio;

            C04261(float f) {
                this.val$imageAspectRatio = f;
            }

            public void run() {
                C04271.this.val$simpleDraweeView.setAspectRatio(this.val$imageAspectRatio);
            }
        }

        C04271(boolean z, float f, SimpleDraweeView simpleDraweeView) {
            this.val$respectAspectRatio = z;
            this.val$aspectRatio = f;
            this.val$simpleDraweeView = simpleDraweeView;
        }

        public void onFinalImageSet(String str, ImageInfo imageInfo, Animatable animatable) {
            if (imageInfo != null) {
                float f;
                if (this.val$respectAspectRatio) {
                    f = this.val$aspectRatio;
                } else {
                    f = (float) (imageInfo.getWidth() / imageInfo.getHeight());
                }
                this.val$simpleDraweeView.post(new C04261(f));
            }
        }
    }

    static {
        TAG = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, FrescoLibraryUtils.class.getName()});
        mCanUseFresco = false;
        mCanUseFrescoSet = false;
        USED_FRESCO_CLASSES = new String[]{"com.facebook.drawee.backends.pipeline.Fresco", "com.facebook.drawee.interfaces.DraweeController", "com.facebook.drawee.view.SimpleDraweeView", "com.facebook.drawee.backends.pipeline.Fresco", "com.facebook.drawee.controller.BaseControllerListener", "com.facebook.drawee.controller.ControllerListener", "com.facebook.imagepipeline.image.ImageInfo"};
    }

    private static boolean getIsFrescoEnabledFromXml(Resources resources, String str) {
        int identifier = resources.getIdentifier(FRESCO_ENABLED, "bool", str);
        if (identifier != 0) {
            return resources.getBoolean(identifier);
        }
        return false;
    }

    public static boolean canUseFresco(Context context) {
        boolean z = true;
        if (mCanUseFrescoSet) {
            return mCanUseFresco;
        }
        boolean z2;
        Context applicationContext = context.getApplicationContext();
        boolean isFrescoEnabledFromXml = getIsFrescoEnabledFromXml(applicationContext.getResources(), PackageUtils.getResourcePackageName(applicationContext));
        try {
            ClassLoader classLoader = FrescoLibraryUtils.class.getClassLoader();
            for (String cls : USED_FRESCO_CLASSES) {
                if (Class.forName(cls, false, classLoader) == null) {
                    z2 = false;
                    break;
                }
            }
            z2 = true;
        } catch (Exception e) {
            z2 = false;
        } catch (NoClassDefFoundError e2) {
            z2 = false;
        } catch (Throwable th) {
            z2 = false;
        }
        mCanUseFrescoSet = true;
        if (!(z2 && isFrescoEnabledFromXml)) {
            z = false;
        }
        mCanUseFresco = z;
        return mCanUseFresco;
    }

    public static void setDraweeControllerHelper(SimpleDraweeView simpleDraweeView, String str, float f, boolean z) {
        if (str == null) {
            AppboyLogger.m670w(TAG, "The url set for the Drawee controller was null. Controller not set.");
        } else if (simpleDraweeView == null) {
            AppboyLogger.m670w(TAG, "The SimpleDraweeView set for the Drawee controller was null. Controller not set.");
        } else {
            C04271 c04271 = new C04271(z, f, simpleDraweeView);
            try {
                simpleDraweeView.setController(((PipelineDraweeControllerBuilder) ((PipelineDraweeControllerBuilder) ((PipelineDraweeControllerBuilder) Fresco.newDraweeControllerBuilder().setUri(Uri.parse(str)).setAutoPlayAnimations(true)).setTapToRetryEnabled(true)).setControllerListener(c04271)).build());
            } catch (Throwable e) {
                AppboyLogger.m665e(TAG, "Fresco controller builder could not be retrieved. Fresco most likely prematurely shutdown.", e);
            } catch (Throwable e2) {
                AppboyLogger.m665e(TAG, "Fresco controller builder could not be retrieved. Fresco most likely prematurely shutdown.", e2);
            }
        }
    }

    static boolean canUseFrescoMock(Context context, Resources resources, boolean z) {
        return z && getIsFrescoEnabledFromXml(resources, PackageUtils.getResourcePackageName(context.getApplicationContext()));
    }
}
