package com.google.android.gms.auth.api.signin;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.etsy.android.lib.models.ResponseConstants;
import com.google.android.gms.auth.api.signin.internal.e;
import com.google.android.gms.common.api.Api$ApiOptions.Optional;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

public class GoogleSignInOptions extends AbstractSafeParcelable implements Optional {
    public static final Creator<GoogleSignInOptions> CREATOR;
    public static final GoogleSignInOptions DEFAULT_SIGN_IN;
    private static Comparator<Scope> zzacv;
    public static final Scope zzacw;
    public static final Scope zzacx;
    public static final Scope zzacy;
    final int versionCode;
    private Account zzZB;
    private boolean zzacA;
    private final boolean zzacB;
    private final boolean zzacC;
    private String zzacD;
    private String zzacE;
    private final ArrayList<Scope> zzacz;

    static {
        zzacw = new Scope("profile");
        zzacx = new Scope(ResponseConstants.EMAIL);
        zzacy = new Scope("openid");
        DEFAULT_SIGN_IN = new b().a().b().c();
        CREATOR = new zzb();
        zzacv = new 1();
    }

    GoogleSignInOptions(int i, ArrayList<Scope> arrayList, Account account, boolean z, boolean z2, boolean z3, String str, String str2) {
        this.versionCode = i;
        this.zzacz = arrayList;
        this.zzZB = account;
        this.zzacA = z;
        this.zzacB = z2;
        this.zzacC = z3;
        this.zzacD = str;
        this.zzacE = str2;
    }

    private GoogleSignInOptions(Set<Scope> set, Account account, boolean z, boolean z2, boolean z3, String str, String str2) {
        this(2, new ArrayList(set), account, z, z2, z3, str, str2);
    }

    @Nullable
    public static GoogleSignInOptions zzco(@Nullable String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        JSONObject jSONObject = new JSONObject(str);
        Set hashSet = new HashSet();
        JSONArray jSONArray = jSONObject.getJSONArray("scopes");
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            hashSet.add(new Scope(jSONArray.getString(i)));
        }
        Object optString = jSONObject.optString("accountName", null);
        return new GoogleSignInOptions(hashSet, !TextUtils.isEmpty(optString) ? new Account(optString, "com.google") : null, jSONObject.getBoolean("idTokenRequested"), jSONObject.getBoolean("serverAuthRequested"), jSONObject.getBoolean("forceCodeForRefreshToken"), jSONObject.optString("serverClientId", null), jSONObject.optString("hostedDomain", null));
    }

    private JSONObject zzpi() {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONArray jSONArray = new JSONArray();
            Collections.sort(this.zzacz, zzacv);
            Iterator it = this.zzacz.iterator();
            while (it.hasNext()) {
                jSONArray.put(((Scope) it.next()).zzrw());
            }
            jSONObject.put("scopes", jSONArray);
            if (this.zzZB != null) {
                jSONObject.put("accountName", this.zzZB.name);
            }
            jSONObject.put("idTokenRequested", this.zzacA);
            jSONObject.put("forceCodeForRefreshToken", this.zzacC);
            jSONObject.put("serverAuthRequested", this.zzacB);
            if (!TextUtils.isEmpty(this.zzacD)) {
                jSONObject.put("serverClientId", this.zzacD);
            }
            if (!TextUtils.isEmpty(this.zzacE)) {
                jSONObject.put("hostedDomain", this.zzacE);
            }
            return jSONObject;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        try {
            GoogleSignInOptions googleSignInOptions = (GoogleSignInOptions) obj;
            if (this.zzacz.size() != googleSignInOptions.zzpj().size() || !this.zzacz.containsAll(googleSignInOptions.zzpj())) {
                return false;
            }
            if (this.zzZB == null) {
                if (googleSignInOptions.getAccount() != null) {
                    return false;
                }
            } else if (!this.zzZB.equals(googleSignInOptions.getAccount())) {
                return false;
            }
            if (TextUtils.isEmpty(this.zzacD)) {
                if (!TextUtils.isEmpty(googleSignInOptions.zzpn())) {
                    return false;
                }
            } else if (!this.zzacD.equals(googleSignInOptions.zzpn())) {
                return false;
            }
            return this.zzacC == googleSignInOptions.zzpm() && this.zzacA == googleSignInOptions.zzpk() && this.zzacB == googleSignInOptions.zzpl();
        } catch (ClassCastException e) {
            return false;
        }
    }

    public Account getAccount() {
        return this.zzZB;
    }

    public Scope[] getScopeArray() {
        return (Scope[]) this.zzacz.toArray(new Scope[this.zzacz.size()]);
    }

    public int hashCode() {
        List arrayList = new ArrayList();
        Iterator it = this.zzacz.iterator();
        while (it.hasNext()) {
            arrayList.add(((Scope) it.next()).zzrw());
        }
        Collections.sort(arrayList);
        return new e().a(arrayList).a(this.zzZB).a(this.zzacD).a(this.zzacC).a(this.zzacA).a(this.zzacB).a();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzb.zza(this, parcel, i);
    }

    public String zzpg() {
        return zzpi().toString();
    }

    public ArrayList<Scope> zzpj() {
        return new ArrayList(this.zzacz);
    }

    public boolean zzpk() {
        return this.zzacA;
    }

    public boolean zzpl() {
        return this.zzacB;
    }

    public boolean zzpm() {
        return this.zzacC;
    }

    public String zzpn() {
        return this.zzacD;
    }

    public String zzpo() {
        return this.zzacE;
    }
}
