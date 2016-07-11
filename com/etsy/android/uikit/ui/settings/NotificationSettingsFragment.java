package com.etsy.android.uikit.ui.settings;

import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.config.InstallInfo;
import com.etsy.android.lib.core.EtsyJobBuilder;
import com.etsy.android.lib.core.EtsyJobResponse;
import com.etsy.android.lib.core.EtsyResult;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.DeviceNotification;
import com.etsy.android.lib.models.NotificationSettings;
import com.etsy.android.lib.requests.DeviceRequest;
import com.etsy.android.lib.util.SharedPreferencesUtility;
import com.etsy.android.lib.util.af;
import com.etsy.android.lib.util.bl;
import com.etsy.android.uikit.ui.core.TrackingBaseFragment;
import com.etsy.android.uikit.util.TrackingOnCheckedChangeListener;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import java.util.List;

public class NotificationSettingsFragment extends TrackingBaseFragment {
    private static final String TAG;
    protected final int REQUEST_CODE_RINGTONE;
    protected View mCardRingtone;
    protected final OnCheckedChangeListener mCheckedChangedListener;
    private final OnClickListener mClickListener;
    protected View mErrorView;
    protected View mLoadingView;
    private NotificationSettings mNotificationSettings;
    protected LinearLayout mServerDrivenNotificationSettingsSection;
    protected SwitchCompat mSwitchLight;
    protected SwitchCompat mSwitchSound;
    protected SwitchCompat mSwitchVibrate;
    protected TextView mTxtSelectedRingtone;
    protected Uri mUriEtsyNotification;

    /* renamed from: com.etsy.android.uikit.ui.settings.NotificationSettingsFragment.1 */
    class C09771 extends TrackingOnClickListener {
        final /* synthetic */ NotificationSettingsFragment f4083a;

        C09771(NotificationSettingsFragment notificationSettingsFragment) {
            this.f4083a = notificationSettingsFragment;
        }

        public void onViewClick(View view) {
            this.f4083a.requestNotificationSettings();
        }
    }

    /* renamed from: com.etsy.android.uikit.ui.settings.NotificationSettingsFragment.2 */
    class C09782 implements EtsyJobBuilder {
        final /* synthetic */ NotificationSettingsFragment f4084a;

        C09782(NotificationSettingsFragment notificationSettingsFragment) {
            this.f4084a = notificationSettingsFragment;
        }

        public void m5449a() {
            this.f4084a.showLoadingView();
        }
    }

    /* renamed from: com.etsy.android.uikit.ui.settings.NotificationSettingsFragment.3 */
    class C09793 implements EtsyJobResponse<NotificationSettings> {
        final /* synthetic */ NotificationSettingsFragment f4085a;

        C09793(NotificationSettingsFragment notificationSettingsFragment) {
            this.f4085a = notificationSettingsFragment;
        }

        public void m5450a(List<NotificationSettings> list, int i, EtsyResult<NotificationSettings> etsyResult) {
            this.f4085a.mNotificationSettings = (NotificationSettings) list.get(0);
            this.f4085a.fillNotificationSettingsView();
            this.f4085a.showServerSettingList();
        }
    }

    /* renamed from: com.etsy.android.uikit.ui.settings.NotificationSettingsFragment.4 */
    class C09804 implements EtsyJobResponse {
        final /* synthetic */ NotificationSettingsFragment f4086a;

        C09804(NotificationSettingsFragment notificationSettingsFragment) {
            this.f4086a = notificationSettingsFragment;
        }

        public void m5451a(int i, String str, EtsyResult etsyResult) {
            this.f4086a.showErrorView();
            EtsyDebug.m1919e(NotificationSettingsFragment.TAG, "Error requesting notification settings :(");
        }
    }

    /* renamed from: com.etsy.android.uikit.ui.settings.NotificationSettingsFragment.5 */
    class C09815 implements EtsyJobResponse {
        final /* synthetic */ NotificationSettingsFragment f4087a;

        C09815(NotificationSettingsFragment notificationSettingsFragment) {
            this.f4087a = notificationSettingsFragment;
        }

        public void m5452a(EtsyResult etsyResult) {
            this.f4087a.showErrorView();
            EtsyDebug.m1919e(NotificationSettingsFragment.TAG, "Empty response requesting notification settings :|");
        }
    }

    /* renamed from: com.etsy.android.uikit.ui.settings.NotificationSettingsFragment.6 */
    class C09826 extends TrackingOnCheckedChangeListener {
        final /* synthetic */ NotificationSettingsFragment f4088a;

        C09826(NotificationSettingsFragment notificationSettingsFragment) {
            this.f4088a = notificationSettingsFragment;
        }

