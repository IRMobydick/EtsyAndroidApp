package com.etsy.android.ui.local;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.img.ImageBatch;
import com.etsy.android.lib.core.img.ImageDownloadListener;
import com.etsy.android.lib.models.BaseModelImage;
import com.etsy.android.uikit.util.TabletSupportUtil;
import com.etsy.android.uikit.view.FullImageView;
import com.etsy.android.uikit.view.ImageViewWithAspectRatio;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* renamed from: com.etsy.android.ui.local.o */
public class LocalMarketCardUtil {

    /* renamed from: com.etsy.android.ui.local.o.1 */
    final class LocalMarketCardUtil extends ImageDownloadListener {
        final /* synthetic */ LocalMarketCardUtil f3165a;

        LocalMarketCardUtil(ImageView imageView, LocalMarketCardUtil localMarketCardUtil) {
            this.f3165a = localMarketCardUtil;
            super(imageView);
        }

        public void m4380a(Bitmap bitmap, boolean z) {
            super.m1579a(bitmap, z);
            this.f3165a.m4325a();
        }
    }

    public static void m4388a(FrameLayout frameLayout, @Nullable FrameLayout frameLayout2, ImageBatch imageBatch, List<? extends BaseModelImage> list, boolean z, int i, boolean z2, LocalMarketCardUtil localMarketCardUtil) {
        if (z) {
            LocalMarketCardUtil.m4392b(frameLayout, frameLayout2, imageBatch, list, i, z2, localMarketCardUtil);
        } else {
            LocalMarketCardUtil.m4384a(frameLayout, frameLayout2, imageBatch, (List) list, i, z2, localMarketCardUtil);
        }
    }

    public static void m4386a(FrameLayout frameLayout, @Nullable FrameLayout frameLayout2, ImageBatch imageBatch, List<? extends BaseModelImage> list, boolean z, int i, LocalMarketCardUtil localMarketCardUtil) {
        LocalMarketCardUtil.m4388a(frameLayout, frameLayout2, imageBatch, list, z, i, new TabletSupportUtil(frameLayout.getContext()).m5621a(), localMarketCardUtil);
    }

    public static void m4385a(FrameLayout frameLayout, @Nullable FrameLayout frameLayout2, ImageBatch imageBatch, List<? extends BaseModelImage> list, boolean z, int i) {
        LocalMarketCardUtil.m4388a(frameLayout, frameLayout2, imageBatch, list, z, i, new TabletSupportUtil(frameLayout.getContext()).m5621a(), null);
    }

    public static void m4387a(FrameLayout frameLayout, @Nullable FrameLayout frameLayout2, ImageBatch imageBatch, List<? extends BaseModelImage> list, boolean z, int i, boolean z2) {
        LocalMarketCardUtil.m4388a(frameLayout, frameLayout2, imageBatch, list, z, i, z2, null);
    }

    private static void m4384a(@NonNull FrameLayout frameLayout, @Nullable FrameLayout frameLayout2, @NonNull ImageBatch imageBatch, @NonNull List<? extends BaseModelImage> list, int i, boolean z, LocalMarketCardUtil localMarketCardUtil) {
        Resources resources = frameLayout.getContext().getResources();
        frameLayout.setVisibility(0);
        List a = LocalMarketCardUtil.m4381a(resources);
        LocalMarketCardUtil.m4390a(imageBatch, (LinearLayout) frameLayout.findViewById(2131756264), (List) list, i, localMarketCardUtil);
        LocalMarketCardUtil.m4383a(frameLayout, i, 2130837615, a);
        if (frameLayout2 != null && z) {
            if (list.size() > i) {
                LocalMarketCardUtil.m4390a(imageBatch, (LinearLayout) frameLayout2.findViewById(2131756264), list.subList(i, list.size()), i, localMarketCardUtil);
            } else {
                ((LinearLayout) frameLayout2.findViewById(2131756264)).removeAllViews();
            }
            LocalMarketCardUtil.m4383a(frameLayout2, i, 2130837614, a.subList(i, a.size()));
            frameLayout2.setVisibility(0);
        } else if (frameLayout2 != null) {
            frameLayout2.setVisibility(8);
        }
    }

    private static void m4392b(@NonNull FrameLayout frameLayout, @Nullable FrameLayout frameLayout2, @NonNull ImageBatch imageBatch, @NonNull List<? extends BaseModelImage> list, int i, boolean z, LocalMarketCardUtil localMarketCardUtil) {
        if (frameLayout2 != null) {
            frameLayout2.setVisibility(8);
        }
        frameLayout.setVisibility(0);
        double d = (1.0d / ((double) i)) * ImageViewWithAspectRatio.STANDARD_IMAGE_ASPECT_RATIO;
        if (z) {
            d *= 2.0d;
        }
        if (list.size() > 0) {
            LocalMarketCardUtil.m4389a(imageBatch, (LinearLayout) frameLayout.findViewById(2131756264), (BaseModelImage) list.get(0), d, localMarketCardUtil);
        } else {
            ((LinearLayout) frameLayout.findViewById(2131756264)).removeAllViews();
        }
        LocalMarketCardUtil.m4382a(frameLayout, z ? 2130837624 : 2130837625, d);
        frameLayout.requestLayout();
    }

