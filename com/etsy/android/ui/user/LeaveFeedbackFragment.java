package com.etsy.android.ui.user;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;
import com.etsy.android.iconsy.views.IconDrawable;
import com.etsy.android.lib.R;
import com.etsy.android.lib.config.EtsyConfigKeys;
import com.etsy.android.lib.core.EtsyJobBuilder;
import com.etsy.android.lib.core.EtsyJobResponse;
import com.etsy.android.lib.core.EtsyResult;
import com.etsy.android.lib.core.aj;
import com.etsy.android.lib.logger.ITrackedObject;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.Review;
import com.etsy.android.lib.models.Transaction;
import com.etsy.android.lib.models.User;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.requests.EtsyMultipartEntity;
import com.etsy.android.lib.requests.EtsyMultipartEntity.AsyncMultipartRequestCallback;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.requests.EtsyRequest.APIv3Scope;
import com.etsy.android.lib.requests.EtsyRequest.RequestMethod;
import com.etsy.android.lib.requests.apiv3.LeaveReviewRequest;
import com.etsy.android.lib.util.CameraHelper;
import com.etsy.android.lib.util.aa;
import com.etsy.android.lib.util.bl;
import com.etsy.android.lib.util.fonts.StandardFontIcon;
import com.etsy.android.ui.EtsyFragment;
import com.etsy.android.ui.nav.EtsyEventTracker;
import com.etsy.android.ui.nav.Nav;
import com.etsy.android.uikit.image.CropImageUtil.Options;
import com.etsy.android.uikit.p016a.AnimationManager;
import com.etsy.android.uikit.ui.dialog.DialogActivity;
import com.etsy.android.uikit.util.AnimationUtil;
import com.etsy.android.uikit.util.FragmentBackstackUtil;
import com.etsy.android.uikit.util.HardwareAnimatorListener;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.util.UserInputDialogHelper;
import com.etsy.android.uikit.view.ClickableImageView;
import com.etsy.android.uikit.view.RatingIconView;
import com.etsy.android.util.BOEOptionsMenuItemHelper;
import com.foresee.mobileReplay.recorder.ScreenRecorder;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.parceler.Parcels;

public class LeaveFeedbackFragment extends EtsyFragment implements AsyncMultipartRequestCallback<Review, LeaveReviewRequest<Review>>, CameraHelper {
    private static final String CROPPED_IMAGE_FILE_URI = "cropped_image_file_uri";
    private static final int[] EXAMPLE_PHOTO_DROP_DELAYS;
    private static final int EXAMPLE_PHOTO_DROP_START_DELAY = 2000;
    private static final int EXAMPLE_PHOTO_WIGGLE_START_DELAY = 1200;
    private static final String IMAGE_FILE_URI = "image_file_uri";
    private static final int INTRO_STAR_DURATION = 125;
    private static final float INTRO_STAR_SCALE_END = 1.5f;
    private static final float INTRO_STAR_SCALE_START = 1.0f;
    private static final int INTRO_STAR_START_DELAY = 1500;
    private static final String LAST_RATING = "last_rating";
    private static final int PHOTO_MIN_HEIGHT = 640;
    private static final int PHOTO_MIN_WIDTH = 640;
    private static final String REMOVE_PHOTO = "remove_photo";
    private static final String SHOP_NAME = "shop_name";
    private static final String TRANSACTION = "transaction";
    private static final String USER = "user";
    private static final Options options;
    private View mAddPhotoHint;
    private TextView mAddPhotoLaterHint;
    private AnimationManager mAnimationManager;
    private ClickableImageView mCameraButton;
    private ViewGroup mCameraButtonContainer;
    private CameraHelper mCameraHelper;
    private View mContactShopClickArea;
    private final OnClickListener mContactShopClickListener;
    private File mCroppedFile;
    private Uri mCroppedFileUri;
    private boolean mEditing;
    private View mErrorView;
    private ImageView mExamplePhoto1;
    private ImageView mExamplePhoto2;
    private ImageView mExamplePhoto3;
    private ImageView mExamplePhoto4;
    private ImageView mExamplePhoto5;
    private File mFile;
    private Uri mFileUri;
    private ImageView mImgListing;
    private UserInputDialogHelper mInputHelper;
    private boolean mIsDialogActivity;
    private float mLastRating;
    private ViewGroup mLayoutDetailsSection;
    private View mLoadingView;
    private int mMinimumWordsFromConfig;
    private OnRatingBarChangeListener mOnRatingChanged;
    private RatingIconView mRatingIntro;
    private RatingBar mRatingPicker;
    private boolean mRemovePhoto;
    private View mRemovePhotoButton;
    private final OnClickListener mRemovePhotoClickListener;
    private ScrollView mScrollView;
    private String mShopName;
    private boolean mShowReviewMenuItem;
    private final OnClickListener mStartCameraClickListener;
    private Button mSubmitButton;
    private final OnClickListener mSubmitClickListener;
    private Transaction mTransaction;
    private TextView mTxtError;
    private TextView mTxtListingTitle;
    private EditText mTxtMessage;
    private TextView mTxtRatingDesc;
    private TextView mTxtReviewHint;
    private TextView mTxtShopName;
    private TextView mTxtTapStarsHint;
    private User mUser;
    private ViewGroup mView;

    /* renamed from: com.etsy.android.ui.user.LeaveFeedbackFragment.10 */
    class AnonymousClass10 extends HardwareAnimatorListener {
        final /* synthetic */ TextView f3445a;
        final /* synthetic */ LeaveFeedbackFragment f3446b;

        AnonymousClass10(LeaveFeedbackFragment leaveFeedbackFragment, View view, TextView textView) {
            this.f3446b = leaveFeedbackFragment;
            this.f3445a = textView;
            super(view);
        }

        public void onAnimationEnd(Animator animator) {
            if (this.f3445a != null) {
                this.f3445a.setText(this.f3446b.getString(R.review_add_photo_hint));
                AnimationUtil.m5522a(this.f3445a, (int) Callback.DEFAULT_DRAG_ANIMATION_DURATION, this.f3446b.createExamplePhotosFinishedListener(this.f3445a));
                this.f3446b.showMessageBox();
            }
        }
    }