        public void onViewCheckedChanged(CompoundButton compoundButton, boolean z) {
            if (compoundButton.getId() == R.switch_sound) {
                this.f4088a.switchSoundCheckedChanged(z);
            } else if (compoundButton.getId() == R.switch_vibrate) {
                if (z) {
                    ((Vibrator) this.f4088a.getActivity().getSystemService("vibrator")).vibrate(300);
                }
                SharedPreferencesUtility.m3137c(this.f4088a.getActivity(), "notification_vibrate", z);
            } else if (compoundButton.getId() == R.switch_light) {
                SharedPreferencesUtility.m3137c(this.f4088a.getActivity(), "notification_led", z);
            } else {
                this.f4088a.serverOnCheckedChanged(compoundButton);
            }
        }
    }

    /* renamed from: com.etsy.android.uikit.ui.settings.NotificationSettingsFragment.7 */
    class C09837 implements EtsyJobResponse<BaseModel> {
        final /* synthetic */ NotificationSettingsFragment f4089a;

        C09837(NotificationSettingsFragment notificationSettingsFragment) {
            this.f4089a = notificationSettingsFragment;
        }

        public void m5453a(List<BaseModel> list, int i, EtsyResult<BaseModel> etsyResult) {
            EtsyDebug.m1912c(NotificationSettingsFragment.TAG, "Successfully updated setting");
        }
    }

    /* renamed from: com.etsy.android.uikit.ui.settings.NotificationSettingsFragment.8 */
    class C09848 implements EtsyJobResponse {
        final SwitchCompat f4090a;
        final /* synthetic */ CompoundButton f4091b;
        final /* synthetic */ NotificationSettingsFragment f4092c;

        C09848(NotificationSettingsFragment notificationSettingsFragment, CompoundButton compoundButton) {
            this.f4092c = notificationSettingsFragment;
            this.f4091b = compoundButton;
            this.f4090a = (SwitchCompat) this.f4091b;
        }

        public void m5454a(int i, String str, EtsyResult etsyResult) {
            View view = this.f4092c.getView();
            if (this.f4090a != null && view != null) {
                this.f4090a.setOnCheckedChangeListener(null);
                this.f4090a.setChecked(!this.f4090a.isChecked());
                this.f4090a.setOnCheckedChangeListener(this.f4092c.mCheckedChangedListener);
                bl.m3367b(this.f4092c.getString(R.update_notification_setting_error_message), view);
            }
        }
    }

    /* renamed from: com.etsy.android.uikit.ui.settings.NotificationSettingsFragment.9 */
    class C09859 extends TrackingOnClickListener {
        final /* synthetic */ NotificationSettingsFragment f4093a;

        C09859(NotificationSettingsFragment notificationSettingsFragment) {
            this.f4093a = notificationSettingsFragment;
        }

        public void onViewClick(View view) {
            if (view.getId() == R.card_ringtone) {
                Intent intent = new Intent("android.intent.action.RINGTONE_PICKER");
                intent.putExtra("android.intent.extra.ringtone.SHOW_DEFAULT", true);
                intent.putExtra("android.intent.extra.ringtone.DEFAULT_URI", this.f4093a.mUriEtsyNotification);
                intent.putExtra("android.intent.extra.ringtone.SHOW_SILENT", false);
                String a = SharedPreferencesUtility.m3117a(this.f4093a.getActivity(), "notification_ringtone", null);
                intent.putExtra("android.intent.extra.ringtone.EXISTING_URI", a == null ? this.f4093a.mUriEtsyNotification : Uri.parse(a));
                this.f4093a.startActivityForResult(intent, 1);
            }
        }
    }

    public NotificationSettingsFragment() {
        this.REQUEST_CODE_RINGTONE = 1;
        this.mCheckedChangedListener = new C09826(this);
        this.mClickListener = new C09859(this);
    }

