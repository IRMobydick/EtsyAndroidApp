package com.etsy.android.uikit.ui.core;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;
import com.appboy.models.InAppMessageBase;
import com.etsy.android.lib.R;
import com.etsy.android.uikit.nav.NavBase;
import org.apache.commons.lang3.StringUtils;

public class VideoFragment extends TrackingBaseFragment {
    View mActivityIndicator;
    MediaController mMediaController;
    String mVideoUrl;
    VideoView mVideoView;

    /* renamed from: com.etsy.android.uikit.ui.core.VideoFragment.1 */
    class C09711 implements OnErrorListener {
        final /* synthetic */ VideoFragment f4070a;

        C09711(VideoFragment videoFragment) {
            this.f4070a = videoFragment;
        }

        public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
            this.f4070a.onVideoError();
            return false;
        }
    }

    /* renamed from: com.etsy.android.uikit.ui.core.VideoFragment.2 */
    class C09722 implements OnPreparedListener {
        final /* synthetic */ VideoFragment f4071a;

        C09722(VideoFragment videoFragment) {
            this.f4071a = videoFragment;
        }

        public void onPrepared(MediaPlayer mediaPlayer) {
            this.f4071a.mActivityIndicator.setVisibility(8);
            this.f4071a.mMediaController.show(InAppMessageBase.INAPP_MESSAGE_DURATION_DEFAULT_MILLIS);
            this.f4071a.mVideoView.start();
            this.f4071a.onVideoPrepared();
        }
    }

    /* renamed from: com.etsy.android.uikit.ui.core.VideoFragment.3 */
    class C09733 implements OnCompletionListener {
        final /* synthetic */ VideoFragment f4072a;

        C09733(VideoFragment videoFragment) {
            this.f4072a = videoFragment;
        }

        public void onCompletion(MediaPlayer mediaPlayer) {
            this.f4072a.onVideoCompleted();
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        this.mMediaController = new MediaController(getActivity());
        if (arguments != null) {
            this.mVideoUrl = arguments.getString("video_url");
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.fragment_listing_video, viewGroup, false);
        this.mVideoView = (VideoView) inflate.findViewById(R.listing_video_player);
        this.mActivityIndicator = inflate.findViewById(R.activity_indicator);
        this.mVideoView.setMediaController(this.mMediaController);
        return inflate;
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (this.mVideoUrl == null || this.mVideoUrl.equals(StringUtils.EMPTY)) {
            Toast.makeText(getActivity(), R.no_video_found, 0).show();
            NavBase.m4675b(getActivity()).m4679f();
            return;
        }
        this.mVideoView.setVideoURI(Uri.parse(this.mVideoUrl));
        this.mVideoView.setOnErrorListener(new C09711(this));
        this.mVideoView.setOnPreparedListener(new C09722(this));
        this.mVideoView.setOnCompletionListener(new C09733(this));
    }

    protected void onVideoPrepared() {
    }

    protected void onVideoCompleted() {
        NavBase.m4675b(getActivity()).m4679f();
    }

    protected void onVideoError() {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(Uri.parse(this.mVideoUrl), "video/mp4");
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(getActivity(), R.no_video_found, 0).show();
            NavBase.m4675b(getActivity()).m4679f();
        }
    }
}
