package com.etsy.android.uikit.image;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.MotionEventCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.img.ImageDownloadListener;
import com.etsy.android.lib.core.img.ImageDownloadRequest;
import com.etsy.android.lib.core.img.ImageHelper;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.util.ab;
import com.etsy.android.lib.util.bl;
import com.etsy.android.uikit.image.CropImageUtil.Options;
import com.etsy.android.uikit.ui.core.BaseFragment;
import com.etsy.android.uikit.view.FullImageView;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;
import java.io.FileOutputStream;
import java.io.IOException;

public class CropImageFragment extends BaseFragment implements OnMenuItemClickListener {
    private static final String MATRIX_VALUES = "matrix_vales";
    private static final int NUM_VALUES = 9;
    private static final float REQUIRE_RESET = -1.0f;
    private static final String TAG;
    private Uri mDestUri;
    private TextView mHelpText;
    private String mImageUrl;
    private ImageView mImageView;
    private float mInitialTouchX;
    private float mInitialTouchY;
    private boolean mIsMoving;
    private float mLastDistance;
    private float mLastTouchX;
    private float mLastTouchY;
    private float mMaxScale;
    private float mMinScale;
    private MenuItem mNextButton;
    private OnPreDrawListener mOnPreDrawListener;
    private final OnTouchListener mOnTouchListener;
    private Options mOptions;
    private Intent mResultIntent;
    private float mScalePivotX;
    private float mScalePivotY;
    private Uri mSourceUri;
    private int mTouchSlop;
    private final float[] mValues;

    /* renamed from: com.etsy.android.uikit.image.CropImageFragment.1 */
    class C09281 extends ImageDownloadListener {
        final /* synthetic */ CropImageFragment f3974a;

        C09281(CropImageFragment cropImageFragment, ImageView imageView) {
            this.f3974a = cropImageFragment;
            super(imageView);
        }

        protected void m5349a(ImageView imageView) {
            this.f3974a.mImageView.getViewTreeObserver().addOnPreDrawListener(this.f3974a.mOnPreDrawListener);
        }
    }

    /* renamed from: com.etsy.android.uikit.image.CropImageFragment.2 */
    class C09292 implements OnPreDrawListener {
        final /* synthetic */ CropImageFragment f3975a;

        C09292(CropImageFragment cropImageFragment) {
            this.f3975a = cropImageFragment;
        }

        public boolean onPreDraw() {
            this.f3975a.mImageView.getViewTreeObserver().removeOnPreDrawListener(this);
            this.f3975a.initializeImageMatrix();
            return true;
        }
    }

    /* renamed from: com.etsy.android.uikit.image.CropImageFragment.3 */
    class C09303 implements OnTouchListener {
        final /* synthetic */ CropImageFragment f3976a;