    /* renamed from: com.etsy.android.ui.user.LeaveFeedbackFragment.11 */
    class AnonymousClass11 extends HardwareAnimatorListener {
        final /* synthetic */ LeaveFeedbackFragment f3447a;

        AnonymousClass11(LeaveFeedbackFragment leaveFeedbackFragment, View view) {
            this.f3447a = leaveFeedbackFragment;
            super(view);
        }

        public void onAnimationEnd(Animator animator) {
            if (this.f3447a.mExamplePhoto1 != null) {
                this.f3447a.mLayoutDetailsSection.setLayoutAnimation(null);
                this.f3447a.mExamplePhoto1.setVisibility(8);
                this.f3447a.mExamplePhoto2.setVisibility(8);
                this.f3447a.mExamplePhoto3.setVisibility(8);
                this.f3447a.mExamplePhoto4.setVisibility(8);
                this.f3447a.mExamplePhoto5.setVisibility(8);
            }
        }
    }

    /* renamed from: com.etsy.android.ui.user.LeaveFeedbackFragment.19 */
    class AnonymousClass19 extends TrackingOnClickListener {
        final /* synthetic */ LeaveFeedbackFragment f3455a;

        AnonymousClass19(LeaveFeedbackFragment leaveFeedbackFragment, ITrackedObject... iTrackedObjectArr) {
            this.f3455a = leaveFeedbackFragment;
            super(iTrackedObjectArr);
        }

        public void onViewClick(View view) {
            view.setVisibility(8);
        }
    }

    /* renamed from: com.etsy.android.ui.user.LeaveFeedbackFragment.1 */
    class C08211 extends TrackingOnClickListener {
        final /* synthetic */ LeaveFeedbackFragment f3456a;

        C08211(LeaveFeedbackFragment leaveFeedbackFragment) {
            this.f3456a = leaveFeedbackFragment;
        }

        public void onPreTrack() {
            addEventTrackedObjects(this.f3456a.mUser, this.f3456a.mTransaction);
        }

        public void onViewClick(View view) {
            this.f3456a.startImagePicker();
        }
    }

    /* renamed from: com.etsy.android.ui.user.LeaveFeedbackFragment.21 */
    class AnonymousClass21 extends HardwareAnimatorListener {
        final /* synthetic */ LeaveFeedbackFragment f3458a;

        AnonymousClass21(LeaveFeedbackFragment leaveFeedbackFragment, View view) {
            this.f3458a = leaveFeedbackFragment;
            super(view);
        }

        public void onAnimationEnd(Animator animator) {
            if (this.f3458a.mRatingPicker != null) {
                this.f3458a.mRatingPicker.setIsIndicator(false);
                this.f3458a.mRatingPicker.setOnRatingBarChangeListener(this.f3458a.mOnRatingChanged);
            }
        }
    }

    /* renamed from: com.etsy.android.ui.user.LeaveFeedbackFragment.2 */
    class C08222 extends HardwareAnimatorListener {
        final /* synthetic */ LeaveFeedbackFragment f3459a;

        C08222(LeaveFeedbackFragment leaveFeedbackFragment, View view) {
            this.f3459a = leaveFeedbackFragment;
            super(view);
        }

        public void onAnimationEnd(Animator animator) {
            super.onAnimationEnd(animator);
            if (!(this.f3459a.mScrollView == null || this.f3459a.mScrollView.getLayoutTransition() == null)) {
                this.f3459a.mScrollView.getLayoutTransition().getAnimator(4).removeListener(this);
            }
            this.f3459a.showMessageBox();
        }
    }

    /* renamed from: com.etsy.android.ui.user.LeaveFeedbackFragment.3 */
    class C08233 extends HardwareAnimatorListener {
        final /* synthetic */ LeaveFeedbackFragment f3460a;

        C08233(LeaveFeedbackFragment leaveFeedbackFragment, View view) {
            this.f3460a = leaveFeedbackFragment;
            super(view);
        }

        public void onAnimationEnd(Animator animator) {
            super.onAnimationEnd(animator);
            this.f3460a.mTxtReviewHint.setVisibility(8);
            AnimationUtil.m5521a(this.f3460a.mContactShopClickArea, (int) Callback.DEFAULT_DRAG_ANIMATION_DURATION);
        }
    }

    /* renamed from: com.etsy.android.ui.user.LeaveFeedbackFragment.4 */
    class C08244 extends HardwareAnimatorListener {
        final /* synthetic */ LeaveFeedbackFragment f3461a;

        C08244(LeaveFeedbackFragment leaveFeedbackFragment, View view) {
            this.f3461a = leaveFeedbackFragment;
            super(view);
        }

        public void onAnimationEnd(Animator animator) {
            super.onAnimationEnd(animator);
            this.f3461a.mContactShopClickArea.setVisibility(8);
            AnimationUtil.m5521a(this.f3461a.mTxtReviewHint, (int) Callback.DEFAULT_DRAG_ANIMATION_DURATION);
        }
    }

    /* renamed from: com.etsy.android.ui.user.LeaveFeedbackFragment.5 */
    class C08255 implements Runnable {
        final /* synthetic */ LeaveFeedbackFragment f3462a;

        C08255(LeaveFeedbackFragment leaveFeedbackFragment) {
            this.f3462a = leaveFeedbackFragment;
        }

        public void run() {
            this.f3462a.mScrollView.smoothScrollTo(0, this.f3462a.mScrollView.getBottom());
        }
    }

    /* renamed from: com.etsy.android.ui.user.LeaveFeedbackFragment.6 */
    class C08266 implements OnRatingBarChangeListener {
        final /* synthetic */ LeaveFeedbackFragment f3463a;

        C08266(LeaveFeedbackFragment leaveFeedbackFragment) {
            this.f3463a = leaveFeedbackFragment;
        }

