package com.etsy.android.ui.convos;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View.OnClickListener;
import com.etsy.android.lib.R;
import com.etsy.android.lib.convos.BaseSplitView;
import com.etsy.android.lib.convos.ConvoBaseHelper;
import com.etsy.android.lib.convos.ConvoSelectionCallbacks;
import com.etsy.android.lib.convos.ConvoStateManager;
import com.etsy.android.lib.convos.contentprovider.ConvoDatabaseUtil;
import com.etsy.android.lib.core.EtsyJobBuilder;
import com.etsy.android.lib.core.EtsyJobResponse;
import com.etsy.android.lib.core.EtsyRequestJob;
import com.etsy.android.lib.core.EtsyResult;
import com.etsy.android.lib.core.aj;
import com.etsy.android.lib.core.ap;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.messaging.EtsyAction;
import com.etsy.android.lib.models.Conversation;
import com.etsy.android.lib.requests.ConversationRequest;
import com.etsy.android.lib.util.NetworkUtils;
import com.etsy.android.ui.BOENavDrawerActivity;
import com.etsy.android.ui.nav.EtsyFragmentNavigator;
import com.etsy.android.ui.nav.Nav;
import com.etsy.android.uikit.nav.FragmentNavigator.AnimationMode;
import java.util.List;

public class ConvoBaseActivity extends BOENavDrawerActivity implements ConvoSelectionCallbacks {
    private static final String TAG;
    private BaseSplitView mBaseView;
    private boolean mIsFirstTime;
    private long mLastConvoId;
    private Conversation mNotifiedConvo;
    private String mNotifiedConvoId;

    /* renamed from: com.etsy.android.ui.convos.ConvoBaseActivity.1 */
    class C06221 extends AsyncTask<Void, Void, Conversation> {
        final /* synthetic */ ConvoBaseActivity f2640a;

        C06221(ConvoBaseActivity convoBaseActivity) {
            this.f2640a = convoBaseActivity;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m3908a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            m3909a((Conversation) obj);
        }

        protected Conversation m3908a(Void... voidArr) {
            return ConvoDatabaseUtil.m976b(this.f2640a, this.f2640a.mLastConvoId);
        }

        protected void m3909a(Conversation conversation) {
            Nav.m4682a(this.f2640a).m4683a().m4458a(conversation, false);
        }
    }

    /* renamed from: com.etsy.android.ui.convos.ConvoBaseActivity.2 */
    class C06232 implements EtsyJobResponse {
        final /* synthetic */ ConvoBaseActivity f2641a;

        C06232(ConvoBaseActivity convoBaseActivity) {
            this.f2641a = convoBaseActivity;
        }

        public void m3910a(int i, String str, EtsyResult etsyResult) {
            this.f2641a.showConvo(null);
        }
    }

    /* renamed from: com.etsy.android.ui.convos.ConvoBaseActivity.3 */
    class C06243 implements EtsyJobResponse {
        final /* synthetic */ ConvoBaseActivity f2642a;

        C06243(ConvoBaseActivity convoBaseActivity) {
            this.f2642a = convoBaseActivity;
        }

        public void m3911a(EtsyResult etsyResult) {
            this.f2642a.showConvo(null);
        }
    }

    /* renamed from: com.etsy.android.ui.convos.ConvoBaseActivity.4 */
    class C06254 implements EtsyJobResponse<Conversation> {
        final /* synthetic */ ConvoBaseActivity f2643a;

        C06254(ConvoBaseActivity convoBaseActivity) {
            this.f2643a = convoBaseActivity;
        }

        public void m3912a(List<Conversation> list, int i, EtsyResult<Conversation> etsyResult) {
            this.f2643a.showConvo((Conversation) list.get(0));
        }
    }

