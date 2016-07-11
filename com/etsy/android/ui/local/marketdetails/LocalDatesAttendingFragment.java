package com.etsy.android.ui.local.marketdetails;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.models.Attendee;
import com.etsy.android.lib.models.LocalMarket;
import com.etsy.android.lib.models.LocalMarketAttendeeSchedule.Day;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.util.bh;
import com.etsy.android.lib.util.p013b.LocalMarketFormatter;
import com.etsy.android.ui.EtsyFragment;
import com.etsy.android.ui.dialog.EtsyDialogFragment;
import com.etsy.android.ui.local.LocalMarketIntentLauncher;
import com.etsy.android.ui.nav.Nav;
import com.etsy.android.uikit.ui.dialog.IDialogFragment.WindowMode;
import com.etsy.android.uikit.util.EtsyLinkify;
import com.etsy.android.uikit.util.GraphikUtil;
import com.etsy.android.uikit.util.TabletSupportUtil;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class LocalDatesAttendingFragment extends EtsyFragment {
    private Attendee mAttendee;
    private ImageView mAvatar;
    private TextView mComment;
    private SimpleDateFormat mDateFormat;
    private SimpleDateFormat mDayOfWeekFormat;
    private LinearLayout mHoursLayout;
    private LocalMarket mMarket;

    /* renamed from: com.etsy.android.ui.local.marketdetails.LocalDatesAttendingFragment.1 */
    class C07591 extends TrackingOnClickListener {
        final /* synthetic */ LocalDatesAttendingFragment f3087a;

        C07591(LocalDatesAttendingFragment localDatesAttendingFragment) {
            this.f3087a = localDatesAttendingFragment;
        }

        public void onViewClick(View view) {
            Nav.m4682a(this.f3087a.getActivity()).m4683a().m4501b(this.f3087a.mAttendee.getShopId());
        }
    }

    /* renamed from: com.etsy.android.ui.local.marketdetails.LocalDatesAttendingFragment.2 */
    class C07602 extends TrackingOnClickListener {
        final /* synthetic */ LocalDatesAttendingFragment f3088a;

        C07602(LocalDatesAttendingFragment localDatesAttendingFragment) {
            this.f3088a = localDatesAttendingFragment;
        }

        public void onViewClick(View view) {
            LocalMarketIntentLauncher.m4396a(this.f3088a.getActivity(), this.f3088a.mAttendee, this.f3088a.mMarket);
        }
    }

    /* renamed from: com.etsy.android.ui.local.marketdetails.LocalDatesAttendingFragment.3 */
    class C07613 extends TrackingOnClickListener {
        final /* synthetic */ LocalDatesAttendingFragment f3089a;

        C07613(LocalDatesAttendingFragment localDatesAttendingFragment) {
            this.f3089a = localDatesAttendingFragment;
        }

        public void onViewClick(View view) {
            Nav.m4682a(this.f3089a.getActivity()).m4679f();
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mAttendee = (Attendee) getArguments().getSerializable("attendee");
        this.mMarket = (LocalMarket) getArguments().getSerializable(ResponseConstants.LOCAL_MARKET);
        this.mDateFormat = new SimpleDateFormat("MMM d");
        this.mDayOfWeekFormat = new SimpleDateFormat("EEEE");
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(2130903217, viewGroup, false);
        ((TextView) inflate.findViewById(R.shop_name)).setText(this.mAttendee.getShopName());
        inflate.findViewById(2131755875).setOnClickListener(new C07591(this));
        inflate.findViewById(2131755728).setOnClickListener(new C07602(this));
        View findViewById = inflate.findViewById(2131755726);
        findViewById.setVisibility(new TabletSupportUtil(getActivity()).m5621a() ? 8 : 0);
        findViewById.setOnClickListener(new C07613(this));
        this.mAvatar = (ImageView) inflate.findViewById(R.avatar);
        this.mHoursLayout = (LinearLayout) inflate.findViewById(2131755727);
        this.mComment = (TextView) inflate.findViewById(2131755876);
        return inflate;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (getParentFragment() instanceof EtsyDialogFragment) {
            setUpDialog((EtsyDialogFragment) getParentFragment());
        }
        int dimensionPixelOffset = getResources().getDimensionPixelOffset(R.local_avatar_size);
        getImageBatch().m1570a(this.mAttendee.getAvatarUrl(), this.mAvatar, dimensionPixelOffset, dimensionPixelOffset);
        fillComment();
        fillDates();
    }

    private void setUpDialog(EtsyDialogFragment etsyDialogFragment) {
        etsyDialogFragment.hideHeader();
        etsyDialogFragment.setWindowMode(WindowMode.MEDIUM);
        etsyDialogFragment.setWindowBackgroundDim(0.6f);
    }

    private void fillComment() {
        if (bh.m3343b(this.mAttendee.getComment())) {
            this.mComment.setVisibility(0);
            this.mComment.setText(String.format("\"%s\"", new Object[]{this.mAttendee.getComment().trim()}));
            EtsyLinkify.m5483a(getActivity(), this.mComment, false);
            return;
        }
        this.mComment.setVisibility(8);
    }

    private void fillDates() {
        if (this.mAttendee.getSchedule() == null || this.mAttendee.getSchedule().getDays().size() == 0) {
            this.mHoursLayout.setVisibility(8);
            return;
        }
        LayoutInflater from = LayoutInflater.from(getActivity());
        String[] stringArray = getResources().getStringArray(R.weekdays_pluralized);
        boolean z = this.mMarket.isHappeningNow() || this.mMarket.isBetweenStartAndEnd();
        Calendar instance = Calendar.getInstance();
        int i = instance.get(7);
        for (Day day : this.mAttendee.getSchedule().getDays()) {
            View inflate = from.inflate(2130903330, this.mHoursLayout, false);
            TextView textView = (TextView) inflate.findViewById(2131756025);
            TextView textView2 = (TextView) inflate.findViewById(R.date);
            TextView textView3 = (TextView) inflate.findViewById(2131755881);
            textView3.setText(LocalMarketFormatter.m3298a(getActivity(), day.getFrom(), day.getTo()));
            if (this.mAttendee.getSchedule().isDaysOfWeekType()) {
                int weekIndex = day.getDay().getWeekIndex();
                textView.setText(stringArray[weekIndex]);
                if (z && i == weekIndex) {
                    boldFields(textView, textView2, textView3);
                }
            } else {
                textView2.setText(this.mDateFormat.format(day.getSpecificDate()));
                if (z && isSameDay(instance, day.getSpecificDate())) {
                    textView.setText(R.today);
                    boldFields(textView, textView2, textView3);
                } else {
                    textView.setText(this.mDayOfWeekFormat.format(day.getSpecificDate()));
                }
            }
            this.mHoursLayout.addView(inflate);
        }
    }

    private boolean isSameDay(Calendar calendar, Date date) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        if (calendar.get(6) == instance.get(6) && calendar.get(1) == instance.get(1)) {
            return true;
        }
        return false;
    }

    private void boldFields(TextView textView, TextView textView2, TextView textView3) {
        if (!GraphikUtil.m5547a(textView, R.typeface_bold)) {
            textView.setTypeface(null, 1);
        }
        if (!GraphikUtil.m5547a(textView2, R.typeface_bold)) {
            textView2.setTypeface(null, 1);
        }
        if (!GraphikUtil.m5547a(textView3, R.typeface_bold)) {
            textView3.setTypeface(null, 1);
        }
    }
}