        public void onRatingChanged(RatingBar ratingBar, float f, boolean z) {
            int i;
            if (this.f3463a.mLastRating == 0.0f) {
                this.f3463a.mTxtTapStarsHint.setVisibility(8);
            }
            this.f3463a.mTxtRatingDesc.setVisibility(0);
            this.f3463a.mTxtError.setVisibility(8);
            if (f == 5.0f) {
                this.f3463a.mTxtMessage.setHint(R.review_message_hint_with_photo);
                if (this.f3463a.mLastRating == 0.0f) {
                    i = 1;
                    this.f3463a.showExamplePhotosAnimation();
                } else {
                    this.f3463a.mAddPhotoLaterHint.setVisibility(0);
                    this.f3463a.mTxtRatingDesc.setText(this.f3463a.getString(R.review_rating_desc_max));
                    this.f3463a.mTxtReviewHint.setText(this.f3463a.getString(R.review_add_photo_hint));
                    this.f3463a.hideBadRatingAdvice();
                    i = 0;
                }
            } else if (f >= 5.0f || f <= 3.0f) {
                this.f3463a.mAddPhotoLaterHint.setVisibility(8);
                this.f3463a.mTxtMessage.setHint(R.review_message_hint);
                this.f3463a.mTxtRatingDesc.setText(this.f3463a.getString(R.review_rating_desc_neutral));
                this.f3463a.showBadRatingAdvice();
                i = 0;
            } else {
                this.f3463a.mAddPhotoLaterHint.setVisibility(8);
                this.f3463a.mTxtMessage.setHint(R.review_message_hint);
                this.f3463a.mTxtRatingDesc.setText(this.f3463a.getString(R.review_rating_desc_like));
                this.f3463a.mTxtReviewHint.setText(this.f3463a.getString(R.review_review_item_hint));
                this.f3463a.hideBadRatingAdvice();
                i = 0;
            }
            if (f == 5.0f) {
                this.f3463a.mCameraButtonContainer.setVisibility(0);
            } else {
                this.f3463a.mCameraButtonContainer.setVisibility(8);
            }
            if (i == 0) {
                if (this.f3463a.mLayoutDetailsSection.getVisibility() == 0 || !aa.m3167a()) {
                    this.f3463a.showMessageBox();
                } else {
                    this.f3463a.showMessageBoxAfterLayout();
                }
            }
            this.f3463a.mLayoutDetailsSection.setVisibility(0);
            this.f3463a.mLastRating = f;
        }
    }

    /* renamed from: com.etsy.android.ui.user.LeaveFeedbackFragment.7 */
    class C08277 extends TrackingOnClickListener {
        final /* synthetic */ LeaveFeedbackFragment f3464a;

        C08277(LeaveFeedbackFragment leaveFeedbackFragment) {
            this.f3464a = leaveFeedbackFragment;
        }

        public void onPreTrack() {
            addEventTrackedObjects(this.f3464a.mUser, this.f3464a.mTransaction);
        }

        public void onViewClick(View view) {
            this.f3464a.mTxtError.setVisibility(8);
            if (this.f3464a.isValidReview()) {
                this.f3464a.submitReview();
            }
        }
    }

    /* renamed from: com.etsy.android.ui.user.LeaveFeedbackFragment.8 */
    class C08288 extends TrackingOnClickListener {
        final /* synthetic */ LeaveFeedbackFragment f3465a;

        C08288(LeaveFeedbackFragment leaveFeedbackFragment) {
            this.f3465a = leaveFeedbackFragment;
        }

        public void onPreTrack() {
            addEventTrackedObjects(this.f3465a.mUser, this.f3465a.mTransaction);
        }

        public void onViewClick(View view) {
            if (this.f3465a.mUser != null) {
                Bundle bundle = new Bundle();
                bundle.putString("username", this.f3465a.mUser.getLoginName());
                Nav.m4682a(this.f3465a.getActivity()).m4683a().m4516d(bundle);
            }
        }
    }

    /* renamed from: com.etsy.android.ui.user.LeaveFeedbackFragment.9 */
    class C08299 extends HardwareAnimatorListener {
        final /* synthetic */ LeaveFeedbackFragment f3466a;

        C08299(LeaveFeedbackFragment leaveFeedbackFragment, View view) {
            this.f3466a = leaveFeedbackFragment;
            super(view);
        }

        public void onAnimationEnd(Animator animator) {
            if (this.f3466a.mTxtReviewHint != null) {
                this.f3466a.mAddPhotoLaterHint.setVisibility(0);
                LayoutParams layoutParams = (LayoutParams) this.f3466a.mCameraButtonContainer.getLayoutParams();
                layoutParams.setMargins(layoutParams.leftMargin, layoutParams.topMargin, layoutParams.rightMargin, ((LayoutParams) this.f3466a.mTxtMessage.getLayoutParams()).bottomMargin);
                AnimationUtil.m5526b(this.f3466a.mTxtReviewHint, Callback.DEFAULT_DRAG_ANIMATION_DURATION, this.f3466a.createExamplePhotosCleanupListener(this.f3466a.mTxtReviewHint));
            }
        }
    }

    class GetTransactionRequest extends EtsyRequest<Transaction> {
        public GetTransactionRequest(EtsyId etsyId) {
            super("/transactions/" + etsyId, RequestMethod.GET, Transaction.class);
        }
    }

    public LeaveFeedbackFragment() {
        this.mShowReviewMenuItem = true;
        this.mStartCameraClickListener = new C08211(this);
        this.mRemovePhotoClickListener = new TrackingOnClickListener() {
            final /* synthetic */ LeaveFeedbackFragment f3448a;

            {
                this.f3448a = r1;
            }

            public void onPreTrack() {
                addEventTrackedObjects(this.f3448a.mUser, this.f3448a.mTransaction);
            }

            public void onViewClick(View view) {
                this.f3448a.mCameraButton.setOnClickListener(this.f3448a.mStartCameraClickListener);
                this.f3448a.mCameraButton.setImageResource(R.btn_blue_v2);
                this.f3448a.mRemovePhotoButton.setVisibility(8);
                this.f3448a.mAddPhotoHint.setVisibility(0);
                this.f3448a.mAddPhotoLaterHint.setVisibility(0);
                this.f3448a.mFileUri = null;
                this.f3448a.mFile = null;
                if (this.f3448a.mEditing) {
                    this.f3448a.mRemovePhoto = true;
                }
            }
        };
        this.mOnRatingChanged = new C08266(this);
        this.mSubmitClickListener = new C08277(this);
        this.mContactShopClickListener = new C08288(this);
    }

