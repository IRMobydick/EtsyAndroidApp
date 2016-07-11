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
import com.google.android.gms.maps.internal.IGoogleMapDelegate;
import com.google.android.gms.maps.internal.IMapFragmentDelegate;
import com.google.android.gms.maps.model.RuntimeRemoteException;

@TargetApi(11)
public class MapFragment extends Fragment {
    private final l zzaZs;
    private c zzaZt;

    public MapFragment() {
        this.zzaZs = new l(this);
    }

    public static MapFragment newInstance() {
        return new MapFragment();
    }

    public static MapFragment newInstance(GoogleMapOptions googleMapOptions) {
        MapFragment mapFragment = new MapFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("MapOptions", googleMapOptions);
        mapFragment.setArguments(bundle);
        return mapFragment;
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
        this.zzaZs.a(c0739p);
    }

    public void onActivityCreated(Bundle bundle) {
        if (bundle != null) {
            bundle.setClassLoader(MapFragment.class.getClassLoader());
        }
        super.onActivityCreated(bundle);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        l.a(this.zzaZs, activity);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.zzaZs.a(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View a = this.zzaZs.a(layoutInflater, viewGroup, bundle);
        a.setClickable(true);
        return a;
    }

    public void onDestroy() {
        this.zzaZs.g();
        super.onDestroy();
    }

    public void onDestroyView() {
        this.zzaZs.f();
        super.onDestroyView();
    }

    public final void onEnterAmbient(Bundle bundle) {
        zzaa.zzdc("onEnterAmbient must be called on the main thread.");
        this.zzaZs.c(bundle);
    }

    public final void onExitAmbient() {
        zzaa.zzdc("onExitAmbient must be called on the main thread.");
        this.zzaZs.j();
    }

    @SuppressLint({"NewApi"})
    public void onInflate(Activity activity, AttributeSet attributeSet, Bundle bundle) {
        super.onInflate(activity, attributeSet, bundle);
        l.a(this.zzaZs, activity);
        GoogleMapOptions createFromAttributes = GoogleMapOptions.createFromAttributes(activity, attributeSet);
        Bundle bundle2 = new Bundle();
        bundle2.putParcelable("MapOptions", createFromAttributes);
        this.zzaZs.a(activity, bundle2, bundle);
    }

    public void onLowMemory() {
        this.zzaZs.h();
        super.onLowMemory();
    }

    public void onPause() {
        this.zzaZs.d();
        super.onPause();
    }

    public void onResume() {
        super.onResume();
        this.zzaZs.c();
    }

    public void onSaveInstanceState(Bundle bundle) {
        if (bundle != null) {
            bundle.setClassLoader(MapFragment.class.getClassLoader());
        }
        super.onSaveInstanceState(bundle);
        this.zzaZs.b(bundle);
    }

    public void setArguments(Bundle bundle) {
        super.setArguments(bundle);
    }

    protected IMapFragmentDelegate zzDE() {
        this.zzaZs.i();
        return this.zzaZs.a() == null ? null : ((k) this.zzaZs.a()).h();
    }
}
