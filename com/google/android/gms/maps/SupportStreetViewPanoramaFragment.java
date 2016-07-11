package com.google.android.gms.maps;

import android.app.Activity;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate;
import com.google.android.gms.maps.internal.IStreetViewPanoramaFragmentDelegate;
import com.google.android.gms.maps.model.RuntimeRemoteException;

public class SupportStreetViewPanoramaFragment extends Fragment {
    private s zzaZO;
    private final aa zzbah;

    public SupportStreetViewPanoramaFragment() {
        this.zzbah = new aa(this);
    }

    public static SupportStreetViewPanoramaFragment newInstance() {
        return new SupportStreetViewPanoramaFragment();
    }

    public static SupportStreetViewPanoramaFragment newInstance(StreetViewPanoramaOptions streetViewPanoramaOptions) {
        SupportStreetViewPanoramaFragment supportStreetViewPanoramaFragment = new SupportStreetViewPanoramaFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("StreetViewPanoramaOptions", streetViewPanoramaOptions);
        supportStreetViewPanoramaFragment.setArguments(bundle);
        return supportStreetViewPanoramaFragment;
    }

    @Deprecated
    public final s getStreetViewPanorama() {
        IStreetViewPanoramaFragmentDelegate zzDJ = zzDJ();
        if (zzDJ == null) {
            return null;
        }
        try {
            IStreetViewPanoramaDelegate streetViewPanorama = zzDJ.getStreetViewPanorama();
            if (streetViewPanorama == null) {
                return null;
            }
            if (this.zzaZO == null || this.zzaZO.a().asBinder() != streetViewPanorama.asBinder()) {
                this.zzaZO = new s(streetViewPanorama);
            }
            return this.zzaZO;
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void getStreetViewPanoramaAsync(q qVar) {
        zzaa.zzdc("getStreetViewPanoramaAsync() must be called on the main thread");
        this.zzbah.a(qVar);
    }

    public void onActivityCreated(Bundle bundle) {
        if (bundle != null) {
            bundle.setClassLoader(SupportStreetViewPanoramaFragment.class.getClassLoader());
        }
        super.onActivityCreated(bundle);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        aa.a(this.zzbah, activity);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.zzbah.a(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return this.zzbah.a(layoutInflater, viewGroup, bundle);
    }

    public void onDestroy() {
        this.zzbah.g();
        super.onDestroy();
    }

    public void onDestroyView() {
        this.zzbah.f();
        super.onDestroyView();
    }

    public void onInflate(Activity activity, AttributeSet attributeSet, Bundle bundle) {
        super.onInflate(activity, attributeSet, bundle);
        aa.a(this.zzbah, activity);
        this.zzbah.a(activity, new Bundle(), bundle);
    }

    public void onLowMemory() {
        this.zzbah.h();
        super.onLowMemory();
    }

    public void onPause() {
        this.zzbah.d();
        super.onPause();
    }

    public void onResume() {
        super.onResume();
        this.zzbah.c();
    }

    public void onSaveInstanceState(Bundle bundle) {
        if (bundle != null) {
            bundle.setClassLoader(SupportStreetViewPanoramaFragment.class.getClassLoader());
        }
        super.onSaveInstanceState(bundle);
        this.zzbah.b(bundle);
    }

    public void setArguments(Bundle bundle) {
        super.setArguments(bundle);
    }

    protected IStreetViewPanoramaFragmentDelegate zzDJ() {
        this.zzbah.i();
        return this.zzbah.a() == null ? null : ((z) this.zzbah.a()).h();
    }
}