    static {
        options = new Options().setMinHeight(PHOTO_MIN_WIDTH).setMinWidth(PHOTO_MIN_WIDTH);
        EXAMPLE_PHOTO_DROP_DELAYS = new int[]{800, 700, 0, ScreenRecorder.ORIENTATION_SAMPLING_FREQ, Callback.DEFAULT_DRAG_ANIMATION_DURATION};
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        this.mIsDialogActivity = getActivity() instanceof DialogActivity;
        setHasOptionsMenu(!this.mIsDialogActivity);
        this.mTransaction = (Transaction) arguments.getSerializable(TRANSACTION);
        this.mShopName = arguments.getString(SHOP_NAME);
        this.mUser = (User) arguments.getSerializable(USER);
        this.mInputHelper = new UserInputDialogHelper(getActivity());
        this.mMinimumWordsFromConfig = getConfigMap().m886d(EtsyConfigKeys.f1223p);
        this.mCameraHelper = new CameraHelper(getActivity().getApplicationContext(), bundle, this);
        if (bundle != null) {
            this.mLastRating = bundle.getFloat(LAST_RATING);
            String string = bundle.getString(IMAGE_FILE_URI);
            if (string != null) {
                this.mFileUri = Uri.parse(string);
                this.mFile = new File(this.mFileUri.getPath());
            }
            string = bundle.getString(CROPPED_IMAGE_FILE_URI);
            if (string != null) {
                this.mCroppedFileUri = Uri.parse(string);
                this.mCroppedFile = new File(this.mCroppedFileUri.getPath());
            }
            this.mRemovePhoto = bundle.getBoolean(REMOVE_PHOTO, false);
            this.mTransaction = (Transaction) Parcels.m7495a(bundle.getParcelable(TRANSACTION));
            this.mUser = (User) Parcels.m7495a(bundle.getParcelable(USER));
            this.mShopName = bundle.getString(SHOP_NAME);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        super.onCreateView(layoutInflater, viewGroup, bundle);
        this.mAnimationManager = new AnimationManager(getActivity());
        this.mView = (ViewGroup) layoutInflater.inflate(2130903207, viewGroup, false);
        this.mScrollView = (ScrollView) this.mView.getChildAt(0);
        this.mLoadingView = this.mView.findViewById(R.loading_view);
        this.mErrorView = this.mView.findViewById(R.no_internet);
        if (this.mIsDialogActivity) {
            this.mView.setPadding(0, 0, 0, 0);
            this.mScrollView.getChildAt(0).setBackgroundResource(R.white);
            if (getActivity() instanceof LeaveFeedbackDialogActivity) {
                ((ViewGroup) this.mScrollView.getChildAt(0)).addView(createSubmitButton());
            }
        }
        if (aa.m3167a()) {
            bl.m3360a(this.mScrollView);
        }
        this.mTxtMessage = (EditText) this.mView.findViewById(R.review_message);
        this.mRatingPicker = (RatingBar) this.mView.findViewById(R.rating);
        this.mTxtListingTitle = (TextView) this.mView.findViewById(2131755687);
        this.mImgListing = (ImageView) this.mView.findViewById(2131755686);
        this.mTxtMessage.setOnFocusChangeListener(new OnFocusChangeListener() {
            final /* synthetic */ LeaveFeedbackFragment f3451a;

            {
                this.f3451a = r1;
            }

            public void onFocusChange(View view, boolean z) {
                if (view.equals(this.f3451a.mTxtMessage) && z) {
                    this.f3451a.mScrollView.setLayoutTransition(null);
                }
            }
        });
        this.mTxtError = (TextView) this.mView.findViewById(2131755695);
        this.mLayoutDetailsSection = (ViewGroup) this.mView.findViewById(2131755693);
        this.mContactShopClickArea = this.mView.findViewById(2131755692);
        this.mTxtShopName = (TextView) this.mView.findViewById(2131755688);
        this.mCameraButtonContainer = (ViewGroup) this.mView.findViewById(2131755696);
        this.mCameraButton = (ClickableImageView) this.mView.findViewById(2131755697);
        this.mTxtTapStarsHint = (TextView) this.mView.findViewById(2131755691);
        this.mTxtRatingDesc = (TextView) this.mView.findViewById(2131755689);
        this.mTxtReviewHint = (TextView) this.mView.findViewById(2131755694);
        this.mRemovePhotoButton = this.mView.findViewById(2131755700);
        this.mAddPhotoHint = this.mView.findViewById(2131755698);
        this.mRatingIntro = (RatingIconView) this.mView.findViewById(2131755690);
        if (aa.m3167a()) {
            bl.m3360a((ViewGroup) this.mCameraButton.getParent());
        }
        this.mExamplePhoto1 = (ImageView) this.mView.findViewById(2131755701);
        this.mExamplePhoto2 = (ImageView) this.mView.findViewById(2131755702);
        this.mExamplePhoto3 = (ImageView) this.mView.findViewById(2131755703);
        this.mExamplePhoto4 = (ImageView) this.mView.findViewById(2131755704);
        this.mExamplePhoto5 = (ImageView) this.mView.findViewById(2131755705);
        this.mAddPhotoLaterHint = (TextView) this.mView.findViewById(2131755707);
        return this.mView;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (this.mTransaction != null) {
            setTransactionData();
            return;
        }
        showLoading();
        fetchTransaction();
    }

    private void fetchTransaction() {
        getRequestQueue().m1697a((Object) this, EtsyJobBuilder.m1307a(new GetTransactionRequest((EtsyId) getArguments().getSerializable(ResponseConstants.TRANSACTION_ID))).m1326b("Seller(login_name,user_id)/Shops(shop_name),MainImage(listing_image_id,url_fullxfull,url_75x75,full_height,full_width),GiftCardDesign(design_id,url_150x119,url_280x166,url_560x332,url_150x119),UserReview/AppreciationPhoto(url_fullxfull,is_seller_approved,status)").m1321a(new EtsyJobResponse<Transaction>() {
            final /* synthetic */ LeaveFeedbackFragment f3454a;

            {
                this.f3454a = r1;
            }

            public void m5008a(List<Transaction> list, int i, EtsyResult<Transaction> etsyResult) {
                Transaction transaction = (Transaction) list.get(0);
                this.f3454a.mTransaction = transaction;
                this.f3454a.mUser = transaction.getSeller();
                this.f3454a.mShopName = transaction.getSeller().getMainShop().getShopName();
                if (this.f3454a.getActivity() == null) {
                    return;
                }
                if (aj.m1101a().m1125k().equals(this.f3454a.mTransaction.getBuyerUserId())) {
                    this.f3454a.setTransactionData();
                    this.f3454a.showContent();
                    return;
                }
                this.f3454a.showError();
            }
        }).m1319a(new EtsyJobResponse() {
            final /* synthetic */ LeaveFeedbackFragment f3453a;

            {
                this.f3453a = r1;
            }

            public void m5007a(EtsyResult etsyResult) {
                if (this.f3453a.getActivity() != null) {
                    this.f3453a.showError();
                }
            }
        }).m1320a(new EtsyJobResponse() {
            final /* synthetic */ LeaveFeedbackFragment f3452a;

            {
                this.f3452a = r1;
            }

            public void m5006a(int i, String str, EtsyResult etsyResult) {
                if (this.f3452a.getActivity() != null) {
                    this.f3452a.showError();
                }
            }
        }).m1324a());
    }

    private void setTransactionData() {
        this.mEditing = this.mTransaction.getReview() != null;
        if (this.mEditing && this.mLastRating == 0.0f) {
            this.mLastRating = (float) this.mTransaction.getReview().getRating();
        }
        populateReview();
        populateListing();
        addIconImageToAddCameraButton((ImageView) this.mView.findViewById(2131755699));
        this.mContactShopClickArea.setOnClickListener(this.mContactShopClickListener);
        this.mTxtError.setOnClickListener(new AnonymousClass19(this, this.mTransaction, this.mUser));
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        this.mCameraHelper.onActivityResult(i, i2, intent);
    }

    public void onCreateOptionsMenuWithIcons(Menu menu, MenuInflater menuInflater) {
        Context activity = getActivity();
        if (this.mShowReviewMenuItem && activity != null) {
            menuInflater.inflate(2131820556, menu);
            MenuItem findItem = menu.findItem(2131756569);
            View a = BOEOptionsMenuItemHelper.m5675a(activity, R.text_disabled_selector_blue, R.submit, null);
            findItem.setActionView(a);
            a.setOnClickListener(this.mSubmitClickListener);
        }
    }

    public void onDestroy() {
        this.mCameraHelper = null;
        super.onDestroy();
    }

    public void onDestroyView() {
        AnimationUtil.m5524a(this.mRatingIntro, this.mExamplePhoto1, this.mExamplePhoto2, this.mExamplePhoto3, this.mExamplePhoto4, this.mExamplePhoto5, this.mTxtReviewHint);
        this.mAnimationManager.m5283a();
        if (this.mInputHelper != null) {
            this.mInputHelper.m5628a();
        }
        super.onDestroyView();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putFloat(LAST_RATING, this.mLastRating);
        if (this.mFileUri != null) {
            bundle.putString(IMAGE_FILE_URI, this.mFileUri.toString());
        }
        if (this.mCroppedFile != null) {
            bundle.putString(CROPPED_IMAGE_FILE_URI, this.mCroppedFileUri.toString());
        }
        bundle.putBoolean(REMOVE_PHOTO, this.mRemovePhoto);
        if (this.mTransaction != null) {
            bundle.putParcelable(TRANSACTION, Parcels.m7494a(this.mTransaction));
        }
        if (this.mUser != null) {
            bundle.putParcelable(USER, Parcels.m7494a(this.mUser));
        }
        if (!TextUtils.isEmpty(this.mShopName)) {
            bundle.putString(SHOP_NAME, this.mShopName);
        }
    }

    private void addIconImageToAddCameraButton(ImageView imageView) {
        imageView.setImageDrawable(IconDrawable.m775a(getResources()).m779a(getResources().getColor(R.white)).m780a(StandardFontIcon.CAMERA).m778a((float) getResources().getDimensionPixelOffset(2131362240)).m777a());
    }

    private Button createSubmitButton() {
        this.mSubmitButton = new Button(getActivity());
        this.mSubmitButton.setOnClickListener(this.mSubmitClickListener);
        ViewGroup.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2);
        layoutParams.gravity = 80;
        layoutParams.setMargins(0, (int) getResources().getDimension(R.margin_medium_large), 0, 0);
        this.mSubmitButton.setLayoutParams(layoutParams);
        this.mSubmitButton.setBackgroundResource(R.btn_blue_square_v2);
        this.mSubmitButton.setText(getString(R.review_submit_menu));
        this.mSubmitButton.setTextAppearance(getActivity(), bl.m3352a(getActivity(), (int) R.textWhiteLargeBold));
        return this.mSubmitButton;
    }