        C09303(CropImageFragment cropImageFragment) {
            this.f3976a = cropImageFragment;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            switch (MotionEventCompat.getActionMasked(motionEvent)) {
                case Task.NETWORK_STATE_CONNECTED /*0*/:
                case CommonStatusCodes.INVALID_ACCOUNT /*5*/:
                    if (motionEvent.getPointerCount() == 1) {
                        this.f3976a.mInitialTouchX = motionEvent.getRawX();
                        this.f3976a.mInitialTouchY = motionEvent.getRawY();
                        this.f3976a.mIsMoving = false;
                        break;
                    }
                    break;
                case Task.NETWORK_STATE_UNMETERED /*1*/:
                case CommonStatusCodes.RESOLUTION_REQUIRED /*6*/:
                    if (motionEvent.getPointerCount() == 2) {
                        this.f3976a.cancelZoom();
                    }
                    this.f3976a.cancelMove();
                    break;
                case Task.NETWORK_STATE_ANY /*2*/:
                    if (motionEvent.getPointerCount() > 1) {
                        if (this.f3976a.mLastDistance != CropImageFragment.REQUIRE_RESET) {
                            this.f3976a.scaleImage(motionEvent);
                            break;
                        }
                        this.f3976a.mLastDistance = CropImageUtil.m5350a(motionEvent.getX(0), motionEvent.getX(1), motionEvent.getY(0), motionEvent.getY(1));
                        break;
                    }
                    if (this.f3976a.mInitialTouchX == CropImageFragment.REQUIRE_RESET || this.f3976a.mInitialTouchY == CropImageFragment.REQUIRE_RESET) {
                        this.f3976a.mInitialTouchX = motionEvent.getRawX();
                        this.f3976a.mInitialTouchY = motionEvent.getRawY();
                        this.f3976a.mLastTouchX = motionEvent.getRawX();
                        this.f3976a.mLastTouchY = motionEvent.getRawY();
                    }
                    float rawX = motionEvent.getRawX() - this.f3976a.mLastTouchX;
                    float rawY = motionEvent.getRawY() - this.f3976a.mLastTouchY;
                    this.f3976a.checkIsMoving(motionEvent.getRawX() - this.f3976a.mInitialTouchX, motionEvent.getRawX() - this.f3976a.mInitialTouchY);
                    if (this.f3976a.mIsMoving) {
                        this.f3976a.moveImage(rawX, rawY);
                        break;
                    }
                    break;
                case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                    this.f3976a.cancelZoom();
                    this.f3976a.cancelMove();
                    break;
                case CommonStatusCodes.SIGN_IN_REQUIRED /*4*/:
                    this.f3976a.cancelZoom();
                    this.f3976a.cancelMove();
                    break;
            }
            this.f3976a.mLastTouchX = motionEvent.getRawX();
            this.f3976a.mLastTouchY = motionEvent.getRawY();
            return true;
        }
    }

    public CropImageFragment() {
        this.mLastDistance = REQUIRE_RESET;
        this.mInitialTouchX = REQUIRE_RESET;
        this.mInitialTouchY = REQUIRE_RESET;
        this.mResultIntent = new Intent();
        this.mValues = new float[NUM_VALUES];
        this.mOnPreDrawListener = new C09292(this);
        this.mOnTouchListener = new C09303(this);
    }