    private static void m4382a(@NonNull FrameLayout frameLayout, int i, double d) {
        ImageViewWithAspectRatio imageViewWithAspectRatio = (ImageViewWithAspectRatio) frameLayout.findViewById(2131756263);
        imageViewWithAspectRatio.setAspectRatio(d);
        imageViewWithAspectRatio.setImageResource(i);
        ((LinearLayout) frameLayout.findViewById(2131756262)).removeAllViews();
    }

    private static void m4383a(@NonNull FrameLayout frameLayout, int i, int i2, List<Integer> list) {
        ImageViewWithAspectRatio imageViewWithAspectRatio = (ImageViewWithAspectRatio) frameLayout.findViewById(2131756263);
        imageViewWithAspectRatio.setAspectRatio((1.0d / ((double) i)) * ImageViewWithAspectRatio.STANDARD_IMAGE_ASPECT_RATIO);
        imageViewWithAspectRatio.setImageResource(i2);
        LinearLayout linearLayout = (LinearLayout) frameLayout.findViewById(2131756262);
        linearLayout.removeAllViews();
        for (int i3 = 0; i3 < i; i3++) {
            View imageViewWithAspectRatio2 = new ImageViewWithAspectRatio(linearLayout.getContext());
            imageViewWithAspectRatio2.setAspectRatio(ImageViewWithAspectRatio.STANDARD_IMAGE_ASPECT_RATIO);
            imageViewWithAspectRatio2.setLayoutParams(new LayoutParams(0, -1, FullImageView.ASPECT_RATIO_SQUARE));
            imageViewWithAspectRatio2.setBackgroundColor(((Integer) list.get(i3)).intValue());
            linearLayout.addView(imageViewWithAspectRatio2);
        }
        frameLayout.requestLayout();
    }

    private static List<Integer> m4381a(Resources resources) {
        int[] intArray = resources.getIntArray(R.local_grid_image_bg_colors);
        List<Integer> arrayList = new ArrayList(intArray.length);
        for (int valueOf : intArray) {
            arrayList.add(Integer.valueOf(valueOf));
        }
        Collections.shuffle(arrayList);
        return arrayList;
    }

    private static void m4390a(@NonNull ImageBatch imageBatch, @NonNull LinearLayout linearLayout, @NonNull List<? extends BaseModelImage> list, int i, LocalMarketCardUtil localMarketCardUtil) {
        linearLayout.removeAllViews();
        for (int i2 = 0; i2 < i; i2++) {
            BaseModelImage baseModelImage = null;
            if (list.size() > i2) {
                baseModelImage = (BaseModelImage) list.get(i2);
            }
            View imageViewWithAspectRatio = new ImageViewWithAspectRatio(linearLayout.getContext());
            imageViewWithAspectRatio.setScaleType(ScaleType.CENTER_CROP);
            imageViewWithAspectRatio.setUseStandardRatio(true);
            imageViewWithAspectRatio.setLayoutParams(new LayoutParams(0, 0, FullImageView.ASPECT_RATIO_SQUARE));
            if (baseModelImage != null) {
                imageViewWithAspectRatio.setImageInfo(baseModelImage, imageBatch);
                LocalMarketCardUtil.m4391a(localMarketCardUtil, imageViewWithAspectRatio);
            }
            linearLayout.addView(imageViewWithAspectRatio);
        }
    }

    private static void m4389a(@NonNull ImageBatch imageBatch, @NonNull LinearLayout linearLayout, @NonNull BaseModelImage baseModelImage, double d, LocalMarketCardUtil localMarketCardUtil) {
        linearLayout.removeAllViews();
        View imageViewWithAspectRatio = new ImageViewWithAspectRatio(linearLayout.getContext());
        imageViewWithAspectRatio.setScaleType(ScaleType.CENTER_CROP);
        imageViewWithAspectRatio.setAspectRatio(d);
        imageViewWithAspectRatio.setLayoutParams(new LayoutParams(0, 0, FullImageView.ASPECT_RATIO_SQUARE));
        imageViewWithAspectRatio.setImageInfo(baseModelImage, imageBatch);
        LocalMarketCardUtil.m4391a(localMarketCardUtil, imageViewWithAspectRatio);
        linearLayout.addView(imageViewWithAspectRatio);
    }

    private static void m4391a(LocalMarketCardUtil localMarketCardUtil, ImageViewWithAspectRatio imageViewWithAspectRatio) {
        if (localMarketCardUtil != null) {
            imageViewWithAspectRatio.setImageDownloadListener(new LocalMarketCardUtil(imageViewWithAspectRatio, localMarketCardUtil));
        }
    }
}