    private void animateStarIntro() {
        this.mRatingPicker.setIsIndicator(true);
        this.mRatingIntro.setRating(5.0f);
        this.mRatingIntro.getViewTreeObserver().addOnPreDrawListener(new OnPreDrawListener() {
            final /* synthetic */ LeaveFeedbackFragment f3457a;

            {
                this.f3457a = r1;
            }

            public boolean onPreDraw() {
                this.f3457a.mRatingIntro.getViewTreeObserver().removeOnPreDrawListener(this);
                this.f3457a.mRatingIntro.getLayoutParams().width = this.f3457a.mRatingPicker.getLayoutParams().width;
                this.f3457a.mRatingIntro.getLayoutParams().height = this.f3457a.mRatingPicker.getLayoutParams().height;
                this.f3457a.mRatingIntro.setBackgroundResource(17170445);
                for (int i = 0; i < this.f3457a.mRatingIntro.getChildCount(); i++) {
                    this.f3457a.animateStarAt(i);
                }
                this.f3457a.hideStarIntro();
                return false;
            }
        });
    }

    private void animateStarAt(int i) {
        ImageView imageView = (ImageView) this.mRatingIntro.getChildAt(i);
        imageView.setAlpha(0.0f);
        Animator ofFloat = ObjectAnimator.ofFloat(imageView, "scaleX", new float[]{INTRO_STAR_SCALE_START, INTRO_STAR_SCALE_END});
        ofFloat.setDuration(125);
        Animator ofFloat2 = ObjectAnimator.ofFloat(imageView, "scaleY", new float[]{INTRO_STAR_SCALE_START, INTRO_STAR_SCALE_END});
        ofFloat2.setDuration(125);
        Animator ofFloat3 = ObjectAnimator.ofFloat(imageView, "scaleX", new float[]{INTRO_STAR_SCALE_END, INTRO_STAR_SCALE_START});
        ofFloat3.setDuration(125);
        Animator ofFloat4 = ObjectAnimator.ofFloat(imageView, "scaleY", new float[]{INTRO_STAR_SCALE_END, INTRO_STAR_SCALE_START});
        ofFloat4.setDuration(125);
        Animator ofFloat5 = ObjectAnimator.ofFloat(imageView, "alpha", new float[]{0.0f, INTRO_STAR_SCALE_START});
        ofFloat5.setDuration(125);
        Animator animatorSet = new AnimatorSet();
        animatorSet.play(ofFloat3).with(ofFloat4);
        ofFloat3 = new AnimatorSet();
        ofFloat3.setStartDelay((long) ((i * 100) + INTRO_STAR_START_DELAY));
        ofFloat3.play(ofFloat).with(ofFloat2).with(ofFloat5).before(animatorSet);
        this.mAnimationManager.m5282a(ofFloat3);
        ofFloat3.start();
    }

