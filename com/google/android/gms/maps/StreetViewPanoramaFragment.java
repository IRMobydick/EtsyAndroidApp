package com.google.android.gms.maps;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate;
import com.google.android.gms.maps.internal.IStreetViewPanoramaFragmentDelegate;
import com.google.android.gms.maps.model.RuntimeRemoteException;

@TargetApi(11)
public class StreetViewPanoramaFragment extends Fragment {
    private final u zzaZN;
    private s zzaZO;

    public StreetViewPanoramaFragment() {
        this.zzaZN = new u(this);
    }

    public static StreetViewPanoramaFragment newInstance() {
        return new StreetViewPanoramaFragment();
    }

    public static StreetViewPanoramaFragment newInstance(StreetViewPanoramaOptions streetViewPanoramaOptions) {
        StreetViewPanoramaFragment streetViewPanoramaFragment = new StreetViewPanoramaFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("StreetViewPanoramaOptions", streetViewPanoramaOptions);
        streetViewPanoramaFragment.setArguments(bundle);
        return streetViewPanoramaFragment;
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
        this.zzaZN.a(qVar);
    }

    public void onActivityCreated(Bundle bundle) {
        if (bundle != null) {
            bundle.setClassLoader(StreetViewPanoramaFragment.class.getClassLoader());
        }
        super.onActivityCreated(bundle);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        u.a(this.zzaZN, activity);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.zzaZN.a(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return this.zzaZN.a(layoutInflater, viewGroup, bundle);
    }

    public void onDestroy() {
        this.zzaZN.g();
        super.onDestroy();
    }

    public void onDestroyView() {
        this.zzaZN.f();
        super.onDestroyView();
    }

    @SuppressLint({"NewApi"})
    public void onInflate(Activity activity, AttributeSet attributeSet, Bundle bundle) {
        super.onInflate(activity, attributeSet, bundle);
        u.a(this.zzaZN, activity);
        this.zzaZN.a(activity, new Bundle(), bundle);
    }

    public void onLowMemory() {
        this.zzaZN.h();
        super.onLowMemory();
    }

    public void onPause() {
        this.zzaZN.d();
        super.onPause();
    }

    public void onResume() {
        super.onResume();
        this.zzaZN.c();
    }

    public void onSaveInstanceState(Bundle bundle) {
        if (bundle != null) {
            bundle.setClassLoader(StreetViewPanoramaFragment.class.getClassLoader());
        }
        super.onSaveInstanceState(bundle);
        this.zzaZN.b(bundle);
    }

    public void setArguments(Bundle bundle) {
        super.setArguments(bundle);
    }

    protected IStreetViewPanoramaFragmentDelegate zzDJ() {
        this.zzaZN.i();
        return this.zzaZN.a() == null ? null : ((t) this.zzaZN.a()).h();
    }
}
