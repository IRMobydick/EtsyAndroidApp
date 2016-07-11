package com.appboy.models;

import bo.app.ce;
import bo.app.da;
import bo.app.fj;
import com.appboy.support.AppboyLogger;
import com.etsy.android.lib.models.ResponseConstants;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public abstract class InAppMessageImmersiveBase extends InAppMessageBase implements IInAppMessageImmersive {
    private String f932e;
    private int f933f;
    private int f934g;
    private boolean f935h;
    private List<MessageButton> f936i;

    protected InAppMessageImmersiveBase() {
        this.f933f = 0;
        this.f934g = 0;
    }

    public InAppMessageImmersiveBase(JSONObject jSONObject, ce ceVar) {
        this(jSONObject, ceVar, jSONObject.optString(ResponseConstants.HEADER), jSONObject.optInt("header_text_color"), jSONObject.optInt("close_btn_color"));
        if (jSONObject.optJSONArray("btns") != null) {
            List arrayList = new ArrayList();
            JSONArray optJSONArray = jSONObject.optJSONArray("btns");
            for (int i = 0; i < optJSONArray.length(); i++) {
                arrayList.add(new MessageButton(optJSONArray.optJSONObject(i)));
            }
            setMessageButtons(arrayList);
        }
    }

    private InAppMessageImmersiveBase(JSONObject jSONObject, ce ceVar, String str, int i, int i2) {
        super(jSONObject, ceVar);
        this.f933f = 0;
        this.f934g = 0;
        this.f932e = str;
        this.f933f = i;
        this.f934g = i2;
    }

    public String getHeader() {
        return this.f932e;
    }

    public int getHeaderTextColor() {
        return this.f933f;
    }

    public int getCloseButtonColor() {
        return this.f934g;
    }

    public List<MessageButton> getMessageButtons() {
        return this.f936i;
    }

    public void setMessageButtons(List<MessageButton> list) {
        this.f936i = list;
    }

    public void setHeader(String str) {
        this.f932e = str;
    }

    public void setHeaderTextColor(int i) {
        this.f933f = i;
    }

    public void setCloseButtonColor(int i) {
        this.f934g = i;
    }

    public boolean logButtonClick(MessageButton messageButton) {
        if (fj.m353b(this.b) && fj.m353b(this.c)) {
            String str = a;
            return false;
        } else if (messageButton == null) {
            AppboyLogger.m670w(a, "Message button was null. Ignoring button click.");
            return false;
        } else if (this.f935h) {
            AppboyLogger.m666i(a, "Button click already logged for this message. Ignoring.");
            return false;
        } else if (this.d == null) {
            AppboyLogger.m664e(a, "Cannot log a button click because the AppboyManager is null.");
            return false;
        } else {
            try {
                this.d.m57a(da.m156a(this.b, this.c, messageButton));
                this.f935h = true;
                return true;
            } catch (Throwable e) {
                this.d.m56a(e);
                return false;
            }
        }
    }
}