    private void hideStarIntro() {
        Animator ofFloat = ObjectAnimator.ofFloat(this.mRatingIntro, "alpha", new float[]{INTRO_STAR_SCALE_START, 0.0f});
        ofFloat.setDuration(300);
        Animator animatorSet = new AnimatorSet();
        animatorSet.play(ofFloat).after((long) ((this.mRatingIntro.getChildCount() * 100) + 1750));
        animatorSet.start();
        this.mAnimationManager.m5282a(animatorSet);
        ofFloat.addListener(new AnonymousClass21(this, this.mRatingIntro));
    }

    private void setCameraButtonFromFile(File file) {
        this.mCameraButton.setImageBitmap(BitmapFactory.decodeFile(file.getPath()));
    }

    private void populateCameraButton() {
        Review review = this.mTransaction.getReview();
        this.mRemovePhotoButton.setOnClickListener(this.mRemovePhotoClickListener);
        if ((review == null || !review.hasAppreciationPhoto() || this.mRemovePhoto) && this.mFile == null && this.mCroppedFile == null) {
            this.mRemovePhotoButton.setVisibility(8);
            this.mAddPhotoHint.setVisibility(0);
            this.mCameraButton.setOnClickListener(this.mStartCameraClickListener);
            return;
        }
        if (this.mCroppedFile != null) {
            setCameraButtonFromFile(this.mCroppedFile);
        } else if (this.mFile != null) {
            setCameraButtonFromFile(this.mFile);
        } else if (review.hasAppreciationPhoto()) {
            getImageBatch().m1568a(review.getAppreciationPhoto().getImageUrl(), this.mCameraButton);
        }
        this.mRemovePhotoButton.setVisibility(0);
        this.mAddPhotoHint.setVisibility(8);
        this.mAddPhotoLaterHint.setVisibility(8);
        this.mCameraButton.setOnClickListener(null);
    }

    private void populateReview() {
        Review review = this.mTransaction.getReview();
        if (review != null) {
            this.mTxtMessage.setText(review.getReviewMessage());
            this.mRatingPicker.setRating((float) review.getRating());
            this.mTxtMessage.setVisibility(0);
        } else if (this.mLastRating == 0.0f) {
            this.mTxtMessage.setVisibility(8);
        }
        populateCameraButton();
        if (review != null || this.mLastRating > 0.0f) {
            float rating = review != null ? (float) review.getRating() : this.mLastRating;
            this.mLayoutDetailsSection.setVisibility(0);
            this.mTxtRatingDesc.setVisibility(0);
            this.mTxtTapStarsHint.setVisibility(8);
            if (this.mTransaction.isFeedbackMutable()) {
                if (rating < 5.0f) {
                    this.mCameraButtonContainer.setVisibility(8);
                    this.mTxtReviewHint.setText(getString(R.review_review_item_hint));
                } else {
                    if (review == null || !review.hasAppreciationPhoto()) {
                        this.mAddPhotoLaterHint.setVisibility(0);
                    } else {
                        this.mAddPhotoLaterHint.setVisibility(8);
                    }
                    this.mTxtReviewHint.setText(getString(R.review_add_photo_hint));
                }
                if (rating <= 3.0f) {
                    this.mContactShopClickArea.setVisibility(0);
                    this.mTxtReviewHint.setVisibility(8);
                }
                this.mRatingIntro.setVisibility(8);
                this.mRatingPicker.setOnRatingBarChangeListener(this.mOnRatingChanged);
                return;
            }
            configureForImmutableReview(review);
            return;
        }
        this.mLayoutDetailsSection.setVisibility(8);
        this.mTxtRatingDesc.setVisibility(8);
        this.mTxtTapStarsHint.setVisibility(0);
        animateStarIntro();
    }

    private void configureForImmutableReview(Review review) {
        this.mTxtMessage.setFocusable(false);
        this.mTxtMessage.setEnabled(false);
        this.mTxtMessage.setHint(null);
        if (!this.mIsDialogActivity) {
            configureSubmitMenuItem(false);
        } else if (this.mSubmitButton != null) {
            this.mSubmitButton.setVisibility(8);
        }
        this.mTxtReviewHint.setVisibility(8);
        this.mRatingPicker.setIsIndicator(true);
        if (review.hasAppreciationPhoto()) {
            this.mRemovePhotoButton.setVisibility(8);
        } else {
            this.mCameraButtonContainer.setVisibility(8);
        }
    }

    private void populateListing() {
        this.mTxtListingTitle.setText(this.mTransaction.getTitle());
        if (this.mShopName != null) {
            this.mTxtShopName.setText(getString(R.review_listing_item_from, this.mShopName));
            this.mTxtShopName.setVisibility(0);
        } else {
            this.mTxtShopName.setVisibility(8);
        }
        String str = null;
        if (this.mTransaction.isGiftCard() && this.mTransaction.getGiftCardDesign() != null && this.mTransaction.getGiftCardDesign().getUrl150x119() != null) {
            str = this.mTransaction.getGiftCardDesign().getUrl150x119();
        } else if (!(this.mTransaction.getMainImage() == null || this.mTransaction.getMainImage().getUrl75x75() == null)) {
            str = this.mTransaction.getMainImage().getUrl75x75();
        }
        getImageBatch().m1570a(str, this.mImgListing, getResources().getDimensionPixelSize(R.listing_thumbnail_width), getResources().getDimensionPixelSize(R.listing_thumbnail_height));
    }

