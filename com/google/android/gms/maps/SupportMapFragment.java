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
import com.google.android.gms.maps.internal.IGoogleMapDelegate;
import com.google.android.gms.maps.internal.IMapFragmentDelegate;
import com.google.android.gms.maps.model.RuntimeRemoteException;

public class SupportMapFragment extends Fragment {
    private c zzaZt;
    private final y zzbaf;

    public SupportMapFragment() {
        this.zzbaf = new y(this);
    }

    public static SupportMapFragment newInstance() {
        return new SupportMapFragment();
    }

    public static SupportMapFragment newInstance(GoogleMapOptions googleMapOptions) {
        SupportMapFragment supportMapFragment = new SupportMapFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("MapOptions", googleMapOptions);
        supportMapFragment.setArguments(bundle);
        return supportMapFragment;
    }

    @Deprecated
    public final c getMap() {
        IMapFragmentDelegate zzDE = zzDE();
        if (zzDE == null) {
            return null;
        }
        try {
            IGoogleMapDelegate map = zzDE.getMap();
            if (map == null) {
                return null;
            }
            if (this.zzaZt == null || this.zzaZt.a().asBinder() != map.asBinder()) {
                this.zzaZt = new c(map);
            }
            return this.zzaZt;
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void getMapAsync(C0739p c0739p) {
        zzaa.zzdc("getMapAsync must be called on the main thread.");
        this.zzbaf.a(c0739p);
    }

    public void onActivityCreated(Bundle bundle) {
        if (bundle != null) {
            bundle.setClassLoader(SupportMapFragment.class.getClassLoader());
        }
        super.onActivityCreated(bundle);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        y.a(this.zzbaf, activity);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.zzbaf.a(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View a = this.zzbaf.a(layoutInflater, viewGroup, bundle);
        a.setClickable(true);
        return a;
    }

    public void onDestroy() {
        this.zzbaf.g();
        super.onDestroy();
    }

    public void onDestroyView() {
        this.zzbaf.f();
        super.onDestroyView();
    }

    public final void onEnterAmbient(Bundle bundle) {
        zzaa.zzdc("onEnterAmbient must be called on the main thread.");
        this.zzbaf.c(bundle);
    }

    public final void onExitAmbient() {
        zzaa.zzdc("onExitAmbient must be called on the main thread.");
        this.zzbaf.j();
    }

    public void onInflate(Activity activity, AttributeSet attributeSet, Bundle bundle) {
        super.onInflate(activity, attributeSet, bundle);
        y.a(this.zzbaf, activity);
        GoogleMapOptions createFromAttributes = GoogleMapOptions.createFromAttributes(activity, attributeSet);
        Bundle bundle2 = new Bundle();
        bundle2.putParcelable("MapOptions", createFromAttributes);
        this.zzbaf.a(activity, bundle2, bundle);
    }

    public void onLowMemory() {
        this.zzbaf.h();
        super.onLowMemory();
    }

    public void onPause() {
        this.zzbaf.d();
        super.onPause();
    }

    public void onResume() {
        super.onResume();
        this.zzbaf.c();
    }

    public void onSaveInstanceState(Bundle bundle) {
        if (bundle != null) {
            bundle.setClassLoader(SupportMapFragment.class.getClassLoader());
        }
        super.onSaveInstanceState(bundle);
        this.zzbaf.b(bundle);
    }

    public void setArguments(Bundle bundle) {
        super.setArguments(bundle);
    }

    protected IMapFragmentDelegate zzDE() {
        this.zzbaf.i();
        return this.zzbaf.a() == null ? null : ((x) this.zzbaf.a()).h();
    }
}