    static {
        TAG = EtsyDebug.m1891a(ConvoBaseActivity.class);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2130903071);
        setTitle((int) R.conversations);
        this.mBaseView = (BaseSplitView) findViewById(2131755316);
        this.mIsFirstTime = bundle == null;
        if (!(getIntent() == null || getIntent().getExtras() == null)) {
            Bundle extras = getIntent().getExtras();
            this.mNotifiedConvo = (Conversation) extras.getSerializable("conversation");
            this.mNotifiedConvoId = extras.getString("convo_id");
        }
        this.mLastConvoId = this.mNotifiedConvo == null ? ConvoStateManager.m1003a((Context) this, bundle) : this.mNotifiedConvo.getConversationId();
        requireSignIn(EtsyAction.VIEW_CONVO);
    }

    protected void onUserSignedInForAction(EtsyAction etsyAction) {
        super.onUserSignedInForAction(etsyAction);
        if (this.mNotifiedConvo != null || TextUtils.isEmpty(this.mNotifiedConvoId)) {
            showConvo(this.mNotifiedConvo);
        } else {
            getConvo(this.mNotifiedConvoId);
        }
    }

    public void showConvo(Conversation conversation) {
        EtsyFragmentNavigator b;
        if (this.mIsFirstTime) {
            b = Nav.m4682a((FragmentActivity) this).m4684b();
        } else {
            b = Nav.m4682a((FragmentActivity) this).m4685c();
        }
        this.mIsFirstTime = false;
        b.m4622a((int) R.fragment_container).m4626a("convo_fragment").m4645e();
        if (this.mBaseView.isTwoPane()) {
            Bundle bundle = new Bundle();
            bundle.putBoolean("should_activate", true);
            bundle.putLong("last_convo", this.mLastConvoId);
            if (conversation != null) {
                bundle.putBoolean("reload_only", true);
            }
            b.m4623a(bundle).m4666r();
            if (conversation != null) {
                onItemSelected(conversation, true);
                return;
            }
            return;
        }
        ConvoBaseHelper.m946a(getSupportFragmentManager());
        if (conversation != null) {
            b.m4666r();
            Nav.m4682a((FragmentActivity) this).m4683a().m4458a(conversation, true);
        } else if (this.mLastConvoId > 0) {
            ap.m1142a(new C06221(this), new Void[0]);
        } else {
            b.m4666r();
        }
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        if (!this.mBaseView.isTwoPane()) {
            menu.removeGroup(R.menu_group_convo_thread);
            menu.removeItem(R.menu_send_reply);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    public void onItemSelected(Conversation conversation, boolean z) {
        if (conversation == null) {
            return;
        }
        if (this.mBaseView.isTwoPane()) {
            Nav.m4682a((FragmentActivity) this).m4685c().m4625a(AnimationMode.FADE).m4645e().m4622a((int) R.fragment_detail_container).m4618a(conversation, z);
        } else {
            Nav.m4682a((FragmentActivity) this).m4683a().m4458a(conversation, true);
        }
    }

    public boolean isShowingConvo() {
        return ConvoBaseHelper.m947b(getSupportFragmentManager());
    }

    public boolean isTwoPane() {
        return this.mBaseView.isTwoPane();
    }

    public void onShowEmpty() {
        ConvoStateManager.m1005b(this);
        this.mBaseView.showEmptyView(getSupportFragmentManager());
    }

    public void onShowConvo() {
        this.mBaseView.showBase();
    }

    public void onShowErrorView(OnClickListener onClickListener) {
        this.mBaseView.setErrorViewRetryListener(onClickListener);
        this.mBaseView.showErrorView();
    }

    public void getConvo(String str) {
        if (NetworkUtils.m3107a().m3114b()) {
            aj.m1101a().m1123i().m1697a((Object) this, createGetConvoJob(str));
        }
    }

    private EtsyRequestJob createGetConvoJob(String str) {
        return EtsyJobBuilder.m1307a(ConversationRequest.getConversation(str)).m1321a(new C06254(this)).m1319a(new C06243(this)).m1320a(new C06232(this)).m1324a();
    }
}