    private void showMessageBox() {
        if (this.mTxtMessage.getVisibility() == 8) {
            this.mTxtMessage.setVisibility(0);
        }
    }

    @TargetApi(16)
    private void showMessageBoxAfterLayout() {
        if (this.mScrollView.getLayoutTransition() == null) {
            showMessageBox();
        } else {
            this.mScrollView.getLayoutTransition().getAnimator(4).addListener(new C08222(this, this.mScrollView));
        }
    }

    private void showBadRatingAdvice() {
        if (this.mUser != null && this.mContactShopClickArea.getVisibility() != 0) {
            AnimationUtil.m5526b(this.mTxtReviewHint, Callback.DEFAULT_DRAG_ANIMATION_DURATION, new C08233(this, this.mTxtReviewHint));
        }
    }

    private void hideBadRatingAdvice() {
        if (this.mContactShopClickArea.getVisibility() != 8) {
            AnimationUtil.m5526b(this.mContactShopClickArea, Callback.DEFAULT_DRAG_ANIMATION_DURATION, new C08244(this, this.mContactShopClickArea));
        }
    }

    protected void finishReview(Review review) {
        if (review == null) {
            review = new Review((int) this.mRatingPicker.getRating(), this.mTxtMessage.getText().toString().trim());
        }
        this.mTransaction.setReview(review);
        if (getActivity() != null) {
            UserBadgeCountManager.m5065a(getActivity());
            Intent intent = new Intent();
            intent.putExtra(TRANSACTION, this.mTransaction);
            getActivity().setResult(411, intent);
            FragmentBackstackUtil.m5542b(getActivity().getSupportFragmentManager(), Nav.m4682a(getActivity()));
        }
    }

    boolean isValidReview() {
        int rating = (int) this.mRatingPicker.getRating();
        int length = this.mTxtMessage.getText().toString().trim().split("\\s+").length;
        if (rating < 1 || rating > 5) {
            this.mInputHelper.m5631a(this.mTxtError, (int) R.error_review_stars);
            return false;
        } else if (rating >= 5 || length >= this.mMinimumWordsFromConfig) {
            return true;
        } else {
            this.mInputHelper.m5631a(this.mTxtError, (int) R.error_review_words);
            return false;
        }
    }

    void submitReview() {
        int rating = (int) this.mRatingPicker.getRating();
        String trim = this.mTxtMessage.getText().toString().trim();
        EtsyEventTracker.m4559b(rating);
        EtsyRequest leaveReviewRequest = new LeaveReviewRequest("/reviews", RequestMethod.POST, Review.class);
        leaveReviewRequest.setV3Scope(APIv3Scope.MEMBER);
        try {
            EtsyMultipartEntity etsyMultipartEntity = new EtsyMultipartEntity();
            etsyMultipartEntity.addPart(ResponseConstants.RATING, new StringBody(Integer.toString(rating)));
            etsyMultipartEntity.addPart(ResponseConstants.TRANSLATED_REVIEW, new StringBody(trim));
            etsyMultipartEntity.addPart(ResponseConstants.TRANSACTION_ID, new StringBody(this.mTransaction.getTransactionId().getId()));
            if (this.mCroppedFile != null) {
                etsyMultipartEntity.addPart(ResponseConstants.IMAGE, new FileBody(this.mCroppedFile, "jpeg"));
            } else if (this.mFile != null) {
                etsyMultipartEntity.addPart(ResponseConstants.IMAGE, new FileBody(this.mFile, "jpeg"));
            } else if (this.mRemovePhoto) {
                etsyMultipartEntity.addPart("remove_image", new StringBody(Integer.toString(1)));
            }
            etsyMultipartEntity.createMultipartAsync(leaveReviewRequest, this);
        } catch (UnsupportedEncodingException e) {
            onRequestCreationFailed();
        }
    }

    private void showExamplePhotosAnimation() {
        this.mLayoutDetailsSection.setVisibility(0);
        this.mExamplePhoto1.setVisibility(0);
        this.mExamplePhoto2.setVisibility(0);
        this.mExamplePhoto3.setVisibility(0);
        this.mExamplePhoto4.setVisibility(0);
        this.mExamplePhoto5.setVisibility(0);
        this.mScrollView.setLayoutTransition(null);
        this.mScrollView.post(new C08255(this));
        int width = ((((this.mView.getWidth() - (getResources().getDimensionPixelSize(R.margin_medium_large) * 4)) - this.mView.getPaddingLeft()) - this.mView.getPaddingRight()) - (getResources().getDimensionPixelSize(R.card_shadow_padding) * 2)) / 3;
        this.mExamplePhoto1.getLayoutParams().width = width;
        this.mExamplePhoto1.getLayoutParams().height = width;
        this.mExamplePhoto2.getLayoutParams().width = width;
        this.mExamplePhoto2.getLayoutParams().height = width;
        this.mExamplePhoto3.getLayoutParams().width = width;
        this.mExamplePhoto3.getLayoutParams().height = width;
        this.mExamplePhoto4.getLayoutParams().width = width;
        this.mExamplePhoto4.getLayoutParams().height = width;
        this.mExamplePhoto5.getLayoutParams().width = width;
        this.mExamplePhoto5.getLayoutParams().height = width;
        this.mCameraButtonContainer.getLayoutParams().width = width;
        this.mCameraButtonContainer.getLayoutParams().height = width;
        ((LayoutParams) this.mAddPhotoHint.getLayoutParams()).addRule(13);
        this.mAnimationManager.m5282a(AnimationUtil.m5525b(this.mExamplePhoto1, (int) EXAMPLE_PHOTO_WIGGLE_START_DELAY));
        this.mAnimationManager.m5282a(AnimationUtil.m5525b(this.mExamplePhoto2, (int) EXAMPLE_PHOTO_WIGGLE_START_DELAY));
        this.mAnimationManager.m5282a(AnimationUtil.m5525b(this.mExamplePhoto3, (int) EXAMPLE_PHOTO_WIGGLE_START_DELAY));
        this.mAnimationManager.m5282a(AnimationUtil.m5525b(this.mExamplePhoto4, (int) EXAMPLE_PHOTO_WIGGLE_START_DELAY));
        this.mAnimationManager.m5282a(AnimationUtil.m5525b(this.mExamplePhoto5, (int) EXAMPLE_PHOTO_WIGGLE_START_DELAY));
        this.mTxtReviewHint.setText(getString(R.review_show_your_appreciation));
        this.mAnimationManager.m5282a(AnimationUtil.m5520a(this.mExamplePhoto1, EXAMPLE_PHOTO_DROP_DELAYS[0] + EXAMPLE_PHOTO_DROP_START_DELAY, createExamplePhotosSkewAndDropListener(this.mExamplePhoto1)));
        this.mAnimationManager.m5282a(AnimationUtil.m5520a(this.mExamplePhoto2, EXAMPLE_PHOTO_DROP_DELAYS[1] + EXAMPLE_PHOTO_DROP_START_DELAY, null));
        this.mAnimationManager.m5282a(AnimationUtil.m5520a(this.mExamplePhoto3, EXAMPLE_PHOTO_DROP_DELAYS[2] + EXAMPLE_PHOTO_DROP_START_DELAY, null));
        this.mAnimationManager.m5282a(AnimationUtil.m5520a(this.mExamplePhoto4, EXAMPLE_PHOTO_DROP_DELAYS[3] + EXAMPLE_PHOTO_DROP_START_DELAY, null));
        this.mAnimationManager.m5282a(AnimationUtil.m5520a(this.mExamplePhoto5, EXAMPLE_PHOTO_DROP_DELAYS[4] + EXAMPLE_PHOTO_DROP_START_DELAY, null));
    }