    static {
        TAG = EtsyDebug.m1891a(NotificationSettingsFragment.class);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.fragment_notification_settings, null);
        this.mServerDrivenNotificationSettingsSection = (LinearLayout) inflate.findViewById(R.server_driven_settings_section);
        this.mLoadingView = inflate.findViewById(R.loading_notification_settings);
        this.mErrorView = inflate.findViewById(R.error_view);
        this.mErrorView.findViewById(R.btn_retry_endless).setOnClickListener(new C09771(this));
        this.mUriEtsyNotification = af.m3196a(getActivity(), R.notification);
        this.mCardRingtone = inflate.findViewById(R.card_ringtone);
        this.mCardRingtone.setOnClickListener(this.mClickListener);
        this.mTxtSelectedRingtone = (TextView) inflate.findViewById(R.txt_selected_ringtone);
        this.mSwitchVibrate = (SwitchCompat) inflate.findViewById(R.switch_vibrate);
        this.mSwitchLight = (SwitchCompat) inflate.findViewById(R.switch_light);
        this.mSwitchSound = (SwitchCompat) inflate.findViewById(R.switch_sound);
        return inflate;
    }

    public void onResume() {
        super.onResume();
        setRingtoneText(SharedPreferencesUtility.m3117a(getActivity(), "notification_ringtone", this.mUriEtsyNotification.toString()));
        this.mSwitchVibrate.setChecked(SharedPreferencesUtility.m3133b(getActivity(), "notification_vibrate", true));
        this.mSwitchLight.setChecked(SharedPreferencesUtility.m3133b(getActivity(), "notification_led", true));
        this.mSwitchSound.setChecked(SharedPreferencesUtility.m3133b(getActivity(), "notification_sound", true));
        this.mCardRingtone.setVisibility(this.mSwitchSound.isChecked() ? 0 : 8);
        this.mSwitchVibrate.setOnCheckedChangeListener(this.mCheckedChangedListener);
        this.mSwitchLight.setOnCheckedChangeListener(this.mCheckedChangedListener);
        this.mSwitchSound.setOnCheckedChangeListener(this.mCheckedChangedListener);
    }

    public void onPause() {
        super.onPause();
        this.mSwitchVibrate.setOnCheckedChangeListener(null);
        this.mSwitchLight.setOnCheckedChangeListener(null);
        this.mSwitchSound.setOnCheckedChangeListener(null);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (this.mNotificationSettings == null) {
            requestNotificationSettings();
        } else {
            fillNotificationSettingsView();
        }
    }

    public void requestNotificationSettings() {
        EtsyJobBuilder a = EtsyJobBuilder.m1307a(DeviceRequest.getNotificationSettings(InstallInfo.m919a().m925b()));
        a.m1318a(new C09782(this));
        a.m1321a(new C09793(this));
        a.m1320a(new C09804(this));
        a.m1319a(new C09815(this));
        getRequestQueue().m1697a((Object) this, a.m1324a());
    }

    protected void switchSoundCheckedChanged(boolean z) {
        SharedPreferencesUtility.m3137c(getActivity(), "notification_sound", z);
        this.mCardRingtone.setVisibility(z ? 0 : 8);
    }

    protected void serverOnCheckedChanged(CompoundButton compoundButton) {
        DeviceNotification deviceNotification = (DeviceNotification) compoundButton.getTag(R.tag_device_notification);
        deviceNotification.setPhoneEnabled(compoundButton.isChecked());
        EtsyJobBuilder a = EtsyJobBuilder.m1307a(DeviceRequest.updateNotificationSetting(InstallInfo.m919a().m925b(), deviceNotification));
        a.m1321a(new C09837(this));
        a.m1320a(new C09848(this, compoundButton));
        getRequestQueue().m1697a((Object) this, a.m1324a());
    }

    protected void fillNotificationSettingsView() {
        this.mServerDrivenNotificationSettingsSection.removeAllViews();
        if (this.mNotificationSettings != null) {
            for (DeviceNotification deviceNotification : this.mNotificationSettings.getDeviceNotifications()) {
                View inflate = getActivity().getLayoutInflater().inflate(R.settings_switch, null);
                ((TextView) inflate.findViewById(R.text)).setText(deviceNotification.getText());
                SwitchCompat switchCompat = (SwitchCompat) inflate.findViewById(R.zwitch);
                switchCompat.setChecked(deviceNotification.isPhoneEnabled());
                switchCompat.setOnCheckedChangeListener(this.mCheckedChangedListener);
                switchCompat.setTag(R.tag_device_notification, deviceNotification);
                this.mServerDrivenNotificationSettingsSection.addView(inflate);
            }
        }
    }

    private void showLoadingView() {
        this.mLoadingView.setVisibility(0);
        this.mErrorView.setVisibility(8);
        this.mServerDrivenNotificationSettingsSection.setVisibility(8);
    }

    private void showErrorView() {
        this.mLoadingView.setVisibility(8);
        this.mErrorView.setVisibility(0);
        this.mServerDrivenNotificationSettingsSection.setVisibility(8);
    }

    private void showServerSettingList() {
        this.mLoadingView.setVisibility(8);
        this.mErrorView.setVisibility(8);
        this.mServerDrivenNotificationSettingsSection.setVisibility(0);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 1 && i2 == -1 && intent != null) {
            Parcelable parcelableExtra = intent.getParcelableExtra("android.intent.extra.ringtone.PICKED_URI");
            if (parcelableExtra != null) {
                String obj = parcelableExtra.toString();
                EtsyDebug.m1912c(TAG, obj);
                if (getActivity() != null) {
                    SharedPreferencesUtility.m3130b(getActivity(), "notification_ringtone", obj);
                }
                setRingtoneText(obj);
            }
        }
    }

    private void setRingtoneText(String str) {
        if (str.startsWith("android.resource://com.etsy.android")) {
            this.mTxtSelectedRingtone.setText("Default Ringtone");
            return;
        }
        Ringtone ringtone = RingtoneManager.getRingtone(getActivity(), Uri.parse(str));
        if (ringtone != null) {
            this.mTxtSelectedRingtone.setText(ringtone.getTitle(getActivity()));
        }
    }

    @NonNull
    public String getTrackingName() {
        return "push_notifications";
    }
}