    static {
        TAG = EtsyDebug.m1891a(CropImageFragment.class);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mSourceUri = Uri.parse(getArguments().getString("source_uri"));
        this.mDestUri = Uri.parse(getArguments().getString("dest_uri"));
        this.mImageUrl = getArguments().getString(ResponseConstants.URL);
        this.mOptions = (Options) getArguments().getSerializable(ResponseConstants.OPTIONS);
        this.mTouchSlop = ViewConfiguration.get(getActivity()).getScaledTouchSlop();
        setHasOptionsMenu(true);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate;
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (onCreateView == null) {
            inflate = layoutInflater.inflate(this.mOptions.getLayoutId(), viewGroup, false);
        } else {
            inflate = onCreateView;
        }
        this.mImageView = (ImageView) inflate.findViewById(R.cropped_image);
        if (bundle != null) {
            Matrix matrix = new Matrix();
            matrix.setValues(bundle.getFloatArray(MATRIX_VALUES));
            this.mImageView.setImageMatrix(matrix);
        }
        ab abVar = new ab(getActivity());
        LayoutParams layoutParams = (LayoutParams) this.mImageView.getLayoutParams();
        int min = Math.min(Math.min(this.mOptions.getMaxWidth(), (abVar.m3182e() - layoutParams.leftMargin) - layoutParams.rightMargin), Math.min(this.mOptions.getMaxHeight(), (abVar.m3183f() - layoutParams.topMargin) - layoutParams.bottomMargin));
        this.mImageView.getLayoutParams().width = min;
        this.mImageView.getLayoutParams().height = min;
        View findViewById = inflate.findViewById(R.crop_guide);
        findViewById.getLayoutParams().width = min;
        findViewById.getLayoutParams().height = min;
        if (this.mImageUrl != null) {
            ImageDownloadRequest imageDownloadRequest = new ImageDownloadRequest(this.mImageUrl, this.mImageView);
            imageDownloadRequest.m1596a(new C09281(this, this.mImageView));
            getImageBatch().m1565a(imageDownloadRequest);
        } else {
            Bitmap a = ImageHelper.m1625a(getActivity(), this.mSourceUri);
            if (a == null || a.getWidth() < this.mOptions.getMinWidth() || a.getHeight() < this.mOptions.getMinHeight()) {
                bl.m3356a(getContext(), getString(R.error_image_too_small, Integer.valueOf(this.mOptions.getMinWidth()), Integer.valueOf(this.mOptions.getMinHeight())));
                finishCropImage(false);
                return inflate;
            }
            this.mImageView.setImageBitmap(a);
            this.mImageView.getViewTreeObserver().addOnPreDrawListener(this.mOnPreDrawListener);
        }
        this.mHelpText = (TextView) inflate.findViewById(R.crop_help_text);
        this.mHelpText.setText(this.mOptions.getHelpTextId());
        this.mImageView.setOnTouchListener(this.mOnTouchListener);
        return inflate;
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.mImageView.getImageMatrix().getValues(this.mValues);
        bundle.putFloatArray(MATRIX_VALUES, this.mValues);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.shop_share_menu, menu);
        this.mNextButton = menu.findItem(R.menu_next);
        this.mNextButton.setOnMenuItemClickListener(this);
    }

    public boolean onMenuItemClick(MenuItem menuItem) {
        cropImage();
        return true;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        this.mResultIntent = intent;
    }

    public boolean handleBackPressed() {
        finishCropImage(false);
        return true;
    }

    private void cancelZoom() {
        this.mLastDistance = REQUIRE_RESET;
    }

    private void cancelMove() {
        this.mInitialTouchX = REQUIRE_RESET;
        this.mInitialTouchY = REQUIRE_RESET;
        this.mIsMoving = false;
    }

    private void checkIsMoving(float f, float f2) {
        if (this.mIsMoving || f > ((float) this.mTouchSlop) || f2 > ((float) this.mTouchSlop) || f < ((float) (-this.mTouchSlop)) || f2 < ((float) (-this.mTouchSlop))) {
            this.mIsMoving = true;
        }
    }

    private void cropImage() {
        this.mImageView.setDrawingCacheEnabled(true);
        Bitmap createBitmap = Bitmap.createBitmap(this.mImageView.getDrawingCache(true));
        this.mImageView.setDrawingCacheEnabled(false);
        saveCroppedImage(createBitmap);
    }

    private void saveCroppedImage(Bitmap bitmap) {
        Throwable th;
        FileOutputStream fileOutputStream = null;
        FileOutputStream fileOutputStream2;
        try {
            fileOutputStream2 = new FileOutputStream(this.mDestUri.getPath());
            try {
                bitmap.compress(CompressFormat.JPEG, 90, fileOutputStream2);
                if (fileOutputStream2 != null) {
                    try {
                        fileOutputStream2.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                bitmap.recycle();
                finishCropImage(true);
            } catch (Exception e2) {
                if (fileOutputStream2 != null) {
                    try {
                        fileOutputStream2.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
                bitmap.recycle();
                finishCropImage(false);
            } catch (Throwable th2) {
                Throwable th3 = th2;
                fileOutputStream = fileOutputStream2;
                th = th3;
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                }
                bitmap.recycle();
                finishCropImage(true);
                throw th;
            }
        } catch (Exception e5) {
            fileOutputStream2 = null;
            if (fileOutputStream2 != null) {
                fileOutputStream2.close();
            }
            bitmap.recycle();
            finishCropImage(false);
        } catch (Throwable th4) {
            th = th4;
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            bitmap.recycle();
            finishCropImage(true);
            throw th;
        }
    }

    private void finishCropImage(boolean z) {
        getActivity().setResult(z ? 50 : 51, new Intent().putExtras(getArguments()));
        getActivity().finish();
    }

    private void moveImage(float f, float f2) {
        Matrix imageMatrix = this.mImageView.getImageMatrix();
        imageMatrix.getValues(this.mValues);
        float intrinsicWidth = ((float) this.mImageView.getDrawable().getIntrinsicWidth()) * this.mValues[0];
        float intrinsicHeight = ((float) this.mImageView.getDrawable().getIntrinsicHeight()) * this.mValues[4];
        if (this.mValues[2] + f > 0.0f) {
            f = 0.0f - this.mValues[2];
        } else if ((this.mValues[2] + intrinsicWidth) + f < ((float) this.mImageView.getWidth())) {
            f = (((float) this.mImageView.getWidth()) - this.mValues[2]) - intrinsicWidth;
        }
        if (this.mValues[5] + f2 > 0.0f) {
            f2 = 0.0f - this.mValues[5];
        } else if ((this.mValues[5] + intrinsicHeight) + f2 < ((float) this.mImageView.getHeight())) {
            f2 = (((float) this.mImageView.getHeight()) - this.mValues[5]) - intrinsicHeight;
        }
        if (f != 0.0f || f2 != 0.0f) {
            imageMatrix.postTranslate(f, f2);
            this.mImageView.setImageMatrix(imageMatrix);
            this.mImageView.postInvalidate();
        }
    }

    private void initializeImageMatrix() {
        this.mScalePivotX = ((float) this.mImageView.getWidth()) / 2.0f;
        this.mScalePivotY = ((float) this.mImageView.getHeight()) / 2.0f;
        int intrinsicWidth = this.mImageView.getDrawable().getIntrinsicWidth();
        int intrinsicHeight = this.mImageView.getDrawable().getIntrinsicHeight();
        float width = ((float) intrinsicWidth) / ((float) this.mImageView.getWidth());
        float height = ((float) intrinsicHeight) / ((float) this.mImageView.getHeight());
        if (width < height) {
            this.mMinScale = FullImageView.ASPECT_RATIO_SQUARE / width;
        } else {
            this.mMinScale = FullImageView.ASPECT_RATIO_SQUARE / height;
        }
        if (intrinsicWidth < intrinsicHeight) {
            this.mMaxScale = (((float) intrinsicWidth) * this.mMinScale) / ((float) this.mOptions.getMinWidth());
        } else {
            this.mMaxScale = (this.mMinScale * ((float) intrinsicHeight)) / ((float) this.mOptions.getMinHeight());
        }
        Matrix imageMatrix = this.mImageView.getImageMatrix();
        if (imageMatrix.isIdentity()) {
            if (this.mMinScale < FullImageView.ASPECT_RATIO_SQUARE) {
                imageMatrix.setTranslate(((float) (this.mImageView.getDrawable().getIntrinsicWidth() - this.mImageView.getWidth())) / -2.0f, ((float) (this.mImageView.getDrawable().getIntrinsicHeight() - this.mImageView.getHeight())) / -2.0f);
            } else {
                imageMatrix.setScale(this.mMinScale, this.mMinScale);
            }
            this.mImageView.setImageMatrix(imageMatrix);
            this.mImageView.postInvalidate();
        }
    }

    private void scaleImage(MotionEvent motionEvent) {
        Matrix imageMatrix = this.mImageView.getImageMatrix();
        int i = 0;
        int i2 = 0;
        while (i < motionEvent.getHistorySize()) {
            int postDistanceChange = i2 | postDistanceChange(imageMatrix, motionEvent.getHistoricalX(0, i), motionEvent.getHistoricalX(1, i), motionEvent.getHistoricalY(0, i), motionEvent.getHistoricalY(1, i));
            i++;
            i2 = postDistanceChange;
        }
        int postDistanceChange2 = postDistanceChange(imageMatrix, motionEvent.getX(0), motionEvent.getX(1), motionEvent.getY(0), motionEvent.getY(1)) | i2;
        this.mImageView.setImageMatrix(imageMatrix);
        if (postDistanceChange2 > 0) {
            this.mImageView.postInvalidate();
        }
    }

    private int postDistanceChange(Matrix matrix, float f, float f2, float f3, float f4) {
        int i = 0;
        float a = CropImageUtil.m5350a(f, f2, f3, f4);
        float f5 = a / this.mLastDistance;
        matrix.getValues(this.mValues);
        if (this.mValues[0] * f5 >= this.mMinScale && this.mValues[0] * f5 <= this.mMaxScale) {
            matrix.postScale(f5, f5, this.mScalePivotX, this.mScalePivotY);
            i = 1;
        }
        this.mLastDistance = a;
        return i;
    }
}