    private HardwareAnimatorListener createExamplePhotosSkewAndDropListener(View view) {
        return new C08299(this, view);
    }

    private HardwareAnimatorListener createExamplePhotosCleanupListener(TextView textView) {
        return new AnonymousClass10(this, textView, textView);
    }

    private HardwareAnimatorListener createExamplePhotosFinishedListener(View view) {
        return new AnonymousClass11(this, view);
    }

    private void startImagePicker() {
        if (getConfigMap().m885c(EtsyConfigKeys.bi)) {
            this.mCameraHelper.startImagePickerForCrop((Fragment) this, (int) R.choose_image);
        } else {
            this.mCameraHelper.startImagePicker((Fragment) this, (int) R.choose_image);
        }
    }

    public void onNoAvailableActivities() {
        bl.m3365b(getContext(), (int) R.no_available_chooser);
    }

    public Object onPreImageSave() {
        return null;
    }

    public void onImageSaveSuccess(Object obj, Bitmap bitmap, File file) {
        this.mFile = file;
        this.mFileUri = Uri.fromFile(this.mFile);
        this.mRemovePhoto = false;
        this.mCameraButton.setImageBitmap(bitmap);
        this.mCameraButton.setOnClickListener(null);
        this.mRemovePhotoButton.setVisibility(0);
        this.mAddPhotoHint.setVisibility(8);
        this.mAddPhotoLaterHint.setVisibility(8);
    }

    public void onImageSaveFail(Object obj, File file) {
        this.mInputHelper.m5632a(this.mTxtError, getString(R.camera_error_creating_file));
    }

    public void onRequestCrop(Uri uri, Uri uri2) {
        Nav.m4682a(getActivity()).m4683a().m4444a(49, (Fragment) this).m4450a(uri, uri2, options);
    }

    public void onPermissionGranted() {
        startImagePicker();
    }

    public void onRequestCreated(LeaveReviewRequest<Review> leaveReviewRequest) {
        this.mInputHelper.m5629a((int) R.sending_review);
        getRequestQueue().m1699a(EtsyJobBuilder.m1307a((EtsyRequest) leaveReviewRequest).m1321a(new EtsyJobResponse<Review>() {
            final /* synthetic */ LeaveFeedbackFragment f3450a;

            {
                this.f3450a = r1;
            }

            public void m5005a(List<Review> list, int i, EtsyResult<Review> etsyResult) {
                if (this.f3450a.mInputHelper != null) {
                    this.f3450a.mInputHelper.m5628a();
                }
                Review review = null;
                if (list.size() > 0 && list.get(0) != null) {
                    review = (Review) list.get(0);
                }
                this.f3450a.finishReview(review);
            }
        }).m1320a(new EtsyJobResponse() {
            final /* synthetic */ LeaveFeedbackFragment f3449a;

            {
                this.f3449a = r1;
            }

            public void m5004a(int i, String str, EtsyResult etsyResult) {
                if (this.f3449a.mInputHelper != null) {
                    this.f3449a.mInputHelper.m5628a();
                    this.f3449a.mInputHelper.m5632a(this.f3449a.mTxtError, this.f3449a.getString(R.review_request_creation_error));
                }
            }
        }).m1324a());
    }

    public void showContent() {
        configureSubmitMenuItem(true);
        if (this.mErrorView != null) {
            this.mErrorView.setVisibility(8);
        }
        if (this.mLoadingView != null) {
            this.mLoadingView.setVisibility(8);
        }
        if (this.mScrollView != null) {
            this.mScrollView.setVisibility(0);
        }
    }

    private void showError() {
        configureSubmitMenuItem(false);
        if (this.mErrorView != null) {
            this.mErrorView.setVisibility(0);
        }
        if (this.mLoadingView != null) {
            this.mLoadingView.setVisibility(8);
        }
        if (this.mScrollView != null) {
            this.mScrollView.setVisibility(8);
        }
    }

    private void showLoading() {
        configureSubmitMenuItem(false);
        if (this.mLoadingView != null) {
            this.mLoadingView.setVisibility(0);
        }
        if (this.mErrorView != null) {
            this.mErrorView.setVisibility(8);
        }
        if (this.mScrollView != null) {
            this.mScrollView.setVisibility(8);
        }
    }

    private void configureSubmitMenuItem(boolean z) {
        this.mShowReviewMenuItem = z;
        getActivity().invalidateOptionsMenu();
    }

    public void onRequestCreationFailed() {
        this.mInputHelper.m5628a();
        this.mInputHelper.m5632a(this.mTxtError, getString(R.review_request_creation_error));
    }
}
