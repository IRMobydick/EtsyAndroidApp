package com.appboy.models;

import bo.app.ce;
import bo.app.da;
import bo.app.fj;
import com.appboy.support.AppboyLogger;
import org.json.JSONObject;

public abstract class IInAppMessageHtmlBase extends InAppMessageBase implements IInAppMessageHtml {
    private String f929e;
    private String f930f;
    private boolean f931g;

    protected IInAppMessageHtmlBase() {
        this.f931g = false;
    }

    public IInAppMessageHtmlBase(JSONObject jSONObject, ce ceVar) {
        super(jSONObject, ceVar);
        this.f931g = false;
        if (!fj.m354c(jSONObject.optString("zipped_assets_url"))) {
            this.f929e = jSONObject.optString("zipped_assets_url");
        }
    }

    public String getLocalAssetsDirectoryUrl() {
        return this.f930f;
    }

    public String getAssetsZipRemoteUrl() {
        return this.f929e;
    }

    public void setLocalAssetsDirectoryUrl(String str) {
        this.f930f = str;
    }

    public void setAssetsZipRemoteUrl(String str) {
        this.f929e = str;
    }

    public boolean logButtonClick(String str) {
        if (fj.m353b(this.b) && fj.m353b(this.c)) {
            String str2 = a;
            return false;
        } else if (this.f931g) {
            AppboyLogger.m666i(a, "Button click already logged for this message. Ignoring.");
            return false;
        } else if (this.d == null) {
            AppboyLogger.m664e(a, "Cannot log a button click because the AppboyManager is null.");
            return false;
        } else {
            try {
                this.d.m57a(da.m157a(this.b, this.c, str));
                this.f931g = true;
                return true;
            } catch (Throwable e) {
                this.d.m56a(e);
                return false;
            }
        }
    }
}
